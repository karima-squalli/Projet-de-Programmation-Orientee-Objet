package logic;

import java.util.ArrayList;


class Algorithm {

    static int cutoutNumber;
    static int algorithmNumber;

    // ========================================================= ALGORITHM 1 ==============================================================================================================

    public static ArrayList<Cut> algorithm1(ArrayList<Generable> clientsGenerable, ArrayList<Generable> suppliersGenerable) {

        cutoutNumber = 1;
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

    // ========================================================= ALGORITHM 2 ==============================================================================================================

    public static ArrayList<Cut> algorithm2(ArrayList<Generable> clientsGenerable, ArrayList<Generable> suppliersGenerable) {

        cutoutNumber = 1;
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
        ArrayList<Board> sortedBoardsList = boardsSorting(boardsList,0);
        for (Board board : sortedBoardsList) {

            System.out.printf("\n===============================Order n° %d to be treated from client %d=======================================\n", board.getId(), board.getIdOwner());

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

                                    panelCutting(cuts, boardDimensions, board, panel, j, k, board.getIdOwner(), supplier, panelDimensions);

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

    // =======================================================END OF ALGORITHM 2 ==============================================================================================================

    // ========================================================= OPTIMIZED ALGORITHM ==============================================================================================================

    public static ArrayList<Cut> optimizedAlgorithm(ArrayList<Generable> clientsGenerable, ArrayList<Generable> suppliersGenerable) {

        cutoutNumber = 1;
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
        ArrayList<Board> sortedBoardsList = boardsSorting(boardsList,1);

        int lengthInt;
        for (int i=0; i<sortedBoardsList.size(); i++) {
            Board board = sortedBoardsList.get(i);

            System.out.printf("\n===============================Order n° %d to be treated from client %d=======================================\n", board.getId(), board.getIdOwner());

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

                                    panelCutting(cuts, boardDimensions, board, panel, board.getInitialNumber() - board.getNumber() , k, board.getIdOwner(), supplier, panelDimensions);

                                    panelDimensions.setDimensions(panelDimensions.getLength() , panelDimensions.getWidth() - boardDimensions.getWidth());
                                    lengthInt = boardDimensions.getLength();
                                    board.setNumber(board.getNumber()-1);

                                    if (board.getNumber() == 0) {
                                        sortedBoardsList.remove(board);
                                    }

                                    for (int m = 0; m < sortedBoardsList.size(); m++) {

                                        Board board1 = sortedBoardsList.get(m);
                                        int boards1Number = board1.getNumber();

                                        for (int t=0; t<boards1Number; t++) {

                                            Date board1Date = (Date) board1.getDate();
                                            Dimensions board1Dimensions = (Dimensions) board1.getDimensions();
                                            if (board1Date.toCompare(panelDate) && board1Dimensions.toCompare(panelDimensions) && board1Dimensions.getLength() <= lengthInt) {

                                                System.out.printf("\n===============================Order n° %d to be treated from client %d=======================================\n", board1.getId(), board1.getIdOwner());
                                                panelCutting(cuts, board1Dimensions, board1, panel, board1.getInitialNumber() - board1.getNumber() , k, board1.getIdOwner(), supplier, panelDimensions);
                                                panelDimensions.setDimensions(panelDimensions.getLength(), panelDimensions.getWidth() - board1Dimensions.getWidth());
                                                board1.setNumber(board1.getNumber() - 1);

                                                if (board1.getNumber() == 0) {
                                                    sortedBoardsList.remove(board1);
                                                    m--;
                                                }
                                            }
                                        }
                                    }
                                    panelDimensions.setDimensions(panelDimensions.getLength()-lengthInt, panelDimensions.getInitialWidth());

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

    // =======================================================END OF OPTIMIZED ALGORITHM ==============================================================================================================

    //  ================================================= COMMON FUNCTIONS TO THE ALGORITHMS ===================================================

    private static ArrayList<Board> listOfBoards(ArrayList<Client> clients){
        ArrayList<Board> boards = new ArrayList<>();

        for (Client client : clients) {
            for (int i = 0; i < client.getCurrentLength(); i++) {
                boards.add((Board)client.getWood(i));
            }
        }
        return boards;
    }

    // If test=0 the function returns the board with maximum length, if test=1 it returns the board with maximum width
    private static Board maximumBoard(ArrayList<Board> boards, int test){
        Board toReturn = boards.get(0);
        for(int i=1;i<boards.size();i++){
            Dimensions dimensions = (Dimensions)boards.get(i).getDimensions();
            Dimensions toReturnDimensions = (Dimensions)toReturn.getDimensions();
            if (test == 0) {
                if (dimensions.getLength() > toReturnDimensions.getLength())
                    toReturn = boards.get(i);
            }
            if (test==1) {
                if (dimensions.getWidth() > toReturnDimensions.getWidth())
                    toReturn = boards.get(i);
            }
        }

        return toReturn;
    }

    // This function sorts a list of boards by length(if test=0), or by width(if test=1)
    private static ArrayList<Board> boardsSorting(ArrayList<Board> boards, int test){
        int size = boards.size();
        ArrayList<Board> sortedBoards = new ArrayList<>();
        while(sortedBoards.size() != size){
            Board board = maximumBoard(boards,test);
            sortedBoards.add(board);
            boards.remove(board);
        }
        return sortedBoards;
    }
    
    private static void panelCutting(ArrayList<Cut>cuts, Dimensions boardDimensions, Board board, Panel panel, int k, int l, int idClient, Supplier supplier, Dimensions panelDimensions) {

        String x = String.format("%d.00", panelDimensions.getInitialWidth() - panelDimensions.getWidth() + boardDimensions.getWidth());
        String y = String.format("%d.00", panelDimensions.getInitialLength() - panelDimensions.getLength() +  boardDimensions.getLength());
        String x1 =  String.format("%d", panelDimensions.getInitialWidth() - panelDimensions.getWidth());
        String y1 =  String.format("%d", panelDimensions.getInitialLength() - panelDimensions.getLength());
        String boardId = String.format("%d.%d", board.getId(), k);
        String panelId = String.format("%d.%d", panel.getId(), l);
        Cut cut = new Cut(x1, y1,panelDimensions.getInitialLengthString(), panelDimensions.getInitialWidthString(), x, y, idClient, boardId, supplier.getId(), panelId);
        cuts.add(cut);

        System.out.println("Cutout n°" + cutoutNumber + ": board with id " + cut.getIdBoard() + " from client " + cut.getIdClient() + " was token from panel " + cut.getIdPanel() + " ---> x= " + cut.getX() + " ,y=" + cut.getY() + "\n");
        cutoutNumber++;
    }

}
