import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements IEnvService {

    /** Array of EnvData to store and access the different Sensors available */
    EnvData[] mSensors = new EnvData[3];

    /**
     * Constructor creating a Server Object and initializing new EnvData Objects
     * @throws RemoteException Throws a remote exception
     */
    protected Server() throws RemoteException {
       initalizesSensors();
    }

    protected Server(int port) throws RemoteException {
        super(port);
        initalizesSensors();
    }

    protected Server(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
        initalizesSensors();
    }

    /**
     * Method used in the constructor to initialize the different Sensors
     * and store them into the mSensors array of EnvData.
     */
    private void initalizesSensors(){
        mSensors[EnvData.EnvDataTypes.air.ordinal()] = new EnvData(EnvData.EnvDataTypes.air);
        mSensors[EnvData.EnvDataTypes.light.ordinal()] = new EnvData(EnvData.EnvDataTypes.light);
        mSensors[EnvData.EnvDataTypes.noise.ordinal()] = new EnvData(EnvData.EnvDataTypes.noise);
    }

<<<<<<< HEAD
    public static void main(String[] _args){
        try {
            Server server = new Server();
            Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            reg.rebind("DateService", server);
            System.err.println("Server ready");

        } catch (Exception e) {

            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }

=======
>>>>>>> serviceMgmt
=======
>>>>>>> 1d60ae517593695790c4272a1ccfd48db44f6bba
    @Override
    public String[] requestEnvironmentDataTypes() throws RemoteException {
        String[] rv = new String[EnvData.EnvDataTypes.values().length];
        for (EnvData.EnvDataTypes type : EnvData.EnvDataTypes.values()) {
            rv[type.ordinal()] = type.toString();
        }
        return rv;
    }

    @Override
    public EnvData requestEnvironmentData(String _type) throws RemoteException {
        EnvData.EnvDataTypes type = EnvData.EnvDataTypes.valueOf(_type);
        EnvData data = mSensors[type.ordinal()];
        data.seedSensorData();
        return data;
    }

    @Override
    public EnvData[] requestAll() throws RemoteException {
        for (EnvData d : mSensors) { d.seedSensorData(); }
        return mSensors;
    }

    @Override
    public String saySomething() throws RemoteException {
        return "Cookies!";
    }
}
