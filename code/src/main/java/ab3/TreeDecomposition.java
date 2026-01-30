package ab3;

/**
 * Eine Baumzerlegung (Tree Decomposition) eines Graphen.
 */
public final class TreeDecomposition
{
	/// Der zugrundeliegende (ungerichtete) Graph in Form einer Adjazenzmatrix.
	public final boolean[][] graph;

	/// Der Wurzelknoten der Baumzerlegung.
	public final TreeNode root;

	/**
	 * Erzeugt ein neues TreeDecomposition-Objekt
	 *
	 * @param graph die Adjazenzmatrix des Graphen
	 * @param root der Wurzelknoten
	 */
	public TreeDecomposition(boolean[][] graph, TreeNode root) {
		this.graph = graph;
		this.root = root;
	}
}
