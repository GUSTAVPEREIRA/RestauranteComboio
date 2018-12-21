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
public class Fornecedor {
    private int for_id;
    private String for_nome;
    private String for_cnpj;
    private String for_ie;
    private String for_celular;
    private String for_telefone;
    private String for_cep;

    public Fornecedor() {
    }

    public Fornecedor(int for_id, String for_nome, String for_cnpj, String for_ie, String for_celular, String for_telefone, String for_cep) {
        this.for_id = for_id;
        this.for_nome = for_nome;
        this.for_cnpj = for_cnpj;
        this.for_ie = for_ie;
        this.for_celular = for_celular;
        this.for_telefone = for_telefone;
        this.for_cep = for_cep;
    }

    public int getFor_id() {
        return for_id;
    }

    public void setFor_id(int for_id) {
        this.for_id = for_id;
    }

    public String getFor_nome() {
        return for_nome;
    }

    public void setFor_nome(String for_nome) {
        this.for_nome = for_nome;
    }

    public String getFor_cnpj() {
        return for_cnpj;
    }

    public void setFor_cnpj(String for_cnpj) {
        this.for_cnpj = for_cnpj;
    }

    public String getFor_ie() {
        return for_ie;
    }

    public void setFor_ie(String for_ie) {
        this.for_ie = for_ie;
    }

    public String getFor_celular() {
        return for_celular;
    }

    public void setFor_celular(String for_celular) {
        this.for_celular = for_celular;
    }

    public String getFor_telefone() {
        return for_telefone;
    }

    public void setFor_telefone(String for_telefone) {
        this.for_telefone = for_telefone;
    }

    public String getFor_cep() {
        return for_cep;
    }

    public void setFor_cep(String for_cep) {
        this.for_cep = for_cep;
    }

    @Override
    public String toString() {
        return getFor_nome();
    }

}
