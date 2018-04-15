/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.mylla.vinnsla;

/**
 *
 * @author Leifur Daníel Sigurðarson lds2@hi.isw
 * Háskóli Íslands
 */
public class Stigatafla {
    
    private static int score[] = new int[2]; //Array með stigum leikmanna
    
    /**
     * Hækkar stig viðeigandi leikmanns
     * @param l númer leikmanns
     */
    public static void updateStig(int l){
        if(l==1) score[0]++;
        else score[1]++;
    }
    
    /**
     * Skilar stigum í array
     * @return 
     */
    public int[] getStigatafla() {
        return score;
    }
    
    /**
     * Endursetur stigatöflu
     */
    public void resetStigatafla() {
        score[0]=0;
        score[1]=0;
    }
}
