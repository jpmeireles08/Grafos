import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BuscaEmProfundidade {
    public static int[] origem;
    public static int[] destino;
    public static int[] pointer;
    public static int[] td;
    public static int[] tt;
    public static int[] pai;

    public static int numVertices;
    public static int numArestas;

    public static void ler(File nomeArq) throws FileNotFoundException {
        Scanner sc = new Scanner(nomeArq);

        numVertices = sc.nextInt();
        numArestas = sc.nextInt();

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
            if (i == 0 || origem[i] != origem[i - 1]) {
                pointer[aux] = i;
                aux++;
            }
        }

        pointer[numVertices] = numArestas;

        sc.close();
    }

    static int t = 0;

    public static void busca() {
        busca(numVertices, numArestas);
    }

    public static void busca(int numVertices, int numArestas) {

        td = new int[numVertices];
        tt = new int[numVertices];
        pai = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            td[i] = 0;
            tt[i] = 0;
            pai[i] = -1;
        }

        int v = 0;

        while ((v = verticeTDZero(td)) != -1) {
            buscarEmProfundidade(v);
        }

    }

    public static void buscarEmProfundidade(int v) {
        t += 1;
        td[v] = t;

        int numVizinhos = pointer[v+1] - pointer[v];
        int[] vizinhos = new int[numVizinhos];
        for (int i = 0; i < numVizinhos; i++) {
            vizinhos[i] = destino[pointer[v] + i];
        }

        for (int i = 0; i < numVizinhos; i++) {

            if (td[vizinhos[i] - 1] == 0) {
                pai[vizinhos[i] - 1] = v;
                System.out.println("Aresta de arvore: (" + (v + 1) + " -> " + vizinhos[i] + ")");
                buscarEmProfundidade(vizinhos[i] - 1);

            } else if (tt[vizinhos[i] - 1] == 0) {
                System.out.println("Aresta de retorno: (" + (v + 1) + " -> " + vizinhos[i] + ")");
            }
        }

        t += 1;
        tt[v] = t;
    }

    public static int verticeTDZero(int[] TD) {
        for (int i = 0; i < TD.length; i++) {
            if (TD[i] == 0) {
                return i;

            }
        }
        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Insira o nome do arquivo: ");
        String nomeArquivo = sc.nextLine();

        File file = new File(nomeArquivo);
        ler(file);

        busca();

        sc.close();
    }
}
