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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class curso extends AppCompatActivity {
    String curso;
    TextView nombre;
    ParseObject letra;
    Button save;
    CheckBox a,b,c,d,e,f,g,h,i1,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;
    private ProgressDialog pDialog;
    List<ParseObject> ob;
    private ArrayList letter;
    private ArrayList nombrecurso;
    int cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);
        letter= new ArrayList<String>();
        nombrecurso= new ArrayList<String>();
         nombre=(TextView)findViewById(R.id.textView7);
        save = (Button)findViewById(R.id.save);
        a=(CheckBox)findViewById(R.id.checkBox);
        b= (CheckBox)findViewById(R.id.checkBox2);
        c=(CheckBox)findViewById(R.id.checkBox3);
        d=(CheckBox)findViewById(R.id.checkBox4);
        e=(CheckBox)findViewById(R.id.checkBox5);
        f=(CheckBox)findViewById(R.id.checkBox6);
        g= (CheckBox)findViewById(R.id.checkBox7);
        h=(CheckBox)findViewById(R.id.checkBox8);
        i1=(CheckBox)findViewById(R.id.checkBox9);
        j=(CheckBox)findViewById(R.id.checkBox10);
        k=(CheckBox)findViewById(R.id.checkBox11);
        l= (CheckBox)findViewById(R.id.checkBox12);
        m=(CheckBox)findViewById(R.id.checkBox13);
        n=(CheckBox)findViewById(R.id.checkBox14);
        o=(CheckBox)findViewById(R.id.checkBox15);
        p=(CheckBox)findViewById(R.id.checkBox16);
        q= (CheckBox)findViewById(R.id.checkBox17);
        r=(CheckBox)findViewById(R.id.checkBox18);
        s=(CheckBox)findViewById(R.id.checkBox19);
        t=(CheckBox)findViewById(R.id.checkBox20);
        u=(CheckBox)findViewById(R.id.checkBox21);
        v= (CheckBox)findViewById(R.id.checkBox22);
        w=(CheckBox)findViewById(R.id.checkBox23);
        x=(CheckBox)findViewById(R.id.checkBox24);
        y=(CheckBox)findViewById(R.id.checkBox25);
        z=(CheckBox)findViewById(R.id.checkBox26);

        Intent intent= getIntent();

        Bundle bundle = intent.getExtras();
        if (bundle !=null) {
            curso = bundle.getString("curso");
            Toast.makeText(this, "nombre del curso: " + curso, Toast.LENGTH_SHORT).show();
        }
        nombre.setText(curso);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(curso.this,ListadoCursos.class);
                startActivity(intent);
            }
        });

        requestData();
    }

    public void guardarletra(View view){
        //Toast.makeText(this, "Entro ", Toast.LENGTH_SHORT).show();
        if(a.isChecked()== true){
           // Toast.makeText(this, "Entro A ", Toast.LENGTH_SHORT).show();
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("A"));
            letra.saveInBackground();
        }
        if(b.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("B"));
            letra.saveInBackground();
        }
        if(c.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("C"));
            letra.saveInBackground();
        }
        if(d.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("D"));
            letra.saveInBackground();
        }
        if(e.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("E"));
            letra.saveInBackground();
        }
        if(f.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("F"));
            letra.saveInBackground();
        }
        if(g.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("G"));
            letra.saveInBackground();
        }
        if(h.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("H"));
            letra.saveInBackground();
        }
        if(i1.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("I"));
            letra.saveInBackground();
        }
        if(j.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("J"));
            letra.saveInBackground();
        }

        if(k.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("K"));
            letra.saveInBackground();
        }
        if(l.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("L"));
            letra.saveInBackground();
        }
        if(m.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("M"));
            letra.saveInBackground();
        }
        if(n.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("N"));
            letra.saveInBackground();
        }
        if(o.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("O"));
            letra.saveInBackground();
        }

        if(p.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("P"));
            letra.saveInBackground();
        }
        if(q.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("Q"));
            letra.saveInBackground();
        }
        if(r.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("R"));
            letra.saveInBackground();
        }
        if(s.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("S"));
            letra.saveInBackground();
        }
        if(t.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("T"));
            letra.saveInBackground();
        }
        if(u.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("U"));
            letra.saveInBackground();
        }
        if(v.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("V"));
            letra.saveInBackground();
        }
        if(w.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("W"));
            letra.saveInBackground();
        }
        if(x.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("X"));
            letra.saveInBackground();
        }
        if(y.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("Y"));
            letra.saveInBackground();
        }

        if(z.isChecked()== true){
            letra= new ParseObject("letras");
            letra.put("nombrecurso", String.valueOf(curso));
            letra.put("letra", String.valueOf("Z"));
            letra.saveInBackground();
        }
    }

        public void listarest(View view){
            Bundle bundle = new Bundle();
            bundle.putString("nombrecurso", "" +curso);
            Intent intent = new Intent(curso.this,listadoest.class);
            startActivity(intent);
        }


    public void comparar(){

        for(int i=0;i<letter.size();i++){
            if(letter.get(i).toString().equals("A") && curso.equals(nombrecurso.get(i).toString()) ){
                a.setChecked(true);
            }
            if(letter.get(i).toString().equals("B") && curso.equals(nombrecurso.get(i).toString())){
                b.setChecked(true);
            }
            if(letter.get(i).toString().equals("C") && curso.equals(nombrecurso.get(i).toString())){
                c.setChecked(true);
            }
            if(letter.get(i).toString().equals("D") && curso.equals(nombrecurso.get(i).toString())){
                d.setChecked(true);
            }
            if(letter.get(i).toString().equals("E") && curso.equals(nombrecurso.get(i).toString())){
                e.setChecked(true);
            }
            if(letter.get(i).toString().equals("F") && curso.equals(nombrecurso.get(i).toString())){
                f.setChecked(true);
            }

            if(letter.get(i).toString().equals("G") && curso.equals(nombrecurso.get(i).toString())){
                g.setChecked(true);
            }
            if(letter.get(i).toString().equals("H") && curso.equals(nombrecurso.get(i).toString())){
                h.setChecked(true);
            }
            if(letter.get(i).toString().equals("I") && curso.equals(nombrecurso.get(i).toString()) ){
                i1.setChecked(true);
            }
            if(letter.get(i).toString().equals("J") && curso.equals(nombrecurso.get(i).toString())){
                j.setChecked(true);
            }
            if(letter.get(i).toString().equals("K") && curso.equals(nombrecurso.get(i).toString())){
                k.setChecked(true);
            }
            if(letter.get(i).toString().equals("L") && curso.equals(nombrecurso.get(i).toString())){
                l.setChecked(true);
            }
            if(letter.get(i).toString().equals("M") && curso.equals(nombrecurso.get(i).toString())){
                m.setChecked(true);
            }
            if(letter.get(i).toString().equals("N") && curso.equals(nombrecurso.get(i).toString())){
                n.setChecked(true);
            }
            if(letter.get(i).toString().equals("O") && curso.equals(nombrecurso.get(i).toString())){
                o.setChecked(true);
            }

            if(letter.get(i).toString().equals("P") && curso.equals(nombrecurso.get(i).toString())){
                p.setChecked(true);
            }
            if(letter.get(i).toString().equals("Q") && curso.equals(nombrecurso.get(i).toString())){
                q.setChecked(true);
            }
            if(letter.get(i).toString().equals("R")&& curso.equals(nombrecurso.get(i).toString())){
                r.setChecked(true);
            }
            if(letter.get(i).toString().equals("S")&& curso.equals(nombrecurso.get(i).toString())){
                s.setChecked(true);
            }
            if(letter.get(i).toString().equals("T") && curso.equals(nombrecurso.get(i).toString())){
                t.setChecked(true);
            }
            if(letter.get(i).toString().equals("U") && curso.equals(nombrecurso.get(i).toString())){
                u.setChecked(true);
            }
            if(letter.get(i).toString().equals("V") && curso.equals(nombrecurso.get(i).toString())){
                v.setChecked(true);
            }
            if(letter.get(i).toString().equals("W") && curso.equals(nombrecurso.get(i).toString())){
                w.setChecked(true);
            }
            if(letter.get(i).toString().equals("X") && curso.equals(nombrecurso.get(i).toString())){
                x.setChecked(true);
            }
            if(letter.get(i).toString().equals("Y") && curso.equals(nombrecurso.get(i).toString())){
                y.setChecked(true);
            }
            if(letter.get(i).toString().equals("Z") && curso.equals(nombrecurso.get(i).toString())){
                z.setChecked(true);
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
            pDialog = new ProgressDialog(curso.this);
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

                    letter.add(dato.get("letra"));
                    nombrecurso.add(dato.get("nombrecurso"));


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
