/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.eud.ups.interfaz;

import ec.edu.ups.clases.BaseDatos;
import ec.edu.ups.clases.Direccion;
import ec.edu.ups.clases.Persona;
import ec.edu.ups.controladores.ControladorDireccion;
import ec.edu.ups.controladores.ControladorPersona;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Byron PC
 */
public class Principla extends javax.swing.JFrame {

    int cont = 0;
    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;
    int cont4 = 0;
    int conp1 = 0;
    int conp2 = 0;
    int conp3 = 0;
    int conp4 = 0;
    int conp5 = 0;
    int conp6 = 0;
    int conp7 = 0;
    int conp8 = 0;
    Double suma1 = 0.00;
    Double suma2 = 0.00;
    Double suma3 = 0.00;
    Double suma4 = 0.00;
    Double suma5 = 0.00;
    Double suma6 = 0.00;
    Double suma7 = 0.00;
    Double suma8 = 0.00;
    double[] sumas;
    int[] contadores;
    double[] acumulador;
    String url = "jdbc:postgresql://localhost:5432/BaseDatosDireccion";
    String user = "postgres";
    String password = "QLJPikrq7833";
    Set<Persona> listaPersonas;
    

    /**
     * Creates new form Principla
     */
    public Principla() {
        initComponents();
         this.setExtendedState(Principla.MAXIMIZED_BOTH);
        ControladorPersona controlador = new ControladorPersona(url, user, password);
       

        sumas = new double[38];
        contadores = new int[38];
        acumulador = new double[38];
        listaPersonas = controlador.Listar();

        for (Persona persona : listaPersonas) {
            if (persona.getEdad() >= 16 && persona.getEdad() <= 20) {
                // System.out.println(persona.toString());
                if (persona.getGenero().equals("Masculino")) {
                    conp1++;
                    suma1 = suma1 + persona.getSalario();
                }
                if (persona.getGenero().equals("Femenino")) {
                    conp2++;
                    suma2 = suma2 + persona.getSalario();
                }
                cont1++;

            }
            if (persona.getEdad() >= 21 && persona.getEdad() <= 30) {
                //System.out.println(persona.toString());

                if (persona.getGenero().equals("Masculino")) {
                    conp3++;
                    suma3 = suma3 + persona.getSalario();
                }
                if (persona.getGenero().equals("Femenino")) {
                    conp4++;
                    suma4 = suma4 + persona.getSalario();
                }
                cont2++;

            }
            if (persona.getEdad() >= 31 && persona.getEdad() <= 40) {
                //System.out.println(persona.toString());
                if (persona.getGenero().equals("Masculino")) {
                    conp5++;
                    suma5 = suma5 + persona.getSalario();
                }
                if (persona.getGenero().equals("Femenino")) {
                    conp6++;
                    suma6 = suma6 + persona.getSalario();
                }
                cont3++;

            }
            if (persona.getEdad() >= 41) {
                //System.out.println(persona.toString());
                if (persona.getGenero().equals("Masculino")) {
                    conp7++;
                    suma7 = suma7 + persona.getSalario();
                }
                if (persona.getGenero().equals("Femenino")) {
                    conp8++;
                    suma8 = suma8 + persona.getSalario();
                }
                cont4++;

            }
            cont++;
        }

        int i = 0;
        for (int ed = 16; ed <= 53; ed++) {

            for (Persona persona : listaPersonas) {
                if (persona.getEdad() == ed) {
                    sumas[i] = sumas[i] + persona.getSalario();
                    contadores[i]++;
                }
            }
            if (contadores[i] == 0) {
                acumulador[i] = 0;
            } else {
                acumulador[i] = sumas[i] / contadores[i];
            }
            i++;
        }
        grafico1();
        grafico2();
        grafico3();
    }

    public void grafico1() {
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Entre 16-20", cont1);
        data.setValue("Entre 21-30", cont2);
        data.setValue("Entre 31-40", cont3);
        data.setValue("Mayores a 40", cont4);

        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
                "Promedio de Edades",
                data,
                true,
                true,
                false);

        // Mostrar Grafico
        ChartPanel frame = new ChartPanel(chart);
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(frame, BorderLayout.CENTER);
        jPanel1.setSize(700,470);
        jPanel1.validate();

    }

    public void grafico2() {
        // Fuente de Datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue((suma2 / conp2), "Mujeres", "16-20");
        dataset.setValue((suma1 / conp1), "Hombres", "16-20");
        dataset.setValue((suma4 / conp4), "Mujeres", "21-30");
        dataset.setValue((suma3 / conp3), "Hombres", "21-30");
        dataset.setValue((suma6 / conp6), "Mujeres", "31-40");
        dataset.setValue((suma5 / conp5), "Hombres", "31-40");
        dataset.setValue((suma8 / conp8), "Mujeres", "Mayores 40");
        dataset.setValue((suma7 / conp7), "Hombres", "Mayores 40");

        // Creando el Grafico
        JFreeChart chart = ChartFactory.createBarChart3D("Promedio de Salario", "Genero", "Salario",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        chart.getTitle().setPaint(Color.black);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.red);
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.add(chartPanel, BorderLayout.CENTER);
        jPanel2.setSize(700,470);
       
        jPanel2.validate();
    }

    public void grafico3() {
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

        int ed = 16;
        for (int i = 0; i < sumas.length; i++) {
            String edad = String.valueOf(ed);
            line_chart_dataset.addValue(acumulador[i], "salario", edad);
            ed++;
        }

        // Creando el Grafico
        JFreeChart chart = ChartFactory.createLineChart("Promedio de Edad",
                "Edades", "Salario", line_chart_dataset, PlotOrientation.VERTICAL,
                true, true, false);

        // Mostrar Grafico
        ChartPanel chartPanel1 = new ChartPanel(chart);
        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(chartPanel1, BorderLayout.CENTER);
        jPanel3.setSize(700,470);
        jPanel3.validate();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Generar Reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 99, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jButton2.setText("Reporte Direcciones");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton2)))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void generarPDF() {

        BaseDatos base = new BaseDatos(url, user, password);
        base.conectar();
        try {

            File reporte = new File("src/ec/edu/ups/reportes/report1.jasper");
            JasperReport reporteJasper = (JasperReport) JRLoader.loadObject(reporte);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporteJasper, null, base.getConexionBD());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "Reporte.pdf");
            JasperViewer.viewReport(jasperPrint);
            base.desconectar();
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }
    
    public void generarDirecciones(){
         BaseDatos base = new BaseDatos(url, user, password);
        base.conectar();
        try {
            String parametro = JOptionPane.showInputDialog("Ingresa cedula a buscar");
            Map parametros = new HashMap();
            parametros.put("DIR_PERSONA", parametro);
            File reporte = new File("src/ec/edu/ups/reportes/report2.jasper");
            JasperReport reporteJasper = (JasperReport) JRLoader.loadObject(reporte);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporteJasper, parametros, base.getConexionBD());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "Reporte2.pdf");
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
        
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        generarPDF();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        generarDirecciones();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
