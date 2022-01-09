package br.com.roberto.campominado.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.roberto.campominado.excecao.ExplosaoException;

public class Campo {
	
	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>(); 
	
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		
		int deltaGeral = deltaColuna + deltaLinha;
		
		boolean vizinhoEmCruz = deltaGeral == 1 && !diagonal;
		boolean vizinhoEmDiagonal = deltaGeral == 2 && diagonal;
		
		if (vizinhoEmCruz) {
			vizinhos.add(vizinho);
			return true;
		}else if(vizinhoEmDiagonal) {
			vizinhos.add(vizinho);
			return true;
		}else {
			return false;
		}

	}
	
	void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
		}
	}
	
	boolean abrir() {
		if(!aberto && !marcado) {
			aberto = true;
			
			if(minado) {
				throw new ExplosaoException();
			}

			if(vizinhancaSegura()) {
				//Recursividade de Vizinhan�a segura
				vizinhos.forEach( v -> v.abrir());
			}
			return true;
		}else {
			return false;
		}
	}
	
	boolean vizinhancaSegura() {
		return vizinhos.stream()
				.noneMatch(v -> v.minado);
	}
	
	
	void minar() {
		minado = true;
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}
	
	

}