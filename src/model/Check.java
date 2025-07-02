package model;

public class Check {

    private int id;
    private String datahoracadastro;
    private String datahoraentrada;
    private String datahorasaida;
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

    public String getDatahoraentrada() {
        return datahoraentrada;
    }

    public void setDatahoraentrada(String datahoraentrada) {
        this.datahoraentrada = datahoraentrada;
    }

    public String getDatahorasaida() {
        return datahorasaida;
    }

    public void setDatahorasaida(String datahorasaida) {
        this.datahorasaida = datahorasaida;
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
