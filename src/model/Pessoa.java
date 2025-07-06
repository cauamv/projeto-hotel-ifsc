package model;

import generator.CrudEntity;
import generator.CrudField;

@CrudEntity(tableName = "pessoas", displayName = "Pessoas")
public abstract class Pessoa {

    @CrudField(label = "ID", editable = false, order = 1, type = "NUMBER", showInTable = true, tableOrder = 1)
    private int id;
    
    @CrudField(label = "Nome", required = true, order = 2, maxLength = 150, showInTable = true, tableOrder = 2)
    private String nome;

    @CrudField(label = "Telefone 1", required = true, order = 3, maxLength = 15, mask = true, typeMask = "TELEFONE")
    private String fone1;

    @CrudField(label = "Telefone 2", order = 4, maxLength = 15, mask = true, typeMask = "TELEFONE")
    private String fone2;

    @CrudField(label = "Email", required = true, order = 5, maxLength = 100, showInTable = true, tableOrder = 4)
    private String email;

    @CrudField(label = "CEP", required = true, order = 6, maxLength = 9, mask = true, typeMask = "CEP")
    private String cep;

    @CrudField(label = "Logradouro", required = true, order = 7, maxLength = 200)
    private String logradouro;

    @CrudField(label = "Bairro", required = true, order = 8, maxLength = 100)
    private String bairro;

    @CrudField(label = "Cidade", required = true, order = 9, maxLength = 100)
    private String cidade;

    @CrudField(label = "Complemento", order = 10, maxLength = 100)
    private String complemento;
    
    @CrudField(label = "Data Cadastro", order = 11, editable = false, showInTable = true, tableOrder = 5)
    private String dataCadastro;

    @CrudField(label = "CPF", required = true, order = 12, maxLength = 14, showInTable = true, tableOrder = 6, mask = true, typeMask = "CPF")
    private String cpf;

    @CrudField(label = "RG", order = 13, maxLength = 12)
    private String rg;
    
    @CrudField(label = "Observação", order = 14, maxLength = 255)
    private String obs;

    @CrudField(label = "Status", order = 15, type = "STATUS_CHAR", showInTable = true, tableOrder = 9)
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
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID=" + id + ", Nome=" + nome;
    }
}