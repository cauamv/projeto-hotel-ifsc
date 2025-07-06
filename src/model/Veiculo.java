package model;

import generator.CrudEntity;
import generator.CrudField;

@CrudEntity(tableName = "veículos", displayName = "Veículos")
public class Veiculo {
    @CrudField(label = "ID", editable = false, order = 1, type = "NUMBER", showInTable = true, tableOrder = 1)
    private int id;
    @CrudField(label = "Placa", required = true, order = 2, maxLength = 7, showInTable = true, tableOrder = 2)
    private String placa;
     @CrudField(label = "Cor", required = true, order = 3, maxLength = 15, showInTable = true, tableOrder = 3)
    private String cor;
    @CrudField(label = "Status",required = true,maxLength = 1, order = 4, type = "CHAR", showInTable = true, tableOrder = 4) 
    private char status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
