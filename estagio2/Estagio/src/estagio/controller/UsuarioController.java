/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import estagio.dao.UsuarioDAO;
import estagio.model.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Pereira
 */
public class UsuarioController implements Initializable {

    @FXML
    private JFXTextField txt_codigo;
    @FXML
    private JFXButton btn_Buscar;
    @FXML
    private JFXTextField txt_nome;
    @FXML
    private JFXTextField txt_login;
    @FXML
    private JFXPasswordField txt_senha;
    @FXML
    private JFXComboBox<String> cbb_ativo;
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
    @FXML
    private JFXComboBox<String> cbb_tipo;
    private ObservableList<String> cbb_Tipos;
    private ObservableList<String> cbb_Ativo;
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private int qt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        qt = 0;
        usuarioDAO = new UsuarioDAO();
        usuario = new Usuario();
        btn_Cancelar.setDisable(true);
        btn_Excluir.setDisable(true);
        cbb_Tipos = FXCollections.observableArrayList(
                "Admin",
                "Normal"  
        ); 
        cbb_tipo.setItems(cbb_Tipos);
        cbb_Ativo = FXCollections.observableArrayList(
                "Ativado",
                "Desativado"  
        );
        cbb_ativo.setItems(cbb_Ativo);
    }    

    @FXML
    private void OnActionBuscar(ActionEvent event) {
    }

    @FXML
    private void OnActionNovo(ActionEvent event) {
    }

    @FXML
    private void OnActionGravar(ActionEvent event) 
    {
        usuario = new Usuario();
        boolean erro = false; 
     
        String erros="";
        qt = usuarioDAO.qt_Admin();
        if(!txt_codigo.getText().isEmpty())
        {
            
            usuario.setUsu_id(Integer.parseInt(txt_codigo.getText()));
        }
        else
        usuario.setUsu_id(0);

        if(txt_login.getText().length() < 5 || txt_login.getText().length() >25)
        {
            erro=true;
            if(txt_login.getText().length() < 5)
            erros = erros + " Login com tamanho inferior a 5 caracteres\n";
            else
            erros = erros + " Login com tamanho maior que 25 caracteres\n";    
            txt_login.setStyle("-fx-border-color: red;");
        }
        else
        {
            
            usuario.setUsu_login(txt_login.getText());
            txt_login.setStyle("-fx-border-color: #D3D3D3;");
        }
            
        
        if(txt_senha.getText().length() < 5 || txt_senha.getText().length() >25)
        {
            erro=true;
            if(txt_senha.getText().length() < 5)
            erros = erros + " Senha com tamanho inferior a 5 caracteres\n";
            else
            erros = erros + " Senha com tamanho maior que 25 caracteres\n"; 
            txt_senha.setStyle("-fx-border-color: red;");
        }
        else
        {
            txt_senha.setStyle("-fx-border-color: #D3D3D3;");
            usuario.setUsu_senha(txt_senha.getText());
        }
            

        if(cbb_tipo.getSelectionModel().getSelectedItem().isEmpty())
        {
            erro = true;
            erros = erros + " Selecione um item por favor\n";
        } 
        else
        {
            usuario.setUsu_tipo(cbb_tipo.getSelectionModel().getSelectedItem());
            cbb_tipo.setStyle("-fx-border-color: #D3D3D3;");
            if(usuario.getUsu_id()!= 0 && usuarioDAO.verif_admin(usuario.getUsu_id()) == true )
            {
                if(qt < 2 && usuario.getUsu_tipo().equals("Normal"))
                {
                    erros = erros + " Não se pode ter nenhum admin\n";
                    erro = true;
                    cbb_tipo.setStyle("-fx-border-color: red;");
                }
                else
                    cbb_tipo.setStyle("-fx-border-color: #D3D3D3;");
            }
        }
        

        if(usuarioDAO.login_dup(txt_login.getText()) == true && "".equals(txt_codigo.getText()))
        {
                erro = true;
                erros = erros + " Login já existente\n"; 
                txt_login.setStyle("-fx-border-color: red;");
        }
        if(erro == false)
        {
             
            
            if("".equals(txt_codigo.getText()))
            {
                usuarioDAO.inserir(usuario);
               
            }
            else
            {
                
                int cod = usuarioDAO.login_alt_dif(txt_login.getText());
               
                if(cod == 0 || cod == usuario.getUsu_id())
                {
                    usuarioDAO.alterar(usuario);
                    
                }  
                else
                {
                    erro = true;
                    erros = erros + " Usuario não pode ser alterado para esse login"; 
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(erros);
                    alert.show();
                }
                    
            }
       
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(erros);
            alert.show();
        }
    }

    @FXML
    private void OnActionExcluir(ActionEvent event) {
    }

    @FXML
    private void OnActionCancelar(ActionEvent event) {
    }

    @FXML
    private void OnActionSair(ActionEvent event) {
    }
    
}
