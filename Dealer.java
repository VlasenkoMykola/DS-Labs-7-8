package ua.knu.vlasenko;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Facade
public interface Dealer extends Remote {

    boolean createMaker(String name) throws RemoteException;

    boolean removeMaker(String name) throws RemoteException;

    boolean addModel(String makerName, String modelName, int year, int price) throws RemoteException;

    boolean removeModel(String modelName) throws RemoteException;

    List<String> getMakerNames() throws RemoteException;

    int getNumberOfMakers() throws RemoteException;

    int getNumberOfModels(String makerName) throws RemoteException;

    List<String> getModelList() throws RemoteException;

//  static Dealer connect() throws RemoteException;

    void done() throws RemoteException;

}
