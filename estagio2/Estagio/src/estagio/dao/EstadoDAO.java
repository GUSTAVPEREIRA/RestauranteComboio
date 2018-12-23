/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.dao;

import estagio.model.Estado;
import estagio.view.util.JPAUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    EntityManager em;
   
    public static  Connection con;

    
    public EstadoDAO() 
    {
        em = new JPAUtil().getEntityManager();
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
                em = new JPAUtil().getEntityManager();
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
    
    public void alterar(Estado estado) 
    {
            if(!em.isOpen())
            {
                em = new JPAUtil().getEntityManager();
            }
            else
                em.getTransaction().begin();
            try
            {
                
                Estado aux = em.find(Estado.class,estado.getId());
                aux.setNome(estado.getNome());
                aux.setUf(estado.getUf());
                em.merge(aux);
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

    public boolean Deletar(Estado estado) {
        boolean deletado = false;
            if(!em.isOpen())
            {
                em = new JPAUtil().getEntityManager();
            }
            else
                em.getTransaction().begin();
            try
            {
                
                Estado aux = em.find(Estado.class,estado.getId());
                em.remove(aux);
                em.getTransaction().commit();
                deletado = true;
            } 
            catch(Exception e)
            {
                em.getTransaction().rollback();
                System.out.println(e.getMessage());
            }
            finally
            {
                em.close();
                return deletado;
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
