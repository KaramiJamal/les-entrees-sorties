package org.example;

import java.util.ArrayList;
import java.util.List;

public class MetierProduitImpl implements IMetier<Produit, Integer>{

    private List<Produit> produits = new ArrayList<>();
    @Override
    public Produit add(Produit o) {
        if(o!= null){
            this.produits.add(o);
            return o;
        }
        return null;
    }

    @Override
    public List<Produit> getAll() {
        return produits;
    }

    @Override
    public Produit findById(Integer id) {
        for (int i = 0; i < produits.size(); i++) {
            if(produits.get(i).getId() == id){
                return produits.get(i);
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        for (int i = 0; i < produits.size(); i++) {
            if(produits.get(i).getId() == id){
                produits.remove(i);
            }
        }
    }

    @Override
    public void saveAll() {

    }
}
