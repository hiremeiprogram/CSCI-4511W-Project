package Application;
import java.lang.Math;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game{
    private List<Player> players;
    private List<Node> board;
    private boolean isNotDone;

//players and board are initialized in setPlayers and setBoard functions below.
//these actions are performed in main
    Game(){
        isNotDone = true;
        temprestrain = 0;
    }
//calculates number of moves a player is allowed to use
    public int turn_calculator(Player current) {
        if(current.getTerritories().size() <= 4){
            return current.getTerritories().size();
        }
        return 2+(int)Math.sqrt(current.getTerritories().size());
    }
//sets the number of moves a player is allowed to use
    public void set_moves(){
        for(int i = 0; i < players.size(); i++){
            players.get(i).setMoves(turn_calculator(players.get(i)));
        }
    }
//gets moves from each player
    public void get_actions(){
        List<Action> temp;
        for(int i = 0; i < players.size(); i++){
            temp = new LinkedList<Action>();
            players.get(i).setActions(temp);
            players.get(i).addActions();
        }
    }
//performs moves provided by each player
    public void perform_actions(){
        List<Action> actionsList;
        for(int i = 0; i < players.size(); i++){
            actionsList = players.get(i).getActions();
            for(int j = 0; j < actionsList.size(); j++){
                if(actionsList.get(j).getType().equals("rec")){
                    if((actionsList.get(j).getAmount() <= actionsList.get(j).getPerf().getGold()) &&
                    (actionsList.get(j).getAmount() <= actionsList.get(j).getFrom().getEconomy())){
                        actionsList.get(j).getPerf().setGold(actionsList.get(j).getPerf().getGold() - actionsList.get(j).getAmount());
                        actionsList.get(j).getFrom().setEconomy(actionsList.get(j).getFrom().getEconomy() - actionsList.get(j).getAmount());
                        actionsList.get(j).getFrom().setSoldiers(actionsList.get(j).getFrom().getSoldiers()+actionsList.get(j).getAmount());
                    }
                }
                else if(actionsList.get(j).getType().equals("move")){
                    actionsList.get(j).getFrom().setSoldiers(actionsList.get(j).getFrom().getSoldiers() - actionsList.get(j).getAmount());
                    actionsList.get(j).getTo().setSoldiers(actionsList.get(j).getTo().getSoldiers() + actionsList.get(j).getAmount());
                }
                else if(actionsList.get(j).getType().equals("att")){
                    if(actionsList.get(j).getAmount() <= actionsList.get(j).getFrom().getSoldiers()) {
                        if (actionsList.get(j).getAmount() >= actionsList.get(j).getTo().getSoldiers()) {
                            actionsList.get(j).getPerf().addTerritory(actionsList.get(j).getTo());
                            actionsList.get(j).getTo().setSoldiers(actionsList.get(j).getAmount() - actionsList.get(j).getTo().getSoldiers());
                            actionsList.get(j).getFrom().setSoldiers(actionsList.get(j).getFrom().getSoldiers() - actionsList.get(j).getAmount());
                        }
                        else {
                            actionsList.get(j).getFrom().setSoldiers(0);
                            actionsList.get(j).getTo().setSoldiers(actionsList.get(j).getTo().getSoldiers() - actionsList.get(j).getAmount());
                        }
                    }
                    else{
                        if(actionsList.get(j).getFrom().getSoldiers() >= actionsList.get(j).getTo().getSoldiers()){
                            actionsList.get(j).getPerf().addTerritory(actionsList.get(j).getTo());
                            actionsList.get(j).getTo().setOwner(actionsList.get(j).getPerf().getName());
                            actionsList.get(j).getTo().setSoldiers(actionsList.get(j).getFrom().getSoldiers() - actionsList.get(j).getTo().getSoldiers());
                            actionsList.get(j).getFrom().setSoldiers(0);
                        }
                        else{
                            actionsList.get(j).getFrom().setSoldiers(0);
                            actionsList.get(j).getTo().setSoldiers(actionsList.get(j).getTo().getSoldiers() - actionsList.get(j).getFrom().getSoldiers());
                        }
                    }

                }
            }
        }
    }
//sets a players strategy (random or A* with Heuristic 1, 2 or 3)
    public void set_strategy(String strat, int index){
        players.get(index).setStrategy(strat);
    }

//adds gold to each players treasury;
    public void add_gold(){
        for(int i = 0; i < players.size(); i++){
            players.get(i).setGold(players.get(i).getGold() + new_gold(players.get(i)));
        }
    }
//calculates amount of gold accrued by a player during a turn
    public int new_gold(Player current){
        int current_economy = 0;
        List<Node> terr = current.getTerritories();
        for(int i = 0; i < terr.size(); i++){
            current_economy += terr.get(i).getEconomy();
        }
        return 4000 + (int)Math.sqrt(current_economy) + (int)((float)current.getGold() * .01);
    }
//provides a copy of the player list
    private List<Player> playerCopy(){
        List<Player> temp = new LinkedList<Player>();
        for(int i = 0; i < players.size(); i++) {
            temp.add(players.get(i));
        }
        return temp;
    }
//gives a new order each turn
    public void randomizeOrder(){
        List<Player> turn_order = new LinkedList<Player>();
        List<Player> current_order = playerCopy();
        Random rd = new Random();
        int temp;
        while(current_order.size()-1 > 0){
            temp = rd.nextInt(current_order.size()-1);
            turn_order.add(current_order.get(temp));
            current_order.remove(temp);
        }
        turn_order.add(current_order.get(0));
        current_order.remove(0);
        players = turn_order;
    }

    public void check_if_eliminated(){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).hasNoTerritories()){
                eliminated(players.get(i));
            }
        }
    }

    public void eliminated(Player current){
        players.remove(current);
    }

    public void check_if_over(){
        temprestrain += 1;
        if(temprestrain >= 500){
            isNotDone = false;
        }
        if(players.size() == 1){
            isNotDone = false;
        }
    }

    public boolean isOver(){
        return !isNotDone;
    }
    private int temprestrain;

    public Player winner(){
        return players.get(0);
    }
//Creates the computer players
    public void setPlayers(int i){
        players = new LinkedList<Player>();
        if(i == 2){
            Player player1 = new Player();
            player1.setName("p1");
            players.add(player1);
            Player player2 = new Player();
            player2.setName("p2");
            players.add(player2);
        }
        if(i == 3){
            Player player1 = new Player();
            player1.setName("p1");
            players.add(player1);
            Player player2 = new Player();
            player2.setName("p2");
            players.add(player2);
            Player player3 = new Player();
            player3.setName("p3");
            players.add(player3);
        }
        if(i == 4){
            Player player1 = new Player();
            player1.setName("p1");
            players.add(player1);
            Player player2 = new Player();
            player2.setName("p2");
            players.add(player2);
            Player player3 = new Player();
            player3.setName("p3");
            players.add(player3);
            Player player4 = new Player();
            player4.setName("p4");
            players.add(player4);
        }
    }
//Gives each player a random starting point on the game board
    public void randStartPos(){
        Random rd = new Random();
        int temp;
        temp = rd.nextInt(board.size());
        for(int i = 0; i < players.size(); i++){
            if(board.get(temp).getOwner().equals("")){
                players.get(i).addTerritory(board.get(temp));
                board.get(temp).setOwner(players.get(i).getName());
            }
            temp = rd.nextInt(board.size());
        }
    }

// hard coded game boards
// visual approximations of each game board provided in src
    public void setBoard(int i){
        board = new LinkedList<Node>();
        if(i == 1){
            Node node1 = new Node();
            Node node2 = new Node();
            Node node3 = new Node();
            Node node4 = new Node();
            Node node5 = new Node();
            Node node6 = new Node();
            Node node7 = new Node();
            Node node8 = new Node();
            Node node9 = new Node();
            Node node10 = new Node();
            node1.addConnected(node2);
            node1.addConnected(node4);
            node2.addConnected(node1);
            node2.addConnected(node3);
            node2.addConnected(node5);
            node3.addConnected(node2);
            node3.addConnected(node6);
            node4.addConnected(node1);
            node4.addConnected(node7);
            node4.addConnected(node5);
            node5.addConnected(node2);
            node5.addConnected(node4);
            node5.addConnected(node6);
            node5.addConnected(node8);
            node6.addConnected(node3);
            node6.addConnected(node5);
            node6.addConnected(node9);
            node7.addConnected(node4);
            node7.addConnected(node8);
            node8.addConnected(node5);
            node8.addConnected(node7);
            node8.addConnected(node9);
            node9.addConnected(node6);
            node9.addConnected(node8);
            node9.addConnected(node10);
            node10.addConnected(node9);
            board.add(node1);
            board.add(node2);
            board.add(node3);
            board.add(node4);
            board.add(node5);
            board.add(node6);
            board.add(node7);
            board.add(node8);
            board.add(node9);
            board.add(node10);
        }
        if(i == 2){
            Node node1 = new Node();
            Node node2 = new Node();
            Node node3 = new Node();
            Node node4 = new Node();
            Node node5 = new Node();
            Node node6 = new Node();
            Node node7 = new Node();
            Node node8 = new Node();
            Node node9 = new Node();
            Node node10 = new Node();
            Node node11 = new Node();
            Node node12 = new Node();
            Node node13 = new Node();
            Node node14 = new Node();
            Node node15 = new Node();
            node1.addConnected(node2);
            node1.addConnected(node3);
            node2.addConnected(node1);
            node2.addConnected(node4);
            node3.addConnected(node1);
            node3.addConnected(node4);
            node4.addConnected(node2);
            node4.addConnected(node3);
            node4.addConnected(node5);
            node5.addConnected(node4);
            node5.addConnected(node6);
            node5.addConnected(node7);
            node6.addConnected(node5);
            node6.addConnected(node7);
            node7.addConnected(node5);
            node7.addConnected(node6);
            node7.addConnected(node8);
            node7.addConnected(node9);
            node7.addConnected(node10);
            node8.addConnected(node7);
            node8.addConnected(node10);
            node8.addConnected(node11);
            node8.addConnected(node12);
            node9.addConnected(node7);
            node9.addConnected(node10);
            node10.addConnected(node7);
            node10.addConnected(node8);
            node10.addConnected(node9);
            node10.addConnected(node11);
            node11.addConnected(node8);
            node11.addConnected(node10);
            node11.addConnected(node12);
            node11.addConnected(node13);
            node12.addConnected(node8);
            node12.addConnected(node11);
            node12.addConnected(node13);
            node13.addConnected(node11);
            node13.addConnected(node12);
            node13.addConnected(node14);
            node13.addConnected(node15);
            node14.addConnected(node13);
            node14.addConnected(node15);
            node15.addConnected(node13);
            node15.addConnected(node14);
            board.add(node1);
            board.add(node2);
            board.add(node3);
            board.add(node4);
            board.add(node5);
            board.add(node6);
            board.add(node7);
            board.add(node8);
            board.add(node9);
            board.add(node10);
            board.add(node11);
            board.add(node12);
            board.add(node13);
            board.add(node14);
            board.add(node15);
        }
        if(i == 3){
            Node node1 = new Node();
            Node node2 = new Node();
            Node node3 = new Node();
            Node node4 = new Node();
            Node node5 = new Node();
            Node node6 = new Node();
            Node node7 = new Node();
            Node node8 = new Node();
            Node node9 = new Node();
            Node node10 = new Node();
            Node node11 = new Node();
            Node node12 = new Node();
            Node node13 = new Node();
            Node node14 = new Node();
            Node node15 = new Node();
            Node node16 = new Node();
            Node node17 = new Node();
            Node node18 = new Node();
            Node node19 = new Node();
            Node node20 = new Node();
            Node node21 = new Node();
            node1.addConnected(node2);
            node1.addConnected(node3);
            node2.addConnected(node1);
            node2.addConnected(node3);
            node2.addConnected(node5);
            node3.addConnected(node1);
            node3.addConnected(node2);
            node4.addConnected(node5);
            node4.addConnected(node6);
            node5.addConnected(node2);
            node5.addConnected(node4);
            node5.addConnected(node6);
            node5.addConnected(node8);
            node6.addConnected(node4);
            node6.addConnected(node5);
            node7.addConnected(node8);
            node8.addConnected(node5);
            node8.addConnected(node7);
            node8.addConnected(node9);
            node8.addConnected(node12);
            node8.addConnected(node14);
            node8.addConnected(node15);
            node8.addConnected(node18);
            node9.addConnected(node8);
            node9.addConnected(node10);
            node9.addConnected(node11);
            node9.addConnected(node12);
            node10.addConnected(node9);
            node10.addConnected(node11);
            node11.addConnected(node9);
            node11.addConnected(node10);
            node11.addConnected(node12);
            node12.addConnected(node8);
            node12.addConnected(node9);
            node12.addConnected(node11);
            node12.addConnected(node13);
            node12.addConnected(node14);
            node13.addConnected(node12);
            node13.addConnected(node13);
            node13.addConnected(node14);
            node14.addConnected(node8);
            node14.addConnected(node12);
            node14.addConnected(node13);
            node14.addConnected(node15);
            node14.addConnected(node16);
            node15.addConnected(node8);
            node15.addConnected(node14);
            node15.addConnected(node16);
            node15.addConnected(node17);
            node15.addConnected(node18);
            node16.addConnected(node13);
            node16.addConnected(node14);
            node16.addConnected(node15);
            node16.addConnected(node17);
            node17.addConnected(node15);
            node17.addConnected(node16);
            node17.addConnected(node18);
            node17.addConnected(node19);
            node18.addConnected(node8);
            node18.addConnected(node15);
            node18.addConnected(node17);
            node18.addConnected(node19);
            node19.addConnected(node17);
            node19.addConnected(node18);
            node19.addConnected(node20);
            node19.addConnected(node21);
            node20.addConnected(node19);
            node20.addConnected(node21);
            node21.addConnected(node19);
            node21.addConnected(node20);
            board.add(node1);
            board.add(node2);
            board.add(node3);
            board.add(node4);
            board.add(node5);
            board.add(node6);
            board.add(node7);
            board.add(node8);
            board.add(node9);
            board.add(node10);
            board.add(node11);
            board.add(node12);
            board.add(node13);
            board.add(node14);
            board.add(node15);
            board.add(node16);
            board.add(node17);
            board.add(node18);
            board.add(node19);
            board.add(node20);
            board.add(node21);
        }
    }
}