package net.model;

import java.util.Date;

public class torno {
    private int id;
    private int status; //0 libre, 1 ocupado
    private Date horafin;

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }
    
    public torno(int id, int status) {
        this.id = id;
        this.status = status;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
