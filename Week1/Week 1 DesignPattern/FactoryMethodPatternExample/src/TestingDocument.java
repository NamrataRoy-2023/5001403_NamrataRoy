public class TestingDocument {
    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument("FactoryMethodPattern.docx");
        wordDoc.open();
        wordDoc.save();
        wordDoc.close();


        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument("FactoryMethodPattern.pdf");
        pdfDoc.open();
        pdfDoc.save();
        pdfDoc.close();


        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createDocument("FactoryMethodPattern.xlsx");
        excelDoc.open();
        excelDoc.save();
        excelDoc.close();
    }
}
