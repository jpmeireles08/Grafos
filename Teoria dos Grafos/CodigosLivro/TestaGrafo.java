package CodigosLivro;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import CodigosLivro.GrafoLivro;

public class TestaGrafo {   
     static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static GrafoLivro.Aresta lerAresta () throws Exception {
        System.out.println("Aresta:");
        System.out.print("  V1:");
        int v1 = Integer.parseInt (in.readLine());
        System.out.print("  V2:");
        int v2 = Integer.parseInt (in.readLine());
        System.out.print("  Peso:");
        int peso = Integer.parseInt(in.readLine());
        return new GrafoLivro.Aresta (v1, v2, peso);
    }
    public static void main (String[] args) throws Exception {
        System.out.print("No. vertices:");
        int nVertices = Integer.parseInt (in.readLine());
        System.out.print("No. arestas:");
        int nArestas = Integer.parseInt(in.readLine());
        GrafoLivro grafo = new GrafoLivro(nVertices, nArestas);
        for (int i = 0; i < nArestas; i++) {
            GrafoLivro.Aresta a = lerAresta();
            // Duas chamadas porque o grafo é não direcionado
            grafo.insereAresta(a.v1(), a.v2(), a.peso());
            grafo.insereAresta(a.v2(), a.v1(), a.peso());
        }
        grafo.imprime();;
        in.readLine();
        System.out.print("Lista de adjacentes de: ");
        int v1 = Integer.parseInt(in.readLine());
        if (!grafo.listaAdjVazia(v1)) {
            GrafoLivro.Aresta adj = grafo.primeiroListaAdj(v1);
            while (adj != null) {
                System.out.println("  " + adj.v2() + " )" + adj.peso() + ")");
                adj = grafo.proxAdj(v1);            
            }
            System.out.println();
            in.readLine();
        }

        System.out.println("Retira aresta: ");
        GrafoLivro.Aresta a = lerAresta();
        if (grafo.existeAresta(a.v1(), a.v2())) {
            //Duas chamadas porque o grafo é não direcionado
            grafo.retiraAresta(a.v1(),a.v2());
            grafo.retiraAresta(a.v1(),a.v2());
        }
        else System.out.println("Aresta nao existe");
        grafo.imprime();
        in.readLine();
        System.out.print("Existe aresta: ");
        a = lerAresta();
        if (grafo.existeAresta(a.v1(), a.v2()))
            System.out.println("  Sim");
        else System.out.println("  Nao");
    }
    
}
