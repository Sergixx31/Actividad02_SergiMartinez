package com.example.actividad02_sergimartinez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CurrencyActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ArrayList<Currency> currencys;
    CurrencyAdapter currencyAdapter;
    Context context;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
        CurrencyTask currencyTask = new CurrencyTask();
        currencyTask.execute(url);
    }

    public class CurrencyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CurrencyActivity.this);
            progressDialog.setMessage("Please wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            String result = "";
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                StringBuilder respuesta;
                try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))){
                    respuesta = new StringBuilder();
                    String respuestaLinea;
                    while((respuestaLinea = br.readLine()) != null) {
                        respuesta.append(respuestaLinea.trim());
                    }
                    result = respuesta.toString();
                }
            } catch (Exception exception) {
                result = "Error";
            } finally {
                return result;
            }
        }
        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                InputSource inputSource = new InputSource(new StringReader(resultado));
                Document document = documentBuilder.parse(inputSource);

                NodeList nodeList = document.getElementsByTagName("Cube");
                currencys = new ArrayList<Currency>();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);

                    if (node.hasAttributes() && !node.hasChildNodes()) {
                        Element element = (Element) node;
                        String currencyName = element.getAttribute("currency");
                        String rate = element.getAttribute("rate");

                        Currency currency = new Currency(currencyName, rate);
                        currencys.add(currency);
                    }
                }
                verCurrency();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            progressDialog.dismiss();
        }


        }
    void verCurrency() {
        ListView listViewCoins = (ListView) findViewById(R.id.listViewCurrency);
        CurrencyAdapter currencyAdapter = new CurrencyAdapter(this, currencys);
        listViewCoins.setAdapter(currencyAdapter);
    }
    }


