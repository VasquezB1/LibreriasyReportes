/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.eud.ups.interfaz;

import ec.edu.ups.clases.Persona;
import ec.edu.ups.controladores.ControladorPersona;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Byron PC
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
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
    Set<Persona> listaPersonas;
    public static void main(String[] args) {
        // TODO code application logic here
       String url = "jdbc:postgresql://localhost:5432/BaseDatosDireccion";
        String user = "postgres";
        String password = "QLJPikrq7833";
        
        
        /*Persona p = new Persona("0302306543", "BYRON SIMON", "VÁSQUEZ SALDAÑA", 12, new java.util.Date(), "0987815998", 2536.9);
        ControladorPersona control = new ControladorPersona(url, user, password);
        try {
            control.create(p);
        } catch (SQLException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //Realizar Graficos con El JFreeChart
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("C", 40);
        data.setValue("Java", 45);
        data.setValue("Python", 15);
 
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart(
         "Ejemplo Rapido de Grafico en un ChartFrame", 
         data, 
         true, 
         true, 
         false);
 
        // Mostrar Grafico
        ChartFrame frame = new ChartFrame("JFreeChart", chart);
        frame.pack();
        frame.setVisible(true);
        
        
      
        
    }
    
}
