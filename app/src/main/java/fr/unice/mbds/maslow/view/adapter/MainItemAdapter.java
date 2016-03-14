package fr.unice.mbds.maslow.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;

import java.util.List;

import fr.unice.mbds.maslow.R;

/**
 * Created by Gael on 20/12/2015.
 */
public class MainItemAdapter extends BaseAdapter {
    private Context context;
    private List<Class<? extends AppCompatActivity>> boutons;

    public MainItemAdapter(Context context, List<Class<? extends AppCompatActivity>> boutons) {
        this.context = context;
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
            convertView = View.inflate(context, R.layout.activity_main_list_item, null);
        }

        AQuery aq = new AQuery(convertView);

        aq.id(convertView.findViewById(R.id.activity_main_img_view)).image(R.drawable.fff).width(150).height(150);

        aq.id(convertView).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, boutons.get(position));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(i);
            }
        });

        return convertView;
    }
}
