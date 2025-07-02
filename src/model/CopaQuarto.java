package model;

public class CopaQuarto {

    private int id;
    private float quantidade;
    private String datahorapedido;
    private String obs;
    private char status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public String getDatahorapedido() {
        return datahorapedido;
    }

    public void setDatahorapedido(String datahorapedido) {
        this.datahorapedido = datahorapedido;
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
