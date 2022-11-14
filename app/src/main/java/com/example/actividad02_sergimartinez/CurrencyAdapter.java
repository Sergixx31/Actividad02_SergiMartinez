package com.example.actividad02_sergimartinez;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CurrencyAdapter extends ArrayAdapter<Currency> {

    public CurrencyAdapter(@NonNull Context context, ArrayList<Currency> currencies) {
        super(context, 0, currencies);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Currency currency = this.getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.item_currency, parent, false);
        }

        TextView tvCurrencyName = (TextView) convertView.findViewById(R.id.tvCurrency);
        TextView tvRatio = (TextView) convertView.findViewById(R.id.tvRatio);

        tvCurrencyName.setText(currency.currency);
        tvRatio.setText(currency.rate);

        return convertView;
    }
}
