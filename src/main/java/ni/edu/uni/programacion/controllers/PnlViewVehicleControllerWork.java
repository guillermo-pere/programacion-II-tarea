/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.programacion.controllers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import ni.edu.uni.programacion.backend.dao.implementation.JsonVehicleDaoImpl;
import ni.edu.uni.programacion.backend.pojo.Vehicle;
import ni.edu.uni.programacion.data.models.ListTableModel;
import ni.edu.uni.programacion.views.FrmVehicle;
import ni.edu.uni.programacion.views.panels.PnlVehicle;
import ni.edu.uni.programacion.views.panels.PnlViewVehicleWork;


/**
 *
 * @author Sistemas-05
 */
public class PnlViewVehicleControllerWork {
    private PnlViewVehicleWork pnlViewVehicleWork;
    private JsonVehicleDaoImpl jsonVehicleDaoImpl;
    private PnlVehicle pnlVehicle;
    private PnlVehicleController pnlVehicleController;
    private ListTableModel tblViewVehicleModel;
    private List<Vehicle> vehicles;
    private final String[] HEADERS = new String[]{"StockNumber","Year","Make","Model",
    "Style","Vin","Exterior color","Interior color","Miles","price","Transmission",
    "Engine","Image","Status"};
    private TableRowSorter<ListTableModel> tblRowSorter;
    private JsonVehicleDaoImpl jvdao;
    
    public PnlViewVehicleControllerWork(PnlViewVehicleWork pnlViewVehicleWork) {
        this.pnlViewVehicleWork = pnlViewVehicleWork;
        initComponent();
    }
    
    private void initComponent(){
        try {
            jsonVehicleDaoImpl = new JsonVehicleDaoImpl();
            loadTable();  
            
            pnlViewVehicleWork.getTxtFinder().addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e){
                    txtFinderKeyTyped(e);
                }
            });
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PnlViewVehicleControllerWork.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PnlViewVehicleControllerWork.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pnlViewVehicleWork.getBtnNew().addActionListener((e)->{
            btnNewActionListener(e);
        });
        
        pnlViewVehicleWork.getBtnDelete().addActionListener((e) -> {
            try {
                btnDeleteActionListener(e);
            } catch (IOException ex) {
                Logger.getLogger(PnlViewVehicleControllerWork.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          pnlViewVehicleWork.getBtnUpdate().addActionListener((e)->{
            try {
                btnUpdateActionListener(e);
            } catch (IOException ex) {
                Logger.getLogger(PnlViewVehicleControllerWork.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private void txtFinderKeyTyped(KeyEvent e){
        RowFilter<ListTableModel, Object> rf = null;        
        rf = RowFilter.regexFilter(pnlViewVehicleWork.getTxtFinder().getText(), 0,1,2,3,4,5,6,7,8,9);
        tblRowSorter.setRowFilter(rf);
    }
    
    private void btnUpdateActionListener(ActionEvent e) throws IOException{
        jvdao = new JsonVehicleDaoImpl();
            if(pnlVehicle == null){
            pnlVehicle = new PnlVehicle();
            try {
                pnlVehicleController = new  PnlVehicleController(pnlVehicle);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrmVehicle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         JDialog dialog = new JDialog();
         pnlVehicle.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnCancelActionListener(e, dialog);
                } catch (IOException ex) {
                    Logger.getLogger(PnlViewVehicleControllerWork.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        String stock, make, model, style, vin, eColor, iColor, engine, image, status, year;
        int miles;
        float price;
        Vehicle.Transmission transmission = Vehicle.Transmission.AUTOMATIC;
        List<Vehicle> v = jsonVehicleDaoImpl.getAll().stream().collect(Collectors.toList());
        int index = pnlViewVehicleWork.getTblViewVehicle().getSelectedRow();
        
        if(index== -1){
            return;
        }
        
        var vehicle = v.get(index);
       stock =  String.valueOf(vehicle.getStockNumber());
       style = vehicle.getStyle();
       vin = vehicle.getVin();
       engine = vehicle.getEngine();
       image = vehicle.getImage();
       
       year = String.valueOf(vehicle.getYear());
       make = vehicle.getMake();
       model = vehicle.getModel();
       eColor = vehicle.getExteriorColor();
       iColor = vehicle.getInteriorColor();
       status = vehicle.getStatus();
       
       miles =Integer.parseInt(vehicle.getMiles());
       price = vehicle.getPrice();
       
       
               
        pnlVehicle.getTxtStock().setText(stock);
        pnlVehicle.getTxtStyle().setText(style);
        pnlVehicle.getFmtVin().setText(vin);
        pnlVehicle.getTxtEngine().setText(engine);
        pnlVehicle.getTxtImage().setText(image);
        
        pnlVehicle.getSpnMiles().setValue(miles);
        pnlVehicle.getSpnPrice().setValue(price);
        
        pnlVehicle.getCmbYear().setSelectedItem(year);
        pnlVehicle.getCmbMake().setSelectedItem(make);
        pnlVehicle.getCmbModel().setSelectedItem(model);
        pnlVehicle.getCmbEColor().setSelectedItem(eColor);
        pnlVehicle.getCmbIColor().setSelectedItem(iColor);
        pnlVehicle.getCmbStatus().setSelectedItem(status);
        

         pnlVehicle.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnCancelActionListener(e, dialog);
                } catch (IOException ex) {
                    Logger.getLogger(PnlViewVehicleControllerWork.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         pnlVehicle.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSaveActionListener(e);
                try {
                    btnDeleteActionListener(e);
                } catch (IOException ex) {
                    Logger.getLogger(PnlViewVehicleControllerWork.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    loadTable();
                } catch (IOException ex) {
                    Logger.getLogger(PnlViewVehicleControllerWork.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        });
         
         
          Component add = dialog.add(pnlVehicle);
        dialog.setSize(new Dimension(400, 600));
        dialog.setTitle("Update Vehicle.");
        dialog.setVisible(true);
        
        
    }
    
    private void btnNewActionListener(ActionEvent e){
        if(pnlVehicle == null){
            pnlVehicle = new PnlVehicle();
            try {
                pnlVehicleController = new  PnlVehicleController(pnlVehicle);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrmVehicle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         JDialog dialog = new JDialog();
         pnlVehicle.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnCancelActionListener(e, dialog);
                } catch (IOException ex) {
                    Logger.getLogger(PnlViewVehicleControllerWork.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         
         
        Component add = dialog.add(pnlVehicle);
        dialog.setSize(new Dimension(400, 600));
        dialog.setTitle("New Vehicle.");
        dialog.setVisible(true);
        
    }
      private void btnCancelActionListener(ActionEvent e, JDialog dialog) throws IOException{
        dialog.dispose();
        loadTable();
    }
      
     
      
    
    private void btnDeleteActionListener(ActionEvent e) throws IOException{
        List<Vehicle> v = jsonVehicleDaoImpl.getAll().stream().collect(Collectors.toList());
        int index = pnlViewVehicleWork.getTblViewVehicle().getSelectedRow();
        
        if(index == -1){
            return;
        }
        jsonVehicleDaoImpl.delete(v.get(index));
        
        loadTable();
    }
    
    private void btnSaveActionListener(ActionEvent e){
        int stock, year;
        String make, model, style, vin, eColor, iColor, miles, engine, image, status;
        float price;
        Vehicle.Transmission transmission = Vehicle.Transmission.AUTOMATIC;
        
        if(pnlVehicle.getTxtStock().getText().isEmpty()){
            return;
        }
        stock = Integer.parseInt(pnlVehicle.getTxtStock().getText());
        year = Integer.parseInt(pnlVehicle.getCmbYear().getSelectedItem().toString());
        make = pnlVehicle.getCmbMake().getSelectedItem().toString();
        model = pnlVehicle.getCmbModel().getSelectedItem().toString();
        style = pnlVehicle.getTxtStyle().getText();
        vin = pnlVehicle.getFmtVin().getText();
        eColor = pnlVehicle.getCmbEColor().getSelectedItem().toString();
        iColor = pnlVehicle.getCmbIColor().getSelectedItem().toString();
        miles = pnlVehicle.getSpnMiles().getModel().getValue().toString();
        price = Float.parseFloat(pnlVehicle.getSpnPrice().getModel().getValue().toString());
        engine = pnlVehicle.getTxtEngine().getText();
        image = pnlVehicle.getTxtImage().getText();
        status = pnlVehicle.getCmbStatus().getSelectedItem().toString();
        transmission = pnlVehicle.getRbtnAut().isSelected() ?
                transmission : Vehicle.Transmission.MANUAL;
                
        Vehicle v = new Vehicle(stock, year, make, model, 
                style, vin, eColor, iColor, miles, price, transmission, engine, image, status);
        
        try {
            jvdao.create(v);
            JOptionPane.showMessageDialog(null, "Vehicle saved successfully.", 
                    "Information message", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), 
                    "Error message", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(PnlVehicleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void loadTable() throws IOException{
        vehicles = jsonVehicleDaoImpl.getAll().stream().collect(Collectors.toList());
        tblViewVehicleModel = new ListTableModel(vehicles, HEADERS);
        tblRowSorter = new TableRowSorter<>(tblViewVehicleModel);
        
        pnlViewVehicleWork.getTblViewVehicle().setModel(tblViewVehicleModel);
        pnlViewVehicleWork.getTblViewVehicle().setRowSorter(tblRowSorter);
    }
    
//    private Object[][] getData() throws IOException{
//        vehicles = jsonVehicleDaoImpl.getAll().stream().collect(Collectors.toList());
//        Object[][] data = new Object[vehicles.size()][vehicles.get(0).asArray().length];
//        
//        IntStream.range(0, vehicles.size()).forEach(i -> {
//            data[i] = vehicles.get(i).asArray();
//        });
//        
//        return data;
//    }
}
