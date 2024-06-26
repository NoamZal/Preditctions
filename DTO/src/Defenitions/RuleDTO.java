package Defenitions;

import Defenitions.Actions.api.ActionDTO;

import java.util.List;

public class RuleDTO
{
    private final String name;
    private final ActivationDTO activation;
    private final List<ActionDTO> actions;

    public RuleDTO(String name, ActivationDTO activation, List<ActionDTO> actions) {
        this.name = name;
        this.activation = activation;
        this.actions = actions;
    }

    public String getName() {
        return name;
    }

    public ActivationDTO getActivation() {
        return activation;
    }

    public List<ActionDTO> getActions() {
        return actions;
    }
}
