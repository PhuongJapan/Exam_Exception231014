package ra.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Student implements IEntity<Student>, Serializable {
    private String studentId;
    private String studentName;
    private Date birthDay;
    private int age;
    private boolean sex;
    private float mark_html;
    private float mark_css;
    private float mark_javascript;
    private float avgMark;
    private String rank;

    public Student() {

    }

    public Student(String studentId, String studentName, Date birthDay, int age, boolean sex, float mark_html, float mark_css, float mark_javascript, float avgMark, String rank) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.birthDay = birthDay;
        this.age = age;
        this.sex = sex;
        this.mark_html = mark_html;
        this.mark_css = mark_css;
        this.mark_javascript = mark_javascript;
        this.avgMark = avgMark;
        this.rank = rank;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public float getMark_html() {
        return mark_html;
    }

    public void setMark_html(float mark_html) {
        this.mark_html = mark_html;
    }

    public float getMark_css() {
        return mark_css;
    }

    public void setMark_css(float mark_css) {
        this.mark_css = mark_css;
    }

    public float getMark_javascript() {
        return mark_javascript;
    }

    public void setMark_javascript(float mark_javascript) {
        this.mark_javascript = mark_javascript;
    }

    public float getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(float avgMark) {
        this.avgMark = avgMark;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public void inputData(Scanner scanner, List<Student> studentList) {
        this.studentId = validateStudentId(scanner, studentList);
        this.studentName = validateStudentName(scanner);
        this.birthDay = validateBirthday(scanner);
        this.sex = validateSex(scanner);
        this.mark_html = validateMarkHtml(scanner);
        this.mark_css = validateMarkCss(scanner);
        this.mark_javascript = validateMarkJavascript(scanner);
        calAge();
        calAvgMark_Rank();
    }

    private String validateStudentId(Scanner scanner, List<Student> list) {
        System.out.println("Nhập mã SV");
        do {
            this.studentId = scanner.nextLine();
            if (this.studentId.length() == 4) {
                if (this.studentId.startsWith("S")) {
                    boolean isExist = false;
                    for (Student std : list) {
                        if (std.getStudentId().equals(studentId)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        return studentId;
                    } else {
                        System.err.println("Mã SP đã tồn tại, vui lòng nhập lại");
                    }
                } else {
                    System.err.println("Mã SV phải bắt đầu từ S, vui lòng nhập lại");
                }
            } else {
                System.err.println("Mã SV phải gồm 4 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    private String validateStudentName(Scanner scanner) {
        System.out.println("Nhập tên SV");
        do {
            String studentName = scanner.nextLine();
            if (studentName.length() >= 10 && studentName.length() <= 50) {
                return studentName;
            } else {
                System.err.println("Tên SV phải có độ dài 10-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public Date validateBirthday(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Nhập ngày sinh (dd/MM/yyyy, năm sinh trước 2005): \n");
        do {
            try {
                Date birthDay = sdf.parse(scanner.nextLine());
                Calendar cal = Calendar.getInstance();
                cal.setTime(birthDay);
                if (cal.get(Calendar.YEAR) < 2005) {
                    return birthDay;
                }else {
                    System.out.println("Năm sinh phải trước 2005");
                }
            } catch (ParseException ex1) {
                System.out.println("Ngày sinh không hợp lệ.");
            }
        } while (true);

    }

    private boolean validateSex(Scanner scanner) {
        System.out.println("Nhập vào giới tính của SV");
        do {
            String sex = scanner.nextLine();
            if (sex.equalsIgnoreCase("true") || sex.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(sex);
            } else {
                System.err.println("Giới tính chỉ nhận giá trị true hoặc false");
            }

        } while (true);
    }

    private float validateMarkHtml(Scanner scanner) {
        System.out.println("Nhập điểm HTML");
        do {
            try {
                float mark_html = Float.parseFloat(scanner.nextLine());
                if (mark_html >= 0 && mark_html <= 10) {
                    return mark_html;
                } else {
                    System.err.println("Điểm HTML phải trong khoảng 0-10, vui lòng nhập lại");
                }

            } catch (NumberFormatException ex1) {
                System.err.println("Điểm HTML không phải định dạng, vui lòng nhập lại");
            } catch (Exception ex) {
                System.err.println("Có lỗi, vui lòng nhập lại");
            }
        } while (true);
    }

    private float validateMarkCss(Scanner scanner) {
        System.out.println("Nhập điểm CSS");
        do {
            try {
                float mark_css = Float.parseFloat(scanner.nextLine());
                if (mark_css >= 0 && mark_css <= 10) {
                    return mark_css;
                } else {
                    System.err.println("Điểm CSS phải trong khoảng 0-10, vui lòng nhập lại");
                }

            } catch (NumberFormatException ex1) {
                System.err.println("Điểm CSS không phải định dạng, vui lòng nhập lại");
            } catch (Exception ex) {
                System.err.println("bị lỗi, vui lòng nhập lại");
            }
        } while (true);

    }

    private float validateMarkJavascript(Scanner scanner) {
        System.out.println("Nhập điểm Javascript");
        do {
            try {
                float mark_javascript = Float.parseFloat(scanner.nextLine());
                if (mark_javascript >= 0 && mark_javascript <= 10) {
                    return mark_javascript;
                } else {
                    System.err.println("Điểm Javascipt phải trong khoảng 0-10, vui lòng nhập lại");
                }

            } catch (NumberFormatException ex1) {
                System.err.println("Điểm Javascipt không phải định dạng, vui lòng nhập lại");
            } catch (Exception ex) {
                System.err.println("Nhập bị lỗi, vui lòng nhập lại");
            }
        } while (true);
    }

    @Override
    public void displayData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(this.birthDay);
        System.out.printf("Mã SV: %s - Tên SV: %s  - Ngày tháng năm sinh: %s -Tuổi SV: %d - Giới tính SV:%s\n",
                this.studentId, this.studentName,formattedDate,this.age, this.sex ? "Nam" : "Nữ");
        System.out.printf("Điểm HTML: %f - Điểm CSS: %f - Điểm Javascript : %f - avgMark: %f - rank: %s\n",
                this.mark_html, this.mark_css, this.mark_javascript, this.avgMark, this.rank);
    }

    @Override
    public void calAge() {
        this.age =  2023-(this.birthDay.getYear()+1900);
//        LocalDate localDate = LocalDate.now();
//        this.age = (localDate.getYear() - this.birthDay.getYear());
    }

    @Override
    public void calAvgMark_Rank() {
        this.avgMark = (this.mark_html + this.mark_css + this.mark_javascript) / 3;
        if (this.avgMark < 5) {
            this.rank = "Yếu";
        } else if (this.avgMark < 7) {
            this.rank = "Trung bình";
        } else if (this.avgMark < 8) {
            this.rank = "Khá";
        } else if (this.avgMark < 9) {
            this.rank = "Giỏi";
        } else {
            this.rank = "Xuất sắc";
        }
    }
}
