import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// Client/Server functionality credit https://www.journaldev.com/741/java-socket-programming-server-client

public class server {
    private static ServerSocket server;
    private static int port = 9000;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Establish Server
        System.out.println("Server running...");
        server = new ServerSocket(port);
        Socket socket = server.accept();

        // Receive data from client
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        float[] heightWeight = (float[]) inputStream.readObject();

        System.out.println("Height: " + heightWeight[0] + "; Weight: " + heightWeight[1]);

        // Calculations
        float bmi = heightWeight[1] / (heightWeight[0] * heightWeight[0]);
        System.out.println(bmi);

        // Send data to client
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

        outputStream.writeObject(bmi);

        inputStream.close();
        outputStream.close();
        socket.close();


        server.close();
        
    }
}