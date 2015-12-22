package fr.unice.mbds.maslow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import adapters.MainItemAdapter;

public class MainActivity extends AppCompatActivity {

    private GridView gridViewListeBoutons;
    private MainItemAdapter adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridViewListeBoutons = (GridView) findViewById(R.id.activity_main_grid_view);

        List<Class<? extends AppCompatActivity>> listeBoutons = new ArrayList<>();

        listeBoutons.add(PlayListsActivity.class);
        listeBoutons.add(ListeConsoEnergieActivity.class);
        listeBoutons.add(ListeLumieresActivity.class);

        adaptor = new MainItemAdapter(getApplicationContext(), listeBoutons);
        gridViewListeBoutons.setAdapter(adaptor);
    }
}
