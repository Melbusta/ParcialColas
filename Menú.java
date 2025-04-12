import java.util.Scanner;

public class Menú {
    private static Censo censo = new Censo();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opt = 0;

        do {
            mostrarMenu();
            try {
                opt = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opción válida.");
                continue;
            }

            switch (opt) {
                case 1:
                    registrarCiudadano();
                    break;
                case 2:
                    eliminarRegistro();
                    break;
                case 3:
                    censo.mostrarCenso();
                    break;
                case 4:
                    exportarDatos();
                    break;
                case 5:
                    importarDatos();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opt != 6);
    }

    private static void mostrarMenu() {
        System.out.println("=== CENSO DE MEDELLÍN ===");
        System.out.println("1. Registrar ciudadano");
        System.out.println("2. Eliminar ciudadano (atender en orden)");
        System.out.println("3. Mostrar todos los registros");
        System.out.println("4. Exportar datos a CSV");
        System.out.println("5. Importar datos desde CSV");
        System.out.println("6. Salir");
        System.out.print("Ingrese una opción: ");
    }

    private static void registrarCiudadano() {
        try {
            System.out.print("Ingrese nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Ingrese cédula: ");
            String cedula = sc.nextLine();

            System.out.print("Ingrese edad: ");
            int edad = sc.nextInt();

            System.out.print("¿Es desplazado? (true/false): ");
            boolean esDesplazado = sc.nextBoolean();

            System.out.print("Ingrese estrato (1-6): ");
            int estrato = sc.nextInt();

            Registro reg = new Registro(nombre, nombre, edad, edad, esDesplazado, estrato);
            censo.agregarRegistro(reg);
            System.out.println("Registro agregado correctamente.");
        } catch (NumberFormatException nfe) {
            System.out.println("Error en el formato de alguno de los datos. Intente nuevamente.");
        } catch (IllegalArgumentException iae) {
            System.out.println("Error de validación: " + iae.getMessage());
        }
    }

    private static void eliminarRegistro() {
        Registro reg = censo.eliminarRegistro();
        if (reg != null) {
            System.out.println("Registro eliminado: " + reg);
        } else {
            System.out.println("No hay registros para eliminar.");
        }
    }

    private static void exportarDatos() {
        System.out.print("Ingrese la ruta y nombre de archivo (por ejemplo, exportacion.csv): ");
        String ruta = sc.nextLine();
        censo.exportarAHCSV(ruta);
    }

    private static void importarDatos() {
        System.out.print("Ingrese la ruta y nombre de archivo (por ejemplo, importacion.csv): ");
        String ruta = sc.nextLine();
        censo.importarDesdeCSV(ruta);
    }
}
