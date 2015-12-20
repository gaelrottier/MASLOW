package model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zac on 20/12/2015.
 */
public class MusicItemAdaptor extends BaseAdapter {
    private Context context;
    private List<Musique> musiques = new ArrayList<>();

    public MusicItemAdaptor(Context context, List<Musique> musiques) {
        this.context = context;
        this.musiques = musiques;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

class MusiqueViewHolder{
    TextView titre;
    TextView artiste;

}
