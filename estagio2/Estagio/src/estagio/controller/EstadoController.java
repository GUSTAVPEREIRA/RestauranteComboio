/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import estagio.dao.EstadoDAO;
import estagio.model.Estado;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import estagio.executar.Estagio;
import estagio.view.util.UISetup;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pereira
 */
public class EstadoController implements Initializable {

    @FXML
    private JFXTextField txt_codigo;
    @FXML
    private JFXButton btn_Buscar;
    @FXML
    private JFXTextField txt_nome;
    @FXML
    private JFXTextField txt_sigla;
    @FXML
    private JFXButton btn_Novo;
    @FXML
    private JFXButton btn_Gravar;
    @FXML
    private JFXButton btn_Excluir;
    @FXML
    private JFXButton btn_Cancelar;
    @FXML
    private JFXButton btn_Sair;
    
    private Estado estado;
    private EstadoDAO estadoDAO;
    @FXML
    private Tooltip ttp_codigo;
    @FXML
    private Tooltip ttp_btnBuscar;
    @FXML
    private Tooltip ttp_nome;
    @FXML
    private Tooltip ttp_sigla;
    @FXML
    private Tooltip ttp_btnGravar;
    @FXML
    private Tooltip ttp_btnExcluir;
    @FXML
    private Tooltip ttp_btnCancelar;
    @FXML
    private Tooltip ttp_btnSair;
    @FXML
    private Tooltip ttp_btnNovo;

        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estado = new Estado();
        estadoDAO = new EstadoDAO();
        txt_nome.setText("");
        txt_sigla.setText("");
        txt_codigo.setText("0");
        txt_nome.setPromptText("SÃO PAULO");
        txt_sigla.setPromptText("SP");
        btn_Excluir.setDisable(false);
        btn_Cancelar.setDisable(false);
        
    }    
    public void desativaTela()
    {
       txt_nome.setText("");
       txt_sigla.setText("");       
       txt_codigo.setText("0");
       btn_Excluir.setDisable(true);
       btn_Cancelar.setDisable(true);
    }

    @FXML
    private void OnActionBuscar(ActionEvent event) {
        try 
        {
         
          EstadoConsultaController estadoConsulta;
          estadoConsulta = 
          UISetup.getInstance().launchNewSceneAndWait(Estagio.class,"/estagio/view/EstadoConsultaFXML.fxml")
          .<EstadoConsultaController>getController();
          estadoConsulta.setControllerOrigem(this);
          estado = estadoConsulta.getEstado();
          if (estado != null) 
          {
            this.setEstado(estado);                
          }          
          
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OnActionNovo(ActionEvent event) {
        estado = new Estado();
        estadoDAO = new EstadoDAO();
        txt_nome.setText("");
        txt_sigla.setText("");
        txt_codigo.setText("0");
        btn_Excluir.setDisable(false);
        btn_Cancelar.setDisable(false);
    }

    @FXML
    private void OnActionGravar(ActionEvent event) {
        Boolean erro=false;
        estado = new Estado();
        if (txt_nome.getText() == "") 
            erro = true;
        else
            estado.setNome(txt_nome.getText().toString());
        
        
        if (txt_sigla.getText() == "") 
            erro = true;
        else     
            estado.setUf(txt_sigla.getText().toString());
        
        
        if (txt_codigo.getText() == "") 
            erro = true;
        else
            estado.setId(Long.parseLong(txt_codigo.getText()));
        
        if (erro != true) 
        {
            if (estado.getId()==0)
            {
                estado.setId(null);
                estadoDAO.inserir(estado);                
            }
            else
                estadoDAO.alterar(estado);
            
            txt_nome.setText("");
            txt_sigla.setText("");
            txt_codigo.setText("0");
            btn_Excluir.setDisable(false);
            btn_Cancelar.setDisable(false);
            estadoDAO = new EstadoDAO();
        }
        
    }
    public void setEstado(Estado estado)
    {
        this.estado = estado;
        txt_nome.setText(estado.getNome());
        txt_codigo.setText(""+estado.getId());
        txt_sigla.setText(estado.getUf());        
        ativaTela();
    }
    
    public void ativaTela()
    {
       btn_Excluir.setDisable(false);
       btn_Cancelar.setDisable(false);
    }    
    public Estado getEstado()
    {
        return estado;
    }
    @FXML
    private void OnActionExcluir(ActionEvent event) {
        Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        dialogoExe.setTitle("");
        dialogoExe.setHeaderText("Você deseja realmente excluir "+estado.getNome()+"?");
        dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
        dialogoExe.showAndWait().ifPresent(b -> 
        {
            if (b == btnSim)
            {
                if (txt_codigo.getText() != "0" && !txt_codigo.getText().isEmpty()) 
                {
                  estado.setId(Long.parseLong(txt_codigo.getText()));
                  Boolean deletado = estadoDAO.Deletar(estado);
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  
                  if (deletado == true) 
                  {
                        
                    alert.setContentText("Estado "+estado.getNome()+" Excluido");
                    alert.show();             
                    desativaTela();
                  }
                  else
                  {
                    alert.setContentText("Estado "+estado.getNome()+" não pode ser excluido, verifique se há cidades com o estado.");
                    alert.show();       
                  }

                }     

            }
        });
    }

    @FXML
    private void OnActionCancelar(ActionEvent event) {
        btn_Excluir.setDisable(false);
        btn_Cancelar.setDisable(false);
    }

    @FXML
    private void OnActionSair(ActionEvent event) {
        Stage stage=(Stage)btn_Sair.getScene().getWindow();
        stage.close();        
    }
    

    
    
}
