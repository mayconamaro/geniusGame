package genius.controle;

import genius.gui.Fita;
import genius.gui.Jogo;
import genius.gui.ModuloPanico;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Manipulador de eventos para os modos de jogo GENIUS
 *
 * @author maycon
 * @param <T> Tipo de Jogo
 */
public class ManipuladorDeEventos<T extends Jogo> extends MouseAdapter implements KeyListener {

    private final T obj;

    /**
     * Inicializa a classe
     *
     * @param obj objeto do modo de jogo que receberá o processamento dos
     * eventos
     */
    public ManipuladorDeEventos(T obj) {
        this.obj = obj;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'C':
            case 'c':
                Fita.acender(obj.getBlocos(0));
                if (obj.isJogoEmAndamento()) {
                    obj.validar(0);
                }
                break;
            case 'V':
            case 'v':
                Fita.acender(obj.getBlocos(1));
                if (obj.isJogoEmAndamento()) {
                    obj.validar(1);
                }
                break;
            case 'B':
            case 'b':
                Fita.acender(obj.getBlocos(2));
                if (obj.isJogoEmAndamento()) {
                    obj.validar(2);
                }
                break;
            case 'N':
            case 'n':
                Fita.acender(obj.getBlocos(3));
                if (obj.isJogoEmAndamento()) {
                    obj.validar(3);
                }
                break;
            case 'M':
            case 'm':
                Fita.acender(obj.getBlocos(4));
                if (obj.isJogoEmAndamento()) {
                    obj.validar(4);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Fita.acender((Fita) e.getSource());

        if (obj.isJogoEmAndamento()) {
            if (e.getSource().equals(obj.getBlocos(0))) {
                obj.validar(0);
            }

            if (e.getSource().equals(obj.getBlocos(1))) {
                obj.validar(1);
            }

            if (e.getSource().equals(obj.getBlocos(2))) {
                obj.validar(2);
            }

            if (e.getSource().equals(obj.getBlocos(3))) {
                obj.validar(3);
            }

            if (e.getSource().equals(obj.getBlocos(4))) {
                obj.validar(4);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(obj.getBlocos(4)))
                if(e.getY() >= 500){
                    ModuloPanico modu = new ModuloPanico();
                    modu.setVisible(true);
                }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        //faz nada
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //faz nada
    }
}
