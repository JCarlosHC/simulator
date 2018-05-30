package net.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.model.pieza_A;

public class simulacion {

    public simulacion() {
    }
    
    public List<pieza_A> getSimulacionA(){
        ArrayList<pieza_A> lista = new ArrayList<pieza_A>();
        
        Calendar calendario = Calendar.getInstance();
        Date fecha_actual = calendario.getTime(); //Fecha actual
        Date fecha_final = utils.sumarRestarMeses(fecha_actual, 1);
        int tiempoEspera, tiempoSistema;
        int i = 0, compara, validaSimulacion;
        Date horaLlegada = fecha_actual, horaSalida = fecha_actual, horaInicio;
        
        do{
            pieza_A p = new pieza_A();
            p.setId(i);
            p.setRam1(utils.randomWithRange(0, 1));
            p.setRam2(utils.randomWithRange(0, 1));
            p.setRam3(utils.randomWithRange(0, 1));
            p.setTimeInLlegar(2 + (6 * p.getRam1())); //y = 2 + 6ri
            p.setTimeInA(5 + (6 * p.getRam2())); // z = 5 + 6ri
            
            //Validando la hora de llegada anterior
            //-1 si la hora de llegada es antes de la hora de salida de i - 1 y se espera
            //0 si son iguales
            //1 entra directo y no se espera
            compara = horaLlegada.compareTo(horaSalida); 
            if(compara == 0 || compara == 1){
                horaLlegada = utils.sumarRestarSegundos(horaLlegada, utils.convertirMinutosSeconds(p.getTimeInLlegar()));
                horaInicio = horaLlegada;
                horaSalida = utils.sumarRestarSegundos(horaInicio, utils.convertirMinutosSeconds(p.getTimeInA()));
                tiempoEspera = 0;
            }else {
                horaLlegada = utils.sumarRestarSegundos(horaLlegada, utils.convertirMinutosSeconds(p.getTimeInLlegar()));
                horaInicio = horaSalida;
                horaSalida = utils.sumarRestarSegundos(horaInicio, utils.convertirMinutosSeconds(p.getTimeInA()));
                
                tiempoEspera=(int) (horaInicio.getTime()-horaLlegada.getTime()); //En milisegundos
            }
            
            p.setHoraLlegada(horaLlegada);
            p.setHoraInicio(horaInicio);
            p.setHoraSalida(horaSalida);
            p.setTiempoEspera(utils.convertirMilisecondstoMinuts(tiempoEspera));
            p.setIsDefectuosa((p.getRam3() <= 0.25));
            tiempoSistema=(int) (horaSalida.getTime() - horaLlegada.getTime());
            p.setTimeInSystem(utils.convertirMilisecondstoMinuts(tiempoSistema));
            lista.add(p);
            i++;
            
            //Simular durante un mes
            validaSimulacion = horaSalida.compareTo(fecha_final);
            
        }while(validaSimulacion != 1);
        return lista;
    }
}
