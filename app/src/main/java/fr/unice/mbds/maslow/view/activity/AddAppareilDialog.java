package fr.unice.mbds.maslow.view.activity;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidquery.AQuery;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Appareil;
import fr.unice.mbds.maslow.entities.Evenement;
import fr.unice.mbds.maslow.entities.Watchlist;
import fr.unice.mbds.maslow.interfaces.ICallback;
import fr.unice.mbds.maslow.util.ApiCallService;
import fr.unice.mbds.maslow.util.ApiUrlService;
import fr.unice.mbds.maslow.util.UtilisateurManager;
import fr.unice.mbds.maslow.view.adapter.DialogAddEvenementAdapter;

public class AddAppareilDialog extends DialogFragment {

    private Context context;
    private int watchlistId;
    private List<Appareil> appareils;
    private BaseAdapter adapterWatchlistListeAppareils;
    private DialogAddEvenementAdapter addEvenementAdapter;
    private View convertView;
    private Spinner spinner;

    public AddAppareilDialog() {
    }

    public AddAppareilDialog(Context context, int watchlistId, List<Appareil> appareils, BaseAdapter adapterWatchlistListeAppareils) {
        this.context = context;
        this.watchlistId = watchlistId;
        this.appareils = appareils;
        this.adapterWatchlistListeAppareils = adapterWatchlistListeAppareils;
        this.addEvenementAdapter = new DialogAddEvenementAdapter(context);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        adapterWatchlistListeAppareils.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.convertView = View.inflate(getActivity(), R.layout.dialog_add_appareil, null);

        new AsyncTaskGetAllAppareils().execute();

        final AQuery aq = new AQuery(context);

        Button btnValiderAppareilExistant = aq.id(convertView.findViewById(R.id.btn_dialog_valider_add_appareil_existant)).getButton();
        spinner = aq.id(convertView.findViewById(R.id.dialog_add_appareil_spinner_appareils_existants)).getSpinner();

        btnValiderAppareilExistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner = aq.id(convertView.findViewById(R.id.dialog_add_appareil_spinner_appareils_existants)).getSpinner();

                Appareil appareil = (Appareil) spinner.getSelectedItem();

                new AsyncTaskSetAppareil(watchlistId, appareil.getId()).execute(spinner.getSelectedItemPosition());
            }
        });

        Button btnAjouterEvenement = aq.id(convertView.findViewById(R.id.btn_dialog_add_evenement_new_evenement)).getButton();
        btnAjouterEvenement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvenementAdapter.addParent();
                addEvenementAdapter.notifyDataSetChanged();
            }
        });

        ExpandableListView listView = aq.id(convertView.findViewById(R.id.dialog_add_appareil_list_view)).getExpandableListView();

        listView.setAdapter(addEvenementAdapter);

        Button btnAjouterAppareil = aq.id(convertView.findViewById(R.id.btn_valider_add_product_nouveau)).getButton();
        btnAjouterAppareil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText txtNomAppareil = aq.id(convertView.findViewById(R.id.edit_dialog_add_appareil_new_nom)).getEditText();
                String nomAppareil = txtNomAppareil.getText().toString();

                if (!"".equals(nomAppareil)) {
                    Map<ViewGroup, List<ViewGroup>> data = addEvenementAdapter.getData();

                    Appareil a = new Appareil();
                    a.setNom(nomAppareil);
                    a.setEvenements(new ArrayList<Evenement>());

                    //Pour chaque évènement
                    for (Map.Entry<ViewGroup, List<ViewGroup>> evenement : data.entrySet()) {

                        Button btnTxtIdOrchestra = aq.id(evenement.getKey().findViewById(R.id.btn_dialog_add_evenement_new_id_orchestra)).getButton();
                        String txtIdOrchestra = btnTxtIdOrchestra.getText().toString();

                        if (!"".equals(txtIdOrchestra)) {

                            Evenement e = new Evenement();
                            e.setIdOrchestra(txtIdOrchestra);
                            e.setAlias(new HashMap<String, String>());

                            //Pour chaque alias
                            Map<String, String> aliasMap = new HashMap<String, String>();
                            for (ViewGroup alias : evenement.getValue()) {
                                String txtNomAliasAppli = aq.id(alias.findViewById(R.id.txt_dialog_add_alias_expandable_list_item_child_cle_parametre))
                                        .getEditText().getText().toString();
                                String txtNomAliasOrchestra = aq.id(alias.findViewById(R.id.txt_dialog_add_alias_expandable_list_item_child_valeur_parametre))
                                        .getEditText().getText().toString();

                                if (!"".equals(txtNomAliasAppli) && !"".equals(txtNomAliasOrchestra)) {
                                    aliasMap.put(txtNomAliasAppli, txtNomAliasOrchestra);
                                }
                            }

                            if (aliasMap.size() != 0) {
                                e.setAlias(aliasMap);
                                a.getEvenements().add(e);
                            }
                        }
                    }

                    if (a.getEvenements().size() != 0) {
                        new AsyncTaskCreateAppareilAndEvenements(context, a).execute();
                    }
                }


            }
        });

        return convertView;

    }

    /**
     * Met à jour la liste des appareils du spinner
     *
     * @param appareilsRecus
     */
    private void updateSpinner(List<Appareil> appareilsRecus) {

        for (Appareil appareilWatchlist : appareils) {
            for (Appareil appareilRecu : appareilsRecus) {
                if (appareilRecu.getId() == appareilWatchlist.getId()) {
                    appareilsRecus.remove(appareilRecu);
                    break;
                }
            }
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<Appareil> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, appareilsRecus);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private class AsyncTaskGetAllAppareils extends AsyncTask<Void, Void, List<Appareil>> {
        private ProgressDialog progress;

        public AsyncTaskGetAllAppareils() {
            progress = setProgressDialog();
        }

        @Override
        protected List<Appareil> doInBackground(Void... params) {
            ResponseEntity<List<Appareil>> result = null;

            try {

                result = ApiCallService.getInstance().executeForList(ApiUrlService.addToken(ApiUrlService.APPAREILS_URL, UtilisateurManager.getToken(context)),
                        new ParameterizedTypeReference<List<Appareil>>() {
                        });
            } catch (Exception e) {
                Log.e("GET REST", e.getMessage());

            }

            return result.getBody() == null ? null : result.getBody();
        }

        @Override
        protected void onPostExecute(List<Appareil> appareils) {
            if (appareils == null) {

            } else {

                progress.hide();

                updateSpinner(appareils);
            }
        }
    }


    ///////////////////////
    // Tache asynchrone ajouter appareil à watchlist
    ///////////////////////
    private class AsyncTaskSetAppareil extends AsyncTask<Integer, Void, Watchlist> {
        private final int idWatchlist;
        private final int idAppareil;
        private ProgressDialog progressDialog;
        private int spinnerPosition;

        AsyncTaskSetAppareil(int idWatchlist, int idAppareil) {
            this.idWatchlist = idWatchlist;
            this.idAppareil = idAppareil;
            progressDialog = setProgressDialog();
        }

        @Override
        protected Watchlist doInBackground(Integer... params) {
            ResponseEntity<Watchlist> result = null;
            spinnerPosition = params[0];

            try {
                String url = ApiUrlService.addToken(ApiUrlService.getWatchlistAppareilUrl(
                        UtilisateurManager.getId(context),
                        idWatchlist,
                        idAppareil), UtilisateurManager.getToken(context));

                result = ApiCallService.getInstance().executeForEntity(url, HttpMethod.PUT, null, Watchlist.class);
            } catch (Exception e) {
                Log.e("GET REST", e.getMessage());

            }

            return result.getBody() == null ? null : result.getBody();
        }

        @Override
        protected void onPostExecute(Watchlist watchlist) {
            if (watchlist != null) {
                progressDialog.hide();
                appareils = watchlist.getAppareils();

                //mise à jour de l'adapterWatchlistListeAppareils de la watchlist
                ((ICallback) adapterWatchlistListeAppareils).setWatchlist(watchlist);
                adapterWatchlistListeAppareils.notifyDataSetChanged();
                ((ICallback) adapterWatchlistListeAppareils).reconnect();

                //mise à jour de l'adapterWatchlistListeAppareils du spinner
                ArrayAdapter<Appareil> appareilArrayAdapter = (ArrayAdapter<Appareil>) spinner.getAdapter();

                appareilArrayAdapter.remove(appareilArrayAdapter.getItem(spinnerPosition));
            }
        }


    }

    private class AsyncTaskCreateAppareilAndEvenements extends AsyncTask<Void, Void, Boolean> {

        private final Context context;
        private final Appareil appareil;

        public AsyncTaskCreateAppareilAndEvenements(Context context, Appareil appareil) {

            this.context = context;
            this.appareil = appareil;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            ResponseEntity<Appareil> resultAppareil = null;

            try {
                JSONObject appareilJson = appareil.toJson();

                appareilJson.remove("id");

                resultAppareil = ApiCallService.getInstance().executeForJson(
                        ApiUrlService.addToken(
                                ApiUrlService.APPAREILS_URL
                                , UtilisateurManager.getToken(context))
                        , HttpMethod.POST
                        , appareilJson
                        , Appareil.class);
            } catch (Exception e) {
                Log.e("POST REST", e.getMessage());
            }

            Appareil appareilRecu = resultAppareil.getBody() == null ? null : resultAppareil.getBody();

            if (appareilRecu == null) {
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                new AsyncTaskGetAllAppareils().execute();
            } else {
                Toast.makeText(context, "Erreur lors de la création de l'appareil", Toast.LENGTH_SHORT).show();
                Log.e("Création d'un apparreil", "échec");
            }
        }
    }

    private ProgressDialog setProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(context);

        progressDialog.setMessage("Patientez...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        return progressDialog;
    }
}
