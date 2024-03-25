package service.winningStrategy;

import model.Board;
import model.Cell;
import model.Move;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy{
    private int dimension;
    private List<HashMap<Character,Integer>> rowHashmapList;//Index in the list will correspond to row number for hashmap identification
    private List<HashMap<Character,Integer>> columnHashmapList; //Index in the list will correspond to column number for hashmap identification
    private HashMap<Character,Integer> leftDiagonal;
    private HashMap<Character,Integer> rightDiagonal;

    public OrderOneWinningStrategy(int dimension) {
        this.dimension = dimension;
        rowHashmapList = new ArrayList<>();
        columnHashmapList = new ArrayList<>();
        rightDiagonal = new HashMap<>();
        leftDiagonal = new HashMap<>();

        for(int i=0;i<dimension;i++){
            rowHashmapList.add(new HashMap<>());
            columnHashmapList.add(new HashMap<>());
        }

    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        Player player = lastMove.getPlayer();
        char symbol = player.getSymbol();
        int row = lastMove.getCell().getRow();
        int column = lastMove.getCell().getColumn();

        boolean winnerresult = winningCheckforCorners(board.getMatrix(),symbol) ||
                checkAndUpdateforRowhashmap(row,symbol)||
                checkAndUpdateforColumnhashmap(column,symbol)||
                (checkleftDiagonal(row,column) && checkAndUpdateforleftDiagonal(symbol))||
                (checkRightDiagonal(row,column) && checkAndUpdateforrightDiagonal(symbol));
        if(winnerresult==true){
            return player;
        }else{
            return null;
        }

    }
    private boolean checkleftDiagonal(int row, int col){
        return row==col;
    }
    private boolean checkRightDiagonal(int row, int col){
        if((row+col)==dimension-1){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean checkAndUpdateforRowhashmap(int row , char symbol){
        HashMap<Character,Integer> rowHasMap = rowHashmapList.get(row);
        if(rowHasMap.containsKey(symbol)){
            rowHasMap.put(symbol,rowHasMap.get(symbol)+1);
            return rowHasMap.get(symbol)==dimension;
        }else{
            rowHasMap.put(symbol,1);
        }
        return false;
    }

    private boolean checkAndUpdateforColumnhashmap(int row , char symbol){
        HashMap<Character,Integer> colHasMap = columnHashmapList.get(row);
        if(colHasMap.containsKey(symbol)){
            colHasMap.put(symbol,colHasMap.get(symbol)+1);
            return colHasMap.get(symbol)==dimension;
        }else{
            colHasMap.put(symbol,1);
        }
        return false;
    }

    private boolean checkAndUpdateforleftDiagonal(char symbol){
        if(leftDiagonal.containsKey(symbol)){
            leftDiagonal.put(symbol,leftDiagonal.get(symbol)+1);
            return leftDiagonal.get(symbol)==dimension;
        }else{
            leftDiagonal.put(symbol,1);
        }
        return false;
    }

    private boolean checkAndUpdateforrightDiagonal(char symbol){
        if(rightDiagonal.containsKey(symbol)){
            rightDiagonal.put(symbol,rightDiagonal.get(symbol)+1);
            return rightDiagonal.get(symbol)==dimension;
        }else{
            rightDiagonal.put(symbol,1);
        }
        return false;
    }


    private boolean winningCheckforCorners(List<List<Cell>> matrix, char symbol){
        return ((matrix.get(0).get(0).getPlayer().getSymbol()==symbol)
            &&(matrix.get(0).get(dimension-1).getPlayer().getSymbol()==symbol)
                &&(matrix.get(dimension-1).get(0).getPlayer().getSymbol()==symbol)
                &&(matrix.get(dimension-1).get(dimension-1).getPlayer().getSymbol()==symbol)
        );

    }
}
/*
  Board Size - N
  No of hashmaps required are 2N +2
  no of hashmaps for rows - N
  no of hashmaps for columns - N
  left diagonal - 1
  right diagonal -1
 */