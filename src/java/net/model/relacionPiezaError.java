package net.model;

import java.util.Date;

public class relacionPiezaError {
    private String idpieza; 
    private int iderror;
    private Date horainicio, horafin;

    public relacionPiezaError(String idpieza, int iderror, Date horainicio, Date horafin) {
        this.idpieza = idpieza;
        this.iderror = iderror;
        this.horainicio = horainicio;
        this.horafin = horafin;
    }

    public String getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(String idpieza) {
        this.idpieza = idpieza;
    }

    public int getIderror() {
        return iderror;
    }

    public void setIderror(int iderror) {
        this.iderror = iderror;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }
    
    @Override
    public String toString (){
        String mensaje = "ID PIEZA:\t"+ idpieza + "\tIDERROR:\t" + iderror + "\tHora inicio:\t" + horainicio +
                "\tHora fin:\t" + horafin;
        return mensaje;
    }
}
