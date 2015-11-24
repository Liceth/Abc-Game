package licethovalles.administrador.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class level3 extends AppCompatActivity  implements ViewAdapter.RecyclerClickListner{

    String letra, usuario,palabra="",nombrecurso;
    private ArrayList values, letras;
    private ViewAdapter viewAdapter;
    private RecyclerView mRecyclerView;

    ParseObject Users;
    String puntaje;
    TextView respuesta;
    List<Information> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3);
        respuesta = (TextView)findViewById(R.id.editText5);
        letras = new ArrayList<String>();
        values= new ArrayList<String>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent =getIntent();
        Bundle extras = intent.getExtras();

        if(extras!=null){
            letra=extras.getString("letra");
            usuario=extras.getString("usuario");
            nombrecurso=extras.getString("nombrecurso");

            Toast.makeText(this, "letra liberada: " + letra, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "usuario: " + usuario, Toast.LENGTH_SHORT).show();
        }
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
        int val=0,cont=0,entro=0;
        Random rand = new Random();
        int n = rand.nextInt(3);
        for (int i = 0; i < letras.size();i++) {

            Information info = new Information(values.get(i).toString());

            if (letras.get(i).toString().equals(letra)) {
                if (val == 0 && entro == n) {
                    data.add(info);
                    val = 1;
                    palabra = values.get(i).toString();

                }
            entro=entro+1;
            }

            viewAdapter = new ViewAdapter(this, data,3);
            viewAdapter.setRecyclerClickListner(this);
            mRecyclerView.setAdapter(viewAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        }

    }


    public void next(View view){
        Users = new ParseObject("puntaje");
        Users.put("usuario", String.valueOf(usuario));
        Users.put("nombrecurso",String.valueOf(nombrecurso));

        if(respuesta.getText().toString().equals(palabra)){
            puntaje="100";
            Users.put("puntaje", String.valueOf(puntaje));
            Toast.makeText(this, "Respuesta Correcta ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(level3.this,MainEst.class);
            Bundle bundle = new Bundle();
            bundle.putString("usuario", "" + usuario);
            bundle.putString("nombrecurso",""+nombrecurso);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            puntaje="0";
            Users.put("puntaje", String.valueOf(puntaje));
            Toast.makeText(this, "Respuesta Incorrecta", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(level3.this,MainEst.class);
            Bundle bundle = new Bundle();
            bundle.putString("usuario", "" + usuario);
            bundle.putString("nombrecurso", "" + nombrecurso);
            startActivity(intent);
        }
        Users.saveInBackground();
    }

    public void itemClick(View view, int position) {

        Log.d("Recyclerview", "Click position " + position);
    }

}
