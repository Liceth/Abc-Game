package licethovalles.administrador.app;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class ListadoCursos extends ActionBarActivity {
    int cont =0; int margen=100;
    ParseObject ncurso;
    String usuario;
    private ProgressDialog pDialog;
    List<ParseObject> ob;
    private ArrayList curso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_cursos);
        curso = new ArrayList<String>();
//        Parse.enableLocalDatastore(this);
        //Parse.initialize(this, "iYZVYwHAE2gjvEjToa4IMTfFrg3rbsdFP24gZp8U", "y7QpEg3YWPVOVnFhZ1NhbiUn5VD62LdhRgzE3OWf");
        Intent intent= getIntent();

        Bundle bundle = intent.getExtras();
        if (bundle !=null) {
            usuario = bundle.getString("usuario");
            Toast.makeText(this, "nombre del usuario: " + usuario, Toast.LENGTH_SHORT).show();
        }
        requestData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listado_cursos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void agregar(View V){

        final Button myButton = new Button(this);
        myButton.setText("Transicion No "+cont++);
        ncurso= new ParseObject("cursos");
        ncurso.put("nombrecurso",String.valueOf(myButton.getText().toString()));

        ncurso.put("docente",String.valueOf(usuario));
        ncurso.saveInBackground();

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ListadoCursos.this,curso.class);
                Bundle bundle = new Bundle();
                bundle.putString("curso",""+myButton.getText());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.layout);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(300,120);
        lp.setMargins(100,margen+100,100,100);
        rl.addView(myButton, lp);
        //Toast.makeText(this,"prueba",Toast.LENGTH_SHORT).show();
        margen =margen+100;
    }


    public void comparar(){
        int val=0;
        for(int i=0;i<curso.size();i++){

            final Button myButton = new Button(this);
            myButton.setText("Transicion No "+cont++);

            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(ListadoCursos.this,curso.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("curso",""+myButton.getText());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            RelativeLayout rl = (RelativeLayout)findViewById(R.id.layout);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(300,120);
            lp.setMargins(100,margen+100,100,100);
            rl.addView(myButton, lp);
            //Toast.makeText(this,"pruebac",Toast.LENGTH_SHORT).show();
            margen =margen+100;
            cont=cont++;
        }

    }
    public void requestData() {
            curso.clear();
        new GetData().execute();
    }
    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ListadoCursos.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance


            try {

                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("cursos");
                //query.setSkip(300);

                ob = query.find();
                //for(int i=0;i<6;i++) {
                for (ParseObject dato : ob) {

                    curso.add(dato.get("nombrecurso"));


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
