package controller;

import constants.GameStatus;
import model.Board;
import model.Game;
import model.Move;
import model.Player;
import service.winningStrategy.WinningStrategy;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players, WinningStrategy winningStrategy){
        return null;
    }
    public void displayBoard(Game game){
        game.getCurrentBoard().displayBoard();
    }

    public GameStatus getGameStatus(Game game){

        return null;
    }
    public Player getWinner(Game game){

        return null;
    }

    public Move executeMove(Game game, Player player){

        return null;
    }

    public Player checkWinner(Game game, Move lastMovePlayer){

        return null;
    }
    public Board undoMove(Game game,Move lastPlayerMove){

        return null;
    }
    public void replaygame(Game game){

    }
}
