package net.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.model.pieza_A;
import net.model.pieza_B;

public class prueba {

    public static void main(String[] args) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        String strfechainicio = "5/6/2018 11:16:45";
        String strfechafin = "5/6/2018 14:16:45";
        Date fechainicio, fechafin;
                
        try {
            fechainicio = formato.parse(strfechainicio);
            fechafin = formato.parse(strfechafin);
            simulacion datos = new simulacion(fechainicio, fechafin);
            datos.getSimulacion();
            System.out.println("Piezas A");
            for(pieza_A pa: datos.getListA()){
                
            }
            System.out.println("Piezas B");
            for(pieza_B pb: datos.getListB()){
            
            }
            
        } catch (ParseException ex) {
            
        }
    }
    
}
