package br.com.roberto.campominado;

import br.com.roberto.campominado.modelo.Tabuleiro;
import br.com.roberto.campominado.visao.TabuleiroConsole;

public class Applicacao {
	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);
		new TabuleiroConsole(tabuleiro);
		/*
		tabuleiro.alternarMarcacao(4, 4);
		tabuleiro.alternarMarcacao(4, 5);
		tabuleiro.abrir(3, 3);
		
		System.out.println(tabuleiro);
		*/
		
	}
}
