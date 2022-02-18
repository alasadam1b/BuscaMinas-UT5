/**
 * @author Adrián Las
 */
public class Tablero
{

    private int numFilas;
    private int numColumnas;
    private int[][] matriz;
    private int numBombas;
    private Bombero bombero;
    private int descubiertos;
    private boolean bombasDescubiertas;

    public Tablero(int numFilas, int numColumnas, int numBombas) {
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.numBombas = Math.min(numBombas, numFilas * numColumnas);
        matriz = new int[numFilas][numColumnas];
        limpiarTablero();
        bombero = new Bombero(numFilas * numColumnas);
        bombero.ponerBombas(numFilas, numColumnas, numBombas);
        descubiertos = 0;
        bombasDescubiertas = false;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    private void limpiarTablero() {
        for (int f = 0; f < numFilas; f++) {
            for (int c = 0; c < numColumnas; c++) {
                matriz[f][c] = V.TAPADO;
            }
        }
    }

    public boolean hayBombaEn(int fila, int columna) {
        return bombero.hayBombaEn(fila, columna);
    }

    public boolean dentroLimites(int f, int c) {
        return f >= 0 && f < matriz.length && c >= 0 && c < matriz[0].length;
    }

    public void calculaBombasVecinas(int fila, int columna) {
        int calculo = 0;
        if(dentroLimites(fila, columna) && bombero.hayBombaEn(fila, columna + 1)) calculo++;
        if(dentroLimites(fila, columna) && bombero.hayBombaEn(fila, columna - 1)) calculo++;
        if(dentroLimites(fila, columna) && bombero.hayBombaEn(fila + 1, columna)) calculo++;
        if(dentroLimites(fila, columna) && bombero.hayBombaEn(fila - 1, columna)) calculo++;
        if(dentroLimites(fila, columna) && bombero.hayBombaEn(fila + 1, columna + 1)) calculo++;
        if(dentroLimites(fila, columna) && bombero.hayBombaEn(fila - 1, columna - 1)) calculo++;
        if(dentroLimites(fila, columna) && bombero.hayBombaEn(fila - 1, columna + 1)) calculo++;
        if(dentroLimites(fila, columna) && bombero.hayBombaEn(fila + 1, columna - 1)) calculo++;

        if(matriz[fila][columna] == V.TAPADO) descubiertos++;
        matriz[fila][columna] = calculo;
    }

    public void descubreBombas() {
        for (int i = 0; i < numBombas; i++) {
            matriz[bombero.getPosicion(i).getFila()][bombero.getPosicion(i).getColumna()] = V.BOMBA;
        }
        bombasDescubiertas = true;
    }

    public boolean estanDescubiertasTodasLasNoBombas() {
        return descubiertos + numBombas == numFilas * numColumnas;
    }

    public boolean haDescubiertoBombas() {
        return bombasDescubiertas;
    }

    public boolean finalizado() {
        return estanDescubiertasTodasLasNoBombas() || haDescubiertoBombas();
    }

}
