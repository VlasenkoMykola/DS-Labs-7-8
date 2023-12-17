package ua.knu.vlasenko;
import ua.knu.vlasenko.Dealer;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class DealerTCPServer implements Runnable {

    private final Dealer dealer;

    public DealerTCPServer(Dealer dealer) {
	this.dealer = dealer;
    }

    @Override
    public void run() {
	try (ServerSocket serverSocket = new ServerSocket(5000)) {
	    System.out.println("Socket server is running on port 5000. Waiting for connections...");

	    while (true) {
		try (Socket clientSocket = serverSocket.accept();
		     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

		    String line;
		    while ((line = reader.readLine()) != null) {
			processRequest(line, writer);
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private void processRequest(String request, PrintWriter writer) {
        // Remove "\n" from the line
        request = request.replace("\n", "");

        // Split line by separator <space>
        String[] tokens = request.split(" ");
        String command = tokens[0];
        String[] arguments = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, arguments, 0, tokens.length - 1);

        try {
            switch (command) {
                case "end":
                    // Close the connection with the client
                    writer.println("Connection closed.");
                    return;
                case "createMaker":
                    writer.println(String.valueOf(dealer.createMaker(arguments[0])));
                    break;
                case "removeMaker":
		    writer.println(String.valueOf(dealer.removeMaker(arguments[0])));
                    break;
                case "addModel":
                    writer.println(String.valueOf(dealer.addModel(arguments[0], arguments[1], Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]))));
                    break;
                case "removeModel":
		    writer.println(String.valueOf(dealer.removeModel(arguments[0])));
                    break;
                case "getMakerNames":
                    writer.println(String.join(" ", dealer.getMakerNames()));
                    break;
                case "getNumberOfMakers":
                    writer.println(dealer.getNumberOfMakers());
                    break;
                case "getNumberOfModels":
                    writer.println(dealer.getNumberOfModels(arguments[0]));
                    break;
                case "getModelList":
                    writer.println(String.join(" ", dealer.getModelList()));
                    break;
                default:
                    writer.println("Invalid command: " + command);
            }
        } catch (RemoteException e) {
            writer.println("Error processing request: " + e.getMessage());
        }
    }
}
