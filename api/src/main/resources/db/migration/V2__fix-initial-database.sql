create table if not exists equipe (
                        id int primary key,
                        nome varchar(20),
                        ano int
);

create table if not exists base (
                      id_equipe int primary key,
                      tema varchar(50),
                      foreign key(id_equipe) references equipe(id)
);

create table if not exists nucleo (
                        id_equipe int primary key,
                        foreign key(id_equipe) references equipe(id)
);

create table if not exists encontrista (
                             cpf_pessoa varchar(11) primary key,
                             pais_separados bool,
                             ano_encontro int,
                             id_nucleo int,
                             foreign key(cpf_pessoa) references pessoa(cpf),
                             foreign key(ano_encontro) references encontro(ano),
                             foreign key(id_nucleo) references nucleo(id_equipe)
);

create table if not exists responsavel (
                             telefone varchar(15) primary key,
                             nome varchar(100) not null
);

create table if not exists responsavel_encontrista  (
                                         telefone_responsavel varchar(15),
                                         cpf_encontrista varchar(11),
                                         primary key(telefone_responsavel, cpf_encontrista),
                                         foreign key(telefone_responsavel) references responsavel(telefone),
                                         foreign key(cpf_encontrista) references encontrista(cpf_pessoa)
);

