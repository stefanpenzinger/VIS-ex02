package at.fhooe.mc.vis.task_2_1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/*
 * Simple java client
 */
public class Client {

    private static IEnvService look_up;

    public static void main(String[] args)
            throws MalformedURLException, RemoteException, NotBoundException {

        look_up = (IEnvService) Naming.lookup("//localhost/MyServer");

        //String response = look_up.saySomething();
    }
}
