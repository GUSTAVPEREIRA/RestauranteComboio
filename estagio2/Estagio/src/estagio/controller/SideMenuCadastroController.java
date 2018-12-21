/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Pereira
 */
public class SideMenuCadastroController implements Initializable {

    @FXML
    private JFXButton Categoria;
    @FXML
    private JFXButton Cliente;
    @FXML
    private JFXButton Cidade;
    @FXML
    private JFXButton Estado;
    @FXML
    private JFXButton Empresa;
    @FXML
    private JFXButton Produto;
    @FXML
    private JFXButton Usuario;
    
    private AnchorPane anchorpane;
    
    public AnchorPane getAnchorPane()
    {
        return this.anchorpane;
    }
    
    public void setAnchorPane(AnchorPane anchorpane)
    {
        this.anchorpane=anchorpane;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void OnActionCategoria(ActionEvent event) {
        
    }

    @FXML
    private void OnActionCliente(ActionEvent event) {
    }

    @FXML
    private void OnActionCidade(ActionEvent event) {
    }

    @FXML
    private void OnActionEstado(ActionEvent event) {
    }

    @FXML
    private void OnActionEmpresa(ActionEvent event) {
    }

    @FXML
    private void OnActionProduto(ActionEvent event) {
    }

    @FXML
    private void OnActionUsuario(ActionEvent event) {
    }
    
    
    
}
