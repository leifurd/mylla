
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
    
   
    
    /**
     * Athugar á hvaða reit peð er, hvort peð er þegar á þeim reit setur peðið á
     * þann reit og athugar hvort það er vinningur.
     *
     * @param x x-gildi hnits
     * @param y y-gildi hnits
     */
    public void setjaABord(int x, int y) {

        mAdal.birtaVilluskilaboð("peð lendir á borði");  
       
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
        // Forritun þessarar aðferðar er ólokið 
        Ped s;
        if(leikmadur == 2)
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

    
    
}
