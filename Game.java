public class Game {
    private Parser parser;
    private Room currentRoom;

    public Game() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        Room outside, theatre, pub, lab, office;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // initialise room exits
        outside.setExits(null, theatre, lab, pub);
        theatre.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        lab.setExits(outside, office, null, null);
        office.setExits(null, null, null, lab);

        currentRoom = outside; // start game outside
    }

    public void play() {
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            finished = stillPlaying();
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    public boolean stillPlaying(){
        boolean finished = false;
        Command command = parser.getCommand();
        finished = processCommand(command);
        return finished;
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        if (currentRoom.northExit != null) {
            System.out.print("north ");
        }
        if (currentRoom.eastExit != null) {
            System.out.print("east ");
        }
        if (currentRoom.southExit != null) {
            System.out.print("south ");
        }
        if (currentRoom.westExit != null) {
            System.out.print("west ");
        }
        System.out.println();
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        if (commandWord.equals("go"))
            goRoom(command);
        if (commandWord.equals("quit"))
            wantToQuit = quit(command);
            
        return wantToQuit;
    }

    // implementations of user commands:
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = assignNextRoom(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            moveRoom(nextRoom);
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true; // signal that we want to quit
        }
    }

    //assigs the room to move
    private Room assignNextRoom(String direction){
        if (direction.equals("north")) {
            return currentRoom.northExit;
        }
        if (direction.equals("east")) {
            return currentRoom.eastExit;
        }
        if (direction.equals("south")) {
            return currentRoom.southExit;
        }
        if (direction.equals("west")) {
            return currentRoom.westExit;
        }
        return null;
    }

    //changes your room
    private void moveRoom(Room nextRoom){
        currentRoom = nextRoom;
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        if (currentRoom.northExit != null) {
            System.out.print("north ");
        }
        if (currentRoom.eastExit != null) {
            System.out.print("east ");
        }
        if (currentRoom.southExit != null) {
            System.out.print("south ");
        }
        if (currentRoom.westExit != null) {
            System.out.print("west ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
