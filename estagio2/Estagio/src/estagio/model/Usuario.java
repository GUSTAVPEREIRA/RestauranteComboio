/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.model;

/**
 *
 * @author Pereira
 */
public class Usuario {
    private int usu_id;
    private String usu_nome;
    private String usu_senha;
    private String usu_tipo;
    private boolean usu_ativo;
    private String usu_login;

    public Usuario() {
    }

    public Usuario(int usu_id, String usu_nome, String usu_senha, String usu_tipo, boolean usu_ativo, String usu_login) {
        this.usu_id = usu_id;
        this.usu_nome = usu_nome;
        this.usu_senha = usu_senha;
        this.usu_tipo = usu_tipo;
        this.usu_ativo = usu_ativo;
        this.usu_login = usu_login;
    }

    public int getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(int usu_id) {
        this.usu_id = usu_id;
    }

    public String getUsu_nome() {
        return usu_nome;
    }

    public void setUsu_nome(String usu_nome) {
        this.usu_nome = usu_nome;
    }

    public String getUsu_senha() {
        return usu_senha;
    }

    public void setUsu_senha(String usu_senha) {
        this.usu_senha = usu_senha;
    }

    public String getUsu_tipo() {
        return usu_tipo;
    }

    public void setUsu_tipo(String usu_tipo) {
        this.usu_tipo = usu_tipo;
    }

    public boolean getUsu_ativo() {
        return usu_ativo;
    }

    public void setUsu_ativo(boolean usu_ativo) {
        this.usu_ativo = usu_ativo;
    }

    public String getUsu_login() {
        return usu_login;
    }

    public void setUsu_login(String usu_login) {
        this.usu_login = usu_login;
    }
    
    
}
