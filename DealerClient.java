package ua.knu.vlasenko;
import ua.knu.vlasenko.Dealer;
import ua.knu.vlasenko.DealerSQL;
import ua.knu.vlasenko.DealerRMIServer;
import ua.knu.vlasenko.DealerRMIClient;
import ua.knu.vlasenko.DealerTCPServer;
import ua.knu.vlasenko.DealerTCPClient;


import java.rmi.Naming;
import java.util.List;

public class DealerClient {
    public static void main(String[] args) {
        try {
	    Dealer dealer;

	    // Lab 2
	    // SQL: use Dealer interface to access SQL db
            dealer = DealerSQL.connect();
	    dealWithDealer(dealer);
	    dealer.done();

	    // Lab 3
	    // TCP: use Dealer interface through TCP
	    Thread tcpServerThread = new Thread(new DealerTCPServer(DealerSQL.connect()));
	    tcpServerThread.start();
	    tcpServerThread.sleep(1000);

            dealer = DealerTCPClient.connect();
	    dealWithDealer(dealer);
	    dealer.done();

	    // Lab 4
            // RMI: use Dealer interface through RMI
	    Thread rmiServerThread = new Thread(new DealerRMIServer(DealerSQL.connect()));
	    rmiServerThread.start();
	    rmiServerThread.sleep(1000);

            dealer = DealerRMIClient.connect();
	    dealWithDealer(dealer);
	    dealer.done();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dealWithDealer(Dealer dealer) {
        try {
            // Use the remote object to call the methods
            dealer.createMaker("Maker1");
            dealer.createMaker("Maker2");

            dealer.addModel("Maker1", "Model1", 2022, 160000);
            dealer.addModel("Maker1", "Model2", 2022, 275000);
            dealer.addModel("Maker2", "Model3", 2022, 800000);

            List<String> makerNames = dealer.getMakerNames();
            System.out.println("Maker Names: " + makerNames);

            int numberOfMakers = dealer.getNumberOfMakers();
            System.out.println("Number of Makers: " + numberOfMakers);

            int numberOfModelsMaker1 = dealer.getNumberOfModels("Maker1");
            System.out.println("Number of Models for Maker1: " + numberOfModelsMaker1);

            List<String> modelList = dealer.getModelList();
            System.out.println("Model List: " + modelList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
