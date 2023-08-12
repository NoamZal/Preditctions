package definition.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import definition.property.api.PropertyDefinition;

public class EntityDefinitionImpl implements EntityDefinition {

    private final String name;
    private final int population;
    private final Map<String,PropertyDefinition> properties;

    public EntityDefinitionImpl(String name, int population) {
        this.name = name;
        this.population = population;
        properties = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPopulation() {
        return population;
    }

    @Override
    public Map<String,PropertyDefinition> getProps() {
        return properties;
    }

    @Override
    public void addPropertyDefinition(PropertyDefinition propertyDefinition) {
        properties.put(propertyDefinition.getName(),propertyDefinition);
    }

}
