package pio.daw;

import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library implements Controlable {
    private Map<String,User> users;

    /**
     * Read the library register file (.txt) and create a library object
     * with the current status of the users.
     * @param path Library registry file path.
     * @return Library object.
     */
    public static Library fromFile(Path path){
        Library library = new Library();
        //TODO
        String line = null;
        String[] splittedLine = line.split(";");
        String id = splittedLine[0];
        EventType e = null;
        if(splittedLine[1].equals("ENTRADA")){
            e = EventType.ENTRY;
        }
        library.registerChange(id, e);
        return library;
    }

    private Library(){
        this.users = new HashMap<>();
    }

    //TODO
    public void registerChange(String id, EventType e){
        User u = this.users.get(id);
        if(u == null){
            u = new User(id);
        }
        u.registerNewEvent(e);
        this.users.put(id, u);
        
    }
}
