package model;

import constants.GameStatus;
import constants.PlayerType;
import exception.InvalidPlayerException;
import exception.InvalidSymbolSetUpException;
import exception.InvalideBotCountException;
import service.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private Board currentBoard;
    private List<Player> players;
    private Player currentPlayer;
    private GameStatus gameStatus;
    private List<Move> moves;
    private List<Board> boardstates;
    private WinningStrategy winningStrategy;
    private int numberOfSymbols;


    private Game(Board currentBoard, List<Player> players, WinningStrategy winningStrategy) {
        this.currentBoard = currentBoard;
        this.players = players;
        this.currentPlayer = null;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.boardstates = new ArrayList<>();
        this.winningStrategy = winningStrategy;
        this.numberOfSymbols = players.size();
    }

    public static Builder builder(){

        return new Builder();
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Board> getBoardstates() {
        return boardstates;
    }

    public void setBoardstates(List<Board> boardstates) {
        this.boardstates = boardstates;
    }

    public WinningStrategy getWinningStrategy() {
        return winningStrategy;
    }

    public void setWinningStrategy(WinningStrategy winningStrategy) {
        this.winningStrategy = winningStrategy;
    }

    public int getNumberOfSymbols() {
        return numberOfSymbols;
    }

    public void setNumberOfSymbols(int numberOfSymbols) {
        this.numberOfSymbols = numberOfSymbols;
    }

    public static class Builder{
        private int dimension;
        private Board currentBoard;
        private List<Player> players;
        private Player currentPlayer;
        private WinningStrategy winningStrategy;

        public Builder setCurrentBoard(Board currentBoard) {
            this.currentBoard = currentBoard;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setCurrentPlayer(Player currentPlayer) {
            this.currentPlayer = currentPlayer;
            return this;
        }

        public Builder setWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        private void validateNumberOfPlayers(){
            //N,no bot ->players=N-1
            //N,bot -> players=N-2
            if(players.size()< currentBoard.getDimension()-2 || players.size()>=currentBoard.getDimension()){
                throw new InvalidPlayerException("Player size should N-1 or N-2 as per Board size");
            }

        }
        private void validatePlayerSymbols(){
            HashSet<Character> symbols = new HashSet<>();
            for(Player player:players){
                symbols.add(player.getSymbol());
            }
            if(symbols.size()!= players.size()){
                throw new InvalidSymbolSetUpException("There should be unique symbols for all the players");
            }
        }

        private void validateBotCount(){
            int botCount=0;
            for(Player player:players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount>1||botCount<0){
                throw new InvalideBotCountException("We can have maximum 1 bot per game");
            }
        }

        private void validate(){
            validateBotCount();
            validateNumberOfPlayers();
            validatePlayerSymbols();
        }

        public Game build(){
            validate();
            return new Game(new Board(dimension),players,winningStrategy);
        }

    }
}
