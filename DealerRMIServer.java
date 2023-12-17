package ua.knu.vlasenko;
import ua.knu.vlasenko.Dealer;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class DealerRMIServer extends UnicastRemoteObject implements Dealer, Runnable {
    private final Dealer impl;

    public DealerRMIServer(Dealer dealerImplementation) throws RemoteException {
        super();
        this.impl = dealerImplementation;
    }

    @Override
    public boolean createMaker(String name) throws RemoteException {
        return impl.createMaker(name);
    }

    @Override
    public boolean removeMaker(String name) throws RemoteException {
        return impl.removeMaker(name);
    }

    @Override
    public boolean addModel(String makerName, String modelName, int year, int price) throws RemoteException {
        return impl.addModel(makerName, modelName, year, price);
    }

    @Override
    public boolean removeModel(String modelName) throws RemoteException {
        return impl.removeModel(modelName);
    }

    @Override
    public List<String> getMakerNames() throws RemoteException {
        return impl.getMakerNames();
    }

    @Override
    public int getNumberOfMakers() throws RemoteException {
        return impl.getNumberOfMakers();
    }

    @Override
    public int getNumberOfModels(String makerName) throws RemoteException {
        return impl.getNumberOfModels(makerName);
    }

    @Override
    public List<String> getModelList() throws RemoteException {
        return impl.getModelList();
    }

    @Override
    public void done() throws RemoteException {
	impl.done();
    }

    @Override
    public void run() {
	try {
	    // Start RMI registry
	    // Create and bind the remote object
	    Naming.rebind("DealerServer", this);
	    System.out.println("DealerRMIServer is running...");
	} catch (RemoteException e) {
            e.printStackTrace();
	} catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
