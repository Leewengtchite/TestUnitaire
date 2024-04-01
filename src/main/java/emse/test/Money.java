package emse.test;

import java.util.Objects;

public class Money implements IMoney {
    private int fAmount;
    private String fCurrency;

    public Money(int amount, String currency) {
        fAmount = amount;
        fCurrency = currency;
    }

    public int amount() {
        return fAmount;
    }

    public String currency() {
        return fCurrency;
    }

    public String getCurrency() {
        return fCurrency;
    }

    public int getAmount() {
        return fAmount;
    }
    ///////////////////////////////////////work for the two first///////////////////////////////////////////////////
    public IMoney add(Money m){
       // return new Money(amount()+m.amount(),currency());
        if (m.currency().equals(currency()))
        return new Money(amount() + m.amount(), currency());
        else {
        return new MoneyBag(this, m);
        }
    }
     @Override
    public IMoney add(IMoney money) {
        if (money instanceof Money) {
            Money other = (Money) money;
            if (this.getCurrency().equals(other.fCurrency)) {
                return new Money(this.getAmount() + other.getAmount(), this.getCurrency());
            } else {
                return new MoneyBag(this, other);
            }
        } else {
            return money.add(this); 
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Money other = (Money) obj;
        return fAmount == other.fAmount && Objects.equals(fCurrency, other.fCurrency);
    }

}
