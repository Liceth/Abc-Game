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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class registrarmecurso extends AppCompatActivity {
    private ProgressDialog pDialog;
    List<ParseObject> ob;
    ParseObject ncurso;
    private ArrayList array_spinner,nombreest,nombrecurs;
    int entro=0,valg=0;
    Spinner s;
    String usuario, curso1,reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarmecurso);
        array_spinner = new ArrayList<String>();
        nombrecurs = new ArrayList<String>();
        nombreest = new ArrayList<String>();
         s = (Spinner) findViewById(R.id.spinner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registrarmecurso.this,MainEst.class);
                startActivity(intent);
            }
        });

        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle !=null) {
            usuario = bundle.getString("usuario");

        }
        requestData();
    }

    public void jugar( View view){
        curso1=s.getSelectedItem().toString();
        requestData();
            if(valg==0) {

            }else{

            }

    }

    public void comparar(){

        if(entro==0){
            entro=1;
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, array_spinner);
            s.setAdapter(adapter);
        }else{
            int val=0;
            for(int i=0;i<nombreest.size();i++) {
              if(usuario.equals(nombreest.get(i).toString())){
                 val=1;
                  reg=nombrecurs.get(i).toString();
                  valg=1;
              }
            }
            if(val == 0){
                ncurso = new ParseObject("registrado");
                ncurso.put("nombrecurso", String.valueOf(curso1));
                ncurso.put("nombreest", String.valueOf(usuario));
                ncurso.saveInBackground();
                Intent intent = new Intent(registrarmecurso.this, jugar.class);
                Bundle bundle = new Bundle();

                bundle.putString("curso", "" + curso1);
                bundle.putString("usuario", "" + usuario);

                intent.putExtras(bundle);
                startActivity(intent);



            }
            else{
                Toast.makeText(this, "Usted ya esta registrado en un curso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(registrarmecurso.this, jugar.class);
                Bundle bundle = new Bundle();
                bundle.putString("curso", "" + reg);
                bundle.putString("usuario", "" + usuario);

                intent.putExtras(bundle);
                startActivity(intent);
            }

        }
    }
    public void requestData() {
        array_spinner.clear();
        new GetData().execute();
    }
    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(registrarmecurso.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance


            try {
                    if(entro==0){
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("cursos");
                //query.setSkip(300);

                ob = query.find();
                //for(int i=0;i<6;i++) {
                for (ParseObject dato : ob) {

                    array_spinner.add(dato.get("nombrecurso"));


                }
                    }else{
                        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("registrado");


                        ob = query.find();

                        for (ParseObject dato : ob) {

                            nombrecurs.add(dato.get("nombrecurso"));
                            nombreest.add(dato.get("nombreest"));


                        }

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
