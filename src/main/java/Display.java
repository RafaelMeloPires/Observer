public class Display implements Observador {
    private String ultimaLeitura = "";

    public void atualizar(float temperatura) {
        ultimaLeitura = "Temperatura atual: " + temperatura + " graus";
    }

    public String getUltimaLeitura() { return ultimaLeitura; }
}