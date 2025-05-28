-- Listar todas as tabelas do banco de dados atual
SHOW TABLES;

-- Exibir a estrutura de uma tabela específica
DESCRIBE encontreiro;

-- Contar o número de registros em cada tabela
SELECT table_name as nome_tabela, table_rows as registros
FROM information_schema.tables
WHERE table_schema = DATABASE();

-- Consultar os 10 primeiros registros de uma tabela
SELECT * FROM encontreiro LIMIT 10;

-- Consulta todas as pessoas com algum tipo de restrição 
SELECT p.nome, ra.alimento, rm.medicamento
from pessoa p
left join restricao_alimento ra on ra.cpf = p.cpf
left join restricao_medicamento rm on rm.cpf = p.cpf
where ra.id is not null or rm.id is not null
ORDER BY p.nome

-- Consulta todas as pessoas com alguma restrição alimentar 
SELECT p.nome, ra.alimento
from pessoa p 
join restricao_alimento ra on ra.cpf = p.cpf

-- Consulta todas as restriçoes alimentares do encontro mais recente, útil para
-- a preparação da equipe responsável pela comida 
SELECT p.nome, ra.alimento
FROM encontreiro e
JOIN pessoa p ON p.cpf = e.cpf_pessoa
JOIN restricao_alimento ra ON ra.cpf = p.cpf
JOIN registro_encontreiro re ON re.cpf_encontreiro = p.cpf
WHERE re.ano_encontro = (
    SELECT MAX(ano_encontro)
    FROM registro_encontreiro
)


-- Lista os encontristas e seus responsáveis, junto com o booleano pais_separados, 
--útil na hora de solicitar as cartinhas para o encontrista
SELECT p.nome, r.nome as nome_responsavel, r.telefone as telefone_responsavel, e.pais_separados
from encontrista e 
join pessoa p on p.cpf = e.cpf_pessoa
join responsavel_encontrista re on re.cpf_encontrista = e.cpf_pessoa
join responsavel r on r.telefone = re.telefone_responsavel


-- Consulta a faixa etária dos encontreiros, dividindo-os em adolescentes e jovens
SELECT 
  en.cpf_pessoa, 
  p.nome,
  CASE
    WHEN TIMESTAMPDIFF(
           YEAR, 
           p.data_nascimento, 
           CURDATE()
         ) <= 18
         AND en.fez_ejc = 0
      THEN 'Adolescente'
    ELSE 'Jovem'
  END AS categoria_etaria
FROM encontreiro AS en
JOIN pessoa AS p 
  ON en.cpf_pessoa = p.cpf;


-- Retorna a quantidade de encontristas e encontreiros em cada encontro distinto
SELECT e.ano, e.tema,
       COUNT(DISTINCT re.cpf_encontreiro) AS total_encontristas,
       COUNT(DISTINCT re.cpf_encontreiro) AS total_encontreiros
FROM encontro e
LEFT JOIN registro_encontreiro re ON re.ano_encontro = e.ano
GROUP BY e.ano, e.tema;

--Consulta todos os parentescos entre encontreiros e se querem trabalhar juntos
SELECT p1.nome AS pessoa, p2.nome AS parente, ehf.relacao, ehf.trabalhar_junto
FROM eh_familiar ehf
JOIN pessoa p1 ON ehf.cpf_encontreiro1 = p1.cpf
JOIN pessoa p2 ON ehf.cpf_encontreiro2 = p2.cpf
ORDER BY p1.nome, ehf.relacao;


-- Consulta todos os coordenadores e suas respectivas equipes e ano de coordenação 
SELECT en.cpf_pessoa, p.nome, eq.nome, eq.ano
FROM encontreiro en
JOIN pessoa p ON en.cpf_pessoa = p.cpf
join registro_encontreiro re on re.cpf_encontreiro = en.cpf_pessoa
join equipe eq on eq.id = re.id_equipe
join coordenador coord on coord.cpf_pessoa = en.cpf_pessoa
join coordenador_equipe coord_eq on coord_eq.cpf_coordenador = coord.cpf_pessoa