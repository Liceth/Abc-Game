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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class registro extends AppCompatActivity {

    ParseObject Users;
    CheckBox docente, estudiante;
    Button registro;
    EditText usuario, contrasena;
    private ProgressDialog pDialog;
    List<ParseObject> ob;
    private  ArrayList user;
    private  ArrayList pass;
    private  ArrayList tipo;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        user= new ArrayList<String>();
        pass= new ArrayList<String>();
        tipo= new ArrayList<String>();

        registro = (Button) findViewById(R.id.button13);
        estudiante = (CheckBox) findViewById(R.id.checkBox27);
        docente = (CheckBox) findViewById(R.id.checkBox28);
        usuario = (EditText)findViewById(R.id.editText3);
        contrasena = (EditText) findViewById(R.id.editText4);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        requestData();
       // a.setText("hello");
       // Toast.makeText(getApplicationContext(), "En "+a.getText(), Toast.LENGTH_SHORT).show();

    }

    public void Guardar(View view) {

        Users = new ParseObject("app");
        Users.put("usuario", String.valueOf(usuario.getText()));
        Users.put("password", String.valueOf(contrasena.getText()));
        if (docente.isChecked()) {
            // Toast.makeText(this, "Entro A ", Toast.LENGTH_SHORT).show();
            Users.put("tipo", String.valueOf("docente"));
            type="docente";
        }else{

            if (estudiante.isChecked()) {
                Users.put("tipo", String.valueOf("est"));
                type="est";
            }
        }
        Users.saveInBackground();

        if(type.equals("est")){
            Intent intent = new Intent(registro.this,MainEst.class);
            Bundle bundle = new Bundle();
            bundle.putString("usuario", "" + usuario.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }else {
            if(type.equals("docente")) {
                Intent intent = new Intent(registro.this, ListadoCursos.class);
                Bundle bundle = new Bundle();
                bundle.putString("usuario", "" + usuario.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }



    public void requestData() {
        user.clear();
        pass.clear();
        tipo.clear();
        new GetData().execute();
    }

    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(registro.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            try {
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("app");

                ob = query.find();

                for (ParseObject dato : ob) {

                    user.add(dato.get("usuario"));
                    pass.add(dato.get("password"));
                    tipo.add(dato.get("tipo"));
                    //nombrecurso.add(dato.get("nombrecurso"));


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
            /**
             * Updating parsed JSON data into ListView
             * */
            //  CustomAdapter adapter = new CustomAdapter(MainActivity.this, listaClima);
            //  listView.setAdapter(adapter);//toca mirar algo asi aca
            //comparar();
        }
    }
}
