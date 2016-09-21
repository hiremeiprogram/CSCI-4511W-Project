package Application;

import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to my Age of Civilizations clone");
        System.out.println("\n");
        System.out.println("Would you like to play? (y or n)");

        String temp = sc.nextLine();
        while(!temp.equals("y") && !temp.equals("n")){
            System.out.println("Please enter y or n");
            temp = sc.nextLine();
        }

        if(temp.equals("y")){
            System.out.println("Which board would you like to play? (Small, Medium, or Large)");
            temp = sc.nextLine();
            while(!temp.equals("Small") && !temp.equals("Medium") && !temp.equals("Large")){
                System.out.println("Please enter Small, Medium, or Large");
                temp = sc.nextLine();
            }

            Game MyGame = new Game();
            if(temp.equals("Small")){
                MyGame.setBoard(1);
            }
            else if(temp.equals("Medium")){
                MyGame.setBoard(2);
            }
            else if(temp.equals("Large")){
                MyGame.setBoard(3);
            }

            System.out.println("How many players would you like to have? (2, 3, or 4)");
            temp = sc.nextLine();
            while(!temp.equals("2") && !temp.equals("3") && !temp.equals("4")){
                System.out.println("Please enter 2, 3, or 4");
                temp = sc.nextLine();
            }
            if(temp.equals("2")){
                MyGame.setPlayers(2);
                System.out.println("Choose a strategy for each player:");
                System.out.println("Should player 1 play randomly or use A* search with heuristic 1, 2 or 3? (r, h1, h2, or h3)");
                temp = sc.nextLine();
                while(!temp.equals("r") && !temp.equals("h1") && !temp.equals("h2") && !temp.equals("h3")){
                    System.out.println("Please enter r, h1, h2, or h3");
                    temp = sc.nextLine();
                }
                if(temp.equals("r")){
                    MyGame.set_strategy("r", 0);
                }
                else if(temp.equals("h1")){
                    MyGame.set_strategy("h1", 0);
                }
                else if(temp.equals("h2")){
                    MyGame.set_strategy("h2", 0);
                }
                else if(temp.equals("h3")){
                    MyGame.set_strategy("h3", 0);
                }
                System.out.println("Should player 2 play randomly or use A* search with h1, h2 or h3? (r, h1, h2, or h3)");
                temp = sc.nextLine();
                while(!temp.equals("r") && !temp.equals("h1") && !temp.equals("h2") && !temp.equals("h3")){
                    System.out.println("Please enter r, h1, h2, or h3");
                    temp = sc.nextLine();
                }
                if(temp.equals("r")){
                    MyGame.set_strategy("r", 1);
                }
                else if(temp.equals("h1")){
                    MyGame.set_strategy("h1", 1);
                }
                else if(temp.equals("h2")){
                    MyGame.set_strategy("h2", 1);
                }
                else if(temp.equals("h3")){
                    MyGame.set_strategy("h3", 1);
                }
            }
            else if(temp.equals("3")){
                MyGame.setPlayers(3);
                System.out.println("Choose a strategy for each player:");
                System.out.println("Should player 1 play randomly or use A* search with heuristic 1, 2 or 3? (r, h1, h2, or h3)");
                temp = sc.nextLine();
                while(!temp.equals("r") && !temp.equals("h1") && !temp.equals("h2") && !temp.equals("h3")){
                    System.out.println("Please enter r, h1, h2, or h3");
                    temp = sc.nextLine();
                }
                if(temp.equals("r")){
                    MyGame.set_strategy("r", 0);
                }
                else if(temp.equals("h1")){
                    MyGame.set_strategy("h1", 0);
                }
                else if(temp.equals("h2")){
                    MyGame.set_strategy("h2", 0);
                }
                else if(temp.equals("h3")){
                    MyGame.set_strategy("h3", 0);
                }
                System.out.println("Should player 2 play randomly or use A* search with h1, h2 or h3? (r, h1, h2, or h3)");
                temp = sc.nextLine();
                while(!temp.equals("r") && !temp.equals("h1") && !temp.equals("h2") && !temp.equals("h3")){
                    System.out.println("Please enter r, h1, h2, or h3");
                    temp = sc.nextLine();
                }
                if(temp.equals("r")){
                    MyGame.set_strategy("r", 1);
                }
                else if(temp.equals("h1")){
                    MyGame.set_strategy("h1", 1);
                }
                else if(temp.equals("h2")){
                    MyGame.set_strategy("h2", 1);
                }
                else if(temp.equals("h3")){
                    MyGame.set_strategy("h3", 1);
                }
                System.out.println("Should player 3 play randomly or use A* search with h1, h2 or h3? (r, h1, h2, or h3)");
                temp = sc.nextLine();
                while(!temp.equals("r") && !temp.equals("h1") && !temp.equals("h2") && !temp.equals("h3")){
                    System.out.println("Please enter r, h1, h2, or h3");
                    temp = sc.nextLine();
                }
                if(temp.equals("r")){
                    MyGame.set_strategy("r", 2);
                }
                else if(temp.equals("h1")){
                    MyGame.set_strategy("h1", 2);
                }
                else if(temp.equals("h2")){
                    MyGame.set_strategy("h2", 2);
                }
                else if(temp.equals("h3")){
                    MyGame.set_strategy("h3", 2);
                }
            }
            else if(temp.equals("4")){
                MyGame.setPlayers(4);
                System.out.println("Choose a strategy for each player:");
                System.out.println("Should player 1 play randomly or use A* search with heuristic 1, 2 or 3? (r, h1, h2, or h3)");
                temp = sc.nextLine();
                while(!temp.equals("r") && !temp.equals("h1") && !temp.equals("h2") && !temp.equals("h3")){
                    System.out.println("Please enter r, h1, h2, or h3");
                    temp = sc.nextLine();
                }
                if(temp.equals("r")){
                    MyGame.set_strategy("r", 0);
                }
                else if(temp.equals("h1")){
                    MyGame.set_strategy("h1", 0);
                }
                else if(temp.equals("h2")){
                    MyGame.set_strategy("h2", 0);
                }
                else if(temp.equals("h3")){
                    MyGame.set_strategy("h3", 0);
                }
                System.out.println("Should player 2 play randomly or use A* search with h1, h2 or h3? (r, h1, h2, or h3)");
                temp = sc.nextLine();
                while(!temp.equals("r") && !temp.equals("h1") && !temp.equals("h2") && !temp.equals("h3")){
                    System.out.println("Please enter r, h1, h2, or h3");
                    temp = sc.nextLine();
                }
                if(temp.equals("r")){
                    MyGame.set_strategy("r", 1);
                }
                else if(temp.equals("h1")){
                    MyGame.set_strategy("h1", 1);
                }
                else if(temp.equals("h2")){
                    MyGame.set_strategy("h2", 1);
                }
                else if(temp.equals("h3")){
                    MyGame.set_strategy("h3", 1);
                }
                System.out.println("Should player 3 play randomly or use A* search with h1, h2 or h3? (r, h1, h2, or h3)");
                temp = sc.nextLine();
                while(!temp.equals("r") && !temp.equals("h1") && !temp.equals("h2") && !temp.equals("h3")){
                    System.out.println("Please enter r, h1, h2, or h3");
                    temp = sc.nextLine();
                }
                if(temp.equals("r")){
                    MyGame.set_strategy("r", 2);
                }
                else if(temp.equals("h1")){
                    MyGame.set_strategy("h1", 2);
                }
                else if(temp.equals("h2")){
                    MyGame.set_strategy("h2", 2);
                }
                else if(temp.equals("h3")){
                    MyGame.set_strategy("h3", 2);
                }
                System.out.println("Should player 4 play randomly or use A* search with h1, h2 or h3? (r, h1, h2, or h3)");
                temp = sc.nextLine();
                while(!temp.equals("r") && !temp.equals("h1") && !temp.equals("h2") && !temp.equals("h3")){
                    System.out.println("Please enter r, h1, h2, or h3");
                    temp = sc.nextLine();
                }
                if(temp.equals("r")){
                    MyGame.set_strategy("r", 3);
                }
                else if(temp.equals("h1")){
                    MyGame.set_strategy("h1", 3);
                }
                else if(temp.equals("h2")){
                    MyGame.set_strategy("h2", 3);
                }
                else if(temp.equals("h3")){
                    MyGame.set_strategy("h3", 3);
                }
            }
            //Main Game Loop
            System.out.println("Starting Game");
            MyGame.randStartPos();
            while(!MyGame.isOver()){
                MyGame.randomizeOrder();
                MyGame.set_moves();
                MyGame.get_actions();
                MyGame.perform_actions();
                MyGame.check_if_eliminated();
                MyGame.add_gold();
                MyGame.check_if_over();
            }
            System.out.println("Player " + MyGame.winner().getName() + " won using heuristic " + MyGame.winner().getStrategy());
            System.out.println("Ending Game");
        }
        else if (temp.equals("n")){
            System.out.println("Goodbye");
        }


    }
}
