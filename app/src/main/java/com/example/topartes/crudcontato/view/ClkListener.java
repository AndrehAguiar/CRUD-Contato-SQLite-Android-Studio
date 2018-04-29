package com.example.topartes.crudcontato.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.topartes.crudcontato.R;
import com.example.topartes.crudcontato.controller.ContatoCtrl;
import com.example.topartes.crudcontato.model.Contato;

public class ClkListener implements View.OnClickListener{

    @Override
    public void onClick(View v) {

        final Context context = v.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.activity_contato_form, null, false);

        final EditText edTxtNome = (EditText) formElementsView.findViewById(R.id.edTxtNome);
        final EditText edTxtEmail = (EditText) formElementsView.findViewById(R.id.edTxtEmail);

        new AlertDialog.Builder(context)
            .setView(formElementsView)
            .setTitle("Criar Contato")
            .setPositiveButton("Incluir",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        // Regra de negócio para Incluir novos contatos
                        String contatoNome = edTxtNome.getText().toString();
                        String contatoEmail = edTxtEmail.getText().toString();

                        Contato contato = new Contato();

                        contato.setNome(contatoNome);
                        contato.setEmail(contatoEmail);

                        boolean criadoComSucesso = new ContatoCtrl(context).create(contato);

                        if (criadoComSucesso) {
                            Toast.makeText(context, "Contato incluído com sucesso.", Toast.LENGTH_SHORT).show();

                            ((MainActivity) context).countContatos();

                        } else {
                            Toast.makeText(context, "Não foi possível incluir o contato.", Toast.LENGTH_SHORT).show();
                        }



                        dialog.cancel();
                    }

                }).show();

    }
}
