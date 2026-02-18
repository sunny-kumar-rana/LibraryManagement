package main;

import books.EBook;
import books.PhysicalBook;
import library.Library;
import member.Member;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        int input;
        while (true){
            System.out.println("1.Add Book\n2.Remove Book\n3.Display all Books\n4.Register Member\n5.Borrow Book\n6.Return Book\n7.Search Book\n8.Exit");
            input = sc.nextInt();
            sc.nextLine();
            try {
                switch (input) {
                    case 1 -> {
                        System.out.println("Select Type\n1.Physical Book\n2.EBook");
                        input = sc.nextInt();
                        System.out.println("Enter Book ID : ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter Book Name :");
                        String name = sc.nextLine();
                        System.out.println("Enter Author Name : ");
                        String author = sc.nextLine();
                        switch (input) {
                            case 1 -> {
                                System.out.println("Enter shelf number : ");
                                int shelf = sc.nextInt();
                                sc.nextLine();
                                library.addBook(new PhysicalBook(id, name, author, shelf,1));
                            }
                            case 2 -> {

                                System.out.println("Enter size in MB : ");
                                double size = sc.nextDouble();
                                sc.nextLine();
                                library.addBook(new EBook(id, name, author, size,1));
                            }
                            default -> {
                                System.err.println("Invalid");
                            }
                        }
                        System.out.println("Book Added to Library");
                    }
                    case 2 -> {
                        System.out.println("Enter Book Id to remove : ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        library.removeBook(id);
                    }
                    case 3 -> {
                        library.displayAllBooks();
                    }
                    case 4 -> {
                        System.out.println("Enter Member id : ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter Member Name : ");
                        String name = sc.nextLine();
                        System.out.println("enter max allowed books : ");
                        int allowedBooks = sc.nextInt();
                        sc.nextLine();
                        library.registerMember(new Member(id,name,allowedBooks));
                    }
                    case 5 -> {
                        System.out.println("Enter Member id : ");
                        int memberId = sc.nextInt();
                        System.out.println("Enter Book id : ");
                        int bookId = sc.nextInt();
                        sc.nextLine();
                        library.borrowBook(bookId,memberId);
                    }
                    case 6 -> {
                        System.out.println("Enter Member id : ");
                        int memberId = sc.nextInt();
                        System.out.println("Enter Book id : ");
                        int bookId = sc.nextInt();
                        sc.nextLine();
                        library.returnBook(bookId,memberId);
                    }
                    case 7 -> {
                        System.out.println("enter book title to search");
                        String keyword = sc.nextLine();
                        library.searchBook(keyword.trim());
                    }
                    case 8 -> {
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Invalid Choice, Please Select a Valid Option");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
