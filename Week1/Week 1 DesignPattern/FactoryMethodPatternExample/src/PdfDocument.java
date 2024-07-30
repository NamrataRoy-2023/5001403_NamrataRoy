public class PdfDocument extends Document{
    public PdfDocument(String name) {
        super(name);
    }

    @Override
    public void open() {
        System.out.println("Opening PDF document: " + getName());
    }

    @Override
    public void save() {
        System.out.println("Saving PDF document: " + getName());
    }

    @Override
    public void close() {
        System.out.println("Closing PDF document: " + getName());
    }
}
