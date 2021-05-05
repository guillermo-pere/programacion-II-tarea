/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.fcys.programacion2.conversionfx.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import ni.edu.uni.fcys.programacion2.conversionfx.App;

/**
 * FXML Controller class
 *
 * @author Sistemas-03
 */
public class MainFXMLController implements Initializable {

    
    
    @FXML
    public Button btnTemperature;
    @FXML
    public Button btnCurrency;
    @FXML
    public VBox vboxCenter;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    public void BtnTemperatureAction(){
        try {
            Parent parent = App.loadFXML("TemperatureFXML");
            vboxCenter.getChildren().clear();
            vboxCenter.getChildren().add(parent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void BtnCurrencyAction(){
        try {
            Parent parent = App.loadFXML("CurrencyFXML");
            vboxCenter.getChildren().clear();
            vboxCenter.getChildren().add(parent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
