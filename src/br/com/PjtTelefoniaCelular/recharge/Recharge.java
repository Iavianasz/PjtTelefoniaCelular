package br.com.PjtTelefoniaCelular.recharge;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Recharge {
    private GregorianCalendar date;
    private float value;

    //Construtor da Classe
    public Recharge(GregorianCalendar date, float value) {
        this.date = date;
        this.value = value;
    }

    // getters e setters
    public GregorianCalendar getDate() {
        return date;
    }

    public float getValue() {
        return value;
    }

    // Método toString para representação textual
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Data: " + sdf.format(date.getTime()) + "\nValor: R$ " + value;
    }
}

//r