import java.util.Random;

/**
 * @author Adrián Las
 */
public class Bombero
{

    private Random random;
    private Posicion[] posiciones;
    private int total;

    public Bombero(int max) {
        random = new Random();
        posiciones = new Posicion[max];
        total = 0;
    }

    public Posicion getPosicion(int i) {
        if(i < total) return posiciones[i];

        else return null;
    }

    public boolean hayBombaEn(int fila, int columna) {
        for (int i = 0; i < total; i++) {
            if(posiciones[i].getFila() == fila && posiciones[i].getColumna() == columna) return true;
        }
        return false;
    }

    public void ponerBombas(int numFilas, int numColumnas, int numBombas) {
        if(numBombas >= numFilas * numColumnas) {
            todoSonBombas(numFilas, numColumnas);
        }
        else if(numBombas >= numFilas * numColumnas / 2) {
            todoSonBombas(numFilas, numColumnas);
            eliminarBombas(numFilas * numColumnas - numBombas);
        }
        else {
            ponerAlgunasBombas(numFilas, numColumnas, numBombas);
        }
    }

    private void ponerAlgunasBombas(int numFilas, int numColumnas, int numBombas) {
        for (int i = 0; i < numBombas; ) {
            int afil = random.nextInt(numFilas);
            int acol = random.nextInt(numColumnas);
            if(!hayBombaEn(afil, acol)) {
                posiciones[total] = new Posicion(afil, acol);
                total++;
                i++;
            }
        }
    }

    private void todoSonBombas(int numFilas, int numColumnas) {
        for (int f = 0; f < numFilas; f++) {
            for (int c = 0; c < numColumnas; c++) {
                posiciones[total] = new Posicion(f, c);
                total++;
            }
        }
    }

    private void eliminarBombas(int n) {
        for (int i = 0; i < n; i++) {
            eliminarBomba();
        }
    }

    private void eliminarBomba() {
        if(total > 0) {
            int num = random.nextInt(total);
            System.arraycopy(posiciones, num + 1, posiciones, num, total - num - 1);
            posiciones[total - 1] = null;
            total--;
        }
    }

    public String toString() {
        String s = "[";
        if(total > 0) {
            s += posiciones[0].toString();
        }
        for (int i = 1; i < total; i++) {
            s += ", " + posiciones[i].toString();

        }
        s += "]";
        return s;
    }
}
