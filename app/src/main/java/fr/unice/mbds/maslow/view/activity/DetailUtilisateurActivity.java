package fr.unice.mbds.maslow.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Map;

import fr.unice.mbds.maslow.R;

/**
 * Created by Zac on 27/03/2016.
 */
public class DetailUtilisateurActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_utilisateur);

        TextView nom = (TextView)findViewById(R.id.editTextNomUtilisateur);
        TextView prenom = (TextView) findViewById(R.id.editTextPrenomUtilisateur);
        TextView identifiant = (TextView) findViewById(R.id.editTextIdentifiantUtilisateur);

        Bundle extra = getIntent().getBundleExtra("extra");
        Map<String,String> utilisateur = (Map<String, String>) extra.getSerializable("detailUtilisateur");

        nom.setText(utilisateur.get("nom"));
        prenom.setText(utilisateur.get("prenom"));
        identifiant.setText(utilisateur.get("identifiant"));


    }
}
