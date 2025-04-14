import static javax.swing.JOptionPane.*;

import lista.dupla.ListaDupla;
import lista.dupla.NoGenerico;

public class Sistema {
    private static ListaDupla<Cidade> cidades = new ListaDupla<>();

    public static void main(String[] args) throws Exception {
        boolean sair = false;

        while (!sair) {
            String inputString = showInputDialog
            ("1. Cadastrar cidades\n 2. Cadastrar ligações diretas entre cidades.\n 3. Listar todas as cidades com suas ligações diretas. \n 4. Verificar se existe uma ligação direta e seu tempo estimado da entrega \n 5. Exibir todas as ligações diretas em toda a malha que possam ser realizadas dentro desse tempo \n 6. Sair");

            int opcao = Integer.parseInt(inputString);

            if (opcao == 1) {
                String nomeCidade = showInputDialog("Escreva o nome da cidade");
                cidades.inserirFim(new Cidade(nomeCidade, new ListaDupla<>()));
                showMessageDialog(getRootFrame(), "Cidade '" + nomeCidade + "' cadastrada!");
            } else if (opcao == 2) {
                String nomeCidade = showInputDialog("Escreva o nome da cidade");
                NoGenerico<Cidade> pesquisa = cidades.pesquisar(new Cidade(nomeCidade, null));
                if (pesquisa == null) {
                    showMessageDialog(getRootFrame(), "Cidade não encontrada!");
                } else {
                    String nomeDestino = showInputDialog("Escreva o nome do destino da linha direta");
                    String distanciaString = showInputDialog(
                            "Escreva a distancia do destino a partir da cidade de origem");
                    double distancia = Double.parseDouble(distanciaString);
                    if (distancia <= 0) {
                        showMessageDialog(getRootFrame(), "ERRO: Distância negativas ou iguais 0 não são permitidas");
                    } else {
                        String fatorTrafegoString = showInputDialog(
                                "Escreva o fator de trafego do destino a partir da cidade de origem");
                        double fatorTrafego = Double.parseDouble(fatorTrafegoString);
                        if (fatorTrafego > 2.0 || fatorTrafego < 0) {
                            showMessageDialog(getRootFrame(), "ERRO: Fator tráfego deve ser entre 0 e 2");
                        } else {
                            String pedagiosString = showInputDialog(
                                    "Escreva a quantidade de pedagios entre o destino e a cidade de origem");
                            int pedagios = Integer.parseInt(pedagiosString);
                            if (pedagios < 0) {
                                showMessageDialog(getRootFrame(),
                                        "ERRO: A quantidade de pedágios deve ser maior que 0");
                            } else {
                                pesquisa.getDado().getConexoes()
                                        .inserirFim(new ConexaoDireta(nomeDestino, distancia, fatorTrafego, pedagios));
                            }
                        }

                    }
                }
            } else if (opcao == 3) {
                int quantidadeCidades = cidades.getTamanho();

                if (quantidadeCidades == 0) {
                    showConfirmDialog(getRootFrame(), "Não foram encontradas lihas diretas!");
                    return;
                }

                ListaDupla<String> listagem = new ListaDupla<>();

                for (int i = 0; i < quantidadeCidades; i++) {
                    NoGenerico<Cidade> noCidade = cidades.pesquisar(i);

                    Cidade cidade = noCidade.getDado();

                    int quantidadeConexoes = cidade.getConexoes().getTamanho();

                    for (int j = 0; j < quantidadeConexoes; j++) {
                        NoGenerico<ConexaoDireta> noConexao = cidade.getConexoes().pesquisar(j);

                        ConexaoDireta conexaoDireta = noConexao.getDado();

                        listagem.inserirFim(cidade.getNome() + " -> " + conexaoDireta.getDestino());
                    }
                }

                if (listagem.getTamanho() == 0) {
                    showConfirmDialog(getRootFrame(), "Não foram encontradas ligações diretas!");
                } else {
                    String mensagem = "";
                    for (int i = 0; i < listagem.getTamanho(); i++) {
                        mensagem = mensagem + listagem.pesquisar(i).getDado() + "\n";
                    }

                    showMessageDialog(getRootFrame(), mensagem);
                }
            } else if (opcao == 4) {
                String nomeCidadeOrigem = showInputDialog("Escreva o nome da cidade origem");
                NoGenerico<Cidade> pesquisa = cidades.pesquisar(new Cidade(nomeCidadeOrigem, null));
                if (pesquisa == null) {
                    showMessageDialog(getRootFrame(), "ERRO: Cidade origem não encontrada");
                } else {
                    String nomeCidadeDestino = showInputDialog("Escreva o nome da cidade destino");

                    NoGenerico<ConexaoDireta> pesquisaDestino = pesquisa.getDado().getConexoes()
                            .pesquisar(new ConexaoDireta(nomeCidadeDestino, 0, 0, 0));

                    if (pesquisaDestino == null) {
                        showMessageDialog(getRootFrame(),
                                "Ligação direta entre cidade origem e cidade destino não encontrada!");
                    } else {
                        showMessageDialog(getRootFrame(),
                                "Tempo estimado de entrega entre '" + pesquisa.getDado().getNome() + "' e '"
                                        + pesquisaDestino.getDado().getDestino() + "' é "
                                        + pesquisaDestino.getDado().getTempoEntrega() + " minutos");
                    }
                }
            } else if (opcao == 5) {
                String tempoMaximoString = showInputDialog(
                        "Escreva o tempo máximo");
                double tempoMaximo = Double.parseDouble(tempoMaximoString);

                int quantidadeCidades = cidades.getTamanho();

                if (quantidadeCidades == 0) {
                    showConfirmDialog(getRootFrame(), "Não foram encontradas ligações diretas!");
                    return;
                }

                ListaDupla<String> resultadoConsulta = new ListaDupla<>();

                for (int i = 0; i < quantidadeCidades; i++) {
                    NoGenerico<Cidade> noCidade = cidades.pesquisar(i);

                    Cidade cidade = noCidade.getDado();

                    int quantidadeConexoes = cidade.getConexoes().getTamanho();

                    for (int j = 0; j < quantidadeConexoes; j++) {
                        NoGenerico<ConexaoDireta> noConexao = cidade.getConexoes().pesquisar(j);

                        ConexaoDireta conexaoDireta = noConexao.getDado();

                        if (conexaoDireta.getTempoEntrega() <= tempoMaximo) {
                            resultadoConsulta.inserirFim(cidade.getNome() + " -> " + conexaoDireta.getDestino() + " ("
                                    + conexaoDireta.getTempoEntrega() + " min)");
                        }
                    }
                }

                if (resultadoConsulta.getTamanho() == 0) {
                    showConfirmDialog(getRootFrame(), "Não foram encontradas ligações diretas!");
                } else {
                    String mensagem = "";
                    for (int i = 0; i < resultadoConsulta.getTamanho(); i++) {
                        mensagem = mensagem + resultadoConsulta.pesquisar(i).getDado() + "\n";
                    }

                    showMessageDialog(getRootFrame(), mensagem);
                }
            } else if (opcao == 6) {
                sair = true;
            } else {
                showMessageDialog(getRootFrame(), "Opção não reconhecida!");
            }
        }
    }
}
