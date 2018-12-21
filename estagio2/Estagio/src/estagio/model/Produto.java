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
public class Produto {
    private int prod_id;
    private String prod_nome;
    private double prod_valor;
    private double prod_valor_compra;
    private int prod_estoque;
    private Fornecedor fornecedor;
    private Categoria categoria;

    public Produto() {
    }

    public Produto(int prod_id, String prod_nome, double prod_valor, double prod_valor_compra, int prod_estoque, Fornecedor fornecedor,Categoria categoria) {
        this.prod_id = prod_id;
        this.prod_nome = prod_nome;
        this.prod_valor = prod_valor;
        this.prod_valor_compra = prod_valor_compra;
        this.prod_estoque = prod_estoque;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_nome() {
        return prod_nome;
    }

    public void setProd_nome(String prod_nome) {
        this.prod_nome = prod_nome;
    }

    public double getProd_valor() {
        return prod_valor;
    }

    public void setProd_valor(double prod_valor) {
        this.prod_valor = prod_valor;
    }

    public double getProd_valor_compra() {
        return prod_valor_compra;
    }

    public void setProd_valor_compra(double prod_valor_compra) {
        this.prod_valor_compra = prod_valor_compra;
    }

    public int getProd_estoque() {
        return prod_estoque;
    }

    public void setProd_estoque(int prod_estoque) {
        this.prod_estoque = prod_estoque;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return getProd_nome();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    
}
