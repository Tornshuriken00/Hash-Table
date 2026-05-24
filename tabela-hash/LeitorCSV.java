import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorCSV {

    public static String[] lerNomes(String caminho) throws IOException {
        // Primeira passagem: conta linhas
        int count = 0;
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha = br.readLine(); // pula cabecalho
        while ((linha = br.readLine()) != null) {
            if (!linha.trim().isEmpty()) count++;
        }
        br.close();

        // Segunda passagem: le os nomes
        String[] nomes = new String[count];
        br = new BufferedReader(new FileReader(caminho));
        br.readLine(); // pula cabecalho
        int i = 0;
        while ((linha = br.readLine()) != null) {
            linha = linha.trim();
            if (!linha.isEmpty()) {
                // Remove aspas se houver
                if (linha.startsWith("\"")) linha = linha.substring(1);
                if (linha.endsWith("\"")) linha = linha.substring(0, linha.length() - 1);
                nomes[i++] = linha;
            }
        }
        br.close();
        return nomes;
    }
}
