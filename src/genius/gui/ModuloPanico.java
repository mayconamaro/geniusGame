package genius.gui;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Módulo de pânico.
 * @author maycon
 */
public class ModuloPanico extends JDialog {

    private JLabel imagem;
    
    /**
     * Inicializa os componentes, variaveis e etc;
     */
    public ModuloPanico() {
        
        inicializarComponentes();
    }
    
    /**
     * Inicializa os componentes. Por razoẽs de segurança, este método não pode ser substituído via herança.
     */
    public final void inicializarComponentes(){
        
        setLayout(null);
        
        /* Definindo imagem */
        
        imagem = new JLabel();
        imagem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("gmail.jpg"))));
        imagem.setBounds(0, 0, Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);
        imagem.addMouseListener(new Manipulador());
        add(imagem);
        
        /* Propriedades do Dialog */
        
        setSize(Constantes.LARGURA_JANELA, Constantes.ALTURA_JANELA);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);
    }
    
    /**
     * Handler para os eventos disparados pelos componentes desta classe
     */
    protected class Manipulador extends MouseAdapter {
        
        /* Para sair do módulo de pânico, clique na palavra Freedom na imagem (é um assunto de email) */
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getXOnScreen() >= 670 && e.getXOnScreen() <= 690 && e.getYOnScreen() >= 320 && e.getYOnScreen() <= 400)
                dispose();
        }
    }
}
