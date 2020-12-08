import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class serverThreads {
    private static ServerSocket server;
    private static int port = 9001;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Server running...");

        while (true) {
            server = new ServerSocket(port);
            Socket socket = server.accept();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            float[] heightWeight = (float[]) inputStream.readObject();
    
            System.out.println("Height: " + heightWeight[0] + "; Weight: " + heightWeight[1]);
    
            float bmi = heightWeight[1] / (heightWeight[0] * heightWeight[0]);
            System.out.println(bmi);
    
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
    
            outputStream.writeObject(bmi);

            inputStream.close();
            outputStream.close();
            socket.close();
            server.close();

        }

        
    }
}