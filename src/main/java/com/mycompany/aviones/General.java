/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aviones;

/**
 *
 * @author Ivan Calderon, David Caicedo 
 * @version 1.0
 */
public class General {
    
    private String ubicacion;
    private String estado;
    Persona per;

    public General(String estado, String Ubicacion) {
        this.estado = estado;
        
        this.ubicacion= Ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Persona getPer() {
        return per;
    }

    public void setPer(Persona per) {
        this.per = per;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    
    
}
