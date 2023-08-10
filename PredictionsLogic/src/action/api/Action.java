package action.api;

import definition.entity.EntityDefinition;
import execution.context.Context;
import execution.instance.property.PropertyInstance;
import expression.api.Expression;

public interface Action {
    void invoke(Context context);
    ActionType getActionType();
    EntityDefinition getContextEntity();
    Object getExpressionVal(Expression expression, String byExpression);
    boolean verifyNumericPropertyType(PropertyInstance propertyValue);

}
