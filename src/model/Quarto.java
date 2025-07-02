package model;

public class Quarto {

    private int id;
    private String descricao;
    private int capacidadehospedes;
    private float metragem;
    private String identificacao;
    private int andar;
    private boolean flagmanutencao;
    private char status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCapacidadehospedes() {
        return capacidadehospedes;
    }

    public void setCapacidadehospedes(int capacidadehospedes) {
        this.capacidadehospedes = capacidadehospedes;
    }

    public float getMetragem() {
        return metragem;
    }

    public void setMetragem(float metragem) {
        this.metragem = metragem;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public boolean getFlagmanutencao() {
        return flagmanutencao;
    }

    public void setFlagmanutencao(boolean flagmanutencao) {
        this.flagmanutencao = flagmanutencao;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
