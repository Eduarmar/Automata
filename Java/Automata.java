import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



class Nodo {
    String partida;
    List<Integer> cuerpo;
    String firmaDigital;

    public Nodo(String partida, List<Integer> cuerpo) {
        this.partida = partida;
        this.cuerpo = cuerpo;
        this.firmaDigital = calcularFirma();
    }

    private String calcularFirma() {
        StringBuilder contenido = new StringBuilder(partida);
        for (int num : cuerpo) {
            contenido.append(" ").append(num);
        }
        return hashSHA256(contenido.toString());
    }

    private String hashSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Automata {
    public static List<Nodo> generarLista(int n, int k) {
        List<Nodo> listaNodos = new ArrayList<>();
        Random random = new Random();

        
        String tiempoActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String primeraFirma = new Nodo(tiempoActual, new ArrayList<>()).firmaDigital;
        primeraFirma = tiempoActual + " " + primeraFirma;
        
        for (int i = 0; i < n; i++) {
            String partida = (i == 0) ? primeraFirma : listaNodos.get(i - 1).firmaDigital;
            List<Integer> cuerpo = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                cuerpo.add(random.nextInt(100000) + 1);
            }
            listaNodos.add(new Nodo(partida, cuerpo));
        }

        return listaNodos;
    }

    public static void main(String[] args) {
        int[] nValues = {3, 10, 200};
        int[] kValues = {4, 200, 10};
        double[] tiemposEjecucion = new double[nValues.length];

        for (int i = 0; i < nValues.length; i++) {
            int n = nValues[i];
            int k = kValues[i];

            long inicio = System.nanoTime();
            List<Nodo> nodos = generarLista(n, k);
            long fin = System.nanoTime();

            tiemposEjecucion[i] = (fin - inicio) / 1e6;  // Convertir a milisegundos

            System.out.println("\n=== Prueba con n=" + n + ", k=" + k + " ===");
            System.out.printf("Tiempo de ejecución: %.5f ms\n", tiemposEjecucion[i]);

            for (int j = 0; j < nodos.size(); j++) {
                System.out.println("Nodo " + (j + 1) + ":");
                System.out.println("  Partida: " + nodos.get(j).partida);
                System.out.println("  Cuerpo: " + nodos.get(j).cuerpo);
                System.out.println("  Firma Digital: " + nodos.get(j).firmaDigital);
                System.out.println("-".repeat(40));
            }
        }

        // Imprimir resumen de tiempos al final
        System.out.println("\n=== Resumen de tiempos ===");
        for (int i = 0; i < tiemposEjecucion.length; i++) {
            System.out.printf("Tiempo %c: %.5f ms\n", 'a' + i, tiemposEjecucion[i]);
        }
    }
}

