package aufgabe3.TelNetApplication;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Klasse zur Verwaltung von Telefonknoten mit (x,y)-Koordinaten und zur
 * Berechnung eines minimal aufspannenden Baums mit dem Algorithmus von Prim.
 * Kantengewichte sind durch den Mahattan-Abstand definiert.
 * 
 * @author Timo Weiss, Michael Knoch
 * 
 */

public class TelNet {

	class NodeData {
		/** Knoten besucht */
		boolean visited = false;
		/** Gewicht */
		double d = Double.MAX_VALUE;
		/** Vorgaengerknoten. */
		TelKnoten p = null;

		NodeData(boolean visited, double d, TelKnoten p) {
			this.visited = visited;
			this.d = d;
			this.p = p;
		}
	}

	/** Leitungsbegrenzungswert. */
	int lbg;

	List<TelKnoten> candidatesList = new ArrayList<TelKnoten>();
	HashMap<TelKnoten, NodeData> nodes = new HashMap<TelKnoten, NodeData>();
	List<TelVerbindung> optTelNet = new ArrayList<TelVerbindung>();

	/**
	 * Legt ein neues Telefonnetz mit dem Leitungsbegrenzungswert lbg an.
	 * 
	 * @param lbg
	 *            Leitungsbegrenzungswert
	 */
	public TelNet(int lbg) {
		this.lbg = lbg;
	}

	/**
	 * Fuegt einen neuen Telefonknoten mit Koordinate (x,y) dazu.
	 * 
	 * @param x
	 *            x-Koordinate.
	 * @param y
	 *            y-Koordinate.
	 */
	public void addTelKnoten(int x, int y) {
		nodes.put(new TelKnoten(x, y), new NodeData(false, Double.MAX_VALUE,
				null));
	}

	/**
	 * Berechnet ein optimales Telefonnetz als minimal aufspannenden Baum mit
	 * dem Algorithmus von Prim.
	 */
	public void computeOptTelNet() {

		TelKnoten s = nodes.entrySet().iterator().next().getKey();
		// TelKnoten s = (TelKnoten) nodes.keySet().toArray()[0];
		nodes.get(s).visited = true;
		nodes.get(s).d = 0;
		candidatesList.add(s);

		while (!candidatesList.isEmpty()) {
			// Kandidat mit geringster Distanz aus Kandidatenliste entfernen
			TelKnoten v = null;
			double min = Double.MAX_VALUE;
			for (TelKnoten k : candidatesList) {

				for (Entry<TelKnoten, NodeData> e : nodes.entrySet()) {
					// Nur wenn Knoten schon besucht
					if (e.getValue().visited) {
						double dist = Math.abs(e.getKey().x - k.x)
								+ Math.abs(e.getKey().y - k.y);
						if (dist < min) {
							min = dist;
							v = k;
						}
					}
				}
			}
			candidatesList.remove(v);

			if (nodes.get(v).p != null) {
				optTelNet.add(new TelVerbindung(nodes.get(v).p, v));
				nodes.get(v).visited = true;
			}

			// Fuer jeden Nachbarknoten w von v (Knoten die innerhalb der
			// Leitungsbegrenzung erreichbar sind)
			for (TelKnoten w : nodes.keySet()) {
				double dist = Math.abs(v.x - w.x) + Math.abs(v.y - w.y);
				// Gewicht < Leitungsbegrenzung
				if (dist < lbg) {
					// Knoten noch nicht besucht
					if (!nodes.get(w).visited) {
						// Wenn w noch nicht in der Kandidatenliste (d
						// unveraendert)
						if (nodes.get(w).d == Double.MAX_VALUE)
							candidatesList.add(w);
						// Gewicht < altes Gewicht
						if (dist < nodes.get(w).d) {
							// Setze neues Gewicht & Vorgaengerknoten
							nodes.get(w).d = dist;
							nodes.get(w).p = v;
						}
					}
				}
			}
		}

		for (TelKnoten w : nodes.keySet()) {
			if (!nodes.get(w).visited) {
				System.out.println("Kein optimales Telefonnetz moeglich!");
				// Loesche vorheriges Ergebnis
				optTelNet.clear();
			}
		}

	}

	/**
	 * Liefert ein optimales Telefonnetz als Liste von Telefonverbindungen
	 * zurueck.
	 * 
	 * @return Liste von Telefonverbindungen.
	 * @throws IllegalStateException
	 *             falls nicht zuvor computeOptTelNet() aufgerufen wurde.
	 */
	public List<TelVerbindung> getOptTelNet() throws IllegalStateException {
		if (optTelNet.isEmpty())
			throw new IllegalStateException(
					"computeOptTelNet() noch nicht aufgerufen");

		return optTelNet;
	}

	/**
	 * Liefert die Gesamtkosten eines optimalen Telefonnetzes zurueck.
	 * 
	 * @return Gesamtkosten eines optimalen Telefonnetzes.
	 * @throws IllegalStateException
	 *             falls nicht zuvor computeOptTelNet() aufgerufen wurde. nicht
	 *             im Graph vorhanden ist.
	 */
	public int getOptTelNetKosten() throws IllegalStateException {
		if (optTelNet.isEmpty())
			throw new IllegalStateException(
					"computeOptTelNet() noch nicht aufgerufen");

		int costs = 0;
		for (NodeData n : nodes.values()) {
			costs += n.d;
			// System.out.print(n.d + "+");
		}

		/*
		 * for ( TelVerbindung t : optTelNet) { double dist = Math.abs(t.u.x -
		 * t.v.x) + Math.abs(t.u.y - t.v.y); // System.out.println(dist+ "&");
		 * costs += dist; }
		 */

		return costs;

	}

	/**
	 * Zeichnet das gefundene optimale Telefonnetz mit der Groessee xMax*yMax in
	 * ein Fenster.
	 * 
	 * @param xMax
	 *            Maximale x-Groesse.
	 * @param yMax
	 *            Maximale y-Groesse.
	 * @throws IllegalStateException
	 *             falls nicht zuvor computeOptTelNet() aufgerufen wurde.
	 */
	public void drawOptTelNet(int xMax, int yMax) throws IllegalStateException {
		if (optTelNet.isEmpty())
			throw new IllegalStateException(
					"computeOptTelNet() noch nicht aufgerufen");

		StdDraw.setXscale();
		StdDraw.setYscale();

		StdDraw.setCanvasSize(800, 800);

		StdDraw.setPenRadius(0.01);
		StdDraw.setPenColor(Color.RED);
		for (TelKnoten k : nodes.keySet()) {
			StdDraw.point((double) k.x / xMax, (double) k.y / yMax);
		}

		StdDraw.setPenRadius(0.001);
		StdDraw.setPenColor(Color.BLUE);
		for (TelVerbindung k : optTelNet) {
			StdDraw.line((double) k.u.x / xMax, (double) k.v.y / yMax,
					(double) k.v.x / xMax, (double) k.v.y / yMax);
			StdDraw.line((double) k.u.x / xMax, (double) k.u.y / yMax,
					(double) k.u.x / xMax, (double) k.v.y / yMax);
		}
	}

	/**
	 * Fuegt n zufaellige Telefonknoten zum Netz dazu mit x-Koordinaten aus
	 * [0,xMax] und y-Koordinaten aus [0,yMax].
	 * 
	 * @param n
	 *            Anzahl Telefonknoten
	 * @param xMax
	 *            Intervallgrenze fuer x-Koordinate.
	 * @param yMax
	 *            Intervallgrenze fuer y-Koordinate.
	 */
	public void generateRandomTelNet(int n, int xMax, int yMax) {
		Random rand = new Random();
		for (int i = 1; i < n; i++) {
			int x = rand.nextInt(xMax);
			int y = rand.nextInt(yMax);
			addTelKnoten(x, y);
		}
	}

	@Override
	public String toString() {
		// TODO
		if (optTelNet.isEmpty())
			throw new IllegalStateException(
					"computeOptTelNet() noch nicht aufgerufen");

		StringBuilder netz = new StringBuilder();
		netz.append("Knoten: ");
		for (TelKnoten k : nodes.keySet()) {
			netz.append("(" + k.x + "," + k.y + ") ");
		}
		netz.append("\nKosten fuer eine minimale Verbindung: "
				+ getOptTelNetKosten());
		netz.append("\nVerbindungen: " + getOptTelNet());
		return netz.toString();
	}

	/**
	 * 
	 * @param args
	 *            wird nicht benutzt.
	 */
	public static void main(java.lang.String[] args) {
		TelNet sampleNet = new TelNet(7);
		sampleNet.addTelKnoten(1, 1);
		sampleNet.addTelKnoten(3, 1);
		sampleNet.addTelKnoten(4, 2);
		sampleNet.addTelKnoten(3, 4);
		sampleNet.addTelKnoten(7, 5);
		sampleNet.addTelKnoten(2, 6);
		sampleNet.addTelKnoten(4, 7);
		// sampleNet.addTelKnoten(20, 20);
		sampleNet.computeOptTelNet();
		System.out.println(sampleNet);
		sampleNet.drawOptTelNet(7, 7);

		TelNet randomNet = new TelNet(100);
		randomNet.generateRandomTelNet(1000, 1000, 1000);
		randomNet.addTelKnoten(4, 7);
		randomNet.computeOptTelNet();
		System.out.println(randomNet);
		randomNet.drawOptTelNet(1000, 1000);

	}

}
