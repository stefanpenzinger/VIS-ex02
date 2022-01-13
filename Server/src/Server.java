import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI Server class
 *
 */
public class Server extends UnicastRemoteObject implements IEnvService {

    /** Array of EnvData to store and access the different Sensors available */
    EnvData[] mSensors = new EnvData[3];

    /**
     * Constructor creating a Server Object and initializing new EnvData Objects
     * @throws RemoteException
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

    /**
     * returns all available types of sensors
     *
     * @return String[]
     * @throws RemoteException
     */
    @Override
    public String[] requestEnvironmentDataTypes() throws RemoteException {
        String[] rv = new String[EnvData.EnvDataTypes.values().length];
        for (EnvData.EnvDataTypes type : EnvData.EnvDataTypes.values()) {
            rv[type.ordinal()] = type.toString();
        }
        return rv;
    }

    /**
     * returns an EnvData object according to the given senor type (_type)
     *
     * @param _type the type of the targeted sensor
     * @return EnvData
     * @throws RemoteException
     */
    @Override
    public EnvData requestEnvironmentData(String _type) throws RemoteException {
        EnvData.EnvDataTypes type = EnvData.EnvDataTypes.valueOf(_type);
        EnvData data = mSensors[type.ordinal()];
        data.seedSensorData();
        return data;
    }

    /**
     * return an Array of all available sensor data
     *
     * @return EnvData[]
     * @throws RemoteException
     */
    @Override
    public EnvData[] requestAll() throws RemoteException {
        for (EnvData d : mSensors) { d.seedSensorData(); }
        return mSensors;
    }

    /**
     * just returns "Cookies" text for testing purposes
     *
     * @return String ("Cookies")
     * @throws RemoteException
     */
    @Override
    public String saySomething() throws RemoteException {
        return "Cookies!";
    }
}
