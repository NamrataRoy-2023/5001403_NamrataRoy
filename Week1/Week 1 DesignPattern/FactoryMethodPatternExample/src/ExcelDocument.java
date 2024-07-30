public class ExcelDocument extends Document{
    public ExcelDocument(String name) {
        super(name);
    }

    @Override
    public void open() {
        System.out.println("Opening Excel document: " + getName());
    }

    @Override
    public void save() {
        System.out.println("Saving Excel document: " + getName());
    }

    @Override
    public void close() {
        System.out.println("Closing Excel document: " + getName());
    }
}
