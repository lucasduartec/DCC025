package com.mycompany.escape;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Escape {

    public static void jogo() {
        Scanner teclado = new Scanner(System.in);
        List<String> jogadas = new ArrayList<>();
        Tabuleiro t = new Tabuleiro();
        int dificuldade;

        System.out.println("Escolha o nível de dificuldade:\n0 - Fácil\n1 - Médio\n2 - Difícil");
        dificuldade = teclado.nextInt();

        switch (dificuldade) {
            case 0:
                t.posicionaBombas(12);
                break;
            case 1:
                t.posicionaBombas(20);
                break;
            case 2:
                t.posicionaBombas(30);
                break;
            default:
                System.out.println("Dificuldade inválida.");
                System.exit(0);
        }

        t.imprime();

        String entrada = "";
        char sentido;
        int quantidade;
        int fim = 0;

        while (fim == 0) {
            entrada = teclado.next();
            sentido = entrada.charAt(1);
            quantidade = Character.getNumericValue(entrada.charAt(3));

            while ((sentido != 'e' && sentido != 'd' && sentido != 'c' && sentido != 'b') || !t.jogadaValida(sentido, quantidade)) {
                System.out.println("Jogada inválida, digite novamente:");
                entrada = teclado.next();
                sentido = entrada.charAt(1);
                //Como o tabuleiro só tem 10 casas, o número só pode ter um dígito (0-9)
                quantidade = Character.getNumericValue(entrada.charAt(3));
            }

            jogadas.add(entrada);

            fim = t.movimento(sentido, quantidade);

            //Evita imprimir dois tabuleiros ao final do jogo
            if (fim != 0) {
                break;
            }

            t.imprime();

        }

        t.imprimeFinal();

        char jogar;

        if (fim == 1) {
            System.out.println("You won!");
        } else {
            System.out.println("Game Over!");
        }

        System.out.println("Deslocamentos realizados:");
        for (String jogada : jogadas) {
            System.out.println(jogada);
        }
        System.out.println("Deseja jogar novamente? (S/N)");
        jogar = Character.toUpperCase(teclado.next().charAt(0));
        if (jogar == 'S') {
            jogo();
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Escape Game!");
        jogo();
    }
}
