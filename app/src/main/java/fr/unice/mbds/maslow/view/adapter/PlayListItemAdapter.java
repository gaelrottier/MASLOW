package fr.unice.mbds.maslow.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.androidquery.AQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.unice.mbds.maslow.R;
import object.Musique;

/**
 * Created by Zac on 08/01/2016.
 */
public class PlayListItemAdapter extends BaseAdapter{
    private Context context;
    private List<Class<? extends AppCompatActivity>> boutons;
    private Musique musique;
    private List<Musique> musiqueList = new ArrayList<>();

    public PlayListItemAdapter(Context applicationContext, List<Class<? extends AppCompatActivity>> boutons) {
        this.context = applicationContext;
        this.boutons = boutons;
    }

    @Override
    public int getCount() {
        return boutons.size();
    }

    @Override
    public Object getItem(int position) {
        return boutons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.layout_play_list_item, null);
        }
        AQuery aq = new AQuery(convertView);


        aq.id(convertView.findViewById(R.id.buttonPlayListItem)).text("PlayList_" + position).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(context, "PlayList_" + position, Toast.LENGTH_LONG);
                toast.show();
                Random r = new Random();
                int max = r.nextInt(5) + 5;
                musiqueList.clear();
                for (int i = 0; i < max; i++) {
                    musique = new Musique("musique_" + i, "Artiste_" + i);
                    musiqueList.add(musique);
                }
                Intent intent = new Intent(context, boutons.get(position));
                Bundle extra = new Bundle();
                extra.putSerializable("playList", (Serializable) musiqueList);
                intent.putExtra("extra", extra);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });
        return convertView;
    }


}
