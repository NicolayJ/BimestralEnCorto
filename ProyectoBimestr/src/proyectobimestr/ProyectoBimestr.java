package proyectobimestralpro2;

import java.io.File;
import java.util.Scanner;

public class ProyectoBimestralPro2 {
    public static void main(String[] args) {
        Scanner tecla = new Scanner(System.in);
        int horario, cantidadNormal, cantidadPrefer, asist = 0, limFilas = 1000, fil = 18, colum = 7;
        double tarifaNormal, tarifaPreferencial, totalAPagar = 0, totalNormal = 0, totalPreferencial = 0;
        String nombre, respuesta, diaAsis, fechaAsis, mesAsis, boletoNormal = "", boletoPreferencial = "";

        String[] cliente = new String[limFilas];
        double[][] datos = new double[limFilas][colum];
        String[][] archivoJava = new String[fil][colum];
        do {
            fechaArchivos(archivoJava);
            System.out.println("     ARTISTAS \t\t\t| DIAS \t\t| NUM \t|\t MES \t|\t FUNCIONES \t|Normal |Prefer |");
            for (int i = 0; i < fil; i++) {
                for (int j = 0; j < colum; j++) {
                    System.out.print(archivoJava[i][j] + " \t|");
                }
                System.out.println(" ");
            }
            System.out.println(
                    "=================================================================================================================");
            System.out.print("Ingrese el nombre del cliente: ");
            nombre = tecla.nextLine();
            System.out.println(
                    "=================================================================================================================");
            System.out.println("Hola " + nombre + " a continuacion llena los datos en base al cronograma de arriba =D");
            System.out.print("Ingrese la fecha el cual desea asistir (Ejemplo: Sabado): ");
            diaAsis = tecla.nextLine();
            System.out.print("Ingrese el dia de la fecha que desea asistir (Ejemplo: 7): ");
            fechaAsis = tecla.nextLine();
            System.out.print("Ingrese el mes que desea asistir (Ejemplo: Septiembre): ");
            mesAsis = tecla.nextLine();
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------");
            System.out.println(
                    "Ingrese el horario el cual desea asistir (ingrese el numeral del horario a escoger): \n 1. Horario Normal (Empieza a partir de las 02:01 am hasta las 17:59 pm) \n 2. Horario Nocturno  (Empieza a partir de las 18:00 pm hasta las 02:00 am)");
            horario = tecla.nextInt();
            System.out.println("---------------------------------------------------------------");
            for (int i = 0; i < fil; i++) {
                for (int j = 0; j < colum; j++) {
                    if ((diaAsis.equals(archivoJava[i][1])) && (fechaAsis.equals(archivoJava[i][2]))
                            && (mesAsis.equals(archivoJava[i][3]))) {
                        boletoNormal = archivoJava[i][5];
                        boletoPreferencial = archivoJava[i][6];
                        System.out.println("\tEse dia es " + archivoJava[i][4]
                                + "\n \tEl valor del boleto normal es de: $" + boletoNormal
                                + "\n \tEl valor del boleto preferencial es de: $" + boletoPreferencial);
                        break;
                    }
                }
            }
            System.out.println("---------------------------------------------------------------");
            System.out.print("Cuantas entradas de tarifa normal desea comprar?: ");
            cantidadNormal = tecla.nextInt();
            System.out.print("Cuantas entradas de tarifa preferencial desea comprar?: ");
            cantidadPrefer = tecla.nextInt();

            // llamado de funciones de las tarifas
            tarifaNormal = tarifaNormal(diaAsis, fechaAsis, mesAsis, horario);
            tarifaPreferencial = tarifaPreferencial(diaAsis, fechaAsis, mesAsis, horario);
            totalNormal = totalNormal(tarifaNormal, cantidadNormal);
            totalPreferencial = totalPrefer(tarifaPreferencial, cantidadPrefer);
            totalAPagar = totalAPagar + (totalNormal + totalPreferencial);

            System.out.println("---------FACTURA FINAL------------------");
            System.out.println("Cliente: " + nombre);
            System.out.println("Compro entradas para el dia " + diaAsis + " " + fechaAsis + " " + mesAsis);
            System.out.println("Comprando " + cantidadNormal + " entradas normales ($" + tarifaNormal
                    + ") y dandole un costo de $" + totalNormal);
            System.out.println("Comprando " + cantidadPrefer + " entradas preferenciales ($" + tarifaPreferencial
                    + ") y dandole un costo de $" + totalPreferencial);
            System.out.println("El costo final de las entradas es de $" + totalAPagar);
            System.out.println("----------------------------------------------------------");
            System.out.println("Desea ingresar compra de un nuevo cliente? (Si/no): ");
            respuesta = tecla.next();
            tecla.nextLine();

            cliente[asist] = nombre;
            datos[asist][0] = cantidadNormal;
            datos[asist][1] = cantidadPrefer;
            datos[asist][2] = tarifaNormal;
            datos[asist][3] = tarifaPreferencial;
            datos[asist][4] = totalAPagar;

            asist = asist + 1;

        } while (respuesta.equalsIgnoreCase("Si") || respuesta.equalsIgnoreCase("si"));

        if ("No".equalsIgnoreCase(respuesta)) {
            System.out.println(
                    "--------------------------------------------------------ESTADISTICA FINAL----------------------------------------------------------------------------");
            System.out.printf(
                    "|      NOMBRE     | Cantidad Entrada Normal    | Cantidad Entrada Preferencial | Precio Entrada Normal  | Precio Entrada Preferencial  |       Total\n");
            for (int i = 0; i < asist; i++) {
                System.out.printf("| \t%-10s| \t%-23.1f| \t%-23.1f|\t%-16.1f|\t%-23.1f|\t%-20.1f\n", cliente[i],
                        datos[i][0], datos[i][1], datos[i][2], datos[i][3], datos[i][4]);
            }
        }
    }

    public static void fechaArchivos(String archivoJava[][]) {
        try {
            Scanner archivo = new Scanner(new File("fechaFija.csv"));
            int i = 0;
            while (archivo.hasNext()) {
                String[] partes = archivo.nextLine().split(";");
                archivoJava[i][0] = partes[0];
                archivoJava[i][1] = partes[1];
                archivoJava[i][2] = partes[2];
                archivoJava[i][3] = partes[3];
                archivoJava[i][4] = partes[4];
                archivoJava[i][5] = partes[5];
                archivoJava[i][6] = partes[6];
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static double tarifaNormal(String diaAsis, String fechaAsis, String mesAsis, int horario) {
        double tarifaNormal = 0;
        if (("Sabado".equals(diaAsis) && "31".equals(fechaAsis) && "Agosto".equals(mesAsis))
                || ("Martes".equals(diaAsis) && "17".equals(fechaAsis) && "Septiembre".equals(mesAsis))) {
            if (horario == 2) {
                tarifaNormal = 10.00;
            } else {
                tarifaNormal = 1.50;
            }
        } else {
            if (("Sabado".equals(diaAsis) && "14".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                    || ("Lunes".equals(diaAsis) && "16".equals(fechaAsis) && "Septiembre".equals(mesAsis))) {
                if (horario == 2) {
                    tarifaNormal = 7.00;
                } else {
                    tarifaNormal = 1.50;
                }
            } else {
                if (("Domingo".equals(diaAsis) && "1".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Viernes".equals(diaAsis) && "6".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Sabado".equals(diaAsis) && "7".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Viernes".equals(diaAsis) && "13".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Domingo".equals(diaAsis) && "15".equals(fechaAsis) && "Septiembre".equals(mesAsis))) {
                    if (horario == 2) {
                        tarifaNormal = 5.00;
                    } else {
                        tarifaNormal = 1.50;
                    }
                } else {
                    if (horario == 2) {
                        tarifaNormal = 1.50;
                    } else {
                        tarifaNormal = 1.50;
                    }
                }
            }
        }
        return tarifaNormal;
    }

    public static double tarifaPreferencial(String diaAsis, String fechaAsis, String mesAsis, int horario) {
        double tarifaPreferencial;
        if (("Sabado".equals(diaAsis) && "31".equals(fechaAsis) && "Agosto".equals(mesAsis))
                || ("Martes".equals(diaAsis) && "17".equals(fechaAsis) && "Septiembre".equals(mesAsis))) {
            if (horario == 2) {
                tarifaPreferencial = 5.00;
            } else {
                tarifaPreferencial = 0.75;
            }
        } else {
            if (("Sabado".equals(diaAsis) && "14".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                    || ("Lunes".equals(diaAsis) && "16".equals(fechaAsis) && "Septiembre".equals(mesAsis))) {
                if (horario == 2) {
                    tarifaPreferencial = 3.50;
                } else {
                    tarifaPreferencial = 0.75;
                }
            } else {
                if (("Domingo".equals(diaAsis) && "1".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Viernes".equals(diaAsis) && "6".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Sabado".equals(diaAsis) && "7".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Viernes".equals(diaAsis) && "13".equals(fechaAsis) && "Septiembre".equals(mesAsis))
                        || ("Domingo".equals(diaAsis) && "15".equals(fechaAsis) && "Septiembre".equals(mesAsis))) {
                    if (horario == 2) {
                        tarifaPreferencial = 2.50;
                    } else {
                        tarifaPreferencial = 0.75;
                    }
                } else {
                    if (horario == 2) {
                        tarifaPreferencial = 0.75;
                    } else {
                        tarifaPreferencial = 0.75;
                    }
                }
            }
        }
        return tarifaPreferencial;
    }

    public static double totalNormal(double tarifaNormal, int cantidadNormal) {
        double totalNormal;
        totalNormal = tarifaNormal * cantidadNormal;
        return totalNormal;
    }

    public static double totalPrefer(double tarifaPreferencial, int cantidadPrefer) {
        double totalPrefer;
        totalPrefer = tarifaPreferencial * cantidadPrefer;
        return totalPrefer;
    }

    public static double totalAPagar(double tarifaNormal, double tarifaPreferencial) {
        double totalAPagar;
        totalAPagar = tarifaNormal + tarifaPreferencial;
        return totalAPagar;
    }

    public static double totalPersonasAsistidas(int cantidadNormal, int cantidadPrefer) {
        int totalPersonasAsistidas;
        totalPersonasAsistidas = cantidadNormal + cantidadPrefer;
        return totalPersonasAsistidas;
    }
}
