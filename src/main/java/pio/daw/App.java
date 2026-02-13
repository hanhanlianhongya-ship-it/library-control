package pio.daw;

import java.nio.file.Files;
import java.nio.file.Path;

public class App {
    /**
     * Parse the arguments of the program to get the library registry file
     * path. Exits the program if the args are not correct or the file does
     * not exists.
     * @param args program args.
     * @return Path to file if exists.
     */
   public static Path getPathFromArgs(String[] args) throws Exception {
        // Verificar número de argumentos
        if (args.length != 1) {
            throw new Exception("Argumentos inválidos. Uso: java -jar library-control.jar <archivo.txt>");
        }
        
        // Crear objeto Path con el argumento
        Path path = Path.of(args[0]);
        
        // Verificar que el archivo existe
        if (!Files.exists(path)) {
            throw new Exception("El archivo no existe: " + path.toAbsolutePath());
        }
        
        // Verificar que es un archivo .txt
        if (!path.getFileName().toString().toLowerCase().endsWith(".txt")) {
            throw new Exception("El archivo debe tener extensión .txt");
        }
        
        return path;
    }

    /**
     * Método principal de la aplicación.
     * @param args Argumentos de línea de comandos (debe ser la ruta al archivo)
     */
    public static void main(String[] args) {
        try {
            // Obtener ruta del archivo
            Path p = getPathFromArgs(args);
            
            // Crear objeto Library a partir del archivo
            Controlable controller = Library.fromFile(p);
            
            // Mostrar resumen
            controller.printResume();
            
        } catch (Exception e) {
            // Manejo de errores
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}