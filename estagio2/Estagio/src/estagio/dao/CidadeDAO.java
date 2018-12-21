/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.model.Cidade;
import estagio.model.Estado;
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
public class CidadeDAO {

    public static  Connection con;
    private EstadoDAO estadoDAO;
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
    
     public boolean inserir(Cidade cidade) {
        abreConnection();
        String sql = "INSERT INTO cidade(cid_nome,est_id) VALUES(?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cidade.getCid_nome());
            stmt.setLong(2, cidade.getEstado().getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Cidade cidade) {
        abreConnection();
        String sql = "UPDATE cidade SET cid_nome=?, est_id=? WHERE cid_id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cidade.getCid_nome());
            stmt.setLong(2, cidade.getEstado().getId());
            stmt.setInt(3, cidade.getCid_id());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean Deletar(Cidade cidade) {
        abreConnection();
        String sql = "DELETE FROM cidade WHERE cid_id=?";
        try {
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, cidade.getCid_id());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Cidade> listar(String busca) {
        abreConnection();
        String sql ;
        if(busca.equals(""))
        sql = "SELECT * FROM cidade";
        else
        sql = "SELECT * FROM cidade where est_id like '"+busca+"%' or cid_nome like '"+busca+"%'";   
        List<Cidade> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cidade cidade = new Cidade();
                cidade.setCid_id(resultado.getInt("est_id"));
                cidade.setCid_nome(resultado.getString("est_nome"));
                cidade.setEstado(estadoDAO.listar(resultado.getInt("est_id")));  
                retorno.add(cidade);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    
}
