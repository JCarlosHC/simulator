package net.model;

import java.util.Date;
import net.dao.utils;

public class pieza_A_Error {
    private int id;
    private String idPieza;
    private double ramTornos, ramDefectuosa, timeInA, tiempoEspera;
    private Date horaLlegada, horaInicio, horaSalida;
    private Boolean isDefectuosa;
    private int idtorno;  

    public pieza_A_Error(int id, String idPieza, double ramTornos, double ramDefectuosa, Date horaLlegada, Date horaInicio) {
        this.id = id;
        this.idPieza = idPieza;
        this.ramTornos = utils.formatearDecimales(ramTornos,2);
        this.ramDefectuosa = utils.formatearDecimales(ramDefectuosa,2);
        this.horaLlegada = horaLlegada;
        this.horaInicio = horaInicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(String idPieza) {
        this.idPieza = idPieza;
    }

    public double getRamTornos() {
        return ramTornos;
    }

    public void setRamTornos(double ramTornos) {
        this.ramTornos = utils.formatearDecimales(ramTornos,2);
    }

    public double getRamDefectuosa() {
        return ramDefectuosa;
    }

    public void setRamDefectuosa(double ramDefectuosa) {
        this.ramDefectuosa = utils.formatearDecimales(ramDefectuosa,2);
    }

    public double getTimeInA() {
        return timeInA;
    }

    public void setTimeInA(double timeInA) {
        this.timeInA = utils.formatearDecimales(timeInA,2);
    }

    public double getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(double tiempoEspera) {
        this.tiempoEspera = utils.formatearDecimales(tiempoEspera,2);
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

    public int getIdtorno() {
        return idtorno;
    }

    public void setIdtorno(int idtorno) {
        this.idtorno = idtorno;
    }

    @Override
    public String toString() {
        return "pieza_A_Error{" + "id=" + id + ", idPieza=" + idPieza + ", ramTornos=" + ramTornos + ", ramDefectuosa=" + ramDefectuosa + ", timeInA=" + timeInA + ", tiempoEspera=" + tiempoEspera + ", horaLlegada=" + horaLlegada + ", horaInicio=" + horaInicio + ", horaSalida=" + horaSalida + ", isDefectuosa=" + isDefectuosa + ", idtorno=" + idtorno + '}';
    }
    
    
}
