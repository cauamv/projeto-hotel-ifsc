package model;

import generator.CrudEntity;
import generator.CrudField;

@CrudEntity(tableName = "hospedes", displayName = "Hóspedes")
public class Hospede extends Pessoa {

    @CrudField(label = "Razão Social", order = 16, maxLength = 200, showInTable = true, tableOrder = 7)
    private String razaoSocial;

    @CrudField(label = "CNPJ", order = 17, maxLength = 18, showInTable = true, tableOrder = 8, mask = true, typeMask = "CNPJ")
    private String cnpj;

    @CrudField(label = "Inscrição Estadual", order = 18, maxLength = 20)
    private String inscricaoEstadual;

    @CrudField(label = "Contato", order = 19, maxLength = 100)
    private String contato;

    public Hospede() {
    }

    public Hospede(int id, String nome, String fone1, String fone2, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String dataCadastro, String cpf, String rg, String razaoSocial, String cnpj, String inscricaoEstadual, String contato, String obs, char status) {
        super(id, nome, fone1, fone2, email, cep, logradouro, bairro, cidade, complemento, dataCadastro, cpf, rg, obs, status);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.contato = contato;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\ncnpj   = " + this.getCnpj()
                + "\nCep    = " + this.getCnpj()
                + "\nCidade = " + this.getCidade()
                + "\nBairro = " + this.getBairro()
                + "\nLogradouro = " + this.getLogradouro()
                + "\nComplemento= " + this.getComplemento()
                + "\nContato    = " + this.getContato()
                + "\nStatus = " + this.getStatus();
    }

}
