package model;

import generator.CrudEntity;
import generator.CrudField;

@CrudEntity(tableName = "vaga_estacionamento", displayName = "Vagas de Estacionamento")
public class VagaEstacionamento {

    @CrudField(label = "ID", editable = false, order = 1, type = "NUMBER", showInTable = true, tableOrder = 1)
    private int id;

    @CrudField(label = "Identificação da Vaga", required = true, order = 2, maxLength = 100, showInTable = true, tableOrder = 2)
    private String descricao;

    @CrudField(label = "Metragem", required = true, order = 3, typeMask = "METRAGEM", mask = true, maxLength = 7, showInTable = true, tableOrder = 3)
    private float metragemvaga;

    @CrudField(label = "Status", order = 4, type = "STATUS_CHAR", showInTable = true, tableOrder = 4)
    private char status;

    public VagaEstacionamento() {
    }

    public VagaEstacionamento(int id, String descricao, float metragemvaga, char status) {
        this.id = id;
        this.descricao = descricao;
        this.metragemvaga = metragemvaga;
        this.status = status;
    }

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
        char upperStatus = Character.toUpperCase(status);
        if (upperStatus == 'A' || upperStatus == 'I') {
            this.status = upperStatus;
        } else {
            this.status = 'A';
        }
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}