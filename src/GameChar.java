import java.io.Serializable;

public class GameChar implements Serializable{
    private String[] inventory;
    private int locX;
    private int locY;
    private int sight;
    private Map m;

    public GameChar(Map map) {
        locX = 0;
        locY = 0;
        sight = 1; // Default sight value. Determines how many tiles the char can see in each directions
        m = null;
        inventory = new String[10];
        inventory[0] = "brass lantern";
        inventory[1] = "rope";
        inventory[2] = "rations";
        inventory[3] = "staff";

        this.m = map;
    }

    public String showInventory() {
        String output = "You are carrying: ";
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {continue;}
            output += inventory[i] + "\n";
        }
        return output + sayLoc();
    }

    String move (String input) {

        String output;
        //Split input to get the direction
        String[] com = input.split(" +");

        // Check if there is a direction to go
        if ( com.length == 1) {
            return "Tell me where to go";
        }

        // Grab the first char of the direction input
        char dir = com[1].charAt(0);

        // Switch that looks at the first char of the direction input and moves the character if legal
        switch (dir) {
            case 'n':
            case 'N':
                if (locX == 0) {
                    output = "You cannot go that far north";
                    break;
                }
                locX = locX - 1;
                output = "Moving north...";
                break;
            case 's':
            case 'S':
                if (locX == m.getSize()[0] - 1) {
                    output = "You cannot go that far south";
                    break;
                }
                locX = locX + 1;
                output = "Moving south...";
                break;
            case 'e':
            case 'E':
                if (locY == m.getSize()[1] - 1) {
                    output = "You cannot go that far east";
                    break;
                }
                locY = locY + 1;
                output = "Moving east...";
                break;
            case 'w':
            case 'W':
                if (locY == 0) {
                    output = "You cannot go that far west";
                    break;
                }
                locY = locY - 1;
                output = "Moving west...";
                break;
            default:
                output = "You cannot go that way";
                break;
        }
        return output + "\n" + sayLoc();
    }

    String sayLoc() {
        return "You are at location " + locX + "," + locY;

/*        for (int i = locX - sight; i < locX + sight + 1; i++) {
            for (int j = locY - sight; j < locY + sight + 1; j++) {
                if (i < 0 || i >= m.getSize()[0] || j < 0 || j >= m.getSize()[1]) {
                    System.out.print("X ");
                    continue;
                }
                System.out.print(m.getMap()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();*/
    }

    public void setMap(Map v) {
        m = v;
    }

    public String findItem() {
        String output;

        String item = m.getItem(locX, locY);

        if (item != "none") {
            String[] tokens = item.split(";");
            output = "You have found ";
            for (int i = 0; i < tokens.length; i++) {
                output += tokens[i] + "\n";
            }
        }
        else {
            output =  "You have found nothing";
        }
        return output + "\n" + sayLoc();
    }

    public String takeItem(String com) {
        String[] tokens = com.split(" ", 2);
        String item = tokens[1];

        // Make sure that item is there
        tokens = m.getItem(locX, locY).split(";");
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(item)) {
                for (int z = 0; z < inventory.length; z++) {
                    if (inventory[z] == null) {
                        inventory[z] = item;
                        m.removeItem(locX, locY, item);
                        return item + " has been added to your inventory" + "\n" + sayLoc();
                    }
                }
                return "Your inventory is too full" + "\n" + sayLoc();
            }
        }
        return "You are not by an item named " + item + "\n" + sayLoc();
    }

    public String dropItem(String com) {
        String[] tokens = com.split(" ", 2);
        String item = tokens[1];

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {continue;}
            if (inventory[i].equals(item)) {
                inventory[i] = null;
                m.addItem(locX, locY, item);
                return "You have dropped " + item + " at " + locX + ", " + locY + "\n" + sayLoc();

            }
        }
        return "You do not have " + item + "\n" + sayLoc();
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }
}
