/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Shokhjakhon
 */
public class HighScoreTableModel extends AbstractTableModel {
    
    private final ArrayList<HighScore> highScores;
    private final String[] colName = new String[]{"Name", "Score" };
    
    public HighScoreTableModel(ArrayList<HighScore> highScores){
        this.highScores = highScores;
        System.out.println(this.highScores);
    }

    @Override
    public int getRowCount() { return highScores.size() < 10 ? highScores.size() : 10; }

    @Override
    public int getColumnCount() { return 2; }

    @Override
    public Object getValueAt(int r, int c) {
        HighScore h = highScores.get(r);
        if      (c == 0) return h.name;
        else if (c == 1) return h.score;
        return (h.score == 0) ? "0" : h.score;
    }

    @Override
    public String getColumnName(int i) { return colName[i]; }  
}
