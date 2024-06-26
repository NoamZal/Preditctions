package execution.instance.enitty;

import definition.entity.EntityDefinition;
import definition.world.impl.Coordinate;
import execution.instance.property.PropertyInstance;

import java.util.HashMap;
import java.util.Map;

public class EntityInstanceImpl implements EntityInstance {

    private final EntityDefinition entityDefinition;
    private final int id;
    private Map<String, PropertyInstance> properties;
    private Coordinate coordinate = new Coordinate(0,0);

    public EntityInstanceImpl(EntityDefinition entityDefinition, int id) {
        this.entityDefinition = entityDefinition;
        this.id = id;
        properties = new HashMap<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public PropertyInstance getPropertyByName(String name) {
        if (!properties.containsKey(name)) {
            throw new IllegalArgumentException("for entity of type " + entityDefinition.getName() + " has no property named " + name);
        }

        return properties.get(name);
    }

    @Override
    public void addPropertyInstance(PropertyInstance propertyInstance) {
        properties.put(propertyInstance.getPropertyDefinition().getName(), propertyInstance);
    }

    @Override
    public EntityDefinition getEntityDef() {
        return entityDefinition;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }
    @Override
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;

    }

    @Override
    public Map<String, PropertyInstance> getProperties() {
        return properties;
    }
}
