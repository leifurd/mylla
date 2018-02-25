/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.mylla.vinnsla;

import is.hi.mylla.vidmot.MylluReitur;

/**
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is
 * @date
 * Háskóli Íslands
 */
public interface MyllaInterface {

     /**
    * Skilar true ef peð er þegar á reit n
    *
    * @param n línu nr reits
    * @param m dálk nr. reits
    * @return satt ef peð er á reit n annars ósatt
    */
    boolean erThegarABordi(int n, int m, MylluReitur[][] x);
    
    /**
     * Get aðferð fyrir tilviksbreytuna nuverandiLeikmadur
     *
     * @return
     */
    int getNuverandiLeikmadur();
    
    /**
     * Leikmaður n gerir
     *
     * @param n leikmaður
     */
    void setNuverandiLeikmadur(int n);
    
    /**
     * Setur peð á reit [n,m]
     *
     * @param n línu nr. reits
     * @param m dálks nr. reits
     */
    void setjaABord(int n, int m, MylluReitur[][] x);
    
    /**
     * Skilar boolean eftir því hvort vinningur sé kominn eða ekki
     *
     * @return boolean true ef vinningur, annars false
     */
    boolean vinningur(MylluReitur[][] x);
    
    
}
