package lista.dupla;

public class NoGenerico<T> {
    T dado;
    NoGenerico<T> esq, dir;

    public NoGenerico(T dado) {
        this.dado = dado;
    }

    @Override
    public String toString() {
        return dado.toString();
    }

    public T getDado() {
        return this.dado;
    }

    public NoGenerico<T> getDireita() {
        return this.dir;
    }

    public NoGenerico<T> getEsquerda() {
        return this.esq;
    }
}