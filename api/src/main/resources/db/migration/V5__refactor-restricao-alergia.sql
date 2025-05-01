drop table if exists restricao_alimento;

drop table if exists restricao_medicamento;

drop table if exists restricao;


create table if not exists restricao_medicamento(
    id int primary key,
    cpf varchar(11),
    medicamento varchar(255) not null,
    foreign key(cpf) references pessoa(cpf)
);

create table if not exists restricao_alimento(
    id int primary key,
    cpf varchar(11),
    alimento varchar(255) not null,
    foreign key(cpf) references pessoa(cpf)
);

