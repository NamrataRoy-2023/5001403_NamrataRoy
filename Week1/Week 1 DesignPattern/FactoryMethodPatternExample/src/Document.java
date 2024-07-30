public abstract class Document {
    private String name;

    public Document(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void open();
    public abstract void save();
    public abstract void close();
}
