package com.example.bastien.gsb;

/**
 * Created by BASTIEN on 10/11/2016.
 */

import java.util.ArrayList;

/**
 *<b> Protocole est la classe qui définie un protocole</b>
 *<p>Il sert lors du calcul de la glycémie.</br>
 *Le protocole est caractérisé par les informations suivantes :</br>
 *<ul>
 *<li>Un numéro attribué définitivement</li>
 *<li>Un liste (arrayList) de Glycémie et insuline</li>
 *</ul>
 *</p>
 */
public class Protocole {

    /**
     *Le numéro d'un protocole n'est pas modifiable
     */
    private int numeroProtocole;
    private ArrayList<GlycemieInsuline> lesGlycemieInsuline;

    /**
     *Constructeur de la classe Protocole
     *Liste GlycemieInsuline instanciée à la construction du protocole
     *@param unNumero lesGlycemieInsuline
     */
    public Protocole(int unNumero)	{
        this.numeroProtocole = unNumero;
        lesGlycemieInsuline = new ArrayList<GlycemieInsuline>();
    }

    /**
     *Retourne le Numero du Protocole
     *@return numeroProtocole int
     */
    public int getNumeroProtocole()	{
        return this.numeroProtocole;
    }




    /**
     *Sert a ajouter des nouvelles doses d'insuline en fonction de la glycemie
     *@param uneGlycemieInsuline
     */
    public void ajouter(GlycemieInsuline uneGlycemieInsuline)	{
        this.lesGlycemieInsuline.add(uneGlycemieInsuline);
    }


    /**
     *C'est le calcul de la dose d'insuline nécessaire au malade en fonction de sa glycémie
     *@param uneGlycemie double
     *@return retour int
     */
    public Integer insuline(double uneGlycemie)	{
        int retour =0;
        for(GlycemieInsuline g: lesGlycemieInsuline){
            double inf = g.getGlycemieInf();
            double sup = g.getGlycemieSup();
            if(uneGlycemie > inf && uneGlycemie <= sup){
               retour = g.getInsuline();
            }
        }
        return retour;
    }
}
