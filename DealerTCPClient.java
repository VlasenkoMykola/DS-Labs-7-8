package ua.knu.vlasenko;
import ua.knu.vlasenko.Dealer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DealerTCPClient extends UnicastRemoteObject implements Dealer {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public DealerTCPClient() throws RemoteException {
        try {
	    this.socket = new Socket("localhost", 5000);
	    this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
	    throw new RemoteException();
        }
    }

    //@Override
    public static Dealer connect() throws RemoteException {
	return new DealerTCPClient();
    }

    @Override
    public void done() throws RemoteException {
	writer.println("end");
    }

    @Override
    public boolean createMaker(String name) throws RemoteException {
	try {
	    writer.println("createMaker " + name);
	    String response = reader.readLine();
	    System.out.println("Server response: " + response);
	    if (response.equals("true\n")) {
		return true;
	    }
	    return false;
	} catch (IOException e) {
            e.printStackTrace();
	    throw new RemoteException();
	}
    }

    @Override
    public boolean removeMaker(String name) throws RemoteException {
	try {
	    writer.println("removeMaker " + name);
	    String response = reader.readLine();
	    System.out.println("Server response: " + response);
	    if (response.equals("true\n")) {
		return true;
	    }
	    return false;
	} catch (IOException e) {
            e.printStackTrace();
	    throw new RemoteException();
	}
    }

    @Override
    public boolean addModel(String makerName, String modelName, int year, int price) throws RemoteException {
	try {
	    writer.println("addModel " + makerName + " " + modelName + " " + year + " " + price);
	    String response = reader.readLine();
	    System.out.println("Server response: " + response);
	    if (response.equals("true\n")) {
		return true;
	    }
	    return false;
	} catch (IOException e) {
            e.printStackTrace();
	    throw new RemoteException();
	}
    }

    @Override
    public boolean removeModel(String modelName) throws RemoteException {
	try {
	    writer.println("removeModel " + modelName);
	    String response = reader.readLine();
	    System.out.println("Server response: " + response);
	    if (response.equals("true\n")) {
		return true;
	    }
	    return false;
	} catch (IOException e) {
            e.printStackTrace();
	    throw new RemoteException();
	}
    }

    @Override
    public List<String> getMakerNames() throws RemoteException {
	try {
	    writer.println("getMakerNames");
	    String response = reader.readLine();
	    System.out.println("Server response: " + response);
	    String result = response.replace(System.getProperty("line.separator"), "");
	    return Arrays.asList(result.split(" "));
	} catch (IOException e) {
            e.printStackTrace();
	    throw new RemoteException();
	}
    }

    @Override
    public int getNumberOfMakers() throws RemoteException {
	try {
	    writer.println("getNumberOfMakers");
	    String response = reader.readLine();
	    System.out.println("Server response: " + response);
	    String result = response.replace(System.getProperty("line.separator"), "");
	    return Integer.parseInt(result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer input");
	    throw new RemoteException();
	} catch (IOException e) {
            e.printStackTrace();
	    throw new RemoteException();
	}
    }

    @Override
    public int getNumberOfModels(String makerName) throws RemoteException {
	try {
	    writer.println("getNumberOfModels " + makerName);
	    String response = reader.readLine();
	    System.out.println("Server response: " + response);
	    String result = response.replace(System.getProperty("line.separator"), "");
	    return Integer.parseInt(result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer input");
	    throw new RemoteException();
	} catch (IOException e) {
            e.printStackTrace();
	    throw new RemoteException();
	}
    }

    @Override
    public List<String> getModelList() throws RemoteException {
	try {
	    writer.println("getModelList");
	    String response = reader.readLine();
	    System.out.println("Server response: " + response);
	    String result = response.replace(System.getProperty("line.separator"), "");
	    return Arrays.asList(result.split(" "));
	} catch (IOException e) {
            e.printStackTrace();
	    throw new RemoteException();
	}
    }
}
