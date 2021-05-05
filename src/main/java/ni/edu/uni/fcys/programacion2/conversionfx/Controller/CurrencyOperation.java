/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.fcys.programacion2.conversionfx.Controller;

/**
 *
 * @author Sistemas-03
 */
public class CurrencyOperation {
    public static float cambiomoneda(String combobox, float amount){
        String [] Conversions = new String [] {"Cordobas -> Dolar", "Cordobas -> Euro", "Dolares -> Euro", "Dolares -> Cordobas", "Euros -> Dolares", "Euros -> Cordobas"};
        float result=0;
        if(combobox.equals(Conversions[0]))
        {
          result = (float) (amount/35.17);  
        }
        else if(combobox.equals(Conversions[1]))
        {
          result = (float) (amount/42.20);  
        }
        else if(combobox.equals(Conversions[2]))
        {
          result = (float) (amount/1.20);  
        }
        else if(combobox.equals(Conversions[3]))
        {
          result = (float) (amount*35.17);  
        }
        else if(combobox.equals(Conversions[4]))
        {
          result = (float) (amount*1.20);  
        }
        else if(combobox.equals(Conversions[5]))
        {
          result = (float) (amount*42.20);  
        }
        else if(combobox.equals("Select")){
            result = 0;
        }
        
        return result;
    }
}
