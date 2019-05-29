package br.com.juaanhs.cadastro.ui.activity.validador;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidaCpf {

    public static final String CPF_INVALIDO = "CPF inválido";
    public static final String DEVE_TER_ONZE_DIGITOS = "O CPF precisa ter 11 digitos";
    private final TextInputLayout textInputCpf;
    private final EditText campoCpf;
    private final ValidacaoPadrao validadorPadrao;
    private final CPFFormatter formatador = new CPFFormatter();

    public ValidaCpf(TextInputLayout textInputCpf) {
        this.textInputCpf = textInputCpf;
        this.campoCpf = textInputCpf.getEditText();
        this.validadorPadrao = new ValidacaoPadrao(textInputCpf);
    }

    public boolean estaValido(){
        if(!validadorPadrao.estaValido()) return false;
        String cpf = getCpf();
        if(!validaCampoComOnzeDigitos(cpf)) return false;
        if(!validaCalculoCPF(cpf)) return false;

        adicionaFormatacao(cpf);
        return true;
    }

    private void adicionaFormatacao(String cpf) {
        String cpfFormatado = formatador.format(cpf);
        campoCpf.setText(cpfFormatado);
    }

    private boolean validaCalculoCPF(String cpf) {
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        }catch (InvalidStateException e){
            textInputCpf.setError(CPF_INVALIDO);
            return false;
        }
        return true;
    }

    private String getCpf() {
        return campoCpf.getText().toString();
    }

    private boolean validaCampoComOnzeDigitos(String cpf) {
        if(cpf.length() != 11) {
            textInputCpf.setError(DEVE_TER_ONZE_DIGITOS);
            return false;
        }
        return true;
    }
}
