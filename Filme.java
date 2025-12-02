import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Filme {
    private int idFilme;
    private String titulo;
    private String genero;

    // Construtor
    public Filme(int idFilme, String titulo, String genero) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.genero = genero;
    }

    // Getters e Setters
    public int getIdFilme() { return idFilme; }
    public String getTitulo() { return titulo; }

    // Salvar (Igual registrarParticipante)
    public Boolean registrarFilme() throws Exception {
        try (FileWriter fw = new FileWriter("filmes.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            String linha = this.idFilme + "," + this.titulo + "," + this.genero;
            
            bw.write(linha);
            bw.newLine();
            return true;
        }
    }

    // Listar (Igual listarParticipantes)
    public ArrayList<Filme> listarFilmes() throws Exception {
        ArrayList<Filme> lista = new ArrayList<>();
        try (FileReader fr = new FileReader("filmes.txt");
             BufferedReader br = new BufferedReader(fr)) {
            
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 3) continue;

                Filme f = new Filme(
                    Integer.parseInt(dados[0]),
                    dados[1],
                    dados[2]
                );
                lista.add(f);
            }
        }
        return lista;
    }

    // Consultar (Igual consultarLeilao, usado para buscar pelo ID)
    public Filme consultarFilme() throws Exception {
        try (FileReader fr = new FileReader("filmes.txt");
             BufferedReader br = new BufferedReader(fr)) {
            
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 3) continue;

                int idLido = Integer.parseInt(dados[0]);
                if (idLido == this.idFilme) {
                    return new Filme(idLido, dados[1], dados[2]);
                }
            }
        }
        return null;
    }
}