/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import estagio.dao.UsuarioDAO;
import estagio.model.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author Pereira
 */
public class LoginController implements Initializable{

    @FXML
    private JFXTextField txt_login;
    @FXML
    private JFXPasswordField txt_senha;
    @FXML
    private JFXButton btn_login;
    public Usuario usuario;
    public UsuarioDAO usuarioDAO;
    public static Usuario logado = new Usuario();
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       txt_login.setText("");
       txt_senha.setText("");
       usuario = new Usuario();
       usuarioDAO = new UsuarioDAO();
       
    }

    
    
    @FXML
    private void ActionLogin(ActionEvent event) {
        
        String login,senha;
         login = "";
         senha = "";
         Boolean erro = false;
         if(txt_login.getText().equals(""))
         {
             erro = true;
             txt_login.setStyle("-fx-border-color: red;");
         }
         else
         {
            login = txt_login.getText(); 
            txt_login.setStyle("-fx-border-color: #D3D3D3;");
         }
         if(txt_login.getText().equals(""))
         {
             erro = true;
             txt_login.setStyle("-fx-border-color: red;");
         }
         else
         {
            senha = txt_senha.getText(); 
            txt_senha.setStyle("-fx-border-color: #D3D3D3;");
         }
         if(erro == false)
         {
           
          
             
             logado.setUsu_tipo(usuarioDAO.login(login,senha));
             
             if(!logado.getUsu_tipo().equals(""))
             {
                logado.setUsu_login(login);
                logado.setUsu_senha(senha);
                Stage stage = (Stage) btn_login.getScene().getWindow();
                stage.close();

                try 
                {
                    Thread.sleep(0,5 * 1000);
                } 
                catch (InterruptedException ex) 
                {

                }
                Parent root;    
                 try {
                    root = FXMLLoader.load(getClass().getResource("/estagio/view/MenuFXML.fxml"));
                    Scene scene = new Scene(root);
                    stage = new Stage();            
                    stage.setTitle("Menu");
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                 } catch (IOException ex) {
                     Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                
             }
             else
             {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Falha em logar");
                alert.show();
             }
         }
         else
         {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Falha em logar");
             alert.show();
         }
    }
}
