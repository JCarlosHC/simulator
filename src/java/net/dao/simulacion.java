package net.dao;

import com.json.JSONArray;
import com.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import net.model.fresa;
import net.model.pieza_A;
import net.model.pieza_A_Error;
import net.model.pieza_B;
import net.model.pieza_B_Error;
import net.model.torno;

public class simulacion {

    private Date fecha_actual, fecha_final;
    private ArrayList<pieza_A> listA;
    private ArrayList<pieza_B> listB;

    public simulacion(Date fechaAct, Date fechaFin) {
        this.fecha_actual = fechaAct;
        this.fecha_final = fechaFin;
    }

    //Llenamos la estructura de piezas A
    public ArrayList<pieza_A> getSimulacionA(Date fecha_actual, Date fecha_final) {
        ArrayList<pieza_A> lista_piezasA = new ArrayList<>();
        ArrayList<pieza_A_Error> errores;

        int tiempoSistemaA, i = 0, validaSimulacion;
        Date horaLlegadaA = fecha_actual, horaSalidaSystem;
        Boolean isDefectuosa;

        do {
            //Inicializando objetos
            errores = new ArrayList<>();
            pieza_A pA = new pieza_A(i, utils.randomWithRange(0, 1), utils.randomWithRange(0, 1), utils.randomWithRange(0, 1));

            pA.setTimeInLlegar(2 + (6 * pA.getRam1())); //y = 2 + 6ri
            pA.setTimeInA(5 + (6 * pA.getRam2())); // z = 5 + 6ri

            horaLlegadaA = utils.sumarRestarSegundos(horaLlegadaA, utils.convertirMinutosSeconds(pA.getTimeInLlegar()));

            //Piezas a
            pA.setHoraLlegada(horaLlegadaA);
            pA.setHoraInicio(pA.getHoraLlegada());
            pA.setHoraSalida(utils.sumarRestarSegundos(
                    pA.getHoraInicio(), utils.convertirMinutosSeconds(pA.getTimeInA())
            ));
            //Es 0 porque en cuanto no encuetra torno disponible crea uno.
            //Este puede actualizarse cuando se haga la simulacion
            pA.setTiempoEspera(0);
            pA.setIsDefectuosa((pA.getRam3() <= 0.25));
            isDefectuosa = pA.getIsDefectuosa();

            while (isDefectuosa) {
                pieza_A_Error pA_error = new pieza_A_Error(pA.getId(), utils.randomWithRange(0, 1),
                        utils.randomWithRange(0, 1), pA.getHoraSalida(), pA.getHoraSalida());

                pA_error.setTimeInA(5 + (6 * pA_error.getRamTornos()));
                pA_error.setHoraSalida(utils.sumarRestarSegundos(
                        pA.getHoraInicio(), utils.convertirMinutosSeconds(pA_error.getTimeInA())
                ));
                pA_error.setTiempoEspera(0);
                pA_error.setIsDefectuosa((pA_error.getRamDefectuosa() <= 0.25));
                isDefectuosa = pA_error.getIsDefectuosa();

                errores.add(pA_error);

            }
            pA.setErrores(errores);
            if (errores != null && !errores.isEmpty()) {
                horaSalidaSystem = errores.get(errores.size() - 1).getHoraSalida();
                tiempoSistemaA = (int) (errores.get(errores.size() - 1).getHoraSalida().getTime() - pA.getHoraLlegada().getTime());
            } else {
                horaSalidaSystem = pA.getHoraSalida();
                tiempoSistemaA = (int) (pA.getHoraSalida().getTime() - pA.getHoraLlegada().getTime());
            }

            pA.setTimeInSystem(tiempoSistemaA);
            validaSimulacion = horaSalidaSystem.compareTo(fecha_final);

            lista_piezasA.add(pA);
            i++;
        } while (validaSimulacion != 1);

        return lista_piezasA;
    }

    public ArrayList<pieza_B> getSimulacionB(Date fecha_actual, Date fecha_final) {
        ArrayList<pieza_B> lista_piezasB = new ArrayList<>();
        ArrayList<pieza_B_Error> errores;

        int tiempoSistemaB, i = 0, validaSimulacion;
        Date horaLlegadaB_Fresas = fecha_actual, horaSalidaSystem;
        Boolean isDefectuosa;

        do {
            //Inicializando objetos
            errores = new ArrayList<>();
            pieza_B pB = new pieza_B(i, utils.randomWithRange(0, 1), utils.randomWithRange(0, 1), utils.randomWithRange(0, 1), utils.randomWithRange(0, 1));

            pB.setTimeInLlegar(5 + (6 * pB.getRam1())); //Y = 5 + 6ri
            // A is tornos, B is fresas
            pB.setTimeInA(2 + (2 * pB.getRam2())); // Z = 2 + 2ri
            pB.setTimeInB(6 + (6 * pB.getRam3())); // x = 6 + 6ri

            horaLlegadaB_Fresas = utils.sumarRestarSegundos(horaLlegadaB_Fresas, utils.convertirMinutosSeconds(pB.getTimeInLlegar()));

            //Piezas b
            pB.setHoraLlegada(horaLlegadaB_Fresas);
            pB.setHoraInicio(pB.getHoraLlegada());
            pB.setHoraSalida(utils.sumarRestarSegundos(pB.getHoraInicio(), utils.convertirMinutosSeconds(pB.getTimeInB())));
            //Es 0 porque en cuanto no encuetra fresa disponible crea uno.
            //Este puede actualizarse cuando se haga la simulacion
            pB.setTiempoEspera(0);

            pB.setHoraLlegadaTornos(pB.getHoraSalida());
            pB.setHoraInicioTornos(pB.getHoraSalida());
            pB.setHoraSalidaTornos(utils.sumarRestarSegundos(pB.getHoraInicioTornos(), utils.convertirMinutosSeconds(pB.getTimeInA())));
            //Es 0 porque en cuanto no encuetra torno disponible crea uno.
            //Este puede actualizarse cuando se haga la simulacion
            pB.setTiempoEsperaTornos(0);
            pB.setIsDefectuosa((pB.getRam4() <= 0.05));
            isDefectuosa = pB.getIsDefectuosa();

            while (isDefectuosa) {
                pieza_B_Error pB_error = new pieza_B_Error(pB.getId(), utils.randomWithRange(0, 1),
                        utils.randomWithRange(0, 1), utils.randomWithRange(0, 1), pB.getHoraSalidaTornos(), pB.getHoraSalidaTornos());

                pB_error.setTimeInTornos(2 + (2 * pB.getRam2())); // Z = 2 + 2ri
                pB_error.setTimeInFresas(6 + (6 * pB.getRam3())); // x = 6 + 6ri

                pB_error.setHoraSalidaFresas(utils.sumarRestarSegundos(pB_error.getHoraInicioFresas(),
                        utils.convertirMinutosSeconds(pB_error.getTimeInFresas())
                ));
                //Es 0 porque en cuanto no encuetra fresa disponible crea uno.
                //Este puede actualizarse cuando se haga la simulacion
                pB_error.setTiempoEsperaFresas(0);
                pB_error.setHoraInicioTornos(pB_error.getHoraSalidaFresas());
                pB_error.setHoraSalidaTornos(utils.sumarRestarSegundos(pB_error.getHoraInicioTornos(),
                        utils.convertirMinutosSeconds(pB_error.getTimeInTornos())));
                //Es 0 porque en cuanto no encuetra torno disponible crea uno.
                //Este puede actualizarse cuando se haga la simulacion
                pB_error.setTiempoEsperaTornos(0);
                pB_error.setIsDefectuosa(pB_error.getRamDefectuosa() <= 0.05);
                isDefectuosa = pB_error.getIsDefectuosa();

                errores.add(pB_error);
            }
            pB.setErrores(errores);
            if (errores != null && !errores.isEmpty()) {
                tiempoSistemaB = (int) (errores.get(errores.size() - 1).getHoraSalidaTornos().getTime() - pB.getHoraLlegada().getTime());
                horaSalidaSystem = errores.get(errores.size() - 1).getHoraSalidaTornos();
            } else {
                tiempoSistemaB = (int) (pB.getHoraSalidaTornos().getTime() - pB.getHoraLlegada().getTime());
                horaSalidaSystem = pB.getHoraSalidaTornos();
            }
            pB.setTimeInSystem(tiempoSistemaB);
            validaSimulacion = horaSalidaSystem.compareTo(fecha_final);

            lista_piezasB.add(pB);
            i++;

        } while (validaSimulacion != 1);

        return lista_piezasB;
    }

    public JSONObject getSimulacion() {
        JSONObject objJson = new JSONObject();
        JSONArray objPiezasA, objPiezasB, objtornos, objfresas;

        this.listA = getSimulacionA(fecha_actual, fecha_final);
        this.listB = getSimulacionB(fecha_actual, fecha_final);
        ArrayList<torno> tornos = new ArrayList<>();
        ArrayList<fresa> fresas = new ArrayList<>();

        int idtorno = 1, idtornodisponible, idfresa = 1, idfresadisponible, ultimo = 0;
        Boolean continua = true;
        char who;
        Date horasalida;
        
        //Inicializo mis tornos y fresas.
        torno t = new torno(idtorno, 0);
        t.setHorafin(fecha_actual);
        tornos.add(t);
        fresa f = new fresa(idfresa, 0);
        f.setHorafin(fecha_actual);
        fresas.add(f);

        for (pieza_B piezaB : this.listB) {
            //Refrescamos status fresas
            changeStatusFresa(fresas, piezaB.getHoraLlegada());
            //si es 0 no encontro disponibles dentro de los que ya hay
            idfresadisponible = buscafresaDisponible(fresas);
            if (idfresadisponible == 0) {
                idfresa++;
                fresa nuevo = new fresa(idfresa, 0);
                fresas.add(nuevo);
                idfresadisponible = idfresa;
            }
            //Actualizamos informacion fresas
            fresas.get(idfresadisponible - 1).setHorafin(piezaB.getHoraSalida());
            fresas.get(idfresadisponible - 1).setStatus(1);
            piezaB.setIdfresa(idfresadisponible);
            
            continua = true;
            while(continua){
                pieza_A piezaA = listA.get(ultimo);
                //Se compara las horas de la pieza a y b para ver quien entra primero
                if(piezaA.getIdtorno() == 0 && piezaB.getIdtorno() == 0){
                    if (compareDate(piezaB.getHoraLlegadaTornos(), piezaA.getHoraLlegada())) {
                        changeStatusTorno(tornos, piezaA.getHoraLlegada());
                        horasalida = piezaA.getHoraSalida();
                        who = 'a';
                    } else {
                        changeStatusTorno(tornos, piezaB.getHoraLlegadaTornos());
                        horasalida = piezaB.getHoraSalidaTornos();
                        who = 'b';
                    }
                } else if(piezaA.getIdtorno() == 0){
                    changeStatusTorno(tornos, piezaA.getHoraLlegada());
                    horasalida = piezaA.getHoraSalida();
                    who = 'a';
                } else{
                    changeStatusTorno(tornos, piezaB.getHoraLlegadaTornos());
                    horasalida = piezaB.getHoraSalidaTornos();
                    who = 'b';
                }
                //si es 0 no encontro disponibles dentro de los que ya hay
                idtornodisponible = buscaTornoDisponible(tornos);
                if (idtornodisponible == 0) {
                    idtorno++;
                    torno nuevo = new torno(idtorno, 0);
                    tornos.add(nuevo);
                    idtornodisponible = idtorno;
                }
                 //Actualizamos informacion tornos
                tornos.get(idtornodisponible - 1).setHorafin(horasalida);
                tornos.get(idtornodisponible - 1).setStatus(1);
                
                if(who == 'a') {
                    ultimo++;
                    piezaA.setIdtorno(idtornodisponible);
                } else {
                    piezaB.setIdtorno(idtornodisponible);
                }
                
                if(piezaA.getIdtorno() > 0 && piezaB.getIdtorno() > 0) continua = false;
                
            }
        }

        objPiezasA = new JSONArray(this.listA);
        objPiezasB = new JSONArray(this.listB);
        objtornos = new JSONArray(tornos);
        objfresas = new JSONArray(fresas);
        objJson.put("tornos", objtornos);
        objJson.put("fresas", objfresas);
        objJson.put("listaA", objPiezasA);
        objJson.put("listaB", objPiezasB);

        return objJson;
    }

    private int buscaTornoDisponible(ArrayList<torno> lista) {
        int id = 0;
        for (int i = 0; i < lista.size(); i++) {
            torno t = lista.get(i);
            if (t.getStatus() == 0) {
                id = t.getId();
                break;
            }
        }
        return id;
    }

    private void changeStatusTorno(ArrayList<torno> lista, Date horaActual) {
        for (int i = 0; i < lista.size(); i++) {
            if (isAvailabletorno(horaActual, lista.get(i).getHorafin())) {
                lista.get(i).setStatus(0);
            }
        }
    }

    private boolean isAvailabletorno(Date horallegada, Date horasalida) {
        //Validando la hora de llegada anterior
        //-1 si la hora de llegada es antes de la hora de salida de i - 1 y se espera
        //0 si son iguales
        //1 entra directo y no se espera
        int compara = horallegada.compareTo(horasalida);
        return compara == 0 || compara == 1;
    }

    private boolean compareDate(Date horallegada, Date horasalida) {
        //Validando la hora de llegada anterior
        //-1 si la hora de llegada es antes de la hora de salida de i - 1 y se espera
        //0 si son iguales
        //1 entra directo y no se espera
        int compara = horallegada.compareTo(horasalida);
        return compara == 0 || compara == 1;
    }

    private int buscafresaDisponible(ArrayList<fresa> lista) {
        int id = 0;
        for (int i = 0; i < lista.size(); i++) {
            fresa t = lista.get(i);
            if (t.getStatus() == 0) {
                id = t.getId();
                break;
            }
        }
        return id;
    }

    private void changeStatusFresa(ArrayList<fresa> lista, Date horaActual) {
        for (int i = 0; i < lista.size(); i++) {
            if (isAvailablefresa(horaActual, lista.get(i).getHorafin())) {
                lista.get(i).setStatus(0);
            }
        }
    }

    private boolean isAvailablefresa(Date horallegada, Date horasalida) {
        //Validando la hora de llegada anterior
        //-1 si la hora de llegada es antes de la hora de salida de i - 1 y se espera
        //0 si son iguales
        //1 entra directo y no se espera
        int compara = horallegada.compareTo(horasalida);
        return compara == 0 || compara == 1;
    }

    public Date getFecha_actual() {
        return fecha_actual;
    }

    public void setFecha_actual(Date fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public ArrayList<pieza_A> getListA() {
        return listA;
    }

    public void setListA(ArrayList<pieza_A> listA) {
        this.listA = listA;
    }

    public ArrayList<pieza_B> getListB() {
        return listB;
    }

    public void setListB(ArrayList<pieza_B> listB) {
        this.listB = listB;
    }

}
