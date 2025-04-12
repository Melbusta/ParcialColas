import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Censo {
    private Queue<Registro> queue;
    private final String FILE_NAME = "censo.csv";

    public void censo() {
        queue = new LinkedList<>();
        cargarCenso();
    }

    public void agregarRegistro(Registro reg) {
        queue.add(reg);
        guardarCenso();
    }

    public Registro eliminarRegistro() {
        if (!queue.isEmpty()) {
            Registro reg = queue.poll();
            guardarCenso();
            return reg;
        }
        return null;
    }

    public void mostrarCenso() {
        if (queue.isEmpty()) {
            System.out.println("El censo está vacío.");
        } else {
            for (Registro reg : queue) {
                System.out.println(reg);
            }
        }
    }

    private void guardarCenso() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            bw.write("nombre,cedula,edad,auxilio,esDesplazado,estrato");
            bw.newLine();

            for (Registro reg : queue) {
                String linea = String.format("%s,%s,%d,%.2f,%s,%d",
                        reg.getNombre(),
                        reg.getCedula(),
                        reg.getEdad(),
                        reg.getAuxilio(),
                        reg.isEsDesplazado(),
                        reg.getEstrato());
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el censo: " + e.getMessage());
        }
    }

    private void cargarCenso() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 6) {
                    System.out.println("Línea con formato incorrecto: " + linea);
                    continue;
                }
                try {
                    String nombre = datos[0].trim();
                    String cedula = datos[1].trim();
                    int edad = Integer.parseInt(datos[2].trim());

                    boolean esDesplazado = Boolean.parseBoolean(datos[4].trim());
                    int estrato = Integer.parseInt(datos[5].trim());

                    Registro reg = new Registro(nombre, cedula, edad, edad, esDesplazado, estrato);
                    queue.add(reg);
                } catch (NumberFormatException nfe) {
                    System.out.println("Error al procesar la línea: " + linea + " - " + nfe.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el censo: " + e.getMessage());
        }
    }

    public void exportarAHCSV(String ruta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write("nombre,cedula,edad,auxilio,esDesplazado,estrato");
            bw.newLine();
            for (Registro reg : queue) {
                String linea = String.format("%s,%s,%d,%.2f,%s,%d",
                        reg.getNombre(),
                        reg.getCedula(),
                        reg.getEdad(),
                        reg.getAuxilio(),
                        reg.isEsDesplazado(),
                        reg.getEstrato());
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Exportación realizada con éxito en: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al exportar a CSV: " + e.getMessage());
        }
    }

    public void importarDesdeCSV(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 6) {
                    System.out.println("Línea con formato incorrecto: " + linea);
                    continue;
                }
                try {
                    String nombre = datos[0].trim();
                    String cedula = datos[1].trim();
                    int edad = Integer.parseInt(datos[2].trim());
                    boolean esDesplazado = Boolean.parseBoolean(datos[4].trim());
                    int estrato = Integer.parseInt(datos[5].trim());

                    Registro reg = new Registro(nombre, cedula, edad, edad, esDesplazado, estrato);
                    queue.add(reg);
                } catch (Exception e) {
                    System.out.println("Error al procesar la línea: " + linea + " - " + e.getMessage());
                }
            }
            guardarCenso();

            System.out.println("Importación realizada con éxito desde: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al importar desde CSV: " + e.getMessage());
        }

    }
}