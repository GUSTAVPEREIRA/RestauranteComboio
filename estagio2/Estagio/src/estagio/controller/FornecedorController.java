/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import estagio.dao.FornecedorDAO;
import estagio.model.Fornecedor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Pereira
 */
public class FornecedorController implements Initializable {

    @FXML
    private JFXTextField txt_codigo;
    @FXML
    private JFXButton btn_Buscar;
    @FXML
    private JFXTextField txt_nome;
    @FXML
    private JFXTextField txt_cnpj;
    @FXML
    private JFXTextField txt_ie;
    @FXML
    private JFXTextField txt_telefone;
    @FXML
    private JFXTextField txt_celular;
    @FXML
    private JFXTextField txt_cep;
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
    
    private Fornecedor fornecedor;
    private FornecedorDAO fornecedorDAO;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fornecedor = new Fornecedor();
        fornecedorDAO = new FornecedorDAO();
        txt_nome.setText("");
        txt_cep.setText("");
        txt_celular.setText("");
        txt_telefone.setText("");
        txt_cnpj.setText("");
        txt_ie.setText("");
        txt_codigo.setText("0");
    }    

    @FXML
    private void OnActionBuscar(ActionEvent event) {
    }

    @FXML
    private void OnActionNovo(ActionEvent event) {
    }

    @FXML
    private void OnActionGravar(ActionEvent event) {
        Boolean erro=false;
        if (txt_nome.getText() == "") {
            erro = true;
        }
        else
        {
            fornecedor.setFor_nome(txt_nome.getText());
        }
        
        if (txt_cnpj.getText() == "") {
            erro = true;
        }
        else
        {
            fornecedor.setFor_cnpj(txt_cnpj.getText());
        }
        
        
        if (txt_cep.getText() == "") {
            erro = true;
        }
        else
        {
            fornecedor.setFor_cep(txt_cep.getText());
        }
        
        if (txt_celular.getText() == "") {
            erro = true;
        }
        else
        {
            fornecedor.setFor_celular(txt_celular.getText());
        }
        
        if (txt_telefone.getText() == "") {
            erro = true;
        }
        else
        {
            fornecedor.setFor_telefone(txt_telefone.getText());
        }
        
        if (txt_ie.getText() == "") {
            erro = true;
        }
        else
        {
            fornecedor.setFor_ie(txt_ie.getText().toString());
        }
        
        if (txt_codigo.getText() == "") {
            erro = true;
        }
        else
        {
            fornecedor.setFor_id(Integer.parseInt(txt_codigo.getText()));
        }
        
        if (erro != true) 
        {
            if (fornecedor.getFor_id()== 0) {
                fornecedorDAO.inserir(fornecedor);
            }
            else
            {
                fornecedorDAO.alterar(fornecedor);
            }
            txt_nome.setText("");
            txt_cep.setText("");
            txt_celular.setText("");
            txt_telefone.setText("");
            txt_cnpj.setText("");
            txt_ie.setText("");
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
