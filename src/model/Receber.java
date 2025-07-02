package model;

public class Receber {

    private int id;
    private String datarhocadastro;
    private float valororiginal;
    private float desconto;
    private float acrescimo;
    private float valorpago;
    private String obs;
    private char status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatarhocadastro() {
        return datarhocadastro;
    }

    public void setDatarhocadastro(String datarhocadastro) {
        this.datarhocadastro = datarhocadastro;
    }

    public float getValororiginal() {
        return valororiginal;
    }

    public void setValororiginal(float valororiginal) {
        this.valororiginal = valororiginal;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public float getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(float acrescimo) {
        this.acrescimo = acrescimo;
    }

    public float getValorpago() {
        return valorpago;
    }

    public void setValorpago(float valorpago) {
        this.valorpago = valorpago;
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
