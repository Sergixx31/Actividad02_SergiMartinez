package com.example.actividad02_sergimartinez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
        CurrencyTask CurrencyTask = new CurrencyTask();
        CurrencyTask.execute(url);
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

        public Document convertirStringToXMLDocument(String xmlString) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder ;
            try {
                builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
                System.out.println("hgola");
                return doc;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);
            TextView ejemploXML = (TextView) findViewById(R.id.ejemploXML);
            ejemploXML.setText(resultado);
            progressDialog.dismiss();
            String xml = resultado;

            Document document = convertirStringToXMLDocument(xml);
            NodeList listaItem = document.getElementsByTagName("item");
            ArrayList arrayList = new ArrayList<Item>();
            for (int i = 0; i < listaItem.getLength(); i++) {
                Element element = (Element) listaItem.item(i);
                String currency =  element.getAttribute("currency");
                String rate =  element.getAttribute("rate");
                Item item = new Item(currency, rate);
                arrayList.add(item);
            }
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(CurrencyActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrayList);
            ListView listView = (ListView) findViewById(R.id.idListView);
            listView.setAdapter(adaptador);

        }

        public class Item {
            String name;
            String ratio;


            public Item(String anme, String ratio) {
                this.name = name;
                this.ratio = ratio;

            }

            @NonNull
            @Override
            public String toString() {
                return "Atributo: " + this.name + " ID: " + this.ratio;
            }


        }
        }


    }


