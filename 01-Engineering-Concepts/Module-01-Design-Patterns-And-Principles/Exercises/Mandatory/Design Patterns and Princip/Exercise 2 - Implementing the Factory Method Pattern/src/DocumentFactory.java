// Abstract factory class - subclasses will decide which document to create

public abstract class DocumentFactory {

    // factory method - subclasses override this
    public abstract Document createDocument();

    // common method to open a doc using factory
    public void openDocument() {
        Document doc = createDocument();
        doc.open();
    }
}