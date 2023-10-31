import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String response;
        System.out.println("Welcome to File Organizer ");
        do {
            System.out.println("Enter the path of the folder to organize: ");
            String folderPath = sc.nextLine();
            Organizer.OrganizeFiles(folderPath);

            System.out.println("Do you want to organize another folder? (Type yes/no)");
            response = sc.nextLine();
        } while (response.equalsIgnoreCase("yes"));


    }
}
