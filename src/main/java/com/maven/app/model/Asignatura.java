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
public class Asignatura implements Serializable{

    private int idAsignatura;
    private String nombreAsignatura;
    private int cantidadCredito;

    public Asignatura() {
    }

    public Asignatura(int idAsignatura, String nombreAsignatura, int cantidadCredito) {
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.cantidadCredito = cantidadCredito;
    }

    public Asignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public Asignatura(String nombreAsignatura, int cantidadCredito) {
        this.nombreAsignatura = nombreAsignatura;
        this.cantidadCredito = cantidadCredito;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public int getCantidadCredito() {
        return cantidadCredito;
    }

    public void setCantidadCredito(int cantidadCredito) {
        this.cantidadCredito = cantidadCredito;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

}
