package com.mycompany.inicio;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonaDB personaTable = new PersonaDB();
        PersonaDB.Persona andres = new PersonaDB.Persona(1,"Andres", "andres@gmail.com", "Heredia",  "123456789", "60153619" );
        PersonaDB.Persona.crearUsuario(andres);
        List<PersonaDB.Persona> tablaPersona = new ArrayList<>();
        tablaPersona = PersonaDB.Persona.obtenerPersonas();
        for (PersonaDB.Persona persona : tablaPersona) {
            System.out.println(persona.getId());
            System.out.println(persona.getNombre());
            System.out.println(persona.getEmail());
            System.out.println(persona.getDireccion());
            System.out.println(persona.getTelefono());
            System.out.println(persona.getPassword());
        }
    }
}
