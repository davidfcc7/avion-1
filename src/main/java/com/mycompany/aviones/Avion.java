/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aviones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Calderon, David Caicedo 
 * @version 1.0
 */
public class Avion {
    List <Vip> vip = new ArrayList<>();;
    List <General> general = new ArrayList<>(); ;

   
    public List<Vip> getVip() {
        return vip;
    }

    public void setVip(List<Vip> vip) {
        this.vip = vip;
    }

    public List<General> getGeneral() {
        return general;
    }

    public void setGeneral(List<General> general) {
        this.general = general;
    }

    
    
}
