#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> origem, destino, pointer, td, tt, pai;
vector<string> arestasDoVerticeBuscado;
int numVertices, numArestas, t = 0;
int verticeBuscado;

void ler(const string &nomeArq)
{
    ifstream file(nomeArq);
    if (!file)
    {
        cerr << "Erro ao abrir o arquivo!" << endl;
        exit(1);
    }

    file >> numVertices >> numArestas;

    origem.resize(numArestas);
    destino.resize(numArestas);
    pointer.resize(numVertices + 1, 0);

    for (int i = 0; i < numArestas; i++)
        file >> origem[i] >> destino[i];

    int aux = 0;
    for (int i = 0; i < numArestas; i++)
    {
        if (i == 0 || origem[i] != origem[i - 1])
        {
            pointer[aux++] = i;
        }
    }
    pointer[numVertices] = numArestas;
}

int verticeTDZero()
{
    for (int i = 0; i < numVertices; i++)
        if (td[i] == 0)
            return i;
    return -1;
}

void buscarEmProfundidade(int v)
{
    t++;
    td[v] = t;

    int numVizinhos = pointer[v + 1] - pointer[v];
    vector<int> vizinhos(numVizinhos);

    for (int i = 0; i < numVizinhos; i++)
        vizinhos[i] = destino[pointer[v] + i];

    sort(vizinhos.begin(), vizinhos.end());

    for (int vizinho : vizinhos)
    {
        if (td[vizinho - 1] == 0)
        {
            pai[vizinho - 1] = v;
            cout << "Aresta de arvore: (" << v + 1 << " -> " << vizinho << ")\n";

            if (v == verticeBuscado - 1) {
                arestasDoVerticeBuscado.push_back("Aresta de arvore: (" + to_string(v + 1) + " -> " + to_string(vizinho) + ")");
            }

            buscarEmProfundidade(vizinho - 1);
        }
        else
        {
            if (v == verticeBuscado - 1)
            {
                if (tt[vizinho - 1] == 0)
                {
                    arestasDoVerticeBuscado.push_back("Aresta de retorno: (" + to_string(v + 1) + " -> " + to_string(vizinho) + ")");
                }
                else if (td[v] < td[vizinho - 1])
                {
                    arestasDoVerticeBuscado.push_back("Aresta de avanco: (" + to_string(v + 1) + " -> " + to_string(vizinho) + ")");
                }
                else
                {
                    arestasDoVerticeBuscado.push_back("Aresta de cruzamento: (" + to_string(v + 1) + " -> " + to_string(vizinho) + ")");
                }
            }
        }
    }

    t++;
    tt[v] = t;
}

void busca(int verticeBuscado)
{
    td.assign(numVertices, 0);
    tt.assign(numVertices, 0);
    pai.assign(numVertices, -1);
    arestasDoVerticeBuscado.clear();

    int v;
    while ((v = verticeTDZero()) != -1)
        buscarEmProfundidade(v);

    cout << "\nArestas divergentes do vertice escolhido: \n";
    for (const string &aresta : arestasDoVerticeBuscado)
        cout << aresta << endl;
}

int main()
{
    string nomeArquivo;
    cout << "Insira o nome do arquivo: ";
    cin >> nomeArquivo;

    cout << "Insira o vertice buscado:";
    cin >> verticeBuscado;

    ler(nomeArquivo);
    busca(verticeBuscado);

    return 0;
}
