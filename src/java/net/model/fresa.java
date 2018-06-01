package net.model;

import java.util.Date;

public class fresa {
    private int id;
    private int status;
    private Date horafin;

    public fresa(int id, int status) {
        this.id = id;
        this.status = status;
    }

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
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
