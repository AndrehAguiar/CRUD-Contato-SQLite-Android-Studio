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

import static com.example.topartes.crudcontato.R.id.edTxtNome;
import static com.example.topartes.crudcontato.R.id.edTxtEmail;

public class LongClkListen implements View.OnLongClickListener {

    Context context;
    String id;

    @Override
    public boolean onLongClick(View v) {

        context = v.getContext();
        id = v.getTag().toString();

        final CharSequence[] itens = {"Editar", "Deletar"};

        new AlertDialog.Builder(context).setTitle("Detalhes do Contato")
                .setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            edContatoID(Integer.parseInt(id));
                        } else if (item == 1) {

                            boolean excluidoSucesso =
                                    new ContatoCtrl(context).delete(Integer.parseInt(id));

                            if (excluidoSucesso) {
                                Toast.makeText(context, "Contato deletado.",
                                        Toast.LENGTH_SHORT).show();

                                ((MainActivity) context).countContatos();
                                ((MainActivity) context).loadLstContatos();
                            } else {
                                Toast.makeText(context, "Erro ao deletar o contato.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }).show();

        Toast.makeText(v.getContext(),"Clicado num item da lista.",Toast.LENGTH_SHORT).show();
        return false;
    }

    public void edContatoID(final int contatoID){

        Toast.makeText(context,"Editando "+contatoID,Toast.LENGTH_SHORT).show();

        final ContatoCtrl contatoCtrl = new ContatoCtrl(context);
        final Contato contato = contatoCtrl.idSelectContato(contatoID);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // ContatoController
        final ContatoCtrl contatoController =
                new ContatoCtrl(context);

        View formContato = inflater.inflate(R.layout.activity_contato_form,null,false);

        // Popular nome e email com dados da lista
        final EditText editTextNome = (EditText) formContato.findViewById(edTxtNome);
        final  EditText editTextEmail = (EditText) formContato.findViewById(edTxtEmail);

        editTextNome.setText(contato.getNome());
        editTextEmail.setText(contato.getEmail());

        // Show do formulário com dados populados

        new AlertDialog.Builder(context)
                .setView(formContato)
                .setTitle("Editar")
                .setPositiveButton("Atualizar dados",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {


                                Contato novoContato = new Contato();
                                novoContato.setId(contatoID);
                                novoContato.setNome(editTextNome.getText().toString());
                                novoContato.setEmail(editTextEmail.getText().toString());

                                boolean isUpdate = contatoController.update(novoContato);

                                if(isUpdate){

                                    Toast.makeText(context,"Dados atualizados com sucesso....",Toast.LENGTH_SHORT).show();

                                    ((MainActivity) context).loadLstContatos();

                                }else{
                                    Toast.makeText(context,"Falha ao Salvar Contato",Toast.LENGTH_SHORT).show();
                                }

                                dialog.cancel();

                            }

                        }).show();
    }


}
