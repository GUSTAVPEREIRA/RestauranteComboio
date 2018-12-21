/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.model.Categoria;
import estagio.model.Categoria;
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
public class CategoriaDAO {
    
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
    
     public boolean inserir(Categoria categoria) {
        abreConnection();
        String sql = "INSERT INTO categoria(cat_nome) VALUES(?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, categoria.getCat_nome());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Categoria categoria) {
        abreConnection();
        String sql = "UPDATE categoria SET cat_nome=? WHERE cat_id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, categoria.getCat_nome());
            stmt.setInt(2, categoria.getCat_id());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean Deletar(Categoria categoria) {
        abreConnection();
        String sql = "DELETE FROM categoria WHERE cat_id=?";
        try {
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, categoria.getCat_id());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Categoria> listar(String busca) {
        abreConnection();
        String sql ;
        List<Categoria> retorno = new ArrayList<>();
        if(busca.equals(""))
        sql = "SELECT * FROM categoria";
        else
        sql = "SELECT * FROM categoria where cat_nome like '"+busca+"%'"; 
        try 
        {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) 
            {
                Categoria categoria = new Categoria();
                categoria.setCat_id(resultado.getInt("cat_id"));          
                categoria.setCat_nome(resultado.getString("cat_nome"));     
                retorno.add(categoria);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
