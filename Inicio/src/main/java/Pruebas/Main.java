package Pruebas;

public class Main {
    public static void main(String[] args) {
        PersonaDB.Persona newPersona = new PersonaDB.Persona("Andres", "andres@gmail.com","Heredia", "60153619", "123456789");
        PersonaDB dbPersona = new PersonaDB();

        PersonaDB.Persona.crearUsuario(newPersona); //Crear usuario
        PersonaDB.Persona.obtenerPersonas();
        PersonaDB.Persona.buscarUsuarioPorID(newPersona.getId());
        PersonaDB.Persona.actualizarUsuario(newPersona);
        PersonaDB.Persona.eliminarUsuario(newPersona.getId());
    }
}
