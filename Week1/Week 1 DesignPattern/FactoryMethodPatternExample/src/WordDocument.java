public class WordDocument extends Document {
    public WordDocument(String name) {
        super(name);
    }

    @Override
    public void open() {
        System.out.println("Opening Word document: " + getName());
    }

    @Override
    public void save() {
        System.out.println("Saving Word document: " + getName());
    }

    @Override
    public void close() {
        System.out.println("Closing Word document: " + getName());
    }
}
