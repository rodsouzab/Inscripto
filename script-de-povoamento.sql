ALTER TABLE Pessoa ADD COLUMN senha VARCHAR(255) NOT NULL;
ALTER TABLE Pessoa ADD COLUMN admin BOOLEAN DEFAULT FALSE;

use inscripto_api;
-- Ordem: Responsável > Pessoa > Encontro > Equipe > Núcleo > Base > Encontrista > Encontreiro > Habilidade > Habilidades_Encontreiro > Restrições

-- 1. Responsáveis (necessário antes de relacionar encontristas)
INSERT INTO responsavel (telefone, nome)
VALUES 
('31977777777', 'Carlos Souza'),
('21988888888', 'Ana Paula');

-- 2. Pessoa
INSERT INTO pessoa (nome, apelido, cpf, data_nascimento, telefone, foto_url, bairro, complemento, numero, rua, cep, instituicao_ensino, senha, admin)
VALUES 
('João Silva', 'João', '12345678901', '2000-01-01', '11999999999', 'http://example.com/joao.jpg', 'Centro', 'Apto 101', 123, 'Rua A', '12345678', 'Escola A', 'senha1', false),
('Maria Oliveira', 'Maria', '98765432100', '1995-05-15', '21988888888', 'http://example.com/maria.jpg', 'Bairro B', null, 456, 'Rua B', '87654321', 'Universidade B', 'senha2', false),
('Lucas Pereira', 'Luquinhas', '11122233344', '2002-09-09', '31988887777', 'http://example.com/lucas.jpg', 'Centro', '', 321, 'Rua C', '11223344', 'Instituto C', 'senha3', false);

-- 3. Encontro
INSERT INTO encontro (ano, colegio, tema, data)
VALUES 
(2023, 'CBV', 'Eu Seguirei Aonde Fores Senhor', '2024-05-25'),
(2024, 'Carochinha', 'Dai-me Um Coração Semelhante ao Teu', '2025-05-24');

-- 4. Equipe
INSERT INTO equipe (id, nome, ano)
VALUES 
(1, 'Sião', 2023),
(2, 'Emaús', 2024),
(3, 'Jordão', 2022),
(4, 'Maná', 2019);

-- 5. Núcleo
INSERT INTO nucleo (id_equipe)
VALUES 
(2),
(3);

-- 6. Base
INSERT INTO base (id_equipe, tema)
VALUES 
(1, 'Jurassic Park'),
(4, 'Masterchef');

-- 7. Encontrista (referência a núcleo)
INSERT INTO encontrista (cpf_pessoa, pais_separados, ano_encontro, id_nucleo)
VALUES 
('12345678901', true, 2023, 3),
('11122233344', false, 2024, 2);

-- 8. Responsável_Encontrista
INSERT INTO responsavel_encontrista (telefone_responsavel, cpf_encontrista)
VALUES 
('31977777777', '12345678901'),
('21988888888', '11122233344');

-- 9. Encontreiro
INSERT INTO encontreiro (cpf_pessoa, fez_ejc, nome_responsavel, telefone_responsavel)
VALUES 
('98765432100', true, 'Carlos Souza', '31977777777');

-- 10. Habilidades
INSERT INTO habilidade (id, habilidade)
VALUES 
(1, 'Cozinhar'),
(2, 'Organização'),
(3, 'Primeiros Socorros');

-- 11. Habilidades_Encontreiro
INSERT INTO habilidades_encontreiro (cpf_encontreiro, id_habilidade)
VALUES 
('98765432100', 1),
('98765432100', 2);

-- 12. Restrição Alimentar
INSERT INTO restricao_alimento (cpf, alimento)
VALUES 
('12345678901', 'Glúten'),
('98765432100', 'Lactose'),
('11122233344', 'Nozes');

-- 13. Restrição Medicamento
INSERT INTO restricao_medicamento (cpf, medicamento)
VALUES 
('12345678901', 'Ibuprofeno'),
('98765432100', 'Paracetamol'),
('11122233344', 'Dipirona');
