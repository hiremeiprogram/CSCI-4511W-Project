package Application;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Node{
    private String owner;
    private List<Node> connected;
    private int economy;
    private int soldiers;

    public Node(){
        owner = "";
        economy = 50000;
        Random rd = new Random();
        int t = rd.nextInt(20000);
        soldiers = t;
        connected = new LinkedList<Node>();
    }

    public List<Node> can_be_attacked(){
        List<Node> able_to_attack = new LinkedList<Node>();
        for(int i = 0; i < connected.size(); i++){
            if(!owner.equals(connected.get(i).owner)){
                able_to_attack.add(connected.get(i));
            }
        }
        return able_to_attack;
    }

    public List<Node> can_be_moved_to(){
        List<Node> can_move_to = new LinkedList<Node>();
        for(int i = 0; i < connected.size(); i++){
            if(owner.equals(connected.get(i).owner)){
                can_move_to.add(connected.get(i));
            }
        }
        return can_move_to;
    }

    public int attack_surface_size(){
        return can_be_attacked().size();
    }

    public boolean can_attack(){
        return attack_surface_size() > 0;
    }

    public String getOwner(){
        return owner;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }

    public void addConnected(Node connected){
        this.connected.add(connected);
    }

    public int getEconomy() {
        return economy;
    }

    public void setEconomy(int economy) {
        this.economy = economy;
    }

    public int getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(int soldiers) {
        this.soldiers = soldiers;
    }
}
