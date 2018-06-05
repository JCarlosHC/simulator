package net.model;

import java.util.Date;
import net.dao.utils;

public class pieza_B_Error {
    private int id;
    private String idPieza;
    private double ramTornos, ramFresas, ramDefectuosa, timeInTornos, timeInFresas, tiempoEsperaTornos, tiempoEsperaFresas;
    private Date horaLlegada, horaInicioTornos, horaSalidaTornos, horaInicioFresas, horaSalidaFresas;
    private Boolean isDefectuosa;
    private int idtorno;  
    private int idfresa;

    public pieza_B_Error(int id, String idPieza, double ramTornos, double ramFresas, double ramDefectuosa, Date horaLlegada, Date horaInicioFresas) {
        this.id = id;
        this.idPieza = idPieza;
        this.ramTornos = utils.formatearDecimales(ramTornos,2);
        this.ramFresas = utils.formatearDecimales(ramFresas,2);
        this.ramDefectuosa = utils.formatearDecimales(ramDefectuosa,2);
        this.horaLlegada = horaLlegada;
        this.horaInicioFresas = horaInicioFresas;
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

    public double getRamFresas() {
        return ramFresas;
    }

    public void setRamFresas(double ramFresas) {
        this.ramFresas = utils.formatearDecimales(ramFresas,2);
    }

    public double getRamDefectuosa() {
        return ramDefectuosa;
    }

    public void setRamDefectuosa(double ramDefectuosa) {
        this.ramDefectuosa = utils.formatearDecimales(ramDefectuosa,2);
    }

    public double getTimeInTornos() {
        return timeInTornos;
    }

    public void setTimeInTornos(double timeInTornos) {
        this.timeInTornos = utils.formatearDecimales(timeInTornos,2);
    }

    public double getTimeInFresas() {
        return timeInFresas;
    }

    public void setTimeInFresas(double timeInFresas) {
        this.timeInFresas = utils.formatearDecimales(timeInFresas,2);
    }

    public double getTiempoEsperaTornos() {
        return tiempoEsperaTornos;
    }

    public void setTiempoEsperaTornos(double tiempoEsperaTornos) {
        this.tiempoEsperaTornos = utils.formatearDecimales(tiempoEsperaTornos,2);
    }

    public double getTiempoEsperaFresas() {
        return tiempoEsperaFresas;
    }

    public void setTiempoEsperaFresas(double tiempoEsperaFresas) {
        this.tiempoEsperaFresas = utils.formatearDecimales(tiempoEsperaFresas,2);
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
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

    public Date getHoraInicioFresas() {
        return horaInicioFresas;
    }

    public void setHoraInicioFresas(Date horaInicioFresas) {
        this.horaInicioFresas = horaInicioFresas;
    }

    public Date getHoraSalidaFresas() {
        return horaSalidaFresas;
    }

    public void setHoraSalidaFresas(Date horaSalidaFresas) {
        this.horaSalidaFresas = horaSalidaFresas;
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

    public int getIdfresa() {
        return idfresa;
    }

    public void setIdfresa(int idfresa) {
        this.idfresa = idfresa;
    }

    @Override
    public String toString() {
        return "pieza_B_Error{" + "id=" + id + ", idPieza=" + idPieza + ", ramTornos=" + ramTornos + ", ramFresas=" + ramFresas + ", ramDefectuosa=" + ramDefectuosa + ", timeInTornos=" + timeInTornos + ", timeInFresas=" + timeInFresas + ", tiempoEsperaTornos=" + tiempoEsperaTornos + ", tiempoEsperaFresas=" + tiempoEsperaFresas + ", horaLlegada=" + horaLlegada + ", horaInicioTornos=" + horaInicioTornos + ", horaSalidaTornos=" + horaSalidaTornos + ", horaInicioFresas=" + horaInicioFresas + ", horaSalidaFresas=" + horaSalidaFresas + ", isDefectuosa=" + isDefectuosa + ", idtorno=" + idtorno + ", idfresa=" + idfresa + '}';
    }
    
    
}
