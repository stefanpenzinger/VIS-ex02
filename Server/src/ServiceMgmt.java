import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class ServiceMgmt {

    enum ServiceCommands { start, stop, quit }

    public static void main(String[] _args) {

        System.out.println("System commands: ");
        System.out.println(Arrays.toString(ServiceCommands.values()));

        try{
            Server server = new Server();
            Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            do {
                System.out.print("\nInput command: ");
                // Enter data using BufferReader
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                // Reading data using readLine
                ServiceCommands command = ServiceCommands.valueOf(reader.readLine());

                switch (command) {
                    case start -> {
                        reg.rebind("IEnvService", server);
                        System.out.println("Server ready");
                    }
                    case stop -> {
                        reg.unbind("IEnvService");
                        UnicastRemoteObject.unexportObject(server, true);
                        System.out.println("Server stopped");
                    }
                    case quit -> {
                        reg.unbind("IEnvService");
                        System.exit(0);
                    }
                }
            } while (true);
        } catch (Exception e) {
            System.err.println("Exception: " + e);
            e.printStackTrace();
        }
        finally {
            System.out.println("Shutdown...");
            System.exit(0);
        }
    }
}
