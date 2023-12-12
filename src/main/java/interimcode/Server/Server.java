package interimcode.Server;

/**
 * The Server class represents a server with a name, total owed amount, and returning status.
 */
public class Server {

    /**
     * The name of the server.
     */
    protected String name;

    /**
     * The total amount of tips owed to the server.
     */
    protected double totalOwed;

    /**
     * The returning status of the server.
     */
    protected boolean returning;


    /**
     * Default constructor for the Server class. Initializes the server with default values.
     */
    public Server() {
        setName("Empty");
        this.totalOwed = 0;
        returning = false;
    }

    /**
     * Constructor for the Server class with a specified name. Initializes total owed to 0.
     *
     * @param name The name of the server.
     */
    public Server(String name) {
        setName(name);
        this.totalOwed = 0;
    }

    /**
     * Constructor for the Server class with specified name, total owed, and returning status.
     *
     * @param name The name of the server.
     * @param totalOwed The total amount of tips owed to the server.
     * @param returning The returning status of the server.
     */
    public Server(String name, double totalOwed, boolean returning) {
        setName(name);
        this.totalOwed = totalOwed;
        this.returning = returning;
    }

    /**
     * Sets the returning status of the server.
     *
     * @param flag The returning status to set.
     */
    public void setReturning(boolean flag) {
        this.returning = flag;
    }

    /**
     * Gets the returning status of the server.
     *
     * @return The returning status of the server.
     */
    public boolean getReturning() {
        return this.returning;
    }

    /**
     * Gets the name of the server.
     *
     * @return The name of the server.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the server.
     *
     * @param name The name to set for the server.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds the specified amount to the total owed by the server.
     *
     * @param amount The amount to add to the total owed.
     */
    public void addTips(double amount) {
        this.totalOwed += amount;
    }

    /**
     * Subtracts the specified amount from the total owed by the server.
     *
     * @param amount The amount to subtract from the total owed.
     */
    public void subtractTips(double amount) {
        this.totalOwed -= amount;
    }

    /**
     * Gets the total amount owed by the server.
     *
     * @return The total amount owed by the server.
     */
    public double getTotalOwed() {
        return this.totalOwed;
    }

    /**
     * Returns a string representation of the server, including name, total owed, and returning status.
     *
     * @return A string representation of the server.
     */
    public String toString() {
        String formattedName = String.format("%-15s", this.getName());
        String formattedTotalOwed = String.format("%7.2f", this.getTotalOwed());
        return "| Name: " + formattedName + " | Total Owed: $" + formattedTotalOwed + "  | Returning: " + this.getReturning() + " |";
    }
    
    
}//end of Server class
