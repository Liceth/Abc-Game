package licethovalles.administrador.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainEst extends AppCompatActivity {
    String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_est);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle !=null) {
            usuario = bundle.getString("usuario");
           // Toast.makeText(this, "nombre del usuario: " + usuario, Toast.LENGTH_SHORT).show();
        }


    }
    public void registrarme(View view){

        Intent intent = new Intent(MainEst.this,registrarmecurso.class);
        Bundle bundle = new Bundle();
        bundle.putString("usuario", "" + usuario);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void jugar(View view){
        Intent intent = new Intent(MainEst.this,jugar.class);
        Bundle bundle = new Bundle();
        bundle.putString("usuario", "" + usuario);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
