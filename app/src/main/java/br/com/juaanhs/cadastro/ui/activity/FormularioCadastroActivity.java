package br.com.juaanhs.cadastro.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.juaanhs.cadastro.R;

public class FormularioCadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);

        inicializaCampos();
    }

    private void inicializaCampos() {
        configuraCampoNomeCompleto();
        configuraCampoCpf();
        configuraCampoTelefone();
        configuraCampoEmail();
        configuraCampoSenha();
    }

    private void configuraCampoSenha() {
        TextInputLayout textInputSenha = findViewById(R.id.formulario_cadastro_senha);
        //EditText campoSenha = textInputSenha.getEditText();
        adicionaValidacaoPadrao(textInputSenha);
    }

    private void configuraCampoEmail() {
        TextInputLayout textInputEmail = findViewById(R.id.formulario_cadastro_email);
        //EditText campoEmail = textInputEmail.getEditText();
        adicionaValidacaoPadrao(textInputEmail);
    }

    private void configuraCampoTelefone() {
        TextInputLayout textInputTelefone = findViewById(R.id.formulario_cadastro_telefone);
        //EditText campoTelefone = textInputTelefone.getEditText();
        adicionaValidacaoPadrao(textInputTelefone);
    }

    private void configuraCampoCpf() {
        TextInputLayout textInputCpf = findViewById(R.id.formulario_cadastro_cpf);
        //EditText campoCpf = textInputCpf.getEditText();
        adicionaValidacaoPadrao(textInputCpf);
    }

    private void configuraCampoNomeCompleto() {
        TextInputLayout textInputNomeCompleto = findViewById(R.id.formulario_cadastro_nome_completo);
        //EditText campoNomeCompleto = textInputNomeCompleto.getEditText();
        adicionaValidacaoPadrao(textInputNomeCompleto);
    }

    private void adicionaValidacaoPadrao(final TextInputLayout textInputCampo) {
        final EditText campo = textInputCampo.getEditText();
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String texto = campo.getText().toString();
                if(!hasFocus) {
                    validaCampoObrigatorio(texto, textInputCampo);
                }
            }
        });
    }

    private void validaCampoObrigatorio(String texto, TextInputLayout textInputCampo) {
        if(texto.isEmpty()) {
            textInputCampo.setError("Campo obrigatório");
        } else {
            textInputCampo.setError(null);
            textInputCampo.setErrorEnabled(false);
        }
    }

//    private void adicionaValidacaoPadrao(final EditText campo) {
//        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                String texto = campo.getText().toString();
//                if(!hasFocus) {
//                    if (texto.isEmpty()) {
//                        campo.setError("Campo obrigatório");
//                    }
//                }
//            }
//        });
//    }
}
