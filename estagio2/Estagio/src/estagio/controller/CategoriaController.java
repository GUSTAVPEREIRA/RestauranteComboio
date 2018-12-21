/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import estagio.dao.CategoriaDAO;
import estagio.executar.Estagio;
import estagio.model.Categoria;
import estagio.view.util.UISetup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pereira
 */
public class CategoriaController implements Initializable {

    @FXML
    private JFXTextField txt_codigo;
    @FXML
    private JFXTextField txt_nome;
    @FXML
    private JFXButton btn_Buscar;
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
    
    Categoria categoria;
    CategoriaDAO categoriaDAO;
    @FXML
    private ContextMenu ctm_nome;
    @FXML
    private MenuItem mi_nome;
    @FXML
    private Tooltip ttp_Nome;
    @FXML
    private Tooltip ttp_Codigo;
    @FXML
    private Tooltip ttp_btnBuscar;
    @FXML
    private Tooltip ttp_btnNovo;
    @FXML
    private Tooltip ttp_btnGravar;
    @FXML
    private Tooltip ttp_btnExcluir;
    @FXML
    private Tooltip ttp_btnCancelar;
    @FXML
    private Tooltip ttp_btnSair;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       desativaTela();
       categoria = new Categoria();
       categoriaDAO = new CategoriaDAO();
       txt_codigo.setPromptText("Código da Categória");
       txt_nome.setPromptText("Exemplo:Bebidas...");
       ctm_nome.getItems().add(new MenuItem("Por favor insira um nome"));
      
    }    
    
    public void desativaTela()
    {
       txt_nome.setText("");
       txt_codigo.setText("0");
       btn_Excluir.setDisable(true);
       btn_Cancelar.setDisable(true);
    }
    
    public void ativaTela()
    {
       btn_Excluir.setDisable(false);
       btn_Cancelar.setDisable(false);
    }
    @FXML
    private void OnActionGravar(ActionEvent event) {
        Boolean erro=false;
        String erros="";
        String teste = txt_nome.getText();
        if (teste == "" || teste.length() < 4) {
            erro = true;
            ctm_nome.show(txt_nome,Side.RIGHT,10,0);
        }
        else
        {
            categoria.setCat_nome(txt_nome.getText().toString());
        }
        
        if (txt_codigo.getText() == "") {
            erro = true;
        }
        else
        {
            categoria.setCat_id(Integer.parseInt(txt_codigo.getText()));
        }
        
        if (erro != true) 
        {
            if (categoria.getCat_id() == 0) {
                categoriaDAO.inserir(categoria);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setContentText("Categoria "+categoria.getCat_nome()+" Inserida");
                 alert.show();
            }
            else
            {
                categoriaDAO.alterar(categoria);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setContentText("Categoria "+categoria.getCat_nome()+" Alterada");
                 alert.show();
            }
            txt_nome.setText("");
            txt_codigo.setText("0");
        }
        else
        {
                    erro = true;
                    erros = erros + " ";
        }
        desativaTela();
    }

    @FXML
    private void OnActionExcluir(ActionEvent event) 
    {
        
        Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        dialogoExe.setTitle("");
        dialogoExe.setHeaderText("Você deseja realmente excluir "+categoria.getCat_nome()+"?");
        dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
        dialogoExe.showAndWait().ifPresent(b -> 
        {
            if (b == btnSim)
            {
                if (txt_codigo.getText() != "0" && !txt_codigo.getText().isEmpty()) 
                {
                  categoria.setCat_id(Integer.parseInt(txt_codigo.getText()));
                  categoriaDAO.Deletar(categoria);
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setContentText("Categoria "+categoria.getCat_nome()+" Excluido");
                  alert.show();
                }     
                desativaTela();
            }
        });      
    }

    @FXML
    private void OnActionCancelar(ActionEvent event) 
    {
       desativaTela();
    }

    @FXML
    private void OnActionSair(ActionEvent event) 
    {
        Stage stage=(Stage)btn_Sair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void OnActionNovo(ActionEvent event) 
    {
       desativaTela();
    }

    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
        txt_nome.setText(categoria.getCat_nome());
        txt_codigo.setText(""+categoria.getCat_id());
        ativaTela();
    }
    
    public Categoria getCategoria()
    {
        return categoria;
    }
        
    
    @FXML
    private void OnActionBuscar(ActionEvent event) {
        try {
            
          CategoriaConsultaController categoriaConsulta;
          categoriaConsulta = 
          UISetup.getInstance().launchNewSceneAndWait(Estagio.class,"/estagio/view/CategoriaConsultaFXML.fxml")
          .<CategoriaConsultaController>getController();
          categoriaConsulta.setControllerOrigem(this);
          categoria = categoriaConsulta.getCategoria();
          if (categoria != null) 
          {
            this.setCategoria(categoria);                
          }

          
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void Limitetxt_nome(KeyEvent event) {
        if (txt_nome.getText().length() == 60)
            event.consume();
        

    }



    @FXML
    private void ToUpperCase(KeyEvent event) {
        if (!txt_nome.getText().isEmpty()) {
            txt_nome.setText(txt_nome.getText().toUpperCase());
            txt_nome.end();
        }

    }



   

    
    
}
