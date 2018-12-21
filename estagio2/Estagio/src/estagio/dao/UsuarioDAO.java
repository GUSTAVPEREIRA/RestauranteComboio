/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.model.Usuario;
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
public class UsuarioDAO {
    
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
    
   
    
     public boolean inserir(Usuario usuario) {
        abreConnection();
        String sql = "INSERT INTO usuario(usu_login,usu_senha,usu_nome,usu_tipo,usu_ativo) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getUsu_login());
            stmt.setString(2, usuario.getUsu_senha());
            stmt.setString(3, usuario.getUsu_nome());
            stmt.setString(4, usuario.getUsu_tipo());
            stmt.setBoolean(5,usuario.getUsu_ativo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Usuario usuario) {
        abreConnection();
        String sql = "UPDATE usuario SET usu_email=?, "
                + "usu_login=?, usu_senha=?,usu_nome=?,usu_tipo=?,usu_ativo=? WHERE usu_id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getUsu_login());
            stmt.setString(2, usuario.getUsu_senha());
            stmt.setString(3, usuario.getUsu_nome());
            stmt.setString(4, usuario.getUsu_tipo());
            stmt.setBoolean(5,usuario.getUsu_ativo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Usuario usuario) {
        abreConnection();
        String sql = "DELETE FROM usuario WHERE usu_id=?";
        try {
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, usuario.getUsu_id());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean verif_admin(int codigo)
    {
        abreConnection();
        String tippo="";
        try {
            
            String sql = "SELECT usu_tipo as qt FROM usuario WHERE usu_id = "+codigo+"";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet resultado = stmt.executeQuery();
         while (resultado.next()) {
             tippo = resultado.getString("qt");
         }
         if(tippo.equals("Admin"))
         return true;
         
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
    public int qt_Admin() 
    {
        abreConnection();
        int qt=0;
        try {
            
            String sql = "SELECT COUNT(usu_tipo) as qt FROM usuario WHERE usu_tipo = 'ADMIN'";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet resultado = stmt.executeQuery();
         while (resultado.next()) {
             qt = resultado.getInt("qt");
         }
        return qt;
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
    
    public Boolean login_dup(String busca) {
        abreConnection();
        String sql ;
        sql = "SELECT * FROM usuario where usu_login='"+busca+"'"; 
        boolean tem = false;
        List<Usuario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Usuario usuario = new Usuario();
              
                usuario.setUsu_id(resultado.getInt("usu_id"));
                usuario.setUsu_nome(resultado.getString("usu_nome"));
                usuario.setUsu_login(resultado.getString("usu_login"));
                usuario.setUsu_senha(resultado.getString("usu_senha"));
                usuario.setUsu_tipo(resultado.getString("usu_tipo"));
                usuario.setUsu_ativo(resultado.getBoolean("usu_ativo"));
                retorno.add(usuario);
                tem = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tem;
    }
    
    public List<Usuario> listar(String busca) {
        abreConnection();
        String sql ;
        if(busca.equals(""))
        sql = "SELECT * FROM usuario";
        else
        sql = "SELECT * FROM usuario where usu_tipo like '"+busca+"%' or usu_login like '"+busca+"%'";   
        List<Usuario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Usuario usuario = new Usuario();
              
                usuario.setUsu_id(resultado.getInt("usu_id"));
                usuario.setUsu_login(resultado.getString("usu_login"));
                usuario.setUsu_senha(resultado.getString("usu_senha"));
                usuario.setUsu_nome(resultado.getString("usu_nome"));
                usuario.setUsu_tipo(resultado.getString("usu_tipo"));
                usuario.setUsu_ativo(resultado.getBoolean("usu_ativo"));               
                retorno.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    public int login_alt_dif(String busca)
    {
        abreConnection();
        String sql ;
        sql = "SELECT * FROM usuario where usu_login='"+busca+"'"; 
        
        List<Usuario> retorno = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuario.setUsu_id(0);
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                
              
                usuario.setUsu_id(resultado.getInt("usu_id"));
                usuario.setUsu_login(resultado.getString("usu_login"));
                usuario.setUsu_senha(resultado.getString("usu_senha"));
                usuario.setUsu_nome(resultado.getString("usu_nome"));
                usuario.setUsu_tipo(resultado.getString("usu_tipo"));
                usuario.setUsu_ativo(resultado.getBoolean("usu_ativo"));   
                retorno.add(usuario);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario.getUsu_id();
    }

    public String login(String login, String senha)
    {
        abreConnection();
        String sql;
        boolean tem = false;
        sql = "SELECT * FROM usuario where usu_login = '"+login+"' and usu_senha = '"+senha+"'";
        Usuario usuario = new Usuario();
        usuario.setUsu_tipo("");
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                
              
                usuario.setUsu_id(resultado.getInt("usu_id"));
                usuario.setUsu_login(resultado.getString("usu_login"));
                usuario.setUsu_senha(resultado.getString("usu_senha"));
                usuario.setUsu_nome(resultado.getString("usu_nome"));
                usuario.setUsu_tipo(resultado.getString("usu_tipo"));
                usuario.setUsu_ativo(resultado.getBoolean("usu_ativo"));
                tem = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        return usuario.getUsu_tipo();
    }
    
}
