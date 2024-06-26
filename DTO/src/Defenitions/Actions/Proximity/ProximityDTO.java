package Defenitions.Actions.Proximity;

import Defenitions.Actions.api.ActionDTO;
import Enums.ActionTypeDTO;

public class ProximityDTO extends ActionDTO {
    private final String source;
    private final String target;
    private final String depth;
    private final int numActions;

    public ProximityDTO(ActionTypeDTO type, String primaryEntityName, String secondaryEntityName, String source, String target, String depth, int numActions) {
        super(type, primaryEntityName, secondaryEntityName);
        this.source = source;
        this.target = target;
        this.depth = depth;
        this.numActions = numActions;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public String getDepth() {
        return depth;
    }

    public int getNumActions() {
        return numActions;
    }
}
