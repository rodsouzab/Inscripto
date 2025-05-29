u# 🙏 Inscripto — Sistema de Gerenciamento de Encontros Religiosos

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
- **MySQL** 🐬

### 🌐 Frontend (React)
- React.js + Hooks
- Axios
- React Router

### 🧪 Ferramentas de Apoio
- **Postman** 📬 — Testes de API REST
- **DBeaver** 🐿️ — Visualização e manipulação do banco de dados MySQL

---
## 📜 Triggers e Procedures do Banco de Dados

### 🔁 Triggers

- **`base.trg_no_base_if_nucleo`**  
  Garante integridade entre a tabela `base` e a tabela `nucleo`.  
  Disparada quando uma operação na tabela `base` requer validação ou sincronização com a tabela `nucleo`.

- **`nucleo.trg_no_nucleo_if_base`**  
  Garante integridade entre a tabela `nucleo` e a tabela `base`.  
  Disparada quando uma operação na tabela `nucleo` exige coerência com dados da tabela `base`.

---

### ⚙️ Procedures

- **`contar_encontreiros_por_ano(ano)`**  
  Conta o número de encontreiros registrados em um dado ano.

- **`listar_habilidades_encontreiro(cpf)`**  
  Lista todas as habilidades vinculadas aos um encontreiro.

- **`listar_restricoes_alimentares(cpf)`**  
  Exibe todas as restrições alimentares registradas para um encontreiro.

---

## 👨‍💻 Desenvolvedores

Este projeto foi desenvolvido por:

- **Maria Fernanda Ordonho** — mfso@cesar.school 
- **Rodrigo Souza** — rsb2@cesar.school
- **Tiago Gurgel** — tgafa@cesar.school
