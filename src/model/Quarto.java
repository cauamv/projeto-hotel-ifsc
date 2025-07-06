package model;

import generator.CrudEntity;
import generator.CrudField;

@CrudEntity(tableName = "quartos", displayName = "Quartos")
public class Quarto {

    @CrudField(label = "ID", editable = false, order = 1, type = "NUMBER", showInTable = true, tableOrder = 1)
    private int id;
    @CrudField(label = "Descrição", required = true, order = 2, maxLength = 100, showInTable = true, tableOrder = 2)
    private String descricao;
    @CrudField(label = "Capacidade", required = true,type = "NUMBER", order = 3, maxLength = 2, showInTable = true, tableOrder = 3)
    private int capacidadehospedes;
    @CrudField(label = "Metragem", required = true, order = 4,type="NUMBER", maxLength = 7, showInTable = true, tableOrder = 4)
    private float metragem;
    @CrudField(label = "Identificação",required = true,editable = true, order = 5, showInTable = true, tableOrder = 5, maxLength = 4)
    private String identificacao;
    @CrudField(label = "andar", editable = false,required = true,order = 6, type = "NUMBER", showInTable = true,maxLength = 2,tableOrder = 6)
    private int andar;
    @CrudField(label = "Disponível?", editable = true,required = true,order = 7, type = "BOOLEAN", showInTable = true,tableOrder = 7)
    private boolean flagmanutencao;
    @CrudField(label = "Status", order = 8, type = "CHAR", showInTable = true, tableOrder = 8)
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
