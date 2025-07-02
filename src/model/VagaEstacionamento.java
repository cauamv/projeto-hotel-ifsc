package model;

public class VagaEstacionamento {

    private int id;
    private String descricao;
    private float metragemvaga;
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

    public float getMetragemvaga() {
        return metragemvaga;
    }

    public void setMetragemvaga(float metragemvaga) {
        this.metragemvaga = metragemvaga;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
