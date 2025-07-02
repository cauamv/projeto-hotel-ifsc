package model;

public class Caixa {

    private int id;
    private float valordeabertura;
    private float valordefechamento;
    private String datahoraabertura;
    private String datahorafechamento;
    private String obs;
    private char status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValordeabertura() {
        return valordeabertura;
    }

    public void setValordeabertura(float valordeabertura) {
        this.valordeabertura = valordeabertura;
    }

    public float getValordefechamento() {
        return valordefechamento;
    }

    public void setValordefechamento(float valordefechamento) {
        this.valordefechamento = valordefechamento;
    }

    public String getDatahoraabertura() {
        return datahoraabertura;
    }

    public void setDatahoraabertura(String datahoraabertura) {
        this.datahoraabertura = datahoraabertura;
    }

    public String getDatahorafechamento() {
        return datahorafechamento;
    }

    public void setDatahorafechamento(String datahorafechamento) {
        this.datahorafechamento = datahorafechamento;
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
