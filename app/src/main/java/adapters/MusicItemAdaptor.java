package adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fr.unice.mbds.maslow.R;
import object.Musique;

/**
 * Created by Zac on 20/12/2015.
 */
public class MusicItemAdaptor extends BaseAdapter {
    private Context context;
    private List<Musique> musiques;

    public MusicItemAdaptor(Context context, List<Musique> musiques) {
        this.context = context;
        this.musiques = musiques;
    }

    @Override
    public int getCount() {
        return musiques.size();
    }

    @Override
    public Musique getItem(int position) {
        return musiques.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        MusiqueViewHolder viewHolder = null;
        if (v==null){
            v = View.inflate(context, R.layout.activity_liste_musiques_item, null);
            viewHolder = new MusiqueViewHolder();
            viewHolder.titre = (TextView) v.findViewById(R.id.titre_musique);
            viewHolder.artiste = (TextView) v.findViewById(R.id.artiste_musique);
            v.setTag(viewHolder);
        }
        else {
            viewHolder = (MusiqueViewHolder) v.getTag();
        }
        Musique musique = musiques.get(position);
        viewHolder.titre.setText(musique.getTitre());
        viewHolder.artiste.setText(musique.getArtiste());

        return v;
    }
}

class MusiqueViewHolder{
    TextView titre;
    TextView artiste;

}
