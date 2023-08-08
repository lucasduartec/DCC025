package com.mycompany.velha3d;

import java.util.Scanner;

public class Velha3d {

    public static int fimDeJogo(char j1, char j2, char[][] tabuleiro1, char[][] tabuleiro2, char[][] tabuleiro3) {
        int fim = 0;

        //Checa se ainda há jogadas possíveis
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro1[i][j] == '-' || tabuleiro2[i][j] == '-' || tabuleiro3[i][j] == '-') {
                    fim = 0;
                    break;
                } else {
                    fim = -1;
                }
            }
        }

        //Verifica vitória do jogador1        
        //Tabuleiro 1
        //Horizontal
        for (int i = 0; i < 3; i++) {
            if (tabuleiro1[i][0] == j1 && tabuleiro1[i][1] == j1 && tabuleiro1[i][2] == j1) {
                fim = 1;
            }
        }
        //Vertical
        for (int j = 0; j < 3; j++) {
            if (tabuleiro1[0][j] == j1 && tabuleiro1[1][j] == j1 && tabuleiro1[2][j] == j1) {
                fim = 1;
            }
        }

        //Tabuleiro 2
        //Horizontal
        for (int i = 0; i < 3; i++) {
            if (tabuleiro2[i][0] == j1 && tabuleiro2[i][1] == j1 && tabuleiro2[i][2] == j1) {
                fim = 1;
            }
        }
        //Vertical
        for (int j = 0; j < 3; j++) {
            if (tabuleiro2[0][j] == j1 && tabuleiro2[1][j] == j1 && tabuleiro2[2][j] == j1) {
                fim = 1;
            }
        }

        //Tabuleiro 3
        //Horizontal
        for (int i = 0; i < 3; i++) {
            if (tabuleiro3[i][0] == j1 && tabuleiro3[i][1] == j1 && tabuleiro3[i][2] == j1) {
                fim = 1;
            }
        }
        //Vertical
        for (int j = 0; j < 3; j++) {
            if (tabuleiro3[0][j] == j1 && tabuleiro3[1][j] == j1 && tabuleiro3[2][j] == j1) {
                fim = 1;
            }
        }

        ///////////////////////////////////////////////////////////////////////
        //Verifica vitória do jogador2        
        //Tabuleiro 1
        //Horizontal
        for (int i = 0; i < 3; i++) {
            if (tabuleiro1[i][0] == j2 && tabuleiro1[i][1] == j2 && tabuleiro1[i][2] == j2) {
                fim = 2;
            }
        }
        //Vertical
        for (int j = 0; j < 3; j++) {
            if (tabuleiro1[0][j] == j2 && tabuleiro1[1][j] == j2 && tabuleiro1[2][j] == j2) {
                fim = 2;
            }
        }

        //Tabuleiro 2
        //Horizontal
        for (int i = 0; i < 3; i++) {
            if (tabuleiro2[i][0] == j2 && tabuleiro2[i][1] == j2 && tabuleiro2[i][2] == j2) {
                fim = 2;
            }
        }
        //Vertical
        for (int j = 0; j < 3; j++) {
            if (tabuleiro2[0][j] == j2 && tabuleiro2[1][j] == j2 && tabuleiro2[2][j] == j2) {
                fim = 2;
            }
        }

        //Tabuleiro 3
        //Horizontal
        for (int i = 0; i < 3; i++) {
            if (tabuleiro3[i][0] == j2 && tabuleiro3[i][1] == j2 && tabuleiro3[i][2] == j2) {
                fim = 2;
            }
        }
        //Vertical
        for (int j = 0; j < 3; j++) {
            if (tabuleiro3[0][j] == j2 && tabuleiro3[1][j] == j2 && tabuleiro3[2][j] == j2) {
                fim = 2;
            }
        }

        return fim;
    }

    public static void jogada(String jogador, char j, char[][] tabuleiro1, char[][] tabuleiro2, char[][] tabuleiro3) {
        Scanner teclado = new Scanner(System.in);

        int lin, col, camada;

        String entrada;

        imprimeTabuleiro(tabuleiro1, tabuleiro2, tabuleiro3);

        if (!jogador.equals("Bot")) {

            System.out.println("====> Turno do(a) " + jogador);
            System.out.println("Digite a posicao que deseja jogar no formato (linha, coluna, camada):");
            entrada = teclado.nextLine();

            lin = Character.getNumericValue(entrada.charAt(1));
            col = Character.getNumericValue(entrada.charAt(4));
            camada = Character.getNumericValue(entrada.charAt(7));

            //Valores inválidos
            while (lin < 0 || lin > 2 || col < 0 || col > 2 || camada < 0 || camada > 2) {
                System.out.println("Jogada invalida. Por favor digite outra jogada: ");
                entrada = teclado.nextLine();
                lin = Character.getNumericValue(entrada.charAt(1));
                col = Character.getNumericValue(entrada.charAt(4));
                camada = Character.getNumericValue(entrada.charAt(7));
            }
        } else {

            System.out.println("====> Turno do Bot");

            Bot bot = new Bot();

            lin = bot.getRandomNumber();
            col = bot.getRandomNumber();
            camada = bot.getRandomNumber();

        }

        //Espaço ocupado
        while ((camada == 0 && tabuleiro1[lin][col] != '-') || (camada == 1 && tabuleiro2[lin][col] != '-') || (camada == 2 && tabuleiro3[lin][col] != '-')) {
            if (!jogador.equals("Bot")) {
                System.out.println("Espaço ocupado. Por favor digite outra jogada: ");
                entrada = teclado.nextLine();
                lin = Character.getNumericValue(entrada.charAt(1));
                col = Character.getNumericValue(entrada.charAt(4));
                camada = Character.getNumericValue(entrada.charAt(7));
            } else {
                Bot bot = new Bot();

                lin = bot.getRandomNumber();
                col = bot.getRandomNumber();
                camada = bot.getRandomNumber();
            }
        }

        //Jogada
        if (camada == 0) {
            tabuleiro1[lin][col] = j;
        } else if (camada == 1) {
            tabuleiro2[lin][col] = j;
        } else {
            tabuleiro3[lin][col] = j;
        }

    }

    public static void jogo(String jogador1, String jogador2, char j1, char j2, char[][] tabuleiro1, char[][] tabuleiro2, char[][] tabuleiro3) {

        int fim = 0;

        while (fim == 0) {
            fim = fimDeJogo(j1, j2, tabuleiro1, tabuleiro2, tabuleiro3);
            if (fim != 0) {
                break;
            }
            jogada(jogador1, j1, tabuleiro1, tabuleiro2, tabuleiro3);
            fim = fimDeJogo(j1, j2, tabuleiro1, tabuleiro2, tabuleiro3);
            if (fim != 0) {
                break;
            }
            jogada(jogador2, j2, tabuleiro1, tabuleiro2, tabuleiro3);
        }

        imprimeTabuleiro(tabuleiro1, tabuleiro2, tabuleiro3);

        if (fim == -1) {
            System.out.println("Não há mais jogadas possíveis. Fim de Jogo!");
            System.exit(0);
        }

        if (fim == 1) {
            System.out.println("Parabéns " + jogador1 + "! Você ganhou!");
            System.exit(0);
        }
        if (fim == 2) {
            System.out.println("Parabéns " + jogador2 + "! Você ganhou!");
            System.exit(0);
        }

    }

    public static void imprimeTabuleiro(char[][] tabuleiro1, char[][] tabuleiro2, char[][] tabuleiro3) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro1[i][j] + " ");
            }
            System.out.print("\t");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro2[i][j] + " ");
            }
            System.out.print("\t");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro3[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void iniciaTabuleiros(char[][] tabuleiro1, char[][] tabuleiro2, char[][] tabuleiro3) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro1[i][j] = '-';
                tabuleiro2[i][j] = '-';
                tabuleiro3[i][j] = '-';
            }
        }
    }

    public static void telaInicial(char[][] tabuleiro1, char[][] tabuleiro2, char[][] tabuleiro3) {
        Scanner teclado = new Scanner(System.in);
        String entrada;
        String jogador1, jogador2;
        char j1, j2;
        System.out.println("Digite o modo de jogo(JxJ - para dois jogadores, ou JxB - para jogar contra o bot) :");
        entrada = teclado.nextLine();

        if (entrada.equals("JxJ")) {
            System.out.println("Digite o nome do jogador:");
            jogador1 = teclado.next();
            System.out.println("Digite o simbolo que deseja utilizar durante o jogo:");
            j1 = teclado.next().charAt(0);

            System.out.println("Digite o nome do jogador:");
            jogador2 = teclado.next();
            System.out.println("Digite o simbolo que deseja utilizar durante o jogo:");
            j2 = teclado.next().charAt(0);

            while (j1 == j2) {
                System.out.println(jogador2 + ", digite outro símbolo por favor: ");
                j2 = teclado.next().charAt(0);
            }
            jogo(jogador1, jogador2, j1, j2, tabuleiro1, tabuleiro2, tabuleiro3);
        } else if (entrada.equals("JxB")) {
            System.out.println("Digite o nome do jogador:");
            jogador1 = teclado.next();
            System.out.println("Digite o simbolo que deseja utilizar durante o jogo: (O) ou (X) ");
            j1 = teclado.next().charAt(0);
            while (j1 != 'O' && j1 != 'X') {
                System.out.println("Símbolo inválido, escolha um dos dois símbolos: (O) ou (X) ");
                j1 = teclado.next().charAt(0);
            }
            if (j1 == 'O') {
                j2 = 'X';
            } else {
                j2 = 'O';
            }
            jogo(jogador1, "Bot", j1, j2, tabuleiro1, tabuleiro2, tabuleiro3);
        } else {
            System.out.println("Modo de jogo inválido");
        }
    }

    public static void main(String[] args) {
        char tabuleiro1[][] = new char[3][3];
        char tabuleiro2[][] = new char[3][3];
        char tabuleiro3[][] = new char[3][3];
        iniciaTabuleiros(tabuleiro1, tabuleiro2, tabuleiro3);

        telaInicial(tabuleiro1, tabuleiro2, tabuleiro3);
    }
}
