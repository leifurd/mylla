/*
 * Viðmótsforritun - Verkefni 1 - Reiknivél
 * Leifur Daníel Sigurðarson
 * 
 */

package is.hi.mylla.vidmot;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Leifur Daníel Sigurðarson <lds2@hi.is>
 * @date
 * Háskóli Íslands
 */
public class Hringur extends Ped{
    
    /**
     * Atburðarhandler fyrir þegar músin er dregin 
     * Peðið er fært 
     */
    private final EventHandler<MouseEvent> mouseDragged =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    faeraHlut( (Circle)ped, event);
                }
            };
    
     /***
     * Atburðarhandler fyrir þegar músinni er sleppt 
     * Peðið er sett á mylluborðið 
     */
    private final EventHandler<MouseEvent> mouseReleased =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println (" Mús sleppt ");
                    bord.setjaABord( (int)((Circle)ped).getCenterX(),
                               (int)((Circle)ped).getCenterY());
                }
            };
    
    
    
    public Hringur (MyllaPane b, Color l) {
            super(b);
            ped = new Circle(100, 100, 10);
            ped.setOnMouseDragged(mouseDragged);
            ped.setOnMouseReleased(mouseReleased);
            ped.setFill(l);
    }
    
    
         /**
     * Færir  ferning
     * @param s peðið 
     * @param event upplýsingar um released atburðurinn - notum (x,y)
     */
    @Override
    protected void faeraHlut(Shape s, MouseEvent event) {                                  
        ((Circle)s).setCenterX(event.getX());
        ((Circle)s).setCenterY(event.getY());
    }  
    
}
