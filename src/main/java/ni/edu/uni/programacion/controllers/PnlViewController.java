/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.programacion.controllers;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import ni.edu.uni.programacion.backend.dao.implementation.JsonVehicleDaoImpl;
import ni.edu.uni.programacion.backend.pojo.Vehicle;
import ni.edu.uni.programacion.views.panels.PnlView;

/**
 *
 * @author Denzell Connolly
 */
public class PnlViewController {
    private PnlView pnlView;
    private JTable jTable;
    private JScrollPane jScrollPane;
    
    
    
    public PnlViewController(PnlView pnlView){
        this.pnlView = pnlView;
        initComponent();
    }
    
    private void initComponent(){
       jTable = new JTable(new Modelo());
       jScrollPane = new JScrollPane(jTable);
       pnlView.add(jScrollPane, BorderLayout.CENTER);
       
    }
    
}

//Creacion del modelo de la tabla

class Modelo extends AbstractTableModel{
    
    //Nombres de las columnas
    private String[] columNames = new String[]{
           "StockNumber", "Year", "Make", "Model", "Style", "VIN",
           "Exterior Color", "Interior Color", "Miles", "Price",
           "Transmission", "Engine", "Image", "Status"
       };;
    
    
    //Metodo que asigna el numero de filas en la tabla
    
    @Override
    public int getRowCount() {
        try {
            //obtengo el numero de vehiculos totales el cual sera tambien el numero total de filas
            int rows =  new JsonVehicleDaoImpl().getAll().size(); 
            return rows; //Devuelvo el numero de filas que debe tener la tabla 
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //metodo que asigna el numero total de columnas en la tabla
    
    @Override
    public int getColumnCount() {
        //devuelvo el numero total de columnas que debe tener la tabla 
        return columNames.length;
    }

    //metodo qe asigna el valor de cada campo de la tabla
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        //creo un arreglo para ingresar los datos que iran en la tabla
        Object[][] data = new Object[getRowCount()][getColumnCount()];
        int i = 0, j = 0; 
        
        try {
            //Obtengo la lista de los objeto vehiculo totales
            Collection<Vehicle> v = new JsonVehicleDaoImpl().getAll();
            
            /* Recorro la lista y obtengo cada valor correspondiente
               luego lo meto en el arreglo fila por fila*/
            for (Vehicle vehicle : v) {
                
                /* j representa cada columna
                   i representa las filas, cada que se llena una fila
                   el iterador pasa a la siguiente para seguir llenando
                   los datos */
                
                data[i][j] = vehicle.getStockNumber();
                data[i][++j] = vehicle.getYear();
                data[i][++j] = vehicle.getMake();
                data[i][++j] = vehicle.getModel();
                data[i][++j] = vehicle.getStyle();
                data[i][++j] = vehicle.getVin();
                data[i][++j] = vehicle.getExteriorColor();
                data[i][++j] = vehicle.getInteriorColor();
                data[i][++j] = vehicle.getMiles();
                data[i][++j] = vehicle.getPrice();
                data[i][++j] = vehicle.getTransmission();
                data[i][++j] = vehicle.getEngine();
                data[i][++j] = vehicle.getImage();
                data[i][++j] = vehicle.getStatus();
                
                i++;
                j = 0; // j se reestablece para que no genere errores en el arreglo
               
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        //se devuelven los datos que tendra la tabla mediante el arreglo 
        return data[rowIndex][columnIndex];
    }
    
    //metodo que cambia el nombre de las columnas
    
    @Override
    public String getColumnName(int c){
        return columNames[c]; //devuelvo el nombre que debe tener cada columna
    }
    
}
