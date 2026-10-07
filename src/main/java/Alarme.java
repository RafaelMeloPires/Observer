public class Alarme implements Observador {
    private boolean disparado = false;

    public void atualizar(float temperatura) {
        disparado = temperatura > 40;
    }

    public boolean isDisparado() { return disparado; }
}