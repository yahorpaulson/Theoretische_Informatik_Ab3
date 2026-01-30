package ab3;

import java.util.HashSet;

/**
 * Die Knoten im Baum einer Baumzerlegung von einem Graphen.
 */
public final class TreeNode
{
	/// Die Knoten aus dem Graph, die diesem Knoten zugeordnet sind
	public HashSet<Integer> bag;

	/// Die Kindknoten dieses Knotens
	public HashSet<TreeNode> children;

	/// Der Elternknoten dieses Knotens
	public TreeNode parent;

	/// Constructor
	public TreeNode()
	{
		bag = new HashSet<>();
		children = new HashSet<>();
		parent = null;
	}
}
