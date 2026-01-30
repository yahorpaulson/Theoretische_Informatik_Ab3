package ab3.impl.Nachnamen;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getWidth(TreeDecomposition td) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public boolean hasTreewidthBound(boolean[][] graph, int width) {
		// TODO Auto-generated method stub
		return false;
	}
}
