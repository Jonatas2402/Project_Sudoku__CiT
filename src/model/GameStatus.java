package model;

public enum GameStatus {
    NON_STARTED("Não iniciado"),
    INCOMPLETE("Incomplete"),
    COMPLETE("Complete");

    private String label;

    GameStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
