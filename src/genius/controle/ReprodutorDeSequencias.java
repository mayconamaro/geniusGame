package genius.controle;

import genius.gui.Fita;

/**
 * Reprodutor de sequencias GENIUS
 * @author maycon
 */
public class ReprodutorDeSequencias extends Thread {

    Integer[] seq;
    Fita[] blocos;
    Boolean sequenciaEstaSendoReproduzida;

    /**
     * Inicializa os atributos
     * @param seq Vetor com a sequencia numerica a ser tocada
     * @param blocos Vetor de fitas que serão acesas
     * @param sequenciaEstaSendoReproduzida Variavel de controle
     */
    public ReprodutorDeSequencias(Integer[] seq, Fita[] blocos, Boolean sequenciaEstaSendoReproduzida) {
        this.seq = seq;
        this.blocos = blocos;
        this.sequenciaEstaSendoReproduzida = sequenciaEstaSendoReproduzida;
    }

    @Override
    public void run (){
        sequenciaEstaSendoReproduzida = true;
        for (int posicao : seq) {
            Fita.acender(blocos[posicao]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
        sequenciaEstaSendoReproduzida = false;
    }
}
