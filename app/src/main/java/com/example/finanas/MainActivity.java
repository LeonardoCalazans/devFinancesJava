package com.example.finanas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button openDialog;
    TextView transacao;
    ArrayList<Object> listaTransacoes = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDialog = findViewById(R.id.buttonNovaTransacao);
        transacao = findViewById(R.id.transacao);

        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showModal();
            }
        });
    }

    public void newTransaction(View view) {

    }

    void showModal() {
        final Dialog dialog = new Dialog(MainActivity.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.modal);

        //Initializing the views of the dialog.
        final EditText descricaoEt = dialog.findViewById(R.id.descricao);
        final EditText valorEt = dialog.findViewById(R.id.valor);
        Button submitConfirm = dialog.findViewById(R.id.submit_confirm);
        Button submitCancel = dialog.findViewById(R.id.submit_confirm);

        submitConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descricao = descricaoEt.getText().toString();
                String valor = valorEt.getText().toString();
                populateTransacao(descricao,valor);
                if(true){
                    transacaoEntrada(valor);
                }
                transacaoTotal(valor);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    void populateTransacao(String descricao, String valor) {

        transacao.setVisibility(View.VISIBLE);

        String text = String.format(getString(R.string.descricao_transacao), descricao, valor);

        listaTransacoes.add(text);
        transacao.setText(text);
    }

    void transacaoEntrada(String valor){
        TextView textViewEntradaSaldo;
        textViewEntradaSaldo = findViewById(R.id.textViewEntradaSaldo);
        String text = String.format(getString(R.string.entrada_valor), valor);
        textViewEntradaSaldo.setText(text);
    }

    void transacaoTotal(String valor){
        TextView textViewTotalSaldo;
        textViewTotalSaldo = findViewById(R.id.textViewTotalSaldo);
        String text = String.format(getString(R.string.total_valor), valor);
        textViewTotalSaldo.setText(text);
    }

}