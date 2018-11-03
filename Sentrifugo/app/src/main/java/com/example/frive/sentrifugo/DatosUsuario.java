package com.example.frive.sentrifugo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frive.sentrifugo.Base.Referencias;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatosUsuario extends AppCompatActivity {
    private List<Usuario> listaUsuarios;
    private TextView nombre;
    private TextView edad;
    private TextView correo;
    private TextView Rango;
    private TextView Puesto;
    private Button Regresar;
    private String CorreoUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);
        Regresar =(Button)findViewById(R.id.Regresar);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(Referencias.Principal);
        nombre=(TextView)findViewById(R.id.nombre);
        edad=(TextView)findViewById(R.id.edad);
        correo=(TextView)findViewById(R.id.correo);
        Rango = (TextView)findViewById(R.id.Rango);
        Puesto = (TextView) findViewById(R.id.Puesto);
        Bundle extras = getIntent().getExtras();
        listaUsuarios=new ArrayList<Usuario>();
        //muestra datos del usuario
        if (extras != null) {
            CorreoUsuario=extras.getString("correo");
        }
        Log.d("ekey",myRef.getKey());
        Log.d("e","asas");
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DatosUsuario.this, MainActivity.class);

                startActivity(intent);
            }
        });
        myRef.child(Referencias.Usuario).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Usuario usuario = snapshot.getValue(Usuario.class);
                    if(usuario == null){
                        Log.d("estoEntro2","nulo");
                    }else{
                        if(CorreoUsuario.equals(usuario.getCorreo())) {
                            listaUsuarios.add(usuario);
                            nombre.setText("Nombre :"+usuario.getNombre().toString());
                            edad.setText("Edad :"+Integer.toString(usuario.getEdad()));
                            correo.setText("Correo :"+usuario.getCorreo().toString());
                            Rango.setText("Rango :"+usuario.getRango().toString());
                            Puesto.setText("Puesto :"+usuario.getPuesto().toString());
                        }
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       /* nombre.setText("Nombre :"+listaUsuarios.get(0).getNombre());
        edad.setText("Edad :"+listaUsuarios.get(0).getEdad());
        correo.setText("Correo :"+listaUsuarios.get(0).getCorreo());
        Rango.setText("Rango :"+listaUsuarios.get(0).getRango());
        Puesto.setText("Puesto :"+listaUsuarios.get(0).getPuesto());*/
    }

}
