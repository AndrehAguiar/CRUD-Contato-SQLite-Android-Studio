package com.example.topartes.crudcontato.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.topartes.crudcontato.R;
import com.example.topartes.crudcontato.controller.ContatoCtrl;
import com.example.topartes.crudcontato.model.Contato;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnCriaContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCriaContato = (Button) findViewById(R.id.btnCriaContato);
        btnCriaContato.setOnClickListener(new ClkListener());
        countContatos();
        loadLstContatos();

        new ContatoCtrl(this).idSelectContato(2);
    }

    public void countContatos(){
        String msg = "";
        int contador = new ContatoCtrl(this).totalContatos();

        TextView tvContador = (TextView) findViewById(R.id.tvContador);
        if (contador == 0){
            msg = "Nenhum registro encontrado!";
        }else if (contador == 1){
            msg = contador + " cadastrados!";
        }else {
            msg = contador + "  cadastrados!";
        }

        tvContador.setText(msg);

    }

    public void loadLstContatos(){
        LinearLayout linearLayoutRecords = (LinearLayout)
                findViewById(R.id.objContatos);
        linearLayoutRecords.removeAllViews();

        List<Contato> usuarios = new ContatoCtrl(this).listaContatos();
        if(usuarios.size() > 0){
            for(Contato obj : usuarios){

                int id = obj.getId();
                String nome = obj.getNome();
                String email = obj.getEmail();

                String tvContents = nome + " - " + email;

                TextView textViewContatoItem = new TextView(this);
                textViewContatoItem.setPadding(0, 10,0, 10);
                textViewContatoItem.setText(tvContents);
                textViewContatoItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewContatoItem);
                textViewContatoItem.setOnLongClickListener(new LongClkListen());
            }
        }else{

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("Nenhum Contato Cadastrado.");

            linearLayoutRecords.addView(locationItem);

        }
    }

}
