package genius.gui;

import genius.controle.ManipuladorDeEventos;
import genius.controle.ReprodutorDeSequencias;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Modo de Jogo GENIUS REPETIR SEQUENCIA COM UNICO BOTAO
 * @author maycon
 */
public class ContarCores extends Jogo{
    
    private Integer[] seq;
    
    /**
     * Inicializa os componentes, variáveis etc.
     */
    public ContarCores() {
        super();
        inicializarComponentes();
        jogoEmAndamento = false;
    }
    
    /**
     * inicializa os componentes da interface. Por razões de segurança, 
     * este método não pode ser substituído via herança
     */
    public final void inicializarComponentes(){
        setLayout(null);
        
        inicializarBlocos();
        
        ManipuladorDeEventos<ContarCores> handler = new ManipuladorDeEventos<>(this);
        
        addKeyListener(handler);
        for(Fita ft : blocos)
            ft.addMouseListener(handler);
    }
    
    @Override
    public void novoJogo(int numeroSequencias) {
        JOptionPane.showMessageDialog(null, "Quando a sequencia terminar, selecione a cor que mais apareceu na sequencia!"
            + "\nSe acontecer de duas cores empatarem, selecione aquela que está mais à esquerda da tela de jogo :)");
        jogoEmAndamento = true;
        gerarETocarSequencia(numeroSequencias);
    }
    
    /**
     * Gera uma sequencia e a toca usando o reprodutor de sequencias.
     * @param numero quantidade de notas na sequencia
     */
    private void gerarETocarSequencia(int numero){
        Random rand = new Random();
        seq = new Integer[numero];
        
        for(int i = 0; i < numero; i++)
            seq[i] = rand.nextInt(5);
        
        ReprodutorDeSequencias rep = new ReprodutorDeSequencias(seq, blocos, true);
        rep.start();
    }
    
    /**
     * Determina qual a cor que mais apareceu na sequencia.
     * @param seq sequencia a ser analisada
     * @return identificador do bloco/fita que mais apareceu
     */
    private int corQueMaisApareceu(Integer[] seq){
        /* Usa um counting sort parcial */
       
        Integer[] contador = new Integer[5];
        
        for(int i = 0; i < 5; i++)
            contador[i] = 0;
        
        for(Integer k : seq)
            contador[k]++;
        
        /* Determina o maior valor do resultado do counting */
        int maior = contador[0];
        for(Integer l : contador){
            if(maior < l){
                maior = l;
            }
        }
        
        return maior;
    }

    @Override
    public void validar(int numFita) {
        if(numFita == corQueMaisApareceu(seq)){
            JOptionPane.showMessageDialog(null, "Parabéns :)");
            jogoEmAndamento = false;
        }else{
            JOptionPane.showMessageDialog(null, "Ops, esta não foi a que mais apareceu :/");
            jogoEmAndamento = false;
        }
    }
    
}
