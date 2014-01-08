package aufgabe3.TelNetApplication;

public class UnionTest {
	public static void main(final String[] args) {
		UnionFind wald = new UnionFind(10);

		wald.union(1, 4);
		wald.union(2, 9);
		wald.union(7, 4);
		wald.union(6, 3);
		wald.union(8, 5);

		System.out.println(wald.size());
		System.out.println(wald.find(3));

	}
}
