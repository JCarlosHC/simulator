package net.dao;

import com.json.JSONArray;
import com.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.model.fresa;
import net.model.pieza_A;
import net.model.pieza_B;
import net.model.torno;

public class simulacion {

    public simulacion() {
    }
    
    public JSONObject getSimulacion(){
        JSONObject objJson = new JSONObject();
        JSONArray objPiezasA, objPiezasB, objtornos, objfresas;
        
        ArrayList<pieza_A> lista_piezasA = new ArrayList<>();
        ArrayList<pieza_B> lista_piezasB = new ArrayList<>();
        ArrayList<torno> tornos = new ArrayList<>();
        ArrayList<fresa> fresas = new ArrayList<>();
        
        Calendar calendario = Calendar.getInstance();
        Date fecha_actual = calendario.getTime(); //Fecha actual
        Date fecha_final = utils.sumarRestarDias(fecha_actual, 1);
        //Date fecha_final = utils.sumarRestarMeses(fecha_actual, 1);
        int tiempoEsperaA, tiempoSistemaA, tiempoEsperaB_Fresas, tiempoEsperaB_tornos, tiempoSistemaB;
        int i = 0, validaSimulacion, idtorno = 1, idtornodisponible, idfresa = 1, idfresadisponible;
        Date horaLlegadaA = fecha_actual, horaSalidaA, horaInicioA;
        Date horaLlegadaB_Fresas = fecha_actual, horaSalidaB_Fresas, horaInicioB_Fresas, 
             horaLlegadaB_tornos, horaSalidaB_tornos, horaInicioB_tornos;
        
        //Inicializo mis tornos y fresas.
        torno t = new torno(idtorno, 0);  
        t.setHorafin(horaLlegadaA);
        tornos.add(t);
        fresa f = new fresa(idfresa, 0);
        f.setHorafin(horaLlegadaB_Fresas);
        fresas.add(f);
        
        do{
            //Inicializando objetos
            pieza_A pA = new pieza_A(i,utils.randomWithRange(0, 1),utils.randomWithRange(0, 1),utils.randomWithRange(0, 1));
            pieza_B pB = new pieza_B(i,utils.randomWithRange(0, 1),utils.randomWithRange(0, 1),utils.randomWithRange(0, 1),utils.randomWithRange(0, 1));
            
            pA.setTimeInLlegar(2 + (6 * pA.getRam1())); //y = 2 + 6ri
            pB.setTimeInLlegar(5 + (6 * pB.getRam1())); //Y = 5 + 6ri
            // A is tornos, B is fresas
            pA.setTimeInA(5 + (6 * pA.getRam2())); // z = 5 + 6ri
            pB.setTimeInA(2 + (2 * pB.getRam2())); // Z = 2 + 2ri
            pB.setTimeInB(6 + (6 * pB.getRam3())); // x = 6 + 6ri
                  
            //tiempos pieza A para tornos
            horaLlegadaA = utils.sumarRestarSegundos(horaLlegadaA, utils.convertirMinutosSeconds(pA.getTimeInLlegar()));
            horaInicioA = horaLlegadaA;
            horaSalidaA = utils.sumarRestarSegundos(horaInicioA, utils.convertirMinutosSeconds(pA.getTimeInA()));
            tiempoEsperaA = 0;
            //tiempos pieza B para fresas
            horaLlegadaB_Fresas= utils.sumarRestarSegundos(horaLlegadaB_Fresas, utils.convertirMinutosSeconds(pB.getTimeInLlegar()));
            horaInicioB_Fresas = horaLlegadaB_Fresas;
            horaSalidaB_Fresas = utils.sumarRestarSegundos(horaInicioB_Fresas, utils.convertirMinutosSeconds(pB.getTimeInB()));
            tiempoEsperaB_Fresas = 0;
            
            //Refrescamos status tornos
            changeStatusTorno(tornos, horaLlegadaA);
            //si es 0 no encontro disponibles dentro de los que ya hay
            idtornodisponible = buscaTornoDisponible(tornos);
            if(idtornodisponible == 0){
                idtorno++;
                torno nuevo = new torno(idtorno, 0);
                tornos.add(nuevo);
                idtornodisponible = idtorno;
            }
            //Actualizamos informacion tornos
            tornos.get(idtornodisponible - 1).setHorafin(horaSalidaA);
            tornos.get(idtornodisponible - 1).setStatus(1);
            
             //Piezas a
            pA.setHoraLlegada(horaLlegadaA);
            pA.setHoraInicio(horaInicioA);
            pA.setHoraSalida(horaSalidaA);
            pA.setTiempoEspera(utils.convertirMilisecondstoMinuts(tiempoEsperaA));
            pA.setIsDefectuosa((pA.getRam3() <= 0.25));
            
            tiempoSistemaA=(int) (horaSalidaA.getTime() - horaLlegadaA.getTime());
            pA.setTimeInSystem(utils.convertirMilisecondstoMinuts(tiempoSistemaA));
            pA.setIdtorno(idtornodisponible);
            
            //Refrescamos status fresas
            changeStatusFresa(fresas, horaLlegadaA);
            //si es 0 no encontro disponibles dentro de los que ya hay
            idfresadisponible = buscafresaDisponible(fresas);
            if(idfresadisponible == 0){
                idfresa++;
                fresa nuevo = new fresa(idfresa, 0);
                fresas.add(nuevo);
                idfresadisponible = idfresa;
            }
            //Actualizamos informacion fresas
            fresas.get(idfresadisponible - 1).setHorafin(horaSalidaB_Fresas);
            fresas.get(idfresadisponible - 1).setStatus(1);
            
            //tiempos pieza B para tornos
            horaLlegadaB_tornos = horaSalidaB_Fresas;
            horaInicioB_tornos = horaSalidaB_Fresas;
            horaSalidaB_tornos = utils.sumarRestarSegundos(horaInicioB_tornos, utils.convertirMinutosSeconds(pB.getTimeInA()));
            tiempoEsperaB_tornos = 0;
            
            //Refrescamos status tornos
            changeStatusTorno(tornos, horaLlegadaB_tornos);
            //si es 0 no encontro disponibles dentro de los que ya hay
            idtornodisponible = buscaTornoDisponible(tornos);
            if(idtornodisponible == 0){
                idtorno++;
                torno nuevo = new torno(idtorno, 0);
                tornos.add(nuevo);
                idtornodisponible = idtorno;
            }
            //Actualizamos informacion tornos
            tornos.get(idtornodisponible - 1).setHorafin(horaSalidaB_tornos);
            tornos.get(idtornodisponible - 1).setStatus(1);
            
            //Piezas b
            pB.setHoraLlegada(horaLlegadaB_Fresas);
            pB.setHoraInicio(horaInicioB_Fresas);
            pB.setHoraSalida(horaSalidaB_Fresas);
            pB.setTiempoEspera(utils.convertirMilisecondstoMinuts(tiempoEsperaB_Fresas));
            pB.setHoraLlegadaTornos(horaLlegadaB_tornos);
            pB.setHoraInicioTornos(horaInicioB_tornos);
            pB.setHoraSalidaTornos(horaSalidaB_tornos);
            pB.setTiempoEsperaTornos(tiempoEsperaB_tornos);
            pB.setIsDefectuosa((pB.getRam4() <= 0.05));
            
            tiempoSistemaB=(int) (horaSalidaB_tornos.getTime() - horaLlegadaB_Fresas.getTime());
            pB.setTimeInSystem(utils.convertirMilisecondstoMinuts(tiempoSistemaB));
            pB.setIdtorno(idtornodisponible);
            pB.setIdfresa(idfresadisponible);
           
            lista_piezasA.add(pA);
            lista_piezasB.add(pB);
            
            //Simular durante un mes
            validaSimulacion = horaSalidaA.compareTo(fecha_final);
            i++;
        }while(validaSimulacion != 1);
        
        objPiezasA = new JSONArray(lista_piezasA);
        objPiezasB = new JSONArray(lista_piezasB);
        objtornos = new JSONArray(tornos);
        objfresas = new JSONArray(fresas);
        objJson.put("tornos", objtornos);
        objJson.put("fresas", objfresas);
        objJson.put("listaA", objPiezasA);
        objJson.put("listaB", objPiezasB);
        return objJson;
    }
    
    public List<pieza_A> getSimulacionA(){
        ArrayList<pieza_A> lista = new ArrayList<pieza_A>();
        ArrayList<torno> tornos = new ArrayList<torno>();
        ArrayList<fresa> fresas = new ArrayList<fresa>();
        
        Calendar calendario = Calendar.getInstance();
        Date fecha_actual = calendario.getTime(); //Fecha actual
        Date fecha_final = utils.sumarRestarMeses(fecha_actual, 1);
        int tiempoEsperaA, tiempoSistemaA, tiempoEsperaB, tiempoSistemaB;
        int i = 0, validaSimulacion, idtorno = 1, idtornodisponible = 0, idfresa = 1;
        Date horaLlegadaA = fecha_actual, horaSalidaA, horaInicioA;
        Date horaLlegadaB = fecha_actual, horaSalidaB = fecha_actual, horaInicioB;
        
        //Inicializo mis tornos y fresas con uno cada quien
        torno t = new torno(idtorno, 0);  
        t.setHorafin(horaLlegadaA);
        tornos.add(t);
        do{
            //Inicializando objetos
            pieza_A pA = new pieza_A(i,utils.randomWithRange(0, 1),utils.randomWithRange(0, 1),utils.randomWithRange(0, 1));
            pieza_B pB = new pieza_B(i,utils.randomWithRange(0, 1),utils.randomWithRange(0, 1),utils.randomWithRange(0, 1),utils.randomWithRange(0, 1));
            
            pA.setTimeInLlegar(2 + (6 * pA.getRam1())); //y = 2 + 6ri
            pB.setTimeInLlegar(5 + (6 * pB.getRam1())); //Y = 5 + 6ri
            // A is tornos, B is fresas
            pA.setTimeInA(5 + (6 * pA.getRam2())); // z = 5 + 6ri
            pB.setTimeInA(2 + (2 * pB.getRam2())); // Z = 2 + 2ri
            pB.setTimeInB(6 + (6 * pB.getRam4())); // x = 6 + 6ri
                  
            
            horaLlegadaA = utils.sumarRestarSegundos(horaLlegadaA, utils.convertirMinutosSeconds(pA.getTimeInLlegar()));
            horaInicioA = horaLlegadaA;
            horaSalidaA = utils.sumarRestarSegundos(horaInicioA, utils.convertirMinutosSeconds(pA.getTimeInA()));
            tiempoEsperaA = 0;
            
            //Refrescamos status tornos
            changeStatusTorno(tornos, horaLlegadaA);
            //si es 0 no encontro disponibles dentro de los que ya hay
            idtornodisponible = buscaTornoDisponible(tornos);
            if(idtornodisponible == 0){
                idtorno++;
                torno nuevo = new torno(idtorno, 0);
                tornos.add(nuevo);
                idtornodisponible = idtorno;
            }
            
            tornos.get(idtornodisponible - 1).setHorafin(horaSalidaA);
            tornos.get(idtornodisponible - 1).setStatus(1);
            
            
            pA.setHoraLlegada(horaLlegadaA);
            pA.setHoraInicio(horaInicioA);
            pA.setHoraSalida(horaSalidaA);
            pA.setTiempoEspera(utils.convertirMilisecondstoMinuts(tiempoEsperaA));
            pA.setIsDefectuosa((pA.getRam3() <= 0.25));
            tiempoSistemaA=(int) (horaSalidaA.getTime() - horaLlegadaA.getTime());
            pA.setTimeInSystem(utils.convertirMilisecondstoMinuts(tiempoSistemaA));
            pA.setIdtorno(idtornodisponible);
            lista.add(pA);
            i++;
            
            //Simular durante un mes
            validaSimulacion = horaSalidaA.compareTo(fecha_final);
            
        }while(validaSimulacion != 1);
        
        return lista;
    }
    
    private int buscaTornoDisponible(ArrayList<torno> lista){
        int id = 0;
        for (int i = 0; i < lista.size(); i++) {
            torno t = lista.get(i);
            if(t.getStatus() == 0) {
                id = t.getId();
                break;
            }
        }
        return id;
    }
    
    private void changeStatusTorno(ArrayList<torno> lista, Date horaActual){
        for (int i = 0; i < lista.size(); i++) {
            if(isAvailabletorno(horaActual, lista.get(i).getHorafin()))
                lista.get(i).setStatus(0);
        }
    }
    
    private boolean isAvailabletorno(Date horallegada, Date horasalida){
        //Validando la hora de llegada anterior
        //-1 si la hora de llegada es antes de la hora de salida de i - 1 y se espera
        //0 si son iguales
        //1 entra directo y no se espera
        int compara = horallegada.compareTo(horasalida);
        return compara == 0 || compara == 1;
    }
    
    private int buscafresaDisponible(ArrayList<fresa> lista){
        int id = 0;
        for (int i = 0; i < lista.size(); i++) {
            fresa t = lista.get(i);
            if(t.getStatus() == 0) {
                id = t.getId();
                break;
            }
        }
        return id;
    }
     
    private void changeStatusFresa(ArrayList<fresa> lista, Date horaActual){
        for (int i = 0; i < lista.size(); i++) {
            if(isAvailablefresa(horaActual, lista.get(i).getHorafin()))
                lista.get(i).setStatus(0);
        }
    }
    
    private boolean isAvailablefresa(Date horallegada, Date horasalida){
        //Validando la hora de llegada anterior
        //-1 si la hora de llegada es antes de la hora de salida de i - 1 y se espera
        //0 si son iguales
        //1 entra directo y no se espera
        int compara = horallegada.compareTo(horasalida);
        return compara == 0 || compara == 1;
    }

}
