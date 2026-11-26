import java.util.Scanner;

public class PracticaBoveda {

    static class Desafios {
        public void presentarDesafio(int numeroDesafio) {
            switch (numeroDesafio) {
                case 1:
                    System.out.println("\n--- Desafio 1: Secuencia numerica ---");
                    System.out.println("Encontrar el siguiente numero: 2, 6, 12, 20, 30, 42, ___");
                    System.out.print("Respuesta: ");
                    break;
                case 2:
                    System.out.println("\n--- Desafio 2: El numero oculto ---");
                    System.out.println("Encontrar un numero entero que cumpla las siguientes condiciones:");
                    System.out.println("- Es mayor que 20 y menor que 100.");
                    System.out.println("- Es divisible entre 2.");
                    System.out.println("- Al dividirlo entre 5 deja residuo 2.");
                    System.out.println("- La suma de sus digitos es 8.");
                    System.out.print("Respuesta: ");
                    break;
                case 3:
                    System.out.println("\n--- Desafio 3: Problema de edades ---");
                    System.out.println("Un padre tiene actualmente el triple de la edad de su hijo. Dentro de 12 anos, el padre tendra el doble de la edad de su hijo.");
                    System.out.println("¿Cuales son las edades actuales de ambos? (escribe hijo y padre, ej. 12 36): ");
                    System.out.print("Respuesta: ");
                    break;
                case 4:
                    System.out.println("\n--- Desafio 4: El tablero logico ---");
                    System.out.println("Encontrar un numero de tres cifras que cumpla las siguientes condiciones:");
                    System.out.println("- La cifra de las centenas es el doble de la cifra de las unidades.");
                    System.out.println("- La cifra de las decenas es 3 unidades mayor que la cifra de las unidades.");
                    System.out.println("- La suma de las tres cifras es 15.");
                    System.out.print("Respuesta: ");
                    break;
                case 5:
                    System.out.println("\n--- Desafio 5: El problema de las monedas ---");
                    System.out.println("Una persona tiene 20 monedas, algunas de $5 y otras de $10. En total tiene $140.");
                    System.out.println("¿Cuantas monedas tiene de cada denominacion? (escribe monedas de $5 y de $10, ej. 12 8): ");
                    System.out.print("Respuesta: ");
                    break;
                default:
                    System.out.println("Desafio no valido.");
                    break;
            }
        }
    }

    static class Validador {
        public boolean validarRespuesta(int numeroDesafio, String respuesta) {
            if (respuesta == null) {
                return false;
            }
            String limpia = respuesta.trim().replaceAll("\\s+", " ");
            switch (numeroDesafio) {
                case 1:
                    return limpia.equals("56");
                case 2:
                    return limpia.equals("62");
                case 3:
                    return limpia.equals("12 36");
                case 4:
                    return limpia.equals("663");
                case 5:
                    return limpia.equals("12 8");
                default:
                    return false;
            }
        }
    }

    static class Puntuacion {
        private boolean[] aciertos = new boolean[5];

        public void registrarAcierto(int numeroDesafio) {
            if (numeroDesafio >= 1 && numeroDesafio <= 5) {
                aciertos[numeroDesafio - 1] = true;
            }
        }

        public boolean estaResuelto(int numeroDesafio) {
            if (numeroDesafio >= 1 && numeroDesafio <= 5) {
                return aciertos[numeroDesafio - 1];
            }
            return false;
        }

        public boolean[] getAciertos() {
            return aciertos;
        }

        public int calcularPuntuacion() {
            int puntos = 0;
            for (boolean acerto : aciertos) {
                if (acerto) {
                    puntos++;
                }
            }
            return puntos;
        }

        public void mostrarResultados() {
            int puntos = calcularPuntuacion();
            System.out.println("\n--- RESULTADO FINAL ---");
            for (int i = 0; i < aciertos.length; i++) {
                String estado = aciertos[i] ? "Correcto (1 pt)" : "Incorrecto / Pendiente (0 pts)";
                System.out.println("Desafio " + (i + 1) + ": " + estado);
            }
            System.out.println("Puntuacion obtenida: " + puntos + " de 5");
        }
    }

    static class Boveda {
        public String generarClave(boolean[] aciertos) {
            StringBuilder bits = new StringBuilder();
            int suma = 0;
            for (int i = 0; i < aciertos.length; i++) {
                if (aciertos[i]) {
                    bits.append("1");
                    suma += (i + 1) * 10;
                } else {
                    bits.append("0");
                }
            }
            return "BOV-" + bits.toString() + "-" + suma;
        }

        public boolean verificarClave(String claveIngresada, boolean[] aciertos) {
            if (claveIngresada == null) {
                return false;
            }
            return claveIngresada.trim().equals(generarClave(aciertos));
        }
    }

    static class Menu {
        private final Scanner scanner = new Scanner(System.in);
        private final Desafios desafios = new Desafios();
        private final Validador validador = new Validador();
        private final Puntuacion puntuacion = new Puntuacion();
        private final Boveda boveda = new Boveda();

        public void mostrarMenu() {
            int opcion = -1;
            while (opcion != 0) {
                System.out.println("\n=== MENU PRINCIPAL - BOVEDA ===");
                System.out.println("1. Desafio 1: Secuencia numerica");
                System.out.println("2. Desafio 2: El numero oculto");
                System.out.println("3. Desafio 3: Problema de edades");
                System.out.println("4. Desafio 4: El tablero logico");
                System.out.println("5. Desafio 5: El problema de las monedas");
                System.out.println("6. Reintentar desafios incorrectos");
                System.out.println("7. Mostrar resultado final, puntuacion y clave de acceso");
                System.out.println("8. Verificar clave de acceso");
                System.out.println("0. Salir");
                System.out.print("Elige una opcion: ");

                String entrada = scanner.nextLine().trim();
                try {
                    opcion = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("Ingresa un numero valido.");
                    continue;
                }

                switch (opcion) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        intentarDesafio(opcion);
                        break;
                    case 6:
                        reintentarIncorrectos();
                        break;
                    case 7:
                        puntuacion.mostrarResultados();
                        System.out.println("Clave de acceso: " + boveda.generarClave(puntuacion.getAciertos()));
                        break;
                    case 8:
                        System.out.print("Ingresa la clave de acceso: ");
                        String clave = scanner.nextLine();
                        if (boveda.verificarClave(clave, puntuacion.getAciertos())) {
                            System.out.println("Clave valida. Acceso concedido a la boveda.");
                        } else {
                            System.out.println("Clave incorrecta.");
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }
            }
            scanner.close();
        }

        private void intentarDesafio(int numeroDesafio) {
            if (puntuacion.estaResuelto(numeroDesafio)) {
                System.out.println("Ya respondiste correctamente el Desafio " + numeroDesafio + ".");
                return;
            }
            desafios.presentarDesafio(numeroDesafio);
            String respuesta = scanner.nextLine();
            if (validador.validarRespuesta(numeroDesafio, respuesta)) {
                puntuacion.registrarAcierto(numeroDesafio);
                System.out.println("¡Correcto! +1 punto.");
            } else {
                System.out.println("Incorrecto. Puedes reintentarlo despues.");
            }
        }

        private void reintentarIncorrectos() {
            boolean huboPendientes = false;
            for (int i = 1; i <= 5; i++) {
                if (!puntuacion.estaResuelto(i)) {
                    huboPendientes = true;
                    intentarDesafio(i);
                }
            }
            if (!huboPendientes) {
                System.out.println("Todos los desafios ya estan resueltos.");
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mostrarMenu();
    }
}

