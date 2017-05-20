package com.example.bastien.gsb;

import java.io.Serializable;

/**
 * Created by BASTIEN on 10/11/2016.
 */

/**
 * Cette classe  sert a instancier les malades
 */
public class Malade implements Serializable{

    /**
     * <ul>Déclaration des Variables correspondant aux malades :
     * <li>un Id en int</li>
     * <li>un nom en String</li>
     * <li>un prenom en String</li>
     * <li>la Glycemie en Double</li>
     * </ul>
     */
    private int id;
    private String nom;
    private String prenom;
    private double glycemie;
    private double poids;
    private double taille;

    /**
     * Constructeur classe Malade
     * @param unNom String
     * @param unPrenom String
     * @param uneGlycemie double
     */
    public Malade(int id, String unNom, String unPrenom, double uneGlycemie, double unPoids, double uneTaille) {
        this.id = id;
        this.nom = unNom;
        this.prenom = unPrenom;
        this.glycemie = uneGlycemie;
        this.poids = unPoids;
        this.taille = uneTaille;
    }


    /**
     * Retourne l'id du malade
     * @return id int
     */
    public int getId() {
        return this.id;
    }

    /**
     *  Met à jour l'id
     * @param unId int
     */
    public void setId(int unId) {
        this.id = unId;
    }


    /**
     * Retourne le nom du malade
     * @return nom String
     */
    public String getNom() {
        return this.nom;
    }


    /**
     * Retourne le prenom du malade
     * @return Prenom String
     */
    public String getPrenom() {
        return this.prenom;
    }


    /**
     * Retourne la glycemie du malade
     * @return Glycemie double
     */
    public double getGlycemie() {
        return this.glycemie;
    }


    public double getPoids() {
        return this.poids;
    }

    public double getTaille() {
        return this.taille;
    }


    /**
     * Retourne le nom le prenom et la glycemie du malade pour l'affichage
     * @return nom String
     * @return prenom String
     * @return Glycemie double
     */


    public String getInfoPatient(){
        return  "ID : "+id +" "+"NOM: "+nom +"  " +prenom + "  "+"Glycemie: "+ glycemie +"  "+"Poids: "+poids+"  "+"Taille: "+taille;
    }
    @Override
    public String toString() {
        return nom +"  "+prenom;
    }
}