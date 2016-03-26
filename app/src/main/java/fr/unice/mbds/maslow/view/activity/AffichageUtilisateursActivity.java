package fr.unice.mbds.maslow.view.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.springframework.core.ParameterizedTypeReference;

import java.util.ArrayList;
import java.util.List;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Utilisateur;
import fr.unice.mbds.maslow.util.ApiCallService;
import fr.unice.mbds.maslow.util.ApiUrlService;
import fr.unice.mbds.maslow.view.adapter.UtilisateursItemAdapter;

public class AffichageUtilisateursActivity extends AppCompatActivity {

    private ListView listeUtilisateurs;
    private List<Utilisateur> utilisateurs = new ArrayList<>();
    private UtilisateursItemAdapter utilisateursItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_utilisateurs);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        listeUtilisateurs = (ListView)findViewById(R.id.listViewUtilisateurs);

        new AsyncGetUtilisateurs().execute();
    }

    private class AsyncGetUtilisateurs extends AsyncTask<Integer, Integer, List<Utilisateur>> {

        private ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = setProgressDialog();
            progress.show();
        }

        @Override
        protected List<Utilisateur> doInBackground(Integer... params) {
            List<Utilisateur> result = null;

            try {
//                RestTemplate restTemplate = new RestTemplate();
//                resultat = restTemplate.getForObject(ApiUrlService.UTILISATEURS_URL,UtilisateursInfoResponse.class).getUtilisateurs();
                //System.out.println("UTILISATEURS "+utilisateurs.toString());
                //result = ApiCallService.getInstance().execute(ApiUrlService.getUserListUrl(), HttpMethod.GET, null, Utilisateur.class);
                result = (List<Utilisateur>) ApiCallService.getInstance().executeForList(ApiUrlService.addToken(ApiUrlService.UTILISATEUR_URL, Utilisateur.getToken(AffichageUtilisateursActivity.this)),
                        new ParameterizedTypeReference<List<Utilisateur>>() {
                        });
            } catch (Exception e) {
                Log.e("GET REST", e.getMessage());

            }

            utilisateurs =  result;
            return  result;
        }

        @Override
        protected void onPostExecute(List<Utilisateur> utilisateurList) {
            super.onPostExecute(utilisateurList);
            progress.hide();
            utilisateursItemAdapter = new UtilisateursItemAdapter(AffichageUtilisateursActivity.this, utilisateurList);
            listeUtilisateurs.setAdapter(utilisateursItemAdapter);
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
