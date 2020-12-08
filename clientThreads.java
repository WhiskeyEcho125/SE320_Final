import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.lang.Thread;
import java.util.Scanner;

public class clientThreads extends Thread {
    static Scanner heightInput;
    static Scanner weightInput;
    public Socket socket;

    public void run() {
        try {
            InetAddress host = InetAddress.getLocalHost();
            ObjectOutputStream outputStream = null;
            ObjectInputStream inputStream = null;
    
            heightInput = new Scanner(System.in);
            System.out.print("Enter height (m): ");
            float height = Float.parseFloat(heightInput.nextLine());
    
            weightInput = new Scanner(System.in);
            System.out.print("Enter weight (kg): ");
            float weight = Float.parseFloat(weightInput.nextLine());
    
            float[] heightWeight = {height, weight};
    
            socket = new Socket(host.getHostName(), 9001);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
    
            outputStream.writeObject(heightWeight);
    
            inputStream = new ObjectInputStream(socket.getInputStream());
            float message = (float) inputStream.readObject();
            System.out.println("Body Mass Index: " + message);
    
            inputStream.close();
            outputStream.close();
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        clientThreads client = new clientThreads();
        client.start();
    }
}