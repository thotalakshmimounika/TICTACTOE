import constants.BotDifficultyLevel;
import constants.GameStatus;
import constants.PlayerType;
import controller.GameController;
import model.Bot;
import model.Game;
import model.Move;
import model.Player;
import service.winningStrategy.WinningStrategyName;

import java.util.*;

public class TicTacToe {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        int id =1;
        List<Player> playerList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to ticTactoe Game ");
        System.out.println("Please enter the dimension for the board");
        int dimension = sc.nextInt();
        System.out.println("Do you want bot in the game ? Y or N");
        String botAns = sc.next();
        if(botAns.equalsIgnoreCase("Y")){
            Player bot = new Bot(id++,'$',BotDifficultyLevel.HARD);
            playerList.add(bot);
        }
        while (id<dimension){
            System.out.println("Please enter the Player name : ");
            String playerName = sc.next();
            System.out.println("Pleas enter the Plyer Symbol : ");
            char playerSymbol = sc.next().charAt(0);
            Player newplayer = new Player(id++,playerName,playerSymbol, PlayerType.HUMAN);
            playerList.add(newplayer);
        }
        Collections.shuffle(playerList);
        Game game = gameController.createGame(dimension,playerList, WinningStrategyName.ORDERONEWINNINGSTRATEGY);
        int playerindex = -1;
        while(game.getGameStatus().equals(GameStatus.IN_PROGRESS)){
            System.out.println("Current board Status");
            gameController.displayBoard(game);
            playerindex++;
            playerindex = playerindex% playerList.size();
            Move movePlayed = gameController.executeMove(game,playerList.get(playerindex));
            Player winner = gameController.checkWinner(game,movePlayed);
            if(winner!=null){
                System.out.println("WINNER IS : "+winner.getName());
                break;
            }
        }
        System.out.println("Final Board Status : ");
        gameController.displayBoard(game);
        System.out.println("Do you want to replay ? Y or N");

    }
}