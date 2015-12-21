package fr.unice.mbds.maslow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import model.MainItemAdaptor;

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
        listeBoutons.add(ListeMusiquesActivity.class);

        adaptor = new MainItemAdaptor(getApplicationContext(), listeBoutons);

        //start playlist activity
        //startActivity(new Intent(this, PlayListsActivity.class));
    }
}
