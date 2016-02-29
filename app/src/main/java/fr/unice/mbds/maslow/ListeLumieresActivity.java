package fr.unice.mbds.maslow;



import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import adapters.LumieresItemAdapter;
import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.ResultListener;

public class ListeLumieresActivity extends AppCompatActivity  {



    ListView listeLumieres;
    LumieresItemAdapter adapter;
    public Switch mySwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_liste_lumieres);


        listeLumieres = (ListView) findViewById(R.id.activity_liste_lumieres_list_view);

        Map<String, String> lumieres = new HashMap<>();

        lumieres.put("Chambre", "true");
        lumieres.put("Cuisine", "false");
        lumieres.put("Salon", "true");
        lumieres.put("Salle de bain", "false");


        mySwitch = (Switch) findViewById(R.id.activity_liste_lumieres_item_switch);


        adapter = new LumieresItemAdapter(getApplicationContext(), lumieres);
        listeLumieres.setAdapter(adapter);


    }



}
