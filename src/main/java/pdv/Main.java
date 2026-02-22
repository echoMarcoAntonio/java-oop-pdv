package main.java.pdv;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));

        // Teste 1: Forçando erro de preço no Produto
        try {
            System.out.println("Testando preço negativo...");
            new Produto("Erro", "001", new BigDecimal("-1.00"));
        } catch (RuntimeException e) {
            System.err.println("Capturado esperado: " + e.getMessage());
        }

        // Teste 2: Forçando erro de quantidade no ItemVenda
        try {
            System.out.println("\nTestando quantidade zero...");
            Produto p = new Produto("Teste", "002", new BigDecimal("10.00"));
            new ItemVenda(p, 0);
        } catch (RuntimeException e) {
            System.err.println("Capturado esperado: " + e.getMessage());
        }

        // Teste 3: Fluxo de sucesso
        try {
            System.out.println("\nExecutando fluxo válido...");
            Produto arroz = new Produto("Arroz 5kg", "ARR001", new BigDecimal("25.90"));
            ItemVenda item = new ItemVenda(arroz, 3);

            System.out.println("Produto: " + arroz.getNome());
            System.out.println("Quantidade: " + item.getQuantidade());
            System.out.println("Subtotal: " + nf.format(item.getSubtotal()));
        } catch (RuntimeException e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
}