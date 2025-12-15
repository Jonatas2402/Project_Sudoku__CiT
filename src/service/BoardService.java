package service;

import model.Board;
import model.GameStatus;
import model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardService {
    private final static int BOARD_LIMIT = 9;

    private final Board board;

    public BoardService(final Map<String, String> gameConfig) {
        this.board = new Board(initBoard(gameConfig));
    }

    public List<List<Space>> getSpaces() {
        return board.getSpaces();
    }

    public void reset() {
        board.reset();
    }

    public boolean hasErrors() {
        return board.hasErrors();
    }

    public GameStatus getStatus() {
        return board.getStatus();
    }

    public boolean gameIsFinished() {
        return board.gameIsFinished();
    }

    private List<List<Space>> initBoard(final Map<String, String> gameConfig) {
        List<List<Space>> spaces = new ArrayList<>();

        for (int row = 0; row < BOARD_LIMIT; row++) {
            spaces.add(new ArrayList<>());

            for (int col = 0; col < BOARD_LIMIT; col++) {
                Space space = createSpace(row, col, gameConfig);
                spaces.get(row).add(space);
            }
        }

        return spaces;
    }

    private Space createSpace(int row, int col, Map<String, String> gameConfig) {
        String key = row + "," + col;
        String config = gameConfig.get(key);

        // 👉 valor padrão caso não exista configuração
        if (config == null || config.isBlank()) {
            return new Space(0, false);
        }

        String[] parts = config.split(",");

        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Configuração inválida para a posição " + key + ": " + config
            );
        }

        int expected = parseIntSafe(parts[0], key);
        boolean fixed = Boolean.parseBoolean(parts[1]);

        return new Space(expected, fixed);
    }

    private int parseIntSafe(String value, String key) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Valor numérico inválido para a posição " + key + ": " + value
            );
        }
    }
}