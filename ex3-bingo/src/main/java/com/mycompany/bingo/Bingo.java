package com.mycompany.bingo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Bingo {

    public static void verificaCartelas(List<Cartela> cartelas, int numSorteado) {
        for (Cartela cartela : cartelas) {
            if (cartela.contemNumero(numSorteado)) {
                System.out.println("A " + cartela.title + " marcou!\n");
            }
        }

        for (Cartela cartela : cartelas) {
            cartela.imprimeCartela();
        }

    }

    public static void rodada(List<Cartela> cartelas, int modoJogo) {
        Scanner teclado = new Scanner(System.in);

        boolean vitoria = false;

        while (vitoria == false) {
            System.out.println("Deseja continuar? (S/N)");
            char entrada = teclado.next().charAt(0);

            entrada = Character.toLowerCase(entrada);

            switch (entrada) {
                case 's':
                    int numSorteado = getRandomNumber();
                    System.out.println("\nO número sorteado foi: " + numSorteado + "\n");
                    verificaCartelas(cartelas, numSorteado);
                    break;

                case 'n':
                    System.out.println("Fim de jogo!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Entrada inválida!");
            }

            for (Cartela cartela : cartelas) {
                if (modoJogo == 1) {
                    vitoria = cartela.vitoriaLinCol();
                } else {
                    vitoria = cartela.vitoriaCruz();
                }
            }
        }

        System.out.println("Fim de jogo!");
        System.exit(0);

    }

    public static int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(1, 76);
    }

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.println("Bem-vindo ao Bingo!\nDigite o número de jogadores:");

        int numJogadores = teclado.nextInt();

        while (numJogadores <= 0) {
            System.out.println("Número de jogadores inválido. Digite novamente:");
            numJogadores = teclado.nextInt();
        }

        List<Cartela> cartelas = new ArrayList<>();

        for (int i = 0; i < numJogadores; i++) {
            cartelas.add(new Cartela("Cartela " + i));
        }

        System.out.println("\nDigite o modo de jogo:\n1 - Linha\n2 - Cruz");
        int modoJogo = teclado.nextInt();
        while (modoJogo != 1 && modoJogo != 2) {
            System.out.println("Modo de jogo inválido. Digite novamente:");
            modoJogo = teclado.nextInt();
        }

        rodada(cartelas, modoJogo);

    }
}
