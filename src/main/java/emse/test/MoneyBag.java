/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emse.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

/**
 *
 * @author leewt
 */
public class MoneyBag implements IMoney {

    private Vector<Money> fMonies = new Vector<Money>();
    
    public MoneyBag() {

}

//    }
     public MoneyBag(Money... monies) {
        for (Money m : monies) {
            appendMoney(m);
        }
    }

     private void appendMoney(Money m) {
        if (fMonies.isEmpty()) {
            fMonies.add(m);
        } else {
            boolean found = false;
            for (Money money : fMonies) {
                if (money.currency().equals(m.currency())) {
                    money = new Money(money.amount() + m.amount(), m.currency());
                    found = true;
                    break;
                }
            }
            if (!found) {
                fMonies.add(m);
            }
        }
    }

    public Vector<Money> getfMonies() {
        return fMonies;
    }

    public void setfMonies(Vector<Money> fMonies) {
        this.fMonies = fMonies;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    MoneyBag other = (MoneyBag) o;
    if (fMonies.size() != other.fMonies.size()) {
        return false;
    }
    // Vérifier si les deux MoneyBag ont la même somme totale dans la même devise
    int totalAmount = 0;
    for (Money money : fMonies) {
        totalAmount += money.amount();
    }
    int otherTotalAmount = 0;
    for (Money otherMoney : other.fMonies) {
        otherTotalAmount += otherMoney.amount();
    }
    if (totalAmount != otherTotalAmount || !currency().equals(other.currency())) {
        return false;
    }
    // Vérifier si les deux MoneyBag ont le même contenu
    for (Money money : fMonies) {
        boolean found = false;
        for (Money otherMoney : other.fMonies) {
            if (money.amount() == otherMoney.amount() && money.currency().equals(otherMoney.currency())) {
                found = true;
                break;
            }
        }
        if (!found) {
            return false;
        }
    }
    return true;
}

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 29 * hash + Objects.hashCode(this.fMonies);
//        return hash;
//    }
public String currency() {
    if (fMonies.isEmpty()) {
        throw new IllegalStateException("Cannot determine currency of an empty MoneyBag");
    } else {
        // Initialiser un compteur pour chaque devise
        Map<String, Integer> currencyCount = new HashMap<>();
        for (Money money : fMonies) {
            String currency = money.currency();
            currencyCount.put(currency, currencyCount.getOrDefault(currency, 0) + 1);
        }

        // Trouver la devise la plus courante
        String mostCommonCurrency = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : currencyCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostCommonCurrency = entry.getKey();
                maxCount = entry.getValue();
            } else if (entry.getValue() == maxCount) {
                
                mostCommonCurrency = entry.getKey();
            }
        }
        // Retourner la devise la plus courante
        return mostCommonCurrency;
    }
}

    /////////////////////////////////////////////work forst two one backup//////////////////////////////////////
public IMoney add(MoneyBag bag) {
    // Vérifie si les devises des deux MoneyBag sont les mêmes
    if (this.currency().equals(bag.currency())) {
        // Si les devises sont identiques, combinez simplement les deux MoneyBag (smplification)
Money totalMoney = (Money) this.calculateTotalMoney().add(bag.calculateTotalMoney());
        return totalMoney;
    } else {
        // Si les devises sont différentes, créez un nouveau MoneyBag contenant les deux
        Vector<Money> combinedMonies = new Vector<>();
        combinedMonies.addAll(this.getfMonies());
        combinedMonies.addAll(bag.getfMonies());
        return new MoneyBag(combinedMonies.toArray(new Money[0]));
    }
}

//public IMoney add(Money m) {
//    // Recherche d'un Money avec la même devise dans le MoneyBag
//    for (int i = 0; i < fMonies.size(); i++) {
//        Money existingMoney = fMonies.get(i);
//        if (existingMoney.currency().equals(m.currency())) {
//            // Si une devise correspondante est trouvée, ajoutez simplement le montant
//            fMonies.set(i, new Money(existingMoney.amount() + m.amount(), m.currency()));
//            return this; // Retourne le MoneyBag modifié
//        }
//    }
//    // Si aucune devise correspondante n'est trouvée, créez un nouveau MoneyBag avec le Money ajouté
//    Vector<Money> newMonies = new Vector<>(fMonies);
//    newMonies.add(m);
//    return new MoneyBag(newMonies.toArray(new Money[0]));
//}

public IMoney add(Money m) {
    // Recherche d'un Money avec la même devise dans le MoneyBag
    for (int i = 0; i < fMonies.size(); i++) {
        Money existingMoney = fMonies.get(i);
        if (existingMoney.currency().equals(m.currency())) {
            // Si une devise correspondante est trouvée, ajoutez simplement le montant
            int newAmount = existingMoney.amount() + m.amount();
            if (newAmount > 0) {
                fMonies.set(i, new Money(newAmount, m.currency()));
                // S'il n'y a qu'un seul type de devise, retournez un objet Money au lieu d'un MoneyBag
                if (fMonies.size() == 1) {
                    return fMonies.get(0);
                } else {
                    return this; // Retourne le MoneyBag modifié
                }
            } else {
                fMonies.remove(i); // Supprimez le Money si le montant est nul ou négatif
                // S'il n'y a qu'un seul type de devise, retournez un objet Money au lieu d'un MoneyBag
                if (fMonies.size() == 1) {
                    return fMonies.get(0);
                } else {
                    return this; // Retourne le MoneyBag modifié
                }
            }
        }
    }
    // Si aucune devise correspondante n'est trouvée, créez un nouveau MoneyBag avec le Money ajouté
    if (m.amount() > 0) {
        Vector<Money> newMonies = new Vector<>(fMonies);
        newMonies.add(m);
        return new MoneyBag(newMonies.toArray(new Money[0]));
    }
    // S'il n'y a qu'un seul type de devise, retournez un objet Money au lieu d'un MoneyBag
    if (fMonies.size() == 1) {
        return fMonies.get(0);
    } else {
        return this; // Retourne le MoneyBag original si le Money ajouté a un montant nul ou négatif
    }
}
@Override
public IMoney add(IMoney money) {
    if (money instanceof Money) {
        appendMoney((Money) money);
    } else if (money instanceof MoneyBag) {
        MoneyBag other = (MoneyBag) money;
        if (this.currency().equals(other.currency())) {
            // Si les devises sont identiques, combinez simplement les montants des deux MoneyBag
            Vector<Money> combinedMonies = new Vector<>(this.getfMonies());
            for (Money m : other.getfMonies()) {
                combinedMonies = combineSameCurrencyMonies(combinedMonies, m);
            }
            return new MoneyBag(combinedMonies.toArray(new Money[0]));
        } else {
            // Si les devises sont différentes, créez un nouveau MoneyBag contenant les deux
            Vector<Money> combinedMonies = new Vector<>(this.getfMonies());
            combinedMonies.addAll(other.getfMonies());
            return new MoneyBag(combinedMonies.toArray(new Money[0]));
        }
    }
    return this;
}

private Vector<Money> combineSameCurrencyMonies(Vector<Money> monies, Money newMoney) {
    boolean found = false;
    for (int i = 0; i < monies.size(); i++) {
        Money existingMoney = monies.get(i);
        if (existingMoney.currency().equals(newMoney.currency())) {
            monies.set(i, new Money(existingMoney.amount() + newMoney.amount(), newMoney.currency()));
            found = true;
            break;
        }
    }
    if (!found) {
        monies.add(newMoney);
    }
    return monies;
}
//private MoneyBag combineSameCurrencyMonies(MoneyBag bag) {
//    MoneyBag newBag = new MoneyBag();
//    
//    // Ajoute tous les montants du premier MoneyBag
//    for (Money money : this.getfMonies()) {
//        newBag.appendMoney(money);
//    }
//    
//    // Ajoute les montants du deuxième MoneyBag
//    for (Money money : bag.getfMonies()) {
//        newBag.appendMoney(money);
//    }
//    
//    return newBag;
//}

// Méthode pour calculer la somme totale des montants dans ce MoneyBag et retourner un Money avec la devise commune
  public Money calculateTotalMoney() {
        int totalAmount = 0;
        for (Money money : fMonies) {
            totalAmount += money.amount();
        }
        return new Money(totalAmount, currency());
    }

}