import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;

class Student {
    public static void main(String Args[]) {
        Scanner s = new Scanner(System.in);
        int option;
        do {
            System.out.println(
                    "1. View The Student Details 2. Add The Student Details 3. Search The Student Details 4. Exit ");
            option = s.nextInt();
            switch (option) {
                case 1:
                    Viewdata v = new Viewdata();
                    v.data();
                    break;
                case 2:
                    detail d = new detail();
                    d.Adddetail();
                    break;
                case 3:
                    search sc = new search();
                    sc.searchdata();
                    break;
                case 4:
                    System.out.println("EXIT");
                    break;
                default:
                    System.out.println("INVALID");
            }
        } while (option != 4);
    }
}

class Viewdata {
    void data() {
        File f = new File("Data.txt");
        try {
            if (!f.exists()) {
                System.out.println("No Data Found");
                f.createNewFile();
            } else {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String l;
                while ((l = br.readLine()) != null) {
                    String[] p = l.split(",");
                    String id = p[0];
                    String name = p[1];
                    String avg = p[2];
                    String grade = p[3];
                    System.out.println(id + " | " + name + " | " + avg + " | " + grade);
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println("Error in view data " + e.getMessage());
        }
    }
}

class detail {
    void Adddetail() {
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Enter student Id");
            int id = s.nextInt();
            s.nextLine();
            System.out.println("Enter student Name");
            String name = s.nextLine();
            System.out.println("Enter student dept");
            String dept = s.nextLine();
            System.out.println("Enter student Marks one by one ");
            int i = 0, sum = 0;
            int[] m = new int[5];
            while (i < 5) {
                try {
                    m[i] = s.nextInt();
                    if (m[i] > 100 || m[i] < 0) {
                        throw new IllegalArgumentException("Invalid mark");
                    }
                    sum = sum + m[i];
                    i++;
                } catch (Exception e) {
                    System.out.println("Invalid mark " + e.getMessage());
                    return;
                }
            }
            int avg = sum / 5;
            String grade;
            if (avg >= 80) {
                grade = "A";
            } else if (avg >= 60) {
                grade = "B";
            } else if (avg > 35) {
                grade = "C";
            } else {
                grade = "F";
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("Data.txt", true));
            bw.write(id + "," + name + "," + avg + "," + grade);
            bw.newLine();
            bw.close();
            System.out.println("Data Saved Successfully.");
        } catch (Exception e) {
            System.out.println("Error in adding details " + e.getMessage());
        }
    }
}

class search {
    void searchdata() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a student ID");
        String idf = s.nextLine();
        File f = new File("Data.txt");
        String l;
        boolean found = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            while ((l = br.readLine()) != null) {
                String[] a = l.split(",");
                String id = a[0];
                String name = a[1];
                String avg = a[2];
                String grade = a[3];
                if (id.equals(idf)) {
                    System.out.println(id + " | " + name + " | " + avg + " | " + grade);
                    found = true;
                    break;
                }
            }
            br.close();
            if (!found) {
                System.out.println("Not Found");
            }
        } catch (Exception e) {
            System.out.println("Error in search " + e.getMessage());
        }
    }
}
