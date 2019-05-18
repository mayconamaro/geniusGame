package genius.gui;

import genius.controle.ManipuladorDeEventos;
import genius.controle.ReprodutorDeSequencias;
import java.util.Random;
import javax.swing.JOptionPane;


/**
 * Modo de jogo GENIUS RepetirSequencia
 * @author Maycon Amaro
 */
public class RepetirSequencia extends Jogo {

    private int contadorDeFitas;
    private int numeroSequencias;

    private Integer[] sequencia;
    private Boolean sequenciaEstaSendoReproduzida;
   

    /**
     * Inicializa os componentes através do método inicializarComponentes()
     */
    public RepetirSequencia() {

        super();
        inicializarComponentes();
    }

    /**
     * inicializa os componentes da interface. Por razões de segurança, 
     * este método não pode ser substituído via herança
     */
    public final void inicializarComponentes() {

        setLayout(null);

        inicializarBlocos();
        
        sequenciaEstaSendoReproduzida = false;
        jogoEmAndamento = false;

        ManipuladorDeEventos<RepetirSequencia> handler = new ManipuladorDeEventos(this);
        
        for(Fita ft : blocos)
            ft.addMouseListener(handler);
        
        addKeyListener(handler);
    }

    @Override
    public void novoJogo(int numeroSequencias) {
        JOptionPane.showMessageDialog(null, "Repita corretamente as sequencias que eu vou gerar :)");
        jogoEmAndamento = true;
        this.numeroSequencias = numeroSequencias;
        sequencia = gerarSequencia(null);
        tocarSequencia(sequencia);
    }

    /**
     * Adiciona um novo número aleatório para a sequencia. Caso o parametro seja
     * nulo, um vetor unitário será criado.
     * @param seq vetor com a sequencia atual
     * @return vetor com um novo número acrescido
     */
    private Integer[] gerarSequencia(Integer[] seq) {
        Random rand = new Random();
        Integer[] resposta;
        
        if (seq == null) {
            resposta = new Integer[1];
            resposta[0] = rand.nextInt(5);
        } else {
            resposta = new Integer[seq.length + 1];

            System.arraycopy(seq, 0, resposta, 0, seq.length);

            resposta[seq.length] = rand.nextInt(5);
        }

        return resposta;
    }

    /**
     * Utiliza o ReprodutorDeSequencias para tocar uma sequencia gerada
     * @param seq sequencia a ser tocada
     */
    private void tocarSequencia(Integer[] seq) {
        ReprodutorDeSequencias reprodutor = new ReprodutorDeSequencias(seq, blocos, sequenciaEstaSendoReproduzida);
        reprodutor.start();
        contadorDeFitas = 0;
    }

    @Override
    public void validar(int posBloco) {
        if (sequencia[contadorDeFitas++] != posBloco) {
            JOptionPane.showMessageDialog(null, "Ops, você errou!");
            sequencia = null;
            jogoEmAndamento = false;
        } else if (contadorDeFitas == sequencia.length) {
            JOptionPane.showMessageDialog(null, "Correto!");

            if (sequencia.length == numeroSequencias) {
                JOptionPane.showMessageDialog(null, "Você venceu");
                jogoEmAndamento = false;
            } else {
                sequencia = gerarSequencia(sequencia);
                tocarSequencia(sequencia);
            }
        }
    }
}