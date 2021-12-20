package at.fhooe.mc.vis.task_2_1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*
 * Simple java client
 */
public class Client {

    private static IEnvService look_up;

    public static void main(String[] args)
            throws MalformedURLException, RemoteException, NotBoundException {
        String addr = "DateService";
        Registry reg = LocateRegistry.getRegistry();
        IEnvService service = (IEnvService)reg.lookup(addr);

        String response = service.saySomething();
        System.out.println(response);
    }
}
