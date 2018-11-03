package com.example.frive.sentrifugo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText correo,passw;
    private Button entrar,registrar;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog=new ProgressDialog(MainActivity.this);
        correo=(EditText) findViewById(R.id.correo);
        passw=(EditText) findViewById(R.id.password2);
        entrar=(Button) findViewById(R.id.entrar);
        registrar=(Button) findViewById(R.id.registrar);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inicia
                if(!correo.getText().toString().equals("") && !passw.getText().toString().equals("")) {
                    iniciar(correo.getText().toString(), passw.getText().toString());
                }else{
                    Toast.makeText(MainActivity.this,"LLene todos los campos",Toast.LENGTH_LONG).show();
                }
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //registra
                if(!correo.getText().toString().equals("") && !passw.getText().toString().equals("")) {
                    Log.d("estoEntro","entro");
                    registrar(correo.getText().toString(), passw.getText().toString());
                }else{
                    Toast.makeText(MainActivity.this,"Llene todos los campos",Toast.LENGTH_LONG).show();
                }
            }
        });
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //valida inicio
                FirebaseUser usuario = firebaseAuth.getCurrentUser();
                if(usuario!=null){
                    //inicio seccion
                    // Log.i("Seccion","inicio"+usuario.getEmail());
                }else{
                    //Log.i("Seccion","seccion Cerrada");
                    Toast.makeText(MainActivity.this,"No se pudo ingresar",Toast.LENGTH_LONG).show();
                }
            }
        };
    }
    private void registrar(final String email, final String pass){
        //registra en firebase
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // Log.i("seccion","se creo bien");
                    Intent intent=new Intent(MainActivity.this, MenuInicio.class);
                    String correo=email;
                    intent.putExtra("correo",correo);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"Registro",Toast.LENGTH_LONG).show();

                }else{
                    // Log.i("seccion","no se creo");
                    // correo.setText("");
                    // passw.setText("");
                    Toast.makeText(MainActivity.this,"No se creo el usuario",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void iniciar(final String email, String pass){
        //inicia con firebase
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Log.i("seccion","inicio");
                  // Intent intent=new Intent(MainActivity.this,DatosUsuario.class);
                    Intent intent=new Intent(MainActivity.this, MenuInicio.class);
                    intent.putExtra("correo", email);

                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"Inicio",Toast.LENGTH_LONG).show();

                }else{
                    //  Log.i("seccion","fallido");
                    Toast.makeText(MainActivity.this,"No se pudo iniciar seccion",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null){
            FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
        }
    }

}
