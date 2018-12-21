/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.model.Fornecedor;
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
public class FornecedorDAO {
    
   
    public static  Connection con;

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
    
     public boolean inserir(Fornecedor fornecedor) {
        abreConnection();
        String sql = "INSERT INTO fornecedor(for_nome,for_cnpj,for_ie,for_telefone,for_celular,for_cep) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, fornecedor.getFor_nome());
            stmt.setString(2, fornecedor.getFor_cnpj());
            stmt.setString(3, fornecedor.getFor_ie());
            stmt.setString(4, fornecedor.getFor_telefone());
            stmt.setString(5, fornecedor.getFor_celular());
            stmt.setString(6, fornecedor.getFor_cep());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Fornecedor fornecedor) {
        abreConnection();
        String sql = "UPDATE fornecedor SET for_nome=?,for_cnpj=?,for_ie=?,for_telefone=?"
                + ",for_celular=?,for_cep=? WHERE for_id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, fornecedor.getFor_nome());
            stmt.setString(2, fornecedor.getFor_cnpj());
            stmt.setString(3, fornecedor.getFor_ie());
            stmt.setString(4, fornecedor.getFor_telefone());
            stmt.setString(5, fornecedor.getFor_celular());
            stmt.setString(6, fornecedor.getFor_cep());
            stmt.setInt(7, fornecedor.getFor_id());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean Deletar(Fornecedor fornecedor) {
        abreConnection();
        String sql = "DELETE FROM fornecedor WHERE for_id=?";
        try {
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, fornecedor.getFor_id());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Fornecedor listar(int busca) {
        abreConnection();
        Fornecedor fornecedor = new Fornecedor();
        String sql ;
        sql = "SELECT * FROM fornecedor where for_id = ?";
        List<Fornecedor> retorno = new ArrayList<Fornecedor>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, busca);
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                fornecedor.setFor_id(resultado.getInt("for_id"));
                fornecedor.setFor_nome(resultado.getString("for_nome"));
                fornecedor.setFor_cnpj(resultado.getString("for_cnpj"));
                fornecedor.setFor_ie(resultado.getString("for_ie"));
                fornecedor.setFor_telefone(resultado.getString("for_telefone"));
                fornecedor.setFor_celular(resultado.getString("for_celular"));
                fornecedor.setFor_cep(resultado.getString("for_cep"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fornecedor;
    }
    
    public List<Fornecedor> listar(String busca) {
        abreConnection();
        String sql ;
        if(busca.equals(""))
        sql = "SELECT * FROM fornecedor";
        else
        sql = "SELECT * FROM fornecedor where for_nome like '"+busca+"%' or for_cep like '"+busca+"%'";   
        List<Fornecedor> retorno = new ArrayList<Fornecedor>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setFor_id(resultado.getInt("for_id"));
                fornecedor.setFor_nome(resultado.getString("for_nome"));
                fornecedor.setFor_cnpj(resultado.getString("for_cnpj"));
                fornecedor.setFor_ie(resultado.getString("for_ie"));
                fornecedor.setFor_telefone(resultado.getString("for_telefone"));
                fornecedor.setFor_celular(resultado.getString("for_celular"));
                fornecedor.setFor_cep(resultado.getString("for_cep"));
                retorno.add(fornecedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
