package fr.unice.mbds.maslow.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.androidquery.AQuery;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Appareil;
import fr.unice.mbds.maslow.entities.Evenement;
import fr.unice.mbds.maslow.entities.Watchlist;
import fr.unice.mbds.maslow.interfaces.ICallback;
import fr.unice.mbds.maslow.service.MeteorService;
import im.delight.android.ddp.Meteor;

/**
 * Created by Gael on 21/12/2015.
 */
public class LumieresItemAdapter extends BaseAdapter implements ICallback {

    private Context context;
    private final Watchlist watchlist;
    private Meteor meteor;
    private Map<Appareil, Switch> switchsAppareils = new HashMap<>();

    public LumieresItemAdapter(Context context, Watchlist watchlist) {
        this.context = context;
        this.watchlist = watchlist;

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.activity_liste_lumieres_item, null);
        }

        AQuery aq = new AQuery(convertView);

        Appareil appareil = watchlist.getAppareils().get(position);

        aq.id(convertView.findViewById(R.id.activity_liste_lumieres_item_appareil)).text(appareil.getNom());

        Switch swtch = (Switch) aq.id(convertView.findViewById(R.id.activity_liste_lumieres_item_switch)).getView();

        swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String onId = "";
                String offId = "";

                for (Evenement e : watchlist.getAppareils().get(position).getEvenements()) {

                    if (e.getAlias().get("nom").equals("on")) {
                        onId = e.getIdOrchestra();
                    } else if (e.getAlias().get("nom").equals("off")) {
                        offId = e.getIdOrchestra();
                    }
                }

                if (isChecked) {
                    MeteorService.getInstance().sendCommand(onId);
                } else {
                    MeteorService.getInstance().sendCommand(offId);
                }
            }
        });

        switchsAppareils.put(appareil, swtch);
        return convertView;
    }


    @Override
    public void onDataAdded(String collectionName, String documentID, JSONObject newValueJson, Appareil appareil) {

        for (Evenement e : appareil.getEvenements()) {
            if (e.getIdOrchestra().equals(documentID)) {
                Switch s = switchsAppareils.get(appareil);

                String nom = e.getAlias().get("nom");

                if ("on".equals(nom)) {
                    s.setChecked(true);
                    break;
                } else if ("off".equals(nom)) {
                    s.setChecked(false);
                    break;
                }

            }
        }

    }

    @Override
    public void onDataChanged(String collectionName, String documentID, String updateValuesJson, String removedValuesJson, Appareil appareil) {
        Log.w("onDataChanged", "collectionName = [" + collectionName + "], documentID = [" + documentID + "], updateValuesJson = [" + updateValuesJson + "], removedValuesJson = [" + removedValuesJson + "], appareil = [" + appareil + "]");
    }

    @Override
    public void onDataRemoved(String collectionName, String documentID, Appareil appareil) {
        Log.w("onDataRemoved", "collectionName = [" + collectionName + "], documentID = [" + documentID + "], appareil = [" + appareil + "]");
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
