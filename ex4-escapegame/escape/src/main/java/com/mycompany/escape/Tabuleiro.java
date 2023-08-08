package com.mycompany.escape;

import java.util.Random;

public class Tabuleiro {

    private char[][] tabuleiro;
    private int linJogador;
    private int colJogador;

    public Tabuleiro() {
        tabuleiro = new char[10][10];
    }

    public void getPosicaoJogador() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tabuleiro[i][j] == 'P') {
                    linJogador = i;
                    colJogador = j;
                }
            }
        }
    }

    public void posicionaBombas(int numBombas) {
        iniciaTabuleiro();
        int lin = 0;
        int col = 0;

        tabuleiro[0][0] = 'P';

        for (int i = 0; i < numBombas; i++) {
            lin = getRandomNumber();
            col = getRandomNumber();
            while (tabuleiro[lin][col] == 'B' || (lin == 0 && col == 0)) {
                lin = getRandomNumber();
                col = getRandomNumber();
            }
            tabuleiro[lin][col] = 'B';
        }

        lin = getRandomNumber();
        col = getRandomNumber();

        while (tabuleiro[lin][col] == 'B' || (lin == 0 && col == 0)) {
            lin = getRandomNumber();
            col = getRandomNumber();
        }
        tabuleiro[lin][col] = 'S';

        getPosicaoJogador();

    }

    public void iniciaTabuleiro() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    public int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(10);
    }

    public void imprimeFinal() {
        for (int i = 0; i < 10; i++) {
            System.out.print("|");
            for (int j = 0; j < 10; j++) {
                System.out.print(" " + tabuleiro[i][j] + " |");
            }
            System.out.println();

        }
    }

    public void imprime() {
        for (int i = 0; i < 10; i++) {
            System.out.print("|");
            for (int j = 0; j < 10; j++) {
                if (tabuleiro[i][j] != 'P') {
                    System.out.print("   |");
                } else {
                    System.out.print(" P |");
                }
            }
            System.out.println();

        }
    }

    public int movimento(char sentido, int quantidade) {

        int target;

        switch (sentido) {
            case 'e':
                target = colJogador - quantidade;
                for (int col = colJogador; col >= target; col--) {
                    if (tabuleiro[linJogador][col] == 'B') {
                        tabuleiro[linJogador][colJogador] = ' ';
                        tabuleiro[linJogador][col] = 'X';
                        return -1;
                    } else if (tabuleiro[linJogador][col] == 'S') {
                        tabuleiro[linJogador][colJogador] = ' ';
                        tabuleiro[linJogador][col] = 'P';
                        return 1;
                    } else {
                        tabuleiro[linJogador][colJogador] = ' ';
                        tabuleiro[linJogador][col] = 'P';
                        getPosicaoJogador();
                    }
                }
                break;
            case 'd':
                target = colJogador + quantidade;
                for (int col = colJogador; col <= target; col++) {
                    if (tabuleiro[linJogador][col] == 'B') {
                        tabuleiro[linJogador][colJogador] = ' ';
                        tabuleiro[linJogador][col] = 'X';
                        return -1;
                    } else if (tabuleiro[linJogador][col] == 'S') {
                        tabuleiro[linJogador][colJogador] = ' ';
                        tabuleiro[linJogador][col] = 'P';
                        return 1;
                    } else {
                        tabuleiro[linJogador][colJogador] = ' ';
                        tabuleiro[linJogador][col] = 'P';
                        getPosicaoJogador();
                    }
                }
                break;
            case 'c':
                target = linJogador - quantidade;
                for (int lin = linJogador; lin >= target; lin--) {
                    if (tabuleiro[lin][colJogador] == 'B') {
                        tabuleiro[linJogador][colJogador] = ' ';
                        tabuleiro[lin][colJogador] = 'X';
                        return -1;
                    } else if (tabuleiro[lin][colJogador] == 'S') {
                        tabuleiro[linJogador][colJogador] = ' ';
                        tabuleiro[lin][colJogador] = 'P';
                        return 1;
                    } else {
                        tabuleiro[linJogador][colJogador] = ' ';
                        tabuleiro[lin][colJogador] = 'P';
                        getPosicaoJogador();
                    }
                }
                break;

            case 'b':
                target = linJogador + quantidade;
                for (int lin = linJogador; lin <= target; lin++) {
                    if (tabuleiro[lin][colJogador] == 'B') {
                        tabuleiro[linJogador][colJogador] = ' ';
                        tabuleiro[lin][colJogador] = 'X';
                        return -1;
                    } else if (tabuleiro[lin][colJogador] == 'S') {
                        tabuleiro[linJogador][colJogador] = ' ';
                        tabuleiro[lin][colJogador] = 'P';
                        return 1;
                    } else {
                        tabuleiro[linJogador][colJogador] = ' ';
                        tabuleiro[lin][colJogador] = 'P';
                        getPosicaoJogador();
                    }
                }
                break;

        }

        return 0;
    }

    public boolean jogadaValida(char sentido, int quantidade) {
        switch (sentido) {
            case 'e':
                if ((colJogador - quantidade) < 0) {
                    return false;
                }
                break;

            case 'd':
                if ((colJogador + quantidade) > 10) {
                    return false;
                }
                break;

            case 'c':
                if ((linJogador - quantidade) < 0) {
                    return false;
                }
                break;

            case 'b':
                if ((linJogador + quantidade) > 10) {
                    return false;
                }
                break;
        }

        return true;
    }

}

/*


public int fimDeJogo(int targetLin, int targetCol) {
        if (tabuleiro[targetLin][targetCol] == 'B') {
            return -1;
        } else if (tabuleiro[targetLin][targetCol] == 'S') {
            return 1;
        }
        return 0;
    }





public int movimento(char sentido, int quantidade) {

        int fim = 0;

        tabuleiro[linJogador][colJogador] = ' ';

        switch (sentido) {
            case 'e':
                for(int col = colJogador; col >colJogador - quantidade; col--){
                    if(tabuleiro[linJogador][col] == 'B'){
                        tabuleiro[linJogador][col] = 'X'
                        return -1;
                    }
                }
                
                
                fim = fimDeJogo(linJogador, colJogador - quantidade);
                tabuleiro[linJogador][colJogador - quantidade] = 'P';
                break;
            case 'd':
                fim = fimDeJogo(linJogador, colJogador + quantidade);
                tabuleiro[linJogador][colJogador + quantidade] = 'P';
                break;
            case 'c':
                fim = fimDeJogo(linJogador - quantidade, colJogador);
                tabuleiro[linJogador - quantidade][colJogador] = 'P';
                break;
            case 'b':
                fim = fimDeJogo(linJogador + quantidade, colJogador);
                tabuleiro[linJogador + quantidade][colJogador] = 'P';
                break;
        }

        getPosicaoJogador();
        if (fim == -1) {
            tabuleiro[linJogador][colJogador] = 'X';
        }
        return fim;
    }*/
