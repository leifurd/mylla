package is.hi.mylla.vinnsla;

/**
 *
 * @author Leifur Daníel Sigurðarson lds2@hi.is
 * Háskóli Íslands
 */
public interface MyllaInterface {

     /**
    * Skilar true ef peð er þegar á reit n
    *
    * @param n línu nr reits
    * @param m dálk nr. reits
    * @return satt ef peð er á reit n annars ósatt
    */
    boolean erThegarABordi(int n, int m);
    
    /**
     * Get aðferð fyrir tilviksbreytuna nuverandiLeikmadur
     *
     * @return
     */
    int getNuverandiLeikmadur();
    
    /**
     * Leikmaður n gerir
     *
     * @param n leikmaður
     */
    void setNuverandiLeikmadur(int n);
    
    /**
     * Setur peð á reit [n,m]
     *
     * @param n línu nr. reits
     * @param m dálks nr. reits
     */
    void setjaABord(int n, int m);
    
    /**
     * Skilar boolean eftir því hvort vinningur sé kominn eða ekki
     *
     * @return boolean true ef vinningur, annars false
     */
    int[] vinningur();
    
    /**
     * Athugar hvort búið sé að fylla alla reiti af peðum
     * @param x
     * @return boolean true ef allir reitir fullir
     */
    boolean maxPed();
    
    
}
