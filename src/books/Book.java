package books;

public abstract class Book {
    private int id;
    private String title;
    private String author;
    private boolean available = true;

    public Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
    }

    public int getId(){
        return this.id;
    }
    public boolean isAvailable(){
        return available;
    }
    public void borrow(){
        if(!available){
            throw new IllegalStateException("Book is already borrowed");
        }
        else {
            available = false;
        }
    }
    public void returnBook(){
        available = true;
    }
    public abstract String getInfo();

    public String basicInfo(){
        return "ID - "+ id + "\nTitle - "+ title +"\nAuthor - "+ author;
    }
    public String getTitle(){
        return this.title;
    }
}
