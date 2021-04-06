/**
 * 
 */
package br.edu.ifs.ed2.dados.no;

/**
 * Classe que implementas as operações de um nó com encadeamento triplo.
 * 
 * @author Marlos Tacio Silva
 *
 */
public class NoTriplo<G> extends No<G> {

	/*
	 * Atributos -------------------------------------------------------------------
	 */

	/**
	 * Atributo que armazena uma referência para o nó que modela pai.
	 */
	private NoTriplo<G> pai;

	/**
	 * Atributo que armazena uma referência para o nó que modela o filho esquerdo.
	 */
	private NoTriplo<G> esquerdo;

	/**
	 * Atributo que armazena uma referência para o nó que modela o filho direito.
	 */
	private NoTriplo<G> direito;

	/*
	 * Construtores ----------------------------------------------------------------
	 */

	/**
	 * Construtor da classe.
	 * 
	 * @param conteudo Conteúdo do nó.
	 */
	public NoTriplo(G conteudo) {

		super(conteudo);
	}

	/*
	 * Gets e Sets -----------------------------------------------------------------
	 */

	/**
	 * Método que retorna uma referência para o nó pai.
	 * 
	 * @return Uma referência para o nó pai, caso exista, ou nulo, caso contrário.
	 */
	public NoTriplo<G> getPai() {

		return this.pai;
	}

	/**
	 * Método que altera a referência para do nó pai.
	 * 
	 * @param pai O nó a ser alterado.
	 * 
	 */
	protected void setPai(NoTriplo<G> pai) {

		this.pai = pai;
	}

	/**
	 * Método que retorna uma referência para o nó filho esquerdo.
	 * 
	 * @return Uma referência para o nó filho esquerdo, caso exista, ou nulo, caso
	 *         contrário.
	 */
	public NoTriplo<G> getEsquerdo() {

		return this.esquerdo;
	}

	/**
	 * Método que retorna uma referência para o nó filho direito.
	 * 
	 * @return Uma referência para o nó filho direito, caso exista, ou nulo, caso
	 *         contrário.
	 */
	public NoTriplo<G> getDireito() {

		return this.direito;
	}

	/**
	 * Método que altera a referência para o nó filho esquerdo. Essa operação altera
	 * também a relação de paternidade entre os nós envolvidos.
	 * 
	 * @param esquerdo O nó a ser alterado.
	 * 
	 */
	public void setEsquerdo(NoTriplo<G> esquerdo) {

		if (this.esquerdo == esquerdo) {

			return;
		}

		this.esquerdo = esquerdo;

		if (esquerdo != null) {

			esquerdo.setPai(this);
		}
	}

	/**
	 * Método que altera a referência para o nó filho direito. Essa operação altera
	 * também a relação de paternidade entre os nós envolvidos.
	 * 
	 * @param direito O nó a ser alterado.
	 * 
	 */
	public void setDireito(NoTriplo<G> direito) {

		if (this.direito == direito) {

			return;
		}

		this.direito = direito;

		if (direito != null) {

			direito.setPai(this);
		}
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

		return this.mostrarPreOrdem(this);
	}

	/*
	 * Métodos privados ------------------------------------------------------------
	 */

	/**
	 * Método que efetua o caminhamento em ordem a partir de um determinado nó.
	 * 
	 * @param no Nó de início do percurso.
	 * 
	 * @return Representação da árvore em formato texto.
	 */
	private String mostrarPreOrdem(NoTriplo<G> no) {

		/*
		 * Representação do nó nulo.
		 */
		if (no == null) {

			return "-";
		}

		StringBuilder string = new StringBuilder();

		/*
		 * Adiciona o conteúdo do nó pai à estrutura de texto.
		 */
		string.append(no.getConteudo());

		/*
		 * Se pelo menos um dos nós filhos não for nulo, então adicionar recursivamente
		 * os nós filhos à estrutura de texto.
		 */
		if (no.getEsquerdo() != null || no.getDireito() != null) {

			string.append("( ");
			string.append(mostrarPreOrdem(no.getEsquerdo()));
			string.append(" , ");
			string.append(mostrarPreOrdem(no.getDireito()));
			string.append(" )");
		}

		return string.toString();
	}
}