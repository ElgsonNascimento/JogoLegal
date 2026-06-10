package Telas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TelaBiblioteca extends JFrame {

    private static final String[] JOGOS_EXEMPLO = {
        "Cyber Quest", "Dragon Arena", "Space Run"
    };

    public TelaBiblioteca(String nome, interfacejogolegal.SistemaLogin sistema) {
        setTitle("JOGO LEGAL - Biblioteca");
        setSize(520, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(null);
        painel.setBackground(new Color(24, 26, 27));

        JLabel titulo = new JLabel("EM DESENVOLVIMENTO");
        }
}
