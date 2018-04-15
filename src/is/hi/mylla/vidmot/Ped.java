
package is.hi.mylla.vidmot;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import static javafx.scene.paint.Color.*;

/**
 * Abstract klasi sem útfærir peð á mylluborði. Getur fært peð 
 * @author Leifur Daníel Sigurðarson    lds2@hi.is
 * @date Febrúar 2018
 * Háskóli Íslands
 */
public abstract class Ped {

    protected Shape ped;
    protected MyllaPane bord;
    protected Color litur;
    
    
    public Ped(MyllaPane b) {
        bord = b;
    }
    
    /**
     * Skilar ped
     * @return 
     */
    public Shape getPed() {
        return ped;
    }

    /**
     * Færir  peð s í staðsetningu (x,y) í event 
     *
     * @param s    hluturinn sem á að færa 
     * @param event upplýsingar um released atburðurinn - notum (x,y)
     */
    protected abstract void faeraHlut(Shape s, MouseEvent event);
    
    /**
     * Færir peð aftur á upphafsreit
     * @param l int númer leikmanns
     */
    public void pedTilBaka(int l){
        if (l==1){
            ((Rectangle)ped).setX(300);
            ((Rectangle)ped).setY(300);
        }
        else{
            ((Circle)ped).setCenterX(120);
            ((Circle)ped).setCenterY(120);
        }
    }
    
    /**
     * Færir peð í miðju reits
     * @param midjuHnit array, index: 0=x-hnit, 1=y-hnit, 2=nr.leikmanns. 
     */
    public void pedIMidju(int[] midjuHnit){
        if (midjuHnit[2]==1){
            ((Rectangle)ped).setX(midjuHnit[0]-10);
            ((Rectangle)ped).setY(midjuHnit[1]-10);
        }
        else{
            ((Circle)ped).setCenterX(midjuHnit[0]);
            ((Circle)ped).setCenterY(midjuHnit[1]);
        }
    }
    
    /**
     * Litar peðið blátt
     * @param l númer leikmanns
     */
    public void vinningsPed(int l){
        if (l == 1)
            ((Rectangle)ped).setFill(BLUE);
        else
            ((Circle)ped).setFill(BLUE);
    }
}

