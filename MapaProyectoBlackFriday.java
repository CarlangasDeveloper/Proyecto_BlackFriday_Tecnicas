import java.util.Random;
import java.util.Scanner;

public class MapaProyectoBlackFriday {
    public static void main(String[] args) {
        int filas = 10;
        int columnas = 30;
        char[][] mapa = new char[filas][columnas];

        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        // Generar el mapa aleatorio
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i == 0 || i == filas - 1 || j == 0 || j == columnas - 1) {
                    mapa[i][j] = '#'; // Bordes del mapa
                } else if (rand.nextDouble() < 0.3) {
                    mapa[i][j] = '#'; // Paredes (30% de probabilidad)
                } else {
                    mapa[i][j] = '-'; // Espacio en blanco (70% de probabilidad)
                }
            }
        }
        int objetosRestantes = 0;

        // Generar objetos en el mapa
        while (objetosRestantes < 5) {
            int fila = rand.nextInt(filas);
            int columna = rand.nextInt(columnas);

            if (mapa[fila][columna] == '-') {
                mapa[fila][columna] = '■'; // Representa un objeto al cual toca recolectar para ganar
                objetosRestantes++;
            }
        }

        int jugadorFila = filas / 2;
        int jugadorColumna = columnas / 2;
        mapa[jugadorFila][jugadorColumna] = '▓'; // Representa al jugador

        int puntos = 0; // Variable para almacenar los puntos del jugador
        int mov = 0; //Variable para almacenar los movimienos del jugador

        System.out.println();

        // Bucle principal del juego
        while (true) {
            // Imprimir el mapa
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    System.out.print(mapa[i][j]);
                }
                System.out.println();
            }

            System.out.println("Puntos totales: " + puntos+ "\nRestantes: "+objetosRestantes); // Mostrar los puntos
            System.out.println("Movimientos: "+mov); //Mostrar numero de movimientos
            System.out.println("Direccion: 1: Izquierda | 2: Derecha | 3: Arriba | 4: Abajo | 0: Salir");
            int movimiento = scanner.nextInt();
            System.out.println();

            if (movimiento == 0) {
                break; // Salir del juego si el jugador ingresa 0
            }

            int nuevaFila = jugadorFila;
            int nuevaColumna = jugadorColumna;

            // Procesar el movimiento del jugador
            switch (movimiento) {
                case 1:
                    nuevaColumna--;
                    break;
                case 2:
                    nuevaColumna++;
                    break;
                case 3:
                    nuevaFila--;
                    break;
                case 4:
                    nuevaFila++;
                    break;
                default:
                    System.out.println("Movimiento no válido.");
                    System.out.println();
                    continue;//repite el bucle despues de el default
            }

            // Verificar si el movimiento es válido y actualizar el mapa
            if (nuevaFila >= 0 && nuevaFila < filas && nuevaColumna >= 0 && nuevaColumna < columnas && mapa[nuevaFila][nuevaColumna] != '#') {
                if (mapa[nuevaFila][nuevaColumna] == '■') {
                    objetosRestantes--;
                    puntos++; // Incrementar puntos cuando se recoge un objeto
                }

                mapa[jugadorFila][jugadorColumna] = '-';
                mapa[nuevaFila][nuevaColumna] = '▓';
                jugadorFila = nuevaFila;
                jugadorColumna = nuevaColumna;
                mov++; //Incrementa los movimientos del jugador

                if (objetosRestantes == 0) {
                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            System.out.print(mapa[i][j]);
                        }
                        System.out.println();
                    }
                    System.out.println("¡Has recogido todos los objetos! Ganaste.");
                    break;
                }
            } else {
                System.out.println("Movimiento inválido.");
            }
        }

        System.out.println("Puntos totales: " + puntos); // Mostrar los puntos totales al final del juego
        System.out.println("¡Juego terminado!");
    }
}