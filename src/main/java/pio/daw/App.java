package pio.daw;

import java.nio.file.Path;

public class App {
    /**
     * Parse the arguments of the program to get the library registry file
     * path. Exits the program if the args are not correct or the file does
     * not exists.
     * @param args program args.
     * @return Path to file if exists.
     */
    public static Path getPathFromArgs(String[] args){
        //TODO
        return null;
    }

    public static void main(String[] args) {
        Path p = getPathFromArgs(args);
        Controlable controler = Library.fromFile(p);
        controler.printResume();
    }
}
