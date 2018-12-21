/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author Pereira
 */
public class CadastroController {

    @FXML
    private JFXHamburger ham_menu;
    @FXML
    private JFXDrawer draw_menu;
    @FXML
    private AnchorPane ap_cadastros;

    private HamburgerSlideCloseTransition task;
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
    @FXML
    private VBox VB_Menu;
    @FXML
    private JFXButton Fornecedor;
    
    public void inicialization()
    {

 
    }
    
   

    @FXML
    private void OnMouseAbreMenu(MouseEvent event) {
        if (task == null) 
        {
            task = new HamburgerSlideCloseTransition (ham_menu);
            task.setRate(-1);  
        }
        VBox box = VB_Menu;
        draw_menu.setSidePane(box);
        task.setRate(task.getRate()*-1);
        task.play();    
        draw_menu.setVisible(false);
        VB_Menu.setVisible(false);

        if (draw_menu.isShown())
        {
            draw_menu.close();
        }
        else
        {
            draw_menu.setVisible(true);
            VB_Menu.setVisible(true);
            draw_menu.open();
        }
    }

    @FXML
    private void OnActionCategoria(ActionEvent event) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/CategoriaFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionCliente(ActionEvent event) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/ClienteFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionCidade(ActionEvent event) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/CidadeFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionEstado(ActionEvent event) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/EstadoFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionEmpresa(ActionEvent event) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/EmpresaFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionProduto(ActionEvent event) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/ProdutoFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionUsuario(ActionEvent event) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/UsuarioFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }

    @FXML
    private void OnActionFornecedor(ActionEvent event) throws IOException {
        Node node;
        node = (Node)FXMLLoader.load(getClass().getResource("/estagio/view/FornecedorFXML.fxml"));
        ap_cadastros.getChildren().setAll(node);
    }
    
    
    
}
