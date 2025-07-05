package model;

import generator.CrudEntity;
import generator.CrudField;

@CrudEntity(tableName = "marcas", displayName = "Marcas")
public class Marca {
    @CrudField(label = "ID", editable = false, order = 1, type = "NUMBER", showInTable = true, tableOrder = 1)
    private int id;
    @CrudField(label = "Nome", required = true, order = 2, maxLength = 100, showInTable = true, tableOrder = 2)
    private String descricao;
    @CrudField(label = "Status", order = 3, type = "CHAR", showInTable = true, tableOrder = 3)
    private char status;

    public Marca() {
    }

    public Marca(int id, String descricao, char status) {
        this.id = id;
        this.descricao = descricao;
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

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        if ( (status == 'A') || (status == 'C') || (status == 'a') || (status == 'c') ) {
            this.status = status;
        }else {
            this.status = 'A';
        }
    }

    @Override
    public String toString() {
        return    "id    = " + this.getId()
                + "Desc. = " + this.getDescricao()
                + "Status= " + this.getStatus();
    }

}
