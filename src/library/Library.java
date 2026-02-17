package library;

import books.Book;
import member.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    public void addBook(Book book){
        for(Book b : books){
            if (b.getId() == book.getId()){
                throw new IllegalArgumentException("Book ID is already present!");
            }
        }
        books.add(book);
    }
    public void removeBook(int bookId){
        Book book = findBookById(bookId);
        if (!book.isAvailable()){
            throw new IllegalStateException("can't remove borrowed book!");
        }
        books.remove(book);
    }
    public void displayAllBooks(){
        if (books.isEmpty()){
            System.out.println("No Books Available!");
            return;
        }
        for(Book book : books){
            System.out.println(book.getInfo());
        }
    }

    public Book findBookById(int id){
        for (Book b : books){
            if(b.getId() == id){
                return b;
            }
        }
        throw new NoSuchElementException("Book not found!");
    }


    public void registerMember(Member member){
        for(Member m : members){
            if (m.getMemberId() == member.getMemberId()){
                throw new IllegalArgumentException("Member ID is already present!");
            }
        }
        members.add(member);
    }
    public Member findMember(int memberId){
        for (Member m : members){
            if(m.getMemberId() == memberId){
                return m;
            }
        }
        throw new NoSuchElementException("Member not found!");
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
        for (Book book : books){
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())){
                System.out.println("\n-----------\n"+book.getInfo()+"\n--------------------\n");
            }
        }
        System.out.println("no such books found");
    }
}
