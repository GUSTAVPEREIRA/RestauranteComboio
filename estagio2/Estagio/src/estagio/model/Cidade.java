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
public class Cidade {
    private int cid_id;
    private String cid_nome;
    private Estado estado;

    public Cidade() {
    }
    
    public Cidade(int cid_id, String cid_nome, Estado estado) {
        this.cid_id = cid_id;
        this.cid_nome = cid_nome;
        this.estado = estado;
    }

    public int getCid_id() {
        return cid_id;
    }

    public void setCid_id(int cid_id) {
        this.cid_id = cid_id;
    }

    public String getCid_nome() {
        return cid_nome;
    }

    public void setCid_nome(String cid_nome) {
        this.cid_nome = cid_nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
}
