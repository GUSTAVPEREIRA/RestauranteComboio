/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import estagio.dao.FornecedorDAO;
import estagio.dao.ProdutoDAO;
import estagio.model.Estado;
import estagio.model.Fornecedor;
import estagio.model.Produto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Pereira
 */
public class ProdutoController implements Initializable {

    @FXML
    private JFXTextField txt_codigo;
    @FXML
    private JFXButton btn_Buscar;
    @FXML
    private JFXTextField txt_nome;
    @FXML
    private JFXTextField txt_valor;
    @FXML
    private JFXTextField txt_valor_compra;
    @FXML
    private JFXTextField txt_estoque;
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
    private JFXComboBox<Fornecedor> cbb_forId;
    
    private Produto produto;
    private ProdutoDAO produtoDAO;
    private Fornecedor fornecedor;
    private FornecedorDAO fornecedorDAO;
    private List<Fornecedor> listaFornecedor;
    private ObservableList<Fornecedor> obslFornecedor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       produto = new Produto();
       produtoDAO = new ProdutoDAO();
       fornecedor = new Fornecedor();
       fornecedorDAO = new FornecedorDAO();
       listaFornecedor = fornecedorDAO.listar("");
       obslFornecedor = FXCollections.observableArrayList(listaFornecedor);
       cbb_forId.setItems(obslFornecedor);
       txt_nome.setText("");
       txt_valor.setText("");
       txt_valor_compra.setText("");
       txt_estoque.setText("");
       txt_codigo.setText("0");
       btn_Excluir.setDisable(false);
       btn_Cancelar.setDisable(false);
    }    

    @FXML
    private void OnActionBuscar(ActionEvent event) {
    }

    @FXML
    private void OnActionNovo(ActionEvent event) {
        txt_nome.setText("");
        txt_valor.setText("");
        txt_valor_compra.setText("");
        txt_estoque.setText("");
        txt_codigo.setText("0");
        btn_Excluir.setDisable(false);
        btn_Cancelar.setDisable(false);
    }

    @FXML
    private void OnActionGravar(ActionEvent event) {
        Boolean erro=false;
        if (txt_nome.getText() == "") {
            erro = true;
        }
        else
        {
            produto.setProd_nome(txt_nome.getText());
        }
        
        if (txt_valor.getText() == "") {
            erro = true;
        }
        else
        {
            produto.setProd_valor(Double.parseDouble(txt_valor.getText()));
        }
        
        
        if (txt_valor_compra.getText() == "") {
            erro = true;
        }
        else
        {
            produto.setProd_valor_compra(Double.parseDouble(txt_valor_compra.getText()));
        }
               
        if (txt_estoque.getText() == "") {
            erro = true;
        }
        else
        {
            produto.setProd_estoque(Integer.parseInt(txt_estoque.getText()));
        }
        
        if (txt_codigo.getText() == "") {
            erro = true;
        }
        else
        {
            produto.setProd_id(Integer.parseInt(txt_codigo.getText()));
        }
        
        if (cbb_forId.getSelectionModel().getSelectedItem().equals("")) {
            erro = true;
        }
        else
        {
            fornecedor = cbb_forId.getSelectionModel().getSelectedItem();
            produto.setFornecedor(fornecedor);
            
        }
        
        if (erro != true) 
        {
            if (produto.getProd_id()== 0) {
                produtoDAO.inserir(produto);
            }
            else
            {
                produtoDAO.alterar(produto);
            }
            txt_nome.setText("");
            txt_valor.setText("");
            txt_valor_compra.setText("");
            txt_estoque.setText("");
            txt_codigo.setText("0");
            btn_Excluir.setDisable(false);
            btn_Cancelar.setDisable(false);
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
