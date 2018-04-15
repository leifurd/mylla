
package is.hi.mylla.vidmot;

import is.hi.mylla.vinnsla.Mylla;
import is.hi.mylla.vinnsla.Stigatafla;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * Controller fyrir mylluna. Er núna mjög einfaldur 
 * 
 * @author Leifur Daníel Sigurðarson lds2@hi.is 
 */
public class MyllaAdalController implements Initializable {
    
    @FXML
    private Label skilabod;         // Skilaboð
    @FXML
    private MyllaPane myllaBord = new MyllaPane();    // Mylluborðið 
    private Stigatafla stigatafla = new Stigatafla(); // Stigataflan
    @FXML
    private Canvas mittCanvas;      // Teiknisvæði 
    @FXML
    private ToggleGroup leikmennToggle; // Togglegroup fyrir leikmenn
    @FXML
    private RadioButton jLeikmadur1; // Leikmaður 1 radiobutton
    @FXML
    private RadioButton jLeikmadur2; // Leikmaður 2 radiobutton
    @FXML
    private NyrLeikurDialogController sDialogController; //Dialog fyrir nýjan leik
    @FXML
    private Button jNyUmferd; // Takki; ný umferð
    private Mylla mylla = new Mylla(); //Mylla vinnsluklasi

    
    /**
     * Initialize fallið. Teiknar grunnborð og stigatöflu. Endursetur breytur.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myllaBord.setAdal(this);
        myllaBord.teiknaGrunnbord(mittCanvas.getGraphicsContext2D());
        nyrLeikur(false);
        myllaBord.synaStig(mittCanvas.getGraphicsContext2D());
    }    

   /**
     * Birtir villuskilaboð í strengnum s
     *
     * @param s villuskilaboð
     */
    public void birtaVilluskilaboð(String s) {
          skilabod.setText(s);
    }

    /**
     * Þegar ýtt er á radiobutton fyrir leikmann
     * Skilgreinir hvaða leikmaður á að gera
     * Slekkur á radio buttons.
     * @param event 
     */
    @FXML
    private void leikmadurHandler(ActionEvent event) {
        RadioButton b = (RadioButton)event.getSource();
        int n = Integer.parseInt(b.getId());
        myllaBord.leikmadurGerir(n);
        myllaBord.setjaLeikmann(n);
        virkjaRadioHnappa(false);
    }
    
    /**
     * Setur nöfn á leikmönnum á RadioButtons
     * @param l1 Nafn á leikmanni 1
     * @param l2 Nafn á leikmanni 2
     */
    public void setLeikmenn(String l1, String l2){
        jLeikmadur1.setText(l1);
        jLeikmadur2.setText(l2);
    }

    /**
     * Þegar nýr leikur er valinn úr menu bar.
     * @param event 
     */
    @FXML
    private void nyrLeikurHandler(ActionEvent event) {
        nyrLeikur(true); 
    }

    /**
     * Kallar á NyrLeikurDialog
     * Endurskilgreinir nöfn leikmanna ef nöfnin eru ekki null.
     * Endursetur stigatöflu og mylluborði
     * @param nyrleikur 
     */
    private void nyrLeikur(boolean nyrleikur) {
        String[] nofn = sDialogController.hefjaLeik(nyrleikur);
        if (nofn != null){  //Ef nöfn eru ekki "null"
            virkjaNyUmferdHnappa(false);
            stigatafla.resetStigatafla();
            jLeikmadur1.setText(nofn[0]);
            jLeikmadur2.setText(nofn[1]);
            myllaBord.nyrLeikur(); 
            leikmennToggle.selectToggle(null);       
        }
    }
    
    /**
     * Þegar valið er "Hætta" í menubar 
     * Lokar forriti.
     * @param event 
     */
    @FXML
    private void haettaHandler(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Gerir radio hnappa virka
     * @param b true ef á að virkja, annars false.
     */
    public void virkjaRadioHnappa(Boolean b) {
        jLeikmadur1.setDisable(!b);
        jLeikmadur2.setDisable(!b);
    }

    /**
     * Þegar ýtt er á takkann "Ný umferð"
     * Endursetur mylluborð en heldur stigatöflu og nöfnum leikmanna.
     * @param event 
     */
    @FXML
    private void nyUmferd(ActionEvent event) {
        myllaBord.nyrLeikur();
        virkjaNyUmferdHnappa(false);
        leikmennToggle.selectToggle(null);
    }
    
    /**
     * Afvirkja eða virkja takka fyrir nýja umferð
     * @param b 
     */
    public void virkjaNyUmferdHnappa(boolean b) {
        jNyUmferd.setDisable(!b);
    }

    /**
     * Skilar nafni vinningshafa.
     * @param i int númer leikmanns
     * @return String nafn vinningshafa
     */
    String getVinningshafi(int i) {
        if (i==1)
            return jLeikmadur1.getText();
        return jLeikmadur2.getText();
    }
    
    /**
     * Skilar nöfnum leikmanna sem Array.
     * @return l
     */
    String[] getNames(){
        String[] l = {jLeikmadur1.getText(), jLeikmadur2.getText()};
        return l;
    }

    /**
     * Skilar GraphicsContext2D úr Canvas.
     * @return 
     */
    GraphicsContext getCanvas() {
        return mittCanvas.getGraphicsContext2D();
    }
}
