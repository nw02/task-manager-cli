import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final String FILE_NAME = "task_manager_cli/src/resources/tasks.txt";
    public static final ArrayList<String> tasks = new ArrayList<>();
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        carregarTarefas();
        while(true){
            exibirMenu();
            String escolha = scanner.nextLine();
            switch (escolha){
                case "1" -> adicionarTarefa();
                case "2" -> listarTarefas();
                case "3" -> deletarTarefa();
                case "4" -> {
                    salvarTarefas();
                    System.out.println("Bye Bye ^(°.°)^");
                    return;
                }
                default -> System.out.println("Essa opção não existe");
            }
        }
    }

    public static void carregarTarefas(){
        File file = new File(FILE_NAME);
        if(!file.exists()) return;

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String linha;
            while((linha = reader.readLine()) != null){
                tasks.add(linha);
            }

        }catch(IOException e){
            System.out.println("Erro ao carregar as tarefas: "+ e.getMessage());
        }
    }

    public static void salvarTarefas(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            for(String task: tasks){
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas: "+ e.getMessage());
        }
    }

    public static void exibirMenu(){
        System.out.println("---Lista de Tarefas---");
        System.out.println("Digite o numero para a opção desejada:");
        System.out.println("1-Adicionar tarefa");
        System.out.println("2-Listar tarefas");
        System.out.println("3-Remover tarefa");
        System.out.println("4-Sair");
    }

    public static void adicionarTarefa(){
        System.out.println("Digite a nova tarefa:");
        String tarefa = scanner.nextLine();
        tasks.add(tarefa);
        System.out.println("Tarefa adicionada");
    }

    public static void listarTarefas(){
        if(!tasks.isEmpty()){
            System.out.println("---Lista de Tarefas---");
            for(int i=1; i <= tasks.size(); i++){
                System.out.println(i + "- " + tasks.get(i-1));
            }
        }else{
            System.out.println("Não existe nenhuma tarefa");
        }
    }

    public static void deletarTarefa(){
        System.out.println("Escolha a tarefa a ser deletada:");
        listarTarefas();
        int escolha = scanner.nextInt();
        if(0 < escolha && escolha <= tasks.size()){
            tasks.remove(escolha-1);
        }else{
            System.out.println("Essa tarefa não existe");
        }
        scanner.nextLine();
    }

}
