package model;

public class CheckHospede {

    private int id;
    private String tipohospedagem;
    private String obs;
    private char status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipohospedagem() {
        return tipohospedagem;
    }

    public void setTipohospedagem(String tipohospedagem) {
        this.tipohospedagem = tipohospedagem;
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
