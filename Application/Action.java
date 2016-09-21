package Application;

//Action class contains information critical to performing actions
public class Action {
    private String type;
    private Player perf;
    private Node from;
    private Node to;
    private int amount;
    Action(){type = "";}

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public Player getPerf(){
        return perf;
    }

    public void setPerf(Player perf){
        this.perf = perf;
    }

    public Node getFrom(){
        return from;
    }

    public void setFrom(Node from){
        this.from = from;
    }

    public Node getTo(){
        return to;
    }

    public void setTo(Node to){
        this.to = to;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

}
