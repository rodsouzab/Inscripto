/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.inscripto.api.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author ticolinux
 */

 

@AllArgsConstructor
@NoArgsConstructor
public class EhFamiliar extends Encontreiro{

    private Encontreiro encontreiro1;
    private Encontreiro encontreiro2;
    private boolean trabalhar_junto;
    private String relacao;


    public boolean isEhFamiliar() {
        return true;
    }

    public String getEncontreiro1Cpf(){
        return this.encontreiro1.getCpf();
    }

    public String getEncontreiro2Cpf(){
        return this.encontreiro2.getCpf();
    }

    public Encontreiro getEncontreiro1() {
        return encontreiro1;
    }

    public void setEncontreiro1(Encontreiro encontreiro1) {
        this.encontreiro1 = encontreiro1;
    }

    public Encontreiro getEncontreiro2() {
        return encontreiro2;
    }

    public void setEncontreiro2(Encontreiro encontreiro2) {
        this.encontreiro2 = encontreiro2;
    }

    public boolean isTrabalhar_junto() {
        return trabalhar_junto;
    }

    public void setTrabalhar_junto(boolean trabalhar_junto) {
        this.trabalhar_junto = trabalhar_junto;
    }

    public String getRelacao() {
        return relacao;
    }

    public void setRelacao(String relacao) {
        this.relacao = relacao;
    }

}
