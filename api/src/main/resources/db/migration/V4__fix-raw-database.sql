create table if not exists coordenador (
                             cpf_pessoa varchar(11) primary key,
                             foreign key(cpf_pessoa) references pessoa(cpf)
);

create table if not exists coordenador_equipe (
                                    id_equipe int,
                                    cpf_coordenador varchar(11),
                                    primary key(id_equipe, cpf_coordenador),
                                    foreign key(id_equipe) references equipe(id),
                                    foreign key(cpf_coordenador) references coordenador(cpf_pessoa)
);

create table if not exists registro_encontreiro (
                                      id_equipe int,
                                      cpf_encontreiro varchar(11),
                                      ano_encontro int,
                                      primary key(id_equipe, cpf_encontreiro, ano_encontro),
                                      foreign key(id_equipe) references equipe(id),
                                      foreign key(cpf_encontreiro) references encontreiro(cpf_pessoa),
                                      foreign key(ano_encontro) references encontro(ano)
);
