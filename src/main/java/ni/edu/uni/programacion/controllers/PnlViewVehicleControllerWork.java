/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.programacion.controllers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JDialog;
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
    }
    
    private void txtFinderKeyTyped(KeyEvent e){
        RowFilter<ListTableModel, Object> rf = null;        
        rf = RowFilter.regexFilter(pnlViewVehicleWork.getTxtFinder().getText(), 0,1,2,3,4,5,6,7,8,9);
        tblRowSorter.setRowFilter(rf);
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
        Component add = dialog.add(pnlVehicle);
        dialog.setSize(new Dimension(400, 600));
        dialog.setTitle("New Vehicle.");
        dialog.setVisible(true);
        
    }
    
    private void btnDeleteActionListener(ActionEvent e) throws IOException{
        List<Vehicle> v = jsonVehicleDaoImpl.getAll().stream().collect(Collectors.toList());
        int index = pnlViewVehicleWork.getTblViewVehicle().getSelectedRow();
        
        if(index == -1){
            return;
        }
        jsonVehicleDaoImpl.delete(v.get(index));
    }
    
    private void loadTable() throws IOException{
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
