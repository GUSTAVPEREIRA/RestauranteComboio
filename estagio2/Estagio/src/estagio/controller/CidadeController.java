/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import estagio.dao.CidadeDAO;
import estagio.dao.EstadoDAO;
import estagio.model.Cidade;
import estagio.model.Estado;
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
public class CidadeController implements Initializable {

    @FXML
    private JFXTextField txt_codigo;
    @FXML
    private JFXButton btn_Buscar;
    @FXML
    private JFXTextField txt_nome;
    @FXML
    private JFXComboBox<Estado> cbb_estId;
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
    
    private List<Estado> listaEstado;
    private ObservableList<Estado> obslEstado;
    private EstadoDAO estadoDAO= new EstadoDAO(); 
    private Estado estado = new Estado();
    private Cidade cidade = new Cidade();
    private CidadeDAO cidadeDAO = new CidadeDAO();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       listaEstado = estadoDAO.listar("");
       obslEstado = FXCollections.observableArrayList(listaEstado);
       cbb_estId.setItems(obslEstado);
       txt_nome.setText("");
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
            cidade.setCid_nome(txt_nome.getText().toString());
        }
        
        if (txt_codigo.getText() == "") {
            erro = true;
        }
        else
        {
            cidade.setCid_id(Integer.parseInt(txt_codigo.getText()));
        }
        
        if (cbb_estId.getSelectionModel().getSelectedItem().equals("")) {
            erro = true;
        }
        else
        {
            estado = cbb_estId.getSelectionModel().getSelectedItem();
            cidade.setEstado(estado);
            
        }
        
        if (erro != true) 
        {
            if (cidade.getCid_id() == 0) {
                cidadeDAO.inserir(cidade);
            }
            else
            {
                cidadeDAO.alterar(cidade);
            }
            txt_nome.setText("");
            txt_codigo.setText("0");
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
