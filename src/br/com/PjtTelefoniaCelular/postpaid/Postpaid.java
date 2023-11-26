package br.com.PjtTelefoniaCelular.postpaid;
import br.com.PjtTelefoniaCelular.call.Call;
import br.com.PjtTelefoniaCelular.subscribers.Subscribers;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Postpaid extends Subscribers{
    private float subscription;
    private Call[] calls;
    private int numCalls;

    // Construtor
    public Postpaid(long cpf, String name, int celNumber) {
        super(cpf, name, celNumber, celNumber);
        this.subscription = subscription;
        this.calls = new Call[100]; // Tamanho arbitrário para o vetor de chamadas
        this.numCalls = 0;
    }

    // Método para registrar uma chamada
    public void makeCall(GregorianCalendar date, int duration) {
        float costCall = 1.04f * duration;

        if (numCalls < calls.length) {
            // Registra a chamada (supondo que exista uma classe Chamada)
            Call call = new Call(date, duration);
            calls[numCalls] = call;
            numCalls++;

            System.out.println("Chamada realizada com sucesso!");
        } else {
            System.out.println("Limite de chamadas atingido. Não foi possível realizar a chamada.");
        }
    }

    // Método para imprimir a fatura
    public void printInvoice(int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Dados do assinante:");
        // ... código para imprimir dados do assinante ...

        System.out.println("Chamadas do mês " + month + ":");

        // ... código para iterar sobre chamadas do mês e imprimir os detalhes ...

        float valueTotalInvoice = calculateTotalValueInvoice();
        System.out.println("Valor total da fatura: R$ " + (subscription + valueTotalInvoice));
    }

    // Método auxiliar para calcular o valor total das chamadas
    public float calculateTotalValueInvoice() {
        float valueTotalInvoice = 0;

        for (int i = 0; i < numCalls; i++) {
            valueTotalInvoice += calls[i].getDuration() * 1.04f;
        }

        return valueTotalInvoice;
    }
}
