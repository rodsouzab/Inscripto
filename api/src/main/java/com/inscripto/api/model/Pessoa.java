package com.inscripto.api.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name="pessoa")
@Inheritance(strategy = InheritanceType.JOINED)

public class Pessoa {
    
    @Id
    private String cpf;

    private String nome;
    private String apelido;
    private Date data_nascimento;
    private String telefone;
    private String foto_url;
    private String bairro;
    private String complemento;
    private int numero;
    private String rua;
    private String cep;
    private String instituicao_ensino;
    private String senha;
    private boolean admin;

    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getApelido() {
        return apelido;
    }
    
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
    
    public Date getData_nascimento() {
        return data_nascimento;
    }
    
    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getFoto_url() {
        return foto_url;
    }
    
    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }
    
    public String getBairro() {
        return bairro;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getComplemento() {
        return complemento;
    }
    
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public String getRua() {
        return rua;
    }
    
    public void setRua(String rua) {
        this.rua = rua;
    }
    
    public String getCep() {
        return cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public String getInstituicao_ensino() {
        return instituicao_ensino;
    }
    
    public void setInstituicao_ensino(String instituicao_ensino) {
        this.instituicao_ensino = instituicao_ensino;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public boolean isAdmin() {
        return admin;
    }
    
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    

}
