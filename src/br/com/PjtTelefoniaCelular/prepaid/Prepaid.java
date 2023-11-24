package br.com.PjtTelefoniaCelular.prepaid;
import br.com.PjtTelefoniaCelular.call.Call

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Prepaid {
    private long cpf;
    private String name;
    private String number;
    private Recharge[] recharge;
    private int numRecharge;
    private int credit;

    // Construtor
    public Prepaid(long cpf, String name, String number) {
        this.cpf = cpf;
        this.name = name;
        this.number = number;
        this.recharge = new Recharge[100]; // Tamanho arbitrário para o vetor de recargas
        this.numRecharge = 0;
        this.credit = 0;
    }

    // Método para registrar uma chamada
    public void makeCall(GregorianCalendar date, int duration) {
        float costCall = 1.45f * duration;

        if (credit >= costCall) {
            credit -= costCall;

            // Registra a chamada (supondo que exista uma classe Chamada)
            Call call = new Call(date, duration);
            // ... código para adicionar chamada ao vetor de chamadas ...

            System.out.println("Chamada realizada com sucesso!");
        } else {
            System.out.println("Créditos insuficientes para fazer a chamada.");
        }
    }

    // Método para registrar uma recarga
    public void recarregar(GregorianCalendar date, float value) {
        if (numRecharge < recharge.length) {
            // Registra a recarga (supondo que exista uma classe Recarga)
            Recharge recharge = new Recharge(date, value);
            recharge[numRecharge] = recharge;
            numRecharge++;
            credit += value;

            System.out.println("Recarga realizada com sucesso!");
        } else {
            System.out.println("Limite de recargas atingido.");
        }
    }

    // Método para imprimir a fatura 
    public void printInvoice(int month) {  
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("CPF: " + cpf);
        System.out.println("Nome: " + name);
        System.out.println("Número: " + number);

        System.out.println("Chamadas e recargas do mês " + month + ":");

        // ... código para iterar sobre chamadas e recargas do mês e imprimir os detalhes ...

        System.out.println("Valor total de chamadas e recargas: " + calculateTotalValue());
        System.out.println("Créditos disponíveis: " + credit);
    }

    // Método auxiliar para calcular o valor total de chamadas e recargas
    private float calculateTotalValue() {
        float TotalValue = 0;

        // ... código para somar os valores de chamadas e recargas ...

        return TotalValue;
    }
}
