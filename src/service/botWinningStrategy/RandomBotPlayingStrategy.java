package service.botWinningStrategy;

import constants.CellState;
import exception.GameOverException;
import model.Board;
import model.Cell;
import model.Move;
import model.Player;

import java.util.List;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board, Player bot) {
        List<List<Cell>> matrix = board.getMatrix();
        for(int i=0;i<matrix.size();i++) {
            for (int j = 0; j < matrix.size(); j++) {
                if (matrix.get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    board.getMatrix().get(i).get(j).setPlayer(bot);
                    board.getMatrix().get(i).get(j).setCellState(CellState.FILLED);
                    return new Move(board.getMatrix().get(i).get(j), bot);
                }
            }
        }
        throw new GameOverException("There are no empty cells in the board");
    }
}
