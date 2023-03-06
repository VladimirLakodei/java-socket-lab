import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void sendMessage(String message) throws IOException {
        out.println(message);
        String response = in.readLine();
        System.out.println("Response from server: " + response);
    }

    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String message;

        while (true) {
            System.out.print("Enter a string to send to the server (or 'quit' to exit): ");
            message = console.readLine();

            if (message.equals("quit")) {
                break;
            }

            Client client = new Client(host, port);
            client.sendMessage(message);
            client.close();
        }
    }
}
