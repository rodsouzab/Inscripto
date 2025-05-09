/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.inscripto.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ticolinux
 */

 
@Getter
@Setter
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

}
