package model;

public class CheckQuarto {

    private int id;
    private String datahorainicio;
    private String datahorafim;
    private String obs;
    private char status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatahorainicio() {
        return datahorainicio;
    }

    public void setDatahorainicio(String datahorainicio) {
        this.datahorainicio = datahorainicio;
    }

    public String getDatahorafim() {
        return datahorafim;
    }

    public void setDatahorafim(String datahorafim) {
        this.datahorafim = datahorafim;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
