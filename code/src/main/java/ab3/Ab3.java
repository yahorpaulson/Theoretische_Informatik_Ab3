package ab3;

public interface Ab3 {

	/**
	 * Diese Methode liefert ein String-Array der Länge 1, 2, oder 3, in
	 * dem die Matrikelnummern der Gruppenmitglieder enthalten sind.
	 *
	 * @return String-Array mit Matrikelnummern
	 */
	public String[] matriculationNumbers();

	/**
	 * Liefert eine Baumzerlegung mit minimaler Weite (sprich: mit der
	 * Baumweite des Graphen). Dies kann z.B. durch einen
	 * Brute-Force-Algorithmus, der alle Eliminations-Reihenfolgen
	 * durchprobiert, geschehen. Allerdings gibt es auch intelligentere
	 * Vorgehensweisen.
	 *
	 * Bewertung: max. 2 Punkte.
	 *
	 * @param graph der zu zerlegende Graph (ungerichtet)
	 */
	public TreeDecomposition decomposeExact(boolean[][] graph);

	/**
	 * Liefert eine Baumzerlegung mit guter, aber nicht zwangsweise
	 * minimaler Baumweite, dafür aber schneller, als die exakte Methode.
	 * Recherchieren Sie eigenständig (gerne auch mittels Ihrer
	 * favorisierten LLM) geeignete Verfahren (Stichwort: Min-Degree- oder
	 * Min-Fill-Heuristik).
	 *
	 * Bewertung: max. 3 Punkte.
	 *
	 * @param graph der zu zerlegende Graph (ungerichtet)
	 */
	public TreeDecomposition decomposeFast(boolean[][] graph);

	/**
	 * Liefert eine Baumzerlegung durch zufälliges Generieren von einer
	 * bestimmten Anzahl Baumzerlegungen, und dann die Selektion der besten
	 * (also der mit der geringster Weite) davon. Dies kann z.B. durch
	 * zufälliges Generieren von Eliminations-Reihenfolgen geschehen.
	 *
	 * Bewertung: max. 2 Punkte.
	 *
	 * @param graph der zu zerlegende Graph (ungerichtet)
	 * @param samples die Anzahl zufällig zu generierender Baumzerlegungen
	 */
	public TreeDecomposition decomposeRandom(boolean[][] graph, int samples);

	/**
	 * Liefert eine Baumzerlegung in linearer Zeit O(n). Dies darf auch die
	 * triviale Baumzerlegung sein.
	 *
	 * Bewertung: max. 1 Punkte.
	 *
	 * @param graph der zu zerlegende Graph (ungerichtet)
	 */
	public TreeDecomposition decomposeTrivial(boolean[][] graph);

	/**
	 * Überprüft, ob ein bestimmter Baum tatsächlich alle Bedingungen einer
	 * Baumzerlegung erfüllt.
	 *
	 * Bewertung: max. 3 Punkte.
	 *
	 * @param td eine Baumzerlegung
	 *
	 * @return true, falls gültig; false, falls unglültig
	 */
	public boolean isValid(TreeDecomposition td);

	/**
	 * Ermittelt die Weite einer Baumzerlegung.
	 *
	 * Bewertung: max. 1 Punkte.
	 *
	 * @param td eine Baumzerlegung
	 *
	 * @return die Weite der Baumzerlegung
	 */
	public int getWidth(TreeDecomposition td);

	/**
	 * Löst das NP-vollständige Baumweiten-Entscheidungsproblem: Hat ein
	 * Graph eine Baumweite kleiner-gleich einer bestimmten Schranke?
	 *
	 * @param graph ein ungerichteter Input-Graph
	 * @param width die Schranke für die Baumweite
	 *
	 * Bewertung: max. 3 Punkte.
	 *
	 * @return true, falls der Graph eine Baumweite kleiner-gleich der
	 *         Schranke hat; sonst false
	 */
	public boolean hasTreewidthBound(boolean[][] graph, int width);
}
