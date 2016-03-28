package fr.unice.mbds.maslow.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidquery.AQuery;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Utilisateur;
import fr.unice.mbds.maslow.util.ApiCallService;
import fr.unice.mbds.maslow.util.ApiUrlService;

public class CreerUtilisateurActivity extends AppCompatActivity {
    private TextView nom;
    private TextView prenom;
    private TextView identifiant;
    private TextView password;
    public static void alertDialog(View v, String titre, String contenu) {
        AlertDialog show = new AlertDialog.Builder(v.getContext())
                .setTitle(titre)
                .setMessage(contenu)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static boolean isNotEmpty(View v,String string){
        if (string.isEmpty()){
            alertDialog(v, string, "Champ Vide!! Completer");
            return false;
        }
        return  true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_utilisateur);

         nom = (TextView) findViewById(R.id.editTextNewNomUtilisateur);
         prenom = (TextView) findViewById(R.id.editTextNewPrenomUtilisateur);
         identifiant = (TextView) findViewById(R.id.editTextNewIdentifiantUtilisateur);
         password = (TextView) findViewById(R.id.editTextNewPasswordUtilisateur);

        final Utilisateur utilisateur = new Utilisateur();

       // Button senregistrer = (Button) findViewById(R.id.buttonEnregUser);
        AQuery aq = new AQuery(this);
        aq.id(R.id.buttonEnregUser).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotEmpty(v, nom.getText().toString()) &&
                        (isNotEmpty(v, prenom.getText().toString())) &&
                        (isNotEmpty(v, identifiant.getText().toString())) &&
                        (isNotEmpty(v, password.getText().toString()))){
                    utilisateur.setNom(nom.getText().toString());
                    utilisateur.setPrenom(prenom.getText().toString());
                    utilisateur.setIdentifiant(identifiant.getText().toString());
                    utilisateur.setPassword(password.getText().toString());

                    new AsyncTaskCreerUtilisateurs().execute(utilisateur);
                }
            }
        });

    }

    private class AsyncTaskCreerUtilisateurs extends AsyncTask<Utilisateur, Integer, Void> {

        private ProgressDialog progress;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = setProgressDialog();
            progress.show();
        }

        @Override
        protected Void doInBackground(Utilisateur... params) {
            ResponseEntity result = null;
            JSONObject userJson = new JSONObject();
            try {
                userJson.put("nom",params[0].getNom());
                userJson.put("prenom",params[0].getPrenom());
                userJson.put("identifiant",params[0].getIdentifiant());
                userJson.put("password", params[0].getPassword());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {

                result = ApiCallService.getInstance().executeForJson(ApiUrlService.addToken(ApiUrlService.UTILISATEUR_URL, "a"), HttpMethod.POST,
                        userJson, Utilisateur.class);
            } catch (Exception e) {
                Log.e("POST REST", e.getMessage());

            }
            try{
                startActivity(new Intent(CreerUtilisateurActivity.this, LoginActivity.class));
            }catch (Exception ex){
                 ex.getMessage();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void vide) {
            super.onPostExecute(vide);
            progress.hide();

        }
    }


    private ProgressDialog setProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Patientez...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        return progressDialog;
    }

}
