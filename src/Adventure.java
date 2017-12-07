import java.io.*;

public class Adventure {

    GameChar test;

/*    public static void main(String[] args) {
        Adventure a = new Adventure();

*//*        Scanner in = null;
        try {
            in = new Scanner(new File(args[0]));
        } catch (FileNotFoundException x) {
            System.out.println("File open failed.");
            x.printStackTrace();
            System.exit(0);
        }*//*

        Scanner in = new Scanner(System.in);

        Map m;
        GameChar test;

        System.out.println("Would you like to start a new game: new");
        System.out.println("Or would you like to load a game: load");
        String ans = in.nextLine();

        if (ans.equals("load")) {
            GameState g = a.load();
            m = g.getMap();
            test = g.getGameChar();
        }
        else {
            in = null;
            try {
                in = new Scanner(new File(args[0]));
            } catch (FileNotFoundException x) {
                System.out.println("File open failed.");
                x.printStackTrace();
                System.exit(0);
            }

            m = new Map();
            //m.addMap(in);
            //m.printMap();

            test = new GameChar();
            test.setMap(m);
        }

*//*        Map m = new Map();
        m.addMap(in);
        //m.printMap();

        GameChar test = new GameChar();
        test.setMap(m);*//*
        in = new Scanner(System.in);
        String choice = "";

        while (!choice.equals("quit")) {
            choice = in.nextLine();
            char[] ch = choice.toCharArray();
            switch (ch[0]) {
                case 'b':
                case 'B':
                    GameState gameState = new GameState(m, test);
                    a.save(gameState);
                    break;
                case 's':
                case 'S':
                    test.findItem();
                    break;
                case 'd':
                case 'D':
                    test.dropItem(choice);
                    break;
                case 't':
                case 'T':
                    test.takeItem(choice);
                    break;
                case 'i':
                case 'I':
                    test.showInventory();
                    break;
                case 'g':
                case 'G':
                    test.move(choice);
                    break;
                case 'q':
                case 'Q':
                    test.sayLoc();
                    System.out.println("Wasn't that fun!");
                    choice = "quit";
                    break;
                default:
                    System.out.println("Invalid command: " + choice);
                    break;
            }
        }
        in.close();
        System.exit(0);
    }*/

    public Adventure(GameChar c) {
        this.test = c;
    }

    public String action(String choice) {
        char[] ch = choice.toCharArray();
        switch (ch[0]) {
            case 's':
            case 'S':
                return test.findItem();
            case 'd':
            case 'D':
                return test.dropItem(choice);
            case 't':
            case 'T':
                return test.takeItem(choice);
            case 'i':
            case 'I':
                return test.showInventory();
            case 'g':
            case 'G':
                return test.move(choice);
            case 'q':
            case 'Q':
                test.sayLoc();
                return "Wasn't that fun!";
                //break;
            default:
                return "Invalid command: " + choice;
                //break;
        }
    }

    public void save(GameState g) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("state.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(g);
            out.close();
            fileOut.close();
            //System.out.println("Serialized data is saved in state.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public GameState load() {
        GameState e = null;

        try {
            FileInputStream fileIn = new FileInputStream("state.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (GameState) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            //return;
        } catch (ClassNotFoundException c) {
            System.out.println("GameState class not found");
            c.printStackTrace();
            //return;
        }

        return e;
    }
}


