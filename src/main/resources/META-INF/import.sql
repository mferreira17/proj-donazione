insert into Colaborador (id, nome, celular, habilidade, profissao, email, senha) values (1, 'CAC Admin', '00000000000','Não sei', 'Administrador do CAC', 'cac@gmail.com', '$2a$10$BKm4MGZKEfZFsa/dBdHnD.nj7QWMqmHiAaIesqnXsp9lBcZtH2yba');

insert into Grupo (id, nome, descricao) values (1, 'CAC', 'Centro Alternativo Cultural');

insert into Permissao (id, nome) values (1, 'ADMINISTRADOR');
insert into Permissao (id, nome) values (2, 'COLABORADOR');

insert into Usuario_Grupo (colaboradores_id, grupos_id) values (1, 1);

insert into Grupo_Permissao (grupos_id, permissoes_id) values (1, 1);
insert into Grupo_Permissao (grupos_id, permissoes_id) values (1, 2);
