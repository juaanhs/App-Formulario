package br.com.juaanhs.cadastro.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.juaanhs.cadastro.R;
import br.com.juaanhs.cadastro.ui.activity.formatador.FormataTelefone;
import br.com.juaanhs.cadastro.ui.activity.validador.ValidaCpf;
import br.com.juaanhs.cadastro.ui.activity.validador.ValidaEmail;
import br.com.juaanhs.cadastro.ui.activity.validador.ValidaTelefone;
import br.com.juaanhs.cadastro.ui.activity.validador.ValidacaoPadrao;
import br.com.juaanhs.cadastro.ui.activity.validador.Validador;

public class FormularioCadastroActivity extends AppCompatActivity {

    private static final String ERRO_FORMATACAO_CPF = "erro formatação cpf";
    private final List<Validador> validadores = new ArrayList<>();

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
        configuraBotaoCadastrar();
    }

    private void configuraBotaoCadastrar() {
        Button botaoCadastrar = findViewById(R.id.formulario_cadastro_botao_cadastrar);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean formularioEstaValido = validaTodosCampos();
                if(formularioEstaValido) {
                    cadastroRealizado();
                }
            }
        });
    }

    private void cadastroRealizado() {
        Toast.makeText(getApplicationContext(),
                "Cadastro realizado com sucesso!",
                Toast.LENGTH_SHORT).show();
    }

    private boolean validaTodosCampos() {
        boolean formularioEstaValido = true;
        for (Validador validador :
             validadores) {
            if(!validador.estaValido()){
                formularioEstaValido = false;
            }
        }
        return formularioEstaValido;
    }

    private void configuraCampoSenha() {
        TextInputLayout textInputSenha = findViewById(R.id.formulario_cadastro_senha);
        adicionaValidacaoPadrao(textInputSenha);
    }

    private void configuraCampoEmail() {
        TextInputLayout textInputEmail = findViewById(R.id.formulario_cadastro_email);
        EditText campoEmail = textInputEmail.getEditText();
        final ValidaEmail validador = new ValidaEmail(textInputEmail);
        validadores.add(validador);
        campoEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validador.estaValido();
                }
            }
        });
    }

    private void configuraCampoTelefone() {
        TextInputLayout textInputTelefone = findViewById(R.id.formulario_cadastro_telefone);
        final EditText campoTelefone = textInputTelefone.getEditText();
        final ValidaTelefone validador = new ValidaTelefone(textInputTelefone);
        final FormataTelefone formatador = new FormataTelefone();
        validadores.add(validador);
        campoTelefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String telefone = campoTelefone.getText().toString();
                if(hasFocus){
                    String telefoneSemFormatacao = formatador.remove(telefone);
                    campoTelefone.setText(telefoneSemFormatacao);
                } else {
                    validador.estaValido();
                }
            }
        });
    }

    private void configuraCampoCpf() {
        TextInputLayout textInputCpf = findViewById(R.id.formulario_cadastro_cpf);
        final EditText campoCpf = textInputCpf.getEditText();
        final CPFFormatter formatador = new CPFFormatter();
        final ValidaCpf validador = new ValidaCpf(textInputCpf);
        validadores.add(validador);
        campoCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    removeFormatacao(formatador, campoCpf);
                } else {
                    validador.estaValido();
                }
            }
        });
    }

    private void removeFormatacao(CPFFormatter formatador, EditText campoCpf) {
        String cpf = campoCpf.getText().toString();
        try {
            String cpfSemFormato = formatador.unformat(cpf);
            campoCpf.setText(cpfSemFormato);
        }catch (IllegalArgumentException e) {
            Log.e(ERRO_FORMATACAO_CPF, e.getMessage());
        }
    }

    private void configuraCampoNomeCompleto() {
        TextInputLayout textInputNomeCompleto = findViewById(R.id.formulario_cadastro_nome_completo);
        adicionaValidacaoPadrao(textInputNomeCompleto);
    }

    private void adicionaValidacaoPadrao(final TextInputLayout textInputCampo) {
        final EditText campo = textInputCampo.getEditText();
        final ValidacaoPadrao validador = new ValidacaoPadrao(textInputCampo);
        validadores.add(validador);
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validador.estaValido(); return;
                }
            }
        });
    }


}
