package at.fhooe.mc.vis.task_2_1;

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
        boolean validAnswer = false;

        do {
            System.out.println("Do you want to log onto the server? (y/n)");
            Scanner s = new Scanner(System.in);
            String answer = s.nextLine();

            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
                validAnswer = true;

                try {
                    int choice  = 6;

                    String addr = "DateService";

                    Registry reg = LocateRegistry.getRegistry();
                    IEnvService service = (IEnvService)reg.lookup(addr);

                    do {
                        System.out.println("""
                                1. requestEnvironmentDataTypes()
                                2. requestEnvironmentData()
                                3. requestAll()
                                4. saySomething()
                                5. Exit""");
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

                } catch (RemoteException | NotBoundException e) {
                    e.printStackTrace();
                }
            }
            else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
                System.out.println("Dann halt ned.");
                validAnswer = true;
            }
            else {
                System.out.println("Please enter a valid answer.");
            }
        } while (!validAnswer);
    }
}
