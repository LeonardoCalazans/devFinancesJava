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
    double saldo = 0, entradaTotal, saidaTotal;
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
        // We have added a title in the custom layout. So let's disable the default
        // title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // The user will be able to cancel the dialog bu clicking anywhere outside the
        // dialog.
        dialog.setCancelable(true);
        // Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.modal);

        // Initializing the views of the dialog.
        final EditText descricaoEt = dialog.findViewById(R.id.descricao);
        final EditText valorEt = dialog.findViewById(R.id.valor);

        Button submitConfirm = dialog.findViewById(R.id.submit_confirm);
        Button submitCancel = dialog.findViewById(R.id.submit_cancel);

        submitCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        submitConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descricao = descricaoEt.getText().toString();
                String valor = valorEt.getText().toString();
                if (!valor.isEmpty()) {
                    double valorFloat = Double.parseDouble(String.valueOf(valor));
                    saldo += valorFloat;
                    if (!descricao.isEmpty() && valorFloat > 0) {
                        entradaTotal += valorFloat;
                        populateTransacao(descricao, valor.toString());
                        transacaoEntrada(Double.toString(entradaTotal));
                        transacaoTotal(Double.toString(saldo));
                    }
                    if (!descricao.isEmpty() && valorFloat < 0) {
                        saidaTotal += valorFloat;
                        populateTransacao(descricao, valor.toString());
                        transacaoSaida(Double.toString(saidaTotal));
                        transacaoTotal(Double.toString(saldo));
                    }
                }
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

    void transacaoEntrada(String valor) {
        TextView textViewEntradaSaldo;
        textViewEntradaSaldo = findViewById(R.id.textViewEntradaSaldo);
        String text = String.format(getString(R.string.entrada_valor), valor);
        textViewEntradaSaldo.setText(text);
    }

    void transacaoSaida(String valor) {
        TextView textViewSaidaSaldo;
        textViewSaidaSaldo = findViewById(R.id.textViewSaidaSaldo);
        String text = String.format(getString(R.string.saida_valor), valor);
        textViewSaidaSaldo.setText(text);
    }

    void transacaoTotal(String valor) {
        TextView textViewTotalSaldo;
        textViewTotalSaldo = findViewById(R.id.textViewTotalSaldo);
        String text = String.format(getString(R.string.total_valor), valor);
        textViewTotalSaldo.setText(text);
    }
}