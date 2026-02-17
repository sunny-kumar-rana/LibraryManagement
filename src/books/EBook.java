package books;

public class EBook extends Book{

    private double fileSizeMB;

    public EBook(int id, String title, String author, double fileSizeMB) {
        super(id, title, author);
        this.fileSizeMB = fileSizeMB;
    }

    @Override
    public String getInfo() {
        return super.basicInfo()+"\nFile size - "+fileSizeMB;
    }
}
