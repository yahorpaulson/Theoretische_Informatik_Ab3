package ab3.impl.Siarheyeu;

import ab3.Ab3;
import ab3.TreeNode;
import ab3.TreeDecomposition;

import java.util.HashSet;

public class Ab3Impl implements Ab3 {

	@Override
	public String[] matriculationNumbers() {

		//return new String[] { "1234567", "2345678", "3456789" };
		return new String[]{"11930943"};
	}

	@Override
	public TreeDecomposition decomposeExact(boolean[][] graph) {

		HashSet<Integer> bag = new HashSet<>(graph.length);
		TreeNode root = new TreeNode();

		root.bag = bag;

		for (int i= 0; i < graph.length; i++){
			root.bag.add(i);
		}
		return new TreeDecomposition(graph, root);
	}

	@Override
	public TreeDecomposition decomposeFast(boolean[][] graph) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeDecomposition decomposeRandom(boolean[][] graph, int samples) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeDecomposition decomposeTrivial(boolean[][] graph) {
		TreeNode root = new TreeNode();
		root.bag = new HashSet<>();
		for (int i = 0; i < graph.length; i++) {
			root.bag.add(i);
		}
		return new TreeDecomposition(graph, root);
	}

	@Override
	public boolean isValid(TreeDecomposition td) {
		if(td==null || td.graph == null || td.root == null){
			return false;
		} // initial check

		HashSet<Integer> covered = new HashSet<>();
		HashSet<TreeNode> stack = new HashSet<>();
		stack.add(td.root);

		while (!stack.isEmpty()) {
			TreeNode cur = stack.iterator().next();
			stack.remove(cur);

			if (cur == null || cur.bag == null || cur.children == null) return false;


			covered.addAll(cur.bag);

			for (TreeNode child : cur.children) {
				if(child == null) return false;
				stack.add(child); //fügen Kinderknoten im Stack hinzu
			}

			int n = td.graph.length;
			for (int v = 0; v < n; v++) {
				if (!covered.contains(v)) return false;
			} //alle Knoten des Graphen sind in den bags enthalten
		}

		return true;
	}

	@Override
	public int getWidth(TreeDecomposition td) {

		return -1;
	}

	@Override
	public boolean hasTreewidthBound(boolean[][] graph, int width) {
		// TODO Auto-generated method stub
		return false;
	}
}
