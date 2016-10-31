/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.app.model;

import java.io.Serializable;

/**
 *
 * @author DESARROLLO
 */
public class Objecto implements Serializable{
    private Double transicion[][];
    private Double definitiva[];

    public Double[][] getTransicion() {
        return transicion;
    }

    public void setTransicion(Double[][] transicion) {
        this.transicion = transicion;
    }

    public Double[] getDefinitiva() {
        return definitiva;
    }

    public void setDefinitiva(Double[] definitiva) {
        this.definitiva = definitiva;
    }

    public Objecto() {
    }
    
}
