package definition.property.api;

public interface PropertyDefinition {
    String getName();
    PropertyType getType();
    Object generateValue();
    Range getRange();
    Boolean getRandomInit();

}