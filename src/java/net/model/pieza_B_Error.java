package net.model;

import java.util.Date;

public class pieza_B_Error {
    private int idPieza;
    private double ramTornos, ramFresas, ramDefectuosa, timeInTornos, timeInFresas, tiempoEsperaTornos, tiempoEsperaFresas;
    private Date horaLlegada, horaInicioTornos, horaSalidaTornos, horaInicioFresas, horaSalidaFresas;
    private Boolean isDefectuosa;
    private int idtorno;  
    private int idfresa;

    public pieza_B_Error(int idPieza, double ramDefectuosa, Date horaLlegada, Date horaInicioFresas) {
        this.idPieza = idPieza;
        this.ramDefectuosa = ramDefectuosa;
        this.horaLlegada = horaLlegada;
        this.horaInicioFresas = horaInicioFresas;
    }

    public int getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(int idPieza) {
        this.idPieza = idPieza;
    }

    public double getRamTornos() {
        return ramTornos;
    }

    public void setRamTornos(double ramTornos) {
        this.ramTornos = ramTornos;
    }

    public double getRamFresas() {
        return ramFresas;
    }

    public void setRamFresas(double ramFresas) {
        this.ramFresas = ramFresas;
    }

    public double getRamDefectuosa() {
        return ramDefectuosa;
    }

    public void setRamDefectuosa(double ramDefectuosa) {
        this.ramDefectuosa = ramDefectuosa;
    }

    public double getTimeInTornos() {
        return timeInTornos;
    }

    public void setTimeInTornos(double timeInTornos) {
        this.timeInTornos = timeInTornos;
    }

    public double getTimeInFresas() {
        return timeInFresas;
    }

    public void setTimeInFresas(double timeInFresas) {
        this.timeInFresas = timeInFresas;
    }

    public double getTiempoEsperaTornos() {
        return tiempoEsperaTornos;
    }

    public void setTiempoEsperaTornos(double tiempoEsperaTornos) {
        this.tiempoEsperaTornos = tiempoEsperaTornos;
    }

    public double getTiempoEsperaFresas() {
        return tiempoEsperaFresas;
    }

    public void setTiempoEsperaFresas(double tiempoEsperaFresas) {
        this.tiempoEsperaFresas = tiempoEsperaFresas;
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
    
    
}
