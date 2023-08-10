package action.impl.calculation.impl;

import action.api.ActionType;
import action.impl.calculation.api.CalculationAction;
import definition.entity.EntityDefinition;
import definition.property.api.PropertyType;
import execution.context.Context;
import execution.instance.property.PropertyInstance;
import expression.api.Expression;

import java.util.List;

public class DivideAction extends CalculationAction {
    public DivideAction(ActionType actionType, EntityDefinition entityDefinition, List<Expression> expressionList, String resultProp) {
        super(actionType, entityDefinition, expressionList, resultProp);
    }

    @Override
    public void invoke(Context context) {
        PropertyInstance propertyInstance = context.getPrimaryEntityInstance().getPropertyByName(resultProp);
        if (!verifyNumericPropertyType(propertyInstance)) {
            throw new IllegalArgumentException("increase action can't operate on a none number property [" + resultProp + "]");
        }
        Object expVal1 = getExpressionVal(getExpressionList().get(0));
        Object expVal2 = getExpressionVal(getExpressionList().get(1));
        if(((Number)expVal2).doubleValue() == 0)
            throw new IllegalArgumentException("Argument 2 was equal to 0, cant perform calculation-divide by 0!");
        Number divRes;
        if(expVal1 instanceof Double || expVal2 instanceof Double)
        {
            Double val1 = PropertyType.FLOAT.convert(expVal1);
            Double val2 = PropertyType.FLOAT.convert(expVal2);
            divRes = val1 / val2;
        }
        else
        {
            Integer val1 = PropertyType.DECIMAL.convert(expVal1);
            Integer val2 = PropertyType.DECIMAL.convert(expVal2);
            divRes = val1 / val2;
        }
        if (propertyInstance.getPropertyDefinition().getRange() != null) {
            if (divRes.doubleValue() >= propertyInstance.getPropertyDefinition().getRange().getRangeFrom()) {
                if (propertyInstance.getPropertyDefinition().getType() == PropertyType.DECIMAL) {
                    propertyInstance.updateValue(divRes);
                }
            }
        } else
            propertyInstance.updateValue(divRes);
    }
}