package com.example.bastien.gsb;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static android.R.drawable.checkbox_off_background;
import static android.R.drawable.checkbox_on_background;

public class AffMaladeActivity extends AppCompatActivity {


    private ListView lstVue;

    private static MyListAdapter adapter;
    private Button btnRetour;
    private Button btnMail;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aff_malade);
        btnMail = (Button) findViewById(R.id.btnMail);
        btnRetour = (Button) findViewById(R.id.btnRetour);
        lstVue = (ListView) findViewById(R.id.lstVue);
        ArrayList<Malade> test = MainActivity.unMaladeBDD.getTousLesMalades();
        adapter = new MyListAdapter(this,R.layout.list_item,MainActivity.unMaladeBDD.getTousLesMalades());
        lstVue.setAdapter(adapter);
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AffMaladeActivity.this.finish();
            }
        });

        btnMail.setOnClickListener(clickListenerMail);
    }

    private View.OnClickListener clickListenerMail = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ArrayList<Malade> lstMalades = MainActivity.unMaladeBDD.getTousLesMalades();

            Intent returnIt = new Intent(Intent.ACTION_SEND);
            String[] tos = { "bastienpoggi@msn.com" };
            returnIt.putExtra(Intent.EXTRA_EMAIL, tos);
            returnIt.putExtra(Intent.EXTRA_TEXT, lstMalades.toString());
            returnIt.putExtra(Intent.EXTRA_SUBJECT, "subject");
            returnIt.setType("message/rfc882");
            startActivity(Intent.createChooser(returnIt,"Choisir un client pour envoyer Mail"));
            MainActivity.unMaladeBDD.viderTable();
            System.exit(0);
        }
    };
    @Override
    public void onPause(){
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        lstVue.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_main_2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_retour:
                AffMaladeActivity.this.finish();
                return true;
            case R.id.menu_gsb:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.siterb.sioklm.com"));
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyListAdapter extends ArrayAdapter<Malade> {
        int layout;
        ArrayList<Malade> malades;
        private MyListAdapter (Context context, int resource, ArrayList<Malade> objects) {
            super(context, resource, objects);
            layout = resource;
            malades = objects;

        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewHolder = null;

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout ,parent,false );
                final ViewHolder viewHolder = new ViewHolder();

                viewHolder.nom = (TextView) convertView.findViewById(R.id.list_item_tevNom);
                viewHolder.nom.setText(malades.get(position).getNom());
                String test = malades.get(position).getNom();


                viewHolder.prenom = (TextView) convertView.findViewById(R.id.list_item_tevPrenom);
                viewHolder.prenom.setText(malades.get(position).getPrenom());

                viewHolder.info = (ImageButton) convertView.findViewById(R.id.list_item_btnInfo) ;
                viewHolder.info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Malade unMalade = (Malade) lstVue.getItemAtPosition(position);
                        Intent intent = new Intent(AffMaladeActivity.this, InfoPatient.class);
                        intent.putExtra("message", unMalade.getInfoPatient());
                        startActivity(intent);
                    }
                });

                viewHolder.envoyer = (ImageButton) convertView.findViewById(R.id.list_item_btnEnvoie);
                viewHolder.envoyer.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        String corp = "Le malade signaler :"+System.getProperty("line.separator")+malades.get(position).toString();
                        Intent returnIt = new Intent(Intent.ACTION_SEND_MULTIPLE);
                        returnIt.putExtra(Intent.EXTRA_TEXT,corp);
                        returnIt.setType("messages/rfc882");
                        startActivity(Intent.createChooser(returnIt,"Choisir client pour envoyer le Mail"));
                    }
                });

                viewHolder.supprimer = (ImageButton) convertView.findViewById(R.id.list_item_btnSupprimer);
                viewHolder.supprimer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.unMaladeBDD.supprimerMalade(malades.get(position).getId());
                        int i = 0;
                        adapter.clear();
                        adapter.addAll(MainActivity.unMaladeBDD.getTousLesMalades());
                        lstVue.setAdapter(adapter);
                    }
                });
                convertView.setTag(viewHolder);
            }
            else{
                mainViewHolder = (ViewHolder) convertView.getTag();
                mainViewHolder.nom.setText(malades.get(position).getNom());
                mainViewHolder.prenom.setText(malades.get(position).getPrenom());
            }
            return convertView;
        }
    }
    public class ViewHolder {
        TextView nom;
        TextView prenom;
        ImageButton info;
        ImageButton envoyer;
        ImageButton supprimer;
    }
}
