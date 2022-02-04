package ch.bbw;

public record Vector2(int x, int y) {
    public String toTextPos(){
        char character = (char) (x + 97);

        return String.valueOf(character) + (y + 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2 vector2){

            return x == vector2.x && y == vector2.y;
        }

        return false;
    }
}
