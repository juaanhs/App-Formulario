package br.com.juaanhs.cadastro.ui.activity.formatador;

public class FormataCpf {

    public String formata(String cpf){
        return cpf.replaceAll("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})", "$1.$2.$3-$4");
    }
    public String remove(String cpf) {
        return cpf
                .replace(".", "")
                .replace("-", "");
    }
}
