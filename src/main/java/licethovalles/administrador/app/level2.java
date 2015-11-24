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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class level2 extends AppCompatActivity implements ViewAdapter.RecyclerClickListner {
    String letra, usuario,palabra="",nombrecurso;
    private ArrayList values, letras;
    private ViewAdapter viewAdapter;
    private RecyclerView mRecyclerView;
    RadioButton op1,op2,op3,op4;
    List<Information> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);
        letras = new ArrayList<String>();
        values= new ArrayList<String>();
        op1 =(RadioButton)findViewById(R.id.radioButton);
        op2 =(RadioButton)findViewById(R.id.radioButton2);
        op3 =(RadioButton)findViewById(R.id.radioButton3);
        op4 =(RadioButton)findViewById(R.id.radioButton4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent =getIntent();
        Bundle extras = intent.getExtras();

        if(extras!=null){
            letra=extras.getString("letra");
            usuario=extras.getString("usuario");
            nombrecurso=extras.getString("nombrecurso");
            // texto.setText(dato);
            //Toast.makeText(this, "nombre del usuario: " + dato, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "letra liberada: " + letra, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "usuario: " + usuario, Toast.LENGTH_SHORT).show();
        }

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
        int val=0,cont=0,entro=0;
        Random rand = new Random();
        int n = rand.nextInt(3);
        //Toast.makeText(this,"VALOR "+n, Toast.LENGTH_SHORT).show();
        for (int i = 0; i < letras.size();i++){

            Information info = new Information(values.get(i).toString());
            //Toast.makeText(this, "" + letra+" "+letras.get(i).toString()+" "+letra.equals(letras.get(i).toString()), Toast.LENGTH_SHORT).show();

            if(letras.get(i).toString().equals(letra)){
                if(val==0 && entro==n) {
                    data.add(info);
                    val=1;
                    palabra=values.get(i).toString();
                    //Toast.makeText(this,"si entro al if val==0" ,Toast.LENGTH_SHORT).show();
                }
                entro=entro+1;
                if(cont==0)
                {
                    op1.setText(""+values.get(i).toString());

                }
                else
                {
                   if(cont==1)
                   {
                       op2.setText(""+values.get(i).toString());
                   }
                   else
                   {
                       if(cont==2)
                       {
                           op3.setText(""+values.get(i).toString());
                       }
                       else
                       {
                           if(cont==3)
                           {
                               op4.setText(""+values.get(i).toString());
                           }
                       }
                   }
                }
                cont=cont+1;
                //Toast.makeText(this, "entro con "+letra , Toast.LENGTH_SHORT).show();
            }


        }
        //Toast.makeText(this,"tamaño data "+data.size(), Toast.LENGTH_SHORT).show();
        viewAdapter= new ViewAdapter(this,data,2);
        viewAdapter.setRecyclerClickListner(this);
        mRecyclerView.setAdapter(viewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }


    public void itemClick(View view, int position) {

        Log.d("Recyclerview", "Click position " + position);
    }
    public void next(View view){
        int i=1;
        if(op1.isChecked()==true && op1.getText().equals(palabra) || op2.isChecked()==true && op2.getText().equals(palabra) || op3.isChecked()==true && op3.getText().equals(palabra) || op4.isChecked()==true && op4.getText().equals(palabra)){
            Bundle bundle = new Bundle();
            bundle.putString("letra", "" + letra);
            bundle.putString("usuario", "" + usuario);
            bundle.putString("nombrecurso",""+nombrecurso);
            Intent intent = new Intent(level2.this,level3.class);
            intent.putExtras(bundle);
            startActivity(intent);}
        else{
            Toast.makeText(this,"Respuesta incorrecta", Toast.LENGTH_SHORT).show();
        }
    }

}
