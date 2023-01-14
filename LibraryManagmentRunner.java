public class LibraryManagmentRunner extends Library {
    public static void main(String[] args) {
        Library library = new Library();
        User user = new User("admin", Role.ADMIN);
        Author rouling = new Author("Joahn Rouling");
        
        library.createCategory(user, "Adventures");
        library.createBook(user, "Harry Potter 1", "A book about wizard", adventuresCategory, rouling);
        library.welcome();
    }
}
