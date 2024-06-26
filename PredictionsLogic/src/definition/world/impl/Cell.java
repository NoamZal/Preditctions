package definition.world.impl;

import execution.instance.enitty.EntityInstance;

public class Cell {

    private Coordinate coordinate;
    private Boolean isOccupied;
    private EntityInstance entityInstance;

    public Cell(Coordinate coordinate, Boolean isOccupied, EntityInstance entityInstance){
        this.coordinate = coordinate;
        this.isOccupied = isOccupied;
        this.entityInstance = entityInstance;
    }
    public Boolean getIsOccupied(){
        return isOccupied;
    }
    public Coordinate getCoordinate(){
        return  coordinate;
    }
    public EntityInstance getEntityInstance(){return entityInstance;}

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied= isOccupied;
    }
    public void setEntityInstance(EntityInstance entityInstance) {
        this.entityInstance = entityInstance;
    }
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate= coordinate;
    }
}
