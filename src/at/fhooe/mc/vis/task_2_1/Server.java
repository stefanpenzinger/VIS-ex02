package at.fhooe.mc.vis.task_2_1;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements IEnvService {

    private static final long serialVersionUID = 1L;

    protected Server() throws RemoteException {
    }

    protected Server(int port) throws RemoteException {
        super(port);
    }

    protected Server(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    public static void main(String[] args){

        try {
            //Naming.rebind("//localhost/MyServer", new Server());
            Server server = new Server();
            Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            reg.rebind("DateService", server);
            System.err.println("Server ready");

        } catch (Exception e) {

            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }

    }

    @Override
    public String[] requestEnvironmentDataTypes() throws RemoteException {
        return new String[0];
    }

    @Override
    public EnvData requestEnvironmentData(String _type) throws RemoteException {
        return null;
    }

    @Override
    public EnvData[] requestAll() throws RemoteException {
        return new EnvData[0];
    }

    @Override
    public String saySomething() throws RemoteException {
        return "Cookies!";
    }
}
