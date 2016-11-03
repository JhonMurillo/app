/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maven.app.controller;

import com.maven.app.model.Asignatura;
import com.maven.app.model.Corte;
import com.maven.app.model.Objecto;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
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
    private Objecto objeto;
    private boolean mostrarTabla;
    private boolean mostrarPanel;
    private boolean mostrarDefinitiva;
    private int cantCortes;
    private int cantAsignatura;
    private boolean corteTecnologico;
    private Double promedio;
    private int pro;

    @PostConstruct
    public void init() {
        cantidadAsignaturas = "1";
        cantidadCortes = "1";
        listaAsignaturas = new ArrayList<>();
        listaCortes = new ArrayList<>();
        cantCortes = Integer.parseInt(cantidadCortes);
        cantAsignatura = Integer.parseInt(cantidadAsignaturas);
        transicion = new Double[cantAsignatura][cantCortes];
        objeto = new Objecto();
        objeto.setTransicion(transicion);
        objeto.setDefinitiva(new Double[cantCortes]);
        mostrarTabla = true;
        mostrarPanel = false;
        mostrarDefinitiva = false;
        corteTecnologico = false;
        promedio = 0D;
        pro = 0;
    }

    public void cargar() {
        try {
            promedio = 0D;
            pro = 0;
            if (cantidadAsignaturas.isEmpty() || cantidadAsignaturas == null || "0".equals(cantidadAsignaturas)) {
                mostrarPanel = false;
                mostrarDefinitiva = false;
                mostrarTabla = true;
                listaAsignaturas = new ArrayList<>();
                listaCortes = new ArrayList<>();
                FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, null, "La cantidad de asignaturas no puede ser 0 o estar vacia."));
                return;
            }
            if (cantidadCortes.isEmpty() || cantidadCortes == null || "0".equals(cantidadCortes)) {
                mostrarPanel = false;
                mostrarDefinitiva = false;
                mostrarTabla = true;
                listaAsignaturas = new ArrayList<>();
                listaCortes = new ArrayList<>();

                FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, null, "La cantidad de cortes no puede ser 0 o estar vacia."));
                return;
            }
            mostrarTabla = true;
            mostrarPanel = true;
            mostrarDefinitiva = false;

            listaAsignaturas = new ArrayList<>();
            listaCortes = new ArrayList<>();

            cantCortes = Integer.parseInt(cantidadCortes);
            cantAsignatura = Integer.parseInt(cantidadAsignaturas);
            transicion = new Double[cantAsignatura][cantCortes];
            objeto.setTransicion(transicion);
            Double[] definitiva = new Double[cantAsignatura];
            objeto.setDefinitiva(definitiva);

            if (corteTecnologico) {
                cantidadCortes = "3";
                cantCortes = Integer.parseInt(cantidadCortes);
                transicion = new Double[cantAsignatura][cantCortes];
                objeto.setTransicion(transicion);
                for (int j = 0; j < cantCortes; j++) {
                    if (j == cantCortes - 1) {
                        String consecutivoN = String.valueOf(j + 1);
                        Corte corte = new Corte(j, "Corte - " + consecutivoN, 40);
                        listaCortes.add(corte);
                    } else {
                        String consecutivoN = String.valueOf(j + 1);
                        Corte corte = new Corte(j, "Corte - " + consecutivoN, 30);
                        listaCortes.add(corte);
                    }
                }
            } else {
                for (int j = 0; j < cantCortes; j++) {
                    String consecutivoN = String.valueOf(j + 1);
                    Corte corte = new Corte(j, "Corte - " + consecutivoN, 0);
                    listaCortes.add(corte);
                }
            }

            for (int i = 0; i < transicion.length; i++) {

                String consecutivoA = String.valueOf(i + 1);
                Asignatura asignatura = new Asignatura(i, "Asignatura - " + consecutivoA, 0);
                listaAsignaturas.add(asignatura);
                for (int j = 0; j < transicion[i].length; j++) {
                    transicion[i][j] = 0D;
                    definitiva[i] = 0D;
                    objeto.setTransicion(transicion);
                    objeto.setDefinitiva(definitiva);
                }
            }

            if (listaCortes.size() == 1) {
                listaCortes.get(0).setPorcentajeCorte(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_FATAL, null, "Ha ocurrido un error interno, por favor comuniquese con el administrador del sistemas"));
        }
    }

    public void calcular(ActionEvent event) {
        try {
            pro = 1;
            promedio = 0D;
            DecimalFormat decimal = new DecimalFormat("#.#");
            mostrarDefinitiva = true;
            Double[] definitiva = new Double[listaAsignaturas.size()];
            for (Asignatura listaAsignatura : listaAsignaturas) {
                Double notaDefinitiva = 0D;
                for (Corte listaCorte : listaCortes) {
                    Double nota = transicion[listaAsignatura.getIdAsignatura()][listaCorte.getIdCorte()];
                    Double notaXporcentaje = nota * listaCorte.getPorcentajeCorte() / 100;
                    notaDefinitiva += notaXporcentaje;
                    definitiva[listaAsignatura.getIdAsignatura()] = notaDefinitiva;
                    objeto.setDefinitiva(definitiva);
                }
                promedio += objeto.getDefinitiva()[listaAsignatura.getIdAsignatura()] * listaAsignatura.getCantidadCredito();
            }

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_FATAL, null, "Ha ocurrido un error interno, por favor comuniquese con el administrador del sistemas"));
        }
    }

    public void corteTecnologicoC() {
        try {
            if (corteTecnologico) {
                cantidadCortes = "3";
                if (!listaCortes.isEmpty()) {
                    cargar();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_FATAL, null, "Ha ocurrido un error interno, por favor comuniquese con el administrador del sistemas"));
        }
    }

    public void corteTecnologicoCC(ValueChangeEvent event) {
        corteTecnologico = Boolean.valueOf(event.getNewValue().toString());
    }

    public void validarPorcentajeCortes() {
        try {
            if (listaCortes.size() == 1) {
                listaCortes.get(0).setPorcentajeCorte(100);
                return;
            }

            listaCortes.get(listaCortes.size() - 1).setPorcentajeCorte(0);

            int por = 0;
            for (int i = 0; i < listaCortes.size(); i++) {
                Corte get = listaCortes.get(i);
                por += get.getPorcentajeCorte();

                if (i == listaCortes.size() - 2) {
                    if (por > 100) {
                        for (int j = 0; j < listaCortes.size(); j++) {
                            listaCortes.get(j).setPorcentajeCorte(0);
                        }
                        return;
                    }
                }

                if (i == listaCortes.size() - 1) {
                    listaCortes.get(i).setPorcentajeCorte(100 - por);
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

    public Objecto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objecto objeto) {
        this.objeto = objeto;
    }

    public boolean isMostrarDefinitiva() {
        return mostrarDefinitiva;
    }

    public void setMostrarDefinitiva(boolean mostrarDefinitiva) {
        this.mostrarDefinitiva = mostrarDefinitiva;
    }

    public boolean isCorteTecnologico() {
        return corteTecnologico;
    }

    public void setCorteTecnologico(boolean corteTecnologico) {
        this.corteTecnologico = corteTecnologico;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public int getPro() {
        return pro;
    }

    public void setPro(int pro) {
        this.pro = pro;
    }

}
