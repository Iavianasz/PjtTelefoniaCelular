package br.com.PjtTelefoniaCelular.call;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

// Definição da classe Call
public class Call {

    // Atributos da classe
    private GregorianCalendar date;
    private int duration;

    // Construtor da classe, usado para inicializar objetos Call
    public Call(GregorianCalendar date, int duration) {
        this.date = date;
        this.duration = duration;
    }

    // Getter para obter a data da chamada
    public GregorianCalendar getDate() {
        return date;
    }

    // Getter para obter a duração da chamada
    public int getDuration() {
        return duration;
    }

    // Sobrescreve o método toString da classe Object para fornecer uma representação em string formatada
    @Override
    public String toString() {
        // Criando um objeto SimpleDateFormat para formatar a data
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // Retorna uma string formatada contendo a data e a duração da chamada
        return "Data: " + sdf.format(date.getTime()) + "\nDuração: " + duration + " minutos";
    }
}
