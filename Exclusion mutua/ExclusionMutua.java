import java.util.concurrent.Semaphore;

public class ExclusionMutua {
    // usamos un semaforo para controlar la exclusión mutua
    private static Semaphore semaforo = new Semaphore(1);

    // recurso compartido
    private static int recursoCompartido = 0;
    // seccion critica
    public static void seccionCritica(String nombre) {
        try {
            System.out.println(nombre + " está esperando para entrar a la seccion crítica.");

            // adquirir el semáforo (se bloquea si otro proceso esta adentro)
            semaforo.acquire();

            System.out.println(nombre + " ha entrado en la sección critica.");
            recursoCompartido++;
            System.out.println(nombre + " esta modificando el recurso compartido: " + recursoCompartido);

            // simulamos el procesamiento
            Thread.sleep(2000);
            
            System.out.println("valor de recurso compartido: " + recursoCompartido);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // se libera el semaforo
            System.out.println(nombre + " esta saliendo de la seccion critica.");
            semaforo.release();
        }
    }

    public static void main(String[] args) {

        // se crea los hilos que accederan al recurso compartido
        for (int i = 1; i <= 5; i++) {
            final String nombreHilo = "Hilo-" + i;
            new Thread(() -> seccionCritica(nombreHilo)).start();
        }
    }
}
