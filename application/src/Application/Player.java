package Application;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//contains Player helper functions and data values


public class Player {
    private int moves;
    private int gold;
    private String strategy;
    private String name;
    private List<Node> territories;
    private List<Action> actions;

    Player(){
        gold = 50000;
        territories = new LinkedList<Node>();
        actions = new LinkedList<Action>();
        name = "";
        strategy = "";
    }

    public void addTerritory(Node temp){
        territories.add(temp);
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public List<Node> getTerritories() {
        return territories;
    }

    public boolean hasNoTerritories(){
        return territories.isEmpty();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setStrategy(String strat){
        strategy = strat;
    }

    public String getStrategy(){
        return strategy;
    }

    public List<Action> getActions(){
        return actions;
    }

    public void setActions(List<Action> temp){
        this.actions = temp;
    }

    public void addActions(){
        if(strategy.equals("r")){
            Random rd = new Random();
            int rand;
            int temp2;

                Action temp = new Action();
                rand = rd.nextInt(2);
                if(rand == 0){
                    temp.setType("att");
                    if(territories.size()-1 > 0) {
                        rand = rd.nextInt(territories.size() - 1);
                        temp.setFrom(territories.get(rand));
                        if (territories.get(rand).getSoldiers() == 0) {
                            temp.setType("rec");
                            temp.setPerf(this);
                            if (gold > territories.get(rand).getEconomy()) {
                                rand = rd.nextInt(territories.get(rand).getEconomy() - 2);
                                temp.setAmount(rand);
                            } else {
                                rand = rd.nextInt(gold - 2);
                                temp.setAmount(rand);
                            }
                            actions.add(temp);
                        } else {
                            temp2 = rand;
                            rand = rd.nextInt(territories.get(temp2).can_be_attacked().size() - 1);
                            temp.setTo(territories.get(temp2).can_be_attacked().get(rand));
                            rand = rd.nextInt(territories.get(temp2).getSoldiers() - 1);
                            temp.setAmount(rand);
                            temp.setPerf(this);
                        }
                    }
                    else{
                        temp.setFrom(territories.get(0));
                        if(territories.get(0).getSoldiers() == 0){
                            temp.setType("rec");
                            temp.setPerf(this);
                            if (gold > territories.get(0).getEconomy()) {
                                rand = rd.nextInt(territories.get(0).getEconomy() - 2);
                                temp.setAmount(rand);
                            }
                            else{
                                rand = rd.nextInt(gold - 2);
                                temp.setAmount(rand);
                            }
                            actions.add(temp);
                        }
                        else {
                            temp2 = rand;
                            if ((territories.get(temp2).can_be_attacked().size() - 1) > 0) {
                                rand = rd.nextInt(territories.get(temp2).can_be_attacked().size() - 1);
                                temp.setTo(territories.get(temp2).can_be_attacked().get(rand));
                                rand = rd.nextInt(territories.get(temp2).getSoldiers() - 1);
                                temp.setAmount(rand);
                                temp.setPerf(this);
                            } else if ((territories.get(temp2).can_be_attacked().size() - 1) == 0) {
                                temp.setTo(territories.get(0));
                                if(territories.get(0).getSoldiers() > 0) {
                                    rand = rd.nextInt(territories.get(0).getSoldiers());
                                }
                                else{
                                    temp.setType("rec");

                                }
                                temp.setAmount(rand);
                                temp.setPerf(this);
                                actions.add(temp);
                            }
                        }
                    }
                }
                else if(rand == 1){
                    temp.setType("move");
                    temp.setPerf(this);
                    if(territories.size()-1 > 0) {
                        rand = rd.nextInt(territories.size() - 1);
                        temp2 = rand;
                        temp.setFrom(territories.get(temp2));
                        if ((territories.get(temp2).can_be_moved_to().size() > 0) && (territories.get(temp2).getSoldiers() > 0)) {
                            rand = rd.nextInt(territories.get(temp2).can_be_moved_to().size() - 1);
                            temp.setTo(territories.get(temp2).can_be_moved_to().get(rand));
                            rand = rd.nextInt(territories.get(temp2).getSoldiers() - 1);
                            temp.setAmount(rand);
                            actions.add(temp);
                        }
                    }
                    else{
                        moves += 1;
                    }
                }
                else if(rand == 2) {
                    temp.setType("rec");
                    temp.setPerf(this);
                    if (territories.size() - 1 > 0) {
                        rand = rd.nextInt(territories.size() - 1);
                        temp.setFrom(territories.get(rand));
                        if ((gold > 0) && (territories.get(rand).getEconomy() > 0)) {
                            if (gold > territories.get(rand).getEconomy()) {
                                temp2 = rand;
                                rand = rd.nextInt(territories.get(temp2).getEconomy() - 1);
                                temp.setAmount(rand);
                                actions.add(temp);
                            } else {
                                rand = rd.nextInt(gold - 1);
                                temp.setAmount(rand);
                                actions.add(temp);
                            }
                        }
                    } else {
                        temp.setFrom(territories.get(0));
                        if ((gold > 0) && (territories.get(0).getEconomy() > 0)) {
                            if (gold > territories.get(0).getEconomy()) {
                                temp2 = rand;
                                rand = rd.nextInt(territories.get(0).getEconomy() - 1);
                                temp.setAmount(rand);
                                actions.add(temp);
                            } else {
                                rand = rd.nextInt(gold - 1);
                                temp.setAmount(rand);
                                actions.add(temp);
                            }
                        }
                    }
                }

        }
        if(strategy.equals("h1")){

                int heurscore = 0;
                int heurTo = 0;
                Node bestFrom = new Node();
                Node bestTo = new Node();
                for(int q = 0; q < territories.size(); q++) {
                    if (territories.get(q).can_attack()) {
                        bestFrom = territories.get(q);
                        bestTo = territories.get(q).can_be_attacked().get(0);
                    }
                }
                for (int i = 0; i < territories.size(); i++) {
                    if (territories.get(i).attack_surface_size() > heurscore) {
                        heurscore = territories.get(i).attack_surface_size();
                        bestFrom = territories.get(i);
                    }
                }
                for (int j = 0; j < bestFrom.can_be_attacked().size(); j++) {
                    if (bestFrom.can_be_attacked().get(j).getSoldiers() < heurTo) {
                        heurTo = bestFrom.can_be_moved_to().get(j).getSoldiers();
                        bestTo = bestFrom.can_be_moved_to().get(j);
                    }
                }
                Action best = new Action();
                best.setFrom(bestFrom);
                best.setTo(bestTo);
                best.setAmount(bestFrom.getSoldiers());
                best.setPerf(this);
                best.setType("att");
                actions.add(best);
        }
        if(strategy.equals("h2")){


                int heurscore = 0;
                int heurTo = 0;
                Node bestFrom = new Node();
                Node bestTo = new Node();
                for(int q = 0; q < territories.size(); q++) {
                    if (territories.get(q).can_attack()) {
                        bestFrom = territories.get(q);
                        bestTo = territories.get(q).can_be_attacked().get(0);
                    }
                }
                for (int i = 0; i < territories.size(); i++) {
                    if (territories.get(i).can_be_moved_to().size() > heurscore) {
                        heurscore = territories.get(i).can_be_moved_to().size();
                        bestFrom = territories.get(i);
                    }
                }
                for (int j = 0; j < bestFrom.can_be_attacked().size(); j++) {
                    if (bestFrom.can_be_attacked().get(j).getSoldiers() < heurTo) {
                        heurTo = bestFrom.can_be_moved_to().get(j).getSoldiers();
                        bestTo = bestFrom.can_be_moved_to().get(j);
                    }
                }
                Action best = new Action();
                best.setFrom(bestFrom);
                best.setTo(bestTo);
                best.setAmount(bestFrom.getSoldiers());
                best.setPerf(this);
                best.setType("att");
                actions.add(best);
        }
        if(strategy.equals("h3")){

                int heurscore = 0;
                int heurTo = 0;
                Node bestFrom = new Node();
                Node bestTo = new Node();
                for(int q = 0; q < territories.size(); q++) {
                    if (territories.get(q).can_attack()) {
                        bestFrom = territories.get(q);
                        bestTo = territories.get(q).can_be_attacked().get(0);
                    }
                }
                for (int h = 0; h < territories.size(); h++) {
                    if ((territories.get(h).getSoldiers() + territories.get(h).getEconomy()) > heurscore) {
                        heurscore = territories.get(h).getSoldiers() + territories.get(h).getEconomy();
                        bestFrom = territories.get(h);
                    }
                }
                for (int j = 0; j < bestFrom.can_be_attacked().size(); j++) {
                    if (bestFrom.can_be_attacked().get(j).getEconomy() - bestFrom.can_be_attacked().get(j).getSoldiers() > heurTo) {
                        bestTo = bestFrom.can_be_attacked().get(j);
                        heurTo = bestFrom.can_be_attacked().get(j).getEconomy() - bestFrom.can_be_attacked().get(j).getSoldiers();
                    }
                }
                Action best = new Action();
                best.setFrom(bestFrom);
                best.setTo(bestTo);
                best.setAmount(bestFrom.getSoldiers());
                best.setPerf(this);
                best.setType("att");
                actions.add(best);
        }
    }
}
