package genius.gui;

import genius.sons.EscalaPentatonica;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 * Componente fita para o Jogo GENIUS
 * @author maycon
 */
public class Fita extends JPanel{
    
    private JLabel labelCaracterAtivacao;
    private char caractereDeAtivacao;
    private Color corPadrao;
    private int notaMusical;
    
    /**
     * Constrói a base da fita e define seu tamanho e posição (bounds) de acordo
     * @param numeroDoBloco Indicador de posição da fita, a partir do qual o cálculo
     * do tamanho e da posição na tela serão feitos
     */
    public Fita(int numeroDoBloco){
        super();
        inicializarComponentes();
        definirPosicao((numeroDoBloco - 1) * Constantes.LARGURA_JANELA / 5, 0);
    }
    
    /**
     * Inicializa os componentes
     */
    private void inicializarComponentes(){
        setLayout(null);
        setSize(Constantes.LARGURA_JANELA / 5, (int) (Constantes.ALTURA_JANELA));
        
        labelCaracterAtivacao = new JLabel();
        labelCaracterAtivacao.setSize(50, 50);
        labelCaracterAtivacao.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 48));
        labelCaracterAtivacao.setForeground(Color.WHITE);
        labelCaracterAtivacao.setLocation((int) getSize().getWidth() / 2, (int) getSize().getHeight() / 2);
        
        add(labelCaracterAtivacao);
    }
    
    /**
     * Utiliza o método setLocation para definir a posicao
     * @param x posicao no eixo das abcissas
     * @param y posicao no eixo das ordenadas
     */
    private void definirPosicao(int x, int y) {
        setLocation(x, y);
    }

    /**
     * Getter para o atributo caractereAtivacao
     * @return o valor do atributo
     */
    public char getCaractereDeAtivacao() {
        return caractereDeAtivacao;
    }

    /**
     * Setter para o atributo caractereAtivacao
     * @param caractereDeAtivacao valor a ser atribuido
     */
    public void setCaractereDeAtivacao(char caractereDeAtivacao) {
        this.caractereDeAtivacao = caractereDeAtivacao;
        labelCaracterAtivacao.setText(String.valueOf(caractereDeAtivacao));
    }    

    /**
     * Setter para o atributo corPadrao, atribuindo automaticamente como cor de fundo
     * @param corPadrao valor a ser atribuido
     */
    public void setCorPadrao(Color corPadrao) {
        this.corPadrao = corPadrao;
        setBackground(corPadrao);
    }

    /**
     * Getter para o atributo corPadrao
     * @return a cor padrao da fita
     */
    public Color getCorPadrao() {
        return corPadrao;
    }

    /**
     * Setter para o atributo notaMusical
     * @param notaMusical valor a ser atribuido. Utilize as constantes da classe EscalaPentatonica.
     */
    public void setNotaMusical(int notaMusical) {
        this.notaMusical = notaMusical;
    }
    
    /**
     * Getter para o atributo notaMusical
     * @return numero da nota musical conforme as contantes da classe EscalaPentatonica.
     */
    public int getNotaMusical() {
        return notaMusical;
    }
    
    /**
     * Torna o fundo de uma fita mais claro por 300 milissegundos e toca a
     * nota musical definida no objeto. Utiliza a classe Thread para que o
     * efeito de mudança do fundo e a execução da nota musical sejam desvinculados
     * do processo da interface principal.
     * @param ft Objeto de Fita que deve ser aceso
     */
    public static void acender(Fita ft){
            new EscalaPentatonica().tocar(ft.notaMusical);
            new SwingWorker(){
                @Override
                protected Void doInBackground() {
                    ft.setBackground(ft.corPadrao.brighter());
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ex) {
                        System.err.println(ex.getMessage());
                    }
                    ft.setBackground(ft.corPadrao);
                    return null;
                }
            }.execute();
    }
}
