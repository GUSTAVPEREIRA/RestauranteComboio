/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.model.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Pereira
 */

public class EstadoDAO {
    
    @PersistenceContext
    EntityManagerFactory  emf; 
    EntityManager em;
   
    public static  Connection con;

    
    public EstadoDAO() 
    {
        emf = Persistence.createEntityManagerFactory("estagio");
        em = emf.createEntityManager();
    }

    
        
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
    
    @Transactional
    public void inserir(Estado estado) 
    {

            if(!em.isOpen())
            {
                em = emf.createEntityManager();
            }
            else
                em.getTransaction().begin();
            try
            {
    
                em.persist(estado);
                em.getTransaction().commit();
               
            } 
            catch(Exception e)
            {
                em.getTransaction().rollback();
                System.out.println(e.getMessage());
            }
            finally
            {
                em.close();
            }
            

    }
    
    public boolean alterar(Estado estado) 
    {
        abreConnection();
        String sql = "UPDATE estado SET est_nome=?, est_uf=? WHERE est_id=?";
        try 
        {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, estado.getNome());
            stmt.setString(2, estado.getUf());
            stmt.setLong(3, estado.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean Deletar(Estado estado) {
        abreConnection();
        String sql = "DELETE FROM estado WHERE est_id=?";
        try {
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, estado.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    public Estado busca(String busca) {
        abreConnection();
        Estado estado = new Estado();
        String sql ;
        sql = "SELECT * FROM estado where est_nome = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, busca);
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                estado.setId(resultado.getLong("est_id"));
                estado.setNome(resultado.getString("est_nome"));
                estado.setUf(resultado.getString("est_uf"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }
    
    public Estado listar(int busca) {
        abreConnection();
        Estado estado = new Estado();
        String sql ;
        sql = "SELECT * FROM estado where est_id = ?";
        List<Estado> retorno = new ArrayList<Estado>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, busca);
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                estado.setId(resultado.getLong("est_id"));
                estado.setNome(resultado.getString("est_nome"));
                estado.setUf(resultado.getString("est_uf"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }
    
    public List<Estado> listar(String busca) {
        abreConnection();
        String sql ;
        if(busca.equals(""))
        sql = "SELECT * FROM estado";
        else
        sql = "SELECT * FROM estado where est_uf like '"+busca+"%' or est_nome like '"+busca+"%'";   
        List<Estado> retorno = new ArrayList<Estado>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Estado estado = new Estado();
                estado.setId(resultado.getLong("est_id"));
                estado.setNome(resultado.getString("est_nome"));
                estado.setUf(resultado.getString("est_uf"));
                retorno.add(estado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
        
}
