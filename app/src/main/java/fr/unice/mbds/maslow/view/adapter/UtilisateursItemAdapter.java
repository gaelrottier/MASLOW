package fr.unice.mbds.maslow.view.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Utilisateur;
import fr.unice.mbds.maslow.util.ApiCallService;
import fr.unice.mbds.maslow.util.ApiUrlService;
import fr.unice.mbds.maslow.util.UtilisateurManager;
import fr.unice.mbds.maslow.view.activity.AffichageUtilisateursActivity;
import fr.unice.mbds.maslow.view.activity.AfficherUtilisateurActivity;

/**
 * Created by Zac on 21/03/2016.
 */
public class UtilisateursItemAdapter extends BaseAdapter {
    private Context context;
    private List<Utilisateur> utilisateurList;

    public UtilisateursItemAdapter(Context context, List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return utilisateurList.size();
    }

    @Override
    public Object getItem(int position) {
        return utilisateurList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.layout_utilisateur_item,null);
        }

        AQuery aq = new AQuery(convertView);
        if (!utilisateurList.get(position).getIdentifiant().equals("admin")) {
            aq.id(R.id.textViewNomUserList).text(utilisateurList.get(position).getNom());
            aq.id(R.id.textViewPrenomUserList).text(utilisateurList.get(position).getPrenom());
            aq.id(R.id.buttonDetailUtilisateur).getButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AfficherUtilisateurActivity.class);
                    Bundle extra = new Bundle();
                    Map<String, String> strings = new HashMap<String, String>();
                    strings.put("nom", utilisateurList.get(position).getNom());
                    strings.put("prenom", utilisateurList.get(position).getPrenom());
                    strings.put("identifiant", utilisateurList.get(position).getIdentifiant());
                    extra.putSerializable("detailUtilisateur", (Serializable) strings);
                    intent.putExtra("extra", extra);
                    context.startActivity(intent);
                }
            });

            aq.id(R.id.buttonSupprimerUtilisateur).getButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(context);
                    adb.setTitle("Annuler");
                    adb.setMessage("Supprimer l'utilisateur ? ");
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            new AsyncTaskDeleteUtilisateurs().execute(utilisateurList.get(position).getId());
                        }
                    });
                    adb.show();

                }
            });
        }else {
            ( convertView).setVisibility(View.INVISIBLE);


        }
        return convertView;
    }

    private class AsyncTaskDeleteUtilisateurs extends AsyncTask<Integer, Integer, Void> {

        private ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = setProgressDialog();
            progress.show();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            ResponseEntity result = null;

            try {

                result = ApiCallService.getInstance().execute(ApiUrlService.addToken(ApiUrlService.UTILISATEUR_URL + "/" + params[0] + "/", UtilisateurManager.getToken(context)), HttpMethod.DELETE,
                        null, Utilisateur.class);
            } catch (Exception e) {
                Log.e("DELETTE REST", e.getMessage());

            }
            context.startActivity(new Intent(context, AffichageUtilisateursActivity.class));
            return null;
        }

        @Override
        protected void onPostExecute(Void vide) {
            super.onPostExecute(vide);
            progress.hide();

        }
    }


    private ProgressDialog setProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(context);

        progressDialog.setMessage("Patientez...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        return progressDialog;
    }
}
