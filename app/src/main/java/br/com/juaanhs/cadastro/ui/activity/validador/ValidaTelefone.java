package br.com.juaanhs.cadastro.ui.activity.validador;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.com.juaanhs.cadastro.ui.activity.formatador.FormataTelefone;

public class ValidaTelefone {

    public static final String TELEFONE_DEVE_TER_DEZ_OU_ONZE_DIGITOS = "Telefone deve ter entre 10 a 11 dígitos";
    private final TextInputLayout textInputTelefone;
    private final ValidacaoPadrao validacaoPadrao;
    private final EditText campoTelefone;
    private final FormataTelefone formatador = new FormataTelefone();

    public ValidaTelefone(TextInputLayout textInputTelefone) {
        this.textInputTelefone = textInputTelefone;
        this.validacaoPadrao = new ValidacaoPadrao(textInputTelefone);
        this.campoTelefone = textInputTelefone.getEditText();
    }

    private boolean validaEntreDezOuOnzeDigitos(String telefone) {
        int digitos = telefone.length();
        if(digitos < 10 || digitos > 11){
            textInputTelefone.setError(TELEFONE_DEVE_TER_DEZ_OU_ONZE_DIGITOS);
            return false;
        }
        return true;
    }

    public boolean estaValido() {
        if(!validacaoPadrao.estaValido()) return false;
        String telefone = campoTelefone.getText().toString();
        if(!validaEntreDezOuOnzeDigitos(telefone)) return false;
        adicionaFormatacao(telefone);
        return true;
    }

    private void adicionaFormatacao(String telefone) {
        String telefoneFormatado = formatador.formata(telefone);
        campoTelefone.setText(telefoneFormatado);
    }


}
