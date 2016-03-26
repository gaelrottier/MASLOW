package fr.unice.mbds.maslow.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Utilisateur;

public class AfficherUtilisateurActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_utilisateur);

        TextView nom = (TextView) findViewById(R.id.editTextNomUtilisateur);
        TextView prenom = (TextView) findViewById(R.id.editTextPrenomUtilisateur);
        TextView identifiant = (TextView) findViewById(R.id.editTextIdentifiantUtilisateur);

        Bundle bundle = getIntent().getBundleExtra("extra");
        Utilisateur utilisateur = (Utilisateur) bundle.getSerializable("detailUtilisateur");

        nom.setText(utilisateur.getNom());
        prenom.setText(utilisateur.getPrenom());
        identifiant.setText(utilisateur.getIdentifiant());


    }

}
