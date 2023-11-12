package cmpt276.group4;

import java.util.Objects;

/**
 * Represents a position in a two-dimensional space with x and y coordinates.
 */
public class Position {
    private int x_axis;
    private int y_axis;

    /**
     * Constructs a new Position with specified x and y coordinates.
     *
     * @param x_axis The x-coordinate of the position.
     * @param y_axis The y-coordinate of the position.
     */
    public Position(int x_axis, int y_axis) {
        this.x_axis = x_axis;
        this.y_axis = y_axis;
    }

    /**
     * Gets the x-coordinate of this position.
     *
     * @return The x-coordinate.
     */
    public int getX_axis() {
        return x_axis;
    }

    /**
     * Gets the y-coordinate of this position.
     *
     * @return The y-coordinate.
     */
    public int getY_axis() {
        return y_axis;
    }

    /**
     * Increments the x-coordinate by a specified amount.
     *
     * @param x_increment The amount to increment the x-coordinate.
     */
    public void addOnX_axis(int x_increment) {
        x_axis += x_increment;
    }

    /**
     * Increments the y-coordinate by a specified amount.
     *
     * @param y_increment The amount to increment the y-coordinate.
     */
    public void addOnY_axis(int y_increment) {
        y_axis += y_increment;
    }

    /**
     * Checks if this position is equal to another position.
     *
     * @param position The position to compare with.
     * @return true if both positions have the same x and y coordinates.
     */
    public boolean equal(Position position) {
        return position.getX_axis() == x_axis && position.getY_axis() == y_axis;
    }

    /**
     * Sets the x and y coordinates of this position.
     *
     * @param position The position whose coordinates will be copied.
     */
    public void setPosition(Position position) {
        x_axis = position.getX_axis();
        y_axis = position.getY_axis();
    }

    /**
     * Sets the x-coordinate of this position.
     *
     * @param x_axis The new x-coordinate.
     */
    public void setX_axis(int x_axis) {
        this.x_axis = x_axis;
    }

    /**
     * Sets the y-coordinate of this position.
     *
     * @param y_axis The new y-coordinate.
     */
    public void setY_axis(int y_axis) {
        this.y_axis = y_axis;
    }

    /**
     * Compares this position to another object for equality.
     *
     * @param o The object to compare with.
     * @return true if the other object is a Position with the same x and y coordinates.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x_axis == position.x_axis &&
               y_axis == position.y_axis;
    }

    /**
     * Returns a hash code value for this position.
     *
     * @return A hash code value for this position.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x_axis, y_axis);
    }
}
