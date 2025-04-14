import lista.dupla.ListaDupla;

public class Cidade {
    private String nome;
    private ListaDupla<ConexaoDireta> conexoes;

    public Cidade(String nome, ListaDupla<ConexaoDireta> conexoes) {
        this.nome = nome;
        this.conexoes = conexoes;
    }

    public String getNome() {
        return nome;
    }

    public ListaDupla<ConexaoDireta> getConexoes() {
        return conexoes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cidade) {
            return this.nome.equals(((Cidade) obj).nome);
        } else {
            return false;
        }
    }

}