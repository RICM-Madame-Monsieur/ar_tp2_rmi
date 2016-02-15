package com.ar.common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by matthieu on 05/02/16.
 */
public class Supplier extends UnicastRemoteObject implements ISupplier {

    private String name;
    private int num;

    public Supplier(String name, int num) throws RemoteException {
        super();
        this.name = name;
        this.num = num;
    }

    @Override
    public String question() throws RemoteException {
        return System.getProperty("os.name");
    }

    @Override
    public String question(String s) throws RemoteException {
        return System.getProperty(s);
    }

    @Override
    public String name() throws RemoteException {
        return name+num;
    }

    @Override
    public String toString() {
        return "supplier"+num;
    }
}
