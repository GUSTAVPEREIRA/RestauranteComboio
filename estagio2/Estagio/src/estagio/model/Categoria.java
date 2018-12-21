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
public class Categoria {
    private int cat_id;
    private String cat_nome;

    public Categoria() {
    }
    
    public Categoria(int cat_id, String cat_nome) {
        this.cat_id = cat_id;
        this.cat_nome = cat_nome;
    }
   
    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_nome() {
        return cat_nome;
    }

    public void setCat_nome(String cat_nome) {
        this.cat_nome = cat_nome;
    }
    
}
