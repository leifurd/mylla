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
 *
 * Vinnsluklasi fyrir mylluborð
 *
 */
public class Mylla implements MyllaInterface {
    private int nuverandiLeikmadur; //Númer núverandi leikmanns

    @Override
    public boolean erThegarABordi(int n, int m, MylluReitur[][] x) {
        return x[n][m].getLeikmadur() > 0;
    }

    @Override
    public int getNuverandiLeikmadur() {
        return nuverandiLeikmadur;
    }

    @Override
    public void setNuverandiLeikmadur(int n) {
        nuverandiLeikmadur = n;
    }

    @Override
    public void setjaABord(int n, int m, MylluReitur[][] x) {
        if(!erThegarABordi(n,m,x))
            x[n][m].setLeikmadur(getNuverandiLeikmadur()); 
        }

    @Override
    public boolean vinningur(MylluReitur[][] x) {
        //athuga allar línur
        for(int k=1; k<=2; k++){
            for(int i=0; i<3;i++){
                //athuga alla dálka
                for(int j=0;j<3;j++){
                    //ef láréttur vinningur
                    if(x[i][0].getLeikmadur()== k && x[i][1].getLeikmadur() == k 
                            && x[i][2].getLeikmadur() == k) 
                        return true;
                    //ef lóðréttur vinningur
                    if(x[0][j].getLeikmadur() == k && x[1][j].getLeikmadur() == k 
                            && x[2][j].getLeikmadur() == k)
                        return true;
                }
            }
            //athuga vinning á ská, báða vegu
            //ef enginn vinningur: false
             if ((x[2][0].getLeikmadur() == k && x[1][1].getLeikmadur() == k
                && x[0][2].getLeikmadur() == k)
                || (x[0][0].getLeikmadur() == k && x[1][1].getLeikmadur() == k 
                        && x[2][2].getLeikmadur() == k))
                 return true;
        }
        return false;
    }
    
    


}