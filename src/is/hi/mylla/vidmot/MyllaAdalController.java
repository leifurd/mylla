
package is.hi.mylla.vidmot;

import is.hi.mylla.vinnsla.Mylla;
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
  
    @FXML
    private Canvas mittCanvas;      // Teiknisvæði 
    @FXML
    private ToggleGroup leikmennToggle;
    @FXML
    private RadioButton jLeikmadur1;

    @FXML
    private RadioButton jLeikmadur2;
    
    
    
    @FXML
    private NyrLeikurDialogController sDialogController;
    @FXML
    private Button jNyUmferd;
    
    int[] stigatafla = {0,0}; 
    private Mylla mylla = new Mylla(stigatafla); //Mylla vinnsluklasi

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

    @FXML
    private void nyrLeikurHandler(ActionEvent event) {
        nyrLeikur(true); 
    }

    private void nyrLeikur(boolean nyrleikur) {
        String[] nofn = sDialogController.hefjaLeik(nyrleikur);
        if (nofn != null){
            virkjaNyUmferdHnappa(false);
            jLeikmadur1.setText(nofn[0]);
            jLeikmadur2.setText(nofn[1]);
            myllaBord.nyrLeikur(stigatafla); 
            leikmennToggle.selectToggle(null);
        }
    }

    @FXML
    private void haettaHandler(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Gerir radio hnappa virka
     * @param true ef á að virkja, annars false.
     */
    public void virkjaRadioHnappa(Boolean b) {
        jLeikmadur1.setDisable(!b);
        jLeikmadur2.setDisable(!b);
    }

    @FXML
    private void nyUmferd(ActionEvent event) {
        
        int [] stig = mylla.getStigatafla();
        System.out.println(stig[0]+ "og "+ stig[1]);
        myllaBord.nyrLeikur(stig);
        mylla.setStigatafla(stig);
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
    
    String[] getNames(){
        String[] l = {jLeikmadur1.getText(), jLeikmadur2.getText()};
        return l;
    }

    GraphicsContext getCanvas() {
        return mittCanvas.getGraphicsContext2D();
    }
    
    public void show(){
        int[] stig = mylla.getStigatafla();
        System.out.println(stig[0] + "og " + stig[1]);
    }
}
