package aufgabe3.TelNetApplication;

public class UnionFind {
	private int p[];

	public UnionFind(int n) {
		p = new int[n];
	}

	public int size() {
		int size = 0;
		for (int i = 0; i < p.length; i++) {

			if (p[i] <= 0) {
				size++;
			}
		}
		return size;
	}

	public int find(int e) {
		while (p[e] > 0) {
			e = p[e];
		}
		return e;
	}

	public void union(int e1, int e2) {
		if (e1 < 0 || e1 > p.length - 1 || e2 < 0 || e2 > p.length - 1) {
			throw new IllegalArgumentException();
		}
		unionByHeight(e1, e2);
	}

	private void unionByHeight(int s1, int s2) {
		if (-p[s1] < -p[s2]) // Höhe von s1 < Höhe von s2
			p[s1] = s2;
		else {
			if (-p[s1] == -p[s2])
				p[s1]--; // Höhe von s1 wird erhöht

			p[s2] = s1;
		}
	}
}