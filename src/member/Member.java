package member;

public class Member {
    private int memberId;
    private String name;
    private int borrowedBook;
    private int maxAllowedBooks;

    public Member(int memberId, String name, int maxAllowedBooks) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBook = 0;
        this.maxAllowedBooks = maxAllowedBooks;
    }

    public int getMemberId(){
        return memberId;
    }
    public boolean canBorrow(){
        return maxAllowedBooks>borrowedBook;
    }
    public void borrowBook(){
        if(!canBorrow()){
            throw new IllegalStateException("Borrow Limit Reached!");
        }
        else {
            borrowedBook++;
        }
    }
    public void returnBook(){
        if(borrowedBook>0){
            borrowedBook--;
        }
    }

    public String getName() {
        return name;
    }

    public int getBorrowedBook() {
        return borrowedBook;
    }

    public String toString(){
        return "----------------\nMember ID - "+memberId+"\nName - "+name+"\nBorrowed Books - "+borrowedBook;
    }
}
