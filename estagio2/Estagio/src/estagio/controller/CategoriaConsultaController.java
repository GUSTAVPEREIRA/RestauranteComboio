/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import estagio.dao.CategoriaDAO;
import estagio.model.Categoria;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
public class CategoriaConsultaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private ObservableList<Categoria> obslCategoria;
    private Categoria categoria;
    public CategoriaController categoriaController;
    private CategoriaDAO categoriaDAO;
    private List<Categoria> listCategoria;
    @FXML
    private JFXTextField txt_filtro;
    @FXML
    private JFXButton btn_filtro;
    @FXML
    private TableView<Categoria> tb_categoria;
    @FXML
    private TableColumn<Categoria, String> tc_codigo;
    @FXML
    private TableColumn<Categoria, String> tc_nome;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoria = new Categoria();
        categoriaDAO = new CategoriaDAO();
        listCategoria = new ArrayList();
        carregaTela(txt_filtro.getText());
    }    

    @FXML
    private void OnActionFiltro(ActionEvent event) 
    {
        if (txt_filtro.getText() != null) 
        {
          carregaTela(txt_filtro.getText()); 
        }
    }
    
    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
    }
    
    public Categoria getCategoria()
    {
        return categoria;
    }

    @FXML
    private void OnMouseClickedCategoria(MouseEvent event) 
    {
       if (tb_categoria.getSelectionModel().getSelectedItem() != null) 
       {
           this.setCategoria(tb_categoria.getSelectionModel().getSelectedItem());
           if (this.getCategoria() != null) 
           {
               Stage stage = (Stage) btn_filtro.getScene().getWindow();
               stage.close();
           }
       }
       
       
    }
   public void carregaTela(String palavra)
   {
        listCategoria.clear();
        tb_categoria.getItems().clear();
        tc_codigo.setCellValueFactory(new PropertyValueFactory<>("Cat_id"));
        tc_nome.setCellValueFactory(new PropertyValueFactory<>("Cat_nome"));
        listCategoria = categoriaDAO.listar(palavra);
        if (!listCategoria.isEmpty()) 
        {
            obslCategoria = FXCollections.observableArrayList(listCategoria);
            tb_categoria.setItems(obslCategoria);
       }

   }

    public void setControllerOrigem(CategoriaController categoria) {
        this.categoriaController = categoria;
    }
    
}
