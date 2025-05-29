DELIMITER //

-- Conta Qtd. de Encontreiros em um encontro
CREATE PROCEDURE contar_encontreiros_por_ano(IN ano INT)
BEGIN
SELECT COUNT(DISTINCT cpf_encontreiro) AS total_encontreiros
FROM registro_encontreiro
WHERE ano_encontro = ano;
END //

-- Lista Habilidades de um Encontreiro
CREATE PROCEDURE listar_habilidades_encontreiro(
    IN p_cpf_encontreiro VARCHAR(11)
)
BEGIN
SELECT h.habilidade
FROM habilidades_encontreiro he
         JOIN habilidade h ON he.id_habilidade = h.id
WHERE he.cpf_encontreiro = p_cpf_encontreiro;
END //

-- Lista restrições alimentares
CREATE PROCEDURE listar_restricoes_alimentares(
    IN p_cpf_encontreiro VARCHAR(11)
)
BEGIN
SELECT alimento
FROM restricao_alimento
WHERE cpf = p_cpf_encontreiro;
END //

DELIMITER ;
