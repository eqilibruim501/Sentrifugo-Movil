package com.example.frive.sentrifugo;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.frive.sentrifugo.Base.Referencias;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PedirVaciones extends AppCompatActivity {
    private NotificationManager notificationManager;
    ImageButton bfecha;
    EditText efecha;
    private int dia, mes, anio, hora, minutos;
    DatePickerDialog dpd;
    TimePickerDialog timePickerDialog;
    private Button sacarCita;
    private TextView nombre,dias,motivo;
    private Button regresar;
    String Correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_vaciones);
        bfecha = findViewById(R.id.bfecha);
        efecha = findViewById(R.id.efecha);
        nombre = (TextView)findViewById(R.id.Nombre);
        dias = (TextView)findViewById(R.id.Dias);
        motivo=(TextView)findViewById(R.id.Motivo);
        efecha.setKeyListener(null);
        regresar=(Button)findViewById(R.id.Regresar);
        sacarCita=(Button)findViewById(R.id.solicitud);
        Bundle extras = getIntent().getExtras();
        //crea notificacion
        if (extras != null) {
            Correo=extras.getString("correo");
        }
        //muestra fecha
        bfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);

                mes= c.get(Calendar.MONTH);

                anio = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(PedirVaciones.this, new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day){
                        efecha.setVisibility(View.VISIBLE);
                        efecha.setText(day + "/" + (month+1)+ "/" + year);
                    }
                }
                        ,dia ,mes ,anio);
                dpd.updateDate(anio, mes, dia);
                dpd.show();
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PedirVaciones.this,MenuInicio.class);

                startActivity(intent);
            }
        });
        //muestra hora
        //saca cita
        sacarCita.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //guarda de firebase
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference(Referencias.Principal);
                int dia=0;
                if(!nombre.getText().toString().equals("")  && !motivo.getText().toString().equals("")
                        && !efecha.getText().toString().equals("") && !dias.getText().toString().equals("")
                        && nombre.getText().toString().length()<=10) {
                    dia = Integer.parseInt(dias.getText().toString());
                    Vacacion vacacion =
                            new Vacacion(nombre.getText().toString(),
                                    efecha.getText().toString(), dia,
                                    motivo.getText().toString(),Correo);
                    myRef.child(Referencias.Vacacion).push().setValue(vacacion);
                    Toast.makeText(PedirVaciones.this,"La solicitud se saco correctamente", Toast.LENGTH_LONG).show();

                    nombre.setText("");
                    efecha.setText("");
                    dias.setText("");
                    motivo.setText("");

                }else{
                    Toast.makeText(PedirVaciones.this,"No deje ningun espacio en blanco, el nombre debe ser menor a 10 caracteres y el numero menor o igual a 8 digitos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
