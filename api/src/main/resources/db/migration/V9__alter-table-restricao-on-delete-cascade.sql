ALTER TABLE restricao_alimento
DROP FOREIGN KEY restricao_alimento_ibfk_1;

ALTER TABLE restricao_alimento
    ADD CONSTRAINT restricao_alimento_ibfk_1 FOREIGN KEY (cpf) REFERENCES pessoa(cpf) ON DELETE CASCADE;

ALTER TABLE restricao_medicamento
DROP FOREIGN KEY restricao_medicamento_ibfk_1;

ALTER TABLE restricao_medicamento
    ADD CONSTRAINT restricao_medicamento_ibfk_1 FOREIGN KEY (cpf) REFERENCES pessoa(cpf) ON DELETE CASCADE;