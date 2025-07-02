package model;

public class Reserva {

    private int id;
    private String datahorareserva;
    private java.util.Date dataprimeiravisita;
    private java.util.Date dataprevissaida;
    private char status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatahorareserva() {
        return datahorareserva;
    }

    public void setDatahorareserva(String datahorareserva) {
        this.datahorareserva = datahorareserva;
    }

    public java.util.Date getDataprimeiravisita() {
        return dataprimeiravisita;
    }

    public void setDataprimeiravisita(java.util.Date dataprimeiravisita) {
        this.dataprimeiravisita = dataprimeiravisita;
    }

    public java.util.Date getDataprevissaida() {
        return dataprevissaida;
    }

    public void setDataprevissaida(java.util.Date dataprevissaida) {
        this.dataprevissaida = dataprevissaida;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
