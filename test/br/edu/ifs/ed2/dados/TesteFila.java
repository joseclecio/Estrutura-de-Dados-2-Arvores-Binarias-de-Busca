package br.edu.ifs.ed2.dados;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * 
 */

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

/**
 * @author Marlos Tacio Silva
 *
 */
public class TesteFila {

	/*
	 * Instancia��o de uma fila
	 */
	private Fila<Integer> fila = new Fila<Integer>();

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

		/*
		 * Efetua a limpeza da fila antes de cada um dos testes
		 */
		this.fila.limpar();
	}

	/**
	 * Test method for {@link br.edu.ifs.ed2.dados.Fila#inserir(java.lang.Object)}.
	 */
	@Test
	public void testInserir() {

		/*
		 * Teste de inser��o do primeiro item
		 */
		this.fila.inserir(1);
		assertEquals(this.fila.toString(), "1 -> *");

		/*
		 * Teste de inser��o do segundo item
		 */
		this.fila.inserir(2);
		assertEquals(this.fila.toString(), "1 -> 2 -> *");

		/*
		 * Teste de inser��o do terceiro item
		 */
		this.fila.inserir(3);
		assertEquals(this.fila.toString(), "1 -> 2 -> 3 -> *");
	}

	/**
	 * Test method for {@link br.edu.ifs.ed2.dados.Fila#obterInicio()}.
	 */
	@Test
	public void testObterInicio() {

		/*
		 * Teste de busca com a fila vazia
		 */
		assertNull(this.fila.obterInicio());

		/*
		 * Teste de in�cio com a fila contendo um elemento
		 */
		this.fila.inserir(1);

		assertEquals((int) this.fila.obterInicio(), 1);

		/*
		 * Teste de in�cio com a fila contendo mais do que um elemento
		 */
		this.fila.inserir(2);

		assertEquals((int) this.fila.obterInicio(), 1);

		this.fila.inserir(3);

		assertEquals((int) this.fila.obterInicio(), 1);

		/*
		 * Teste de in�cio com a fila sendo esvaziada
		 */
		this.fila.remover();

		assertEquals((int) this.fila.obterInicio(), 2);

		this.fila.remover();

		assertEquals((int) this.fila.obterInicio(), 3);

		this.fila.remover();

		assertNull(this.fila.obterInicio());
	}

	/**
	 * Test method for {@link br.edu.ifs.ed2.dados.Fila#remover()}.
	 */
	@Test
	public void testRemover() {

		/*
		 * Teste de remo��o com a fila vazia
		 */
		assertFalse(this.fila.remover());
		assertEquals(this.fila.toString(), "*");

		/*
		 * Teste de remo��o com a fila contendo um �nico elemento
		 */
		this.fila.inserir(1);

		assertTrue(this.fila.remover());
		assertEquals(this.fila.toString(), "*");

		/*
		 * Teste de remo��o com a fila contendo mais do que um elemento
		 */
		this.fila.inserir(1);
		this.fila.inserir(2);
		this.fila.inserir(3);

		assertTrue(this.fila.remover());
		assertEquals(this.fila.toString(), "2 -> 3 -> *");

		assertTrue(this.fila.remover());
		assertEquals(this.fila.toString(), "3 -> *");

		assertTrue(this.fila.remover());
		assertEquals(this.fila.toString(), "*");
	}

	/**
	 * Test method for {@link br.edu.ifs.ed2.dados.Fila#limpar()}.
	 */
	@Test
	public void testLimpar() {

		/*
		 * Teste de limpeza quando a fila j� est� vazia
		 */
		this.fila.limpar();

		assertEquals(this.fila.toString(), "*");
		/*
		 * Teste de limpeza quando a fila n�o est� vazia
		 */
		this.fila.inserir(1);
		this.fila.limpar();

		assertEquals(this.fila.toString(), "*");
	}
}
