package model;

import generator.CrudEntity;
import generator.CrudField;

@CrudEntity(tableName = "produtos copa", displayName = "Produtos copa")
public class ProdutoCopa {
    
    @CrudField(label = "ID", editable = false, order = 1, type = "NUMBER", showInTable = true, tableOrder = 1)
    private int id;
    @CrudField(label = "Descrição", required = true, order = 2, maxLength = 100, showInTable = true, tableOrder = 2)
    private String descricao;
    @CrudField(label = "Valor", required = true, order = 3,typeMask="VALOR",mask = true, maxLength = 6, showInTable = true, tableOrder = 3)
    private float valor;
    @CrudField(label = "Observação", required = false, order = 4, maxLength = 100, showInTable = true, tableOrder = 4)
    private String obs;
    @CrudField(label = "Status", order = 5, type = "STATUS_CHAR", showInTable = true, tableOrder = 5)
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

    public double getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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
