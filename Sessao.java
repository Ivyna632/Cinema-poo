import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Sessao {
    private int idSessao;
    private String sala;
    private Filme filme; // Vínculo com Filme

    public Sessao(int idSessao, String sala, Filme filme) {
        this.idSessao = idSessao;
        this.sala = sala;
        this.filme = filme;
    }

    // Getters e Setters
    public int getIdSessao() { return idSessao; }
    public String getSala() { return sala; }
    public Filme getFilme() { return filme; }
    
    public Integer getidFilme() { 
        return (filme != null) ? filme.getIdFilme() : null; 
    }

    // Registrar (Igual registrarItem)
    public Boolean registrarSessao() throws Exception {
        try (FileWriter fw = new FileWriter("sessoes.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            // Pega o ID com segurança igual ao seu código
            int idFilmeSalvar = (this.filme != null) ? this.filme.getIdFilme() : 0;

            String linha = this.idSessao + "," + this.sala + "," + idFilmeSalvar;

            bw.write(linha);
            bw.newLine();
            return true;
        }
    }

    // Listar (Igual listarItens - reconstrói o objeto Filme)
    public ArrayList<Sessao> listarSessoes() throws Exception {
        ArrayList<Sessao> lista = new ArrayList<>();
        
        try (FileReader fr = new FileReader("sessoes.txt");
             BufferedReader br = new BufferedReader(fr)) {
            
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length < 3) continue;

                int id = Integer.parseInt(dados[0]);
                String sala = dados[1];
                int idFilme = Integer.parseInt(dados[2]);

                // Instancia Filme dummy para buscar os dados reais (igual ItemLeilao faz com Leilao)
                Filme fBusca = new Filme(idFilme, "", "");
                fBusca = fBusca.consultarFilme(); 

                Sessao s = new Sessao(id, sala, fBusca);
                lista.add(s);
            }
        }
        return lista;
    }
}
