/**
 * 
 */
package br.edu.ifs.ed2.dados.hash.colisao;

import br.edu.ifs.ed2.dados.hash.Hash;
import br.edu.ifs.ed2.dados.hash.HashAberto;

/**
 * Classe que implementa a t�cnica linear de tratamento de colis�es.
 * 
 * @author Marlos Tacio Silva
 *
 */
public class ColisaoLinear<G> implements EstrategiaColisao<G> {

	/**
	 * 
	 */
	@Override
	public int obterIndice(int indiceInicial, Hash<G> tabela) {

		/*
		 * Coer��o da tabela para um hash aberto.
		 */
		HashAberto<G> hash = (HashAberto<G>) tabela;

		/*
		 * Inicializa��o do novo �ndice.
		 */
		int novoIndice = 0;

		/*
		 * Aplica��o da t�cnica de endere�amento linear para a busca de uma c�lula livre
		 * a partir do �ndice inicial.
		 */
		for (int i = 0; i < hash.getTamanho(); ++i) {

			/*
			 * C�lculo do novo �ndice
			 */
			novoIndice = 0;

			/*
			 * Se a c�lula da tabela estiver livre ent�o retornar o �ndice calculado.
			 */
			if (hash.getEstado()[novoIndice] == null) {

			}

			/*
			 * Se a c�lula da tabela estiver removida ent�o retornar o �ndice calculado.
			 */
			if (hash.getEstado()[novoIndice].equals("R")) {

			}
		}

		/*
		 * Indica��o de resolu��o de colis�o mal sucedida.
		 */
		return -1;
	}

	/**
	 * 
	 */
	@Override
	public int obterIndice(int indiceInicial, Hash<G> tabela, G conteudo) {

		/*
		 * Coer��o da tabela para um hash aberto.
		 */
		HashAberto<G> hash = (HashAberto<G>) tabela;

		/*
		 * Inicializa��o do �ndice.
		 */
		int novoIndice = 0;

		/*
		 * Aplica��o da t�cnica de endere�amento linear para a busca de um conte�do a
		 * partir do �ndice inicial.
		 */
		for (int i = 0; i < hash.getTamanho(); ++i) {

			/*
			 * 
			 */
			novoIndice = 0;

			/*
			 * Se a c�lula da tabela estiver livre ent�o a busca foi mal sucedida.
			 */
			if (hash.getEstado()[novoIndice] == null) {

			}

			/*
			 * Se a c�lula da tabela estiver removida ent�o continuar com a busca.
			 */
			if (hash.getEstado()[novoIndice].equals("R")) {

			}

			/*
			 * Se o conte�do da c�lula for igual ao do valor buscado ent�o retornar o �ndice
			 * calculado.
			 */
			if (hash.getTabela()[novoIndice].equals(conteudo)) {

			}
		}

		/*
		 * Indica��o de resolu��o de colis�o mal sucedida.
		 */
		return -1;
	}
}
