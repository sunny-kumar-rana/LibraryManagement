package books;

public class PhysicalBook extends Book{

    private final int shelfNumber;

    public PhysicalBook(int id, String title, String author, int shelfNumber, int available) {
        super(id, title, author,available);
        this.shelfNumber = shelfNumber;
    }

    @Override
    public String getInfo() {
        return super.basicInfo()+"\nShelf - "+shelfNumber;
    }
    public int getShelfNumber(){
        return this.shelfNumber;
    }

}
