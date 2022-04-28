package rrhh.controlador;

public class clscargo {
    private int idcargo;
    private String nombrecargo;
    private String eestatusecargo;

    public clscargo() {
    }

    public clscargo(int idcargo) {
        this.idcargo = idcargo;
    }
    
    public clscargo(String snombre, String sestado) {
        this.nombrecargo = snombre;
        this.eestatusecargo = sestado;
    }
    
    public clscargo(int idcargo, String snombre, String sestado) {
        this.idcargo = idcargo;
        this.nombrecargo = snombre;
        this.eestatusecargo = sestado;
    }
    

    public int getidcargo() {
        return idcargo;
    }

    public void setidcargo(int idcargo) {
        this.idcargo = idcargo;
    }

    public String getnombrecargo() {
        return nombrecargo;
    }

    public void setnombrecargo(String snombre) {
        this.nombrecargo = snombre;
    }

    public String getestatuscargo() {
        return eestatusecargo;
    }

    public void setestatuscargo(String sestado) {
        this.eestatusecargo = sestado;
    }
    @Override
    public String toString() {
        return "tbl_departamento{" + "idcargo=" + idcargo + ", nombrecargo="  +  nombrecargo +", estatus=" + eestatusecargo +  "}";
    }
    
    
}
