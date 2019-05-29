package br.com.juaanhs.cadastro.ui.activity.validador;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public class ValidaSenha implements Validador {

    private static final String SENHA_DEVE_TER_ENTRE_SEIS_A_VINTE_DIGITOS = "Senha deve ter entre 6 a 20 dígitos";
    private static final String SENHA_DEVE_CONTER_LETRAS_MAIUSCULAS_MINUSCULAS_E_NUMEROS = "Senha deve conter letras maiúsculas, minúsculas e números";
    private final TextInputLayout textInputSenha;
    private final EditText campoSenha;
    private ValidacaoPadrao validadorPadrao;

    public ValidaSenha(TextInputLayout textInputSenha) {
        this.textInputSenha = textInputSenha;
        this.campoSenha = textInputSenha.getEditText();
        this.validadorPadrao = new ValidacaoPadrao(this.textInputSenha);
    }

    private boolean validaPadrao(String senha){
        if(senha.matches("(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])[A-Za-z\\d@#$%]{6,20}")){
            return true;
        }
        textInputSenha.setError(SENHA_DEVE_CONTER_LETRAS_MAIUSCULAS_MINUSCULAS_E_NUMEROS);
        return false;
    }

    private boolean validaEntreSeisOuVinteDigitos(String senha){
        int digitos = senha.length();
        if(digitos < 6 || digitos > 20){
            textInputSenha.setError(SENHA_DEVE_TER_ENTRE_SEIS_A_VINTE_DIGITOS);
            return false;
        }
        return true;
    }

    @Override
    public boolean estaValido() {
        if(!validadorPadrao.estaValido()) return false;
        final String senha = campoSenha.getText().toString();
        if(!validaPadrao(senha)) return false;
        if(!validaEntreSeisOuVinteDigitos(senha)) return false;
        return true;
    }
}
