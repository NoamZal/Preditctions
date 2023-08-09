package definition.property.impl;


import definition.property.api.AbstractPropertyDefinition;
import definition.property.api.PropertyType;
import definition.value.generator.api.ValueGenerator;

public class BooleanPropertyDefinition extends AbstractPropertyDefinition<Boolean> {

    public BooleanPropertyDefinition(String name, PropertyType propertyType, ValueGenerator<Boolean> valueGenerator) {
        super(name, propertyType.BOOLEAN, valueGenerator,null);
    }
}
