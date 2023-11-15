
package com.xuanhieu.di.dao;

import com.xuanhieu.di.dto.Student;
import com.xuanhieu.di.util.Filter;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    List<Student> listStu = new ArrayList<>();

    //floating block of code
    {
        listStu.add(new Student("SE999999", "Chin Pham", 1999, 9.0, "SE"));
        listStu.add(new Student("SE888888", "Tam Le", 1998, 5.0, "SE"));
        listStu.add(new Student("SE444444", "Bon Phan", 2004, 4.0, "SE"));
        listStu.add(new Student("IA555555", "Nam Ly", 1995, 5.0, "IA"));
        listStu.add(new Student("IA333333", "Ba Mai", 1993, 3.0, "IA"));
        listStu.add(new Student("GD777777", "Bay Vo", 1997, 7.0, "GD"));
        listStu.add(new Student("GD666666", "Luc Tran", 1996, 6.0, "GD"));
        listStu.add(new Student("SS222222", "Hai Vu", 2002, 2.0, "IB"));
    }

    public List<Student> getAll() {
        return listStu;
    }

    //2.In ra/trả về danh sách sinh viên ngành SE
    public List<Student> getSEStudents() {
        List<Student> result = new ArrayList<>();
        for (Student student : listStu) {
            if (student.getMajor().equalsIgnoreCase("SE")) {
                result.add(student);
            }
        }
        return result;
    }
    
    public List<Student> getStudents(Filter<Student> cond) {
        List<Student> result = new ArrayList<>();
        for (Student student : listStu) {
            if(cond.check(student))
                result.add(student);
        }
        return result;
    } 
    
    //CHÍNH THỨC VÀO DI
    //public List<Student> getStudent(??? obj) {
    //  for(từn phần tử của ArrayList lấy được một sv, x)
    //      obj.check() check gium tao ban sv x nào thào đk này hay không
    //          có -> lấy bạn x này bỏ vào WishList
    //              không thỏa thì thôi, đến kiểm tra bạn tiếp theo
    //obj chính nó phải có lệnh if, đầy if hàm mình thành if bên ngoài đưa
    //vào, dependency đã được chích vào/inject
    //mày đưa cách mày nhìn dữ liệu vào đây cho tao, tao xử lí theo cách
    //mày muốn, gọi ìf của mày, hàm của mày, hàm trong obj đưa vào
    //if viết chỗ khác, viết ở obj khác, class khác
    //tực là chơi với DI, mình phải tạo ít nhất 1 class khác chứa if chứa
    //dependency, ràng buộc, filter, bộ lọc, cách bạn muốn xử lí info
    //class ??? này viết ntn? if để trong hàm nào, nhiều if thì sao
    //if nằm trong bao nhiêu hàm thì đủ??
    //không thể mỗi if 1 hàm và trong 1 classs, nếu thế thì hàm gétStudent()
    //lại phải tùy từn query type, mà phải chọn if tương ứng -> lặp lại
    //CHỐT HẠ: OBJECT ĐƯA VÀO CHỈ 1 HÀM IF
    //      for(với mỗi sv x tìm thấy trong danh sách đang có)
    //          if(obj.check(x) là đúng tiêu chuâ thfi
    //              add x vào WishList
    //          không đúng tiêu chuẩn move next sv tiếp theo
    
    
    //check() là hàm có if, nhưng bao nhiêu if trong check()
    //hóa ra bao nhiêu if ta lại viết trong check(), VỚ VẪN TẬP 2
    //check() không có code, ai muốn viết thế nào thì viết như thês
    //theo nhu cầu rieeng, check() không viết if luuon
    //lúc nào ai đó thực sự muốn filter, viết if cho check() 
    //lúc đó viết code cho check() implement một abstract method
    //mùi của Interface/Abstract class xuất hiện
    
    //hóa ra thay vì em phụ thuộc vào 1 cái gì cụ thể
    //ta chỉ phụ thuộc vào cái chung chung, ko chi tiết, abstract
    //LOOSE COUPLING
    //HIGH COHESION LOW COUPLING - SOLID - mỗi thằng làm 1 nhiệm vụ nhất định
    //nhưng liên kết lỏng lẻo với nhau
    
    //}

}
