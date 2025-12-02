import java.util.Scanner;
import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("===BEM-VINDO AO SISTEMA DE CINEMA===");

        while (opcao != 10) {
            System.out.println("--------------------------------");
            System.out.println("        MENU PRINCIPAL");
            System.out.println("--------------------------------");
            System.out.println("1. Cadastrar Filme");
            System.out.println("2. Listar Filmes");
            System.out.println("3. Cadastrar Sessão (Vincular a Filme)");
            System.out.println("4. Listar Sessões");
            System.out.println("10. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                // 1. CADASTRAR FILME (Igual a Cadastrar Participante)
                if (opcao == 1) {
                    System.out.println("--- Cadastro de Filme ---");
                    System.out.print("ID: "); int idF = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Título: "); String titulo = scanner.nextLine();
                    System.out.print("Gênero: "); String genero = scanner.nextLine();

                    Filme f = new Filme(idF, titulo, genero);
                    if (f.registrarFilme()) System.out.println(">> Sucesso!");

                // 2. LISTAR FILMES (Igual a Listar Participantes)
                } else if (opcao == 2) {
                    System.out.println("--- Lista de Filmes ---");
                    Filme fTemp = new Filme(0, "", "");
                    if(new File("filmes.txt").exists()) {
                        for (Filme film : fTemp.listarFilmes()) {
                            System.out.println("ID: " + film.getIdFilme() + " | Título: " + film.getTitulo());
                        }
                    } else System.out.println("Nenhum filme cadastrado.");

                // 3. CADASTRAR SESSÃO (Igual a Cadastrar Item)
                } else if (opcao == 3) {
                    System.out.println("--- Cadastro de Sessão ---");
                    System.out.print("ID da Sessão: "); int idS = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Sala: "); String sala = scanner.nextLine();
                    System.out.print("ID do Filme: "); int idFilmeVinculo = scanner.nextInt();

                    // Busca o Filme pai antes de vincular
                    Filme filmeBusca = new Filme(idFilmeVinculo, "", "");
                    if(new File("filmes.txt").exists()) filmeBusca = filmeBusca.consultarFilme();
                    else filmeBusca = null;

                    if (filmeBusca != null) {
                        Sessao novaSessao = new Sessao(idS, sala, filmeBusca);
                        if (novaSessao.registrarSessao()) System.out.println(">> Sucesso!");
                    } else System.out.println(">> Erro: Filme não encontrado!");

                // 4. LISTAR SESSÕES (Igual a Listar Itens)
                } else if (opcao == 4) {
                    System.out.println("--- Lista de Sessões ---");
                    Sessao sTemp = new Sessao(0, "", null);
                    if(new File("sessoes.txt").exists()) {
                        for (Sessao sess : sTemp.listarSessoes()) {
                            // Imprime manual para seguir o estilo simples do seu App
                            String nomeFilme = (sess.getFilme() != null) ? sess.getFilme().getTitulo() : "Desconhecido";
                            System.out.println("ID: " + sess.getIdSessao() + " | Sala: " + sess.getSala() + " | Filme: " + nomeFilme);
                        }
                    } else System.out.println("Nenhuma sessão cadastrada.");

                } else if (opcao == 10) {
                    System.out.println("Saindo...");
                } else {
                    System.out.println("Opção inválida!");
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}
