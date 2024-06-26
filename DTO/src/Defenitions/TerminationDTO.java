package Defenitions;

public class TerminationDTO
{
    private final Integer bySeconds;
    private final Integer byTicks;
    private final Boolean byUser;

    public TerminationDTO(Integer bySeconds, Integer byTicks, Boolean byUser) {
        this.bySeconds = bySeconds;
        this.byTicks = byTicks;
        this.byUser = byUser;
    }
    public boolean getByUser() {
        return byUser;
    }
    public Integer getBySeconds() {
        return bySeconds;
    }

    public Integer getByTicks() {
        return byTicks;
    }
}
