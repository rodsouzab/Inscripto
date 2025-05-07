ALTER TABLE habilidades_encontreiro
DROP FOREIGN KEY habilidades_encontreiro_ibfk_2;

ALTER TABLE habilidades_encontreiro
    ADD CONSTRAINT habilidades_encontreiro_ibfk_2
        FOREIGN KEY (id_habilidade) REFERENCES habilidade(id)
            ON DELETE CASCADE;