/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genius.gui;

import genius.controle.ManipuladorDeEventos;
import genius.controle.ReprodutorDeSequencias;
import javax.swing.JOptionPane;

/**
 * Modo de Jogo GENIUS Criar Sequencia
 *
 * @author maycon
 */
public class CriarSequencia extends Jogo {

    private int teclasRestantes;
    private Integer[] sequencia;

    /**
     * Inicializa componentes, atributos, variaveis etc;
     */
    public CriarSequencia() {
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

        ManipuladorDeEventos<CriarSequencia> handler = new ManipuladorDeEventos<>(this);
        addKeyListener(handler);
        for (Fita ft : blocos) {
            ft.addMouseListener(handler);
        }

        jogoEmAndamento = false;
    }

    @Override
    public void novoJogo(int numeroSequencias) {
        jogoEmAndamento = true;
        teclasRestantes = numeroSequencias;
        sequencia = new Integer[numeroSequencias];

        JOptionPane.showMessageDialog(null, "Digite uma sequencia de " + numeroSequencias + " notas para eu repetir :)");
    }

    @Override
    public void validar(int numFita) {

        sequencia[sequencia.length - teclasRestantes] = numFita;
        teclasRestantes--;

        if (teclasRestantes == 0) {
            JOptionPane.showMessageDialog(null, "Agora é a minha vez de repetir :)");
            ReprodutorDeSequencias rep = new ReprodutorDeSequencias(sequencia, blocos, true);
            rep.start();
            jogoEmAndamento = false;
        }
    }
}
