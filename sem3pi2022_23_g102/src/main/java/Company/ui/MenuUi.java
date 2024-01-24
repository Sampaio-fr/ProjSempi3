package Company.ui;

import Company.Controller.MainController;
import Company.Model.Cabaz;
import Company.Model.Pessoa;
import Company.exception.ERRORCSV;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The type Menu ui.
 */
@SuppressWarnings("ALL")
public class MenuUi {

    /**
     * Instantiates a new Menu ui.
     */
    public MenuUi() {

    }

    /**
     * Menu ui.
     *
     * @throws Exception the exception
     */
    public void menuUi() throws Exception {
        MainController mainController = new MainController();
        Scanner scanner = new Scanner(System.in);
        File fileName;
        boolean flag = true;
        String aux = null, hora = null;
        LocalDateTime dateTime = null;
        int day = 0, n = 0;

        String selection = "555";
        while (!selection.equals("exit")) {
            System.out.println();
            System.out.println("Select one of the Options :");
            System.out.println();
            System.out.println("______________________________________________________________Sprint1______________________________________________________________");
            System.out.println();
            System.out.println(" 1 - US301 Ler Cliente Produtor csv ");
            System.out.println(" 2 - US301 Ler Distancias");
            System.out.println(" 3 - US301 Criar Grafo");
            System.out.println(" 4 - US302 Verificar se o Grafo é Conexo ");
            System.out.println(" 5 - US302 Encontrar o numero de conexoes em cada Cliente");
            System.out.println(" 6 - US304 Para cada cliente (particular ou empresa) determinar o hub mais próximo");
            System.out.println(" 7 - US305 Determinar a rede que conecte todos os clientes e produtores agrícolas com uma distância total mínima.");
            System.out.println(" 8 - US303 Definir os hubs da rede de distribuição, ou seja, encontrar as N empresas mais próximas de todos os pontos da rede");
            System.out.println(" 9 - US306 Importar ficheiro com informaçoes de rega.");
            System.out.println("10 - US306 Plano de rega para 30 dias e verificação de rega.");
            System.out.println();
            System.out.println("______________________________________________________________Sprint2______________________________________________________________");
            System.out.println();
            System.out.println("11 - US307 Importar a lista de cabazes.");
            System.out.println("12 - US308/309/310 Criar Armazem de Stock,lista de Cabazes dos Clientes e Limpeza da Lista de Expedição.");
            System.out.println("13 - US308 Gerar uma lista de expedição para um determinado dia que forneça os cabazes sem qualquer restrição quanto aos produtores.");
            System.out.println("14 - US309 Gerar uma lista de expedição para um determinado dia que forneça apenas com os N produtores agrícolas mais próximos do hub de entrega do cliente.");
            System.out.println("15 - US310 Para uma lista de expedição diária gerar o percurso de entrega que minimiza a distância total percorrida.");
            System.out.println("16 - US311 Para uma lista de expedição calcular estatísticas.");
            System.out.println("exit");
            System.out.println();
            selection = scanner.nextLine();
            switch (selection) {
                case "1": {
                    flag = true;
                    while (flag) {
                        try {
                            System.out.println("Localização do ficheiro");
                            fileName = new File("src/main/Resources/clientes-produtores_big.csv");
                            //fileName = new File(scanner.nextLine());
                            mainController.lerClienteProdutor(fileName.getPath());
                            flag = false;
                        } catch (Exception e) {
                            System.out.println(e);
                            System.out.println("Dados Incorretos.");
                            flag = true;
                        }
                    }
                    System.out.println("Upload clientes-produtores feito.");
                    System.out.println();
                    break;
                }

                case "2": {
                    flag = true;
                    while (flag) {
                        try {
                            System.out.println("Localização do ficheiro");
                            fileName = new File("src/main/Resources/distancias_big.csv");
                            //fileName = new File(scanner.nextLine());
                            mainController.lerDistancias(fileName.getPath());
                            flag = false;
                        } catch (Exception e) {
                            System.out.println(e);
                            System.out.println("Dados Incorretos.");
                            flag = true;
                        }
                    }
                    System.out.println("Upload distancias feito.");
                    System.out.println();
                    break;
                }

                case "3": {
                    long startTime = System.currentTimeMillis();
                    mainController.setMapGraph(mainController.createGraph());
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    System.out.println("Grafo Criado.");
                    System.out.println("Method running time: " + duration + " ms");
                    System.out.println();
                    break;
                }
                case "4": {
                    mainController.verificarGraphConexo();
                    System.out.println();
                    break;
                }
                case "5": {
                    long startTime = System.currentTimeMillis();
                    System.out.println("Numero de ligações: " + mainController.numeroligacoesDiam());
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    System.out.println("Method running time: " + duration + " ms");
                    System.out.println();
                    break;
                }
                case "6": {
                    /*System.out.println("Número de Hubs proximos");
                    flag = false;
                    while (!flag){
                        try {
                            n = Integer.parseInt(scanner.nextLine());
                            if (n < 0){
                                flag = false;
                                System.out.println("Valor inválido.");
                            }
                            else {
                                flag = true;
                            }
                        }catch (Exception e){
                            flag = false;
                        }
                    }*/
                    long startTime = System.currentTimeMillis();
                    Map<Pessoa, Map<Pessoa, Integer>> nearestHubsMap = mainController.findNearestsHubEachClients(1);
                    for (Pessoa client : nearestHubsMap.keySet()) {
                        System.out.println("Nearest hubs for client " + client.getIdPessoa() + ":");
                        Map<Pessoa, Integer> hubs = nearestHubsMap.get(client);
                        for (Pessoa hub : hubs.keySet()) {
                            int distance = hubs.get(hub);
                            System.out.println("  " + hub.getIdPessoa() + " (distance: " + distance + ")");
                        }
                    }
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    System.out.println("Method running time: " + duration + " ms");
                    System.out.println();
                    break;
                }
                case "7": {
                    long startTime = System.currentTimeMillis();
                    Map<List<Pessoa>, Integer> map = mainController.findNetworkWithMinimumDistance();
                    Map.Entry<List<Pessoa>, Integer> entry = map.entrySet().iterator().next();
                    System.out.println("Caminho:\n" + entry.getKey() + "\nDistância:\n" + entry.getValue());
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    System.out.println("Method running time: " + duration + " ms");
                    System.out.println();
                    break;
                }
                case "8": {
                    System.out.println("Numero de hubs:");
                    flag = false;
                    while (!flag) {
                        try {
                            n = Integer.parseInt(scanner.nextLine());
                            if (n < 0) {
                                flag = false;
                                System.out.println("Valor inválido.");
                            } else {
                                flag = true;
                            }
                        } catch (Exception e) {
                            flag = false;
                        }
                    }
                    long startTime = System.currentTimeMillis();
                    List<Pessoa> list = mainController.findNearestHubToAllClients(n);
                    for (Pessoa pessoa : list) {
                        System.out.println("Hub by Order: " + pessoa.getIdPessoa() + " Local: " + pessoa.getLocalidade());
                    }
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    System.out.println("Method running time: " + duration + " ms");
                    System.out.println();
                    break;
                }

                case "9": {
                    System.out.println("Localização do ficheiro");
                    //fileName = new File("src/main/Resources/rega_exemplo.csv");
                    fileName = new File(scanner.nextLine());

                    long startTime = System.currentTimeMillis();

                    flag = mainController.lerPlanosDeRega(fileName.getPath());
                    if (flag) {
                        System.out.println("Lido com sucesso.");
                    } else {
                        System.out.println("Não foi lido com sucesso.");
                    }
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    System.out.println("Method running time: " + duration + " ms");
                    System.out.println();
                    break;
                }

                case "10": {
                    System.out.println("Dê os dados para verificar se existe a rega e se esta a regar.");
                    long startTime = System.currentTimeMillis();
                    flag = false;
                    while (!flag) {
                        //String helper = "a";
                        String str = "2023-01-03 17:00";
                        System.out.println("Setor");
                        aux = scanner.nextLine();
                        try {
                            System.out.println("Data a verificar:");
                            System.out.println("Um exemplo " + str);
                            hora = scanner.nextLine();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            dateTime = LocalDateTime.parse(hora, formatter);
                            flag = true;
                        } catch (Exception e) {
                            System.out.println("Dados não existentes.");
                            flag = false;
                        }
                    }
                    flag = mainController.verificarRega(mainController.getSetorList(), aux, dateTime);
                    if (flag) {
                        System.out.println("Esta a ser regada");
                    } else {
                        System.out.println("Não esta a ser regado");
                    }
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    System.out.println("Method running time: " + duration + " ms");
                    System.out.println();
                    break;
                }

                case "11": {
                    flag = true;
                    long startTime = System.currentTimeMillis();
                    while (flag) {
                        try {
                            System.out.println("Localização do ficheiro");
                            fileName = new File("src/main/Resources/cabazes_big.csv");
                            //fileName = new File(scanner.nextLine());
                            mainController.lerCabaz(fileName.getPath());
                            flag = false;
                        } catch (ERRORCSV e) {
                            flag = true;
                            System.out.println(e);
                            System.out.println("Dados Incorretos.");
                        }
                    }
                    System.out.println("Upload cabazes feito.");
                    System.out.println();
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    System.out.println("Method running time: " + duration + " ms");
                    System.out.println();
                    break;
                }

                case "12": {
                    long startTime = System.currentTimeMillis();
                    mainController.criarStockAndCabaz();
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    System.out.println("Method running time: " + duration + " ms");
                    System.out.println();
                    break;
                }

                case "13": {
                    long startTime = System.currentTimeMillis();
                    flag = false;
                    while (!flag) {
                        try {
                            System.out.println("Dia em que pretende a lista de expedicao.");
                            flag = true;
                            day = Integer.parseInt(scanner.nextLine());
                            if (day < 0) {
                                flag = false;
                                System.out.println("Dados inválidos.");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            flag = false;
                        }
                    }

                    List<Cabaz> list = mainController.expedatingForDayNoRestrictions(day);
                    System.out.println(list);
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    System.out.println("Method running time: " + duration + " ms");
                    System.out.println();
                    break;
                }

                case "14": {
                    long startTime = System.currentTimeMillis();
                    flag = false;
                    while (!flag) {
                        try {
                            flag = true;
                            System.out.println("Dia:");
                            day = Integer.parseInt(scanner.nextLine());
                            System.out.println("Numero de Produtores Proximos:");
                            n = Integer.parseInt(scanner.nextLine());
                            if (n < 0 || day < 0) {
                                flag = false;
                                System.out.println("Dados inválidos.");
                            }

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            flag = false;
                        }
                    }

                    List<Cabaz> list = mainController.expedatingForDayRestrictions(day, n);
                    System.out.println(list);
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    System.out.println("Method running time: " + duration + " ms");
                    System.out.println();
                    break;
                }

                case "15": {
                    long startTime = System.currentTimeMillis();
                    flag = false;
                    while (!flag) {
                        try {
                            flag = true;
                            System.out.println("Dia:");
                            day = Integer.parseInt(scanner.nextLine());
                            if (day < 0) {
                                flag = false;
                                System.out.println("Dado inválido.");
                            }

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            flag = false;
                        }
                    }
                    Map<List<Pessoa>, Integer> map = mainController.pathWay(day);
                    Map.Entry<List<Pessoa>, Integer> entry = map.entrySet().iterator().next();
                    System.out.println("Caminho:\n" + entry.getKey() + "\nDistância:\n" + entry.getValue());
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    System.out.println("Method running time: " + duration + " ms");
                    System.out.println();
                    break;
                }

                case "16": {
                    long startTime = System.currentTimeMillis();
                    mainController.printMainStatistics();
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    System.out.println("Method running time: " + duration + " ms");
                    System.out.println();
                    break;
                }

                case "exit": {
                    System.out.println("Desligando o Programa...");
                    break;
                }
                default:
                    selection = "2452";
                    break;
            }

        }

    }
}
