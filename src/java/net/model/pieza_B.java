package net.model;

import java.util.Date;
import java.util.List;
import net.dao.utils;

public class pieza_B {
    private int id;
    private double ram1, ram2, ram3, ram4, timeInLlegar, timeInA, tiempoEspera, tiempoEsperaTornos, timeInSystem, timeInB; //A es tornos, B Fresas
    private Date horaLlegada, horaInicio, horaSalida;
    private Date horaLlegadaTornos, horaInicioTornos, horaSalidaTornos;
    private Boolean isDefectuosa; //5% y 95%
    private int idtorno, idfresa;
    private List<pieza_B_Error> errores;
    
    public pieza_B(){}

    public pieza_B(int id, double ram1, double ram2, double ram3, double ram4) {
        this.id = id;
        this.ram1 = utils.formatearDecimales(ram1,2);
        this.ram2 = utils.formatearDecimales(ram2,2);
        this.ram3 = utils.formatearDecimales(ram3,2);
        this.ram4 = utils.formatearDecimales(ram4,2);
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdtorno() {
        return idtorno;
    }

    public void setIdtorno(int idtorno) {
        this.idtorno = idtorno;
    }

    public int getIdfresa() {
        return idfresa;
    }

    public void setIdfresa(int idfresa) {
        this.idfresa = idfresa;
    }
    
    
    public double getRam1() {
        return ram1;
    }

    public void setRam1(double ram1) {
        this.ram1 = utils.formatearDecimales(ram1, 2);
    }

    public double getRam2() {
        return ram2;
    }

    public void setRam2(double ram2) {
        this.ram2 = utils.formatearDecimales(ram2, 2);
    }

    public double getRam3() {
        return ram3;
    }

    public void setRam3(double ram3) {
        this.ram3 = utils.formatearDecimales(ram3, 2);
    }

    public double getRam4() {
        return ram4;
    }

    public void setRam4(double ram4) {
        this.ram4 = utils.formatearDecimales(ram4,2);
    }

    public double getTimeInLlegar() {
        return timeInLlegar;
    }

    public void setTimeInLlegar(double timeInLlegar) {
        this.timeInLlegar = utils.formatearDecimales(timeInLlegar, 2);
    }

    public double getTimeInA() {
        return timeInA;
    }

    public void setTimeInA(double timeInA) {
        this.timeInA = utils.formatearDecimales(timeInA, 2);
    }

    public double getTimeInB() {
        return timeInB;
    }

    public void setTimeInB(double timeInB) {
        this.timeInB = utils.formatearDecimales(timeInB,2);
    }
    
    public double getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(double tiempoEspera) {
        this.tiempoEspera = utils.formatearDecimales(tiempoEspera, 2);
    }

    public double getTiempoEsperaTornos() {
        return tiempoEsperaTornos;
    }

    public void setTiempoEsperaTornos(double tiempoEsperaTornos) {
        this.tiempoEsperaTornos = utils.formatearDecimales(tiempoEsperaTornos,2);
    }

    public double getTimeInSystem() {
        return timeInSystem;
    }

    public void setTimeInSystem(double timeInSystem) {
        this.timeInSystem = utils.formatearDecimales(timeInSystem, 2);
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }
    
    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getHoraLlegadaTornos() {
        return horaLlegadaTornos;
    }

    public void setHoraLlegadaTornos(Date horaLlegadaTornos) {
        this.horaLlegadaTornos = horaLlegadaTornos;
    }

    public Date getHoraInicioTornos() {
        return horaInicioTornos;
    }

    public void setHoraInicioTornos(Date horaInicioTornos) {
        this.horaInicioTornos = horaInicioTornos;
    }

    public Date getHoraSalidaTornos() {
        return horaSalidaTornos;
    }

    public void setHoraSalidaTornos(Date horaSalidaTornos) {
        this.horaSalidaTornos = horaSalidaTornos;
    }

    public Boolean getIsDefectuosa() {
        return isDefectuosa;
    }

    public void setIsDefectuosa(Boolean isDefectuosa) {
        this.isDefectuosa = isDefectuosa;
    }

    public List<pieza_B_Error> getErrores() {
        return errores;
    }

    public void setErrores(List<pieza_B_Error> errores) {
        this.errores = errores;
    }

  
}
