/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.fcys.programacion2.conversionfx.Controller;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sistemas-03
 */
public class CurrencyFXMLController implements Initializable {

    private String [] Conversions = new String [] {"Cordobas -> Dolar", "Cordobas -> Euro", "Dolares -> Euro", "Dolares -> Cordobas", "Euros -> Dolares", "Euros -> Cordobas"};
     @FXML
    public TextField txtAmount;
      @FXML
    public TextField txtResultCurr;
      @FXML 
    public Button btnComputeCurr;
      @FXML 
    public Button btnClearCurr;
      @FXML 
    public ComboBox cmbxConversion;
      
      List<String> list = Arrays.asList(Conversions);
        ObservableList<String> conversionlist = FXCollections.observableList(list);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbxConversion.setItems(conversionlist);
    }
    
    public void btnComputeCurrAction(){
        if(txtAmount.getText() == null){
            txtResultCurr.setText("0");
        }
        else{
        float result = CurrencyOperation.cambiomoneda(String.valueOf(cmbxConversion.getValue()), Float.parseFloat(txtAmount.getText()));
        
        txtResultCurr.setText(String.valueOf(result));
        }
    }
   
    public void btnClearCurrAction(){
        cleanTexFields();
    }
    
    private void cleanTexFields(){
        txtAmount.setText("");
        txtResultCurr.setText("");
    }
}
