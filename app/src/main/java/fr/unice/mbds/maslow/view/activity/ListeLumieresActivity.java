package fr.unice.mbds.maslow.view.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Watchlist;
import fr.unice.mbds.maslow.util.ApiCallService;
import fr.unice.mbds.maslow.util.ApiUrlService;
import fr.unice.mbds.maslow.util.UtilisateurManager;
import fr.unice.mbds.maslow.view.adapter.LumieresItemAdapter;

public class ListeLumieresActivity extends AppCompatActivity {

    private ListView listeLumieres;
    private LumieresItemAdapter adapter;

    private final Integer WATCHLIST_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_liste_lumieres);


        listeLumieres = (ListView) findViewById(R.id.activity_liste_lumieres_list_view);

        new AsyncGetWatchlist().execute(WATCHLIST_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_appareil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_btn_add_appareil:
                AddAppareilDialog addAppareilDialog = new AddAppareilDialog(this, WATCHLIST_ID, adapter.getWatchlist().getAppareils(), adapter);
                addAppareilDialog.show(getFragmentManager(), "addAppareil");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class AsyncGetWatchlist extends AsyncTask<Integer, Integer, Watchlist> {

        private ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = setProgressDialog();
            progress.show();
        }

        @Override
        protected Watchlist doInBackground(Integer... params) {
            ResponseEntity<Watchlist> result = null;
            Context context = ListeLumieresActivity.this;

            String url = ApiUrlService.addToken(ApiUrlService
                            .getWatchlistUrl(UtilisateurManager.getId(context),
                                    params[0]),
                    UtilisateurManager.getToken(context));

            result = ApiCallService.getInstance().executeForEntity(url, HttpMethod.GET, null, Watchlist.class);

            if (result == null) {
                //La watchlist n'existe pas, on la crée
                String url2 = ApiUrlService.addToken(ApiUrlService
                                .getWatchlistUrl(UtilisateurManager.getId(context),
                                        params[0]),
                        UtilisateurManager.getToken(context));

                result = ApiCallService.getInstance().executeForEntity(url2, HttpMethod.POST, null, Watchlist.class);
            }
            return result.getBody() == null ? null : result.getBody();
        }

        @Override
        protected void onPostExecute(Watchlist watchlist) {

            progress.hide();
            if (watchlist == null) {

            } else {


                adapter = new LumieresItemAdapter(getApplicationContext(), watchlist);
                listeLumieres.setAdapter(adapter);
            }
        }
    }

    private ProgressDialog setProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Patientez...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        return progressDialog;
    }
}
