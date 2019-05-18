package genius.gui;

import genius.sons.EscalaPentatonica;
import javax.swing.JPanel;

/**
 * Base para os modos de jogos de GENIUS
 * @author maycon
 */

public abstract class Jogo extends JPanel {
    
    protected boolean jogoEmAndamento;
    protected Fita blocos[];
    
    /**
     * Este método será disparado para iniciar o jogo, quando o usuário selecionar uma opção no JMenuBar.
     * @param numeroSequencias Indica o número de sequencias que o usuário deve 
     * acertar para vencer o jogo
     */
    public abstract void novoJogo(int numeroSequencias);
    
    /**
     * Método a ser disparado pelo manipulador de eventos sempre que um evento do teclado ou mouse for disparado.
     * @param numFita Número do bloco/fita que sofreu a interação
     */
    public abstract void validar(int numFita);
    
    /**
     * Inicializa o painel e as fitas, definindo cores, tamanhos, caracteres e notas 
     * musicais
     */
    protected void inicializarBlocos(){
        blocos = new Fita[5];

        blocos[0] = new Fita(1);
        blocos[0].setCorPadrao(Constantes.ANIL);
        blocos[0].setCaractereDeAtivacao('C');
        blocos[0].setNotaMusical(EscalaPentatonica.NOTA_DO);
        add(blocos[0]);

        blocos[1] = new Fita(2);
        blocos[1].setCorPadrao(Constantes.SALMAO);
        blocos[1].setCaractereDeAtivacao('V');
        blocos[1].setNotaMusical(EscalaPentatonica.NOTA_SOL);
        add(blocos[1]);

        blocos[2] = new Fita(3);
        blocos[2].setCorPadrao(Constantes.AZUL_CELESTE);
        blocos[2].setCaractereDeAtivacao('B');
        blocos[2].setNotaMusical(EscalaPentatonica.NOTA_RE);
        add(blocos[2]);

        blocos[3] = new Fita(4);
        blocos[3].setCorPadrao(Constantes.INDIGO);
        blocos[3].setCaractereDeAtivacao('N');
        blocos[3].setNotaMusical(EscalaPentatonica.NOTA_LA);
        add(blocos[3]);

        blocos[4] = new Fita(5);
        blocos[4].setCorPadrao(Constantes.TOMATE);
        blocos[4].setCaractereDeAtivacao('M');
        blocos[4].setNotaMusical(EscalaPentatonica.NOTA_MI);
        add(blocos[4]);
        
        setSize(Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);
    }
    
    /**
     * Getter de Fitas
     * @param pos identificador da fita
     * @return a fita em questão
     */
    public Fita getBlocos(int pos) {
        return blocos[pos];
    }

    public boolean isJogoEmAndamento() {
        return jogoEmAndamento;
    }
    
}
