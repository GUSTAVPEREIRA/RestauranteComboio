/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Pereira
 */
public class MainController {

    @FXML
    private JFXHamburger ham_menu;
    @FXML
    private JFXDrawer drw_menu;
    
    HamburgerNextArrowBasicTransition task;
    
    public void inicialization()
    {
        
        task = new HamburgerNextArrowBasicTransition(ham_menu);
        task.setRate(task.getRate()* -1);
    }

    @FXML
    private void OnMouseAbreMenu(MouseEvent event) {
        
        try {
           
            HBox box = FXMLLoader.load(getClass().getResource("/estagio/view/SideMenuFXML.fxml"));
            drw_menu.setSidePane(box);
            task = new HamburgerNextArrowBasicTransition(ham_menu);
            
            task.setRate(task.getRate()* -1);
            task.setRate(task.getRate()* -1);
            task.play();
            
            if (drw_menu.isShown()) 
            {
                
                drw_menu.close();
            }
            else
            {
                
                drw_menu.open();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
    
}
