/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.mylla.vinnsla;

import is.hi.mylla.vidmot.MyllaPane;
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
    public final MylluReitur[][] myllureitir = new MylluReitur[3][3]; //Fylki af öllum myllureitum
    private int nuverandiPed; //Númer núverandi peðs
    private int[] stigatafla = new int[2]; // Array sem heldur utanum stigatöflu


    public Mylla(int[] stigatafla){
        smidaMylluArray();
        nuverandiPed = -1; //nr peðs. Hækkar eftir hvert nýtt peð á borði
        this.stigatafla = stigatafla; //Setur stigatöflu
    }

    @Override
    public boolean erThegarABordi(int n, int m) {
        return myllureitir[n][m].getLeikmadur() > 0;
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
    public void setjaABord(int n, int m) {
        if(!erThegarABordi(n,m))
            myllureitir[n][m].setLeikmadur(getNuverandiLeikmadur()); 
        }

    @Override
    public int[] vinningur() {
        int [] v = new int [4];
        int nr = 0;
       
        //athuga leikmann 1 eða 2
        for(int k=1; k<=2; k++){
            //athuga allar línur
            for(int i=0; i<3;i++){
                //athuga alla dálka
                for(int j=0;j<3;j++){
                    //ef láréttur vinningur
                    if(myllureitir[i][0].getLeikmadur()== k && myllureitir[i][1].getLeikmadur() == k 
                            && myllureitir[i][2].getLeikmadur() == k){
                        v[0]=i; v[1]=i+3; v[2]=i+6; v[3]=k;
                        return v;
                    }
                    //ef lóðréttur vinningur
                    if(myllureitir[0][j].getLeikmadur() == k && myllureitir[1][j].getLeikmadur() == k 
                            && myllureitir[2][j].getLeikmadur() == k){
                        v[0]=3*j; v[1]=1+(3*j); v[2]=2+(3*j); v[3]=k;
                        return v;
                    }
                }
            }
            //athuga vinning á ská, báða vegu
            //ef enginn vinningur: null
            if (myllureitir[2][0].getLeikmadur() == k && myllureitir[1][1].getLeikmadur() == k
                && myllureitir[0][2].getLeikmadur() == k){
                    v[0]=2; v[1]=4; v[2]=6; v[3]=k;
                    return v;
            }
            if (myllureitir[0][0].getLeikmadur() == k && myllureitir[1][1].getLeikmadur() == k 
                && myllureitir[2][2].getLeikmadur() == k){
                    v[0]=0; v[1]=4; v[2]=8; v[3]=k;
                    return v;
            }
        }
    
        return null;
    }
    
    @Override
    public boolean maxPed(){
        int fjoldi=0;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (myllureitir[i][j].getLeikmadur()>0)
                        fjoldi++;
            }
        }
        if (fjoldi == 9) 
            return true;
        return false;
    }

    /**
     * Býr til 2d array sem heldur utanum staðsetningar á reitum á mylluborði
     * @param myllaAdalController
     */
    private void smidaMylluArray() {
        for (int i = 0; i < myllureitir.length; i++) {
            for (int j = 0; j < myllureitir.length; j++) {
                myllureitir[i][j] = new MylluReitur(180 + (i * 40), 180 + (j * 40), 40, 40, 0);
            }
        }
    }

    /**
     * Ákvarðar hvort leikmaður 1, 2 eða enginn sé á reit á mylluborði
     * @param n línunr. fylkis
     * @param m dálknúmer fylkis
     * @param l nr leikmanns
     */
    public void setMylluArray(int n, int m, int l) {
        myllureitir[n][m].setLeikmadur(l);
    }

    /**
     * Finnur línu og dálksgildi reits fyrir gefin x,y hnit
     * @param x x-hnit
     * @param y y-hnit
     * @return fylki sem inniheldur línu númer og dálknúmer
     */
    public int[] finnaReit(int x, int y) {
        for (int i = 0; i < myllureitir.length; i++) {
            for (int j = 0; j < myllureitir.length; j++) {              
                if (myllureitir[i][j].erInnan(x, y)) {
                    return new int[]{i, j}; 
                }
            }
        }
        return new int[]{-1, -1};
    }
    
    /**
     * Segir til um númer hvað nýjasta peðið í borðinu er.
     */
    public void nyttPed(){
        nuverandiPed++;
    }

    /**
     * Skilar númeri á núverandi peði sem verið er að spila út.
     * @return 
     */
    public int getNuverandiPed() {
        return nuverandiPed;
    }
    
    /**
     * Finnur miðju reits sem peð var sett í.
     * @param x x-hnit peðs
     * @param y y-hnit peðs
     * @return array með x- og y-hnitum miðj reitsins
     */
    public int[] finnaMidjuReits(int x, int y) {
        int[] skila = new int[3];
        int[] reiturNr = finnaReit(x, y);
        for (int i = 0; i < myllureitir.length; i++) {
            for (int j = 0; j < myllureitir.length; j++) {
                if (reiturNr[0] == i && reiturNr[1] == j){
                    skila[0] = 180 + (i * 40); 
                    skila[1] = 180 + (j * 40);
                    skila[2] = getNuverandiLeikmadur();
                    return skila;  
                }
            }
        }
        return null;
    }

    public void updateStig(int l){
        if(l==1) stigatafla[0]++;
        else stigatafla[1]++;
    }

    public int[] getStigatafla() {
        return stigatafla;
    }

    public void setStigatafla(int[] stigatafla) {
        this.stigatafla = stigatafla;
    }
    
}