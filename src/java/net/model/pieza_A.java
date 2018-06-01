package net.model;

import java.util.Date;
import net.dao.utils;

public class pieza_A {
    private int id;
    private double ram1, ram2, ram3, timeInLlegar, timeInA, tiempoEspera, timeInSystem;
    private Date horaLlegada, horaInicio, horaSalida;
    private Boolean isDefectuosa;
    private int idtorno;

    public pieza_A(){}

    public pieza_A(int id, double ram1, double ram2, double ram3) {
        this.id = id;
        this.ram1 = utils.formatearDecimales(ram1,2);
        this.ram2 = utils.formatearDecimales(ram2,2);
        this.ram3 = utils.formatearDecimales(ram3,2);
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

    public double getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(double tiempoEspera) {
        this.tiempoEspera = utils.formatearDecimales(tiempoEspera, 2);
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

    public Boolean getIsDefectuosa() {
        return isDefectuosa;
    }

    public void setIsDefectuosa(Boolean isDefectuosa) {
        this.isDefectuosa = isDefectuosa;
    }

  
}
