package fr.unice.mbds.maslow.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.androidquery.AQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.unice.mbds.maslow.R;

/**
 * Created by Gael on 21/12/2015.
 */
public class DiffuseurOdeursItemAdapter extends BaseAdapter {

    private Context context;
    List<String> nomDiffuseur = new ArrayList<>();
    List<String> checked = new ArrayList<>();

    public DiffuseurOdeursItemAdapter(Context context, Map<String, String> diffuseurs) {
        this.context = context;

        for (Map.Entry<String, String> entry : diffuseurs.entrySet()) {
            nomDiffuseur.add(entry.getKey());
            checked.add(entry.getValue());
        }
    }

    @Override
    public int getCount() {
        return nomDiffuseur.size();
    }

    @Override
    public Object getItem(int position) {
        return nomDiffuseur.get(position) + " : " + checked.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.activity_liste_lumieres_item, null);
        }

        AQuery aq = new AQuery(convertView);

        aq.id(convertView.findViewById(R.id.activity_liste_lumieres_item_appareil)).text(nomDiffuseur.get(position));

        Switch swtch = (Switch) aq.id(convertView.findViewById(R.id.activity_liste_lumieres_item_switch)).getView();

        swtch.setChecked(Boolean.parseBoolean(checked.get(position)));

        swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //TODO
                } else {
                    //TODO
                }
            }
        });
        return convertView;
    }
}