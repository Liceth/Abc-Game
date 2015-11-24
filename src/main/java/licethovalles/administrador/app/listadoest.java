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
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class listadoest extends AppCompatActivity {
    private ProgressDialog pDialog;
    List<ParseObject> ob;
    TextView val;
    String nombrecurso;
    String valores="";
    ParseObject est;
    private ArrayList estudiantes,calificaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadoest);
        estudiantes = new ArrayList<String>();
        calificaciones = new ArrayList<String>();
        val = (TextView)findViewById(R.id.textView12);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent= getIntent();

        Bundle bundle = intent.getExtras();
        if (bundle !=null) {
            nombrecurso = bundle.getString("nombrecurso");
            Toast.makeText(this, "nombre del curso: " + nombrecurso, Toast.LENGTH_SHORT).show();
        }
        requestData();

    }

    public void listar(){
        valores=" ";
        for(int i=0;i<estudiantes.size();i++){

            valores=valores+estudiantes.get(i).toString() +"      "+calificaciones.get(i).toString()+"\r\n";
        }
        val.setText(valores);

    }

    public void requestData() {
        estudiantes.clear();
        calificaciones.clear();
        new GetData().execute();
    }
    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(listadoest.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance


            try {

                    ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("puntaje");
                    //query.setSkip(300);

                    ob = query.find();

                    for (ParseObject dato : ob) {

                        estudiantes.add(dato.get("usuario"));
                        calificaciones.add(dato.get("puntaje"));


                    }

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
            listar();
        }
    }


}
