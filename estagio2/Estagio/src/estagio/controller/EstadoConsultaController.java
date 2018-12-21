/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import estagio.dao.EstadoDAO;
import estagio.model.Estado;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pereira
 */
public class EstadoConsultaController implements Initializable {

    @FXML
    private JFXTextField txt_filtro;
    @FXML
    private JFXButton btn_filtro;
    private EstadoController estadoController;
    @FXML
    private TableColumn<Estado, String> tc_codigo;
    @FXML
    private TableColumn<Estado, String> tc_nome;
    @FXML
    private TableColumn<Estado, String> tc_sigla;
    private ObservableList<Estado> obslEstado;
    
    private Estado estado;
    private EstadoDAO estadoDAO;
    private List<Estado> listEstado;
    @FXML
    private TableView<Estado> tb_estado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estado = new Estado();
        estadoDAO = new EstadoDAO();
        listEstado = new ArrayList();
        carregaTela(txt_filtro.getText());
    }    
    
    @FXML
    private void OnActionFiltro(ActionEvent event) {
        carregaTela(txt_filtro.getText());
    }

    @FXML
    private void OnMouseClickedEstado(MouseEvent event) {
       if (tb_estado.getSelectionModel().getSelectedItem() != null) 
       {
           this.setEstado(tb_estado.getSelectionModel().getSelectedItem());
           if (this.getEstado()!= null) 
           {
               Stage stage = (Stage) btn_filtro.getScene().getWindow();
               stage.close();
           }
       }        
    }
    
    public void carregaTela(String palavra)
    {
        listEstado.clear();
        tb_estado.getItems().clear();
        tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Est_id"));
        tc_nome.setCellValueFactory(new PropertyValueFactory<>("Est_nome"));
        tc_sigla.setCellValueFactory(new PropertyValueFactory<>("Est_sigla"));
        listEstado = estadoDAO.listar(palavra);
        if (!listEstado.isEmpty()) 
        {
            obslEstado = FXCollections.observableArrayList(listEstado);
            tb_estado.setItems(obslEstado);
        }

   }
    
    public void setEstado(Estado estado)
    {
        this.estado = estado;
    }
    
    public Estado getEstado()
    {
        return estado;
    }
    void setControllerOrigem(EstadoController estadoController) {
        this.estadoController = estadoController;
    }

    /**
     * Initializes the controller class.
     */


}
