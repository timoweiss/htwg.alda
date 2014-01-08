package aufgabe3.TelNetApplication;

public class TelKnoten {

	public final int x;
	public final int y;

	public TelKnoten(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
