package model;

public class OrdemServico {

    private int id;
    private String datahoracadastro;
    private String datahoraprevinicio;
    private String datahoraprevsetermino;
    private String obs;
    private char status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatahoracadastro() {
        return datahoracadastro;
    }

    public void setDatahoracadastro(String datahoracadastro) {
        this.datahoracadastro = datahoracadastro;
    }

    public String getDatahoraprevinicio() {
        return datahoraprevinicio;
    }

    public void setDatahoraprevinicio(String datahoraprevinicio) {
        this.datahoraprevinicio = datahoraprevinicio;
    }

    public String getDatahoraprevsetermino() {
        return datahoraprevsetermino;
    }

    public void setDatahoraprevsetermino(String datahoraprevsetermino) {
        this.datahoraprevsetermino = datahoraprevsetermino;
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
