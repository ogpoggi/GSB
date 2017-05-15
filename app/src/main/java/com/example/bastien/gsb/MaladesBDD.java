package com.example.bastien.gsb;

/**
 * Created by BASTIEN on 10/11/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class MaladesBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "bddMalades.db";
    private static final String TABLE_MALADES = "table_malades";

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public MaladesBDD(Context context) {
        //On instancie l'objet de la classe permettant la gestion de la BDD
        maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open() {
        //on ouvre la BDD en lecture et écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public long ajoutMalade(Malade unMalade) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues valeurs = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        valeurs.put("NOM", unMalade.getNom());
        valeurs.put("PRENOM", unMalade.getPrenom());
        valeurs.put("GLYCEMIE", unMalade.getGlycemie());
        valeurs.put("POIDS", unMalade.getPoids());
        valeurs.put("TAILLE", unMalade.getTaille());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_MALADES, null, valeurs);
    }

    public void viderTable() {
        //Suppression de toutes les lignes de la table
        bdd.delete(TABLE_MALADES, null, null);
    }

    public int nombreMalades() {
        Cursor c = bdd.rawQuery("select * from TABLE_MALADES", null);
        int nombre = c.getCount();
        c.close();
        return nombre;
    }

    public ArrayList<Malade> getTousLesMalades() {
        ArrayList<Malade> LesMalades = new ArrayList<Malade>();
        Cursor leCurseur = bdd.rawQuery("select * from "+TABLE_MALADES, null);
        leCurseur.moveToFirst();
        while ( !leCurseur.isAfterLast()){
            Malade malade = new Malade(leCurseur.getInt(0),leCurseur.getString(1),leCurseur.getString(2),leCurseur.getDouble(3),leCurseur.getInt(4),leCurseur.getInt(5));
            LesMalades.add(malade);
            leCurseur.moveToNext();
        }
        leCurseur.close();
        return LesMalades;
        /*ArrayList<Malade> lesMalades = new ArrayList<Malade>();
        Cursor leCurseur = bdd.rawQuery("select * from TABLE_MALADES", null);
        leCurseur.moveToFirst();
        while (!leCurseur.isAfterLast()){
            Malade malade = new Malade(leCurseur.getString(1), leCurseur.getString(2), leCurseur.getDouble(3));
            lesMalades.add(malade);
            leCurseur.moveToNext();
        }
        leCurseur.close();
        return lesMalades;*/

    }

    public void supprimerMalade(int indice){
        bdd.delete(TABLE_MALADES,"ID = "+indice,null);
    }

    public String calculImcByMalade(){
        return null;
    }
    public ArrayList<String> getToutesLesinfos() {
        //A écrire
        //Renvoie les infos sur les malades
        return null;
    }
}
