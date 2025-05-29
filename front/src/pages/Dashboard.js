import React, { useEffect, useState } from "react";
import { Bar, Pie } from "react-chartjs-2";
import { Chart, CategoryScale, LinearScale, BarElement, ArcElement, Tooltip, Legend } from "chart.js";
import { useNavigate } from "react-router-dom";
import "../styles/Dashboard.css";

Chart.register(CategoryScale, LinearScale, BarElement, ArcElement, Tooltip, Legend);

function Dashboard() {
  const [encontreirosPorAno, setEncontreirosPorAno] = useState({});
  const [encontristasPorAno, setEncontristasPorAno] = useState({});
  const [idadeEncontreiros, setIdadeEncontreiros] = useState([]);
  const [idadeEncontristas, setIdadeEncontristas] = useState([]);
  const [totalEncontreiros, setTotalEncontreiros] = useState(0);
  const [totalEncontristas, setTotalEncontristas] = useState(0);
  const [loading, setLoading] = useState(true);
  const [erro, setErro] = useState(null);
  const navigate = useNavigate();

  // Função auxiliar para buscar data_nascimento por CPF
  async function fetchDataNascimentoPorCpf(cpfs) {
    const uniqueCpfs = Array.from(new Set(cpfs));
    const cpfToNascimento = {};
    await Promise.all(
      uniqueCpfs.map(async cpf => {
        try {
          const res = await fetch(`http://localhost:8080/pessoa/${cpf}`);
          if (res.ok) {
            const pessoa = await res.json();
            cpfToNascimento[cpf] = pessoa.data_nascimento;
          }
        } catch (e) {
          // ignora erro
        }
      })
    );
    return cpfToNascimento;
  }

  useEffect(() => {
    setLoading(true);
    setErro(null);

    Promise.all([
      fetch("http://localhost:8080/encontreiros").then(res => res.json()),
      fetch("http://localhost:8080/encontristas").then(res => res.json()),
      fetch("http://localhost:8080/registroEncontreiro").then(res => res.json())
    ])
      .then(async ([encontreiros, encontristas, registrosEncontreiros]) => {
        setTotalEncontreiros(encontreiros.length);
        setTotalEncontristas(encontristas.length);

        // --- Encontreiros por Ano (usando registroEncontreiro) ---
        const porAnoE = {};
        registrosEncontreiros.forEach(reg => {
          const ano = reg.anoEncontro || reg.ano_encontro;
          if (ano !== undefined && ano !== null) {
            porAnoE[ano] = (porAnoE[ano] || 0) + 1;
          }
        });
        setEncontreirosPorAno(porAnoE);

        // --- Encontristas por Ano (usando encontro.ano) ---
        const porAnoI = {};
        const cpfsEncontristas = [];
        encontristas.forEach(e => {
          const ano = e.encontro?.ano;
          if (ano !== undefined && ano !== null) {
            porAnoI[ano] = (porAnoI[ano] || 0) + 1;
          }
          cpfsEncontristas.push(e.cpf || e.cpf_pessoa);
        });
        setEncontristasPorAno(porAnoI);

        // --- Idades Encontreiros ---
        const cpfsEncontreiros = encontreiros.map(e => e.cpf || e.cpf_pessoa);
        const cpfToNascimentoEncontreiros = await fetchDataNascimentoPorCpf(cpfsEncontreiros);
        const idadesEncontreiros = cpfsEncontreiros.map(cpf => {
          const nascStr = cpfToNascimentoEncontreiros[cpf];
          if (!nascStr) return null;
          const nasc = new Date(nascStr);
          const hoje = new Date();
          let idade = hoje.getFullYear() - nasc.getFullYear();
          const m = hoje.getMonth() - nasc.getMonth();
          if (m < 0 || (m === 0 && hoje.getDate() < nasc.getDate())) idade--;
          return idade;
        }).filter(i => i !== null);
        setIdadeEncontreiros(idadesEncontreiros);

        // --- Idades Encontristas ---
        const cpfToNascimentoEncontristas = await fetchDataNascimentoPorCpf(cpfsEncontristas);
        const idadesEncontristas = cpfsEncontristas.map(cpf => {
          const nascStr = cpfToNascimentoEncontristas[cpf];
          if (!nascStr) return null;
          const nasc = new Date(nascStr);
          const hoje = new Date();
          let idade = hoje.getFullYear() - nasc.getFullYear();
          const m = hoje.getMonth() - nasc.getMonth();
          if (m < 0 || (m === 0 && hoje.getDate() < nasc.getDate())) idade--;
          return idade;
        }).filter(i => i !== null);
        setIdadeEncontristas(idadesEncontristas);

        setLoading(false);
      })
      .catch((err) => {
        setErro("Erro ao carregar dados do dashboard.");
        setLoading(false);
      });
  }, []);

  // Função para agrupar idades em faixas
  function agruparFaixas(idades) {
    const faixas = { "0-15": 0, "16-18": 0, "19-25": 0, "26-35": 0, "36+": 0 };
    idades.forEach(i => {
      if (i <= 15) faixas["0-15"]++;
      else if (i <= 18) faixas["16-18"]++;
      else if (i <= 25) faixas["19-25"]++;
      else if (i <= 35) faixas["26-35"]++;
      else faixas["36+"]++;
    });
    return faixas;
  }

  // Função para calcular a média de idade
  function mediaIdade(idades) {
    if (!idades.length) return 0;
    return (idades.reduce((a, b) => a + b, 0) / idades.length).toFixed(1);
  }

  // Função para ordenar os anos
  function ordenarAnos(obj) {
    return Object.keys(obj).sort((a, b) => Number(a) - Number(b));
  }

  if (loading) {
    return <div className="dashboard-container"><h2>Carregando dados do dashboard...</h2></div>;
  }

  if (erro) {
    return <div className="dashboard-container"><h2>{erro}</h2></div>;
  }

  return (
    <div className="dashboard-container">
      {/* Botão de voltar para o painel admin */}
      <button
        className="dashboard-back-btn"
        onClick={() => navigate(-1)}
      >
        Voltar ao Painel Admin
      </button>
      <h1>Dashboard do EAC</h1>
      <div className="dashboard-cards">
        <div className="dashboard-card">
          <h2>Total de Encontreiros</h2>
          <p>{totalEncontreiros}</p>
          <span>Média de idade: <b>{mediaIdade(idadeEncontreiros)}</b></span>
        </div>
        <div className="dashboard-card">
          <h2>Total de Encontristas</h2>
          <p>{totalEncontristas}</p>
          <span>Média de idade: <b>{mediaIdade(idadeEncontristas)}</b></span>
        </div>
      </div>

      <div className="dashboard-graphs">
        <div className="dashboard-graph">
          <h3>Encontreiros por Ano</h3>
          {ordenarAnos(encontreirosPorAno).length === 0 ? (
            <p>Nenhum dado disponível.</p>
          ) : (
            <Bar
              data={{
                labels: ordenarAnos(encontreirosPorAno),
                datasets: [
                  {
                    label: "Encontreiros",
                    data: ordenarAnos(encontreirosPorAno).map(ano => encontreirosPorAno[ano]),
                    backgroundColor: "#4a90e2",
                  },
                ],
              }}
              options={{
                responsive: true,
                plugins: { legend: { display: false } },
                scales: { x: { title: { display: true, text: "Ano" } }, y: { title: { display: true, text: "Qtd" }, beginAtZero: true } }
              }}
            />
          )}
        </div>
        <div className="dashboard-graph">
          <h3>Encontristas por Ano</h3>
          {ordenarAnos(encontristasPorAno).length === 0 ? (
            <p>Nenhum dado disponível.</p>
          ) : (
            <Bar
              data={{
                labels: ordenarAnos(encontristasPorAno),
                datasets: [
                  {
                    label: "Encontristas",
                    data: ordenarAnos(encontristasPorAno).map(ano => encontristasPorAno[ano]),
                    backgroundColor: "#e94e77",
                  },
                ],
              }}
              options={{
                responsive: true,
                plugins: { legend: { display: false } },
                scales: { x: { title: { display: true, text: "Ano" } }, y: { title: { display: true, text: "Qtd" }, beginAtZero: true } }
              }}
            />
          )}
        </div>
      </div>

      <div className="dashboard-graphs">
        <div className="dashboard-graph">
          <h3>Faixa Etária dos Encontreiros</h3>
          {idadeEncontreiros.length === 0 ? (
            <p>Nenhum dado disponível.</p>
          ) : (
            <Pie
              data={{
                labels: Object.keys(agruparFaixas(idadeEncontreiros)),
                datasets: [
                  {
                    data: Object.values(agruparFaixas(idadeEncontreiros)),
                    backgroundColor: ["#4a90e2", "#50e3c2", "#f5a623", "#e94e77", "#7b4173"],
                  },
                ],
              }}
              options={{ responsive: true }}
            />
          )}
        </div>
        <div className="dashboard-graph">
          <h3>Faixa Etária dos Encontristas</h3>
          {idadeEncontristas.length === 0 ? (
            <p>Nenhum dado disponível.</p>
          ) : (
            <Pie
              data={{
                labels: Object.keys(agruparFaixas(idadeEncontristas)),
                datasets: [
                  {
                    data: Object.values(agruparFaixas(idadeEncontristas)),
                    backgroundColor: ["#e94e77", "#f5a623", "#50e3c2", "#4a90e2", "#7b4173"],
                  },
                ],
              }}
              options={{ responsive: true }}
            />
          )}
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
