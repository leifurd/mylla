
package is.hi.mylla.vidmot;



import is.hi.mylla.vinnsla.Mylla;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

/**
 * Viðmótshlutur sem teiknar mylluborð, býr til peð og meðhöndlar aðgerð þegar 
 * peð er sett á borð 
 * @author Ebba Þóra Hvannberg ebba@hi.is 
 * @date 
 * Háskóli Íslands
 */
public class MyllaPane extends Pane {

    private MyllaAdalController mAdal;
    
    
    public MyllaPane () { 
        
    }
    

    // Vinnsluklasinn sem heldur utan um mylluborðið og leikmenn
    private final Mylla mittBord = new Mylla();
    public final MylluReitur myllureitir [][] = new MylluReitur[3][3]; //Fylki af öllum myllureitum
   
    
    
    /**
     * Athugar á hvaða reit peð er, hvort peð er þegar á þeim reit setur peðið á
     * þann reit og athugar hvort það er vinningur.
     *
     * @param x x-gildi hnits
     * @param y y-gildi hnits
     */
    public void setjaABord(int x, int y) {
        int reitur[] = finnaReit(x, y);
        
        if (checkReitur(reitur))return;
        if (checkBounds(reitur))return;
        mittBord.setjaABord(reitur[0],reitur[1], myllureitir);
        mAdal.birtaVilluskilaboð("Peð lendir á borði"); 
        if (mittBord.vinningur(myllureitir))
            mAdal.birtaVilluskilaboð("Vinningur! Leikmaður " + mittBord.getNuverandiLeikmadur() + " vann"); 
        
          
    }
    
    /**
     * Ef reitur inniheldur núþegar peð
     * @param reitur
     * @return true ef reitur er upptekinn
     */
    private boolean checkReitur(int[] reitur) {
        if (myllureitir[reitur[0]][reitur[1]].getLeikmadur()>0) {
            mAdal.birtaVilluskilaboð("Reitur upptekinn, reyndu aftur");
            return true;
        }
        return false;
    }

    /**
     * Ef peð lendir utan borðs
     * @param reitur
     * @return true ef  peð lendir utan borðs
     */
    private boolean checkBounds(int[] reitur) {
        
        if (reitur[0]==-1) {
            mAdal.birtaVilluskilaboð("Peð lendir utan borðs, reyndu aftur");
            return true;
        }
        return false;
    }
    
    public void setjaLeikmann(int n){
        mittBord.setNuverandiLeikmadur(n);
    }

    /**
     * Finnur línu og dálksgildi reits fyrir gefin x,y hnit
     * @param x x-hnit
     * @param y y-hnit
     * @return fylki sem inniheldur línu númer og dálknúmer
     */
    private int []finnaReit (int x, int y) {
        for (int i = 0; i<myllureitir.length; i++) {
            for (int j = 0; j<myllureitir.length; j++) {
                if (myllureitir[i][j].erInnan(x, y)) {
                    return new int[] {i,j};
                }
                
            }
        }
      return new int[] {-1,-1}; 
    }
    
    public void teiknaGrunnbord(GraphicsContext g){
        g.setStroke(Color.BLUE);
        g.strokeLine(160, 200, 280, 200);
        g.strokeLine(160, 240, 280, 240);
        g.strokeLine(200, 160, 200, 280);
        g.strokeLine(240, 160, 240, 280);
    }
    
    public void leikmadurGerir(int leikmadur){
        nyttPed(leikmadur);
    }
    

    
    /**
     * Setur út nýtt peð fyrir leikmann l (ferning eða hring) 
     * ef fjöldi peða hefur ekki náð hámarki
     * @param l LEIKMADUR1 eða LEIKMADUR2
     */
    private void nyttPed(int leikmadur) {
        Ped s;
        if(leikmadur == 1)
            s = new Ferningur(this, Color.RED);
        else 
            s = new Hringur(this, Color.GREEN);
        this.getChildren().add(s.getPed());
    }

    /***
     * Frumstillir teningu í aðalcontroller 
     * @param aThis 
     */
    void setAdal(MyllaAdalController aThis) {
        mAdal = aThis;
    }

    public void smidaMylluArray(MyllaAdalController myllaAdalController) {
        for (int i = 0; i < myllureitir.length; i++) {
            for (int j = 0; j < myllureitir.length; j++) {
                myllureitir[i][j] = new MylluReitur(180 + (i * 40), 180 + (j * 40), 40, 40, 0);
            }
        }
    }
    
    public void setMylluArray(int n, int m, int l){
        myllureitir[n][m].setLeikmadur(l); 
    }

    
    
}
