import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TermometroTest {

    @Test
    void displayComecaVazio() {
        Display d = new Display();
        assertEquals("", d.getUltimaLeitura());
    }

    @Test
    void alarmeComecaDesligado() {
        Alarme a = new Alarme();
        assertFalse(a.isDisparado());
    }

    @Test
    void mudarTemperaturaSemObservadoresNaoQuebra() {
        Termometro t = new Termometro();
        assertDoesNotThrow(() -> t.setTemperatura(30));
    }

    @Test
    void displayRecebeLeituraAoMudar() {
        Termometro t = new Termometro();
        Display d = new Display();
        t.adicionarObservador(d);
        t.setTemperatura(25);
        assertEquals("Temperatura atual: 25.0 graus", d.getUltimaLeitura());
    }

    @Test
    void displayGuardaApenasUltimaLeitura() {
        Termometro t = new Termometro();
        Display d = new Display();
        t.adicionarObservador(d);
        t.setTemperatura(20);
        t.setTemperatura(22);
        assertEquals("Temperatura atual: 22.0 graus", d.getUltimaLeitura());
    }

    @Test
    void alarmeDisparaAcimaDe40() {
        Termometro t = new Termometro();
        Alarme a = new Alarme();
        t.adicionarObservador(a);
        t.setTemperatura(41);
        assertTrue(a.isDisparado());
    }

    @Test
    void alarmeNaoDisparaEm40() {
        Termometro t = new Termometro();
        Alarme a = new Alarme();
        t.adicionarObservador(a);
        t.setTemperatura(40);
        assertFalse(a.isDisparado());
    }

    @Test
    void alarmeNaoDisparaAbaixoDe40() {
        Termometro t = new Termometro();
        Alarme a = new Alarme();
        t.adicionarObservador(a);
        t.setTemperatura(20);
        assertFalse(a.isDisparado());
    }

    @Test
    void alarmeDesligaQuandoTemperaturaCai() {
        Termometro t = new Termometro();
        Alarme a = new Alarme();
        t.adicionarObservador(a);
        t.setTemperatura(45);
        assertTrue(a.isDisparado());
        t.setTemperatura(30);
        assertFalse(a.isDisparado());
    }

    @Test
    void doisObservadoresSaoNotificados() {
        Termometro t = new Termometro();
        Display d = new Display();
        Alarme a = new Alarme();
        t.adicionarObservador(d);
        t.adicionarObservador(a);
        t.setTemperatura(50);
        assertEquals("Temperatura atual: 50.0 graus", d.getUltimaLeitura());
        assertTrue(a.isDisparado());
    }

    @Test
    void observadorRemovidoNaoRecebe() {
        Termometro t = new Termometro();
        Display d = new Display();
        t.adicionarObservador(d);
        t.setTemperatura(25);
        t.removerObservador(d);
        t.setTemperatura(30);
        assertEquals("Temperatura atual: 25.0 graus", d.getUltimaLeitura());
    }

    @Test
    void removerSegundoObservador() {
        Termometro t = new Termometro();
        Display d = new Display();
        Alarme a = new Alarme();
        t.adicionarObservador(d);
        t.adicionarObservador(a);
        t.setTemperatura(50);
        t.removerObservador(a);
        t.setTemperatura(10);
        assertEquals("Temperatura atual: 10.0 graus", d.getUltimaLeitura());
        assertTrue(a.isDisparado());
    }

    @Test
    void removerObservadorNaoInscritoNaoAfetaOutros() {
        Termometro t = new Termometro();
        Display inscrito = new Display();
        Display fora = new Display();
        t.adicionarObservador(inscrito);
        t.removerObservador(fora);
        t.setTemperatura(20);
        assertEquals("Temperatura atual: 20.0 graus", inscrito.getUltimaLeitura());
    }

    @Test
    void terceiroObservadorEhIgnorado() {
        Termometro t = new Termometro();
        Display d1 = new Display();
        Display d2 = new Display();
        Display d3 = new Display();
        t.adicionarObservador(d1);
        t.adicionarObservador(d2);
        t.adicionarObservador(d3);
        t.setTemperatura(22);
        assertEquals("Temperatura atual: 22.0 graus", d1.getUltimaLeitura());
        assertEquals("Temperatura atual: 22.0 graus", d2.getUltimaLeitura());
        assertEquals("", d3.getUltimaLeitura());
    }

    @Test
    void vagaLiberadaPodeSerReutilizada() {
        Termometro t = new Termometro();
        Display d1 = new Display();
        Display d2 = new Display();
        Alarme a = new Alarme();
        t.adicionarObservador(d1);
        t.adicionarObservador(a);
        t.removerObservador(d1);
        t.adicionarObservador(d2);
        t.setTemperatura(50);
        assertEquals("", d1.getUltimaLeitura());
        assertEquals("Temperatura atual: 50.0 graus", d2.getUltimaLeitura());
        assertTrue(a.isDisparado());
    }

    @Test
    void observadorRemovidoPodeVoltar() {
        Termometro t = new Termometro();
        Display d = new Display();
        t.adicionarObservador(d);
        t.setTemperatura(10);
        t.removerObservador(d);
        t.setTemperatura(20);
        t.adicionarObservador(d);
        t.setTemperatura(30);
        assertEquals("Temperatura atual: 30.0 graus", d.getUltimaLeitura());
    }
}