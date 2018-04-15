/*
 * Viðmótsforritun - Verkefni 1 - Reiknivél
 * Leifur Daníel Sigurðarson
 * 
 */
package is.hi.mylla.vidmot;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

/**
 * FXML Controller class
 *
 * @author Leifur Daníel Sigurðarson <lds2@hi.is>
 */
public class NyrLeikurDialogController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField leikmadur1;
    @FXML
    private TextField leikmadur2;
    private MyllaAdalController myllaAdal;
    private String[] leikmenn = new String[2];

    /**
     * Initializes the controller class.

     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public String[] hefjaLeik(boolean leikurHafinn) {
        DialogPane p = new DialogPane();
        p.setContent(anchorPane);
        anchorPane.setVisible(true);
        
        Dialog d = new Dialog();        
        d.setDialogPane(p);
        d.setHeaderText("Mylla");
        d.setTitle("Hefja nýjan leik");
        
        ButtonType iLagi = new ButtonType("Í lagi",
            ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(iLagi);
        
        if(leikurHafinn){
        ButtonType heattaVid = new ButtonType("Hætta við",
            ButtonBar.ButtonData.CANCEL_CLOSE);
        d.getDialogPane().getButtonTypes().add(heattaVid);
        }
        
        Optional<ButtonType> utkoma = d.showAndWait();
            if (utkoma.isPresent() && (utkoma.get()
                    .getButtonData() == ButtonBar.ButtonData.OK_DONE)){
                leikmenn[0] = leikmadur1.getText();
                leikmenn[1] = leikmadur2.getText();
                return leikmenn;
            }
            return null;            
    }
}
