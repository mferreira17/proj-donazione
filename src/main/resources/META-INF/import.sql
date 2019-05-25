insert into permissao (id, nome, perfil) values 
(1, 'ROLE_ADMINISTRADOR', 'A'), (2, 'ROLE_COLABORADOR', 'C');

-- Usuario: cac@gmail.com
-- Senha: 12345678

insert into colaborador (id, nome, celular, habilidade, profissao,perfil, email, senha, ativo) values 
(1, 'CAC Admin', '00000000000','Não sei', 'Administrador do CAC','A', 'cac@gmail.com', '$2a$10$HHwmrb2NnHA.neLFui4JvepmKcUlevw7hLvI0iGef8YXol2aPtXv.', true);
			
insert into colaboradores_permissoes (colaborador_id, permissao_id) values 
(1, 1)
