// O. Bittel;
// 18.10.2011

package aufgabe2.graph;
import java.util.List;

/**
 * Graph, der gerichtet oder ungerichtet sein kann.
 * Der Graph ist gewichtet. Falls keine Gewichte angegeben werden,
 * dann sind die Gewichte implizit auf 1 gesetzt.
 * Der Graph einth??lt keine Mehrfachkanten und keine Schleifen
 * (Kanten von Knoten zu sich selbst).
 * @author Oliver Bittel
 * @since 21.10.2011
 * @param <V> Knotentyp.
 */
public interface Graph<V> {
    /**
     * F??gt neuen Knoten zum Graph dazu.
     * @param v Knoten
     * @return true, falls Knoten noch nicht vorhanden war.
     */
    boolean addVertex(V v);

    /**
     * F??gt neue Kante (mit Gewicht 1) zum Graph dazu.
     * @param v Startknoten
     * @param w Zielknoten
     * @throws IllegalArgumentException falls einer der Knoten
     * nicht im Graph vorhanden ist oder Knoten identisch sind.
     * @return true, falls Kante noch nicht vorhanden war.
     */
    boolean addEdge(V v, V w);

    /**
     * F??gt neue Kante mit Gewicht weight zum Graph dazu.
     * @param v Startknoten
     * @param w Zielknoten
     * @param weight Gewicht
     * @throws IllegalArgumentException falls einer der Knoten
     * nicht im Graph vorhanden ist oder Knoten identisch sind.
     * @return true, falls Kante noch nicht vorhanden war.
     */
    boolean addEdge(V v, V w, double weight);

    /**
     * Pr??ft ob Knoten v im Graph vorhanden ist.
     * @param v Knoten
     * @return true, falls Knoten vorhanden ist.
     */
    boolean containsVertex(V v);

    /**
     * Pr??ft ob Kante im Graph vorhanden ist.
     * @param v Startknoten
     * @param w Endknoten
     * @throws IllegalArgumentException falls einer der Knoten
     * nicht im Graph vorhanden ist.
     * @return true, falls Kante vorhanden ist.
     */
    boolean containsEdge(V v, V w);
    
    /**
     * Liefert Gewicht der Kante zur??ck.
     * @param v Startknoten
     * @param w Endknoten
     * @throws IllegalArgumentException falls einer der Knoten
     * nicht im Graph vorhanden ist.
     * @return Gewicht, falls Kante existiert, sonst 0.
     */
    double getWeight(V v, V w);

    /**
     * Liefert Anzahl der Knoten im Graph zur??ck.
     * @return Knotenzahl.
     */
    int getNumberOfVertexes();

    /**
     * Liefert Anzahl der Kanten im Graph zur??ck.
     * @return Kantenzahl.
     */
    int getNumberOfEdges();

    /**
     * Liefert Liste aller Knoten im Graph zur??ck.
     * @return Knotenliste
     */
    List<V> getVertexList();
    
    /**
     * Liefert Liste aller Kanten im Graph zur??ck.
     * @return Kantenliste.
     */
    List<Edge<V>> getEdgeList();

    /**
     * Liefert eine Liste aller adjazenter Knoten zu v.
     * Genauer: g.getAdjacentVertexList(v) liefert eine Liste aller Knoten w,
     * wobei (v, w) eine Kante des Graphen g ist.
     * @param v Knoten
     * @throws IllegalArgumentException falls Knoten v
     * nicht im Graph vorhanden ist.
     * @return Knotenliste
     */
    List<V> getAdjacentVertexList(V v);

    /**
     * Liefert eine Liste aller inzidenten Kanten.
     * Genauer: g.getIncidentEdgeList(v) liefert
     * eine Liste aller Kanten im Graphen g mit v als Startknoten.
     * @param v Knoten
     * @throws IllegalArgumentException falls Knoten v
     * nicht im Graph vorhanden ist.
     * @return Kantenliste
     */
    List<Edge<V>> getIncidentEdgeList(V v);
}
