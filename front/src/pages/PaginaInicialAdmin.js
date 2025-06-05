import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import '../styles/PaginaInicialAdmin.css';

function PaginaInicialAdmin() {
  const { cpf } = useParams();
  const [apelido, setApelido] = useState('');
  const [fotoUrl, setFotoUrl] = useState('');
  const [form, setForm] = useState({ ano: "", colegio: "", tema: "", data: "" });
  const [formEdicao, setFormEdicao] = useState({ colegio: "", tema: "", data: "" });
  const [encontros, setEncontros] = useState([]);
  const [editandoEncontroId, setEditandoEncontroId] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const buscarPessoa = async () => {
      try {
        const response = await fetch(`http://localhost:8080/pessoa/${cpf}`);
        if (response.ok) {
          const data = await response.json();
          setApelido(data.apelido);
          setFotoUrl(data.foto_url);
        } else {
          setApelido('Administrador');
        }
      } catch (error) {
        console.error('Erro ao buscar dados da pessoa:', error);
        setApelido('Administrador');
      }
    };

    buscarPessoa();
    buscarEncontros();
  }, [cpf]);

  const buscarEncontros = async () => {
    setLoading(true);
    try {
      const response = await fetch("http://localhost:8080/encontros");
      const data = await response.json();
      const encontrosOrdenados = (data || []).sort((a, b) => b.ano - a.ano);
      setEncontros(encontrosOrdenados);
    } catch (error) {
      console.error("Erro ao buscar encontros:", error);
    } finally {
      setLoading(false);
    }
  };

  const criarEncontro = async (e) => {
    e.preventDefault();
    
    if (!form.ano || !form.colegio || !form.tema || !form.data) {
      alert("Por favor, preencha todos os campos!");
      return;
    }

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
        alert("✅ Encontro criado com sucesso!");
        setForm({ ano: "", colegio: "", tema: "", data: "" });
        buscarEncontros();
      } else {
        alert("❌ Erro ao criar encontro.");
      }
    } catch (error) {
      console.error("Erro:", error);
      alert("❌ Erro ao conectar com o servidor.");
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
        alert("✅ Encontro atualizado com sucesso!");
        setEditandoEncontroId(null);
        setFormEdicao({ colegio: "", tema: "", data: "" });
        buscarEncontros();
      } else {
        alert("❌ Erro ao atualizar encontro.");
      }
    } catch (error) {
      console.error("Erro ao atualizar encontro:", error);
      alert("❌ Erro de conexão ao atualizar encontro.");
    }
  };

  const apagarEncontro = async (ano) => {
    if (window.confirm("⚠️ Tem certeza que deseja apagar este encontro? Esta ação não pode ser desfeita.")) {
      try {
        const response = await fetch(`http://localhost:8080/encontros/${ano}`, {
          method: "DELETE",
        });

        if (response.ok || response.status === 204) {
          alert("✅ Encontro apagado com sucesso!");
          buscarEncontros();
        } else {
          alert("❌ Erro ao apagar encontro.");
        }
      } catch (error) {
        console.error("Erro ao apagar encontro:", error);
        alert("❌ Erro de conexão ao apagar encontro.");
      }
    }
  };

  const anoAtual = new Date().getFullYear();

  return (
    <div className="pagina-inicial-admin">
      <div className="admin-top-bar">
        <div className="admin-nav-section">
          <Link to="/admin-login">
            <button className="botao-voltar-admin">← Sair da Conta</button>
          </Link>
          
          <Link to="/dashboard" style={{ textDecoration: "none" }}>
            <button className="dashboard-button-enhanced">
              📊 Dashboard
            </button>
          </Link>
        </div>

        <div className="admin-profile-section">
          <span className="admin-welcome-text">Olá, {apelido}!</span>
          <Link to={`/perfil-admin/${cpf}`} className="admin-profile-link">
            {fotoUrl ? (
              <img src={fotoUrl} alt="Foto de perfil" className="admin-profile-image" />
            ) : (
              <div className="admin-profile-placeholder">FOTO<br />PERFIL</div>
            )}
          </Link>
        </div>
      </div>

      <div className="admin-content">
        <h1 className="admin-welcome-message">Painel Administrativo</h1>
        <p className="admin-subtitle">Gerencie encontros e visualize dados do sistema</p>
        
        <div className="admin-sections">
          {/* Seção de Criar Encontro */}
          <div className="admin-section-card">
            <h2 className="admin-section-title">🎯 Criar Novo Encontro</h2>
            <form onSubmit={criarEncontro} className="formulario-encontro-admin">
              <input
                type="number"
                placeholder="Ano do Encontro"
                value={form.ano}
                onChange={(e) => setForm({ ...form, ano: e.target.value })}
                min={new Date().getFullYear()}
                max={new Date().getFullYear() + 10}
                required
              />
              <input
                type="text"
                placeholder="Nome do Colégio/Local"
                value={form.colegio}
                onChange={(e) => setForm({ ...form, colegio: e.target.value })}
                required
              />
              <input
                type="text"
                placeholder="Tema do Encontro"
                value={form.tema}
                onChange={(e) => setForm({ ...form, tema: e.target.value })}
                required
              />
              <input
                type="date"
                value={form.data}
                onChange={(e) => setForm({ ...form, data: e.target.value })}
                required
              />
              <button type="submit" className="botao-criar-encontro">
                Criar Encontro
              </button>
            </form>
          </div>

          {/* Seção de Encontros Registrados */}
          <div className="admin-section-card">
            <h2 className="admin-section-title">📋 Encontros Registrados</h2>
            
            {loading ? (
              <div className="loading-state">
                <div className="loading-spinner"></div>
                <p>Carregando encontros...</p>
              </div>
            ) : (
              <div className="lista-encontros-admin">
                {encontros.length === 0 ? (
                  <div className="empty-state">
                    <div className="empty-state-icon">📝</div>
                    <p>Nenhum encontro registrado ainda.</p>
                    <p>Crie o primeiro encontro usando o formulário ao lado!</p>
                  </div>
                ) : (
                  encontros.map((encontro) => (
                    <div key={encontro.ano} className="item-encontro-admin">
                      {encontro.ano === anoAtual && (
                        <div className="badge-ano-atual">Atual</div>
                      )}
                      
                      <div className="encontro-info">
                        <div className="encontro-ano">EAC {encontro.ano}</div>
                        
                        <div className="encontro-detalhes">
                          <p><strong>Colégio:</strong> {encontro.colegio || "Não informado"}</p>
                          <p><strong>Tema:</strong> {encontro.tema || "Não informado"}</p>
                          <p><strong>Data:</strong> {encontro.data ? new Date(encontro.data).toLocaleDateString('pt-BR') : "Não informada"}</p>
                        </div>
                      </div>

                      <div className="acoes-encontro">
                        <button 
                          className="botao-acao-encontro botao-editar-encontro" 
                          onClick={() => iniciarEdicao(encontro)}
                        >
                          ✏️ Editar
                        </button>
                        
                        <button 
                          className="botao-acao-encontro botao-apagar-encontro" 
                          onClick={() => apagarEncontro(encontro.ano)}
                        >
                          🗑️ Apagar
                        </button>
                        
                        {encontro.ano === anoAtual && (
                          <Link to={`/encontro-atual/${cpf}/${encontro.ano}`}>
                            <button className="botao-acao-encontro botao-visualizar-encontro">
                              👁️ Ver Inscritos
                            </button>
                          </Link>
                        )}
                      </div>

                      {editandoEncontroId === encontro.ano && (
                        <div className="formulario-edicao-encontro">
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
                          
                          <div className="acoes-edicao">
                            <button 
                              className="botao-salvar-edicao" 
                              onClick={() => salvarEdicao(encontro.ano)}
                            >
                              💾 Salvar
                            </button>
                            <button 
                              className="botao-cancelar-edicao" 
                              onClick={() => setEditandoEncontroId(null)}
                            >
                              ❌ Cancelar
                            </button>
                          </div>
                        </div>
                      )}
                    </div>
                  ))
                )}
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default PaginaInicialAdmin;
