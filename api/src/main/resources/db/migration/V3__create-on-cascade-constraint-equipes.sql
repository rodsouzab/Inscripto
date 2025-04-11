
ALTER TABLE base
DROP FOREIGN KEY base_ibfk_1;

ALTER TABLE nucleo
DROP FOREIGN KEY nucleo_ibfk_1;


ALTER TABLE base
    ADD CONSTRAINT base_ibfk_1
        FOREIGN KEY (id_equipe) REFERENCES equipe(id)
            ON DELETE CASCADE;

ALTER TABLE nucleo
    ADD CONSTRAINT nucleo_ibfk_1
        FOREIGN KEY (id_equipe) REFERENCES equipe(id)
            ON DELETE CASCADE;
