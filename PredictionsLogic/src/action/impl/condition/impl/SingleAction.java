package action.impl.condition.impl;

import Enums.ActionTypeDTO;
import action.api.Action;
import action.impl.condition.api.ConditionAction;
import definition.entity.EntityDefinition;
import definition.secondaryEntity.api.SecondaryEntityDefinition;
import execution.context.Context;
import expression.api.Expression;

import java.util.List;

public class SingleAction extends ConditionAction {

    String operator;

    public SingleAction(EntityDefinition entityDefinition, List<Expression> expressionList,
                        List<Action> thenActionList, List<Action> elseActionList, String operator, SecondaryEntityDefinition secondaryEntityDef) {
        super(ActionTypeDTO.CONDITION, entityDefinition, expressionList, thenActionList, elseActionList,secondaryEntityDef);
        this.operator = operator;
    }

    @Override
    public boolean checkCondition(Context context) {

        Object propValue = getExpressionList().get(0).calculateExpression(context);

        Object valueByExpression = getExpressionList().get(1).calculateExpression(context);

        //Object propValue = context.getPrimaryEntityInstance().getPropertyByName(propertyName).getValue();

        switch(operator.toLowerCase()){
            case "=":
                return propValue.equals(valueByExpression);
            case "!=":
                return !propValue.equals(valueByExpression);
            case "bt":
                return Bt(propValue,valueByExpression);
            case "lt":
                return Lt(propValue,valueByExpression);
            default:
                throw new IllegalArgumentException("invalid argument was given");
        }
    }
     private boolean Bt(Object value,Object expression) {
        Double myPropVal = ((Number)value).doubleValue();
        Double myExpVal = ((Number)expression).doubleValue();

        return myPropVal > myExpVal;
    }
    private boolean Lt(Object value,Object expression) {
        Double myPropVal = ((Number)value).doubleValue();
        Double myExpVal = ((Number)expression).doubleValue();

        return myPropVal < myExpVal;
    }

}
