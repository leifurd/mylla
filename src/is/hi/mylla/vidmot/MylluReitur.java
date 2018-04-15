
package is.hi.mylla.vidmot;

/**
 *
 * @author Leifur Daníel Sigurðarson lds2@hi.is
 *
 * Klasi sem heldur utan um staðsetningu og stærð reits myllunnar
 */
public class MylluReitur {

    private final int xPos;   // x-hnit
    private final int yPos;   // y-hnit
    private final int breidd; // breidd reits
    private final int haed;   // hæð reits
    private int leikmadur;    // nr leikmanns á reit

    
    /**
     * Smiður sem býr til reit á myllu í staðsetningu (x,y) með breidd og hæð
     *
     * @param x x-hnit
     * @param y y-hnit
     * @param b breidd
     * @param h hæð
     * @param l númer leikmanns á reit. 0 == Reitur laus
     */
    public MylluReitur(int x, int y, int b, int h, int l) {
        xPos = x;
        yPos = y;
        breidd = b;
        haed = h;
        leikmadur = l;
    }

    /**
     * Athugar hvort hnit (x,y) er innan reitsins
     *
     * @param x x-hnit sem er skoðað
     * @param y y-hnit sem er skoðað
     * @return satt ef (x,y) er innan reitsins, annars ósatt
     */
    public boolean erInnan(int x, int y) {
        return (x >= xPos-(breidd/2) && x <= (xPos + breidd/2)
                && y >= yPos-(haed/2) && y <= (yPos + haed/2));
    }
    
    /**
     * Skilar hvort/hvaða leikmaður er á reit
     * @return 0 == reitur laus, (1 eða 2) == leikmaður 1 eða 2
     */
    public int getLeikmadur() {
        return leikmadur;
    }
    
    /**
     * Skilgreinir hver núverandi leikmaður er.
     * @param n 
     */
    public void setLeikmadur(int n) {
        leikmadur = n;
    }  
}
