package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        IMetier<Produit, Integer> metier = new MetierProduitImpl();

        String choice = "0";
        while (!choice.equals("6")) {
            System.out.print("###########################\n\n");
            System.out.println("1. Afficher la liste des produits.\n" + "2. Rechercher un produit par son id.\n" + "3. Ajouter un nouveau produit dans la liste.\n" + "4. Supprimer un produit par id.\n" + "5. Sauvegarder les produits\n" + "6. Quitter ce programme.");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    metier.getAll().forEach(produit -> System.out.println(produit));
                    break;
                case "2":
                    System.out.print("Entrer l'id du produit : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Produit p = metier.findById(id);
                    if (p != null) {
                        System.out.println(p);
                    } else {
                        System.out.println("produit avec l'id " + id + " est inexistant");
                    }
                    break;
                case "4":
                    System.out.print("Taper l'id du produit : ");
                    int value = scanner.nextInt();scanner.nextLine();
                    Produit pr = metier.findById(value);
                    if (pr != null){
                        metier.delete(pr.getId());
                        System.out.println("Produit supprimé");
                    }else{
                        System.out.println("Produit inexistant");
                    }
                    break;
                case "3":
                    System.out.print("Taper le nom du produit : ");
                    String name = scanner.nextLine();
                    System.out.print("Taper la marque du produit : ");
                    String marque = scanner.nextLine();
                    System.out.print("Taper le prix du produit : ");
                    double prix = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Taper la description du produit : ");
                    String description = scanner.nextLine();
                    System.out.print("Taper le stock du produit : ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    List<Produit> produitList = metier.getAll();
                    int idP = 0;
                    if (produitList.size() > 0) {
                        idP = produitList.get((produitList.size() - 1)).getId();
                    }
                    Produit produit = new Produit(idP, name, marque, prix, description, stock);
                    metier.add(produit);
                    System.out.println("Produit ajouté avec succès");
                    break;
                case "5":
                    try {
                        FileOutputStream fout = new FileOutputStream("produits.dat");
                        ObjectOutputStream oos = new ObjectOutputStream(fout);
                        metier.getAll().forEach(produit1 -> {
                            try {
                                oos.writeObject(produit1);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        System.out.printf("Les données ont été sauvegardés");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    break;
                case "6":
                    break;
                default:
                    System.out.print("Le choix est invalid");

            }
        }
    }
}