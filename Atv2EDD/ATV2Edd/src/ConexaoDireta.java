public class ConexaoDireta {
    private String destino;
    private double distancia;
    private double fatorTrafego;
    private int pedagios;

    public ConexaoDireta(String destino, double distancia, double fatorTrafego, int pedagios) {
        this.destino = destino;
        this.distancia = distancia;
        this.fatorTrafego = fatorTrafego;
        this.pedagios = pedagios;
    }

    public double getTempoEntrega() {
        return this.distancia * this.fatorTrafego + (this.pedagios * 2);
    }

    public String getDestino() {
        return destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public double getFatorTrafego() {
        return fatorTrafego;
    }

    public int getPedagios() {
        return pedagios;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConexaoDireta) {
            return this.destino.equals(((ConexaoDireta) obj).destino);
        } else {
            return false;
        }
    }

}
