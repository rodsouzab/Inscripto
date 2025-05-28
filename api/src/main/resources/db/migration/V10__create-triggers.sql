DELIMITER //

CREATE TRIGGER trg_no_nucleo_if_base
    BEFORE INSERT ON nucleo
    FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM base WHERE id_equipe = NEW.id_equipe) THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Esta equipe já é uma base e não pode ser adicionada como núcleo.';
END IF;
END;
//

CREATE TRIGGER trg_no_base_if_nucleo
    BEFORE INSERT ON base
    FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM nucleo WHERE id_equipe = NEW.id_equipe) THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Esta equipe já é um núcleo e não pode ser adicionada como base.';
END IF;
END;
//

DELIMITER ;


