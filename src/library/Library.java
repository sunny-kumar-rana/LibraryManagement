package library;

import books.Book;
import books.EBook;
import books.PhysicalBook;
import datatbase.DBMSConn;
import member.Member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Library {
//    private List<Book> books = new ArrayList<>();
//    private List<Member> members = new ArrayList<>();

    public void addBook(Book book){
        DBMSConn db = new DBMSConn();
        if(book instanceof EBook x){
            db.insertEBook(x);
        }else if(book instanceof PhysicalBook px){
            db.insertPBook(px);
        }
        try {
            db.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void removeBook(int bookId){
        Book book = findBookById(bookId);
        if (!book.isAvailable()){
            throw new IllegalStateException("can't remove borrowed book!");
        }
        try(DBMSConn db = new DBMSConn();){
            db.removebook(bookId);
        }catch (SQLException e){
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void displayAllBooks(){
        try {
            DBMSConn db = new DBMSConn();
            db.getBooks();
            db.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Book findBookById(int id){
        try(DBMSConn db = new DBMSConn();) {
            return db.findBook(id);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public void registerMember(Member member){
        Member x = null;
        DBMSConn db = new DBMSConn();
        try {
                x = db.searchMember(member.getMemberId());
                if(x == null){
                    db.insertMember(member);
                }else{
                    System.out.println("member already exists");
                }
                db.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public Member findMember(int memberId){
        try(DBMSConn db = new DBMSConn();) {
            return db.findMembers(memberId);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public void borrowBook(int bookId, int memberId){
        Book book = findBookById(bookId);
        Member member = findMember(memberId);

        if (!book.isAvailable()){
            throw new IllegalStateException("Book is not Available!");
        }
        if (!member.canBorrow()){
            throw new IllegalStateException("Member reached the borrow limit!");
        }
        book.borrow();
        member.borrowBook();
    }
    public void returnBook(int bookId, int memberId){
        Book book = findBookById(bookId);
        Member member = findMember(memberId);
        if (book.isAvailable()) {
            throw new IllegalStateException("Book was not borrowed");
        }
        book.returnBook();
        member.returnBook();
    }

    public void searchBook(String title){
        Book x = null;
        try(DBMSConn db = new DBMSConn();){
            x = db.searchBook(title);
        } catch (SQLException e){
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        if (x == null){
            System.out.println("no such books found");
        } else{
            System.out.println(x);
        }
    }
}
