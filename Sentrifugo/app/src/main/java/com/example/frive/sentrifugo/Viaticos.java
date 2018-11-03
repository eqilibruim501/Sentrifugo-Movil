package com.example.frive.sentrifugo;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frive.sentrifugo.Base.Referencias;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Viaticos extends AppCompatActivity {
    private Button sacarViatico;
    private TextView monto,motivo;
    private Button regresar;
    String Correo,Nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viaticos);
        motivo=(TextView)findViewById(R.id.Justificacion);
        monto=(TextView)findViewById(R.id.Monto);
        regresar=(Button)findViewById(R.id.Regresar);
        sacarViatico=(Button)findViewById(R.id.solicitud);
        Bundle extras = getIntent().getExtras();
        //crea notificacion
        if (extras != null) {
            Correo=extras.getString("Correo");
            Nombre=extras.getString("Nombre");
        }
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Viaticos.this,MenuInicio.class);
                intent.putExtra("correo",Correo);
                startActivity(intent);
            }
        });
        //muestra hora
        //saca cita
        sacarViatico.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //guarda de firebase
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference(Referencias.Principal);
                int mont=0;
                if(!motivo.getText().toString().equals("")  && !monto.getText().toString().equals("")
                       ) {
                    mont = Integer.parseInt(monto.getText().toString());
                    Viatico viatico =
                            new Viatico(Nombre, Correo,motivo.getText().toString(),
                                    mont);
                    myRef.child(Referencias.Viaticos).push().setValue(viatico);
                    Toast.makeText(Viaticos.this,"La solicitud se realizo correctamente", Toast.LENGTH_LONG).show();

                    monto.setText("");
                    motivo.setText("");

                }else{
                    Toast.makeText(Viaticos.this,"No deje ningun espacio en blanco, " +
                            ", debe de ingresar motivo y monto", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
