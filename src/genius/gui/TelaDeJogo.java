package genius.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Frame que contem os modos de jogo e a barra de menu
 *
 * @author maycon
 */
public class TelaDeJogo extends JFrame {

    private RepetirSequencia rp;
    private CriarSequencia cr;
    private ContarCores cn;

    private JMenuBar barraMenu;

    private JMenu menuJogo;
    private JMenu menuNovoJogo;
    private JMenu menuDificuldade;

    private JMenuItem menuItemCriarSeq;
    private JMenuItem menuItemRepetirSeq;
    private JMenuItem menuItemRepetirSeqBotaoUnico;

    private ButtonGroup grupoBotoesDificuldade;
    private JRadioButton botaoRadioFacil;
    private JRadioButton botaoRadioMedio;
    private JRadioButton botaoRadioDificil;
    private JRadioButton botaoRadioDesafiante;

    public TelaDeJogo() {

        super("Genius");
        inicializarComponentes();
    }

    private void inicializarComponentes() {

        /* Inicializando os modos de jogo */
        rp = new RepetirSequencia();
        rp.setFocusable(true);
        rp.setVisible(true);
        add(rp);

        cr = new CriarSequencia();
        cr.setVisible(true);
        cr.setFocusable(true);
        add(cr);

        cn = new ContarCores();
        cn.setFocusable(true);
        cn.setVisible(true);
        add(cn);

        /* Definindo os botoes de radio de dificuldade */
        botaoRadioFacil = new JRadioButton("Fácil");
        botaoRadioMedio = new JRadioButton("Médio");
        botaoRadioDificil = new JRadioButton("Difícil");
        botaoRadioDesafiante = new JRadioButton("Desafiante");

        /* Agrupando botoes de rádio */
        grupoBotoesDificuldade = new ButtonGroup();
        grupoBotoesDificuldade.add(botaoRadioFacil);
        grupoBotoesDificuldade.add(botaoRadioMedio);
        grupoBotoesDificuldade.add(botaoRadioDificil);
        grupoBotoesDificuldade.add(botaoRadioDesafiante);

        grupoBotoesDificuldade.setSelected(botaoRadioFacil.getModel(), true);

        /* Definindo os modos de jogo */
        menuItemCriarSeq = new JMenuItem("Criar sequencia");
        menuItemCriarSeq.addActionListener(new ManipuladorEventos());

        menuItemRepetirSeq = new JMenuItem("Repetir sequencia");
        menuItemRepetirSeq.addActionListener(new ManipuladorEventos());

        menuItemRepetirSeqBotaoUnico = new JMenuItem("Repetir sequencia com único botão");
        menuItemRepetirSeqBotaoUnico.addActionListener(new ManipuladorEventos());

        /* Definindo os menus */
        menuNovoJogo = new JMenu("Novo jogo");
        menuJogo = new JMenu("Jogo");

        menuDificuldade = new JMenu("Dificuldade");
        menuDificuldade.add(botaoRadioFacil);
        menuDificuldade.add(botaoRadioMedio);
        menuDificuldade.add(botaoRadioDificil);
        menuDificuldade.add(botaoRadioDesafiante);

        menuNovoJogo.add(menuItemCriarSeq);
        menuNovoJogo.add(menuItemRepetirSeq);
        menuNovoJogo.add(menuItemRepetirSeqBotaoUnico);

        menuJogo.add(menuNovoJogo);
        menuJogo.add(menuDificuldade);

        /* Definindo a barra de menu */
        
        barraMenu = new JMenuBar();
        barraMenu.add(menuJogo);

        setJMenuBar(barraMenu);

        /* Propriedades do Frame */
        
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);
        setLocationRelativeTo(null);
    }

    /**
     * Método principal. Define o lookAndFeel da aplicação
     *
     * @param args argumentos de linha de comando
     */
    public static void main(String[] args) {
        
        /* Define o LookAndFeel para Linux e Windows. Mac OS automaticamente define o look and feel em sua JVM */
        try {
            for (UIManager.LookAndFeelInfo linfo : UIManager.getInstalledLookAndFeels()) {
                if (linfo.getName().equals("GTK+") || linfo.getName().equals("Windows")) {
                    UIManager.setLookAndFeel(linfo.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException er) {
            System.err.println(er.getMessage());
        }
        
        /* Exibe a tela de jogo */
        TelaDeJogo tjogo = new TelaDeJogo();
        tjogo.setVisible(true);
    }

    public class ManipuladorEventos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            /* Identificando número de sequencias a partir da dificuldade */
            
            int numS = 0;
            ButtonModel bt = grupoBotoesDificuldade.getSelection();

            if (bt.equals(botaoRadioFacil.getModel())) {
                numS = 8;
            } else if (bt.equals(botaoRadioMedio.getModel())) {
                numS = 14;
            } else if (bt.equals(botaoRadioDificil.getModel())) {
                numS = 20;
            } else if (bt.equals(botaoRadioDesafiante.getModel())) {
                numS = 31;
            }

            /* Abrindo novo jogo */
            
            if (e.getSource().equals(menuItemCriarSeq)) {
                cn.setVisible(false);
                cn.setFocusable(false);
                rp.setVisible(false);
                rp.setFocusable(false);
                cr.setVisible(true);
                cr.setFocusable(true);
                cr.novoJogo(numS);
            }

            if (e.getSource().equals(menuItemRepetirSeq)) {
                cn.setVisible(false);
                cn.setFocusable(false);
                cr.setVisible(false);
                cr.setFocusable(false);
                rp.setVisible(true);
                rp.setFocusable(true);
                rp.novoJogo(numS);
            }

            if (e.getSource().equals(menuItemRepetirSeqBotaoUnico)) {
                cr.setVisible(false);
                cr.setFocusable(false);
                rp.setVisible(false);
                rp.setFocusable(false);
                cn.setVisible(true);
                cn.setFocusable(true);
                cn.novoJogo(numS);
            }
        }
    }
}
