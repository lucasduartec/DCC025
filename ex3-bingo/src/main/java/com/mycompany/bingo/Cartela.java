package com.mycompany.bingo;

import java.util.Random;
import java.util.Arrays;

public class Cartela {

    public String title = "";
    public int cartela[][] = new int[5][5];
    public char checks[][] = new char[5][5];

    public Cartela(String title) {
        this.title = title;
        preencheCartela();
        preencheChecks();

    }

    public void preencheCartela() {
        preencheColuna(1, 15, 0);
        preencheColuna(16, 30, 1);
        preencheColuna(31, 45, 2);
        preencheColuna(46, 60, 3);
        preencheColuna(61, 75, 4);

        cartela[2][2] = 0;
    }

    public void preencheColuna(int minimo, int maximo, int numColuna) {
        int[] coluna = new int[5];

        for (int i = 0; i < 5; i++) {

            int newNumber = getRandomNumber(minimo, maximo);

            while (containsValue(newNumber, coluna)) {
                newNumber = getRandomNumber(minimo, maximo);
            }
            coluna[i] = newNumber;
        }

        Arrays.sort(coluna);

        for (int i = 0; i < 5; i++) {
            cartela[i][numColuna] = coluna[i];
        }

        //System.out.println(Arrays.toString(coluna));
    }

    //Checa se o numero gerado não já está na cartela
    public boolean containsValue(int value, int[] coluna) {
        for (int i = 0; i < 5; i++) {
            if (coluna[i] == value) {
                return true;
            }
        }
        return false;
    }

    //Checa se cartela tem numero 
    public boolean contemNumero(int numero) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cartela[i][j] == numero) {
                    checks[i][j] = 'X';
                    return true;
                }
            }
        }
        return false;
    }

    public void preencheChecks() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                checks[i][j] = ' ';
            }
        }
    }

    public boolean vitoriaLinCol() {
        for (int i = 0; i < 5; i++) {
            if (checks[i][0] == 'X' && checks[i][1] == 'X' && checks[i][2] == 'X' && checks[i][3] == 'X' && checks[i][4] == 'X') {
                System.out.println("Vitória da " + title + "!");
                return true;
            } else if (checks[0][i] == 'X' && checks[1][i] == 'X' && checks[2][i] == 'X' && checks[3][i] == 'X' && checks[4][i] == 'X') {
                System.out.println("Vitória da " + title + "!");
                return true;
            }
        }
        return false;
    }

    public boolean vitoriaCruz() {
        if (checks[0][2] == 'X' && checks[1][2] == 'X' && checks[3][2] == 'X' && checks[4][2] == 'X' && checks[2][0] == 'X' && checks[2][1] == 'X' && checks[2][3] == 'X' && checks[2][4] == 'X') {
            return true;
        }
        return false;
    }

    public int getRandomNumber(int minimo, int maximo) {
        Random rand = new Random();
        return rand.nextInt(minimo, maximo);
    }

    public void imprimeCartela() {

        System.out.println(title);
        System.out.println("  B     I     N     G     O");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cartela[i][j] < 10) {
                    if (i == 2 && j == 2) {
                        //blank space
                        System.out.print("      ");
                    } else {
                        //add zero before numbers < 10
                        System.out.print("[" + checks[i][j] + "]" + "0" + cartela[i][j] + " ");
                    }
                } else {
                    System.out.print("[" + checks[i][j] + "]" + cartela[i][j] + " ");
                }
            }
            System.out.println();
        }

        System.out.println("");
    }

}
