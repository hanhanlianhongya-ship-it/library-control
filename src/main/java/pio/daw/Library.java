package pio.daw;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controla los accesos a la biblioteca
 */
public class Library implements Controlable {
    
    // Mapa de usuarios: id -> User
    private Map<String, User> users;

    /**
     * Crea Library desde archivo de registros
     * @param path Ruta al archivo .txt
     * @return Library con los datos procesados
     */
    public static Library fromFile(Path path) {
        Library library = new Library();
        
        try {
            List<String> lines = Files.readAllLines(path);
            
            for (String line : lines) {
                if (line.isBlank()) continue;
                
                String[] splittedLine = line.split(";");
                if (splittedLine.length != 2) continue;
                
                String id = splittedLine[0].trim();
                String eventStr = splittedLine[1].trim();
                
                // Convertir string a EventType
                EventType e = null;
                if (eventStr.equals("ENTRADA")) {
                    e = EventType.ENTRY;
                } else if (eventStr.equals("SALIDA")) {
                    e = EventType.EXIT;
                } else {
                    continue;
                }
                
                library.registerChange(id, e);
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return library;
    }

    // Constructor privado
    private Library() {
        this.users = new HashMap<>();
    }

    @Override
    public void registerChange(String id, EventType e) {
        User u = this.users.get(id);
        if (u == null) {
            u = new User(id);
            this.users.put(id, u);
        }
        u.registerNewEvent(e);
    }

    @Override
    public List<User> getCurrentInside() {
        List<User> insideUsers = new ArrayList<>();
        for (User u : this.users.values()) {
            if (u.isInside()) {
                insideUsers.add(u);
            }
        }
        insideUsers.sort(Comparator.comparing(User::getId));
        return insideUsers;
    }

    @Override
    public List<User> getMaxEntryUsers() {
        if (this.users.isEmpty()) return new ArrayList<>();
        
        // Buscar máximo número de entradas
        int maxEntries = 0;
        for (User u : this.users.values()) {
            if (u.getNEntries() > maxEntries) {
                maxEntries = u.getNEntries();
            }
        }
        
        // Recoger usuarios con ese máximo
        List<User> maxUsers = new ArrayList<>();
        for (User u : this.users.values()) {
            if (u.getNEntries() == maxEntries) {
                maxUsers.add(u);
            }
        }
        
        maxUsers.sort(Comparator.comparing(User::getId));
        return maxUsers;
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>(this.users.values());
        userList.sort(Comparator.comparing(User::getId));
        return userList;
    }

    @Override
    public void printResume() {
        // 1. Usuarios dentro
        System.out.println("Usuarios actualmente dentro de la biblioteca:");
        List<User> inside = getCurrentInside();
        if (inside.isEmpty()) {
            System.out.println("(ninguno)");
        } else {
            for (User u : inside) {
                System.out.println(u.getId());
            }
        }
        System.out.println();
        
        // 2. Entradas por usuario
        System.out.println("Número de entradas por usuario:");
        for (User u : getUserList()) {
            System.out.println(u.getId() + " -> " + u.getNEntries());
        }
        System.out.println();
        
        // 3. Usuario(s) con más entradas
        System.out.println("Usuario(s) con más entradas:");
        List<User> maxUsers = getMaxEntryUsers();
        for (User u : maxUsers) {
            System.out.println(u.getId());
        }
    }
}