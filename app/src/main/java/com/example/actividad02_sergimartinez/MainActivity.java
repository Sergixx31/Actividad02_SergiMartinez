package com.example.actividad02_sergimartinez;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        LlamadaAdaptadorSimpleSpinner();
    }


    public void LLamadaAdaptadorSimpleListViewConstructor1(View view) {
        String[] sm_opciones = {"hola","adios","Viernes"};
        ArrayAdapter<String> sm_adaptador1 = new ArrayAdapter<String>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sm_opciones);
        ListView jmh_origen = (ListView) findViewById(R.id.listviewjmh);
        jmh_origen.setAdapter(sm_adaptador1);
    }
    public void LLamadaAdaptadorSimpleListViewConstructor2(View view) {
        String[] sm_opciones = {"hola","adios"};
        ArrayAdapter<String>sm_adaptador2=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2,android.R.id.text1, sm_opciones);
        ListView jmh_origen = (ListView) findViewById(R.id.listviewjmh);
        jmh_origen.setAdapter(sm_adaptador2);
    }

    public void LlamadaAdaptadorSimpleSpinner() {
        String[] sm_opciones = {"hola","adios"};
        ArrayAdapter<String> sm_adaptador1 = new ArrayAdapter <String> (MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sm_opciones);
        Spinner jmh_origen = (Spinner) findViewById(R.id.spinnerjmh);
        jmh_origen.setAdapter(sm_adaptador1);

    }

    public void LlamadaAdaptadorPropioListView(View view) {

        ArrayList<User> arrayUsera_sm = new ArrayList<User>();
        arrayUsera_sm.add(new User("Maria", "Topeka"));
        arrayUsera_sm.add(new User("Hector ", "Birmingham"));
        arrayUsera_sm.add(new User("Jone ", "Newark"));
        arrayUsera_sm.add(new User("Albert ", "Washington"));
        arrayUsera_sm.add(new User("Adelina ", "Rio Rancho"));
        arrayUsera_sm.add(new User("Samara ", "Huntington"));
        arrayUsera_sm.add(new User("Pascuala", "Albany"));
        arrayUsera_sm.add(new User("Luz", "Danbury"));
        arrayUsera_sm.add(new User("Leonardo ", "Farmington Hills"));
        arrayUsera_sm.add(new User("Basilisa ", "Sandy Springs"));
        arrayUsera_sm.add(new User("Nerea ", "Wilkes-Barre"));
        arrayUsera_sm.add(new User("Roxana ", "Florence"));
        arrayUsera_sm.add(new User("Alonso ", "Kalamazooo"));
        arrayUsera_sm.add(new User("Constantino ", "Burnsville"));
        arrayUsera_sm.add(new User("Yago ", "Billings"));
        arrayUsera_sm.add(new User("Fidel ", "Eau Claire"));
        arrayUsera_sm.add(new User("Miriam ", "Wilkes-Barre"));

        UsersAdapter sm_adaptador3 = new UsersAdapter(this, arrayUsera_sm);
        ListView listView_jmh = (ListView) findViewById(R.id.listviewjmh);
        listView_jmh.setAdapter(sm_adaptador3);

    }

    public void verFrutas(View view){
        Intent intent = new Intent(this, FruitActivity.class);
        this.startActivity(intent);
    }
    public void verCurrency(View view){
        Intent intent = new Intent(this, CurrencyActivity.class);
        this.startActivity(intent);
    }


}









