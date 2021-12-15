package logic;

import java.util.ArrayList;


public class Algorithm {
    static int cutOutnumber;
    static int algorithmNumber;

    // ========================================================= ALGORITHME 1 ==============================================================================================================

    public static ArrayList<Cut> algorithm1(ArrayList<Generable> clientsGenerable, ArrayList<Generable> suppliersGenerable) {

        cutOutnumber = 1;
        algorithmNumber = 1;
        ArrayList<Cut> cuts = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();

        for (Generable clientGenerable : clientsGenerable) {

            Client client = (Client) clientGenerable;
            if (client.isValid())
                clients.add(client);
        }
        for (Generable supplierGenerable : suppliersGenerable) {

            Supplier supplier = (Supplier) supplierGenerable;
            if (supplier.isValid())
                suppliers.add(supplier);
        }

        for (Client client : clients) {

            for (int i = 0; i < client.getCurrentLength(); i++) {

                Generable boardGenerable = client.getWood(i);
                Board board = (Board) boardGenerable;
                System.out.printf("==========Order n° %d to be treated from client %d==============\n", board.getId(), client.getId());
                Date boardDate = (Date) board.getDate();
                Dimensions boardDimensions = (Dimensions) board.getDimensions();

                int boardsNumber = board.getNumber();

                for (int j=0; j<boardsNumber; j++) {
                    for (Supplier supplier : suppliers) {
                        for (int l = 0; l < supplier.getCurrentLength(); l++) {

                            Generable panelGenerable = supplier.getWood(l);
                            Panel panel = (Panel) panelGenerable;
                            int panelsNumber = panel.getNumber();
                            Date panelDate = (Date) panel.getDate();
                            Dimensions panelDimensions = (Dimensions) panel.getDimensions();
                            if (panelsNumber > 0 && boardDate.toCompare(panelDate) && boardDimensions.toCompare(panelDimensions)) {

                                System.out.printf("==========Panel n° %d.%d to be treated from supplier %d==============\n", panel.getId(), panel.getInitialNumber() - panelsNumber, supplier.getId());

                                panelCutting(cuts, boardDimensions, board, panel, j, panel.getInitialNumber() - panelsNumber, board.getIdOwner(), supplier, panelDimensions);
                                panel.setNumber(panelsNumber - 1);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return cuts;
    }

    // ========================================================= ALGORITHME 2 ==============================================================================================================

    public static ArrayList<Cut> algorithm2(ArrayList<Generable> clientsGenerable, ArrayList<Generable> suppliersGenerable) {

        cutOutnumber = 1;
        algorithmNumber = 2;
        ArrayList<Cut> cuts = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        for (Generable clientGenerable : clientsGenerable) {

            Client client = (Client) clientGenerable;
            if (client.isValid())
                clients.add(client);
        }
        for (Generable supplierGenerable : suppliersGenerable) {

            Supplier supplier = (Supplier) supplierGenerable;
            if (supplier.isValid())
                suppliers.add(supplier);
        }
        ArrayList<Board> boardsList = listOfBoards(clients);
        ArrayList<Board> sortedBoardsList = boardsSorting(boardsList);
        for (Board board : sortedBoardsList) {

            //Board board = (Board) board;
            System.out.printf("\n\n\n===============================Order n° %d to be treated from client %d=======================================\n", board.getId(), board.getIdOwner());

            int boardsNumber = board.getNumber();
            Date boardDate = (Date) board.getDate();
            Dimensions boardDimensions = (Dimensions) board.getDimensions();

            for (int j = 0; j < boardsNumber; j++) {

                for (int s = 0; s < suppliers.size(); s++) {
                    Supplier supplier = suppliers.get(s);
                    for (int l = 0; l < supplier.getCurrentLength(); l++) {

                        Generable panelGenerable = supplier.getWood(l);
                        Panel panel = (Panel) panelGenerable;

                        int panelsNumber = panel.getNumber();
                        Date panelDate = (Date) panel.getDate();

                        if (boardDate.toCompare(panelDate)) {
                            for (int k = 0; k < panelsNumber; k++) {


                                Dimensions panelDimensions = (Dimensions) panel.getDimensions(k);

                                if (boardDimensions.toCompare(panelDimensions)) {
                                    System.out.println("_______________________________________________________");

                                    panelCutting(cuts, boardDimensions, (Board) board, panel, j, k, board.getIdOwner(), supplier, panelDimensions);

                                    panelDimensions.setDimensions(panelDimensions.getLength() - boardDimensions.getLength(), panelDimensions.getWidth());
                                    k = panelsNumber;
                                    l = supplier.getCurrentLength();
                                    s = suppliers.size();
                                }
                            }
                        }
                    }
                }
            }
        }


        return cuts;
    }

    // =======================================================FIN ALGORITHME 2 ==============================================================================================================

    // ========================================================= ALGORITHME 3 ==============================================================================================================

    public static ArrayList<Cut> algorithme3(ArrayList<Generable> clientsGenerable, ArrayList<Generable> suppliersGenerable) {

        cutOutnumber = 1;
        algorithmNumber = 3;

        ArrayList<Cut> cuts = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();


        for (Generable clientGenerable : clientsGenerable) {

            Client client = (Client) clientGenerable;
            if (client.isValid())
                clients.add(client);
        }
        for (Generable supplierGenerable : suppliersGenerable) {

            Supplier supplier = (Supplier) supplierGenerable;
            if (supplier.isValid())
                suppliers.add(supplier);
        }
        ArrayList<Board> boardsList = listOfBoards(clients);
        ArrayList<Board> sortedBoardsList = sortedBoardsListByWidth(boardsList);

        System.out.println("taille = " + sortedBoardsList.size()) ;

        int longueurInt;
        for (int i=0; i<sortedBoardsList.size(); i++) {
            Wood wood = sortedBoardsList.get(i); // make this return board
            Board board = (Board) wood;
            System.out.printf("\n\n\n===============================Order n° %d to be treated from client %d=======================================\n", board.getId(), board.getIdOwner());

            int boardsNumber = board.getNumber();
            Date boardDate = (Date) board.getDate();
            Dimensions boardDimensions = (Dimensions) board.getDimensions();

            for (int j = 0; j < boardsNumber; j++) {

                for (int s = 0; s < suppliers.size(); s++) {
                    Supplier supplier = suppliers.get(s);
                    for (int l = 0; l < supplier.getCurrentLength(); l++) {

                        Generable panelGenerable = supplier.getWood(l);
                        Panel panel = (Panel) panelGenerable;

                        int PanelsNumber = panel.getNumber();
                        Date panelDate = (Date) panel.getDate();

                        if (boardDate.toCompare(panelDate)) {
                            for (int k = 0; k < PanelsNumber; k++) {


                                Dimensions panelDimensions = (Dimensions) panel.getDimensions(k);

                                if (boardDimensions.toCompare(panelDimensions)) {
                                    System.out.println("_______________________________________________________");

                                    panelCutting(cuts, boardDimensions, board, panel, board.getInitialNumber() - board.getNumber() , k, wood.getIdOwner(), supplier, panelDimensions);

                                    panelDimensions.setDimensions(panelDimensions.getLength() , panelDimensions.getWidth() - boardDimensions.getWidth());
                                    longueurInt = boardDimensions.getLength();
                                    board.setNumber(board.getNumber()-1);
                                    System.out.println("number of boards remaining == " + board.getNumber());
                                    if (board.getNumber() == 0) {
                                        sortedBoardsList.remove(wood);
                                    }

                                    for (int m = 0; m < sortedBoardsList.size(); m++) {

                                        Board board1 = sortedBoardsList.get(m);
                                        int boards1Number = board1.getNumber();

                                        for (int t=0; t<boards1Number; t++) {

                                            Date board1Date = (Date) board1.getDate();
                                            Dimensions board1Dimensions = (Dimensions) board1.getDimensions();
                                            if (board1Date.toCompare(panelDate) && board1Dimensions.toCompare(panelDimensions) && board1Dimensions.getLength() <= longueurInt) {
                                                panelCutting(cuts, board1Dimensions, board1, panel, board1.getInitialNumber() - board1.getNumber() , k, board1.getIdOwner(), supplier, panelDimensions);
                                                panelDimensions.setDimensions(panelDimensions.getLength(), panelDimensions.getWidth() - board1Dimensions.getWidth());
                                                board1.setNumber(board1.getNumber() - 1);

                                                System.out.println("number of boards remaining == " + board1.getNumber());
                                                if (board1.getNumber() == 0) {
                                                    sortedBoardsList.remove(board1);
                                                    m--;
                                                }
                                            }
                                        }
                                    }
                                    panelDimensions.setDimensions(panelDimensions.getLength()-longueurInt, panelDimensions.getInitialWidth());

                                    k = PanelsNumber;
                                    l = supplier.getCurrentLength();
                                    s = suppliers.size();
                                    j = boardsNumber;
                                    i = -1;
                                }
                            }
                        }
                    }
                }
            }
        }


        return cuts;
    }

    // =======================================================FIN ALGORITHME 3 ==============================================================================================================

    static ArrayList<Board> listOfBoards(ArrayList<Client> clients){
        ArrayList<Board> boards = new ArrayList<>();

        for (Client client : clients) {
            for (int i = 0; i < client.getCurrentLength(); i++) {
                boards.add((Board)client.getWood(i));
            }
        }
        return boards;
    }

    static Board maximumLengthBoard(ArrayList<Board> boards){
        Board max = boards.get(0);
        for(int i=1;i<boards.size();i++){
            Dimensions dimensions = (Dimensions)boards.get(i).getDimensions();
            Dimensions dimensionsMax = (Dimensions)max.getDimensions();
            if(dimensions.getLength()>dimensionsMax.getLength())
                max=boards.get(i);
        }
        return max;
    }

    static ArrayList<Board> boardsSorting(ArrayList<Board> boards){
        int size = boards.size();
        ArrayList<Board> sortedBoards = new ArrayList<>();
        while(sortedBoards.size() != size){
            Board max = maximumLengthBoard(boards);
            sortedBoards.add(max);
            boards.remove(max);

        }
        return sortedBoards;
    }


    public static Board maximumWidthBoard(ArrayList<Board> boardsList){
        Board max = boardsList.get(0);
        for(int i=1;i<boardsList.size();i++){
            Dimensions dimensions = (Dimensions)boardsList.get(i).getDimensions();
            Dimensions dimensionsMax = (Dimensions)max.getDimensions();
            if(dimensions.getWidth()>dimensionsMax.getWidth())
                max=boardsList.get(i);
        }
        return max;
    }

    public static ArrayList<Board> sortedBoardsListByWidth(ArrayList<Board> boardsList){
        int size = boardsList.size();
        ArrayList<Board> sortedBoardsList = new ArrayList<>();
        while(sortedBoardsList.size() != size){
            Board max = maximumWidthBoard(boardsList);
            sortedBoardsList.add(max);
            boardsList.remove(max);

        }
        return sortedBoardsList;
    }

    public static void panelCutting(ArrayList<Cut>cuts, Dimensions boardDimensions, Board board, Panel panel, int k, int l, int idClient, Supplier supplier, Dimensions panelDimensions) {

        String x = String.format("%d.00", panelDimensions.getInitialWidth() - panelDimensions.getWidth() + boardDimensions.getWidth());
        String y = String.format("%d.00", panelDimensions.getInitialLength() - panelDimensions.getLength() +  boardDimensions.getLength());
        String x1 =  String.format("%d", panelDimensions.getInitialWidth() - panelDimensions.getWidth());
        String y1 =  String.format("%d", panelDimensions.getInitialLength() - panelDimensions.getLength());
        String boardId = String.format("%d.%d", board.getId(), k);
        String panelId = String.format("%d.%d", panel.getId(), l);
        Cut cut = new Cut(x1, y1,panelDimensions.getInitialLengthString(), panelDimensions.getInitialWidthString(), x, y, idClient, boardId, supplier.getId(), panelId);
        cuts.add(cut);

        System.out.println("Cut out n°" + cutOutnumber + ": board with id " + cut.getIdBoard() + " from client " + cut.getIdClient() + " was token from panel " + cut.getIdPanel() + " ---> x= " + cut.getX() + " ,y=" + cut.getY() + "\n");
        cutOutnumber++;
    }

}
