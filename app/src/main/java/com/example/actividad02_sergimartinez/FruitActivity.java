package com.example.actividad02_sergimartinez;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class FruitActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruta);
        LlamadaFruta();
    }


    public void LlamadaFruta() {

        ArrayList<Fruta> arrayFruta_sm = new ArrayList<Fruta>();
        arrayFruta_sm.add(new Fruta("Manzana"));
        arrayFruta_sm.add(new Fruta("Pera"));
        arrayFruta_sm.add(new Fruta("Platano"));
        arrayFruta_sm.add(new Fruta("Kiwi"));
        arrayFruta_sm.add(new Fruta("Naranja"));
        arrayFruta_sm.add(new Fruta("Mandarina"));


        FrutaAdapter frutaAdaptador = new FrutaAdapter(this, arrayFruta_sm);

        ListView listaFrutas = (ListView) findViewById(R.id.listafruta);
        listaFrutas.setAdapter(frutaAdaptador);

    }



}
