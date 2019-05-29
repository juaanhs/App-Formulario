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
        //        StringBuilder sb = new StringBuilder();
//        int digitos = telefone.length();
//        for (int i = 0; i < digitos; i++) {
//            if(i==0) {
//                sb.append(("("));
//            }
//            char digito = telefone.charAt(i);
//            sb.append(digito);
//            if(i==1){
//                sb.append((") "));
//            }
//            if(digitos==10 && i==5 || digitos==11 && i==6){
//                sb.append("-");
//            }
//        }
//        String telefoneFormatado = sb.toString();
        return telefone.replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3");
    }
}
