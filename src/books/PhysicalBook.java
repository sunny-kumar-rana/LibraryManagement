package books;

public class PhysicalBook extends Book{

    private int shelfNumber;

    public PhysicalBook(int id, String title, String author, int shelfNumber) {
        super(id, title, author);
        this.shelfNumber = shelfNumber;
    }

    @Override
    public String getInfo() {
        return super.basicInfo()+"\nShelf - "+shelfNumber;
    }

}
