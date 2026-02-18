package books;

public class EBook extends Book{

    private double fileSizeMB;

    public EBook(int id, String title, String author, double fileSizeMB, int available){
        super(id, title, author,available);
        this.fileSizeMB = fileSizeMB;
    }

    @Override
    public String getInfo() {
        return super.basicInfo()+"\nFile size - "+fileSizeMB;
    }
    public double getFileSize(){
        return this.fileSizeMB;
    }
}
