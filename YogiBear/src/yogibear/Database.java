/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shokhjakhon
 */
public class Database {
    private final String tableName = "highscores";
    private final Connection conn;
    private final HashMap<String, Integer> highScores;
    
    public Database(){
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/yogibear?"
                    + "serverTimezone=UTC&user=student&password=asd123");
        } catch (Exception ex) {
            System.out.println("No connection");
        }
        this.conn = c;
        highScores = new HashMap<>();
        loadHighScores();
    }
    
    public boolean storeHighScore(String name, int newScore){
        return mergeHighScores(name, newScore, newScore > 0);
    }
    
    private void loadHighScores(){
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " ORDER BY " + "Score DESC");
            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int score = rs.getInt("Score");
                mergeHighScores(name, score, false);
            }
        } catch (Exception e){ System.out.println("loadHighScores error: " + e.getMessage());}
    }
    
    public ArrayList<HighScore> getHighScores(){
        HashMap<String, Integer> sorted = sortByValue(highScores);
        ArrayList<HighScore> scores = new ArrayList<>();
        int i = 0;
        for (String name : sorted.keySet()){
            if (i == 10) break;
            HighScore h = new HighScore(name, highScores.get(name));
            scores.add(h);
            i++;
        }
        return scores;
    }
    
    //https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
     public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        List<Map.Entry<String, Integer> > list =
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
         
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        Collections.reverse(list);
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    
    private boolean mergeHighScores(String name, int score, boolean store){
        boolean doUpdate = true;
        if (highScores.containsKey(name)){
            int oldScore = highScores.get(name);
            doUpdate = ((score > oldScore && score != 0) || oldScore == 0);
        }
        if (doUpdate){
            highScores.remove(name);
            highScores.put(name, score);
            if (store) return storeToDatabase(name, score) > 0;
            return true;
        }
        return false;
    }
    
    private int storeToDatabase(String name, int score){
        try (Statement stmt = conn.createStatement()){
            String s = "INSERT INTO " + tableName + 
                    " (Name, Score) " + 
                    "VALUES('" + name + "'," + score + 
                    ") ON DUPLICATE KEY UPDATE Score=" + score;
            return stmt.executeUpdate(s);
        } catch (Exception e){
            System.out.println("storeToDatabase error");
        }
        return 0;
    }
}
