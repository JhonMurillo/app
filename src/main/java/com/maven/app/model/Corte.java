/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.app.model;

import java.io.Serializable;

/**
 *
 * @author Solinces
 */
public class Corte implements Serializable {

    private int idCorte;
    private String nombreCorte;
    private Float porcentajeCorte;

    public Corte() {
    }

    public Corte(int idCorte, String nombreCorte, Float porcentajeCorte) {
        this.idCorte = idCorte;
        this.nombreCorte = nombreCorte;
        this.porcentajeCorte = porcentajeCorte;
    }

    public Corte(String nombreCorte) {
        this.nombreCorte = nombreCorte;
    }

    public Corte(String nombreCorte, Float porcentajeCorte) {
        this.nombreCorte = nombreCorte;
        this.porcentajeCorte = porcentajeCorte;
    }

    public String getNombreCorte() {
        return nombreCorte;
    }

    public void setNombreCorte(String nombreCorte) {
        this.nombreCorte = nombreCorte;
    }

    public Float getPorcentajeCorte() {
        return porcentajeCorte;
    }

    public void setPorcentajeCorte(Float porcentajeCorte) {
        this.porcentajeCorte = porcentajeCorte;
    }

    public int getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(int idCorte) {
        this.idCorte = idCorte;
    }

}
