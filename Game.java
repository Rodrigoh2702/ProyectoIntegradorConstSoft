public class Game {
    private Parser parser;
    private Room currentRoom;

    public Game() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        Room outside, theatre, pub, lab, office, nullRoom;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        nullRoom = new NullRoom();

        // initialise room exits
        outside.setExits(nullRoom, theatre, lab, pub);
        theatre.setExits(nullRoom, nullRoom, nullRoom, outside);
        pub.setExits(nullRoom, outside, nullRoom, nullRoom);
        lab.setExits(outside, office, nullRoom, nullRoom);
        office.setExits(nullRoom, nullRoom, nullRoom, lab);

        currentRoom = outside; // start game outside
    }

    public void play() {
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.
        boolean playing = true;
        do{
            playing = stillPlaying();
        }while(playing);
        System.out.println("Thank you for playing.  Good bye.");
    }

    public boolean stillPlaying(){
        Command command = parser.getCommand();
        return processCommand(command);
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        moveRoom(currentRoom);
    }

    private boolean processCommand(Command command) {

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return true;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            return printHelp();
        if (commandWord.equals("go"))
            return goRoom(command);
        if (commandWord.equals("quit"))
            return quit(command);
            
        return true;
    }

    // implementations of user commands:
    private boolean printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
        return true;
    }

    private boolean goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return true;
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = assignNextRoom(direction);

        if (nextRoom.isNull()) {
            System.out.println("There is no door!");
            return true;
        } else {
            moveRoom(nextRoom);
            return true;
        }

    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return true;
        } else {
            return false; // signal that we want to quit
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
        if (!currentRoom.northExit.isNull()) {
            System.out.print("north ");
        }
        if (!currentRoom.eastExit.isNull()) {
            System.out.print("east ");
        }
        if (!currentRoom.southExit.isNull()) {
            System.out.print("south ");
        }
        if (!currentRoom.westExit.isNull()) {
            System.out.print("west ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
