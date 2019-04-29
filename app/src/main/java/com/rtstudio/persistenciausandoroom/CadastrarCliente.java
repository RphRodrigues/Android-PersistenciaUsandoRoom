package com.rtstudio.persistenciausandoroom;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.rtstudio.persistenciausandoroom.classe.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CadastrarCliente extends AppCompatActivity {

    private EditText edtNomeCliente;
    private EditText edtTelefoneCliente;
    private EditText edtDataUltimaCompraCliente;
    private EditText edtNumeroCupomCliente;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        inicializarVariaveis();

        if (getIntent().hasExtra("bundleCliente")) {

            Bundle bundleEditarCliente = getIntent().getBundleExtra("bundleCliente");

            Cliente clienteEditavel = (Cliente) bundleEditarCliente.getSerializable("cliente");

            String data;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY", new Locale("pt-BR"));

            if (clienteEditavel != null) {
                data = sdf.format(clienteEditavel.getUltimaCompra());
                edtNomeCliente.setText(clienteEditavel.getNomeCliente());
                edtTelefoneCliente.setText(clienteEditavel.getTelefone());
                edtDataUltimaCompraCliente.setText(data);
                edtNumeroCupomCliente.setText(clienteEditavel.getCodigoCupom());
            }

        }

        edtDataUltimaCompraCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();

                if (!edtDataUltimaCompraCliente.getText().toString().isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));
                    try {
                        calendar.setTime(sdf.parse(edtDataUltimaCompraCliente.getText().toString()));
                    } catch (ParseException e) {

                        e.printStackTrace();
                    }
                }

                new DatePickerDialog(CadastrarCliente.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar c = Calendar.getInstance();
                        c.set(year, month, dayOfMonth);
                        String data = new SimpleDateFormat("dd/MM/YYYY", new Locale("pt-BR")).format(c.getTime());
                        edtDataUltimaCompraCliente.setText(data);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResposta = new Intent();
                if (edtNomeCliente.getText().toString().isEmpty() || edtTelefoneCliente.getText().toString().isEmpty()) {
                    setResult(RESULT_CANCELED, intentResposta);
                } else {
                    Date dataUltimaCompra = null;
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));
                        dataUltimaCompra = format.parse(edtDataUltimaCompraCliente.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Cliente cliente = new Cliente(
                            edtNomeCliente.getText().toString(),
                            edtTelefoneCliente.getText().toString(),
                            dataUltimaCompra,
                            edtNumeroCupomCliente.getText().toString()
                    );

                    Bundle bundleCliente = new Bundle();
                    bundleCliente.putSerializable("cliente", cliente);

                    intentResposta.putExtra("bundleCliente", bundleCliente);
                }

                setResult(RESULT_OK, intentResposta);
                finish();
            }
        });
    }

    public void inicializarVariaveis() {
        edtNomeCliente = findViewById(R.id.cadastrar_edtNome);
        edtTelefoneCliente = findViewById(R.id.cadastrar_edtTelefone);
        edtDataUltimaCompraCliente = findViewById(R.id.cadastrar_edtDataUltimaCompra);
        edtNumeroCupomCliente = findViewById(R.id.cadastrar_edtCodigoCupom);
        btnSalvar = findViewById(R.id.cadastrar_btnSalvar);
    }
}
