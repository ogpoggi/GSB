package com.example.bastien.gsb;

/**
 * Created by BASTIEN on 10/11/2016.
 */

/**
 *<p>GlycemieInsuline est la classe qui sert à créer une règle prenant en compte :
 * <ul>
 *     <li>Double pour le taux de glycémie inférieur</li>
 *     <li>Double pour le taux de glycémie supérieur</li>
 *     <li>Int pour l'insuline</li>
 * </ul>
 * </p>
 *
 * @author BASTIEN POGGI
 */

public class GlycemieInsuline {

    /**
     * Déclaration des variables glycemieInf en double   glycemieSup en double   et insuline en int
     */
    private double glycemieInf;
    private double glycemieSup;
    private int insuline;

    /**
     *Constructeur GlycémieInsuline
     *@param uneGlycemieInf double
     *@param uneGlycemieSup double
     *@param uneInsuline int
     */
    public GlycemieInsuline(double uneGlycemieInf, double uneGlycemieSup, int uneInsuline)
    {
        this.glycemieInf = uneGlycemieInf;
        this.glycemieSup = uneGlycemieSup;
        this.insuline = uneInsuline;
    }

    /**
     *Cette fonction retourne le taux de glycemie inférieure rentrée dans le constructeur
     *@return Glycemie inférieure double
     */
    public double getGlycemieInf()	{
        return this.glycemieInf;
    }

    /**
     *Cette fonction retourne le taux de glycemie supérieure rentrée dans le constructeur
     *@return Glycémie supérieure double
     */
    public double getGlycemieSup()	{
        return this.glycemieSup;
    }

    /**
     *Cette fonction retourne l'insuline
     *@return insuline int
     */
    public int getInsuline()	{
        return this.insuline;
    }
}
