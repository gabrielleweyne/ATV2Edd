package lista.dupla;

public class ListaDupla<T> {
    private NoGenerico<T> inicio, fim;
    private int tamanho = 0;

    public void inserirInicio(T dado) {
        NoGenerico<T> novoNodo = new NoGenerico<T>(dado);
        if (inicio == null && fim == null) {
            inicio = novoNodo;
            fim = novoNodo;
        } else {
            inicio.esq = novoNodo;
            novoNodo.dir = inicio;

            inicio = novoNodo;
        }

        tamanho++;
    }

    public void inserirFim(T dado) {
        NoGenerico<T> novoNodo = new NoGenerico<T>(dado);
        if (inicio == null && fim == null) {
            inicio = novoNodo;
            fim = novoNodo;
        } else {
            fim.dir = novoNodo;
            novoNodo.esq = fim;

            fim = novoNodo;
        }

        tamanho++;
    }

    public NoGenerico<T> pesquisar(T busca) {
        NoGenerico<T> curr = inicio;

        while (curr != null) {
            if (curr.dado.equals(busca)) {
                return curr;
            }
            curr = curr.dir;
        }

        return null;
    }

    public NoGenerico<T> pesquisar(int indice) {
        if (indice >= tamanho && indice < 0) {
            return null;
        }

        NoGenerico<T> curr = inicio;
        int sobrando = indice;

        while (curr != null && sobrando > 0) {
            curr = curr.dir;
            sobrando--;
        }

        return curr;
    }

    public boolean deletar(T busca) {
        NoGenerico<T> nodoParaDeletar = pesquisar(busca);

        if (nodoParaDeletar == null) {
            return false;
        }

        if (tamanho == 1) {
            inicio = null;
            fim = null;
        } else if (nodoParaDeletar.equals(inicio)) {
            nodoParaDeletar.dir.esq = null;
            inicio = nodoParaDeletar.dir;
        } else if (nodoParaDeletar.equals(fim)) {
            nodoParaDeletar.esq.dir = null;
            fim = nodoParaDeletar.esq;
        } else {
            nodoParaDeletar.esq.dir = nodoParaDeletar.dir;
            nodoParaDeletar.dir.esq = nodoParaDeletar.esq;
        }

        nodoParaDeletar = null;
        tamanho--;
        return true;
    }

    public NoGenerico<T> getInicio() {
        return inicio;
    }

    public NoGenerico<T> getFim() {
        return fim;
    }

    public int getTamanho() {
        return tamanho;
    }
}