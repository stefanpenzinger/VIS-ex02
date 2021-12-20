import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/*
 * Simple java client
 */
public class Client {

    private static IEnvService look_up;

    public static void main(String[] _args) {

        System.out.println("Do you want to log onto the server? (y/n)");
        Scanner s = new Scanner(System.in);
        String answer = s.nextLine();

        if (answer.equals("y") || answer.equals("Y") || answer.equals("yes") || answer.equals("Yes")) {
            try {
                int choice  = 7;

                String addr = "IEnvService";
                Registry reg = null;

                reg = LocateRegistry.getRegistry();
                IEnvService service = (IEnvService)reg.lookup(addr);

                do {
                    System.out.println("1. requestEnvironmentDataTypes()\n2. requestEnvironmentData(air)\n" +
                            "3. requestAll()\n4. saySomething()\n5. Exit");
                    answer = s.nextLine();
                    choice = Integer.parseInt(answer);

                    switch (choice) {
                        case(1):
                            String[] sensorTypes = service.requestEnvironmentDataTypes();

                            for (String sensorType:sensorTypes) {
                                System.out.println(sensorType);
                            }

                            break;
                        case(2):
                            EnvData data = service.requestEnvironmentData("air");
                            System.out.println(data);

                            break;
                        case(3):
                            EnvData[] sensorData = service.requestAll();

                            for (EnvData sensorDataEntry:sensorData) {
                                System.out.println(sensorDataEntry);
                            }

                            break;
                        case(4):
                            System.out.println(service.saySomething());

                            break;
                        case(5):
                            break;
                        default:
                            System.out.println("Invalid answer");
                    }

                } while (choice != 5);

                System.out.println("Shutting down");
                reg.unbind(addr);

            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Dann halt ned.");
        }
    }
}
