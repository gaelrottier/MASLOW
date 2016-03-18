package fr.unice.mbds.maslow.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.util.List;
import java.util.Map;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Appareil;
import fr.unice.mbds.maslow.entities.Watchlist;
import fr.unice.mbds.maslow.interfaces.ICallback;

/**
 * Created by Gael on 21/12/2015.
 */
public class ConsoEnergieItemAdapter extends BaseAdapter implements ICallback {

    private final Watchlist watchlist;
    private Context context;
    List<Appareil> appareils;
    Map<Appareil, TextView> element;

    public ConsoEnergieItemAdapter(Context context, Watchlist watchlist) {
        this.context = context;
        this.watchlist = watchlist;
        this.appareils = watchlist.getAppareils();
    }
//
//    public ConsoEnergieItemAdapter(Context context, Map<String, String> appareils) {
//        this.context = context;
//
//        for (Map.Entry<String, String> entry : appareils.entrySet()) {
//            nomAppareils.add(entry.getKey());
//            consoEnergie.add(entry.getValue());
//        }
//    }

    @Override
    public int getCount() {
        return appareils.size();
    }

    @Override
    public Object getItem(int position) {
        return appareils.get(position);
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

        aq.id(convertView.findViewById(R.id.activity_liste_conso_energie_item_appareil)).text(appareils.get(position).getNom());
        aq.id(convertView.findViewById(R.id.activity_liste_conso_energie_item_conso)).text("0 W");
        element.put(appareils.get(position), aq.id(convertView.findViewById(R.id.activity_liste_conso_energie_item_conso)).getTextView());
//        aq.id(convertView.findViewById(R.id.activity_liste_conso_energie_item_conso)).text

        return convertView;
    }

    @Override
    public void onDataAdded(String collectionName, String documentID, String newValueJson, Appareil appareil) {
//        /*pour chaque appareil de la watchlist
//          pour chaque evenement
//              si idOrchestra = documentID*/


//                  TextView txtconso = element.get(appareil)
//                  JsonObject newValue = new jsonobject(newValueJson)
//                  txtConso.setText(newValue.getString("conso"))
//
        Log.w("data added", "collectionName :" + collectionName + "; documentID : " + documentID + "; newValueJson : " + newValueJson);
    }

    @Override
    public void onDataChanged(String collectionName, String documentID, String updateValuesJson, String removedValuesJson, Appareil appareil) {
        Log.w("data changed", "collectionName :" + collectionName + "; documentID : " + documentID + "; updateValuesJson : " + updateValuesJson + "; removedValuesJson : " + removedValuesJson);
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
    public Context getContext() {
        return context;
    }
}
