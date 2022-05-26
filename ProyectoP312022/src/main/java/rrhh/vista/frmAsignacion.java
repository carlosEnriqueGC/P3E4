/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrhh.vista;

import rrhh.modelo.daoEmpleados;
import rrhh.controlador.clsEmpleados;
import rrhh.modelo.daoConcepto;
import rrhh.controlador.clsConcepto;

import rrhh.modelo.daoAsignacion;
import rrhh.controlador.clsAsignacion;

import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import java.io.File;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author visitante
 */
public class frmAsignacion extends javax.swing.JInternalFrame {

    public void llenadoDeCombos() {
        daoEmpleados empleadoDAO = new daoEmpleados();
        List<clsEmpleados> empleados = empleadoDAO.select();
        empleadoss.addItem("Seleccione una opción");
        for (int i = 0; i < empleados.size(); i++) {
            empleadoss.addItem(empleados.get(i).getempnombre());
        }
    }
    
    public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Concepto");
        modelo.addColumn("Total");
     
        daoAsignacion asignaDAO = new daoAsignacion();
        List<clsAsignacion> asignas = asignaDAO.select();
        tablaasignacion.setModel(modelo);
        String[] dato = new String[5];
        for (int i = 0; i < asignas.size(); i++) {
            dato[0] = Integer.toString(asignas.get(i).getaid());
            dato[1] = asignas.get(i).getanombre();
            dato[2] = asignas.get(i).getaconcepto();
            dato[3] = asignas.get(i).getavalor();
            //System.out.println("vendedor:" + vendedores);
            modelo.addRow(dato);
        }
    }

    public void agrega(Stack pila){
    
     agregados.setText(String.valueOf(pila));
   
       
    }
 

    public void buscarconcepto() {
        clsConcepto conceptoAConsultar = new clsConcepto();
        daoConcepto conceptoDAO = new daoConcepto();
        
        conceptoAConsultar.setconcepid(Integer.parseInt(txtbuscado.getText()));
        conceptoAConsultar = conceptoDAO.query(conceptoAConsultar);
        concepto.setText(conceptoAConsultar.getconcepnombre());     
        valor.setText(conceptoAConsultar.getconcepvalor());
        efecto.setSelectedItem(conceptoAConsultar.getconcepefecto());
       
        }
        
    public void buscarsalario() {
        clsEmpleados empleadoAConsultar2 = new clsEmpleados();
        daoEmpleados empleadoDAO = new daoEmpleados();
        
        empleadoAConsultar2.setempnombre(empleadoss.getSelectedItem().toString());
        empleadoAConsultar2 = empleadoDAO.query2(empleadoAConsultar2);
    
        salario.setText(empleadoAConsultar2.getempsueldo());     
           
        }

    
    public void sumayresta(){
efecto.addItem("");
efecto.addItem("+");
efecto.addItem("-");
}

   public void tipo(){
tiposAsignacion.addItem("algunos/excepcion");
tiposAsignacion.addItem("muchos");
}    
   
 public void empleado(){
empleadoss.addItem("seleccione:");
}    
    
    public void procedimientosAdd(){
   String igss = "igss";
   String isr= "isr" ;
   String conceptos = concepto.getText();
   Double numero1= Double.parseDouble(salario.getText());
   Double numero2= Double.parseDouble(valor.getText()); 
   String operacion = efecto.getSelectedItem().toString();
   int calculos = efecto.getSelectedIndex();
   
   if(calculos == 1){
   
   if (conceptos.equals(igss)){
   double porcentaje = (numero2 * numero1)/100;
   salario.setText(String.valueOf(numero1 + porcentaje));
   
   }
   else if (isr.equals(conceptos)){
   double porcentaje = (numero2 * numero1)/100;
   salario.setText(String.valueOf(numero1 + porcentaje));
   
   }else{
  salario.setText(String.valueOf(numero1 + numero2));
   }
  
   }
   
   
   if(calculos == 2){
   
     if (conceptos.equals(igss)){
   double porcentaje = (numero2 * numero1)/100;
   salario.setText(String.valueOf(numero1 - porcentaje));
   
   }
     else if (isr.equals(conceptos)){
   double porcentaje = (numero2 * numero1)/100;
   salario.setText(String.valueOf(numero1 - porcentaje));
   
   }else{
  salario.setText(String.valueOf(numero1 - numero2));
   }

   }
    }

    public void autollenadoEmpleados(){
     int c = 0;
     String consultar="",vacio="";
    String agregados;
     int contador=1;
    consultar = JOptionPane.showInputDialog("registro" + "Numero de empleados" + c+".");
    int a = Integer.parseInt(consultar);
     clsEmpleados empleadoAConsultar = new clsEmpleados();
     daoEmpleados empleadoDAO = new daoEmpleados();
        
     clsAsignacion busquedaAConsultar = new clsAsignacion();
     daoAsignacion busquedaDAO = new daoAsignacion();
     
     daoAsignacion datosDAO = new daoAsignacion();
     clsAsignacion datosAInsertar = new clsAsignacion(); 
        
    while(contador <= a){
    
    empleadoAConsultar.setempid(contador);
    empleadoAConsultar = empleadoDAO.query(empleadoAConsultar); 
    busquedaAConsultar.setaid(contador);
    busquedaAConsultar = busquedaDAO.query(busquedaAConsultar);
    busquedaAConsultar.getanombre();
     
     empleadoAConsultar.getempnombre();
     empleadoAConsultar.getempsueldo();
     empleadoAConsultar.getempdias();
    
     if(empleadoAConsultar.getempnombre().equals(busquedaAConsultar.getanombre())){
     
     
     
     }else{
         
     agregados =  String.valueOf(pila);
     
     datosAInsertar.setanombre(empleadoAConsultar.getempnombre());
     datosAInsertar.setaconcepto(agregados);
     datosAInsertar.setavalor(empleadoAConsultar.getempsueldo());
  
     datosDAO.insert(datosAInsertar);
      llenadoDeTablas();
     
     }         
    
      
      contador++;
   
    }
    
    
    
    
    
    }
    
    
    public frmAsignacion() {
        initComponents();
        llenadoDeTablas();
        llenadoDeCombos();
        sumayresta();
        tipo();
        empleado();
        
   
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
        jCheckBox1 = new javax.swing.JCheckBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        btnEliminar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        Buscar = new javax.swing.JButton();
        label3 = new javax.swing.JLabel();
        txtbuscado = new javax.swing.JTextField();
        concepto = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        lb = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaasignacion = new javax.swing.JTable();
        label4 = new javax.swing.JLabel();
        empleadoss = new javax.swing.JComboBox<>();
        label2 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        valor = new javax.swing.JTextField();
        lb1 = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();
        lb3 = new javax.swing.JLabel();
        label7 = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        agregados = new javax.swing.JTextField();
        label8 = new javax.swing.JLabel();
        reiniciar = new javax.swing.JButton();
        label9 = new javax.swing.JLabel();
        salario = new javax.swing.JTextField();
        buscarSalario = new javax.swing.JButton();
        efecto = new javax.swing.JComboBox<>();
        label10 = new javax.swing.JLabel();
        tiposAsignacion = new javax.swing.JComboBox<>();
        tipos_asignacion = new javax.swing.JButton();
        label11 = new javax.swing.JLabel();

        lb2.setForeground(new java.awt.Color(204, 204, 204));
        lb2.setText(".");

        jCheckBox1.setText("jCheckBox1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Asignaciones");
        setVisible(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, 95, -1));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, 95, -1));

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        getContentPane().add(Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 70, 30));

        label3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label3.setText(".");
        getContentPane().add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 550, 20, 10));
        getContentPane().add(txtbuscado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 120, 30));

        concepto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        concepto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        concepto.setOpaque(false);
        getContentPane().add(concepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 100, 30));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, 95, -1));

        lb.setForeground(new java.awt.Color(204, 204, 204));
        lb.setText(".");
        getContentPane().add(lb, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 13, -1));

        tablaasignacion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tablaasignacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Concepto", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaasignacion);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 350, 303));

        label4.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label4.setText("Nombre");
        getContentPane().add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, -1, -1));

        empleadoss.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        empleadoss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadossActionPerformed(evt);
            }
        });
        getContentPane().add(empleadoss, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 120, -1));

        label2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label2.setText("Conceptos Asignados");
        getContentPane().add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, -1, 20));

        label5.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label5.setText("Conceptos agregados");
        getContentPane().add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        valor.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        valor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        valor.setOpaque(false);
        getContentPane().add(valor, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 100, 30));

        lb1.setForeground(new java.awt.Color(204, 204, 204));
        lb1.setText(".");
        getContentPane().add(lb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 13, -1));

        label6.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label6.setText("Valor");
        getContentPane().add(label6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, -1, -1));

        lb3.setForeground(new java.awt.Color(204, 204, 204));
        lb3.setText(".");
        getContentPane().add(lb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, 13, -1));

        label7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label7.setText("Efecto");
        getContentPane().add(label7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, -1, -1));

        agregar.setText("agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        getContentPane().add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 95, -1));
        getContentPane().add(agregados, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 220, 30));

        label8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label8.setText("Tipo de asignacion");
        getContentPane().add(label8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        reiniciar.setText("reset");
        reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarActionPerformed(evt);
            }
        });
        getContentPane().add(reiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 140, 95, -1));

        label9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label9.setText("Salario final");
        getContentPane().add(label9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 70, 20));
        getContentPane().add(salario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 220, 30));

        buscarSalario.setText("Buscar");
        buscarSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarSalarioActionPerformed(evt);
            }
        });
        getContentPane().add(buscarSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 70, -1));

        efecto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        efecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efectoActionPerformed(evt);
            }
        });
        getContentPane().add(efecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 100, 20));

        label10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label10.setText("Buscar Concepto");
        getContentPane().add(label10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        tiposAsignacion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tiposAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tiposAsignacionActionPerformed(evt);
            }
        });
        getContentPane().add(tiposAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 120, -1));

        tipos_asignacion.setText("Eligir");
        tipos_asignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipos_asignacionActionPerformed(evt);
            }
        });
        getContentPane().add(tipos_asignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 70, -1));

        label11.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        label11.setText("Empleado");
        getContentPane().add(label11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String algun ="algunos/excepcion", much="muchos";
        
        
        
        int c = 0;
        String consultar="";
        consultar = JOptionPane.showInputDialog("Eliminar Asignacion" + " Ingresar numero de ID que aparece en la tabla derecha." + c+".");
        
        
        
        daoAsignacion asignaDAO = new daoAsignacion();
        clsAsignacion asignaAEliminar = new clsAsignacion();;
        asignaAEliminar.setaid(Integer.parseInt(consultar));
        asignaDAO.delete(asignaAEliminar);
        llenadoDeTablas();
        
       
       
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
       String algun ="algunos/excepcion", much="muchos";
       
         if(tiposAsignacion.getSelectedItem().toString().equals(much)){
        
        
             autollenadoEmpleados();
             
             
        }else{
        daoAsignacion perfilusuarioDAO = new daoAsignacion();
        clsAsignacion perfilusuarioAInsertar = new clsAsignacion();
        perfilusuarioAInsertar.setanombre(empleadoss.getSelectedItem().toString());
        perfilusuarioAInsertar.setaconcepto(String.valueOf(pila));
        perfilusuarioAInsertar.setavalor(salario.getText());
        // aqui falta el valor
        perfilusuarioDAO.insert(perfilusuarioAInsertar);
        llenadoDeTablas();
        
         }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        // TODO add your handling code here:
        buscarconcepto();
 
    }//GEN-LAST:event_BuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        
        concepto.setText("");
        salario.setText("");
        valor.setText("");
        efecto.setSelectedIndex(0);
        tiposAsignacion.setSelectedIndex(0);
        empleadoss.setSelectedIndex(0);
        txtbuscado.setText("");
        agregados.setText("");
        btnRegistrar.setEnabled(true);
        btnEliminar.setEnabled(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void empleadossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleadossActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_empleadossActionPerformed
Stack<String> pila = new Stack<String>();

int nagregados = 1; int n;
    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        // TODO add your handling code here:
       String algun ="algunos/excepcion", much="muchos";
        if(tiposAsignacion.getSelectedItem().toString().equals(much)){
         String nameconcepto; 
        nameconcepto = concepto.getText(); 
        pila.push(nameconcepto); 
        agrega(pila); 
        n = nagregados++;
        }else{
        String nameconcepto; 
        nameconcepto = concepto.getText(); 
        pila.push(nameconcepto); 
        agrega(pila); 
        
      
        
        procedimientosAdd();
        
        
        n = nagregados++;
        }
    }//GEN-LAST:event_agregarActionPerformed

    private void reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarActionPerformed
        // TODO add your handling code here:
        int contador = 1;
        
        while(contador <= n){
        if(!pila.empty()){ 
                pila.pop(); 
                agrega(pila); 
        }
        contador++;
        }
        salario.setText("");
    }//GEN-LAST:event_reiniciarActionPerformed

    private void efectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efectoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_efectoActionPerformed

    private void buscarSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarSalarioActionPerformed
        // TODO add your handling code here:
        buscarsalario();
    }//GEN-LAST:event_buscarSalarioActionPerformed

    private void tiposAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tiposAsignacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tiposAsignacionActionPerformed

    private void tipos_asignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipos_asignacionActionPerformed
        // TODO add your handling code here:
         String algun ="algunos/excepcion", much="muchos";
        
         if(tiposAsignacion.getSelectedItem().toString().equals(much)){
        
             buscarSalario.setVisible(false);
             empleadoss.setVisible(false);
             label11.setVisible(false);
             label9.setVisible(false);
             salario.setVisible(false);
        }else{
         buscarSalario.setVisible(true);
             empleadoss.setVisible(true);
             label11.setVisible(true);
             label9.setVisible(true);
             salario.setVisible(true);
         
         }
        
    }//GEN-LAST:event_tipos_asignacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JTextField agregados;
    private javax.swing.JButton agregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton buscarSalario;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField concepto;
    private javax.swing.JComboBox<String> efecto;
    private javax.swing.JComboBox<String> empleadoss;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label10;
    private javax.swing.JLabel label11;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    private javax.swing.JLabel label9;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lbusu;
    private javax.swing.JButton reiniciar;
    private javax.swing.JTextField salario;
    private javax.swing.JTable tablaasignacion;
    private javax.swing.JComboBox<String> tiposAsignacion;
    private javax.swing.JButton tipos_asignacion;
    private javax.swing.JTextField txtbuscado;
    private javax.swing.JTextField valor;
    // End of variables declaration//GEN-END:variables
}
