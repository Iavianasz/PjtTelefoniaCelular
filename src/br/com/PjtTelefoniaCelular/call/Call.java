package br.com.PjtTelefoniaCelular.call;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Call {
    private GregorianCalendar date;
    private int duration;

    // Construtor
    public Call(GregorianCalendar date, int duration) {
        this.date = date;
        this.duration = duration;
    }

    // Método para obter a data
    public GregorianCalendar getDate() {
        return date;
    }

    // Método para obter a duração
    public int getDuracao() {
        return duration;
    }

    // Método para obter uma representação textual da chamada
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Data: " + sdf.format(date.getTime()) + "\nDuração: " + duration + " minutos";
    }
}