package net.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import net.model.fresa;
import net.model.pieza_A;
import net.model.pieza_A_Error;
import net.model.pieza_B;
import net.model.pieza_B_Error;
import net.model.relacionPiezaError;
import net.model.torno;

public class simulacion {

    private Date fecha_actual, fecha_final;
    private ArrayList<pieza_A> listA;
    private ArrayList<pieza_B> listB;
    private ArrayList<fresa> fresas;
    private ArrayList<torno> tornos;
    private ArrayList<relacionPiezaError> relacion;

    public simulacion(Date fechaAct, Date fechaFin) {
        this.fecha_actual = fechaAct;
        this.fecha_final = fechaFin;
        this.tornos = new ArrayList<>();
        torno t = new torno(1, 0);
        t.setHorafin(fechaAct);
        this.tornos.add(t);
        this.fresas = new ArrayList<>();
        fresa f = new fresa(1, 0);
        f.setHorafin(fechaAct);
        this.fresas.add(f);
    }

    public ArrayList<relacionPiezaError> creaRelacion() {
        ArrayList<relacionPiezaError> relacion = new ArrayList<>();
        //Agregamos todo lo de a
        for (pieza_A pa : listA) {
            relacionPiezaError rel = new relacionPiezaError(pa.getId(), -1, pa.getHoraInicio(), pa.getHoraSalida());
            relacion.add(rel);
            if (pa.getIsDefectuosa()) {
                for (pieza_A_Error eA : pa.getErrores()) {
                    relacionPiezaError rele = new relacionPiezaError(eA.getIdPieza(), eA.getId(), eA.getHoraInicio(), eA.getHoraSalida());
                    relacion.add(rele);
                }
            }
        }
        for (pieza_B pb : listB) {
            relacionPiezaError rel = new relacionPiezaError(pb.getId(), -1, pb.getHoraInicioTornos(), pb.getHoraSalidaTornos());
            relacion.add(rel);
            if (pb.getIsDefectuosa()) {
                for (pieza_B_Error eB : pb.getErrores()) {
                    relacionPiezaError rele = new relacionPiezaError(eB.getIdPieza(), eB.getId(), eB.getHoraInicioTornos(), eB.getHoraSalidaTornos());
                    relacion.add(rele);
                }
            }
        }

        return relacion;
    }

    public ArrayList<pieza_A> getSimulacionA(Date fecha_actual, Date fecha_final) {
        ArrayList<pieza_A> lista_piezasA = new ArrayList<>();
        ArrayList<pieza_A_Error> errores;

        int tiempoSistemaA, i = 0, validaSimulacion, ie;
        Date horaLlegadaA = fecha_actual, horaSalidaSystem;
        Boolean isDefectuosa;

        do {
            //Inicializando objetos
            errores = new ArrayList<>();
            pieza_A pA = new pieza_A(i + "a", utils.randomWithRange(0, 1), utils.randomWithRange(0, 1), utils.randomWithRange(0, 1));

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
            ie = 0;
            while (isDefectuosa) {
                pieza_A_Error pA_error = new pieza_A_Error(ie, pA.getId(), utils.randomWithRange(0, 1),
                        utils.randomWithRange(0, 1), pA.getHoraSalida(), pA.getHoraSalida());

                pA_error.setTimeInA(5 + (6 * pA_error.getRamTornos()));
                pA_error.setHoraSalida(utils.sumarRestarSegundos(
                        pA_error.getHoraInicio(), utils.convertirMinutosSeconds(pA_error.getTimeInA())
                ));
                pA_error.setTiempoEspera(0);
                pA_error.setIsDefectuosa((pA_error.getRamDefectuosa() <= 0.25));
                isDefectuosa = pA_error.getIsDefectuosa();

                errores.add(pA_error);
                ie++;
            }
            pA.setErrores(errores);
            if (errores != null && !errores.isEmpty()) {
                horaSalidaSystem = errores.get(errores.size() - 1).getHoraSalida();
                tiempoSistemaA = (int) (errores.get(errores.size() - 1).getHoraSalida().getTime() - pA.getHoraLlegada().getTime());
            } else {
                horaSalidaSystem = pA.getHoraSalida();
                tiempoSistemaA = (int) (pA.getHoraSalida().getTime() - pA.getHoraLlegada().getTime());
            }

            pA.setTimeInSystem(utils.convertirMilisecondstoMinuts(tiempoSistemaA));
            validaSimulacion = horaSalidaSystem.compareTo(fecha_final);

            lista_piezasA.add(pA);
            i++;
        } while (validaSimulacion != 1);

        return lista_piezasA;
    }

    public ArrayList<pieza_B> getSimulacionB(Date fecha_actual, Date fecha_final) {
        ArrayList<pieza_B> lista_piezasB = new ArrayList<>();
        ArrayList<pieza_B_Error> errores;

        int tiempoSistemaB, i = 0, validaSimulacion, ie;
        Date horaLlegadaB_Fresas = fecha_actual, horaSalidaSystem;
        Boolean isDefectuosa;

        do {
            //Inicializando objetos
            errores = new ArrayList<>();
            pieza_B pB = new pieza_B(i + "b", utils.randomWithRange(0, 1), utils.randomWithRange(0, 1), utils.randomWithRange(0, 1), utils.randomWithRange(0, 1));

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
            ie = 0;
            while (isDefectuosa) {
                pieza_B_Error pB_error = new pieza_B_Error(ie, pB.getId(), utils.randomWithRange(0, 1),
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
                ie++;
            }
            pB.setErrores(errores);
            if (errores != null && !errores.isEmpty()) {
                tiempoSistemaB = (int) (errores.get(errores.size() - 1).getHoraSalidaTornos().getTime() - pB.getHoraLlegada().getTime());
                horaSalidaSystem = errores.get(errores.size() - 1).getHoraSalidaTornos();
            } else {
                tiempoSistemaB = (int) (pB.getHoraSalidaTornos().getTime() - pB.getHoraLlegada().getTime());
                horaSalidaSystem = pB.getHoraSalidaTornos();
            }
            pB.setTimeInSystem(utils.convertirMilisecondstoMinuts(tiempoSistemaB));
            validaSimulacion = horaSalidaSystem.compareTo(fecha_final);

            lista_piezasB.add(pB);
            i++;

        } while (validaSimulacion != 1);

        return lista_piezasB;
    }

    public void getSimulacion() {
        this.listA = getSimulacionA(fecha_actual, fecha_final);
        this.listB = getSimulacionB(fecha_actual, fecha_final);
        this.relacion = creaRelacion();
        Collections.sort(relacion, new horaLlegadaPiezaComparator());
        asignarTornos();
    }

    private void asignarTornos() {
        int id, idfresadisponible, idfresa = 1, idtornodisponible, idtorno = 1;
        for (relacionPiezaError rel : this.relacion) {
            id = getIdPieza(rel.getIdpieza());
            if (rel.getIdpieza().contains("a")) {
                if (rel.getIderror() == -1) {
                    pieza_A pa = listA.get(id);
                    changeStatusTorno(tornos, pa.getHoraLlegada());
                    //si es 0 no encontro disponibles dentro de los que ya hay
                    idtornodisponible = buscaTornoDisponible(tornos);
                    if (idtornodisponible == 0) {
                        idtorno++;
                        torno nuevo = new torno(idtorno, 0);
                        tornos.add(nuevo);
                        idtornodisponible = idtorno;
                    }
                    //Actualizamos informacion tornos
                    tornos.get(idtornodisponible - 1).setHorafin(pa.getHoraSalida());
                    tornos.get(idtornodisponible - 1).setStatus(1);
                    pa.setIdtorno(idtornodisponible);
                } else {
                    pieza_A_Error eA = listA.get(id).getErrores().get(rel.getIderror());
                    changeStatusTorno(tornos, eA.getHoraLlegada());
                    //si es 0 no encontro disponibles dentro de los que ya hay
                    idtornodisponible = buscaTornoDisponible(tornos);
                    if (idtornodisponible == 0) {
                        idtorno++;
                        torno nuevo = new torno(idtorno, 0);
                        tornos.add(nuevo);
                        idtornodisponible = idtorno;
                    }
                    //Actualizamos informacion tornos
                    tornos.get(idtornodisponible - 1).setHorafin(eA.getHoraSalida());
                    tornos.get(idtornodisponible - 1).setStatus(1);
                    eA.setIdtorno(idtornodisponible);
                }
            } else {
                if (rel.getIderror() == -1) {
                    pieza_B pb = listB.get(id);
                    //Refrescamos status fresas
                    changeStatusFresa(fresas, pb.getHoraLlegada());
                    //si es 0 no encontro disponibles dentro de los que ya hay
                    idfresadisponible = buscafresaDisponible(fresas);
                    if (idfresadisponible == 0) {
                        idfresa++;
                        fresa nuevo = new fresa(idfresa, 0);
                        fresas.add(nuevo);
                        idfresadisponible = idfresa;
                    }
                    //Actualizamos informacion fresas
                    fresas.get(idfresadisponible - 1).setHorafin(pb.getHoraSalida());
                    fresas.get(idfresadisponible - 1).setStatus(1);
                    pb.setIdfresa(idfresadisponible);

                    changeStatusTorno(tornos, pb.getHoraLlegadaTornos());
                    //si es 0 no encontro disponibles dentro de los que ya hay
                    idtornodisponible = buscaTornoDisponible(tornos);
                    if (idtornodisponible == 0) {
                        idtorno++;
                        torno nuevo = new torno(idtorno, 0);
                        tornos.add(nuevo);
                        idtornodisponible = idtorno;
                    }
                    //Actualizamos informacion tornos
                    tornos.get(idtornodisponible - 1).setHorafin(pb.getHoraSalidaTornos());
                    tornos.get(idtornodisponible - 1).setStatus(1);
                    pb.setIdtorno(idtornodisponible);
                } else {
                    pieza_B_Error eB = listB.get(id).getErrores().get(rel.getIderror());
                    //Refrescamos status fresas
                    changeStatusFresa(fresas, eB.getHoraInicioFresas());
                    //si es 0 no encontro disponibles dentro de los que ya hay
                    idfresadisponible = buscafresaDisponible(fresas);
                    if (idfresadisponible == 0) {
                        idfresa++;
                        fresa nuevo = new fresa(idfresa, 0);
                        fresas.add(nuevo);
                        idfresadisponible = idfresa;
                    }
                    //Actualizamos informacion fresas
                    fresas.get(idfresadisponible - 1).setHorafin(eB.getHoraSalidaFresas());
                    fresas.get(idfresadisponible - 1).setStatus(1);
                    eB.setIdfresa(idfresadisponible);

                    changeStatusTorno(tornos, eB.getHoraInicioTornos());
                    //si es 0 no encontro disponibles dentro de los que ya hay
                    idtornodisponible = buscaTornoDisponible(tornos);
                    if (idtornodisponible == 0) {
                        idtorno++;
                        torno nuevo = new torno(idtorno, 0);
                        tornos.add(nuevo);
                        idtornodisponible = idtorno;
                    }
                    //Actualizamos informacion tornos
                    tornos.get(idtornodisponible - 1).setHorafin(eB.getHoraSalidaTornos());
                    tornos.get(idtornodisponible - 1).setStatus(1);
                    eB.setIdtorno(idtornodisponible);
                }
            }
        }
    }

    private int getIdPieza(String id) {
        int index = -1;
        if (id.contains("a")) {
            for (pieza_A pa : listA) {
                if(pa.getId().equals(id)){
                    index = listA.indexOf(pa);
                    break;
                }
            }
        } else {
            for (pieza_B pb : listB) {
                if(pb.getId().equals(id)){
                    index = listB.indexOf(pb);
                    break;
                }
            }
        }
        return index;
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

    public ArrayList<fresa> getFresas() {
        return fresas;
    }

    public void setFresas(ArrayList<fresa> fresas) {
        this.fresas = fresas;
    }

    public ArrayList<torno> getTornos() {
        return tornos;
    }

    public void setTornos(ArrayList<torno> tornos) {
        this.tornos = tornos;
    }

    public ArrayList<relacionPiezaError> getRelacion() {
        return relacion;
    }

    public void setRelacion(ArrayList<relacionPiezaError> relacion) {
        this.relacion = relacion;
    }

}
