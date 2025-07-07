package model;

import generator.CrudEntity;
import generator.CrudField;

@CrudEntity(tableName = "veículos", displayName = "Veículos")
public class Veiculo {

    @CrudField(label = "ID", editable = false, order = 1, type = "NUMBER", showInTable = true, tableOrder = 1)
    private int id;

    @CrudField(label = "Placa", required = true, order = 2, maxLength = 7, showInTable = true, tableOrder = 2)
    private String placa;

    @CrudField(label = "Cor", required = true, order = 3, type = "COMBOBOX", showInTable = true, tableOrder = 3, items = {"Branco", "Preto", "Prata", "Cinza", "Vermelho", "Azul", "Verde", "Outra"})
    private String cor;

    @CrudField(label = "Status", required = true, order = 4, type = "STATUS_CHAR", showInTable = true, tableOrder = 4)
    private char status;
    @CrudField(label = "Modelo", required = true, order = 5, type = "COMBOBOX", itemsClass = Modelo.class, showInTable = true, tableOrder = 5)
    private Modelo modelo;

    public Veiculo() {
    }

    public Veiculo(int id, String placa, String cor, char status) {
        this.id = id;
        this.placa = placa;
        this.cor = cor;
        this.status = status;
    }

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
        char upperStatus = Character.toUpperCase(status);
        if (upperStatus == 'A' || upperStatus == 'I') {
            this.status = upperStatus;
        } else {
            this.status = 'A';
        }
    }

    @Override
    public String toString() {
        return this.getPlaca();
    }
}
