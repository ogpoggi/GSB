package com.example.bastien.gsb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {


    private static int MYSECONDACTIVITY_REQUESTCODE=1;

    private ImageButton btnGsb;

    private RadioButton rdbProto1;
    private RadioButton rdbProto2;
    private EditText editGlycemie;
    private Button btnAfficherInsuline;
    private TextView tvResInsuline;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private EditText editNomMalade;
    private EditText editPrenomMalade;
    private EditText editPoids;
    private EditText editTaille;
    private Button btnAjoutMalade;
    private Button btnAfficherMalade;
    private Protocole leProtocole;
    public static MaladesBDD unMaladeBDD;
    private MapProtocoles uneMapProtocole;
    private Protocole p1 = new Protocole(1);
    private Protocole p2 = new Protocole(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView =(TextView) findViewById(R.id.textView);
        textView2 =(TextView) findViewById(R.id.textView2);
        textView3 =(TextView) findViewById(R.id.textView3);
        textView4 =(TextView) findViewById(R.id.textView4);
        rdbProto1 = (RadioButton) findViewById(R.id.rdbProto1);
        rdbProto2 = (RadioButton) findViewById(R.id.rdbProto2);
        editGlycemie = (EditText) findViewById(R.id.editGlycemie);
        btnAfficherInsuline = (Button) findViewById(R.id.btnAfficherInsuline);
        tvResInsuline = (TextView) findViewById(R.id.tvResInsuline);
        editNomMalade = (EditText) findViewById(R.id.editNomMalade);
        editPrenomMalade = (EditText) findViewById(R.id.editPrenomMalade);
        editPoids = (EditText) findViewById(R.id.editPoids);
        editTaille = (EditText) findViewById(R.id.editTaille);
        btnAjoutMalade = (Button) findViewById(R.id.btnAjoutMalade);
        btnAfficherMalade = (Button) findViewById(R.id.btnAfficherMalade);


        btnGsb= (ImageButton) findViewById(R.id.btnGsb);

        btnAfficherInsuline.setOnClickListener(clickListenerBtnAfficherInsuline);
        btnAfficherMalade.setOnClickListener(clickListenerBtnAfficherMalade);
        btnAjoutMalade.setOnClickListener(clickListenerBtnAjoutMalade);


        textView.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);
        editNomMalade.setVisibility(View.INVISIBLE);
        editPrenomMalade.setVisibility(View.INVISIBLE);
        editPoids.setVisibility(View.INVISIBLE);
        editTaille.setVisibility(View.INVISIBLE);
        btnAjoutMalade.setVisibility(View.INVISIBLE);

        btnGsb.setOnClickListener(clickListenerBtnGsb);
        unMaladeBDD = new MaladesBDD(this);
        unMaladeBDD.open();
        unMaladeBDD.viderTable();

        uneMapProtocole = new MapProtocoles();
        uneMapProtocole.initialiser();

    }

    private View.OnClickListener clickListenerBtnAfficherInsuline = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(rdbProto1.isChecked()){

                p1 = uneMapProtocole.getProtocole(1);
                Double gly = Double.parseDouble(editGlycemie.getText().toString());
                int insu = p1.insuline(gly);
                tvResInsuline.setText(String.valueOf(insu));

                if(Double.parseDouble(editGlycemie.getText().toString()) >= 2){
                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);
                    editNomMalade.setVisibility(View.VISIBLE);
                    editPrenomMalade.setVisibility(View.VISIBLE);
                    editPoids.setVisibility(View.VISIBLE);
                    editTaille.setVisibility(View.VISIBLE);
                    btnAjoutMalade.setVisibility(View.VISIBLE);
                    btnAfficherMalade.setVisibility(View.VISIBLE);
                }
            }
            if(rdbProto2.isChecked()){

                p2 = uneMapProtocole.getProtocole(2);
                Double glys = Double.parseDouble(editGlycemie.getText().toString());
                Integer insu = p2.insuline(glys);
                tvResInsuline.setText(insu.toString());


                if(Double.parseDouble(editGlycemie.getText().toString()) >= 2){
                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);
                    editNomMalade.setVisibility(View.VISIBLE);
                    editPrenomMalade.setVisibility(View.VISIBLE);
                    editPoids.setVisibility(View.VISIBLE);
                    editTaille.setVisibility(View.VISIBLE);
                    btnAjoutMalade.setVisibility(View.VISIBLE);
                    btnAfficherMalade.setVisibility(View.VISIBLE);
                }
            }
        }
    };



    private View.OnClickListener clickListenerBtnAjoutMalade = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String nom = editNomMalade.getText().toString();
            String prenom = editPrenomMalade.getText().toString();
            Double glyc = Double.parseDouble(editGlycemie.getText().toString());
            double poids = Double.parseDouble(editPoids.getText().toString());
            double taille = Double.parseDouble(editTaille.getText().toString());
            Malade unMalade = new Malade(1,nom, prenom, glyc, poids, taille);
            unMaladeBDD.ajoutMalade(unMalade);
            Toast.makeText(MainActivity.this,"Le malade a été ajouté avec succés" +" : "+ String.valueOf(unMaladeBDD.
                     nombreMalades())+" malades dans la base de données", Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this,"TEST="+MaBaseSQLite.CREATE_TABLE_MALADES,Toast.LENGTH_SHORT).show();
            editNomMalade.setText("");
            editPrenomMalade.setText("");
            editPoids.setText("");
            editTaille.setText("");
        }
    };

    private View.OnClickListener clickListenerBtnAfficherMalade = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ArrayList<String> lstMalades = new ArrayList<String>();
            ArrayList a = unMaladeBDD.getTousLesMalades();
            lstMalades.addAll(a);

            //Adapteur pour faire le lien entre la liste du programme et la liste de l'interface
            Intent intent = new Intent(MainActivity.this, AffMaladeActivity.class);
            intent.putExtra("message", lstMalades);
            startActivityForResult(intent, MYSECONDACTIVITY_REQUESTCODE);
        }
    };

    private View.OnClickListener clickListenerBtnGsb = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://www.siterb.sioklm.com"));
            startActivity(intent);
        }
    };


}
