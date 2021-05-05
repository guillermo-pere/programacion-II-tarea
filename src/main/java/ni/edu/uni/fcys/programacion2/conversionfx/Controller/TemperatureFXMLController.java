/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.fcys.programacion2.conversionfx.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sistemas-03
 */
public class TemperatureFXMLController implements Initializable {

    ConversionOperation operation = new ConversionOperation();
    
    @FXML
    public TextField txtCelcius;
    
    @FXML
    public TextField txtFarenheit;
    
    @FXML
    public TextField txtResult;
    
    @FXML
    public RadioButton rbtnCelsius;
    @FXML
    public RadioButton rbtnFha;
    @FXML 
    public Button btnCompute;
    @FXML
    public Button btnClear;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void rbtnCelsiusAction(){
        activateTextField();
    }
    
    @FXML
    public void rbtnFhaAction(){
        activateTextField();
    }
    
    @FXML
    public void BtnComputeAction(){
        float result=0;
        if(rbtnCelsius.isSelected()){
         result =   operation.ConvertC2F(Float.parseFloat(txtCelcius.getText()));
        }
        else if (rbtnFha.isSelected()){
         result =   operation.ConvertF2C(Float.parseFloat(txtFarenheit.getText()));   
        }
        
        txtResult.setText(String.valueOf(result));
    }
    
    @FXML
    public void BtnClearAction(){
        cleanTexFields();
    }
    
    
    
    
    private void activateTextField(){
        if(rbtnCelsius.isSelected()){
            txtCelcius.setEditable(true);
            txtFarenheit.setEditable(false);
            txtCelcius.requestFocus();
            cleanTexFields();
        }
        else if(rbtnFha.isSelected()){
           txtCelcius.setEditable(false);
            txtFarenheit.setEditable(true);
            txtFarenheit.requestFocus();
            cleanTexFields(); 
        }
    }
    
    private void cleanTexFields(){
        txtFarenheit.setText("");
        txtCelcius.setText("");
        txtResult.setText("");
    }
}
