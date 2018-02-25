
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
 * @author Ebba Þóra Hvannberg ebba@hi.is
 */
public class MyllaAdalController implements Initializable {
    
    @FXML
    private Label skilabod;         // Skilaboð
    @FXML
    private MyllaPane myllaBord;    // Mylluborðið 
  
    @FXML
    private Canvas mittCanvas;      // Teiknisvæði 
    @FXML
    private ToggleGroup leikmennToggle;
    @FXML
    private RadioButton jLeikmadur1;
    @FXML
    private RadioButton jLeikmadur2;
    private NyrLeikurDialogController sDialogController;
    private Mylla mylla = new Mylla(); //Mylla vinnsluklasi
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myllaBord.setAdal(this);
        GraphicsContext g = mittCanvas.getGraphicsContext2D();
        myllaBord.teiknaGrunnbord(g);
        myllaBord.smidaMylluArray(this);
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
        sDialogController.hefjaLeik();
    }

    @FXML
    private void haettaHandler(ActionEvent event) {
        Platform.exit();
    }

    
}
