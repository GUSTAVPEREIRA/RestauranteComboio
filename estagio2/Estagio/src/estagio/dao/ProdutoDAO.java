/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pereira
 */
public class ProdutoDAO {
    public static  Connection con;
    private FornecedorDAO fornecedorDAO;
    public Connection getConnection() {
        return con;
    }
    
    public void setConnection(Connection con) {
        this.con = con;
    }
    
    public void abreConnection()
    {
        setConnection(Conexao.abre());
    }
    
     public boolean inserir(Produto produto) {
        abreConnection();
        String sql = "INSERT INTO produto(prod_nome,prod_valor,prod_valor_compra,prod_estoque,for_id) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getProd_nome());
            stmt.setDouble(2, produto.getProd_valor());
            stmt.setDouble(3, produto.getProd_valor_compra());
            stmt.setInt(4, produto.getProd_estoque());
            stmt.setInt(5, produto.getFornecedor().getFor_id());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Produto produto) {
        abreConnection();
        String sql = "UPDATE produto SET prod_nome=?,prod_valor=?,prod_valor_compra=?,prod_estoque=?"
                + ",for_id=? WHERE cid_id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getProd_nome());
            stmt.setDouble(2, produto.getProd_valor());
            stmt.setDouble(3, produto.getProd_valor_compra());
            stmt.setInt(4, produto.getProd_estoque());
            stmt.setInt(5, produto.getFornecedor().getFor_id());
            stmt.setInt(6, produto.getProd_id());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean Deletar(Produto produto) {
        abreConnection();
        String sql = "DELETE FROM produto WHERE prod_id=?";
        try {
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, produto.getProd_id());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Produto> listar(String busca) {
        abreConnection();
        String sql ;
        if(busca.equals(""))
        sql = "SELECT * FROM produto";
        else
        sql = "SELECT * FROM produto where prod_nome like '"+busca+"%' or prod_valor like '"+busca+"%'";   
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setProd_nome(resultado.getString("prod_nome"));
                produto.setProd_valor(resultado.getDouble("prod_valor")); 
                produto.setFornecedor(fornecedorDAO.listar(resultado.getInt("for_id")));
                produto.setProd_valor_compra(resultado.getDouble("prod_valor_compra"));
                produto.setProd_id(resultado.getInt("prod_id"));
                produto.setProd_estoque(resultado.getInt("prod_estoque"));
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }        
}
