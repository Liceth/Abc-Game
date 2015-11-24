package licethovalles.administrador.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class jugar extends AppCompatActivity {
    String usuario,curso,reg;
    private ArrayList letter,nombrecurso;
    private ProgressDialog pDialog;
    List<ParseObject> ob;
    int cont=0,margen=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        nombrecurso = new ArrayList<String>();
        letter= new ArrayList<String>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jugar.this, MainEst.class);
                startActivity(intent);
            }
        });

        Intent intent= getIntent();

        Bundle bundle = intent.getExtras();
        if (bundle !=null) {
            usuario = bundle.getString("usuario");
            curso = bundle.getString("curso");
           // Toast.makeText(this, "nombre del usuario: " + usuario, Toast.LENGTH_SHORT).show();
        }
        requestData();
    }


    public void comparar(){
        int val=0;

        for(int i=0;i<nombrecurso.size();i++){

            if(nombrecurso.get(i).toString().equals(curso)){

                final Button myButton = new Button(this);
                myButton.setText(letter.get(i).toString());
                final String f=letter.get(i).toString();
                myButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(jugar.this, level1.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("letra", ""+f);
                        bundle.putString("usuario", ""+usuario);
                        bundle.putString("curso", ""+curso);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                RelativeLayout rl = (RelativeLayout)findViewById(R.id.layout1);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(300,120);
                lp.setMargins(100,margen+100,100,100);

                rl.addView(myButton, lp);
               // Toast.makeText(this,"pruebajugar",Toast.LENGTH_SHORT).show();
                margen =margen+100;
                cont=cont++;
            }


        }

    }
    public void requestData() {
       letter.clear();
        new GetData().execute();
    }
    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(jugar.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance


            try {

                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("letras");
                //query.setSkip(300);

                ob = query.find();
                //for(int i=0;i<6;i++) {
                for (ParseObject dato : ob) {

                    nombrecurso.add(dato.get("nombrecurso"));
                    letter.add(dato.get("letra"));


                }
                //   query.setSkip((i+1)*100);
                // }
            } catch (com.parse.ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;



        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            //  CustomAdapter adapter = new CustomAdapter(MainActivity.this, listaClima);
            //  listView.setAdapter(adapter);//toca mirar algo asi aca
            comparar();
        }
    }



}
