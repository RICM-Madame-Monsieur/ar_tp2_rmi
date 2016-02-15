package com.ar.server;

import com.ar.common.ISupplier;
import com.ar.common.Supplier;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.*;

/**
 * @author morat 
 */
public class Server {

  /**
   * @param args
   */
  public static void main(final String args[]) {
    //System.setProperty("java.security.policy", ".policy");

    String nom="";
    int nombre=1; int port = 1099;
    Registry registry=null;

    // récupération des arguments
    if (args.length!=3){
      System.out.println("Server <nom générique des objets distants> <port du registry> <nombre de noms>");
      System.exit(1);
    }

    try  {
      nom = args[0];
      port = Integer.parseInt(args[1]);
      nombre = Integer.parseInt(args[2]);
    }catch(Exception e) {
      System.out.println("Server <nom générique des objets distants> <port du registry> <nombre de noms>");
      System.exit(1);
    }

    // installation d'un securityManager
    // A COMPLETER : INSTALLATIOND'UN SECURITYMANAGER
    if(System.getSecurityManager() == null){
        System.setSecurityManager(new SecurityManager());
    }

    // A COMPLETER : MISE EN PLACE DU REGISTRY
    try {
      LocateRegistry.createRegistry(port);

      //ArrayList<ISupplier> suppliers = new ArrayList<>();
      for(int i=1;i<=nombre;i++){
      	// A COMPLETER : CONSTRUCTION ET EXPORTATION DES OBJETS DISTANTS
        ISupplier supplier = new Supplier(nom, i);

        // enregistre l'objet auprès du registry
        Naming.bind("//:"+port+"/"+nom+i, supplier);
        //suppliers.add(supplier);
      }
      System.out.println("Tous les objets sont enregistrés dans le serveur d'objets distants");
    } catch (Exception e) {
      System.out.println("Server err: " + e);
    }
  }
}
