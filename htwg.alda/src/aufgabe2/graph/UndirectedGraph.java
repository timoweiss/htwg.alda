// O. Bittel;
// 18.10.2011

package aufgabe2.graph;

/**
 * Graph mit ungerichteten Kanten.
 * Es ist zu beachten, dass nach erfolgreichem Einf??gen der Kante (v,w)
 * sowohl (v,w) als auch (w,v) im Graph enthalten ist.
 * Pr??ziser: Falls g.addEdge(v,w) == true, dann gilt g.contains(v,w) == true und
 * g.contains(w,v) == true.
 * @author Oliver Bittel
 * @since 21.10.2011
 * @param <V> Knotentyp.
 */
public interface UndirectedGraph<V> extends Graph<V> {
    /**
     * Liefert Grad des Knotens v zur??ck.
     * Das ist die Anzahl der adjazenten Knoten bzw. inzidenten Kanten zu v.
     * @param v Knoten
     * @throws IllegalArgumentException falls Knoten v
     * nicht im Graph vorhanden ist.
     * @return Knotengrad
     */
    int getDegree(V v);
}

