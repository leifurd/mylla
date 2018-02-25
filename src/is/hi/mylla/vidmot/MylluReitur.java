
package is.hi.mylla.vidmot;

/**
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is
 *
 * Klasi sem heldur utan um staðsetningu og stærð reits myllunnar
 */
public class MylluReitur {

    private final int xPos;

    public int getxPos() {
        return xPos;
    }
    private final int yPos;
    private final int breidd;
    private final int haed;
    private int leikmadur;

    
    /**
     * Smiður sem býr til reit á myllu í staðsetningu (x,y) með breidd og hæð
     *
     * @param x
     * @param y
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
    
    public void setLeikmadur(int n) {
        leikmadur = n;
    }

    
    
}
