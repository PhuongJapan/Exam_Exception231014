package ra.impl;

import ra.entity.Student;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class StudentImp {
    public static List<Student> studentList = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        readDataFromFile();
        int choice;
        do {
            System.out.println("*****************************MENU************************\n" +
                    "1. Nhập thông tin các sinh viên\n" +
                    "2. Tính tuổi các sinh viên\n" +
                    "3. Tính điểm trung bình và xếp loại sinh viên\n" +
                    "4. Sắp xếp sinh viên theo tuổi tăng dần\n" +
                    "5. Thống kê sinh viên theo xếp loại sinh viên\n" +
                    "6. Cập nhật thông tin sinh viên theo mã sinh viên\n" +
                    "7. Tìm kiếm sinh viên theo tên sinh viên\n" +
                    "8. Thoát\n" +
                    "9. Hiển thị danh sách sinh viên");
            System.out.println("Lựa chọn của bạn: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    StudentImp.inputStudentInf(scanner);
                    break;
                case 2:
                    StudentImp.calAgeStudent();
                    break;
                case 3:
                    StudentImp.calAvgRankStudent();
                    break;
                case 4:
                    StudentImp.sortByAge();
                    break;
                case 5:
                    StudentImp.countRank();
                    break;
                case 6:
                    break;
                case 7:
                    StudentImp.searchStudentByName();
                    break;
                case 8:
                    writeDataToFile();
                    System.exit(0);
                    break;
                case 9:
                    StudentImp.displayDataOfStudent();
                    break;
                default:
                    System.err.println("Vui lòng chỉ chọn từ 1-8");
            }

        } while (true);
    }

    private static void searchStudentByName() {
        System.out.println("Nhập tên SV bạn cần tìm kiếm:");
        String searchName = scanner.nextLine();
        System.out.println("Thông tin của sinh viên bạn cần tìm là:");
        for (Student std:studentList) {
            if (std.getStudentName().contains(searchName)){
                std.displayData();
            }
        }
        System.out.println("Thông tin của sinh viên bạn cần tìm là:");
    }

//    public static void readDataFromFile() {
//        File file = new File("listStudent.txt");
//        FileInputStream fis = null;
//        ObjectInputStream ois = null;
//        try {
//            fis = new FileInputStream(file);
//            ois = new ObjectInputStream(fis);
//            if (ois.readObject() != null) {
//                studentList = (List<Student>) ois.readObject();
//            }
//        } catch (FileNotFoundException ex) {
//            System.err.println("Không tồn tại file");
//        } catch (IOException e) {
//            System.err.println("Có lỗi IO");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    System.err.println("Lỗi");
//                }
//            }
//            if (ois != null) {
//                try {
//                    ois.close();
//                } catch (IOException e) {
//                    System.err.println("Lỗi");
//                }
//            }
//        }
//    }

    public static void writeDataToFile() {
        //1. Khởi tạo file
        //Nếu file students.txt chưa tồn tại thì tạo mới
        //Nếu file students.txt đã tồn tại, ghi đè toàn bộ
        File file = new File("listStudent1.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //2. Khởi tạo đối tượng FileOutputStream
            fos = new FileOutputStream(file);
            //3. Khởi tạo đối tượng ObjectOutputStream
            oos = new ObjectOutputStream(fos);
            //4. Ghi dữ liệu ra file với phương thức writeObject
            oos.writeObject(studentList);
            //5. Đẩy dữ liệu từ stream xuống file
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readDataFromFile() {
        //1. Khởi tạo đối tượng file
        File file = new File("listStudent1.txt");
        ;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            //2. Khởi tạo đôi tượng FileInputStream

            fis = new FileInputStream(file);
            //3. Khởi tạo đối tượng ObjectInputStream để đọc object
            ois = new ObjectInputStream(fis);

            //4. Đọc dữ liệu với phương thức readObject()
            studentList = (List<Student>) ois.readObject();
            //In ra kết quả
            for (Student std : studentList) {
                std.displayData();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ois.close();
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void inputStudentInf(Scanner scanner) {
        System.out.println("Nhập vào số SV cần thêm");

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Student std = new Student();
            std.inputData(scanner, studentList);
            studentList.add(std);
        }
    }

    private static void calAgeStudent() {
        for (Student std : studentList) {
            std.calAge();
        }
        System.out.println("Đã tính xong tuổi của SV");
    }

    private static void calAvgRankStudent() {
        for (Student std : studentList) {
            std.calAvgMark_Rank();
        }
        System.out.println("Đã tính xong điểm SV và xếp loại");

    }
    private static void countRank(){

            int countOfYeu=0;
            int countOfTB = 0;
            int countOfKha = 0;
            int countOfGioi= 0;
            int countOfXuatsac= 0;
        for (Student std:studentList) {
           switch (std.getRank()){
               case "Yếu":
                   countOfYeu++;
                   break;
               case "Trung bình":
                   countOfTB++;
                   break;
               case "Khá":
                   countOfKha++;
                   break;
               case "Giỏi":
                   countOfGioi++;
                   break;
               case "Xuất sắc":
                   countOfXuatsac++;
                   break;
           }
        }
        System.out.println("Đã sắp xếp xong SV theo xếp loại:");
        System.out.printf("Xuất sắc:%d - Giỏi:%d - Khá:%d - Trung bình:%d - Yếu:%d\n"
        ,countOfXuatsac, countOfGioi, countOfKha, countOfTB, countOfYeu);

    }

//    public static void writeDataToFile() {
//        File file = new File("listStudent.txt");
//        FileOutputStream fos = null;
//        ObjectOutputStream oos = null;
//        try {
//            fos = new FileOutputStream(file);
//            oos = new ObjectOutputStream(fos);
//            oos.writeObject(studentList);
//            oos.flush();
//        } catch (FileNotFoundException ex) {
//            System.err.println("Lỗi ko thấy file");
//        } catch (Exception ex1) {
//            System.err.println("Có lỗi ");
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (Exception ex2) {
//                    System.err.println("Lỗi ko xác định");
//                }
//            }
//            if (oos != null) {
//                try {
//                    oos.close();
//                } catch (Exception ex3) {
//                    System.err.println("Lỗi ko xác định");
//                }
//            }
//        }
//    }

    //4. Sắp xếp SV theo tuổi tăng dần
    private static void sortByAge() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println("Đã sắp xếp sinh viên theo tuổi tăng dần");

    }


    private static void displayDataOfStudent() {
        for (Student std : studentList) {
            std.displayData();
        }
    }
}
