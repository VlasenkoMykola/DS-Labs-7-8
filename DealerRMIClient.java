package ua.knu.vlasenko;
import ua.knu.vlasenko.Dealer;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealerRMIClient  {
    public DealerRMIClient() {
    }

    public static Dealer connect() throws RemoteException {
	try {
	    Dealer dealer = (Dealer) Naming.lookup("rmi://localhost/DealerServer");
	    return dealer;
	} catch (NotBoundException e) {
            e.printStackTrace();
	    throw new RemoteException();
	} catch (MalformedURLException e) {
            e.printStackTrace();
	    throw new RemoteException();
        }
    }

}
