package com.example.frive.sentrifugo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.frive.sentrifugo.Base.Referencias;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Proyectos_ListView extends AppCompatActivity {
    private List<Proyecto> listaProyectos;

    private ListView listView;
    private TextView nombre;
    private TextView precio;
    private String correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyectos__list_view);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(Referencias.Principal);
        listView = (ListView) findViewById(R.id.listView_Proyectos);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            correo=extras.getString("correo");
        }
        listaProyectos=new ArrayList<Proyecto>();

        // funcion que manda los datos de los proyectos
        final CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        myRef.child(Referencias.Proyectos).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaProyectos.removeAll(listaProyectos);
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Proyecto proyecto =snapshot.getValue(Proyecto.class);
                    listaProyectos.add(proyecto);
                }
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                //pasa los datos del proyecto a otra clase
                Intent intent = new Intent(Proyectos_ListView.this,Viaticos.class);
                Proyecto proyecto=listaProyectos.get(position);
                intent.putExtra("Nombre",proyecto.getNombre());
                intent.putExtra("Correo",correo);
                startActivity(intent);
            }
        });


    }


    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return listaProyectos.size();
        }

        @Override
        public Object getItem(int position) {
            return listaProyectos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // agrega los datos al listview
            ViewHolder vh=new ViewHolder();
            if (view == null) {

                view = getLayoutInflater().inflate(R.layout.datosproyecto, null);
                vh.nombre = (TextView) view.findViewById(R.id.textView_nombre);
                vh.descripcion= (TextView) view.findViewById(R.id.textView_descripcion);
                view.setTag(vh);
            } else {

                vh = (ViewHolder) view.getTag();
            }
            int tamano=listaProyectos.size();

            if(i<tamano) {
                vh.nombre.setText("Nombre: "+listaProyectos.get(i).getNombre());
                vh.descripcion.setText("Descripcion: "+listaProyectos.get(i).getDescripcion());
               // Glide.with(getApplicationContext()).load(listapaseadores.get(i).getUrl()).into(vh.image);

            }

            return view;
        }
    }


    public class ViewHolder {
        TextView nombre;
        TextView descripcion;

    }
}
