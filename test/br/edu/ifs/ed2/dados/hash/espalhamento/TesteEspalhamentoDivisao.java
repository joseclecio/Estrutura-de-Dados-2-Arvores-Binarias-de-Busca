package br.edu.ifs.ed2.dados.hash.espalhamento;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * 
 * @author Marlos Tacio Silva
 *
 */
public class TesteEspalhamentoDivisao {

	/*
	 * Instancia��o de uma estrat�gia de espalhamento
	 */
	private EstrategiaEspalhamento<Integer> espalhamento = new EspalhamentoDivisao<Integer>();

	/**
	 * 
	 */
	@Test
	public void testCalcularIndice() {

		/*
		 * Defini��o do tamanho da tabela hash
		 */
		int tamanho = 8;

		/*
		 * Aplica��o da fun��o de espalhamento num conjunto de n�meros menores do que 8
		 */
		assertEquals(this.espalhamento.calcularIndice(0, tamanho), 0);
		assertEquals(this.espalhamento.calcularIndice(1, tamanho), 1);
		assertEquals(this.espalhamento.calcularIndice(2, tamanho), 2);
		assertEquals(this.espalhamento.calcularIndice(3, tamanho), 3);
		assertEquals(this.espalhamento.calcularIndice(4, tamanho), 4);
		assertEquals(this.espalhamento.calcularIndice(5, tamanho), 5);
		assertEquals(this.espalhamento.calcularIndice(6, tamanho), 6);
		assertEquals(this.espalhamento.calcularIndice(7, tamanho), 7);

		/*
		 * Aplica��o da fun��o de espalhamento num conjunto de n�meros maiores do que 7
		 * 
		 */
		assertEquals(this.espalhamento.calcularIndice(8, tamanho), 0);
		assertEquals(this.espalhamento.calcularIndice(9, tamanho), 1);
		assertEquals(this.espalhamento.calcularIndice(10, tamanho), 2);
		assertEquals(this.espalhamento.calcularIndice(11, tamanho), 3);
		assertEquals(this.espalhamento.calcularIndice(12, tamanho), 4);
		assertEquals(this.espalhamento.calcularIndice(13, tamanho), 5);
		assertEquals(this.espalhamento.calcularIndice(14, tamanho), 6);
		assertEquals(this.espalhamento.calcularIndice(15, tamanho), 7);
	}
}
