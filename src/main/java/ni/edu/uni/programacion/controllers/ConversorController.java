/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ni.edu.uni.programacion.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ni.edu.uni.programacion.views.panels.PnlConversor;
/**
 *
 * @author GABO
 */
public class ConversorController implements ActionListener{
    private PnlConversor pnlconversor;
    
public ConversorController (PnlConversor pnlconversor){
    this.pnlconversor= pnlconversor;
    initComponent();
}
private void initComponent(){
        pnlconversor.getBtnConv1().addActionListener(this);
        pnlconversor.getBtnConv2().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    double n1;
    n1 = Double.parseDouble(pnlconversor.getTxtConv1().getText());
    if(e.getSource()==pnlconversor.getBtnConv1()){
     pnlconversor.getTxtConv2().setText(String.valueOf(producto(n1,0.028)));
    }
    if(e.getSource()==pnlconversor.getBtnConv2()){
     pnlconversor.getTxtConv2().setText(String.valueOf(producto(n1,35.10)));
    }
    }
    private double producto(double a, double b){
        return (a*b);
    }
}
