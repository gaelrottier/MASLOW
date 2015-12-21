package adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.unice.mbds.maslow.R;

/**
 * Created by Gael on 21/12/2015.
 */
public class ConsoEnergieAdaptor extends BaseAdapter {

    private Context context;
    private Map<String, String> appareils;
    List<String> nomAppareils = new ArrayList<>();
    List<String> consoEnergie = new ArrayList<>();

    public ConsoEnergieAdaptor(Context context, Map<String, String> appareils) {
        this.context = context;
        this.appareils = appareils;

        for (Map.Entry<String, String> entry : appareils.entrySet()) {
            nomAppareils.add(entry.getKey());
            consoEnergie.add(entry.getValue());
        }
    }

    @Override
    public int getCount() {
        return appareils.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
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

        aq.id(convertView.findViewById(R.id.activity_liste_conso_energie_item_appareil)).text(nomAppareils.get(position));
        aq.id(convertView.findViewById(R.id.activity_liste_conso_energie_item_conso)).text(consoEnergie.get(position));

        return convertView;
    }
}
