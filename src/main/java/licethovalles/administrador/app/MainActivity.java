package licethovalles.administrador.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ParseObject testObject;
    private ProgressDialog pDialog;
    List<ParseObject> ob;
    private ArrayList usuario;
    private ArrayList contrasena;
    private ArrayList tipo;
    private EditText user,pass;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "iYZVYwHAE2gjvEjToa4IMTfFrg3rbsdFP24gZp8U", "y7QpEg3YWPVOVnFhZ1NhbiUn5VD62LdhRgzE3OWf");
        user = (EditText) findViewById(R.id.editText);
        pass=(EditText) findViewById(R.id.editText2);
        usuario = new ArrayList<String>();
        contrasena = new ArrayList<String>();
        tipo = new ArrayList<String>();
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registro.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public void comparar(){
        int val=0;
        for(int i=0;i<usuario.size();i++){
            if(user.getText().toString().equals(usuario.get(i).toString()) && pass.getText().toString().equals(contrasena.get(i).toString())){
                val=1;
                type=tipo.get(i).toString();
            }
        }
        if(val==1)
        {
            Toast.makeText(getApplicationContext(), "Usuario correcto", Toast.LENGTH_LONG).show();

            if(type.equals("docente")){
            Intent intent = new Intent(MainActivity.this,ListadoCursos.class);
            Bundle bundle = new Bundle();
            bundle.putString("usuario", "" + user.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
            }else {
                Intent intent = new Intent(MainActivity.this,MainEst.class);
                Bundle bundle = new Bundle();
                bundle.putString("usuario", "" + user.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Usuario o contrasena incorrecta.", Toast.LENGTH_LONG).show();
        }
    }
    public void requestData(View view) {
        usuario.clear();
        contrasena.clear();
        new GetData().execute();


    }


                /**
                 * Async task class to get json by making HTTP call
                 */
        private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance


            try {

                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("app");
                //query.setSkip(300);

                ob = query.find();
                //for(int i=0;i<6;i++) {
                for (ParseObject dato : ob) {

                    usuario.add(dato.get("usuario"));
                    contrasena.add(dato.get("password"));
                    tipo.add(dato.get("tipo"));

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

