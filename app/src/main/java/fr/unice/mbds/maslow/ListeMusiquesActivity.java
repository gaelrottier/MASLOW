package fr.unice.mbds.maslow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapters.MusicItemAdaptor;
import object.Musique;

public class ListeMusiquesActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_musiques);
        listView = (ListView) findViewById(R.id.listViewMusiques);
        Bundle extra = getIntent().getBundleExtra("extra");
        List<Musique> musiqueList = (ArrayList<Musique>) extra.getSerializable("playList");
        final MusicItemAdaptor adaptor = new MusicItemAdaptor(ListeMusiquesActivity.this, musiqueList);
        listView.setAdapter(adaptor);

    }
}
