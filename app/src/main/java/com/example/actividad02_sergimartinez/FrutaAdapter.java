package com.example.actividad02_sergimartinez;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FrutaAdapter extends ArrayAdapter<Fruta> {

    public FrutaAdapter(Context context, ArrayList<Fruta> frutas) {

        super(context, 0, frutas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Fruta fruta = this.getItem(position);

         if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_fruta, parent, false);
        }

        ImageView inventarioFruta = (ImageView) convertView.findViewById(R.id.inventarioFruta);

        //BITMAP
        Bitmap imagenesFrutas = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.frutas3);
        imagenesFrutas = Bitmap.createBitmap(imagenesFrutas,1*imagenesFrutas.getWidth()/4,3*imagenesFrutas.getHeight()/4,imagenesFrutas.getWidth()/4, imagenesFrutas.getHeight()/4);

        inventarioFruta.setImageBitmap(imagenesFrutas);

        TextView inventarioFrutaNombre = (TextView) convertView.findViewById(R.id.inventarioFrutaNombre);
        EditText numeroFruta = (EditText) convertView.findViewById(R.id.numeroFruta);

        inventarioFrutaNombre.setText(fruta.name);

        Button buttonSuma = (Button) convertView.findViewById(R.id.buttonSuma);
        Button buttonResta = (Button) convertView.findViewById(R.id.buttonResta) ;


        buttonSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cantidadFruta = 0;
                if(numeroFruta.getText() != null) {
                    cantidadFruta = Integer.parseInt(numeroFruta.getText().toString());
                }
                cantidadFruta++;
                numeroFruta.setText(Integer.toString(cantidadFruta));
            }
        });

        View finalConvertView = convertView;
        buttonResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cantidadFruta = 0;
                if(numeroFruta.getText() == null) {

                    return;
                }
                cantidadFruta = Integer.parseInt(numeroFruta.getText().toString());
                if(cantidadFruta > 0) {
                    cantidadFruta--;
                    numeroFruta.setText(Integer.toString(cantidadFruta));
                    return;
                }
                Toast notification = Toast.makeText(
                        finalConvertView.getContext(),
                        "No se puede bajar de 0",
                        Toast.LENGTH_LONG
                );
                notification.show();
            }
        });

        return convertView;
    }
}
