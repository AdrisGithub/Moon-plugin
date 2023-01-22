package de.wzrd.PlayerDataBase;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerHandler {

    private static Map<UUID,PlayerInfos> database;

    public static void init(){
        database = new HashMap<>();
    }
    public static void addPlayer(UUID key){
        File file = new File("plugins/Moon-Configs/"+key);
        try {
            if(file.createNewFile()){
                database.put(key,new PlayerInfos());
                savePlayerInfos(key);
            }else{
                database.put(key,getPlayerInfosFromFile(file));
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read Player Data");
        }
    }

    private static PlayerInfos getPlayerInfosFromFile(File file) throws IOException {
        BufferedReader read = new BufferedReader(new FileReader(file));
        return new PlayerInfos(Long.parseLong(read.readLine()));
    }

    public static void leavePlayer(UUID key){
        try {
            savePlayerInfos(key);
        }catch (IOException io){
            throw new RuntimeException("Couldn't save Player data");
        }
        database.remove(key);
    }
    public static void savePlayerInfos(UUID key) throws IOException {
        Writer writer = new FileWriter("plugins/Moon-Configs/"+key,false);
        writer.write(database.get(key).getCurrency()+"");
        writer.close();
    }
    public static Long getBalanceFrom(UUID key) throws IOException {
        if(database.containsKey(key)){
            return database.get(key).getCurrency();
        }
        File file = new File("plugins/Moon-Configs/"+key);
        if(!file.exists()) throw new IOException();
        return getPlayerInfosFromFile(file).getCurrency();
    }
    public static void setBalanceFrom(UUID key,long amount) throws IOException {
        if(database.containsKey(key)){
            database.get(key).setCurrency(amount);
        }else{
            File file = new File("plugins/Moon-Configs/"+key);
            if(!file.exists()) throw new IOException();
            BufferedWriter write = new BufferedWriter(new FileWriter(file,false));
            write.write(amount+"");
            database.put(key,new PlayerInfos(amount));
        }
    }
    public static void addBalanceFrom(UUID key,long amount) throws IOException {
        setBalanceFrom(key,getBalanceFrom(key)+amount);
    }
    public static void subBalanceFrom(UUID key,long amount) throws IOException {
        long currency = getBalanceFrom(key);
        if(amount>currency)throw new IOException();
        setBalanceFrom(key,(currency-amount));
    }
    public static boolean hasEnoughMoney(UUID key,long amount){
        if(!database.containsKey(key)) return false;
        return (database.get(key).getCurrency()>=amount);
    }



}
