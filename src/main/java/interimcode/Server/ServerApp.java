package interimcode.Server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The ServerApp class manages a list of Server objects and provides functionality
 * for interacting with servers, such as adding tips, storing server information to
 * a text file, and loading server information from a text file.
 */
public class ServerApp {

   /**
    * The list of Server objects managed by the ServerApp.
    */
   public ArrayList<Server> servers;

   /**
    * Constructs a ServerApp object and loads server information from a text file.
    */
   public ServerApp() {
        servers = new ArrayList<>();
        loadServerInfo(servers);
   }

   /**
    * Checks if a user exists in the servers list based on their name.
    * Returns the index in the ArrayList if the server is found.
    *
    * @param name The name of the server to search for.
    * @param servers The ArrayList of Server objects.
    * @return The index of the server in the ArrayList, or 0 if not found.
    */
   public int getServerIndexBasedOnName(String name, ArrayList<Server> servers) {
        int flag = 0;
        int i = 0;
        for (Server element : servers) {
            if (element.getName().equals(name)) {
                flag = i;
                break; // Exit the loop if found
            }
            i++;
        }
        return flag;
   }

   /**
    * Returns the Server object at the specified index in the ArrayList.
    *
    * @param servers The ArrayList of Server objects.
    * @param i The index of the Server object to retrieve.
    * @return The Server object at the specified index.
    */
   public Server getServer(ArrayList<Server> servers, int i) {
        return servers.get(i);
   }

   /**
    * Returns the name of the Server object at the specified index in the ArrayList.
    *
    * @param servers The ArrayList of Server objects.
    * @param i The index of the Server object.
    * @return The name of the Server object at the specified index.
    */
   public String getServerName(ArrayList<Server> servers, int i) {
        return servers.get(i).name;
   }

   /**
    * Adds a new tip to the total owed by the specified server.
    *
    * @param servers The ArrayList of Server objects.
    * @param i The index of the Server object.
    * @param num The amount of the tip to add as a double.
    */
   public void addNewTip(ArrayList<Server> servers, int i, double num) {
        try {
            servers.get(i).addTips(num);
        } catch (Exception e) {
            System.out.println("Tip paid out must be a valid double");
        }
   }

   /**
    * Subtracts a tip from the total owed by the specified server.
    *
    * @param servers The ArrayList of Server objects.
    * @param i The index of the Server object.
    * @param num The amount of the tip to subtract as a double.
    */
   public void subtractTips(ArrayList<Server> servers, int i, double num) {
        try {
            servers.get(i).subtractTips(num);
        } catch (Exception e) {
            System.out.println("Tip paid out must be a valid double");
        }
   }

   /**
    * Returns the total amount owed by the specified server.
    *
    * @param servers The ArrayList of Server objects.
    * @param i The index of the Server object.
    * @return The total amount owed by the specified server.
    */
   public double getTotalOwed(ArrayList<Server> servers, int i) {
        return servers.get(i).getTotalOwed();
   }

   /**
    * Prompts the user to enter the name of a server and returns the entered name.
    * Uses error handling to ensure the name is a String.
    *
    * @param input The Scanner object for user input.
    * @return The name of the server entered by the user.
    */
   public String getServerName(Scanner input) {
        System.out.print("Enter the name of the Server: ");
        String name;
        do {
            try {
                name = input.next();
                break;
            } catch (Exception e) {
                throw new IllegalArgumentException("Name must be a String");
            }
        } while (true);
        
      return name;
   }

   /**
    * Creates a new Server object with the specified name.
    *
    * @param name The name of the new server.
    * @return The newly created Server object.
    */
   public Server createServer(String name) {
        Server server = new Server(name);
        return server;
   }


    /**
     * Prints the information of loaded servers to the console.
     * Each server's details, including name, total owed, and returning status, are displayed.
     * The output is formatted within a bordered section for better readability.
     */
    public void printServerArrayList() {
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("| Loaded Servers:                                                 |");
        System.out.println("| --------------------------------------------------------------- |");
        for (Server server : servers) {
            System.out.println(server);
        }
        System.out.println("------------------------------------------------------------------- \n");
    }


   /**
    * Adds a server to the servers list at index 0.
    *
    * @param sv The Server object to add.
    */
   public void addServer(Server sv) {
        this.servers.add(0, sv);
   }

   /**
    * Stores server data in a text file.
    *
    * @param servers The ArrayList of Server objects.
    */
   public void storeServerInfo(ArrayList<Server> servers) {
        if (servers == null || servers.isEmpty()) {
            System.out.println("No servers to store.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/textFile/Servers.txt"))) {
            for (Server obj : servers) {
                writer.write(obj.getName() + "," + obj.getTotalOwed() + "," + obj.getReturning() + ",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

   /**
    * Loads server information from a text file and stores it in the servers list.
    *
    * @param servers The ArrayList of Server objects.
    */
   public void loadServerInfo(ArrayList<Server> servers) {
        final String filePath = "src/main/resources/textFile/Servers.txt";
    
        try (Scanner input = new Scanner(new File(filePath))) {
            input.useDelimiter(",\\s*");
    
            while (input.hasNext()) {
            
                String name = input.next().trim();
                double totalOwed = input.hasNextDouble() ? input.nextDouble() : 0;
    
                // Read the boolean value as a string and then parse it
                String returningStr = input.next();
                boolean returning = Boolean.parseBoolean(returningStr.toLowerCase());
    
                // regex patter for characters to skip when reading from file
                input.skip(",\\s*");
    
                // add server to the servers Array List
                servers.add(new Server(name, totalOwed, returning));
            }
    
            printServerArrayList(); // /Print server data read from file for debugging

        } catch (FileNotFoundException e) { // Handle any errors
            System.err.println("File not found: " + e.getMessage());
        }
   }

   
} // End of ServerApp class
