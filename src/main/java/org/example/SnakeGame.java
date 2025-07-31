package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

public class SnakeGame extends JFrame {
    // Constantes para configurar o tamanho da janela e das unidades do jogo
    private static final int LARGURA = 800; // Largura da janela
    private static final int ALTURA = 600; // Altura da janela
    private static final int TAMANHO_UNIDADE = 20; // Tamanho de cada quadrado (cobra e comida)
    private static final int UNIDADES_JOGO = (LARGURA * ALTURA) / (TAMANHO_UNIDADE * TAMANHO_UNIDADE); // Total de unidades na tela
    private static final int ATRASO = 100; // Atraso do timer em milissegundos (velocidade do jogo)

    // Listas para armazenar as coordenadas x e y da cobra
    private final ArrayList<Integer> cobraX = new ArrayList<>();
    private final ArrayList<Integer> cobraY = new ArrayList<>();
    private int comidaX; // Coordenada x da comida
    private int comidaY; // Coordenada y da comida
    private int pontuacao; // Pontuação do jogador
    private char direcao = 'D'; // Direção inicial da cobra (D = direita)
    private boolean executando = false; // Estado do jogo (true = em execução, false = game over)
    private Timer temporizador; // Temporizador para atualizar o jogo
    private Random aleatorio; // Gerador de números aleatórios para posicionar a comida

    // Construtor da classe SnakeGame
    public SnakeGame() {
        setTitle("Jogo da Cobra"); // Define o título da janela
        setSize(LARGURA, ALTURA); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setResizable(false); // Impede redimensionamento da janela

        // Adiciona o painel do jogo
        PainelJogo painelJogo = new PainelJogo();
        add(painelJogo);
        // Adiciona o ouvinte de teclado
        addKeyListener(new AdaptadorTeclado());
        setFocusable(true); // Permite que a janela receba eventos de teclado

        iniciarJogo(); // Inicia o jogo
    }

    // Método para iniciar ou reiniciar o jogo
    public void iniciarJogo() {
        cobraX.clear(); // Limpa as coordenadas x da cobra
        cobraY.clear(); // Limpa as coordenadas y da cobra
        // Posiciona a cobra no centro da tela
        cobraX.add(LARGURA / 2);
        cobraY.add(ALTURA / 2);
        pontuacao = 0; // Reseta a pontuação
        direcao = 'D'; // Define a direção inicial como direita
        executando = true; // Define o jogo como em execução
        aleatorio = new Random(); // Inicializa o gerador de números aleatórios
        gerarComida(); // Gera a primeira comida
        // Cria um temporizador que atualiza o jogo a cada ATRASO milissegundos
        temporizador = new Timer(ATRASO, e -> {
            if (executando) {
                mover(); // Move a cobra
                verificarComida(); // Verifica se a cobra comeu a comida
                verificarColisao(); // Verifica colisões
                repaint(); // Redesenha a tela
            }
        });
        temporizador.start(); // Inicia o temporizador
    }

    // Método para gerar a comida em uma posição aleatória
    public void gerarComida() {
        // Gera coordenadas aleatórias múltiplas de TAMANHO_UNIDADE
        comidaX = aleatorio.nextInt(LARGURA / TAMANHO_UNIDADE) * TAMANHO_UNIDADE;
        comidaY = aleatorio.nextInt(ALTURA / TAMANHO_UNIDADE) * TAMANHO_UNIDADE;
    }

    // Método para mover a cobra
    public void mover() {
        // Move o corpo da cobra (cada segmento assume a posição do anterior)
        for (int i = cobraX.size() - 1; i > 0; i--) {
            cobraX.set(i, cobraX.get(i - 1));
            cobraY.set(i, cobraY.get(i - 1));
        }

        // Move a cabeça da cobra com base na direção
        switch (direcao) {
            case 'C': // Cima
                cobraY.set(0, cobraY.get(0) - TAMANHO_UNIDADE);
                break;
            case 'B': // Baixo
                cobraY.set(0, cobraY.get(0) + TAMANHO_UNIDADE);
                break;
            case 'E': // Esquerda
                cobraX.set(0, cobraX.get(0) - TAMANHO_UNIDADE);
                break;
            case 'D': // Direita
                cobraX.set(0, cobraX.get(0) + TAMANHO_UNIDADE);
                break;
        }
    }

    // Método para verificar se a cobra comeu a comida
    public void verificarComida() {
        // Se a cabeça da cobra está na mesma posição da comida
        if (cobraX.get(0) == comidaX && cobraY.get(0) == comidaY) {
            // Adiciona um novo segmento ao corpo da cobra
            cobraX.add(cobraX.get(cobraX.size() + 1));
            cobraY.add(cobraY.get(cobraY.size() + 1));
            gerarComida(); // Gera uma nova comida
        }
    }

    // Método para verificar colisões
    public void verificarColisao() {
        // Verifica colisão com as bordas da tela
        if (cobraX.get(0) < 0 || cobraX.get(0) >= LARGURA || cobraY.get(0) < 0 || cobraY.get(0) >= ALTURA) {
            executando = false;
        }

        // Verifica colisão com o próprio corpo
        for (int i = 1; i < cobraX.size(); i++) {
            if (cobraX.get(0).equals(cobraX.get(i)) && cobraY.get(0).equals(cobraY.get(i))) {
                executando = false;
            }
        }

        // Se houve colisão, para o temporizador
        if (!executando) {
            temporizador.stop();
        }
    }

    // Classe interna para desenhar o jogo
    private class PainelJogo extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Preenche o fundo com preto
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, LARGURA, ALTURA);

            // Desenha a comida (vermelha)
            g.setColor(Color.RED);
            g.fillRect(comidaX, comidaY, TAMANHO_UNIDADE, TAMANHO_UNIDADE);

            // Desenha a cobra (verde)
            g.setColor(Color.GREEN);
            for (int i = 0; i < cobraX.size(); i++) {
                g.fillRect(cobraX.get(i), cobraY.get(i), TAMANHO_UNIDADE, TAMANHO_UNIDADE);
            }

            // Desenha a pontuação
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Pontuação: " + pontuacao, 10, 20);

            // Se o jogo terminou, exibe mensagem de game over
            if (!executando) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.drawString("Fim de Jogo! Pontuação: " + pontuacao, LARGURA / 4, ALTURA / 2);
                g.setFont(new Font("Arial", Font.PLAIN, 20));
                g.drawString("Pressione ESPAÇO para reiniciar", LARGURA / 4, ALTURA / 2 + 40);
            }
        }
    }

    // Classe interna para capturar eventos de teclado
    private class AdaptadorTeclado extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            // Altera a direção com base na tecla pressionada, evitando reversão direta
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direcao != 'D') direcao = 'E'; // Não permite ir diretamente para a direita
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direcao != 'E') direcao = 'D'; // Não permite ir diretamente para a esquerda
                    break;
                case KeyEvent.VK_UP:
                    if (direcao != 'B') direcao = 'C'; // Não permite ir diretamente para baixo
                    break;
                case KeyEvent.VK_DOWN:
                    if (direcao != 'C') direcao = 'B'; // Não permite ir diretamente para cima
                    break;
                case KeyEvent.VK_SPACE:
                    if (!executando) {
                        iniciarJogo(); // Reinicia o jogo se estiver em game over
                    }
                    break;
            }
        }
    }

    // Método principal para executar o jogo
    public static void main(String[] args) {
        // Executa a criação da janela na thread de despacho de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            SnakeGame jogo = new SnakeGame();
            jogo.setVisible(true);
        });
    }
}
