package books;

import datatbase.DBMSConn;

import java.sql.SQLException;

public abstract class Book {
    private int id;
    private String title;
    private String author;
    private boolean available = true;

    public Book(int id, String title, String author, int available) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.available = switch (available){
                case 1 -> true;
                default -> false;
            };
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
            try(DBMSConn db = new DBMSConn();) {
                db.setBookAvailabilityFalse(this);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public void returnBook(){
        try(DBMSConn db = new DBMSConn();){
            db.setBookAvailabilityTrue(this);
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public abstract String getInfo();

    public String basicInfo(){
        return "ID - "+ id + "\nTitle - "+ title +"\nAuthor - "+ author;
    }
    public String getTitle(){
        return this.title;
    }
    public String getAuthor(){
        return this.author;
    }
}
