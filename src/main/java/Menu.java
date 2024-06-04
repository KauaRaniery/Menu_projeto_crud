import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Menu {
    private static Scanner entrada = new Scanner(System.in);
    private static FileWriter arquivoSaida;

    public static void main(String[] args) {
        try {
            arquivoSaida = new FileWriter("respostas.txt");
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo de saída: " + e.getMessage());
            System.exit(1);
        }

        int opcao;

        do {
            exibirMenu();
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    consultar();
                    break;
                case 3:
                    remover();
                    break;
                case 4:
                    editar();
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);

        entrada.close();
        try {
            arquivoSaida.close();
        } catch (IOException e) {
            System.out.println("Erro ao fechar arquivo de saída: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        escreverNoArquivo("------------------ MENU -------------------");
        escreverNoArquivo("1. Cadastrar");
        escreverNoArquivo("2. Consultar");
        escreverNoArquivo("3. Remover");
        escreverNoArquivo("4. Editar");
        escreverNoArquivo("5. Sair");
        escreverNoArquivo("Digite sua opção: ");
    }

    private static ArrayList<String> itens = new ArrayList<>();

    private static void cadastrar() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite o item a ser cadastrado: ");
        String item = entrada.nextLine();
        itens.add(item);
        escreverNoArquivo("Item cadastrado com sucesso!");

        entrada.close();
    }

    private static void consultar() {
        if (itens.isEmpty()) {
            escreverNoArquivo("Não há itens cadastrados.");
        } else {
            escreverNoArquivo("\nItens cadastrados:");
            for (String item : itens) {
                escreverNoArquivo("- " + item);
            }
        }
    }

    private static void remover() {
        if (itens.isEmpty()) {
            escreverNoArquivo("Não há itens cadastrados.");
        } else {
            System.out.print("Digite o índice do item a ser removido: ");
            int indice = entrada.nextInt();

            if (indice >= 0 && indice < itens.size()) {
                itens.remove(indice);
                escreverNoArquivo("Item removido com sucesso!");
            } else {
                escreverNoArquivo("Índice inválido!");
            }
        }
    }

    private static void editar() {
        if (itens.isEmpty()) {
            escreverNoArquivo("Não há itens cadastrados para editar.");
        } else {
            System.out.print("Digite o índice do item a ser editado: ");
            int indice = entrada.nextInt();

            if (indice >= 0 && indice < itens.size()) {
                Scanner entrada = new Scanner(System.in);
                System.out.print("Digite o novo valor do item: ");
                String novoItem = entrada.nextLine();
                itens.set(indice, novoItem);
                escreverNoArquivo("Item editado com sucesso!");

                entrada.close();
            } else {
                escreverNoArquivo("Índice inválido!");
            }
        }
    }

    private static void escreverNoArquivo(String texto) {
        try {
            arquivoSaida.write(texto + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}