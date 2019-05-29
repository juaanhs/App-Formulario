package br.com.juaanhs.cadastro.ui.activity.formatador;

public class FormataTelefone {

    public String remove(String telefone) {
        return telefone
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "");
    }

    public String formata(String telefone) {
        return telefone.replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3");
    }
}
