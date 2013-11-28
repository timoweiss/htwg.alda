// O. Bittel;
// 18.10.2011

package aufgabe2.graph;
import java.util.List;

/**
 * Graph mit gerichteten Kanten.
 * Es ist zu beachten, dass g.addEdge(v,w) 
 * eine gerichtete Kante von v nach w einf??gt.
 * @author Oliver Bittel
 * @since 21.10.2011
 * @param <V> Knotentyp.
 */
public interface DirectedGraph<V> extends Graph<V> {

    /**
     * Liefert Eingangsgrad des Knotens v zur??ck.
     * Das ist die Anzahl der Kanten mit Zielknoten v.
     * @param v Knoten
     * @throws IllegalArgumentException falls Knoten v
     * nicht im Graph vorhanden ist.
     * @return Knoteneingangsgrad
     */
    int getInDegree(V v);

    /**
     * Liefert Ausgangsgrad des Knotens v zur??ck.
     * Das ist die Anzahl der Kanten mit Quellknoten v.
     * @param v Knoten
     * @throws IllegalArgumentException falls Knoten v
     * nicht im Graph vorhanden ist.
     * @return Knotenausgangsgrad
     */
    int getOutDegree(V v);

    /**
     * Liefert die Liste aller Vorg??ngerknoten zu v zur??ck.
     * Das sind alle die Knoten, von denen eine Kante zu v f??hrt.
     * @param v Knoten
     * @throws IllegalArgumentException falls Knoten v
     * nicht im Graph vorhanden ist.
     * @return Knotenliste
     */
    List<V> getPredecessorVertexList(V v);

    /**
     * Liefert die Liste aller Nachfolgerknoten zu v zur??ck. 
     * Das sind alle die Knoten, zu denen eine Kante von v f??hrt.
     * Methode leistet dasselbe wie
     * {@link Graph#getAdjacentVertexList(java.lang.Object) getAdjacentVertexList}
     * und ist aufgrund des bequemeren Namens eingf??hrt.
     * @param v Knoten
     * @throws IllegalArgumentException falls Knoten v
     * nicht im Graph vorhanden ist.
     * @return Knotenliste
     */
    List<V> getSuccessorVertexList(V v);

    /**
     * Liefert die Liste aller von Knoten v wegf??hrenden Kanten.
     * Methode leistet dasselbe wie
     * {@link Graph#getIncidentEdgeList(java.lang.Object) getIncidentEdgeList}
     * und ist aufgrund des bequemeren Namens eingef??hrt.
     * @param v Knoten
     * @throws IllegalArgumentException falls Knoten v
     * nicht im Graph vorhanden ist.
     * @return Kantenliste
     */
    List<Edge<V>> getOutgoingEdgeList(V v);

    /**
     * Liefert die Liste aller zu Knoten v hinf??hrenden Kanten.
     * @param v Knoten
     * @throws IllegalArgumentException falls Knoten v
     * nicht im Graph vorhanden ist.
     * @return Kantenliste
     */
    List<Edge<V>> getIncomingEdgeList(V v);
}
