
package is.hi.mylla.vidmot;



import is.hi.mylla.vinnsla.Mylla;
import is.hi.mylla.vinnsla.Stigatafla;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;

/**
 * Viðmótshlutur sem teiknar mylluborð, býr til peð og meðhöndlar aðgerð þegar 
 * peð er sett á borð 
 * @author Leifur Daníel Sigurðarson lds2@hi.is
 * @date 
 * Háskóli Íslands
 */

public class MyllaPane extends Pane {

    private MyllaAdalController mAdal; //Tilvik af MyllaAdalController
    private final Ped[] s = new Ped[9]; //Array af peðum í númera röð
    private final Ped[] v = new Ped[9]; //Array af númerum reita sem peðin lenda á
    private final Stigatafla stigatafla = new Stigatafla(); //Tilvik af Stigatafla   
    // Vinnsluklasinn sem heldur utan um mylluborðið og leikmenn
    private Mylla mittBord = new Mylla();
 
    /**
     * Athugar á hvaða reit peð er, hvort peð sé núþegar á þeim reit, setur peðið á
     * þann reit og athugar hvort það er vinningur.
     *
     * @param x x-gildi hnits
     * @param y y-gildi hnits
     */
    public void setjaABord(int x, int y) {
        int reitur[] = mittBord.finnaReit(x, y);
        if (checkBounds(reitur))return; //Peð lendir utan borðs
        if (checkReitur(reitur))return; //Peð lendir á uppteknum reit
        mittBord.setjaABord(reitur[0],reitur[1]);
        mAdal.birtaVilluskilaboð("Peð lendir á borði"); 
        s[mittBord.getNuverandiPed()].pedIMidju(mittBord.finnaMidjuReits(x, y));
        v[(reitur[0]+(reitur[1]*3))]=s[mittBord.getNuverandiPed()];
        disablePed();
        int[] vinningur = mittBord.vinningur();
        if (vinningur!=null){        // Ef vinningur 
            sigurvegari(vinningur);
        }
        else if (mittBord.maxPed()){ // Ef mylluborð er fullt
            mAdal.birtaVilluskilaboð("Enginn vinningur, leik lokið"); 
            mAdal.virkjaNyUmferdHnappa(true);
        }
        else mAdal.virkjaRadioHnappa(true); //Leyfa næsta leikmanni að spila
    }

    /**
     * Birtir vinningsskilaboð
     * Virkjar ný umferðs hnapp
     * Uppfærir stigatöflu
     * Litar vinningspeð blátt
     * @param vinningur 
     */
    private void sigurvegari(int[] vinningur) {
        // Lita vinningspeð
        for(int i=0; i<3; i++){ 
            v[vinningur[i]].vinningsPed(mittBord.getNuverandiLeikmadur());
        }
        mAdal.birtaVilluskilaboð("Vinningur!  "
                + mAdal.getVinningshafi(vinningur[3]) + " vinnur þessa umferð.");
        mAdal.virkjaNyUmferdHnappa(true);
        stigatafla.updateStig(vinningur[3]);
        synaStig(mAdal.getCanvas());
    }

    /**
     * Slekkur á núverandi peði svo ekki sé hægt að færa það.
     */
    private void disablePed() {
        this.getChildren().get(mittBord.getNuverandiPed()).setDisable(true); 
    }
    
    /**
     * Ef reitur inniheldur núþegar peð
     * @param reitur
     * @return true ef reitur er upptekinn
     */
    private boolean checkReitur(int[] reitur) {
        if (mittBord.myllureitir[reitur[0]][reitur[1]].getLeikmadur()>0) {
            mAdal.birtaVilluskilaboð("Reitur upptekinn, reyndu aftur");
            s[mittBord.getNuverandiPed()]
                    .pedTilBaka(mittBord.getNuverandiLeikmadur());
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
    
    /**
     * Segir til um hver núverandi leikmaður er
     * @param n 
     */
    public void setjaLeikmann(int n){
        mittBord.setNuverandiLeikmadur(n);
    }

    
    /**
     * Teiknar mylluborð.
     * @param g 
     */
    public void teiknaGrunnbord(GraphicsContext g){
        g.setStroke(Color.BLUE);
        g.strokeLine(160, 200, 280, 200);
        g.strokeLine(160, 240, 280, 240);
        g.strokeLine(200, 160, 200, 280);
        g.strokeLine(240, 160, 240, 280);
    }
    
    /**
     * Teiknar stigatöflu.
     * @param g 
     */
    public void synaStig(GraphicsContext g) {
        g.clearRect(0, 280, 600, 600);
        String[] nafn = mAdal.getNames();
        int[] stig = stigatafla.getStigatafla();
        g.fillText("Stigatafla:", 100, 320);
        g.fillText(nafn[0] + ": " + stig[0], 100, 340);
        g.fillText(nafn[1] + ": " + stig[1],100,360);
        System.out.println(stig[0]+"og "+ stig[1]);

    }
    
    /**
     * Þegar leikmaður á leik, kalla á nyttPed
     * @param leikmadur 
     */
    public void leikmadurGerir(int leikmadur){
        nyttPed(leikmadur);
    }
    
    /**
     * Setur út nýtt peð fyrir leikmann l (ferning eða hring) 
     * ef fjöldi peða hefur ekki náð hámarki
     * @param l LEIKMADUR1 eða LEIKMADUR2
     */
    private void nyttPed(int leikmadur) {
        mittBord.nyttPed();
        if(leikmadur == 1)
            s[mittBord.getNuverandiPed()] = new Ferningur(this, Color.RED);
        else 
            s[mittBord.getNuverandiPed()] = new Hringur(this, Color.GREEN);
        this.getChildren().add(s[mittBord.getNuverandiPed()].getPed());
    }

    /***
     * Frumstillir teningu í aðalcontroller 
     * @param aThis 
     */
    void setAdal(MyllaAdalController aThis) {
        mAdal = aThis;
    }
    
    /**
     * Hreinsar peð af mylluborði
     * Býr til nýtt tilvik af Mylla vinnsluklasa
     * Teiknar stigatöflu, hreinsar skilaboð og virkir radio buttons.
     */
    public void nyrLeikur(){
        int fjoldi = getChildren().size();
        getChildren().remove(1, fjoldi);
        mittBord = new Mylla();
        mAdal.birtaVilluskilaboð("");
        mAdal.virkjaRadioHnappa(true);
        synaStig(mAdal.getCanvas());
    }
    

}
