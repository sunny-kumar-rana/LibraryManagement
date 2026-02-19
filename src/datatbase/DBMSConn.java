package datatbase;
import books.Book;
import books.EBook;
import books.PhysicalBook;
import member.Member;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.lang.ClassNotFoundException;
import java.sql.SQLException;

public class DBMSConn implements AutoCloseable{
    private Connection conn = null;
    private Statement st = null;

    {
        try {
            String clsName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(clsName);
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to connect to database");;
        }
        try {
            String password = "Admin";
            String userName = "library";
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(url, userName, password);
            st = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //input check methods
//    private void checkString(String s){
//        if(s.trim().isBlank()||s.isEmpty()){
//            throw new RuntimeException("can't leave fields blank");
//        }
//    }private void checkInt(int x){
//        if(x <= 0 || x>999){
//            throw new RuntimeException("wrong value");
//        }
//    }private void checkDouble(double x){
//        if(x <= 0 || x>999){
//            throw new RuntimeException("wrong value");
//        }
//    }

    // book insertion and display methods
//    public int isAvailable(Book book) throws SQLException {
//        ResultSet r = st.executeQuery("select is_available from book_list where id = "+book.getId());
//        r.next();
//        return r.getInt("is_available");
//    }
    public void setBookAvailabilityFalse(Book book) throws SQLException {
        st.executeUpdate("update book_list set is_available = 0 where book_id = "+book.getId());
    }
    public void setBookAvailabilityTrue(Book book) throws SQLException {
        st.executeUpdate("update book_list set is_available = 1 where book_id = "+book.getId());
    }
    public boolean isBookAvailable(int id) throws SQLException {
        ResultSet r = st.executeQuery("select is_available from book_list where book_id = "+id);
        if(r.getString("is_available").equalsIgnoreCase("true")){
            return true;
        }
        return false;
    }
    public void insertEBook(EBook book){
        String query =
                String.format("INSERT INTO book_list(book_id,book_title,book_author,file_size,book_type) values(book_id_seq.nextval,'%s','%s',%f,'ebook')",
                book.getTitle(),book.getAuthor(),book.getFileSize());
        try {
            System.out.println(st.executeUpdate(query)+"inserted");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void insertPBook(PhysicalBook book){
        String query =
                String.format("INSERT INTO book_list(book_id,book_title,book_author,shelf_no,book_type) values(book_id_seq.nextval,'%s','%s',%d,'pbook')",
                book.getTitle(),book.getAuthor(),book.getShelfNumber());
        try {
            System.out.println(st.executeUpdate(query)+"inserted");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void getBooks() throws SQLException {
        String query = "select * from book_list";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            System.out.println("\n----Book----\nbook-id : "+rs.getInt(1)+"\nbook name : "+rs.getString(2)+"\nbook author : "+
                    rs.getString(3)+"\nsize : "+rs.getDouble(4)+"\nshelf-no : "+rs.getInt(5)+"\n");
        }
    }
    public Book findBook(int id) throws SQLException {
        ResultSet r = st.executeQuery("Select * from book_list where book_id = "+id);
        r.next();
        if(r.getString("book_type").equalsIgnoreCase("ebook")){
            return new EBook(r.getInt(1),r.getString(2),r.getString(3),r.getDouble(4),r.getInt("is_available"));
        }
        else {
            return new PhysicalBook(r.getInt(1),r.getString(2),r.getString(3),r.getInt(5),r.getInt("is_available"));
        }
    }
    public void removebook(int bookId) throws SQLException {
        int x = st.executeUpdate("delete from book_list where book_id = "+bookId);
        System.out.println(x+" book deleted");
    }
    public Book searchBook(String title) throws SQLException {
        ResultSet r = st.executeQuery("Select * from book_list where book_title like %"+title);
        r.next();
        String type = r.getString("book_type");
        if(type.equalsIgnoreCase("ebook")){
            return new EBook(r.getInt(1),r.getString(2),r.getString(3),r.getDouble(4),r.getInt("is_available"));
        } else if (r.getString("book_type").equalsIgnoreCase("pbook")) {
            return new PhysicalBook(r.getInt(1),r.getString(2),r.getString(3),r.getInt(5),r.getInt("is_available"));
        }
        return null;
    }


    //member insertion and display methods
//    public boolean canBorrow(Member member) throws SQLException {
//        ResultSet r = st.executeQuery("select borrowed_books, max_allowed_books from members where member_id = "+member.getMemberId());
//        r.next();
//        return r.getInt("borrowed_books") < r.getInt("max_allowed_books");
//    }
    public void insertMember(Member member){
        String query =
                String.format("INSERT INTO members(member_id,member_name,borrowed_books) values(%d,'%s','%d')",member.getMemberId(),member.getName(),member.getBorrowedBook());
        try {
            System.out.println(st.executeUpdate(query)+"inserted");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public Member searchMember(int memberId) throws SQLException {
        String query = "select * from members where member_id = "+memberId;
        ResultSet r = st.executeQuery(query);
        if(r.next()) {
            if (r.getInt("member_id") == memberId) {
                return new Member(r.getInt(1), r.getString(2), r.getInt(3), r.getInt(4));
            }
        }
        return null;
    }
    public String getMembers() throws SQLException {
        String query = "select * from members";
        ResultSet rs = st.executeQuery(query);
        StringBuilder membersList = new StringBuilder();
        while (rs.next()){
             membersList.append("member-id : ").append(rs.getInt(1)).append("member name : ").append(rs.getString(2)).append("borrowed books").append(rs.getInt(3)).append("\n");
        }
        return membersList.toString();
    }
    public Member findMembers(int id) throws SQLException {
        ResultSet r = st.executeQuery("Select * from members where member_id = "+id);
        r.next();
        return new Member(r.getInt(1), r.getString(2),r.getInt(3), r.getInt(4));
    }
    public void memberBorrowBook(Member member) throws SQLException {
        int x = st.executeUpdate("update members set borrowed_books = borrowed_books + 1 where member_id = "+member.getMemberId());
        if (x == 1){
            System.out.println("success");
        }
        else {
            System.out.println("something went wrong");
        }
    }public void memberReturnBook(Member member) throws SQLException {
        int x = st.executeUpdate("update members set borrowed_books = borrowed_books - 1 where member_id = "+member.getMemberId());
        if (x == 1){
            System.out.println("success");
        }
        else {
            System.out.println("something went wrong");
        }
    }

    @Override
    public void close() throws Exception {
        if (st != null && !st.isClosed()){
            st.close();
        }
        if(conn != null && !conn.isClosed()){
            conn.close();
        }
    }
}
