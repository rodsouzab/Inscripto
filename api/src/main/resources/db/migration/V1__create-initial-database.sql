create table pessoa (
                        nome varchar(100),
                        apelido varchar(50),
                        cpf varchar(11) primary key,
                        data_nascimento date,
                        telefone varchar(15),
                        foto_url varchar(255),
                        bairro varchar(100),
                        complemento varchar(50),
                        numero int,
                        rua varchar(100),
                        cep varchar(8),
                        instituicao_ensino varchar(100)
);

create table restricao (
                           id int primary key,
                           cpf_pessoa varchar(11),
                           foreign key (cpf_pessoa) references pessoa(cpf)
);

create table restricao_medicamento (
                                       id_restricao int,
                                       medicamento varchar(255) not null,
                                       primary key(id_restricao, medicamento),
                                       foreign key(id_restricao) references restricao(id)
);

create table restricao_alimento (
                                    id_restricao int,
                                    alimento varchar(255) not null,
                                    primary key(id_restricao, alimento),
                                    foreign key(id_restricao) references restricao(id)
);

create table encontreiro (
                             cpf_pessoa varchar(11) primary key,
                             nome_responsavel varchar(100),
                             telefone_responsavel varchar(15),
                             fez_ejc bool,
                             foreign key(cpf_pessoa) references pessoa(cpf)
);

create table habilidade (
                            id int primary key,
                            habilidade varchar(100) not null
);

create table habilidades_encontreiro (
                                         cpf_encontreiro varchar(11),
                                         id_habilidade int,
                                         primary key(cpf_encontreiro, id_habilidade),
                                         foreign key(cpf_encontreiro) references encontreiro(cpf_pessoa),
                                         foreign key(id_habilidade) references habilidade(id)
);

create table eh_familiar (
                             cpf_encontreiro1 varchar(11),
                             cpf_encontreiro2 varchar(11),
                             trabalhar_junto bool,
                             relacao varchar(50),
                             primary key(cpf_encontreiro1, cpf_encontreiro2),
                             foreign key(cpf_encontreiro1) references encontreiro(cpf_pessoa),
                             foreign key(cpf_encontreiro2) references encontreiro(cpf_pessoa)
);

create table encontro (
                          ano int primary key,
                          colegio varchar(50),
                          tema varchar(255),
                          data date
);

create table equipe (
                        id int primary key,
                        nome varchar(20),
                        ano int
);

create table base (
                      id_equipe int primary key,
                      tema varchar(50),
                      foreign key(id_equipe) references equipe(id)
);

create table nucleo (
                        id_equipe int primary key,
                        foreign key(id_equipe) references equipe(id)
);

create table encontrista (
                             cpf_pessoa varchar(11) primary key,
                             pais_separados bool,
                             ano_encontro int,
                             id_nucleo int,
                             foreign key(cpf_pessoa) references pessoa(cpf),
                             foreign key(ano_encontro) references encontro(ano),
                             foreign key(id_nucleo) references nucleo(id_equipe)
);

create table responsavel (
                             telefone varchar(15) primary key,
                             nome varchar(100) not null
);

create table responsavel_encontrista (
                                         telefone_responsavel varchar(15),
                                         cpf_encontrista varchar(11),
                                         primary key(telefone_responsavel, cpf_encontrista),
                                         foreign key(telefone_responsavel) references responsavel(telefone),
                                         foreign key(cpf_encontrista) references encontrista(cpf_pessoa)
);



create table coordenador (
                             cpf_pessoa varchar(11) primary key,
                             foreign key(cpf_pessoa) references pessoa(cpf)
);

create table coordenador_equipe (
                                    id_equipe int,
                                    cpf_coordenador varchar(11),
                                    primary key(id_equipe, cpf_coordenador),
                                    foreign key(id_equipe) references equipe(id),
                                    foreign key(cpf_coordenador) references coordenador(cpf_pessoa)
);

create table registro_encontreiro (
                                      id_equipe int,
                                      cpf_encontreiro varchar(11),
                                      ano_encontro int,
                                      primary key(id_equipe, cpf_encontreiro, ano_encontro),
                                      foreign key(id_equipe) references equipe(id),
                                      foreign key(cpf_encontreiro) references encontreiro(cpf_pessoa),
                                      foreign key(ano_encontro) references encontro(ano)
);


