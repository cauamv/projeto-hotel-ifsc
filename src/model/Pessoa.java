package model;

import generator.CrudEntity;
import generator.CrudField;

@CrudEntity(tableName = "pessoas", displayName = "Pessoas")
public class Pessoa {

    @CrudField(label = "ID", editable = false, order = 1, type = "NUMBER", showInTable = true, tableOrder = 1)
    private int id;

    @CrudField(label = "Nome", required = true, order = 2, maxLength = 100, showInTable = true, tableOrder = 2)
    private String nome;

    @CrudField(label = "Telefone 1", order = 3, showInTable = true, tableOrder = 3, mask = true, typeMask = "TELEFONE")
    private String fone1;

    @CrudField(label = "Telefone 2", order = 4, maxLength = 15, mask = true, typeMask = "TELEFONE")
    private String fone2;

    @CrudField(label = "Email", order = 5, maxLength = 100, showInTable = true, tableOrder = 4)
    private String email;

    @CrudField(label = "CEP", order = 6, maxLength = 10, mask = true, typeMask = "CEP")
    private String cep;

    @CrudField(label = "Logradouro", order = 7, maxLength = 200)
    private String logradouro;

    @CrudField(label = "Bairro", order = 8, maxLength = 100)
    private String bairro;

    @CrudField(label = "Cidade", order = 9, maxLength = 100, showInTable = true, tableOrder = 5)
    private String cidade;

    @CrudField(label = "Complemento", order = 10, maxLength = 200)
    private String complemento;

    @CrudField(label = "Data Cadastro", order = 11, mask = true, typeMask = "DATE")
    private String dataCadastro;

    @CrudField(label = "CPF", order = 12, maxLength = 14, mask = true, typeMask = "CPF")
    private String cpf;

    @CrudField(label = "RG", order = 13, maxLength = 20)
    private String rg;

    @CrudField(label = "Observações", order = 14, maxLength = 500)
    private String obs;

    @CrudField(label = "Status", order = 15, type = "STATUS_CHAR", showInTable = true, tableOrder = 6)
    private char status;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String fone1, String fone2, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String dataCadastro, String cpf, String rg, String obs, char status) {
        this.id = id;
        this.nome = nome;
        this.fone1 = fone1;
        this.fone2 = fone2;
        this.email = email;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.complemento = complemento;
        this.dataCadastro = dataCadastro;
        this.cpf = cpf;
        this.rg = rg;
        this.obs = obs;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone1() {
        return fone1;
    }

    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

    public String getFone2() {
        return fone2;
    }

    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
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

        if ((status == 'A') || (status == 'C') || (status == 'a') || (status == 'c')) {
            this.status = status;
        } else {
            this.status = 'A';
        }

    }

    @Override
    public String toString() {
        return "id      = " + this.id
                + "\nnome   = " + this.nome
                + "\nfone1  = " + this.fone1
                + "\nfone2  = " + this.fone2
                + "\nemail  = " + this.email
                + "\nrg     = " + this.rg
                + "\ncpf    = " + this.cpf
                + "\nobs    = " + this.obs
                + "\nstatus = " + this.status;
    }

}
