package aufgabe3.TelNetApplication;


public class TelVerbindung {

	public final TelKnoten u;
	public final TelKnoten v;
	
	public TelVerbindung(TelKnoten u, TelKnoten v) {
		this.u = u;
		this.v = v;
	}
    
	@Override
	public String toString() {
		return u + " -> " + v;
	}
	
}
