/**
 * @author Adrián Las
 */
public class Salida
{

    public void pinta(int[][] matriz) {
        StringBuilder lineaFormateada = new StringBuilder("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + String.format("%4c", ' '));
        int numf = 0;
        StringBuilder separador = new StringBuilder("     |");
        for (int i = 0; i < matriz[0].length; i++) {
            separador.append("---|");
        }
        separador.append("\n");
        for (int f = 0; f < matriz.length; f++) {
            if(f == 0) {
                int numc = 0;
                for (int cs = 0; cs < matriz[0].length; cs++) {
                    lineaFormateada.append(String.format("%4d", numc));
                    numc++;
                }
                lineaFormateada.append("\n");
            }
            lineaFormateada.append(separador);
            for (int c = 0; c < matriz[0].length; c++) {
                if(c == 0) {
                    lineaFormateada.append(String.format("%4d%2c", numf, '|'));
                    numf++;
                }

                if(matriz[f][c] == V.TAPADO) {
                    lineaFormateada.append(String.format("%2c%2c", ' ', '|'));
                }
                else if(matriz[f][c] >= 0 && matriz[f][c] <= 8) {
                    lineaFormateada.append(String.format("%2d%2c", matriz[f][c], '|'));
                }
                else lineaFormateada.append(String.format("%2c%2c", 'X', '|'));
            }
            lineaFormateada.append("\n");
        }
        System.out.println(lineaFormateada);
    }
}
