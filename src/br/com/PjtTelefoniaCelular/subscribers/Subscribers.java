package br.com.PjtTelefoniaCelular.subscribers;
import br.com.PjtTelefoniaCelular.call.Call;

public class Subscribers {
   private long cpf;
   private String name;
   private int celNumber;
   protected int numOfCalls;
   protected Call[] calls;

   public Subscribers(long cpf, String name, int celNumber) {
      this.cpf = cpf;
      this.name = name;
      this.celNumber = celNumber;
      this.calls = new Call[10];
      this.numOfCalls = 0;
   }

   public Long getCpf() {

      return cpf;
   }

   public String getName(){
      return name;
   }

   public int getCelNumber(){
      return celNumber;
   }

   @Override
   public String toString() {
      return "CPF: " + cpf + "\nNome: " + name + "\nNúmero: " + celNumber + "\nNúmero de Chamadas: " + numOfCalls;
   }








}






