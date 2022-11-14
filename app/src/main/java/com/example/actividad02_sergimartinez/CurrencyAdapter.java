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

public class CurrencyAdapter extends ArrayAdapter<Currency> {

    public CurrencyAdapter(Context context, ArrayList<Currency> Currency) {

        super(context, 0, Currency);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Currency currency = this.getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_currency, parent, false);
        }



        return convertView;
    }
}
