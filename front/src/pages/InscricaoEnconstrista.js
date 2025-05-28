import React, { useState, useEffect } from "react";
import { useParams, useNavigate, Link } from "react-router-dom";
import '../styles/InscricaoEncontrista.css';

const InscricaoEncontrista = () => {
  const { cpf, ano } = useParams();
  const navigate = useNavigate();

  const [pessoa, setPessoa] = useState(null);
  const [paisSeparados, setPaisSeparados] = useState(false);
  const [loading, setLoading] = useState(true);

  const [responsavel1, setResponsavel1] = useState("");
  const [numeroResponsavel1, setNumeroResponsavel1] = useState("");
  const [responsavel2, setResponsavel2] = useState("");
  const [numeroResponsavel2, setNumeroResponsavel2] = useState("");

  useEffect(() => {
    const fetchPessoa = async () => {
      try {
        const response = await fetch("http://localhost:8080/pessoas");
        if (!response.ok) throw new Error("Erro ao buscar pessoas");
        const pessoas = await response.json();
        const encontrada = pessoas.find(p => p.cpf === cpf);
        if (encontrada) setPessoa(encontrada);
        else alert("CPF não encontrado.");
      } catch (error) {
        alert("Erro ao carregar dados da pessoa: " + error.message);
      } finally {
        setLoading(false);
      }
    };
    fetchPessoa();
  }, [cpf]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!pessoa) {
      alert("Dados da pessoa não carregados.");
      return;
    }

    try {
      // 1. Criar encontrista
      const dataEncontrista = {
        cpf: pessoa.cpf,
        nome: pessoa.nome,
        apelido: pessoa.apelido,
        data_nascimento: pessoa.data_nascimento,
        telefone: pessoa.telefone,
        foto_url: pessoa.foto_url,
        bairro: pessoa.bairro,
        complemento: pessoa.complemento,
        numero: pessoa.numero,
        rua: pessoa.rua,
        cep: pessoa.cep,
        instituicao_ensino: pessoa.instituicao_ensino,
        senha: pessoa.senha,
        admin: pessoa.admin,
        paisSeparados: paisSeparados,
        encontro: ano,
        nucleo: { id: null }
      };

      const responseEncontrista = await fetch("http://localhost:8080/encontristas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dataEncontrista)
      });

      if (!responseEncontrista.ok) {
        const texto = await responseEncontrista.text();
        throw new Error(`Erro ao criar encontrista: ${responseEncontrista.status} ${responseEncontrista.statusText} - ${texto}`);
      }

      // 2. Criar responsáveis
      const vinculos = [];

      if (responsavel1 && numeroResponsavel1) {
        const resp1 = { nome: responsavel1, telefone: numeroResponsavel1 };
        const response1 = await fetch("http://localhost:8080/responsavel", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(resp1)
        });
        if (!response1.ok) {
          const texto = await response1.text();
          throw new Error(`Erro ao criar responsável 1: ${response1.status} ${response1.statusText} - ${texto}`);
        }
        // adiciona ao array de vínculos
        vinculos.push({ telefoneResponsavel: numeroResponsavel1, cpfEncontrista: pessoa.cpf });
      }

      if (responsavel2 && numeroResponsavel2) {
        const resp2 = { nome: responsavel2, telefone: numeroResponsavel2 };
        const response2 = await fetch("http://localhost:8080/responsavel", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(resp2)
        });
        if (!response2.ok) {
          const texto = await response2.text();
          throw new Error(`Erro ao criar responsável 2: ${response2.status} ${response2.statusText} - ${texto}`);
        }
        vinculos.push({ telefoneResponsavel: numeroResponsavel2, cpfEncontrista: pessoa.cpf });
      }

      // 3. Vincular responsáveis ao encontrista (em lote)
      if (vinculos.length > 0) {
        const responseVinculo = await fetch("http://localhost:8080/responsavel_encontrista/vincular", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(vinculos)
        });

        if (!responseVinculo.ok) {
          const texto = await responseVinculo.text();
          throw new Error(`Erro ao vincular responsáveis: ${responseVinculo.status} ${responseVinculo.statusText} - ${texto}`);
        }
      }

      alert("✅ Inscrição realizada com sucesso!");
      navigate(`/pagina-inicial/${pessoa.cpf}`);

    } catch (error) {
      console.error("Erro na requisição:", error);
      alert("Erro ao realizar inscrição: " + error.message);
    }
  };

  if (loading) return <div>Carregando...</div>;

  return (
    <div className="inscricao-container">
      <Link to={`/verificacao-dados-encontrista/${cpf}/${ano}`} className="botao-voltar">
        Voltar
      </Link>

      <div className="logo-circular">
        EAC<br />{ano}
      </div>

      <form className="formulario-simples" onSubmit={handleSubmit}>
        <label>
          Responsável 1:
          <input
            type="text"
            value={responsavel1}
            onChange={e => setResponsavel1(e.target.value)}
          />
        </label>

        <label>
          Número do responsável 1:
          <input
            type="text"
            value={numeroResponsavel1}
            onChange={e => setNumeroResponsavel1(e.target.value)}
          />
        </label>

        <label>
          Responsável 2:
          <input
            type="text"
            value={responsavel2}
            onChange={e => setResponsavel2(e.target.value)}
          />
        </label>

        <label>
          Número do responsável 2:
          <input
            type="text"
            value={numeroResponsavel2}
            onChange={e => setNumeroResponsavel2(e.target.value)}
          />
        </label>

        <label className="checkbox-label">
          <input
            type="checkbox"
            checked={paisSeparados}
            onChange={() => setPaisSeparados(!paisSeparados)}
          />
          Pais separados?
        </label>

        <button type="submit" className="botao-inscricao">
          Me Inscrever
        </button>
      </form>
    </div>
  );
};

export default InscricaoEncontrista;
