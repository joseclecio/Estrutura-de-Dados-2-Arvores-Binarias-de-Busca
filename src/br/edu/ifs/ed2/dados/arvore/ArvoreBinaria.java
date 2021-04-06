/**
 * 
 */
package br.edu.ifs.ed2.dados.arvore;

import br.edu.ifs.ed2.dados.no.NoTriplo;

/**
 * Classe que implementa as opera��es de uma �rvore bin�ria de busca.
 * 
 * @author Marlos Tacio Silva.
 *
 */
public class ArvoreBinaria<G extends Comparable<G>> {

	/*
	 * Atributos -------------------------------------------------------------------
	 */

	/**
	 * Atributo que cont�m uma refer�ncia para o n� raiz da �rvore.
	 */
	private NoTriplo<G> raiz;

	/*
	 * Construtores ----------------------------------------------------------------
	 */

	/**
	 * Construtor da classe.
	 */
	public ArvoreBinaria() {

		this(null);
	}

	/**
	 * Construtor da classe.
	 * 
	 * @param raiz N� raiz da �rvore.
	 */
	public ArvoreBinaria(NoTriplo<G> raiz) {

		this.setRaiz(raiz);
	}

	/*
	 * M�todos p�blicos ------------------------------------------------------------
	 */

	/**
	 * M�todo para a inser��o de um elemento na �rvore bin�ria de busca.
	 * 
	 * @param conteudo Conte�do a ser inserido.
	 *
	 * @return Verdadeiro, para inser��o bem sucedida, ou falso, caso contr�rio.
	 */
	public boolean inserir(G conteudo) {

		/*
		 * Verifica��o e indica��o de inser��o mal sucedida, caso o conte�do seja nulo.
		 */
		if (conteudo == null) {
			return false;
		}

		/*
		 * Estabelece o conte�do como raiz, caso a �rvore esteja vazia.
		 */
		if (this.getRaiz() == null) {
			setRaiz(new NoTriplo<>(conteudo));
			return true;
		}

		/*
		 * Estabelece dois n�s auxiliares para percorrer a �rvore em busca do ponto de
		 * inser��o.
		 */
		NoTriplo<G> aux1 = this.getRaiz();
		NoTriplo<G> aux2 = null;

		/*
		 * Percorre a �rvore bin�ria de busca a partir da raiz at� encontrar o ponto de
		 * inser��o, ou seja, o n� nulo.
		 */
		while (aux1 != null) {

			/*
			 * Iguala os n�s auxiliares.
			 */

			aux2 = aux1;

			/*
			 * Compara��o do conte�do a ser inserido com o conte�do do n� em foco.
			 * 
			 * Obt�m o filho direito, caso o conte�do a ser inserido seja maior do que ou
			 * igual ao do n� em foco, e o filho esquerdo, caso contr�rio.
			 */
			if (conteudo.compareTo(aux1.getConteudo()) >= 0) {

				aux1 = aux1.getDireito();

			}

			else {

				aux1 = aux1.getEsquerdo();

			}
		}

		/*
		 * Ap�s obter o ponto de inser��o, compara o conte�do com o do n� em foco.
		 * 
		 * Inser��o do conte�do do no filho direito, caso o conte�do seja maior do que
		 * ou igual ao do n� em foco, ou no filho esquerdo, caso contr�rio.
		 */
		if (conteudo.compareTo(aux2.getConteudo()) >= 0) {

			aux2.setDireito(new NoTriplo<>(conteudo));
		}

		else {
			aux2.setEsquerdo(new NoTriplo<>(conteudo));

		}

		return true;
	}

	/**
	 * M�todo para a remo��o de um elemento na �rvore bin�ria de busca.
	 * 
	 * @param conteudo Conte�do a ser removido.
	 *
	 * @return Verdadeiro, para remo��o bem sucedida, ou falso, caso contr�rio.
	 */
	public boolean remover(G conteudo) {

		/*
		 * Efetua uma opera��o de busca a partir do conte�do.
		 */
		NoTriplo<G> aux = this.buscar(conteudo);

		/*
		 * Verifica��o do resultado da busca e, em caso de busca mal sucedida, indica��o
		 * de inser��o mal sucedida.
		 */
		if (aux == null) {

			return false;
		}

		/*
		 * Se o n� a ser removido n�o possuir filho esquerdo, ent�o dever� ser
		 * substitu�do por seu filho direito.
		 *
		 * Se o n� a ser removido n�o possuir filho direito, ent�o dever� ser
		 * substitu�do por seu filho esquerdo.
		 */
		if (aux.getEsquerdo() == null) {

			this.substituir(aux, aux.getDireito());
		}

		else if (aux.getDireito() == null) {

			this.substituir(aux, aux.getEsquerdo());
		}

		/*
		 * Caso o n� a ser substitu�do possua ambos os filhos.
		 */
		else {

			/*
			 * Estabelecer que o n� substituto ser� o sucessor do n� a ser removido.
			 */
			NoTriplo<G> substituto = this.sucessor(aux);

			/*
			 * Se o n� substituto for o filho direito do n� a ser removido, ent�o o n� a ser
			 * removido dever� ser substitu�do pelo n� substituto. Al�m disso, o n�
			 * substituto dever� adicionar o filho esquerdo do n� a ser removido como seu
			 * filho esquerdo.
			 */
			if (aux.getDireito().equals(substituto)) {

				this.substituir(aux, substituto);
				substituto.setEsquerdo(aux.getEsquerdo());
			}

			/*
			 * Caso contr�rio, o n� substituto dever� ser substitu�do por seu filhos
			 * direito. Em seguida o n� a ser removido dever� ser substitu�do pelo n�
			 * substituto. Al�m disso, o n� substituto dever� adicionar ambos os filho do n�
			 * a ser removido como seus filhos.
			 */
			else {

				this.substituir(substituto, substituto.getDireito());
				this.substituir(aux, substituto);

				substituto.setEsquerdo(aux.getEsquerdo());
				substituto.setDireito(aux.getDireito());
			}
		}

		/*
		 * Indica��o de remo��o bem sucedida.
		 */
		return true;
	}

	/**
	 * M�todo para a busca de um elemento na �rvore bin�ria de busca.
	 * 
	 * @param conteudo Conte�do a ser buscado.
	 * 
	 * @return N� encontrado, para busca bem sucedida, ou nulo, caso contr�rio.
	 */
	public NoTriplo<G> buscar(G conteudo) {

		/*
		 * Verifica��o e indica��o de inser��o mal sucedida, caso o conte�do seja nulo.
		 */
		if (conteudo == null) {
			return null;
		}

		/*
		 * Estabelece a raiz como ponto de in�cio da busca.
		 */
		NoTriplo<G> aux = this.raiz;

		/*
		 * Percorre a �rvore bin�ria de busca enquanto n�o encontrar o ponto de
		 * inser��o, ou seja, o n� nulo.
		 */
		while (aux != null) {

			/*
			 * Se o conte�do da busca for igual ao do n� em foco, ent�o retornar esse n�.
			 * 
			 * Se o conte�do da busca for maior do que o do n� em foco, ent�o atualizar o n�
			 * em foco para o filho direito.
			 * 
			 * Se o conte�do da busca for menor do que o do n� em foco, ent�o atualizar o n�
			 * em foco para o filho esquerdo.
			 */
			if (aux.getConteudo().equals(conteudo)) {
				return aux;

			}

			else if (conteudo.compareTo(aux.getConteudo()) > 0) {

				aux=aux.getDireito();
			}

			else {
				aux=aux.getEsquerdo();

			}
		}

		/*
		 * Indica��o de busca mal sucedida.
		 */
		return null;
	}

	/**
	 * M�todo que retorna o n� com m�ximo valor na �rvore.
	 * 
	 * @return N� m�ximo da �rvore.
	 */
	public NoTriplo<G> maximo() {

		/*
		 * Chamada ao m�todo privado m�ximo passando a raiz como n� de refer�ncia.
		 */
		return this.maximo(this.getRaiz());
	}

	/**
	 * M�todo que retorna o n� com m�nimo valor na �rvore.
	 * 
	 * @return N� m�nimo da �rvore.
	 */
	public NoTriplo<G> minimo() {

		/*
		 * Chamada ao m�todo privado m�nimo passando a raiz como n� de refer�ncia.
		 */
		return this.minimo(this.getRaiz());
	}

	/**
	 * M�todo que retorna o sucessor de um determinado conte�do na �rvore.
	 * 
	 * @param conteudo Conte�do em foco.
	 * 
	 * @return O n� sucessor, caso exista, ou nulo, caso contr�rio.
	 */
	public NoTriplo<G> sucessor(G conteudo) {

		/*
		 * Chamada ao m�todo privado passando o n� resultante da busca como refer�ncia.
		 */
		return this.sucessor(this.buscar(conteudo));
	}

	/**
	 * M�todo que retorna o predecessor de um determinado conte�do na �rvore.
	 * 
	 * @param conteudo Conte�do em foco.
	 * 
	 * @return O n� predecessor, caso exista, ou nulo, caso contr�rio.
	 */
	public NoTriplo<G> predecessor(G conteudo) {

		/*
		 * Chamada ao m�todo privado passando o n� resultante da busca como refer�ncia.
		 */
		return this.predecessor(this.buscar(conteudo));
	}

	/**
	 * M�todo que efetua a limpeza da �rvore bin�ria de busca.
	 */
	public void limpar() {

		/*
		 * Estabelece a raiz como nula.
		 */

		this.raiz=null;
	}

	/*
	 * Gets e Sets -----------------------------------------------------------------
	 */

	/**
	 * M�todo que retorna uma refer�ncia para a ra�z da �rvore.
	 * 
	 * @return A ra�z da �rvore.
	 */
	public NoTriplo<G> getRaiz() {

		return this.raiz;
	}

	/**
	 * @param raiz o raiz a ser configurado
	 */
	private void setRaiz(NoTriplo<G> raiz) {

		this.raiz = raiz;
	}

	/*
	 * M�todos Sobrescritos --------------------------------------------------------
	 */

	/**
	 * M�todo que retorna uma representa��o da �rvore no formato texto (string).
	 * Para essa representa��o � utilizado o m�todo de caminhamento em ordem.
	 */
	@Override
	public String toString() {

		if (this.getRaiz() == null) {

			return "";
		}

		return this.getRaiz().toString();
	}

	/*
	 * M�todos privados ------------------------------------------------------------
	 */

	/**
	 * M�todo que verifica se um n� � raiz da �rvore.
	 * 
	 * @param no N� em foco.
	 * 
	 * @return Verdadeiro, caso em caso afirmativo, ou falso, caso contr�rio.
	 */
	private boolean ehRaiz(NoTriplo<G> no) {

		return no.equals(this.getRaiz());
	}

	/**
	 * M�todo que verifica se um n� � um filho esquerdo.
	 * 
	 * @param no N� em foco.
	 * 
	 * @return Verdadeiro, caso em caso afirmativo, ou falso, caso contr�rio.
	 */
	private boolean ehFilhoEsquerdo(NoTriplo<G> no) {

		if (ehRaiz(no)) {

			return false;
		}

		return no.equals(no.getPai().getEsquerdo());
	}

	/**
	 * M�todo que verifica se um n� � um filho direito.
	 * 
	 * @param no N� em foco.
	 * 
	 * @return Verdadeiro, caso em caso afirmativo, ou falso, caso contr�rio.
	 */
	private boolean ehFilhoDireito(NoTriplo<G> no) {

		if (ehRaiz(no)) {

			return false;
		}

		return no.equals(no.getPai().getDireito());
	}

	/**
	 * M�todo para efetuar a substitui��o de um determinado n� por outro. Tal
	 * substitui��o n�o efetua nenhum tipo de altera��o nos filhos dos n�s
	 * envolvidos, sendo necess�ria aplicar essas altera��es fora desse m�todo.
	 * 
	 * @param alvo       N� alvo da substitui��o.
	 * 
	 * @param substituto N� substituto.
	 */
	private void substituir(NoTriplo<G> alvo, NoTriplo<G> substituto) {

		/*
		 * Se o n� alvo for a raiz, ent�o o substituto assumira seu lugar como raiz da
		 * �rvore.
		 * 
		 * Se o n� alvo for filho esquerdo de outro n�, ent�o o n� substituto ser�
		 * inserido como filho esquerdo do pai do n� alvo.
		 * 
		 * Se o n� alvo for filho direito de outro n�, ent�o o n� substituto ser�
		 * inserido como filho direito do pai do n� alvo.
		 * 
		 */
		if (this.ehRaiz(alvo)) {

			this.setRaiz(substituto);
		}

		else if (this.ehFilhoEsquerdo(alvo)) {

			alvo.getPai().setEsquerdo(substituto);
		}

		else if (this.ehFilhoDireito(alvo)) {

			alvo.getPai().setDireito(substituto);
		}
	}

	/**
	 * M�todo que retorna o n� m�ximo a partir de um determinado n� de origem.
	 * 
	 * @param no N� de origem.
	 * 
	 * @return N� m�ximo.
	 */
	private NoTriplo<G> maximo(NoTriplo<G> no) {

		/*
		 * Verifica��o e, consequente, indica��o de busca mal sucedida, caso o n� de
		 * origem seja nulo.
		 */
		if (no == null) {
			return null;
		}

		/*
		 * Defini��o do n� de origem como o valor m�ximo.
		 */
		NoTriplo<G> max = no;

		/*
		 * Deslocamento at� o n� mais a direita da �rvore.
		 */
		while (max.getDireito() != null) {

			predecessor(max);
			max = max.getDireito();

		}

		/*
		 * Indica��o do resultado da busca.
		 */
		return max;
	}

	/**
	 * M�todo que retorna o n� m�nimo a partir de um determinado n� de origem.
	 * 
	 * @param no N� de origem.
	 * 
	 * @return N� m�nimo.
	 */
	private NoTriplo<G> minimo(NoTriplo<G> no) {

		/*
		 * Verifica��o e, consequente, indica��o de busca mal sucedida, caso o n� de
		 * origem seja nulo.
		 */
		if (no == null) {
			return null;
		}

		/*
		 * Defini��o do n� de origem como o valor m�nimo.
		 */
		NoTriplo<G> min = no;

		/*
		 * Deslocamento at� o n� mais a esquerda da �rvore.
		 */
		while (min.getEsquerdo() != null) {

			predecessor(min);
			min = min.getEsquerdo();
		}

		/*
		 * Indica��o do resultado da busca.
		 */
		return min;
	}

	/**
	 * M�todo que retorna o n� sucessor a partir de um determinado n� de origem.
	 * 
	 * @param no N� de origem.
	 *
	 * @return N� sucessor.
	 */
	private NoTriplo<G> sucessor(NoTriplo<G> no) {

		/*
		 * Verifica��o e, consequente, indica��o de busca mal sucedida, caso o n� de
		 * origem seja nulo.
		 */
		if (no == null) {

			return null;
		}

		/*
		 * Se o n� de origem possuir um filho � direita, ent�o o sucessor ser� o menor
		 * valor a sua direita.
		 */
		if (no.getDireito() != null) {
			NoTriplo aux = no.getDireito();
			while (aux.getEsquerdo() != null){
				aux = aux.getEsquerdo();
			}

			return aux;
		}

		/*
		 * Se o n� de origem n�o possuir um filho � direita, ent�o o sucessor dever� ser
		 * procurado em seus ancestrais, com a busca iniciando pelo n� pai.
		 */
		NoTriplo<G> suc = no.getPai();

		/*
		 * Deslocamento at� o n� mais superior do �rvore.
		 */
		while (suc != null) {

			/*
			 * Se o conte�do do n� sucessor for maior do que ou igual ao do n� de origem,
			 * ent�o este ser� o n� sucessor.
			 */
			if (suc.getConteudo().compareTo(no.getConteudo()) >= 0) {

				return suc;

			}

			/*
			 * Atualiza��o do n� sucessor pelo seu pai.
			 */
			no = suc;
			suc = no.getPai();

		}

		/*
		 * Indica��o de busca mal sucedida.
		 */
		return suc;
	}

	/**
	 * M�todo que retorna o n� predecessor a partir de um determinado n� de origem.
	 * 
	 * @param no N� de origem.
	 * 
	 * @return N� predecessor.
	 */
	private NoTriplo<G> predecessor(NoTriplo<G> no) {

		/*
		 * Verifica��o e, consequente, indica��o de busca mal sucedida, caso o n� de
		 * origem seja nulo.
		 */
		if (no == null) {

			return null;

		}

		/*
		 * Se o n� de origem possuir um filho � esquerda, ent�o o predecessor ser� o
		 * maior valor a sua esquerda.
		 */
		if (no.getEsquerdo() != null) {
			NoTriplo aux = no.getEsquerdo();
			while (aux.getDireito() != null){
				aux = aux.getDireito();
			}

			return aux;
		}

		/*
		 * Se o n� de origem n�o possuir um filho � esquerda, ent�o o predecessor dever�
		 * ser procurado em seus ancestrais, com a busca iniciando pelo n� pai.
		 */
		NoTriplo<G> pre = no.getPai();

		/*
		 * Deslocamento at� o n� mais superior do �rvore.
		 */
		while (pre != null) {

			/*
			 * Se o conte�do do n� predecessor for menor do que o do n� de origem, ent�o
			 * este ser� o n� predecessor.
			 */
			if (pre.getConteudo().compareTo(no.getConteudo()) < 0) {

				return pre;

			}

			/*
			 * Atualiza��o do n� predecessor pelo seu pai.
			 */

			no = pre;
			pre = no.getPai();
		}

		/*
		 * Indica��o de busca mal sucedida.
		 */
		return pre;
	}
}