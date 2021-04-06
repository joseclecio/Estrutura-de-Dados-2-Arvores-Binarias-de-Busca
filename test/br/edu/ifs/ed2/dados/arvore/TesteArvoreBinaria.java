/**
 *
 */
package br.edu.ifs.ed2.dados.arvore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifs.ed2.dados.no.NoTriplo;

/**
 * @author Marlos Tacio Silva
 *
 */
public class TesteArvoreBinaria {

	/*
	 *
	 */
	private ArvoreBinaria<Integer> arvore;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		/*
		 * Construção dos nós da árvore.
		 */
		NoTriplo<Integer> n1 = new NoTriplo<Integer>(37);
		NoTriplo<Integer> n2 = new NoTriplo<Integer>(20);
		NoTriplo<Integer> n3 = new NoTriplo<Integer>(80);
		NoTriplo<Integer> n4 = new NoTriplo<Integer>(10);
		NoTriplo<Integer> n5 = new NoTriplo<Integer>(30);
		NoTriplo<Integer> n6 = new NoTriplo<Integer>(59);
		NoTriplo<Integer> n7 = new NoTriplo<Integer>(100);
		NoTriplo<Integer> n9 = new NoTriplo<Integer>(5);
		NoTriplo<Integer> n10 = new NoTriplo<Integer>(60);
		NoTriplo<Integer> n11 = new NoTriplo<Integer>(90);
		NoTriplo<Integer> n12 = new NoTriplo<Integer>(180);

		/*
		 * Estabelecimento das relações.
		 */
		n1.setEsquerdo(n2);
		n1.setDireito(n3);

		n2.setEsquerdo(n4);
		n2.setDireito(n5);

		n3.setEsquerdo(n6);
		n3.setDireito(n7);

		n4.setEsquerdo(n9);

		n6.setDireito(n10);

		n7.setEsquerdo(n11);
		n7.setDireito(n12);

		/*
		 * Inicialização da árvore.
		 */
		arvore = new ArvoreBinaria<Integer>(n1);
	}

	/**
	 * Testar método para
	 * {@link br.edu.ifs.ed2.dados.arvore.ArvoreBinaria#inserir(java.lang.Comparable)}.
	 */
	@Test
	public void testInserir() {

		/*
		 * Criação e verificação de uma árvore vazia.
		 */
		arvore = new ArvoreBinaria<Integer>();
		assertEquals(arvore.toString(), "");

		/*
		 * Tentativa de inserção de um valor nulo na árvore.
		 */
		assertFalse(arvore.inserir(null));

		/*
		 * Inserção do valor 20 que deve ser alocado na raiz da árvore.
		 */
		assertTrue(arvore.inserir(20));
		assertEquals(arvore.toString(), "20");

		/*
		 * Inserção do valor 10 que deve ser alocado à esquerda da raiz da árvore.
		 */
		assertTrue(arvore.inserir(10));
		assertEquals(arvore.toString(), "20( 10 , - )");

		/*
		 * Inserção do valor 30 que deve ser alocado à direita da raiz da árvore.
		 */
		assertTrue(arvore.inserir(30));
		assertEquals(arvore.toString(), "20( 10 , 30 )");

		/*
		 * Inserção do valor 15 que deve ser alocado à direita do valor 10.
		 */
		assertTrue(arvore.inserir(15));
		assertEquals(arvore.toString(), "20( 10( - , 15 ) , 30 )");

		/*
		 * Inserção do valor 14 que deve ser alocado à esquerda do valor 15.
		 */
		assertTrue(arvore.inserir(14));
		assertEquals(arvore.toString(), "20( 10( - , 15( 14 , - ) ) , 30 )");

		/*
		 * Inserção do valor 5 que deve ser alocado à esquerda do valor 10.
		 */
		assertTrue(arvore.inserir(5));
		assertEquals(arvore.toString(), "20( 10( 5 , 15( 14 , - ) ) , 30 )");

		/*
		 * Inserção do valor 16 que deve ser alocado à direita do valor 15.
		 */
		assertTrue(arvore.inserir(16));
		assertEquals(arvore.toString(), "20( 10( 5 , 15( 14 , 16 ) ) , 30 )");
	}

	/**
	 * Testar método para
	 * {@link br.edu.ifs.ed2.dados.arvore.ArvoreBinaria#remover(java.lang.Comparable)}.
	 */
	@Test
	public void testRemover() {

		/*
		 * Verificação do estado inicial da árvore de teste.
		 */
		assertEquals(arvore.toString(), "37( 20( 10( 5 , - ) , 30 ) , 80( 59( - , 60 ) , 100( 90 , 180 ) ) )");

		/*
		 * Tentativa de remoção de um valor nulo e de um valor inexistente na árvore.
		 */
		assertFalse(arvore.remover(null));
		assertFalse(arvore.remover(1));

		/*
		 * Remoção de um nó que possui apenas um filho esquerdo.
		 */
		assertTrue(arvore.remover(10));
		assertEquals(arvore.toString(), "37( 20( 5 , 30 ) , 80( 59( - , 60 ) , 100( 90 , 180 ) ) )");

		/*
		 * Remoção de um nó que não possui filhos.
		 */
		assertTrue(arvore.remover(5));
		assertEquals(arvore.toString(), "37( 20( - , 30 ) , 80( 59( - , 60 ) , 100( 90 , 180 ) ) )");

		/*
		 * Remoção de um nó que possui apenas um filho direito.
		 */
		assertTrue(arvore.remover(20));
		assertEquals(arvore.toString(), "37( 30 , 80( 59( - , 60 ) , 100( 90 , 180 ) ) )");

		/*
		 * Remoção de um nó que possui ambos os filhos e um deles é o seu sucessor.
		 */
		assertTrue(arvore.remover(100));
		assertEquals(arvore.toString(), "37( 30 , 80( 59( - , 60 ) , 180( 90 , - ) ) )");

		/*
		 * Remoção de um nó que possui ambos os filhos, mas nenhum deles é o seu
		 * sucessor.
		 */
		assertTrue(arvore.remover(37));
		assertEquals(arvore.toString(), "59( 30 , 80( 60 , 180( 90 , - ) ) )");
	}

	/**
	 * Testar método para
	 * {@link br.edu.ifs.ed2.dados.arvore.ArvoreBinaria#buscar(java.lang.Comparable)}.
	 */
	@Test
	public void testBuscar() {

		/*
		 * Tentativa de busca de um valor nulo e de um valor inexistente na árvore.
		 */
		assertNull(arvore.buscar(null));
		assertNull(arvore.buscar(1));

		/*
		 * Busca de um valor qualquer existente na árvore.
		 */
		assertEquals(arvore.buscar(100).toString(), "100( 90 , 180 )");

	}

	/**
	 * Testar método para
	 * {@link br.edu.ifs.ed2.dados.arvore.ArvoreBinaria#maximo()}.
	 */
	@Test
	public void testMaximo() {

		/*
		 * O valor máximo na árvore vazia deve ser nulo.
		 */
		assertNull(new ArvoreBinaria<Integer>().maximo());

		/*
		 * O valor máximo na árvore de teste deve ser 180.
		 */
		assertEquals((int) arvore.maximo().getConteudo(), 180);
	}

	/**
	 * Testar método para
	 * {@link br.edu.ifs.ed2.dados.arvore.ArvoreBinaria#minimo()}.
	 */
	@Test
	public void testMinimo() {

		/*
		 * O valor mínimo na árvore vazia deve ser nulo.
		 */
		assertNull(new ArvoreBinaria<Integer>().minimo());

		/*
		 * O valor mínimo na árvore de teste deve ser 5.
		 */
		assertEquals((int) arvore.minimo().getConteudo(), 5);
	}

	/**
	 * Testar método para
	 * {@link br.edu.ifs.ed2.dados.arvore.ArvoreBinaria#sucessor(java.lang.Comparable)}.
	 */
	@Test
	public void testSucessor() {

		/*
		 * O valor do sucessor na árvore vazia deve ser nulo.
		 */
		assertNull(new ArvoreBinaria<Integer>().sucessor(59));

		/*
		 * O valor do sucessor de um valor inexistente na árvore de teste deve ser nulo.
		 */
		assertNull(arvore.sucessor(1));

		/*
		 * O valor do sucessor de 37 na árvore de teste deve ser 59.
		 */
		assertEquals((int) arvore.sucessor(37).getConteudo(), 59);

		/*
		 * O valor do sucessor de 30 na árvore de teste deve ser 37.
		 */
		assertEquals((int) arvore.sucessor(30).getConteudo(), 37);

		/*
		 * O valor do sucessor de 180 (valor máximo) na árvore de teste deve ser nulo.
		 */
		assertNull(arvore.sucessor(180));

	}

	/**
	 * Testar método para
	 * {@link br.edu.ifs.ed2.dados.arvore.ArvoreBinaria#predecessor(java.lang.Comparable)}.
	 */
	@Test
	public void testPredecessor() {

		/*
		 * O valor do predecessor na árvore vazia deve ser nulo.
		 */
		assertNull(new ArvoreBinaria<Integer>().predecessor(59));

		/*
		 * O valor do predecessor de um valor inexistente na árvore de teste deve ser
		 * nulo.
		 */
		assertNull(arvore.predecessor(1));

		/*
		 * O valor do predecessor de 37 na árvore de teste deve ser 30.
		 */
		assertEquals((int) arvore.predecessor(37).getConteudo(), 30);

		/*
		 * O valor do predecessor de 90 na árvore de teste deve ser 80.
		 */
		assertEquals((int) arvore.predecessor(90).getConteudo(), 80);

		/*
		 * O valor predecessor de 5 (valor mínimo) na árvore de teste deve ser nulo.
		 */
		assertNull(arvore.predecessor(5));
	}

	/**
	 * Testar método para
	 * {@link br.edu.ifs.ed2.dados.arvore.ArvoreBinaria#limpar()}.
	 */
	@Test
	public void testLimpar() {

		/*
		 * Antes da limpeza a raiz da árvore deve ser não nula.
		 */
		assertNotNull(arvore.getRaiz());

		/*
		 * Limpeza da árvore.
		 */
		arvore.limpar();

		/*
		 * Após a limpeza a raiz da árvore deve ser nula.
		 */
		assertNull(arvore.getRaiz());
	}
}
