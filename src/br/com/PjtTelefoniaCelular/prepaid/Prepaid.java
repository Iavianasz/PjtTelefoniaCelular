package br.com.PjtTelefoniaCelular.prepaid;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import br.com.PjtTelefoniaCelular.call.Call;
import br.com.PjtTelefoniaCelular.subscribers.Subscribers;
import br.com.PjtTelefoniaCelular.recharge.Recharge;

public class Prepaid extends Subscribers {
    private Recharge[] recharges;
    private int numRecharges;
    private float credit;

    // Construtor
    public Prepaid(long cpf, String name, int celNumber) {
        super(cpf, name, celNumber, celNumber);
        this.recharges = new Recharge[100];
        this.numRecharges = 0;
        this.credit = 0;
    }

    // Método para registrar uma chamada
    public void makeCall(GregorianCalendar date, int duration) {
        float costCall = 1.45f * duration;

        if (credit >= costCall) {
            credit -= costCall;

            // Registra a chamada (supondo que exista uma classe Call)
            Call call = new Call(date, duration);
            // ... código para adicionar chamada ao vetor de chamadas ...

            System.out.println("Chamada realizada com sucesso!");
        } else {
            System.out.println("Créditos insuficientes para fazer a chamada.");
        }
    }

    // Método para registrar uma recarga
    public void recharge(GregorianCalendar date, float value) { // Corrigido o nome do método e vetor
        if (numRecharges < recharges.length) { // Corrigido o nome do vetor
            // Registra a recarga (supondo que exista uma classe Recarga)
            Recharge recharge = new Recharge(date, value);
            recharges[numRecharges] = recharge; // Corrigido o nome do vetor
            numRecharges++;
            credit += value;

            System.out.println("Recarga realizada com sucesso!");
        } else {
            System.out.println("Limite de recargas atingido.");
        }
    }

    // Método para imprimir a fatura
    public void printInvoice(int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("CPF: " + cpf); // Corrigido o acesso ao CPF usando método getter
        System.out.println("Nome: " + name); // Corrigido o acesso ao nome usando método getter
        System.out.println("Número: " + celNumber); // Corrigido o acesso ao número usando método getter

        System.out.println("Chamadas e recargas do mês " + month + ":");

        // ... código para iterar sobre chamadas e recargas do mês e imprimir os detalhes ...

        System.out.println("Valor total de chamadas e recargas: " + calculateTotalValue());
        System.out.println("Créditos disponíveis: " + credit);
    }

    // Método auxiliar para calcular o valor total de chamadas e recargas
    private float calculateTotalValue() {
        float totalValue = 0;

        // ... código para somar os valores de chamadas e recargas ...

        return totalValue;
    }



}