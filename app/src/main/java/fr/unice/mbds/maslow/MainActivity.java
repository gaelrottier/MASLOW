package fr.unice.mbds.maslow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import adapters.MainItemAdaptor;

public class MainActivity extends AppCompatActivity {

    private GridView gridViewListeBoutons;
    private MainItemAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridViewListeBoutons = (GridView) findViewById(R.id.activity_main_grid_view);

        List<Class<? extends AppCompatActivity>> listeBoutons = new ArrayList<>();

        listeBoutons.add(PlayListsActivity.class);
        listeBoutons.add(ListeConsoEnergieActivity.class);

        adaptor = new MainItemAdaptor(getApplicationContext(), listeBoutons);
        gridViewListeBoutons.setAdapter(adaptor);

        //start playlist activity
        //startActivity(new Intent(this, PlayListsActivity.class));
    }
}
