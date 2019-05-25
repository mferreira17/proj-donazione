package br.com.donazo.donazione.utils;


public class JdbcUtils {
	
	public static final String USUARIO_POR_LOGIN = "SELECT email, senha, ativo, nome FROM colaborador"
			+ " WHERE email = ?";
	
	public static final String PERMISSOES_POR_USUARIO = "SELECT c.email, p.nome as nome_permissao FROM colaboradores_permissoes cp"
			+ " JOIN colaborador c ON c.id = cp.colaborador_id"
			+ " JOIN permissao p ON p.id = cp.permissao_id"
			+ " WHERE c.email = ?";
	
	public static final String PERMISSOES_POR_GRUPO = "SELECT g.id, g.nome, p.nome as nome_permissao FROM grupo_permissao gp"
			+ " JOIN grupo g ON g.id = gp.grupo_id"
			+ " JOIN permissao p ON p.id = gp.permissao_id"
			+ " JOIN colaborador_grupo cg ON cg.grupo_id = g.id"
			+ " JOIN colaborador c ON c.id = cg.colaborador_id"
			+ " WHERE c.email = ?";

}