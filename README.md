# 🙏 Inscripto — Sistema de Gerenciamento de Encontros Religiosos

✨ Bem-vindo ao **Inscripto**, um sistema desenvolvido para organizar e registrar de forma eficiente os **encontros religiosos**, participantes e suas funções. Criado com **Java Spring Boot** no backend e **React** no frontend!

---

## 🚀 Funcionalidades

- 👤 **Cadastro completo de pessoas**, incluindo:
  - Nome, apelido, CPF, endereço, data de nascimento, telefone, foto, instituição de ensino
  - Restrições alimentares e alergias

- 🧍‍♂️ **Encontristas**:
  - Participantes dos encontros
  - Devem ter pelo menos um responsável cadastrado (com nome e telefone)
  - Podem ou não participar de um Encontro
  - São parte da Equipe com papel de Encontrista

- 🧑‍🔧 **Encontreiros**:
  - Trabalham nos encontros e podem ter habilidades específicas
  - Devem pertencer a uma Equipe (📦 Base ou 🧠 Núcleo)
  - Podem ser coordenadores de equipe
  - Classificação:
    - 🧒 Adolescente: até 18 anos, sem EJC
    - 🧑 Jovem: ≥18 com EJC ou ≥19 com ou sem EJC
  - Podem ter vínculos familiares com outros participantes

- 📅 **Encontros**:
  - Informações como ano, colégio, tema e data

- 🛡️ Suporte à gestão de participantes, registros e organização de equipes

---

## 🧩 Tecnologias

### 🔙 Backend (Java + Spring Boot)
- Spring Web
- Spring Data JPA
- Spring Security *(opcional)*
- **MySQL** 🐬

### 🌐 Frontend (React)
- React.js + Hooks
- Axios
- React Router
- TailwindCSS *(ou outro framework de UI)*

### 🧪 Ferramentas de Apoio
- **Postman** 📬 — Testes de API REST
- **DBeaver** 🐿️ — Visualização e manipulação do banco de dados MySQL

---

## 📸 Telas

Aqui estão algumas telas do sistema **Inscripto**:

### 📝 Página de Cadastro de Pessoa
![Cadastro](./screenshots/cadastro-pessoa.png)

> 🔍 As imagens devem estar na pasta `screenshots/` dentro do repositório. Se preferir, use links externos de hospedagem como Imgur ou GitHub Issues.

---

## 👨‍💻 Desenvolvedores

Este projeto foi desenvolvido por:

- **Maria Fernanda Ordonho** — mfso@cesar.school 
- **Rodrigo Souza** — rsb2@cesar.school
- **Tiago Gurgel** — tgafa@cesar.school
