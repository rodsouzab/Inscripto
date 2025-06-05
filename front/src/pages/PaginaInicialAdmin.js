import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import '../styles/PaginaInicial.css';

function PaginaInicialAdmin() {
  const { cpf } = useParams();
  const [apelido, setApelido] = useState('');
  const [fotoUrl, setFotoUrl] = useState('');
  const [form, setForm] = useState({ ano: "", colegio: "", tema: "", data: "" });
  const [formEdicao, setFormEdicao] = useState({ colegio: "", tema: "", data: "" });
  const [encontros, setEncontros] = useState([]);
  const [editandoEncontroId, setEditandoEncontroId] = useState(null);

  useEffect(() => {
    const buscarPessoa = async () => {
      try {
        const response = await fetch(`http://localhost:8080/pessoa/${cpf}`);
        if (response.ok) {
          const data = await response.json();
          setApelido(data.apelido);
          setFotoUrl(data.foto_url);
        } else {
          setApelido('usuário');
        }
      } catch (error) {
        console.error('Erro ao buscar dados da pessoa:', error);
        setApelido('usuário');
      }
    };

    buscarPessoa();
    buscarEncontros();
  }, [cpf]);

  const buscarEncontros = async () => {
    try {
      const response = await fetch("http://localhost:8080/encontros");
      const data = await response.json();
      const encontrosOrdenados = (data || []).sort((a, b) => b.ano - a.ano);
      setEncontros(encontrosOrdenados);
    } catch (error) {
      console.error("Erro ao buscar encontros:", error);
    }
  };

  const criarEncontro = async (e) => {
    e.preventDefault();
    const encontro = {
      ano: parseInt(form.ano),
      colegio: form.colegio,
      tema: form.tema,
      data: form.data,
    };

    try {
      const response = await fetch("http://localhost:8080/encontros", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(encontro),
      });

      if (response.ok || response.status === 201) {
        alert("Encontro criado com sucesso!");
        setForm({ ano: "", colegio: "", tema: "", data: "" });
        buscarEncontros();
      } else {
        alert("Erro ao criar encontro.");
      }
    } catch (error) {
      console.error("Erro:", error);
      alert("Erro ao conectar com o servidor.");
    }
  };

  const iniciarEdicao = (encontro) => {
    setEditandoEncontroId(encontro.ano);
    setFormEdicao({
      colegio: encontro.colegio || "",
      tema: encontro.tema || "",
      data: encontro.data || "",
    });
  };

  const salvarEdicao = async (ano) => {
    const encontroAtualizado = {
      ano: parseInt(ano),
      colegio: formEdicao.colegio,
      tema: formEdicao.tema,
      data: formEdicao.data,
    };

    try {
      const response = await fetch("http://localhost:8080/encontros", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(encontroAtualizado),
      });

      if (response.ok) {
        alert("Encontro atualizado com sucesso!");
        setEditandoEncontroId(null);
        setFormEdicao({ colegio: "", tema: "", data: "" });
        buscarEncontros();
      } else {
        alert("Erro ao atualizar encontro.");
      }
    } catch (error) {
      console.error("Erro ao atualizar encontro:", error);
      alert("Erro de conexão ao atualizar encontro.");
    }
  };

  const apagarEncontro = async (ano) => {
    if (window.confirm("Tem certeza que deseja apagar este encontro?")) {
      try {
        const response = await fetch(`http://localhost:8080/encontros/${ano}`, {
          method: "DELETE",
        });

        if (response.ok || response.status === 204) {
          alert("Encontro apagado com sucesso!");
          buscarEncontros();
        } else {
          alert("Erro ao apagar encontro.");
        }
      } catch (error) {
        console.error("Erro ao apagar encontro:", error);
        alert("Erro de conexão ao apagar encontro.");
      }
    }
  };

  const anoAtual = new Date().getFullYear();

  return (
    <div className="pagina-inicial">
      <div className="top-bar">
        <Link to="/admin-login">
          <button className="botao-sair">Voltar</button>
        </Link>
        {/* Botão Dashboard aprimorado e centralizado */}
        <div style={{ flex: 1, display: "flex", justifyContent: "center" }}>
          <Link to="/dashboard" style={{ textDecoration: "none" }}>
            <button
              className="botao-eac"
              style={{
                margin: 0,
                padding: "12px 32px",
                fontSize: "18px",
                borderRadius: "10px",
                fontWeight: 600,
                background: "linear-gradient(135deg, #800020 0%, #A02C40 100%)",
                color: "#ffffff",
                border: "none",
                boxShadow: "0 4px 15px rgba(128, 0, 32, 0.3)",
                transition: "all 0.3s ease",
                cursor: "pointer"
              }}
              onMouseOver={e => {
                e.currentTarget.style.background = "linear-gradient(135deg, #A02C40 0%, #B73E56 100%)";
                e.currentTarget.style.transform = "translateY(-2px)";
                e.currentTarget.style.boxShadow = "0 6px 20px rgba(128, 0, 32, 0.4)";
              }}
              onMouseOut={e => {
                e.currentTarget.style.background = "linear-gradient(135deg, #800020 0%, #A02C40 100%)";
                e.currentTarget.style.transform = "translateY(0)";
                e.currentTarget.style.boxShadow = "0 4px 15px rgba(128, 0, 32, 0.3)";
              }}
            >
              📊 Dashboard
            </button>
          </Link>
        </div>
        <Link to={`/perfil-admin/${cpf}`} className="profile-link">
          {fotoUrl ? (
            <img src={fotoUrl} alt="Foto de perfil" className="profile-image" />
          ) : (
            <div className="profile-placeholder">FOTO<br />DE<br />PERFIL</div>
          )}
        </Link>
      </div>

      <div className="conteudo-central">
        <p className="mensagem-boas-vindas">Bem-vindo(a), {apelido}!</p>
        <h2 className="titulo-encontro">Criar Novo Encontro</h2>
        <form onSubmit={criarEncontro} className="formulario-encontro">
          <input
            type="number"
            placeholder="Ano"
            value={form.ano}
            onChange={(e) => setForm({ ...form, ano: e.target.value })}
            required
          />
          <input
            type="text"
            placeholder="Colégio"
            value={form.colegio}
            onChange={(e) => setForm({ ...form, colegio: e.target.value })}
          />
          <input
            type="text"
            placeholder="Tema"
            value={form.tema}
            onChange={(e) => setForm({ ...form, tema: e.target.value })}
          />
          <input
            type="date"
            value={form.data}
            onChange={(e) => setForm({ ...form, data: e.target.value })}
          />
          <button type="submit" className="botao-encontro">Criar</button>
        </form>

        <h2 className="titulo-encontro">Encontros Registrados</h2>
        <div className="lista-encontros">
          {encontros.map((encontro) => (
            <div key={encontro.ano} className="item-encontro">
              <strong>{encontro.ano}</strong>
              <p><strong>Colégio:</strong> {encontro.colegio}</p>
              <p><strong>Tema:</strong> {encontro.tema}</p>
              <p><strong>Data:</strong> {encontro.data}</p>

              <div style={{ marginTop: "10px" }}>
                <button className="botao-encontro" onClick={() => iniciarEdicao(encontro)}>Editar</button>
                <button className="botao-encontro" onClick={() => apagarEncontro(encontro.ano)}>Apagar</button>
                {encontro.ano === anoAtual && (
                  <Link to={`/encontro-atual/${cpf}/${encontro.ano}`}>
                    <button className="botao-encontro">Visualizar Encontro Atual</button>
                  </Link>
                )}
              </div>

              {editandoEncontroId === encontro.ano && (
                <div className="formulario-encontro">
                  <input
                    type="text"
                    placeholder="Colégio"
                    value={formEdicao.colegio}
                    onChange={(e) => setFormEdicao({ ...formEdicao, colegio: e.target.value })}
                  />
                  <input
                    type="text"
                    placeholder="Tema"
                    value={formEdicao.tema}
                    onChange={(e) => setFormEdicao({ ...formEdicao, tema: e.target.value })}
                  />
                  <input
                    type="date"
                    value={formEdicao.data}
                    onChange={(e) => setFormEdicao({ ...formEdicao, data: e.target.value })}
                  />
                  <button className="botao-encontro" onClick={() => salvarEdicao(encontro.ano)}>Salvar</button>
                  <button className="botao-encontro" onClick={() => setEditandoEncontroId(null)}>Cancelar</button>
                </div>
              )}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default PaginaInicialAdmin;
