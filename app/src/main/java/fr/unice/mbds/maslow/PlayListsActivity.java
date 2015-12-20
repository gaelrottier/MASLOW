package fr.unice.mbds.maslow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Musique;

public class PlayListsActivity extends AppCompatActivity {
    protected Musique musique;
    protected List<Musique> playListSalon, playListChambre, playlistSaleBain, playListCommune;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_lists);
        playListSalon = new ArrayList<>();
        playListChambre = new ArrayList<>();
        playlistSaleBain = new ArrayList<>();
        playListCommune = new ArrayList<>();
        Random random = null;
        int rand = 0;
        for (int i=0;i<10;i++){
            musique = new Musique("musique_"+i, "Artiste_"+i);
            playListSalon.add(musique);
            musique = new Musique("musique_"+i+7, "Artiste_"+i+7);
            playListChambre.add(musique);
            musique = new Musique("musique_"+i+15, "Artiste_"+i+15);
            playlistSaleBain.add(musique);
            rand = random.nextInt(25);
            musique = new Musique("musique_"+rand, "Artiste_"+rand);
            playListCommune.add(musique);
        }
        Button btnPlayListSalon = (Button) findViewById(R.id.btn_playlist_salon);
        Button btnPlayListChambre = (Button) findViewById(R.id.btn_playlist_chambre);
        Button btnPlayListSaleBain = (Button) findViewById(R.id.btn_playlist_sbain);
        Button btnPlayListCommune = (Button) findViewById(R.id.btn_playlist_commune);

        String[] playListSalon = {""};
    }
}
