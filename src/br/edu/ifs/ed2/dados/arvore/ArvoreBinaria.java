/**
 * 
 */
package br.edu.ifs.ed2.dados.arvore;

import br.edu.ifs.ed2.dados.no.NoTriplo;

/**
 * Classe que implementa as operações de uma árvore binária de busca.
 * 
 * @author Marlos Tacio Silva.
 *
 */
public class ArvoreBinaria<G extends Comparable<G>> {

	/*
	 * Atributos -------------------------------------------------------------------
	 */

	/**
	 * Atributo que contém uma referência para o nó raiz da árvore.
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
	 * @param raiz Nó raiz da árvore.
	 */
	public ArvoreBinaria(NoTriplo<G> raiz) {

		this.setRaiz(raiz);
	}

	/*
	 * Métodos públicos ------------------------------------------------------------
	 */

	/**
	 * Método para a inserção de um elemento na árvore binária de busca.
	 * 
	 * @param conteudo Conteúdo a ser inserido.
	 *
	 * @return Verdadeiro, para inserção bem sucedida, ou falso, caso contrário.
	 */
	public boolean inserir(G conteudo) {

		/*
		 * Verificação e indicação de inserção mal sucedida, caso o conteúdo seja nulo.
		 */
		if (conteudo == null) {
			return false;
		}

		/*
		 * Estabelece o conteúdo como raiz, caso a árvore esteja vazia.
		 */
		if (this.getRaiz() == null) {
			setRaiz(new NoTriplo<>(conteudo));
			return true;
		}

		/*
		 * Estabelece dois nós auxiliares para percorrer a árvore em busca do ponto de
		 * inserção.
		 */
		NoTriplo<G> aux1 = this.getRaiz();
		NoTriplo<G> aux2 = null;

		/*
		 * Percorre a árvore binária de busca a partir da raiz até encontrar o ponto de
		 * inserção, ou seja, o nó nulo.
		 */
		while (aux1 != null) {

			/*
			 * Iguala os nós auxiliares.
			 */

			aux2 = aux1;

			/*
			 * Comparação do conteúdo a ser inserido com o conteúdo do nó em foco.
			 * 
			 * Obtém o filho direito, caso o conteúdo a ser inserido seja maior do que ou
			 * igual ao do nó em foco, e o filho esquerdo, caso contrário.
			 */
			if (conteudo.compareTo(aux1.getConteudo()) >= 0) {

				aux1 = aux1.getDireito();

			}

			else {

				aux1 = aux1.getEsquerdo();

			}
		}

		/*
		 * Após obter o ponto de inserção, compara o conteúdo com o do nó em foco.
		 * 
		 * Inserção do conteúdo do no filho direito, caso o conteúdo seja maior do que
		 * ou igual ao do nó em foco, ou no filho esquerdo, caso contrário.
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
	 * Método para a remoção de um elemento na árvore binária de busca.
	 * 
	 * @param conteudo Conteúdo a ser removido.
	 *
	 * @return Verdadeiro, para remoção bem sucedida, ou falso, caso contrário.
	 */
	public boolean remover(G conteudo) {

		/*
		 * Efetua uma operação de busca a partir do conteúdo.
		 */
		NoTriplo<G> aux = this.buscar(conteudo);

		/*
		 * Verificação do resultado da busca e, em caso de busca mal sucedida, indicação
		 * de inserção mal sucedida.
		 */
		if (aux == null) {

			return false;
		}

		/*
		 * Se o nó a ser removido não possuir filho esquerdo, então deverá ser
		 * substituído por seu filho direito.
		 *
		 * Se o nó a ser removido não possuir filho direito, então deverá ser
		 * substituído por seu filho esquerdo.
		 */
		if (aux.getEsquerdo() == null) {

			this.substituir(aux, aux.getDireito());
		}

		else if (aux.getDireito() == null) {

			this.substituir(aux, aux.getEsquerdo());
		}

		/*
		 * Caso o nó a ser substituído possua ambos os filhos.
		 */
		else {

			/*
			 * Estabelecer que o nó substituto será o sucessor do nó a ser removido.
			 */
			NoTriplo<G> substituto = this.sucessor(aux);

			/*
			 * Se o nó substituto for o filho direito do nó a ser removido, então o nó a ser
			 * removido deverá ser substituído pelo nó substituto. Além disso, o nó
			 * substituto deverá adicionar o filho esquerdo do nó a ser removido como seu
			 * filho esquerdo.
			 */
			if (aux.getDireito().equals(substituto)) {

				this.substituir(aux, substituto);
				substituto.setEsquerdo(aux.getEsquerdo());
			}

			/*
			 * Caso contrário, o nó substituto deverá ser substituído por seu filhos
			 * direito. Em seguida o nó a ser removido deverá ser substituído pelo nó
			 * substituto. Além disso, o nó substituto deverá adicionar ambos os filho do nó
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
		 * Indicação de remoção bem sucedida.
		 */
		return true;
	}

	/**
	 * Método para a busca de um elemento na árvore binária de busca.
	 * 
	 * @param conteudo Conteúdo a ser buscado.
	 * 
	 * @return Nó encontrado, para busca bem sucedida, ou nulo, caso contrário.
	 */
	public NoTriplo<G> buscar(G conteudo) {

		/*
		 * Verificação e indicação de inserção mal sucedida, caso o conteúdo seja nulo.
		 */
		if (conteudo == null) {
			return null;
		}

		/*
		 * Estabelece a raiz como ponto de início da busca.
		 */
		NoTriplo<G> aux = this.raiz;

		/*
		 * Percorre a árvore binária de busca enquanto não encontrar o ponto de
		 * inserção, ou seja, o nó nulo.
		 */
		while (aux != null) {

			/*
			 * Se o conteúdo da busca for igual ao do nó em foco, então retornar esse nó.
			 * 
			 * Se o conteúdo da busca for maior do que o do nó em foco, então atualizar o nó
			 * em foco para o filho direito.
			 * 
			 * Se o conteúdo da busca for menor do que o do nó em foco, então atualizar o nó
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
		 * Indicação de busca mal sucedida.
		 */
		return null;
	}

	/**
	 * Método que retorna o nó com máximo valor na árvore.
	 * 
	 * @return Nó máximo da árvore.
	 */
	public NoTriplo<G> maximo() {

		/*
		 * Chamada ao método privado máximo passando a raiz como nó de referência.
		 */
		return this.maximo(this.getRaiz());
	}

	/**
	 * Método que retorna o nó com mínimo valor na árvore.
	 * 
	 * @return Nó mínimo da árvore.
	 */
	public NoTriplo<G> minimo() {

		/*
		 * Chamada ao método privado mínimo passando a raiz como nó de referência.
		 */
		return this.minimo(this.getRaiz());
	}

	/**
	 * Método que retorna o sucessor de um determinado conteúdo na árvore.
	 * 
	 * @param conteudo Conteúdo em foco.
	 * 
	 * @return O nó sucessor, caso exista, ou nulo, caso contrário.
	 */
	public NoTriplo<G> sucessor(G conteudo) {

		/*
		 * Chamada ao método privado passando o nó resultante da busca como referência.
		 */
		return this.sucessor(this.buscar(conteudo));
	}

	/**
	 * Método que retorna o predecessor de um determinado conteúdo na árvore.
	 * 
	 * @param conteudo Conteúdo em foco.
	 * 
	 * @return O nó predecessor, caso exista, ou nulo, caso contrário.
	 */
	public NoTriplo<G> predecessor(G conteudo) {

		/*
		 * Chamada ao método privado passando o nó resultante da busca como referência.
		 */
		return this.predecessor(this.buscar(conteudo));
	}

	/**
	 * Método que efetua a limpeza da árvore binária de busca.
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
	 * Método que retorna uma referência para a raíz da árvore.
	 * 
	 * @return A raíz da árvore.
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
	 * Métodos Sobrescritos --------------------------------------------------------
	 */

	/**
	 * Método que retorna uma representação da árvore no formato texto (string).
	 * Para essa representação é utilizado o método de caminhamento em ordem.
	 */
	@Override
	public String toString() {

		if (this.getRaiz() == null) {

			return "";
		}

		return this.getRaiz().toString();
	}

	/*
	 * Métodos privados ------------------------------------------------------------
	 */

	/**
	 * Método que verifica se um nó é raiz da árvore.
	 * 
	 * @param no Nó em foco.
	 * 
	 * @return Verdadeiro, caso em caso afirmativo, ou falso, caso contrário.
	 */
	private boolean ehRaiz(NoTriplo<G> no) {

		return no.equals(this.getRaiz());
	}

	/**
	 * Método que verifica se um nó é um filho esquerdo.
	 * 
	 * @param no Nó em foco.
	 * 
	 * @return Verdadeiro, caso em caso afirmativo, ou falso, caso contrário.
	 */
	private boolean ehFilhoEsquerdo(NoTriplo<G> no) {

		if (ehRaiz(no)) {

			return false;
		}

		return no.equals(no.getPai().getEsquerdo());
	}

	/**
	 * Método que verifica se um nó é um filho direito.
	 * 
	 * @param no Nó em foco.
	 * 
	 * @return Verdadeiro, caso em caso afirmativo, ou falso, caso contrário.
	 */
	private boolean ehFilhoDireito(NoTriplo<G> no) {

		if (ehRaiz(no)) {

			return false;
		}

		return no.equals(no.getPai().getDireito());
	}

	/**
	 * Método para efetuar a substituição de um determinado nó por outro. Tal
	 * substituição não efetua nenhum tipo de alteração nos filhos dos nós
	 * envolvidos, sendo necessária aplicar essas alterações fora desse método.
	 * 
	 * @param alvo       Nó alvo da substituição.
	 * 
	 * @param substituto Nó substituto.
	 */
	private void substituir(NoTriplo<G> alvo, NoTriplo<G> substituto) {

		/*
		 * Se o nó alvo for a raiz, então o substituto assumira seu lugar como raiz da
		 * árvore.
		 * 
		 * Se o nó alvo for filho esquerdo de outro nó, então o nó substituto será
		 * inserido como filho esquerdo do pai do nó alvo.
		 * 
		 * Se o nó alvo for filho direito de outro nó, então o nó substituto será
		 * inserido como filho direito do pai do nó alvo.
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
	 * Método que retorna o nó máximo a partir de um determinado nó de origem.
	 * 
	 * @param no Nó de origem.
	 * 
	 * @return Nó máximo.
	 */
	private NoTriplo<G> maximo(NoTriplo<G> no) {

		/*
		 * Verificação e, consequente, indicação de busca mal sucedida, caso o nó de
		 * origem seja nulo.
		 */
		if (no == null) {
			return null;
		}

		/*
		 * Definição do nó de origem como o valor máximo.
		 */
		NoTriplo<G> max = no;

		/*
		 * Deslocamento até o nó mais a direita da árvore.
		 */
		while (max.getDireito() != null) {

			predecessor(max);
			max = max.getDireito();

		}

		/*
		 * Indicação do resultado da busca.
		 */
		return max;
	}

	/**
	 * Método que retorna o nó mínimo a partir de um determinado nó de origem.
	 * 
	 * @param no Nó de origem.
	 * 
	 * @return Nó mínimo.
	 */
	private NoTriplo<G> minimo(NoTriplo<G> no) {

		/*
		 * Verificação e, consequente, indicação de busca mal sucedida, caso o nó de
		 * origem seja nulo.
		 */
		if (no == null) {
			return null;
		}

		/*
		 * Definição do nó de origem como o valor mínimo.
		 */
		NoTriplo<G> min = no;

		/*
		 * Deslocamento até o nó mais a esquerda da árvore.
		 */
		while (min.getEsquerdo() != null) {

			predecessor(min);
			min = min.getEsquerdo();
		}

		/*
		 * Indicação do resultado da busca.
		 */
		return min;
	}

	/**
	 * Método que retorna o nó sucessor a partir de um determinado nó de origem.
	 * 
	 * @param no Nó de origem.
	 *
	 * @return Nó sucessor.
	 */
	private NoTriplo<G> sucessor(NoTriplo<G> no) {

		/*
		 * Verificação e, consequente, indicação de busca mal sucedida, caso o nó de
		 * origem seja nulo.
		 */
		if (no == null) {

			return null;
		}

		/*
		 * Se o nó de origem possuir um filho à direita, então o sucessor será o menor
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
		 * Se o nó de origem não possuir um filho á direita, então o sucessor deverá ser
		 * procurado em seus ancestrais, com a busca iniciando pelo nó pai.
		 */
		NoTriplo<G> suc = no.getPai();

		/*
		 * Deslocamento até o nó mais superior do árvore.
		 */
		while (suc != null) {

			/*
			 * Se o conteúdo do nó sucessor for maior do que ou igual ao do nó de origem,
			 * então este será o nó sucessor.
			 */
			if (suc.getConteudo().compareTo(no.getConteudo()) >= 0) {

				return suc;

			}

			/*
			 * Atualização do nó sucessor pelo seu pai.
			 */
			no = suc;
			suc = no.getPai();

		}

		/*
		 * Indicação de busca mal sucedida.
		 */
		return suc;
	}

	/**
	 * Método que retorna o nó predecessor a partir de um determinado nó de origem.
	 * 
	 * @param no Nó de origem.
	 * 
	 * @return Nó predecessor.
	 */
	private NoTriplo<G> predecessor(NoTriplo<G> no) {

		/*
		 * Verificação e, consequente, indicação de busca mal sucedida, caso o nó de
		 * origem seja nulo.
		 */
		if (no == null) {

			return null;

		}

		/*
		 * Se o nó de origem possuir um filho à esquerda, então o predecessor será o
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
		 * Se o nó de origem não possuir um filho á esquerda, então o predecessor deverá
		 * ser procurado em seus ancestrais, com a busca iniciando pelo nó pai.
		 */
		NoTriplo<G> pre = no.getPai();

		/*
		 * Deslocamento até o nó mais superior do árvore.
		 */
		while (pre != null) {

			/*
			 * Se o conteúdo do nó predecessor for menor do que o do nó de origem, então
			 * este será o nó predecessor.
			 */
			if (pre.getConteudo().compareTo(no.getConteudo()) < 0) {

				return pre;

			}

			/*
			 * Atualização do nó predecessor pelo seu pai.
			 */

			no = pre;
			pre = no.getPai();
		}

		/*
		 * Indicação de busca mal sucedida.
		 */
		return pre;
	}
}