package ab3.impl.Siarheyeu;

import ab3.Ab3;
import ab3.TreeNode;
import ab3.TreeDecomposition;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Ab3Impl implements Ab3 {

	@Override
	public String[] matriculationNumbers() {

		return new String[]{"11930943"};
	}


	private TreeDecomposition bestExact = null;
	private int bestWidth;


	@Override
	public TreeDecomposition decomposeExact(boolean[][] graph) {

		if (graph == null) return null;

		bestExact = null;
		bestWidth = Integer.MAX_VALUE;

		int [] vertices = new int[graph.length];
		for (int i = 0; i < vertices.length; i++){
			vertices[i] = i;
		}

		permutations(vertices, 0 ,graph);
		return bestExact;
	}

	private void permutations(int[] arr, int idx, boolean[][] graph) {


		if (idx == arr.length) {
			TreeDecomposition td = buildFromOrder(graph, arr);
			int w = getWidth(td);

			if (w < bestWidth) {
				bestWidth = w;
				bestExact = td;
			}
			return;
		}

		for (int i = idx; i < arr.length; i++) {

			// swap
			swap(arr, i, idx);

			permutations(arr, idx + 1, graph);

			// swap back
			swap(arr, i, idx);
		}
	}

	//methode nimmt eine Permutation
	private TreeDecomposition buildFromOrder(boolean[][] graph, int[] order) {

		boolean[][] newGraph = new boolean[graph.length][graph.length];

		for (int i = 0; i < graph.length; i++) {
            System.arraycopy(graph[i], 0, newGraph[i], 0, graph.length);
		}


		TreeNode root = null;
		TreeNode prev = null;

		for (int v : order) {

			TreeNode node = new TreeNode();

			// bag = v und alle Nachbarn
			node.bag.add(v);
			for (int u = 0; u < newGraph.length; u++) {
				if (newGraph[v][u]) node.bag.add(u);
			}


			if (root == null) root = node;
			if (prev != null) {
				prev.children.add(node);
				node.parent = prev;
			}
			prev = node;

			// aus dem Graph löschen
			for (int i = 0; i < newGraph.length; i++) {
				newGraph[v][i] = false;
				newGraph[i][v] = false;
			}
		}

		return new TreeDecomposition(graph, root);
	}


	private void swap(int [] arr, int first, int second) {

		int tmp = arr[first];
		arr[first] = arr[second];
		arr[second] = tmp;
	}


	@Override
	public TreeDecomposition decomposeFast(boolean[][] graph) {

		//Kopie vom ursprünglichen Graph wird erstellt
		boolean[][] newGraph = new boolean[graph.length][graph.length];

		for (int i = 0; i < graph.length; i++) {
			System.arraycopy(graph[i], 0, newGraph[i], 0, graph.length);
		}

		boolean[] alive = new boolean[newGraph.length];
        Arrays.fill(alive, true); //true - vertices sind im Graph, false - gelöscht

		TreeNode root = null;
		TreeNode prev = null;


		for(int i = 0; i < alive.length; i++) {
			//Vertice mit niedrigsten Anzahl den Nachbarn
			int v = pickMinDegreeVertex(newGraph, alive);

			TreeNode node = new TreeNode();
			node.bag.add(v); //Würzel hinzufügen

			//Nachbarn holen
			for (int u = 0; u < alive.length; u++) {
				if (alive[u] && newGraph[v][u]) {
					node.bag.add(u);
				}
			}

			//eine Kette erstellen
			if (root == null) {
				root = node;
			}
			if (prev != null) {
				prev.children.add(node);
				node.parent = prev;
			}
			prev = node;

			//allen Nachbarn zusammenbinden
			for (int j = 0; j < alive.length; j++) {
				for (int k = j + 1; k < alive.length; k++) {
					if (alive[j] && newGraph[v][k]) {
						newGraph[j][k] = true;
						newGraph[k][j] = true;
					}
				}
			}

			//löschen v
			alive[v] = false;

			for (int t = 0; t < alive.length; t++) {
				newGraph[v][t] = false;
				newGraph[t][v] = false;
			}
		}
		if (root == null) {
			root = new TreeNode();
		}
		return new TreeDecomposition(graph, root);

	}

	private int pickMinDegreeVertex(boolean[][] g, boolean[] alive) {
		int n = g.length;
		int bestV = -1;
		int bestDeg = Integer.MAX_VALUE;

		for (int v = 0; v < n; v++) {
			if (!alive[v]) continue;

			int deg = 0;
			//Anzahl noch nicht gelöschte Nachbarn
			for (int u = 0; u < n; u++) {
				if (alive[u] && g[v][u]) deg++;
			}

			//Vertice mit niedrigste Grad merken
			if (deg < bestDeg) {
				bestDeg = deg;
				bestV = v;
			}
		}
		return bestV;
	}


	@Override
	public TreeDecomposition decomposeRandom(boolean[][] graph, int samples) {

		TreeDecomposition res = null;
		int bestWidth = Integer.MAX_VALUE;

		int[] order = new int[graph.length];
		for (int i = 0; i < order.length; i++) {
			order[i]= i;
		}

		Random rand = new Random();

		for(int sample=0; sample<samples; sample++) {
			for (int i = graph.length - 1; i > 0; i--) {
				int j = rand.nextInt(i + 1);
				int tmp = order[i];
				order[i] = order[j];
				order[j] = tmp;
			}
			TreeDecomposition td = buildFromOrder(graph, order);
			int width = getWidth(td);

			//beste Ergebnis
			if (width < bestWidth) {
				bestWidth = width;
				res = td;
			}
		}
		return res;
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
				//if(child == null) return false;
				stack.add(child); //fügen Kinderknoten im Stack hinzu
			}
		}
		int n = td.graph.length;
		for (int v = 0; v < n; v++) {
			if (!covered.contains(v)) return false;
		} //alle Knoten des Graphen sind in den bags enthalten

		return true;
	}

	@Override
	public int getWidth(TreeDecomposition td) {
		int maxBagSize = 0;

		HashSet<TreeNode> stack = new HashSet<>();
		stack.add(td.root);//Würzelknote addieren

		while (!stack.isEmpty()) {
			TreeNode cur = stack.iterator().next();
			stack.remove(cur);

			if (cur.bag == null) continue;

			maxBagSize = Math.max(maxBagSize, cur.bag.size());

            stack.addAll(cur.children);
		}
		return maxBagSize - 1;
	}

	@Override
	public boolean hasTreewidthBound(boolean[][] graph, int width) {
		TreeDecomposition td = decomposeExact(graph);
		if (td == null) return false;

		return getWidth(td) <= width;
	}
}
