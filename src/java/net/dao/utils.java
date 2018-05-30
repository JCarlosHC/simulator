package net.dao;

import java.util.Calendar;
import java.util.Date;

public class utils {

    static double randomWithRange(double min, double max) {
        double range = (max - min);
        return (Math.random() * range) + min;
    }
    
    public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }

    public static Date sumarRestarDias(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    public static Date sumarRestarMeses(Date fecha, int meses) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.MONTH, meses);
        return calendar.getTime();
    }

    public static Date sumarRestarMilisegundos(Date fecha, int miliseg) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.MILLISECOND, miliseg);
        return calendar.getTime();
    }

    public static Date sumarRestarSegundos(Date fecha, int seg) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.SECOND, seg);
        return calendar.getTime();
    }
    
    public static int convertirMinutosSeconds(double minutos){
        int iPart = (int) minutos;
        double fPart = utils.formatearDecimales(minutos - iPart, 2) * 60;
        return (iPart * 60) + (int)fPart;
    }
    public static double convertirMilisecondstoMinuts(int miliseconds){
        return (double)miliseconds/60000;
    }
}
