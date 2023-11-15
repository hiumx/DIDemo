
package didemo;

import com.xuanhieu.di.dao.FilterBySE;
import com.xuanhieu.di.dao.StudentManager;
import com.xuanhieu.di.dto.Student;
import com.xuanhieu.di.util.Filter;
import java.util.List;

public class DIDemo {

    public static void main(String[] args) {
//        saveAStudent();
//        testStudentManager();
//        testStudentBySE();
//        testGetStudentsByGt5UsingAnonymous();
        testGetStudentsByLt5UsingLambdaExpression();
    }
    
    public static void saveAStudent() {
        Student x = new Student("DE170524", "Mai Xuân Hiếu", 2003, 9.0, "SE");
        x.showProfile();
    }
    
    public static void testStudentManager() {
        StudentManager sm = new StudentManager();
        
        System.out.println("List all student:");
        for (Student student : sm.getAll()) {
            student.showProfile();
        }
        
        System.out.println("List student(s) of SE major:");
        for (Student student : sm.getSEStudents()) {
            student.showProfile();
        }
    }
    
    //cách tạo class FilterSE rời rạc, làm riêng dù hay, nhưng hơi tốn
    //tạo class dùng không nhiều lần, vậy lược bớt thao tác tạo class
    //mục đích cuối cùng: cần code if của hàm check()
    //vì chỉ cần object.check() là đủ
    //2 giải pháp: Anonymous class, class ngầm hiểu, không cần tên gì cả
    //      mượn gió bẻ măng, mượn Interface new luôn
    //          bung rộng gõ code @Override
    
    public static void testGetStudentsByLt5UsingLambdaExpression() {
        StudentManager sm = new StudentManager();
        //lambda - hàm vô danh của class vô danh
        
        for (Student student : sm.getStudents(s -> s.getMajor().equalsIgnoreCase("SE")
                                && s.getGpa() < 5)) {
            student.showProfile();
        }
    }
    
    public static void testGetStudentsByGt5UsingAnonymous() {
        StudentManager sm = new StudentManager();
        Filter<Student> gt5 = new Filter<Student>() {
            @Override
            public boolean check(Student t) {
                return t.getGpa() >= 5 && t.getMajor().equalsIgnoreCase("SE");
            }
        };
        for (Student student : sm.getStudents(gt5)) {
            student.showProfile();
        }
    }
    
    public static void testStudentBySE() {
        StudentManager sm = new StudentManager();
        Filter seFilter = new FilterBySE();
//        for (Student student : sm.getStudents(seFilter)) {
//            
//        }
        List<Student> result = sm.getStudents(seFilter);
        for (Student student : result) {
            student.showProfile();
        }
    }
}


//SELECT * FROM STUDENT WHERE ???
//                            ràng buộc trên dữ liệu
//                            phụ thuộc trên tập dữ liệu
//dependency/filter/condition
//tui muốn đưa cho câu lệnh SELECT 1 ràng buộc
//chỉ lấy ra sv ngành SE
//bạn đưa ràng buộc tớ sẽ trả về kq
//inject một cái dependency/loại filter
//CHỈ 1 LỆNH, TƯƠNG THÍCH N THỨ AI ĐÓ MUỐN
//LỎNG LẺO, MỞ VỚI TẤT CẢ, NHƯNG PHỤ THUỘC VÀO CHÚNG, PHỤ THUỘC MÀ LỎNG
//****LOOSE COUPLING



//BÌNH LUẬN KHI VIẾT CÁC HÀM ỨNG VỚI CÁC CÂU QUERY
//cách 1
//1.Ưu điểm: Dễ viết, cần query thế nào thì thêm hàm tương ứng, với filter/dependency
//tương ứng gài trong hàm
//2.Nhược điểm: nhiều hàm, quá nhiều hàm và tương lai còn thêm nữa 
//theo nhu cầu thống kê

//cách 2
//Thiết kế tốt hơn chút, ít hàm hơn được không?? mf vẫn nhiều filter
//gộp nhiều filter trong 1 hàm
//Ưu điểm: dễ viết như cách 1, gom toàn bộ các hàm vào 1 hàm
//Nhược điểm: vẫn không dự đoán hết được các cases, các trường hợp
//muốn filter là vô chừng, theo cách người xài hàm người ta muốn sài

//cách 3: DEPENDENCY ĐÚNG NGHĨA
// LẬT NGƯỢC VẤN ĐỀ: TẠI SAO MÌNH/HÀM NÀY LẠI PHẢI DỰ ĐOÁN HẾT
//NHU CẦU FILTER CỦA NGƯỜI TA??? - VÔ CHỪNG FILTER
// TAO - MANAGER CÓ DATA, NHƯNG KHÔNG CÓ FILTER
// MÀY - NGƯỜI XÌA HÀM, MÀY CÓ FILTER - VÔ CHỪNG
// MÀY MUỐN GÌ, CỤ THỂ LÀ GÌ, MÀY CỨ CHUẨN BỊ LUÔN, ĐƯA VÀO CHO
// TAO, TAO CĂN THEO CÁI MÀY MUỐN, LẤY RA STUDENT TƯƠNG ỨNG THEO 
// TIÊU CHÍ CỦA MÀY
// MÀY MUỐN IF THẾ NÀO, MÀY LÀM LUÔN ĐI, TAO KHÔNG DỰ ĐOÁN NỮA
// BẠN ĐƯA CÁI BẠN MUỐN VÀO TRONG MANAGER CỦA TỚ, TỚ LÔI TỪNG SINH VIÊN
// XÉT THEO TIÊU CHÍ CỦA BẠN , TỚ RETURN

// HIỆN NÀY: TỚ DỰ ĐOÁN LUÔN TIÊU CHÍ CỦA BẠN, TỰ KIỂM TRA IF Ó THỎA HAY KHÔNG
// TỚ LUÔN BỊ RƯỢT, LÂU LÂU BỎ SUNG THÊM HÀM THEO NHU CẦU CỦA NGƯỜI DÙNG (NHU CẦU THỐNG KÊ)

//==> IF NẰM Ở ĐẦU VÀO CỦA HÀM, TUI CHỈ GỌI IF ĐỂ BIẾT RẰNG
//SV CỦA TA THỎA IF ĐƯA VÀO HAY KHÔNG, THỎA THÌ TRẢ RA
//KHÔNG THỎA ĐI TIẾP QUA SV KHÁC
// CHỐT HẠ: IF ĐƯA TỪ BÊN NGOÀI VÀO, KHÔNG PHẢI LÀ INT ĐƯA VÀO ĐỂ TA TỰ LO
// bên ngoài đưa tiêu chsi xét duyệt vào bên trong, hàm ta theo
// tiêu chí bên ngoài đưa vào mà lấy ra các SV tương ứng

//HÀM EM CHUẨN BỊ DATA VÀ XÉT DUYỆT THEO BÊN NGOÀI
//ĐƯA IF BÊN NGOÀI VÀO?? ĐƯA OBJECT, ĐƯA ĐỐI TƯỢNG VÀO
//SỜ IF QUA CHẤM CỦA ĐỐI TƯỢNG
