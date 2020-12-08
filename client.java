import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
    static Scanner heightInput;
    static Scanner weightInput;

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        
        // Create variables
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;

        // Height and weight input from user
        heightInput = new Scanner(System.in);
        System.out.print("Enter height (m): ");
        float height = Float.parseFloat(heightInput.nextLine());

        weightInput = new Scanner(System.in);
        System.out.print("Enter weight (kg): ");
        float weight = Float.parseFloat(weightInput.nextLine());

        float[] heightWeight = {height, weight};

        // Send data to server
        socket = new Socket(host.getHostName(), 9000);
        outputStream = new ObjectOutputStream(socket.getOutputStream());

        outputStream.writeObject(heightWeight);

        // Receive response from server
        inputStream = new ObjectInputStream(socket.getInputStream());
        float message = (float) inputStream.readObject();
        System.out.println("Body Mass Index: " + message);

        inputStream.close();
        outputStream.close();
        Thread.sleep(1000);

    }
}