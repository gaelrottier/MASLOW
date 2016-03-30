package fr.unice.mbds.maslow.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidquery.AQuery;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Appareil;
import fr.unice.mbds.maslow.entities.Evenement;
import fr.unice.mbds.maslow.entities.Watchlist;
import fr.unice.mbds.maslow.interfaces.ICallback;
import fr.unice.mbds.maslow.service.MeteorService;

/**
 * Created by Gael on 21/12/2015.
 */
public class ConsoEnergieItemAdapter extends BaseAdapter implements ICallback {

    private Context context;
    private Map<Appareil, TextView> element;
    private Watchlist watchlist;

    public ConsoEnergieItemAdapter(Context context, Watchlist watchlist) {
        this.context = context;
        this.watchlist = watchlist;
        element = new HashMap<>();

        MeteorService.getInstance().setCallbackClass(this);
    }

    @Override
    public int getCount() {
        return watchlist.getAppareils().size();
    }

    @Override
    public Object getItem(int position) {
        return watchlist.getAppareils().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.activity_liste_conso_energie_item, null);
        }

        AQuery aq = new AQuery(convertView);
        Appareil appareil = watchlist.getAppareils().get(position);

        aq.id(convertView.findViewById(R.id.activity_liste_conso_energie_item_appareil)).text(appareil.getNom());
        aq.id(convertView.findViewById(R.id.activity_liste_conso_energie_item_conso)).text("nulle");
        element.put(appareil, aq.id(convertView.findViewById(R.id.activity_liste_conso_energie_item_conso)).getTextView());
        return convertView;
    }

    @Override
    public void onDataAdded(String collectionName, String documentID, JSONObject newValueJson, Appareil appareil) {
        Log.w("data added", "collectionName :" + collectionName + "; documentID : " + documentID + "; newValueJson : " + newValueJson);
        updateConso(documentID, newValueJson, appareil);
    }

    private void updateConso(String documentID, JSONObject values, Appareil appareil) {
        for (Evenement e : appareil.getEvenements()) {
            if (e.getIdOrchestra().equals(documentID)) {
                TextView txtConso = element.get(appareil);
                if ("conso".equals(e.getAlias().get("nom")) && values != null) {

                    try {
                        txtConso.setText(values.getString(e.getAlias().get("nom")) + " W");
                    } catch (JSONException e1) {
                        Log.e("json parsing", e1.getMessage());
                    }

                }
            }
        }
    }

    @Override
    public void onDataChanged(String collectionName, String documentID, JSONObject updateValuesJson, String removedValuesJson, Appareil appareil) {
        Log.w("data changed", "collectionName :" + collectionName + "; documentID : " + documentID + "; updateValuesJson : " + updateValuesJson + "; removedValuesJson : " + removedValuesJson);
        updateConso(documentID, updateValuesJson, appareil);
    }

    @Override
    public void onDataRemoved(String collectionName, String documentID, Appareil appareil) {
        Log.w("data removed", "collectionName :" + collectionName + "; documentID : " + documentID);
    }

    @Override
    public Watchlist getWatchlist() {
        return watchlist;
    }

    @Override
    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    @Override
    public void reconnect() {
        MeteorService.getInstance().setCallbackClass(this);
    }

    @Override
    public Context getContext() {
        return context;
    }
}
