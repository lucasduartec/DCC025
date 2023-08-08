package com.mycompany.restaum;

import java.util.Scanner;
import java.util.regex.MatchResult;

public class Restaum {

    static void iniciaTabuleiro(char[][] tabuleiro) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 15; j++) {
                if (j % 2 == 0) {
                    tabuleiro[i][j] = '1';
                } else {
                    tabuleiro[i][j] = ' ';
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 15; j++) {
                if ((i < 3 && j < 6) || (i < 3 && j > 10) || (i > 5 && j < 6) || (i > 5 && j > 10)) {
                    tabuleiro[i][j] = ' ';
                }
            }
        }

        for (int i = 1; i < 8; i++) {
            tabuleiro[i][0] = (char) ((i - 1) + '0');
        }

        for (int j = 1; j < 15; j++) {
            if (j % 2 == 0) {
                tabuleiro[0][j] = (char) ((j / 2 - 1) + '0');
            }
        }

        tabuleiro[0][0] = ' ';
        tabuleiro[4][8] = '0';

    }

    static void imprimeTabuleiro(char[][] tabuleiro) {
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 15; j++) {
                System.out.print(tabuleiro[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }

    static int converteLinha(int posicao) {

        //Converte linha digitada pelo usuário para linha equivalente na matriz
        for (int i = 0; i < 7; i++) {
            if (posicao == i) {
                posicao += 1;
                break;
            }
        }
        return posicao;
    }

    static int converteColuna(int posicao) {

        //Converte coluna digitada pelo usuário para coluna equivalente na matriz
        for (int i = 0; i < 7; i++) {
            if (posicao == i) {
                posicao = 2 * i + 2;
                break;
            }
        }
        return posicao;
    }

    static void movimento(int linPeca, int colPeca, int linBranco, int colBranco, char tabuleiro[][]) {

        // Andar na horizontal
        if (linBranco == linPeca) {
            if (colBranco > colPeca) {
                if (tabuleiro[linPeca][colPeca + 2] == '1') {
                    tabuleiro[linBranco][colBranco] = '1';
                    tabuleiro[linPeca][colPeca + 2] = '0';
                    tabuleiro[linPeca][colPeca] = '0';
                }
            } else if (tabuleiro[linPeca][colPeca - 2] == '1') {
                tabuleiro[linBranco][colBranco] = '1';
                tabuleiro[linPeca][colPeca - 2] = '0';
                tabuleiro[linPeca][colPeca] = '0';
            }
        }

        // Andar na vertical
        if (colBranco == colPeca) {
            if (linBranco > linPeca) {
                if (tabuleiro[linPeca + 1][colPeca] == '1') {
                    tabuleiro[linBranco][colBranco] = '1';
                    tabuleiro[linPeca + 1][colPeca] = '0';
                    tabuleiro[linPeca][colPeca] = '0';
                }
            } else if (tabuleiro[linPeca - 1][colPeca] == '1') {
                tabuleiro[linBranco][colBranco] = '1';
                tabuleiro[linPeca - 1][colPeca] = '0';
                tabuleiro[linPeca][colPeca] = '0';
            }
        }
    }

    static int avaliaDerrota(char tabuleiro[][]) {

        int pecas = 0;
        int resultado = 0;

        for (int i = 1; i < 8; i++) {
            for (int j = 2; j < 15; j++) {
                if (tabuleiro[i][j] == '1') {
                    pecas++;
                }
            }
        }

        if (pecas > 1) {
            //derrota
            resultado = 1;
        } else if (pecas == 1) {
            //vitoria
            resultado = 2;
        }

        return resultado;
    }

    static int avaliaJogada(char tabuleiro[][]) {

        int fim = 0;

        // se tiver uma jogada possivel, então fim = 0;
        //senao recebe 1
        for (int i = 1; i < 8; i++) {
            for (int j = 2; j < 15; j++) {
                if (tabuleiro[i][j] == '1') {
                    if (i + 1 < 8 && i + 2 < 8) {
                        if (tabuleiro[i + 1][j] == '1' && tabuleiro[i + 2][j] == '0') {
                            //jogada possivel
                            fim = 0;
                            break;
                        }
                    } else if (i - 1 > 0 && i - 2 > 0) {
                        if (tabuleiro[i - 1][j] == '1' && tabuleiro[i - 2][j] == '0') {
                            //jogada possivel
                            fim = 0;
                            break;
                        }

                    } else if (j + 2 < 15 && j + 4 < 15) {
                        if (tabuleiro[i][j + 2] == '1' && tabuleiro[i][j + 4] == '0') {
                            //jogada possivel
                            fim = 0;
                            break;
                        }
                    } else if (j - 2 > 0 && j - 4 > 0) {
                        if (tabuleiro[i][j - 2] == '1' && tabuleiro[i][j - 4] == '0') {
                            //jogada possivel
                            fim = 0;
                            break;
                        }
                    } else {
                        fim = avaliaDerrota(tabuleiro);
                        break;
                    }
                }
            }
        }
        return fim;
    }

    static void jogo() {
        char[][] tabuleiro = new char[8][15];

        int linPeca = 0;
        int colPeca = 0;
        int linBranco = 0;
        int colBranco = 0;

        int fim = 0;

        iniciaTabuleiro(tabuleiro);

        while (fim == 0) {

            imprimeTabuleiro(tabuleiro);

            Scanner teclado = new Scanner(System.in);

            String input = teclado.nextLine();

            if (input.contains("(")) {
                linPeca = input.charAt(1);
                colPeca = input.charAt(4);
                linBranco = input.charAt(7);
                colBranco = input.charAt(10);
            } else if (input.contains("reiniciar")) {
                jogo();
            } else if (input.contains("sair")) {
                System.exit(0);
            }

            linPeca = Character.getNumericValue(linPeca);
            colPeca = Character.getNumericValue(colPeca);
            linBranco = Character.getNumericValue(linBranco);
            colBranco = Character.getNumericValue(colBranco);

            linPeca = converteLinha(linPeca);
            colPeca = converteColuna(colPeca);
            linBranco = converteLinha(linBranco);
            colBranco = converteColuna(colBranco);

            if (linPeca >= 1 && linPeca <= 7 && colPeca >= 2 && colPeca <= 14 && tabuleiro[linPeca][colPeca] == '1') {
                if (linBranco >= 1 && linBranco <= 7 && colBranco >= 2 && colBranco <= 14 && tabuleiro[linBranco][colBranco] == '0') {
                    movimento(linPeca, colPeca, linBranco, colBranco, tabuleiro);
                }
            }

            fim = avaliaJogada(tabuleiro);
        }

        if (fim == 1) {
            System.out.println("Não há mais jogadas possíveis :(");
        } else if (fim == 2) {
            System.out.println("Parabéns, tu ganhou!");
        }

    }

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        char j = 's';

        jogo();

        while (j != 'n' || j != 'N') {
            System.out.println("Deseja jogar novamente? (S/N)");
            j = teclado.next().charAt(0);
            if (j == 's' || j == 'S') {
                jogo();
            }
        }
        System.out.println("Fim de Jogo!");
    }
}

