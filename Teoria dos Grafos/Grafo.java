import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Grafo {
    
    public static int[] origem;
    public static int[] destino;
    public static int[] pointer;

    public static void ler (File nomeArq) throws FileNotFoundException {
        Scanner sc = new Scanner(nomeArq);

        int numVertices = sc.nextInt();
        int numArestas = sc.nextInt();
        
        origem = new int[numArestas];
        destino = new int[numArestas];

        int k = 0;
        
        while (sc.hasNext()) {
            origem[k] = sc.nextInt();
            destino[k] = sc.nextInt();
            k++;
        }

        pointer = new int[numVertices + 1];

        int aux = 0;

        for (int i = 0; i < origem.length; i++) {
            if (i == 0 || origem[i] != origem[i-1]) {
                pointer[aux] = i;
                aux++;
            }
        }
        
        pointer[numVertices] = numArestas;

        sc.close();
    }

    public static int grauSaida (int v) {
        int grau = 0;

        for (int i = 0; i < origem.length; i++) {
            if (origem[i] == v) {
                grau++;
            }
        }

        return grau;
    }

    public static int grauEntrada (int v) {
        int grau = 0;

        for (int i = 0; i < destino.length; i++) {
            if (destino[i] == v) {
                grau++;
            }
        }

        return grau;
    }

    public static int[] sucessores (int v) {
        int grau = grauSaida(v);
        int conjuntoSucessores[] = new int[grau];
        int j = 0; // Variável para incrementar a posição no vetor de sucessores

        for (int i = 0; i < origem.length; i++) {
            if (origem[i] == v) {
                conjuntoSucessores[j] = destino[i];
                j++;
            }
        }

        return conjuntoSucessores;
    }

    public static int[] predecessores (int v) {
        int grau = grauEntrada(v);
        int conjuntoPredecessores[] = new int[grau];
        int j = 0; // Variável para incrementar a posição no vetor de predecessores

        for (int i = 0; i < destino.length; i++) {
            if (destino[i] == v) {
                conjuntoPredecessores[j] = origem[i];
                j++;
            }
        }

        return conjuntoPredecessores;
    }

    public static void main (String[] args) throws FileNotFoundException {
        
        Scanner sc = new Scanner(System.in);

        System.out.print("Insira o nome do arquivo: ");
        String nomeArquivo = sc.nextLine();

        File file = new File(nomeArquivo);
        ler(file);

        System.out.print("Insira o número de um dos vértices do grafo descrito no arquivo: ");
        int v = sc.nextInt();

        System.out.println("Grau de saída: " + grauSaida(v));
        System.out.println("Grau de entrada: " + grauEntrada(v));
        System.out.println("Conjunto dos sucessores: " + Arrays.toString(sucessores(v)));
        System.out.println("Conjunto dos predecessores: " + Arrays.toString(predecessores(v)));

        sc.close();
    }

}