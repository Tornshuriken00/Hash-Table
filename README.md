# Hash-Table

📊 Tabela Hash — Comparação de Funções Hash em Java
Projeto acadêmico que implementa e compara duas tabelas hash com estratégias de hashing distintas, utilizando sondagem linear para resolução de colisões. Os dados de entrada são 20.000 nomes reais e distintos lidos de um arquivo CSV.

🗂️ Estrutura do Projeto
tabela-hash/
├── Main.java                          # Ponto de entrada — orquestra leitura, inserção, busca e relatório
├── TabelaHash.java                    # Classe abstrata base com lógica de inserção e busca
├── TabelaHash1.java                   # Implementação 1: hash por soma ASCII
├── TabelaHash2.java                   # Implementação 2: hash polinomial (djb2 adaptado)
├── LeitorCSV.java                     # Utilitário para leitura e parse do arquivo CSV
└── nomes_20000_reais_distintos.csv    # Base de dados com 20.000 nomes únicos

⚙️ Como Executar
Pré-requisito: Java 8 ou superior instalado.
bash# Compilar todos os arquivos
javac *.java

# Executar
java Main
O programa irá ler o CSV, inserir os nomes nas duas tabelas e imprimir o relatório comparativo no terminal.

🔍 O que o Projeto Faz

Lê 20.000 nomes distintos do arquivo CSV via LeitorCSV
Insere todos os nomes em duas tabelas hash de capacidade 16
Mede o tempo de inserção e de busca (primeiros 100 nomes) com System.nanoTime()
Exibe um relatório com colisões, distribuição por posição, clusters e ocupação


🧮 Funções Hash Implementadas
Hash 1 — Soma ASCII (TabelaHash1)
Soma os valores numéricos (Unicode) de cada caractere da chave e aplica módulo pela capacidade.
javaint soma = 0;
for (int i = 0; i < chave.length(); i++) {
    soma += chave.charAt(i);
}
return soma % capacidade;

⚠️ Limitação: anagramas (ex: "Ana" e "aNa") produzem o mesmo hash, aumentando colisões.


Hash 2 — Polinomial djb2 (TabelaHash2)
Algoritmo inspirado no djb2, com multiplicador 31 e semente inicial 7. Considera a posição de cada caractere, evitando colisões por anagramas.
javalong hash = 7;
for (int i = 0; i < chave.length(); i++) {
    hash = hash * 31 + chave.charAt(i);
}
int resultado = (int) (hash % capacidade);
if (resultado < 0) resultado += capacidade;
return resultado;

✅ Mesma estratégia usada pelo String.hashCode() nativo do Java.


🏗️ Arquitetura — Classe Abstrata Base
TabelaHash.java define a estrutura comum:
AtributoDescriçãoString[] tabelaArray que armazena as chavesint capacidadeTamanho da tabelaint colisoesTotal de colisões ocorridasint totalElementosElementos inseridos com sucesso
A resolução de colisões usa sondagem linear: ao encontrar uma posição ocupada, tenta a próxima (pos + 1) % capacidade, de forma circular.

📈 Relatório Gerado
O relatório impresso ao final inclui, para cada tabela:

Capacidade, elementos inseridos e total de colisões
Tempo de inserção e busca (em ns e ms)
Conteúdo de cada posição do array
Análise de clusters (sequências de posições ocupadas consecutivas)
Percentual de ocupação da tabela


🧪 Constantes Configuráveis (em Main.java)
javastatic final int CAPACIDADE = 16;       // Tamanho das tabelas
static final String ARQUIVO = "nomes_20000_reais_distintos.csv";
static final int TOTAL_BUSCAS = 100;    // Número de buscas para medição

A capacidade 16 é propositalmente pequena para evidenciar colisões e facilitar a análise comparativa.


🆚 Comparação entre as Estratégias
CritérioHash 1 (Soma ASCII)Hash 2 (Polinomial)Colisões por anagramas✗ Alta✓ BaixaDistribuiçãoMenos uniformeMais uniformeVelocidadeMuito rápidaRápidaUso práticoDidáticoAmplamente usado

📚 Conceitos Aplicados

Tabela Hash com endereçamento aberto
Sondagem Linear para resolução de colisões
Clustering e seu impacto no desempenho
Fator de Carga (com 20.000 elementos em tabela de 16 posições = fator ~1250)
Medição de performance com System.nanoTime()
