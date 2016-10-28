/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.app.controller;

import com.maven.app.model.Asignatura;
import com.maven.app.model.Corte;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Solinces
 */
@ManagedBean
@Named("controllerApp")
@ViewScoped
public class controllerApp implements Serializable {

    private String cantidadCortes;
    private String cantidadAsignaturas;
    private List<Asignatura> listaAsignaturas;
    private List<Corte> listaCortes;
    private Double transicion[][];
    private boolean mostrarTabla;
    private boolean mostrarPanel;
    private int cantCortes;
    private int cantAsignatura;

    @PostConstruct
    public void init() {
        cantidadAsignaturas = "1";
        cantidadCortes = "1";
        listaAsignaturas = new ArrayList<>();
        listaCortes = new ArrayList<>();
        cantCortes = Integer.parseInt(cantidadCortes);
        cantAsignatura = Integer.parseInt(cantidadAsignaturas);
        transicion = new Double[cantAsignatura][cantCortes];
        mostrarTabla = true;
        mostrarPanel = false;
    }

    public void cargar(ActionEvent event) {
        try {
            mostrarTabla = true;
            mostrarPanel = true;

            listaAsignaturas = new ArrayList<>();
            listaCortes = new ArrayList<>();

            cantCortes = Integer.parseInt(cantidadCortes);
            cantAsignatura = Integer.parseInt(cantidadAsignaturas);
            transicion = new Double[cantAsignatura][cantCortes];
            for (int j = 0; j < cantCortes; j++) {
                String consecutivoN = String.valueOf(j + 1);
                Corte corte = new Corte(j, "Corte - " + consecutivoN, 0F);
                listaCortes.add(corte);
            }

            for (int i = 0; i < transicion.length; i++) {
                String consecutivoA = String.valueOf(i + 1);
                Asignatura asignatura = new Asignatura(i, "Asignatura - " + consecutivoA, 0);
                listaAsignaturas.add(asignatura);
                for (int j = 0; j < transicion[i].length; j++) {
                    transicion[i][j] = 0D;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_FATAL, null, "Ha ocurrido un error interno, por favor comuniquese con el administrador del sistemas"));
        }
    }

    public void calcular(ActionEvent event) {
        try {
            for (Asignatura listaAsignatura : listaAsignaturas) {
                for (Corte listaCorte : listaCortes) {
                    Double nota = transicion[listaAsignatura.getIdAsignatura()][listaCorte.getIdCorte()];
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_FATAL, null, "Ha ocurrido un error interno, por favor comuniquese con el administrador del sistemas"));
        }
    }

    public void mostrarTablaAction() {
        mostrarTabla = false;
    }

    public Double[][] getTransicion() {
        return transicion;
    }

    public void setTransicion(Double[][] transicion) {
        this.transicion = transicion;
    }

    public List<Asignatura> getListaAsignaturas() {
        return listaAsignaturas;
    }

    public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
        this.listaAsignaturas = listaAsignaturas;
    }

    public List<Corte> getListaCortes() {
        return listaCortes;
    }

    public void setListaCortes(List<Corte> listaCortes) {
        this.listaCortes = listaCortes;
    }

    public boolean isMostrarTabla() {
        return mostrarTabla;
    }

    public void setMostrarTabla(boolean mostrarTabla) {
        this.mostrarTabla = mostrarTabla;
    }

    public boolean isMostrarPanel() {
        return mostrarPanel;
    }

    public void setMostrarPanel(boolean mostrarPanel) {
        this.mostrarPanel = mostrarPanel;
    }

    public String getCantidadCortes() {
        return cantidadCortes;
    }

    public void setCantidadCortes(String cantidadCortes) {
        this.cantidadCortes = cantidadCortes;
    }

    public String getCantidadAsignaturas() {
        return cantidadAsignaturas;
    }

    public void setCantidadAsignaturas(String cantidadAsignaturas) {
        this.cantidadAsignaturas = cantidadAsignaturas;
    }

    public int getCantCortes() {
        return cantCortes;
    }

    public void setCantCortes(int cantCortes) {
        this.cantCortes = cantCortes;
    }

    public int getCantAsignatura() {
        return cantAsignatura;
    }

    public void setCantAsignatura(int cantAsignatura) {
        this.cantAsignatura = cantAsignatura;
    }

}
