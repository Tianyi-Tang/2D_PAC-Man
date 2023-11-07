package cmpt276.group4;

import java.util.Objects;

public class Position{
    private int x_axis;
    private int y_axis;

    public Position(int x_axis,int y_axis){
        this.x_axis = x_axis;
        this.y_axis = y_axis;
    }

    public int getX_axis(){
        return x_axis;
    }

    public int getY_axis(){
        return y_axis;
    }

    public void addOnX_axis(int x_increment){
        x_axis += x_increment;
    }

    public void addOnY_axis(int y_increment){
        y_axis += y_increment;
    }

    public boolean equal(Position position){
        if(position.getX_axis() == x_axis && position.getY_axis() == y_axis)
            return true;
        else
            return false;
    }

    public void setPosition(Position position){
        x_axis = position.getX_axis();
        y_axis = position.getY_axis();
    }

    public void setX_axis(int x_axis){
        this.x_axis = x_axis;
    }

    public void setY_axis(int y_axis){
        this.y_axis = y_axis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x_axis == position.x_axis &&
               y_axis == position.y_axis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x_axis, y_axis);
    }


}
