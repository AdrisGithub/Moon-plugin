package de.wzrd.PlayerDataBase;

public class PlayerInfos {

    private long currency;


    public PlayerInfos(){
        currency = 0;
    }
    public PlayerInfos(long currency){
        this.currency = currency;
    }

    public long getCurrency() {
        return currency;
    }
    public void addCurrency(long amount){
        setCurrency(currency+amount);
    }
    public void setCurrency(long amount){
        currency = amount;
    }
}
