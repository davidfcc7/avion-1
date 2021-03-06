/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.avion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ivan Calderon, David Caicedo 
 * @version 1.0
 */
public class Principal {

    public BufferedReader entradaDatos = new BufferedReader(new InputStreamReader(System.in));
    Avion avion1; //Objeto avion
    Persona cliente; //Objeto Persona
    double precioGeneral = 90000.0; //Coto pasaje generel
    double precioVip = 150000.0; //Costo pasaje VIP
    double totalAcumulado = 0; //Total dinero de pasaje vendidos
    boolean av1 = false; //Estado avion 1
    boolean av2 = false; //Estado avion 2
    boolean av3 = false; //Estado avion 3   
    String rojo ="\033[31m"; //Declaro color rojo 
    String verde ="\033[32m"; //Decalro color verde

    public Principal() {

        AgregarAsientos();//Metodo que carga los asientos

    }

    public void Menu() throws IOException, ParseException {
        String opcion = "";
        String opcion2 = "";
  
        do {
            
            boolean estado = false;
            boolean error = false;
            
            System.out.println("1. Avion General/Vip");
            System.out.println("2. Avion General");
            System.out.println("3. Avion Vip");
            System.out.println("0. Salir ");
            System.out.print(rojo+"Digite la opcion deseada:  ");

            try {
                opcion = entradaDatos.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (opcion.equals("1")) {
                if (av1 == true) {
                    System.out.println(verde+"El avion ya despego ");
                } else {
                    do {
                        
                        //Strings que concatenan la sillas por fila para imprimir.
                        String A = concatenarSillasVipA();
                        String B = concatenarSillasVipB();
                        String C = concatenarSillasGenC();
                        String D = concatenarSillasGenD();
                        String E = concatenarSillasGenE();
                        //Imprime el lo asientos
                        System.out.println("\n ");
                        System.out.println("\n"
                        + "|-------------------|\n" 
                        + "|       123456      |\n"               
                        + "|    A: " + A + "      |\n"
                        + "|    B: " + B + "      |\n"
                        + "|-------------------|\n"
                        + "|       12345678    |\n"
                        + "|    C: " + C + "    |\n"
                        + "|    D: " + D + "    |\n"
                        + "|    E: " + E + "    |\n"
                        + "|-------------------|\n");

                        System.out.println("1.Comprar boleto.");
                        System.out.println("2.Despegar.");
                        System.out.print(rojo+"Digite la opcion deseada:  ");
                        //Declaramos variables
                        int cc = 0;
                        String name = "";
                        Date fech = null;
                        String asiento = "";

                        try {
                            opcion2 = entradaDatos.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (opcion2.equals("1")) {

                            while (error == false) {
                                System.out.println("Cedula cliente: ");
                                String v = entradaDatos.readLine();
                                if (isNumeric(v)) {

                                    cc = Integer.parseInt(v);
                                    break;
                                } else {
                                    System.out.println(rojo+"Digite un numero valido");
                                }
                            }
                            while (error == false) {
                                System.out.println("Nombre cliente: ");
                                name = entradaDatos.readLine();
                                if (soloLetras(name)) {
                                    break;
                                } else {
                                    System.out.println(rojo+"Digite un nombre valido, solo letras");
                                }
                            }
                            while (error == false) {
                                System.out.println("Fecha de nacimiento dd-mm-yyyy: ");
                                String f = entradaDatos.readLine();
                                if (validarFecha(f)) {
                                    fech = new SimpleDateFormat("dd-MM-yyyy").parse(f);
                                    break;
                                } else {
                                    System.out.println(rojo+"Ingrese una fecha valida");
                                }

                            }

                            System.out.println(verde+"Costo asiento VIP es de $" + precioVip + " Pesos");
                            System.out.println(verde+"Costo asiento General es de $" + precioGeneral + " Pesos");
                            while (error == false) {

                                System.out.println(rojo+"Ubicacion del asiento, ejemplo: A1 B1)");
                                asiento = entradaDatos.readLine();
                                if (validaAsiento1(asiento)) {
                                    break;

                                } else {
                                    System.out.println(rojo+"Asiento no existe");
                                    System.out.println(rojo+"Letra mayuscula y numero, por favor.");
                                }
                            }

                            compraClienteA1(cc, name, fech, asiento);

                        } else if (opcion2.equals("2")) {
                            despegarAvion();
                            av1 = true;
                            estado = true;
                        }
                        
                    } while (estado == false);

                }

            } else if (opcion.equals("2")) {
                if (av2 == true) {
                    System.out.println(verde+"El avion ya despego.");
                } else {
                    do {
                        String C = concatenarSillasGenC();
                        String D = concatenarSillasGenD();
                        String E = concatenarSillasGenE();
                        System.out.println("\n"
                        + "|-------------------|\n"
                        + "|       12345678    |\n"
                        + "|    C: " + C + "    |\n"
                        + "|    D: " + D + "    |\n"
                        + "|    E: " + E + "    |\n"
                        + "|-------------------|\n");

                        System.out.println("1.Comprar boleto");
                        System.out.println("2.Despegar");
                        System.out.print(rojo+"Digite la opcion deseada:  ");

                        int cc = 0;
                        String name = "";
                        Date fech = null;
                        String asiento = "";
                        try {
                            opcion2 = entradaDatos.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (opcion2.equals("1")) {

                            while (error == false) {
                                System.out.println("Cedula cliente: ");
                                String v = entradaDatos.readLine();
                                if (isNumeric(v)) {

                                    cc = Integer.parseInt(v);
                                    break;
                                } else {
                                    System.out.println(rojo+"Digite un numero valido");
                                }
                            }
                            while (error == false) {
                                System.out.println("Nombre cliente: ");
                                name = entradaDatos.readLine();
                                if (soloLetras(name)) {
                                    break;
                                } else {
                                    System.out.println(rojo+"Digite un nombre valido, solo letras");
                                }
                            }
                            while (error == false) {
                                System.out.println("Fecha de nacimiento dd-mm-yyyy: ");
                                String f = entradaDatos.readLine();
                                if (validarFecha(f)) {
                                    fech = new SimpleDateFormat("dd-MM-yyyy").parse(f);
                                    break;
                                } else {
                                    System.out.println(rojo+"Ingrese una fecha valida");
                                }

                            }
                            System.out.println(verde+"Costo asiento general es de $" + precioGeneral + " Pesos");
                            while (error == false) {

                                System.out.println(rojo+"Ubicacion del asitno, ejemplo: A1 B1");
                                asiento = entradaDatos.readLine();
                                if (validaAsiento2(asiento)) {
                                    break;

                                } else {
                                    System.out.println(rojo+"Asiento no existe");
                                    System.out.println(rojo+"Letra mayuscula y numero, por favor.");
                                }
                            }
                            compraClienteA2(cc, name, fech, asiento);

                        } else if (opcion2.equals("2")) {
                            despegarAvion();
                            av2 = true;
                            estado = true;
                        }
                        
                    } while (estado == false);
                }

            } else if (opcion.equals("3")) {
                if (av3 == true) {
                    System.out.println(verde+"El avion ya despego ");
                } else {
                    do {
                        String A = concatenarSillasVipA();
                        String B = concatenarSillasVipB();
                        System.out.println("\n"
                        + "|-------------------|\n" 
                        + "|       123456      |\n"               
                        + "|    A: " + A + "      |\n"
                        + "|    B: " + B + "      |\n"
                        + "|-------------------|\n");

                        System.out.println("1.Comprar boleto.");
                        System.out.println("2.Despegar.");
                        System.out.print(rojo+"Digite la opcion deseada:  ");

                        int cc = 0;
                        String name = "";
                        Date fech = null;
                        String asiento = "";
                        try {
                            opcion2 = entradaDatos.readLine();
                        } catch (IOException ex) {
                            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (opcion2.equals("1")) {

                            while (error == false) {
                                System.out.println("Cedula cliente: ");
                                String v = entradaDatos.readLine();
                                if (isNumeric(v)) {

                                    cc = Integer.parseInt(v);
                                    break;
                                } else {
                                    System.out.println(rojo+"Digite un numero valido");
                                }
                            }
                            while (error == false) {
                                System.out.println("Nombre cliente: ");
                                name = entradaDatos.readLine();
                                if (soloLetras(name)) {
                                    break;
                                } else {
                                    System.out.println(rojo+"Digite un nombre valido, solo letras");
                                }
                            }
                            while (error == false) {
                                System.out.println("Fecha de nacimiento dd-mm-yyyy: ");
                                String f = entradaDatos.readLine();
                                if (validarFecha(f)) {
                                    fech = new SimpleDateFormat("dd-MM-yyyy").parse(f);
                                    break;
                                } else {
                                    System.out.println(rojo+"Ingrese una fecha valida");
                                }

                            }
                            System.out.println(verde+"Costo asiento VIP es de $" + precioVip + " Pesos");
                            while (error == false) {

                                System.out.println(rojo+"Ubicacion del asiento, ejemplo: A1 B1");
                                asiento = entradaDatos.readLine();
                                if (validaAsiento3(asiento)) {
                                    break;

                                } else {
                                    System.out.println(rojo+"Asiento no existe");
                                    System.out.println(rojo+"Letra mayuscula y numero, por favor.");
                                }
                            }
                            compraClienteA3(cc, name, fech, asiento);

                        } else if (opcion2.equals("2")) {
                            despegarAvion();
                            av3 = true;
                            estado = true;
                        }
                    } while (estado == false);
                }
            }

        } while (!(opcion.equals("0")));
    }
    
    public void AgregarAsientos(){
        avion1 = new Avion(); // Se intancia el avion 
        //Se crea el avion VIP
        avion1.getVip().add(0, new Vip("O", "A1"));
        avion1.getVip().add(1, new Vip("O", "A2"));
        avion1.getVip().add(2, new Vip("O", "A3"));
        avion1.getVip().add(3, new Vip("O", "A4"));
        avion1.getVip().add(4, new Vip("O", "A5"));
        avion1.getVip().add(5, new Vip("O", "A6"));
        avion1.getVip().add(6, new Vip("O", "B1"));
        avion1.getVip().add(7, new Vip("O", "B2"));
        avion1.getVip().add(8, new Vip("O", "B3"));
        avion1.getVip().add(9, new Vip("O", "B4"));
        avion1.getVip().add(10, new Vip("O", "B5"));
        avion1.getVip().add(11, new Vip("O", "B6"));
        //Se crea el avion General
        avion1.getGeneral().add(0, new General("o", "C1"));
        avion1.getGeneral().add(1, new General("o", "C2"));
        avion1.getGeneral().add(2, new General("o", "C3"));
        avion1.getGeneral().add(3, new General("o", "C4"));
        avion1.getGeneral().add(4, new General("o", "C5"));
        avion1.getGeneral().add(5, new General("o", "C6"));
        avion1.getGeneral().add(6, new General("o", "C7"));
        avion1.getGeneral().add(7, new General("o", "C8"));
        avion1.getGeneral().add(8, new General("o", "D1"));
        avion1.getGeneral().add(9, new General("o", "D2"));
        avion1.getGeneral().add(10, new General("o", "D3"));
        avion1.getGeneral().add(11, new General("o", "D4"));
        avion1.getGeneral().add(12, new General("o", "D5"));
        avion1.getGeneral().add(13, new General("o", "D6"));
        avion1.getGeneral().add(14, new General("o", "D7"));
        avion1.getGeneral().add(15, new General("o", "D8"));
        avion1.getGeneral().add(16, new General("o", "E1"));
        avion1.getGeneral().add(17, new General("o", "E2"));
        avion1.getGeneral().add(18, new General("o", "E3"));
        avion1.getGeneral().add(19, new General("o", "E4"));
        avion1.getGeneral().add(20, new General("o", "E5"));
        avion1.getGeneral().add(21, new General("o", "E6"));
        avion1.getGeneral().add(22, new General("o", "E7"));
        avion1.getGeneral().add(23, new General("o", "E8"));
    }

    public String concatenarSillasVipA() {
        String concatenadoA = "";

        for (Vip av : avion1.getVip()) {
            Vip temp = av;
            if (temp.getUbicacion().contains("A")) {
                concatenadoA = concatenadoA + temp.getEstado();
            }
        }
        return concatenadoA;
    }

    public String concatenarSillasVipB() {
        String concatenadoB = "";
        for (Vip av : avion1.getVip()) {
            Vip temp = av;
            if (temp.getUbicacion().contains("B")) {
                concatenadoB = concatenadoB + temp.getEstado();
            }
        }
        return concatenadoB;
    }

    public String concatenarSillasGenC() {
        String concatenadoC = "";
        for (General av : avion1.getGeneral()) {
            General temp = av;
            if (temp.getUbicacion().contains("C")) {
                concatenadoC = concatenadoC + temp.getEstado();
            }
        }
        return concatenadoC;
    }

    public String concatenarSillasGenD() {
        String concatenadoD = "";
        for (General av : avion1.getGeneral()) {
            General temp = av;
            if (temp.getUbicacion().contains("D")) {
                concatenadoD = concatenadoD + temp.getEstado();
            }
        }
        return concatenadoD;
    }

    public String concatenarSillasGenE() {
        String concatenadoE = "";
        for (General av : avion1.getGeneral()) {
            General temp = av;
            if (temp.getUbicacion().contains("E")) {
                concatenadoE = concatenadoE + temp.getEstado();
            }
        }
        return concatenadoE;
    }

    public void compraClienteA1(int cedu, String nom, Date fech, String asien) {

        
            if (asien.contains("A") || asien.contains("B")) {
                for (Vip av : avion1.getVip()) {
                    Vip temp = av;
                    try{
                    if (temp.getUbicacion().contains(asien)) {
                        if (temp.getEstado().contains("O")) {
                            temp.setPer(cliente = new Persona(cedu, nom, fech));
                            temp.setEstado("X");
                            totalAcumulado = totalAcumulado + precioVip;

                        } else {
                            
                            System.out.println(verde+"El Asiento esta ocupado");
                            

                        }

                    }else{
                   
                    }
                    }catch (RuntimeException ex) {
                   System.out.println(rojo+"La silla no existe" + ex);

                    }
                
            
                }
            } else if (asien.contains("C") || asien.contains("D") || asien.contains("E")) {
                for (General av : avion1.getGeneral()) {
                    General temp = av;
                     try{
                    if (temp.getUbicacion().contains(asien)) {
                        if (temp.getEstado().contains("o")) {
                            temp.setPer(cliente = new Persona(cedu, nom, fech));
                            temp.setEstado("x");
                            totalAcumulado = totalAcumulado + precioVip;

                        } else {
                            
                            System.out.println(verde+"El Asiento esta ocupado");
                          

                        }

                    }else{
                   
                    }
                    }catch (RuntimeException ex) {
                   System.out.println(rojo+"La silla no existe" + ex);

                    }
                }
                } else {
                System.out.println(rojo+"Ingreso un caracter no valido ");
            }
        }
    public void compraClienteA2(int cedu, String nom, Date fech, String asien) {

        
            
         if (asien.contains("C") || asien.contains("D") || asien.contains("E")) {
                for (General av : avion1.getGeneral()) {
                    General temp = av;
                     try{
                    if (temp.getUbicacion().contains(asien)) {
                        if (temp.getEstado().contains("o")) {
                            temp.setPer(cliente = new Persona(cedu, nom, fech));
                            temp.setEstado("x");
                            totalAcumulado = totalAcumulado + precioGeneral;

                        } else {
                            
                            System.out.println(verde+"El Asiento esta ocupado");
                          

                        }

                    }else{
                   
                    }
                    }catch (RuntimeException ex) {
                   System.out.println(rojo+"La silla no existe" + ex);

                    }
                }
                } else {
                System.out.println(rojo+"Ingreso un caracter no valido ");
            }
        }
    public void compraClienteA3(int cedu, String nom, Date fech, String asien) {

        
            if (asien.contains("A") || asien.contains("B")) {
                for (Vip av : avion1.getVip()) {
                    Vip temp = av;
                    try{
                    if (temp.getUbicacion().contains(asien)) {
                        if (temp.getEstado().contains("O")) {
                            temp.setPer(cliente = new Persona(cedu, nom, fech));
                            temp.setEstado("X");
                            totalAcumulado = totalAcumulado + precioVip;

                        } else {
                            
                            System.out.println(verde+"El Asiento esta ocupado");
                          

                        }

                    }else{
                   
                    }
                    }catch (RuntimeException ex) {
                   System.out.println(rojo+"La silla no existe" + ex);

                    }
                
            
                }
            }
        } 
    

    public void despegarAvion() {
        System.out.println("CLIENTES VIP");
        for (Vip av : avion1.getVip()) {
            Vip temp = av;
            if (temp.getEstado().contains("X")) {
                System.out.println(" Silla :"+temp.getUbicacion());
                System.out.println(" Cedula :"+temp.getPer().getCedula());
                System.out.println(" Nombre :"+temp.getPer().getNombre());
                System.out.println(" Fecha :"+new SimpleDateFormat("dd-MM-yyyy").format(temp.getPer().getFechaNacimiento()));
                System.out.println("____________________________________");
                temp.setEstado("O");
                temp.setPer(null);
            }

        }
        System.out.println("CLIENTES GENERAL");
        for (General av : avion1.getGeneral()) {
            General temp = av;
            if (temp.getEstado().contains("x")) {
                System.out.println(" Silla :"+temp.getUbicacion());
                System.out.println(" Cedula :"+temp.getPer().getCedula());
                System.out.println(" Nombre :"+temp.getPer().getNombre());
                System.out.println(" fecha :"+new SimpleDateFormat("dd-MM-yyyy").format(temp.getPer().getFechaNacimiento()));
                temp.setEstado("o");
                temp.setPer(null);

            }

        }
        System.out.println("Total Ingresos del avion es \n " + totalAcumulado);
        totalAcumulado=0;

    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private static boolean soloLetras(String cadena) {
        Pattern patron = Pattern.compile("[^A-Za-z ]");
        Matcher encaja = patron.matcher(cadena);

        if (!encaja.find()) {
            if(cadena.isEmpty()){
            return false;
            }
            return true;
        } else {
            return false;
        }

    }

    private static boolean validaAsiento1(String cadena) {
        Pattern patron = Pattern.compile("[^A-Z0-9]");
        Matcher encaja = patron.matcher(cadena);

        if (!encaja.find()) {
            char charAt = cadena.charAt(0);
            if (charAt == 'A' || charAt == 'B' || charAt == 'C' || charAt == 'D' || charAt == 'E') {
                return true;

            } else {
                return false;
            }

        } else {
            return false;
        }

    }
     private static boolean validaAsiento2(String cadena) {
        Pattern patron = Pattern.compile("[^A-Z0-9]");
        Matcher encaja = patron.matcher(cadena);

        if (!encaja.find()) {
            char charAt = cadena.charAt(0);
            if (charAt == 'C' || charAt == 'D' || charAt == 'E') {
                return true;

            } else {
                return false;
            }

        } else {
            return false;
        }

    }
      private static boolean validaAsiento3(String cadena) {
        Pattern patron = Pattern.compile("[^A-Z0-9]");
        Matcher encaja = patron.matcher(cadena);

        if (!encaja.find()) {
            char charAt = cadena.charAt(0);
            if (charAt == 'A' || charAt == 'B' ) {
                return true;

            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public class ExceptionSoloLetra extends Exception {

        public ExceptionSoloLetra(String msg) {
            super(msg);
        }
    }

    public static boolean validarFecha(String fecha) {
        try {

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
