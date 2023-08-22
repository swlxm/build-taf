package engine;

public enum DriverCreatorEnum {
    CHROME(new ChromeDriverCreator()), FIREFOX(new FirefoxDriverCreator()), EDGE(new EdgeDriverCreator());

    private final DriverCreator creator;

    private DriverCreatorEnum(DriverCreator creator) {
        this.creator = creator;
    }

    public DriverCreator getCreator() {
        return creator;
    }
}
