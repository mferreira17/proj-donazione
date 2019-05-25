/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donazo.donazione.entities.tipo;

public enum TipoPerfilUsuario {

    ADMINISTRADOR('A', "Administrador"),
    COLABORADOR('C', "Colaborador"),
    VOLUNTARIO('V', "Voluntárior");

    private Character valor;

    private String descricao;

    private TipoPerfilUsuario(final Character tipo, final String descricao) {

        this.valor = tipo;
        this.descricao = descricao;
    }

    public static TipoPerfilUsuario obter(final char valor) {

        TipoPerfilUsuario resposta = null;
        final TipoPerfilUsuario[] values = TipoPerfilUsuario.values();
        for (final TipoPerfilUsuario t : values) {
            if (t.getValor() == valor) {
                resposta = t;
                return resposta;
            }
        }
        return resposta;
    }

    public boolean isAdministrador() {

        return this.equals(ADMINISTRADOR);
    }

    public boolean isColaborador() {

        return this.equals(COLABORADOR);
    }

    public boolean isVoluntario() {

        return this.equals(VOLUNTARIO);
    }

    public Character getValor() {

        return this.valor;
    }

    public String getDescricao() {

        return this.descricao;
    }

}
