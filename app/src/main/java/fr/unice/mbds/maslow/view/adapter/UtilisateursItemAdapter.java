package fr.unice.mbds.maslow.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;

import org.json.JSONObject;

import java.util.List;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Appareil;
import fr.unice.mbds.maslow.entities.Utilisateur;
import fr.unice.mbds.maslow.entities.Watchlist;
import fr.unice.mbds.maslow.interfaces.ICallback;

/**
 * Created by Zac on 21/03/2016.
 */
public class UtilisateursItemAdapter extends BaseAdapter implements ICallback {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.layout_utilisateur_item,null);
        }

        AQuery aq = new AQuery(convertView);
        aq.id(R.id.textViewNomUserList).text(utilisateurList.get(position).getNom());
        aq.id(R.id.textViewPrenomUserList).text(utilisateurList.get(position).getPrenom());
        return convertView;
    }

    //@Override
    public void onDataAdded(String collectionName, String documentID, String newValueJson, Appareil appareil) {

    }

    @Override
    public void onDataAdded(String collectionName, String documentID, JSONObject newValueJson, Appareil appareil) {

    }

    @Override
    public void onDataChanged(String collectionName, String documentID, JSONObject updateValuesJson, String removedValuesJson, Appareil appareil) {

    }

   // @Override
    public void onDataChanged(String collectionName, String documentID, String updateValuesJson, String removedValuesJson, Appareil appareil) {

    }

    @Override
    public void onDataRemoved(String collectionName, String documentID, Appareil appareil) {

    }

    @Override
    public Watchlist getWatchlist() {
        return null;
    }

    @Override
    public Context getContext() {
        return null;
    }
}
