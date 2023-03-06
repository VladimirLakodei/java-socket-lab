import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server is running on port " + port);
    }

    public void start() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread t = new Thread(new ClientHandler(clientSocket));
            t.start();
        }
    }

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        Server server = new Server(port);
        server.start();
    }
}
