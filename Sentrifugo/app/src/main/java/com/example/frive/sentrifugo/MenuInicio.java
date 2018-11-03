package com.example.frive.sentrifugo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuInicio extends AppCompatActivity {

    private Button btnPerfilUsuario;
    private Button btnSolicitarVacaciones;
    private Button btnIngresarViaticos;
    private Button btnSalir;

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);
        btnPerfilUsuario = (Button) findViewById(R.id.btn_perfil_usuario);
        btnSolicitarVacaciones = (Button) findViewById(R.id.btn_solicitud_vacaciones);
        btnIngresarViaticos = (Button) findViewById(R.id.btn_ingresar_viaticos);
        btnSalir = (Button) findViewById(R.id.btn_salida);
        //extraer informacion enviada desde el anterior activity
        Bundle extras = getIntent().getExtras();
        //crea notificacion
        if (extras != null) {
            email = extras.getString("correo");
        }

        //Boton de redireccion a la ventana de Datos Usuario
        btnPerfilUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuInicio.this, DatosUsuario.class);
                intent.putExtra("correo",email);
                startActivity(intent);
            }
        });

        //Boton de redireccion a la ventana de Pedir Vacaciones
        btnSolicitarVacaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuInicio.this, PedirVaciones.class);
                intent.putExtra("correo",email);
                startActivity(intent);
            }
        });

        //Boton de redireccion a la ventana de Datos Usuario
        btnIngresarViaticos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //modificar al tener la ventana de viaticos
                Intent intent=new Intent(MenuInicio.this, PedirVaciones.class);
                intent.putExtra("correo",email);
                startActivity(intent);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuInicio.this, MainActivity.class);
                intent.putExtra("correo",email);
                startActivity(intent);
            }
        });
    }

}
