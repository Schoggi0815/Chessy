package ch.bbw;

public record Vector2(int x, int y) {
    public String toTextPos(){
        char character = (char) (x + 97);

        return String.valueOf(character) + (y + 1);
    }
}
