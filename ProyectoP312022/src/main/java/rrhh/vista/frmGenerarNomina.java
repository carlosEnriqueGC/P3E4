/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrhh.vista;

import rrhh.modelo.daoNomina;
import rrhh.controlador.clsNomina;
import rrhh.modelo.daoEmpleados;
import rrhh.controlador.clsEmpleados;
import rrhh.modelo.daoConcepto;
import rrhh.controlador.clsConcepto;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.File;

/**
 *
 * @author visitante
 */
public class frmGenerarNomina extends javax.swing.JInternalFrame {

    public void procesos() {
     //iniciamos la busqueda   
     int contador = 1, x =1;
     int limite;
     
     limite = Integer.parseInt(limitante.getText());
    //inicia la busqueda de empleados 
    clsEmpleados empleadoAConsultar = new clsEmpleados();
    daoEmpleados empleadoDAO = new daoEmpleados();
    // inicia la busqueda de conceptos
    clsConcepto conceptoAConsultar = new clsConcepto();
        daoConcepto conceptoDAO = new daoConcepto();
        
        daoNomina nominaDAO = new daoNomina();
        clsEmpleados empleadoAInsertar = new clsEmpleados();
     
    while (contador <= limite) {
    
    empleadoAConsultar.setempid(contador);
    empleadoAConsultar = empleadoDAO.query(empleadoAConsultar);
    empleadoAConsultar.getempnombre();
    empleadoAConsultar.getempcargo();
    empleadoAConsultar.getempdepart();
    empleadoAConsultar.getempsueldo();       
    
    
    while (x<=7){
        
        conceptoAConsultar.setconcepid(x);
        conceptoAConsultar = conceptoDAO.query(conceptoAConsultar);
        
        conceptoAConsultar.getconcepnombre();
        conceptoAConsultar.getconcepefecto();
    x++;             
    }
    
    //"procedemos a hacer que cada concepto sume o reste al salario" by byron 
   if ((conceptoAConsultar.getconcepnombre() == "IGSS")||(conceptoAConsultar.getconcepnombre() == "igss")){
    double igss = 0.0483;
    double salario = Integer.parseInt(empleadoAConsultar.getempsueldo());
    
    double v1 = salario - salario * igss;
    double vigss = salario-v1;

    }    
    if ((conceptoAConsultar.getconcepnombre() == "ISR")||(conceptoAConsultar.getconcepnombre() == "isr")){
    double isr = 0.05;
    double salario = Integer.parseInt(empleadoAConsultar.getempsueldo());
    
    double v2 = salario - salario * isr;
     double vigss = salario-v1;

    }    
    if ((conceptoAConsultar.getconcepnombre() == "CHEQUES")||(conceptoAConsultar.getconcepnombre() == "cheques")){
    double cheque = 500;
    double salario = Integer.parseInt(empleadoAConsultar.getempsueldo());
    
    double v3 = salario +cheque;

    }    
    if ((conceptoAConsultar.getconcepnombre() == "FERIADO")||(conceptoAConsultar.getconcepnombre() == "feriado")){
    double feriado = 0;
    double salario = Integer.parseInt(empleadoAConsultar.getempsueldo());
    
    double v4 = 0;

    }  
    if ((conceptoAConsultar.getconcepnombre() == "HORAS EXTRAS")||(conceptoAConsultar.getconcepnombre() == "horas extras")){
    double horas = 7;
    double salario = Integer.parseInt(empleadoAConsultar.getempsueldo())/30;
    double extra = salario/8;
    double horaExtra = extra * 1.5;
    double calculo = horaExtra * horas;
    
    
    }  
     if ((conceptoAConsultar.getconcepnombre() == "BONOS")||(conceptoAConsultar.getconcepnombre() == "bonos")){
    double bonos = 100;
    double salario = Integer.parseInt(empleadoAConsultar.getempsueldo());
    
    double v6 = salario + bonos ;

    }   
    if ((conceptoAConsultar.getconcepnombre() == "OTROS")||(conceptoAConsultar.getconcepnombre() == "otros")){
    double otros = 500;
    double salario = Integer.parseInt(empleadoAConsultar.getempsueldo());
    
    double v7 = salario - otros ;

    }   
    
    double total =  ;
     
     
    contador++;
    }
        
    }
 
    
    
    
    public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Id Nomina");
        modelo.addColumn("Nombre empleado");
        modelo.addColumn("Cargo");
         modelo.addColumn("Departamento");
        modelo.addColumn("Salario");
        modelo.addColumn("conceptos");
        modelo.addColumn("Valor Nomina");
        
        daoNomina nominaDAO = new daoNomina();
        List<clsNomina> nominas = nominaDAO.select();
        Nomina.setModel(modelo);
        String[] dato = new String[8];
        for (int i = 0; i < nominas.size(); i++) {
            dato[0] = Integer.toString(nominas.get(i).getnomiid());
            dato[1] = nominas.get(i).getnominombre();
            dato[2] = nominas.get(i).getnomicargo();
            dato[3] = nominas.get(i).getnomidepart();
            dato[4] = nominas.get(i).getnomisalario();
            dato[5] = nominas.get(i).getnomiconcepto();
            dato[6] = nominas.get(i).getnomivalor();
            
            //System.out.println("cargo:" + cargos);
            modelo.addRow(dato);
        }
    }

 

    public frmGenerarNomina() {
        initComponents();
        llenadoDeTablas();
        procesos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb2 = new javax.swing.JLabel();
        lbusu = new javax.swing.JLabel();
        Generar = new javax.swing.JButton();
        label1 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        limitante = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Nomina = new javax.swing.JTable();
        lb = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Generacion de nominas");
        setVisible(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Generar.setText("Generar");
        Generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarActionPerformed(evt);
            }
        });
        getContentPane().add(Generar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 95, -1));

        label1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label1.setText("Visualizacion de la nomina");
        getContentPane().add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, -1, -1));

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText("Cantidad de empleados");
        getContentPane().add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        limitante.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        limitante.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        limitante.setOpaque(false);
        limitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limitanteActionPerformed(evt);
            }
        });
        getContentPane().add(limitante, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 110, -1));

        Nomina.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Nomina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Nomina", "Nombre empleado", "Cargo", "Departamento", "Salario", "Conceptos", "Valor Nomina"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Nomina);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 880, 303));

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");
        getContentPane().add(lb, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 20, 13, -1));

        label6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label6.setText("-----------------Generacion de nomina-----------------");
        getContentPane().add(label6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 360, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarActionPerformed
//        daocargo cargoDAO = new daocargo();
       
    }//GEN-LAST:event_GenerarActionPerformed

    private void limitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limitanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_limitanteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Generar;
    private javax.swing.JTable Nomina;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lbusu;
    private javax.swing.JTextField limitante;
    // End of variables declaration//GEN-END:variables
}
