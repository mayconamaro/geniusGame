package genius.sons;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Escala pentatônica de Dó
 *
 * @author maycon
 */
public class EscalaPentatonica {

    public static final int NOTA_DO = 0;
    public static final int NOTA_RE = 1;
    public static final int NOTA_MI = 2;
    public static final int NOTA_SOL = 3;
    public static final int NOTA_LA = 4;

    private final AudioInputStream[] notas;
    private final URL[] arquivos;

    /**
     * Inicializa a escala, carregando os arquivos de som
     */
    public EscalaPentatonica() {
        notas = new AudioInputStream[5];
        arquivos = new URL[5];

        arquivos[0] = getClass().getResource("/genius/sons/Nota Do.wav");
        arquivos[1] = getClass().getResource("/genius/sons/Nota Re.wav");
        arquivos[2] = getClass().getResource("/genius/sons/Nota Mi.wav");
        arquivos[3] = getClass().getResource("/genius/sons/Nota Sol.wav");
        arquivos[4] = getClass().getResource("/genius/sons/Nota La.wav");
    }

    /**
     * Toca uma nota musical da escala através de um SourceDataLine. Para
     * otimizar a experiência do usuário, as notas são sempre reproduzidas em
     * Threads próprias
     *
     * @param posicao nota a ser tocada. Utilize uma das constantes desta
     * classe.
     */
    public void tocar(int posicao) {
        new Thread(() -> {
            try {
                notas[posicao] = AudioSystem.getAudioInputStream(arquivos[posicao]);
                try (SourceDataLine sdl = AudioSystem.getSourceDataLine(notas[posicao].getFormat())) {
                    sdl.open(notas[posicao].getFormat());
                    sdl.start();
                    
                    byte[] bytesBuffer = new byte[4096];
                    int bytesRead;
                    
                    while ((bytesRead = notas[posicao].read(bytesBuffer)) != -1) {
                        sdl.write(bytesBuffer, 0, bytesRead);
                    }
                    
                    sdl.drain();
                }
                notas[posicao].close();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                System.err.println(ex.getMessage());
            }
        }).start();
    }
}
