package licethovalles.administrador.app;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class level1 extends AppCompatActivity  implements ViewAdapter.RecyclerClickListner{
    private ArrayList values, letras;
    private List<ParseObject> ob;
    String letra , usuario;
    String dato,nombrecurso;

    TextView texto;
    private ViewAdapter viewAdapter;
    private RecyclerView mRecyclerView;
    List<Information> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        texto = (TextView) findViewById(R.id.texto1);
        letras = new ArrayList<String>();
        values= new ArrayList<String>();

        Intent intent =getIntent();
        Bundle extras = intent.getExtras();

        if(extras!=null){
            letra=extras.getString("letra");
            dato = extras.getString("usuario");
            nombrecurso=extras.getString("curso");
            // texto.setText(dato);
            //Toast.makeText(this, "nombre del usuario: " + dato, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "letra liberada: " + letra, Toast.LENGTH_SHORT).show();

        }

        new GetData().execute();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        letras.add("A");
        values.add("apple");
        letras.add("A");
        values.add("ant");
        letras.add("A");
        values.add("airplane");
        letras.add("A");
        values.add("alligator");
        letras.add("B");
        values.add("banano");
        letras.add("B");
        values.add("ball");
        letras.add("B");
        values.add("bell");
        letras.add("B");
        values.add("boat");
        List<Information> data = new ArrayList<>();
        String[] titulo = getResources().getStringArray(R.array.data);
       // Toast.makeText(this,"si entro "+letras.size()+" "+values.size(), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < letras.size();i++){

            Information info = new Information(values.get(i).toString());
            //Toast.makeText(this, "" + letra+" "+letras.get(i).toString()+" "+letra.equals(letras.get(i).toString()), Toast.LENGTH_SHORT).show();

            if(letras.get(i).toString().equals(letra)){data.add(info);
                //Toast.makeText(this, "entro con "+letra , Toast.LENGTH_SHORT).show();
            }


        }
        viewAdapter= new ViewAdapter(this,data,1);
        viewAdapter.setRecyclerClickListner(this);
        mRecyclerView.setAdapter(viewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void hola(){
    }


    public void next(View view){

        Bundle bundle = new Bundle();
        bundle.putString("letra", "" + letra);
        bundle.putString("usuario", "" + dato);
        bundle.putString("nombrecurso",""+nombrecurso);
        Intent intent = new Intent(level1.this,level2.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_option1, menu);
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

    @Override
    public void itemClick(View view, int position) {

        Log.d("Recyclerview", "Click position " + position);
    }

    public class GetData extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {


            try{
                ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("attributes");
                ob = query.find();

                for (ParseObject dato : ob){

                    //values.add(dato.get("Palabra") + " " + dato.get("Imagen") + " ");
                    values.add(dato.get("Palabra") + " ");
                    letras.add(dato.get("idletra"));


                }
                Log.e("GETDATA", "" + values.size());
            } catch (com.parse.ParseException e) {
                Log.e("Error",e.getMessage());
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog

            /**
             * Updating parsed JSON data into ListView
             * */
            //  CustomAdapter adapter = new CustomAdapter(MainActivity.this, listaClima);
            //  listView.setAdapter(adapter);//toca mirar algo asi aca
            hola();
        }
    }



}
