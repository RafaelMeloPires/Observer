public class Termometro {
    private float temperatura;
    private Observador obs1;
    private Observador obs2;

    public float getTemperatura() { return temperatura; }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
        notificar();
    }

    public void adicionarObservador(Observador o) {
        if (obs1 == null) obs1 = o;
        else if (obs2 == null) obs2 = o;
    }

    public void removerObservador(Observador o) {
        if (obs1 == o) obs1 = null;
        else if (obs2 == o) obs2 = null;
    }

    private void notificar() {
        if (obs1 != null) obs1.atualizar(temperatura);
        if (obs2 != null) obs2.atualizar(temperatura);
    }
}