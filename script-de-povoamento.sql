use inscripto_api;
-- Ordem: Responsável > Pessoa > Encontro > Equipe > Núcleo > Base > Encontrista > Encontreiro > Habilidade > Habilidades_Encontreiro > Restrições

-- 1. Responsáveis (necessário antes de relacionar encontristas)
INSERT INTO responsavel (telefone, nome)
VALUES 
('31977777777', 'Carlos Souza'),
('21988888888', 'Ana Paula'),
('11966666666', 'Fernanda Lima'),
('21955555555', 'João Pedro'),
('31944444444', 'Mariana Alves'),
('11912341234', 'Patrícia Gomes'),
('21943214321', 'Roberto Dias'),
('31987654321', 'Juliana Rocha'),
('21912349876', 'Eduardo Martins'),
('11987651234', 'Simone Castro'),
('11965439876', 'Bruno Farias'),
('21976543210', 'Helena Duarte'),
('31912344321', 'Marcelo Nunes'),
('21987654321', 'Tatiane Silva'),
('11987654321', 'Ricardo Lopes'),
('11911223344', 'Sônia Martins'),
('21922334455', 'Gustavo Ribeiro'),
('31933445566', 'Patrícia Souza'),
('21944556677', 'Felipe Andrade'),
('11955667788', 'Luciana Barros');

-- 2. Pessoa
INSERT INTO pessoa (nome, apelido, cpf, data_nascimento, telefone, foto_url, bairro, complemento, numero, rua, cep, instituicao_ensino, senha, admin)
VALUES 
('João Silva', 'João', '12345678901', '2000-01-01', '11999999999', 'http://example.com/joao.jpg', 'Centro', 'Apto 101', 123, 'Rua A', '12345678', 'Escola A', 'senha1', false),
('Maria Oliveira', 'Maria', '98765432100', '1995-05-15', '21988888888', 'http://example.com/maria.jpg', 'Bairro B', null, 456, 'Rua B', '87654321', 'Universidade B', 'senha2', false),
('Lucas Pereira', 'Luquinhas', '11122233344', '2002-09-09', '31988887777', 'http://example.com/lucas.jpg', 'Centro', '', 321, 'Rua C', '11223344', 'Instituto C', 'senha3', false),
('Ana Costa', 'Aninha', '22233344455', '1998-03-22', '21977776666', 'http://example.com/ana.jpg', 'Bairro D', 'Casa 2', 789, 'Rua D', '33445566', 'Faculdade D', 'senha4', false),
('Pedro Santos', 'Pedrinho', '33344455566', '1997-07-07', '31966665555', 'http://example.com/pedro.jpg', 'Bairro E', null, 101, 'Rua E', '44556677', 'Escola E', 'senha5', false),
('Carla Mendes', 'Carlinha', '44455566677', '2001-12-12', '11955554444', 'http://example.com/carla.jpg', 'Bairro F', 'Bloco B', 202, 'Rua F', '55667788', 'Instituto F', 'senha6', false),
('Rafael Lima', 'Rafa', '55566677788', '1999-11-11', '21944443333', 'http://example.com/rafael.jpg', 'Bairro G', '', 303, 'Rua G', '66778899', 'Universidade G', 'senha7', false),
('Beatriz Souza', 'Bia', '66677788899', '2003-06-06', '31933332222', 'http://example.com/beatriz.jpg', 'Bairro H', 'Casa 3', 404, 'Rua H', '77889900', 'Escola H', 'senha8', false),
('Gabriel Torres', 'Gabi', '77788899900', '2000-10-10', '11922223333', 'http://example.com/gabriel.jpg', 'Bairro I', 'Apto 202', 505, 'Rua I', '88990011', 'Colégio I', 'senha9', false),
('Larissa Pinto', 'Lari', '88899900011', '1996-04-04', '21933334444', 'http://example.com/larissa.jpg', 'Bairro J', '', 606, 'Rua J', '99001122', 'Universidade J', 'senha10', false),
('Thiago Ramos', 'Thi', '99900011122', '1998-08-08', '31944445555', 'http://example.com/thiago.jpg', 'Bairro K', 'Casa 5', 707, 'Rua K', '00112233', 'Instituto K', 'senha11', false),
('Juliana Alves', 'Ju', '00011122233', '2001-02-02', '11955556666', 'http://example.com/juliana.jpg', 'Bairro L', 'Bloco C', 808, 'Rua L', '11223344', 'Faculdade L', 'senha12', false),
('Paula Fernandes', 'Paulinha', '11133355577', '1997-09-09', '11965439876', 'http://example.com/paula.jpg', 'Bairro M', 'Apto 303', 909, 'Rua M', '22334455', 'Escola M', 'senha13', false),
('Eduardo Silva', 'Dudu', '22244466688', '1996-12-12', '21976543210', 'http://example.com/eduardo.jpg', 'Bairro N', '', 1010, 'Rua N', '33445566', 'Universidade N', 'senha14', false),
('Helena Duarte', 'Lena', '33355577799', '1995-11-11', '31912344321', 'http://example.com/helena.jpg', 'Bairro O', 'Casa 7', 1111, 'Rua O', '44556677', 'Instituto O', 'senha15', false),
('Marcelo Nunes', 'Marcelo', '44466688800', '1998-05-05', '21987654321', 'http://example.com/marcelo.jpg', 'Bairro P', null, 1212, 'Rua P', '55667788', 'Faculdade P', 'senha16', false),
('Tatiane Silva', 'Tati', '55577799911', '2002-03-03', '11987654321', 'http://example.com/tatiane.jpg', 'Bairro Q', 'Bloco D', 1313, 'Rua Q', '66778899', 'Escola Q', 'senha17', false),
('Sônia Martins', 'Sônia', '66688899900', '1994-07-07', '11911223344', 'http://example.com/sonia.jpg', 'Bairro R', 'Apto 404', 1414, 'Rua R', '77889911', 'Escola R', 'senha18', false),
('Gustavo Ribeiro', 'Gus', '77799911122', '1993-08-08', '21922334455', 'http://example.com/gustavo.jpg', 'Bairro S', '', 1515, 'Rua S', '88991122', 'Universidade S', 'senha19', false),
('Patrícia Souza', 'Pat', '88811133344', '1992-09-09', '31933445566', 'http://example.com/patricia.jpg', 'Bairro T', 'Casa 10', 1616, 'Rua T', '99112233', 'Instituto T', 'senha20', false),
('Felipe Andrade', 'Lipe', '99922244455', '1991-10-10', '21944556677', 'http://example.com/felipe.jpg', 'Bairro U', null, 1717, 'Rua U', '11223344', 'Faculdade U', 'senha21', false),
('Luciana Barros', 'Lu', '11144477788', '1990-11-11', '11955667788', 'http://example.com/luciana.jpg', 'Bairro V', 'Bloco E', 1818, 'Rua V', '22334455', 'Escola V', 'senha22', false);

-- 3. Encontro
INSERT INTO encontro (ano, colegio, tema, data)
VALUES 
(2024, 'CBV', 'Eu Seguirei Aonde Fores Senhor', '2024-05-25'),
(2025, 'Carochinha', 'Dai-me Um Coração Semelhante ao Teu', '2025-05-24'),
(2023, 'Santa Clara', 'Vem e Segue-me', '2023-06-10'),
(2022, 'São José', 'Amar Como Jesus Amou', '2022-07-15'),
(2021, 'Dom Bosco', 'Cristo Vive', '2021-08-20'),
(2020, 'São Bento', 'Renascer', '2020-09-12'),
(2019, 'Santa Tereza', 'Novo Caminho', '2019-10-18'),
(2018, 'São Paulo', 'Esperança Viva', '2018-11-11'),
(2017, 'Santa Luzia', 'Luz do Mundo', '2017-12-12'),
(2016, 'Cristo Rei', 'Caminho de Luz', '2016-10-10'),
(2015, 'São Judas', 'Amor Infinito', '2015-09-09'),
(2014, 'Santa Rita', 'Viver em Comunhão', '2014-08-08'),
(2013, 'São Francisco', 'Paz e Bem', '2013-07-07'),
(2012, 'Nossa Senhora', 'Mãe do Amor', '2012-06-06');

-- 4. Equipe
INSERT INTO equipe (id, nome, ano)
VALUES 
(1, 'Sião', 2023),
(2, 'Emaús', 2024),
(3, 'Jordão', 2022),
(4, 'Maná', 2019),
(5, 'Jordão', 2020),
(6, 'Naim', 2021),
(7, 'Cafarnaum', 2018),
(8, 'Storge', 2017),
(9, 'Sião', 2016),
(10, 'Profetas', 2015),
(11, 'Emaús', 2014),
(12, 'Profetas', 2013),
(13, 'Evangelistas', 2012);

-- 5. Núcleo
INSERT INTO nucleo (id_equipe)
VALUES 
(2),
(3),
(5),
(6),
(7),
(11)
;

-- 6. Base
INSERT INTO base (id_equipe, tema)
VALUES 
(1, 'Jurassic Park'),
(4, 'Masterchef'),
(8, 'Piratas do Caribe'),
(9, 'Senhor dos Anéis'),
(10, 'Matrix'),
(12, 'Marvel'),
(13, 'DC Comics');

-- 7. Encontrista (referência a núcleo)
INSERT INTO encontrista (cpf_pessoa, pais_separados, ano_encontro, id_nucleo)
VALUES 
('12345678901', true, 2023, 3),
('11122233344', false, 2023, 2),
('77788899900', false, 2020, 5),
('88899900011', true, 2020, 6),
('11133355577', false, 2018, 7),
('22244466688', true, 2018, 2),
('66688899900', false, 2016, 3),
('77799911122', true, 2015, 5),
('88811133344', false, 2014, 11),
('99922244455', true, 2013, 6),
('11144477788', false, 2012, 7);



-- 8. Responsável_Encontrista
INSERT INTO responsavel_encontrista (telefone_responsavel, cpf_encontrista)
VALUES 
('31977777777', '12345678901'),
('21988888888', '11122233344'),
('11912341234', '77788899900'),
('21943214321', '88899900011'),
('11965439876', '11133355577'),
('21976543210', '22244466688'),
('11911223344', '66688899900'),
('21922334455', '77799911122'),
('31933445566', '88811133344'),
('21944556677', '99922244455'),
('11955667788', '11144477788');

-- 9. Encontreiro
INSERT INTO encontreiro (cpf_pessoa, fez_ejc, nome_responsavel, telefone_responsavel)
VALUES 
('98765432100', true, 'Carlos Souza', '31977777777'),
('22233344455', false, 'Patrícia Gomes', '11912341234'),
('33344455566', true, 'Roberto Dias', '21943214321'),
('44455566677', false, 'Juliana Rocha', '31987654321'),
('55577799911', true, 'Bruno Farias', '11965439876'),
('44466688800', false, 'Helena Duarte', '21976543210'),
('66688899900', true, 'Sônia Martins', '11911223344'),
('77799911122', false, 'Gustavo Ribeiro', '21922334455'),
('88811133344', true, 'Patrícia Souza', '31933445566'),
('99922244455', false, 'Felipe Andrade', '21944556677'),
('11144477788', true, 'Luciana Barros', '11955667788');

-- 10. Habilidades
INSERT INTO habilidade (id, habilidade)
VALUES 
(1, 'Cozinhar'),
(2, 'Organização'),
(3, 'Primeiros Socorros'),
(4, 'Música'),
(5, 'Artesanato'),
(6, 'Fotografia'),
(7, 'Dança'),
(8, 'Teatro'),
(9, 'Pintura'),
(10, 'Costura'),
(11, 'Esportes'),
(12, 'Canto'),
(13, 'Informática');

-- 11. Habilidades_Encontreiro
INSERT INTO habilidades_encontreiro (cpf_encontreiro, id_habilidade)
VALUES 
('98765432100', 1),
('98765432100', 2),
('22233344455', 3),
('33344455566', 4),
('44455566677', 5),
('44455566677', 6),
('55577799911', 7),
('44466688800', 8),
('66688899900', 9),
('77799911122', 10),
('88811133344', 11),
('99922244455', 12),
('11144477788', 13);

-- 12. Restrição Alimentar
INSERT INTO restricao_alimento (cpf, alimento)
VALUES 
('12345678901', 'Glúten'),
('98765432100', 'Lactose'),
('11122233344', 'Nozes'),
('77788899900', 'Ovos'),
('88899900011', 'Peixe'),
('11133355577', 'Frutos do Mar'),
('22244466688', 'Soja'),
('66688899900', 'Amendoim'),
('77799911122', 'Trigo'),
('88811133344', 'Leite'),
('99922244455', 'Camarão'),
('11144477788', 'Tomate');

-- 13. Restrição Medicamento
INSERT INTO restricao_medicamento (cpf, medicamento)
VALUES 
('12345678901', 'Ibuprofeno'),
('98765432100', 'Paracetamol'),
('11122233344', 'Dipirona'),
('77788899900', 'Amoxicilina'),
('88899900011', 'Cetirizina'),
('11133355577', 'Penicilina'),
('22244466688', 'AAS'),
('66688899900', 'Losartana'),
('77799911122', 'Omeprazol'),
('88811133344', 'Metformina'),
('99922244455', 'Ibuprofeno'),
('11144477788', 'Dipirona');

-- 14. Eh_familiar
insert into eh_familiar (cpf_encontreiro1, cpf_encontreiro2,trabalhar_junto,relacao)
VALUES
('98765432100', '22233344455', 1, 'irmão'),
('55577799911', '44466688800', 0, 'primos'),
('66688899900', '77799911122', 1, 'irmãos'),
('88811133344', '99922244455', 0, 'primos'),
('11144477788', '66688899900', 1, 'mãe e filha'),
('77799911122', '88811133344', 0, 'amigos'),
('99922244455', '11144477788', 1, 'tios');

-- 15. Coordenador
INSERT INTO coordenador (cpf_pessoa)
VALUES
('33344455566'),
('55577799911'),
('66688899900'),
('77799911122'),
('88811133344'),
('99922244455'),
('11144477788');

-- 16. Coordenador_equipe
INSERT INTO coordenador_equipe (id_equipe, cpf_coordenador)
VALUES
(1, '33344455566'),
(7, '55577799911'),
(9, '66688899900'),
(10, '77799911122'),
(11, '88811133344'),
(12, '99922244455'),
(13, '11144477788');

-- 17. Registro_encontreiro
INSERT INTO registro_encontreiro (id_equipe, cpf_encontreiro, ano_encontro)
VALUES
(1, '33344455566', 2023),
(7, '55577799911', 2018),
(9, '66688899900', 2016),
(10, '77799911122', 2015),
(11, '88811133344', 2023),
(12, '99922244455', 2023),
(13, '11144477788', 2016);

-- 18. Insere Admin
INSERT INTO pessoa (nome, apelido, cpf, data_nascimento, telefone, foto_url, bairro, complemento, numero, rua, cep, instituicao_ensino, senha, admin)
VALUES 
('Rodrigo Souza', 'Rod', '10718041461', '2004-05-27', '81996576508', 'foto.com', 'Várzea', 'Casa', '298', 'Rua Francisco Lacerda', '50741150', 'CESAR School', 'admin', true);	
