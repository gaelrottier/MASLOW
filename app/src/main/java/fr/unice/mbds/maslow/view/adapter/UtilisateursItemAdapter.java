package fr.unice.mbds.maslow.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Utilisateur;
import fr.unice.mbds.maslow.view.activity.DetailUtilisateurActivity;

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
        aq.id(R.id.textViewNomUserList).text(utilisateurList.get(position).getNom());
        aq.id(R.id.textViewPrenomUserList).text(utilisateurList.get(position).getPrenom());
        aq.id(R.id.buttonDetailUtilisateur).getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailUtilisateurActivity.class);
                Bundle extra = new Bundle();
                Map<String, String> strings = new HashMap<String, String>();
                strings.put("nom",utilisateurList.get(position).getNom());
                strings.put("prenom",utilisateurList.get(position).getPrenom());
                strings.put("identifiant",utilisateurList.get(position).getIdentifiant());
                extra.putSerializable("detailUtilisateur", (Serializable) strings);
                intent.putExtra("extra", extra);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
