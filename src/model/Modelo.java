package model;

import generator.CrudEntity;
import generator.CrudField;

@CrudEntity(tableName = "modelos", displayName = "Modelos")
public class Modelo {

    @CrudField(label = "ID", editable = false, order = 1, type = "NUMBER", showInTable = true, tableOrder = 1)
    private int id;

    @CrudField(label = "Descrição", required = true, order = 2, maxLength = 100, showInTable = true, tableOrder = 2)
    private String descricao;

    @CrudField(label = "Status", required = true, order = 3, type = "STATUS_CHAR", maxLength = 1, showInTable = true, tableOrder = 4)
    private char status;

    @CrudField(label = "Marca", required = true, order = 4, type = "COMBOBOX", itemsClass = Marca.class, showInTable = true, tableOrder = 3)
    private Marca marca;

    public Modelo() {
    }

    public Modelo(int id, String descricao, char status, Marca marca) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.marca = marca;
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
    
    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}