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
public class ConversionOperation {
    public static float ConvertF2C(float farenheit){
    float result;
    float temp;
    temp = farenheit - 32;
     result = temp * 5 / 9;
    return result;
}
    
    public static float ConvertC2F(float celsius){
    float result;
    result = (celsius*9/5) + 32;
    return result;
}
}
