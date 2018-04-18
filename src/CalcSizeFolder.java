import java.io.File;
import java.io.IOException;

public class CalcSizeFolder {

    //nhận vào tên và cấp độ.
    public static String out(String name, int level) {
        String s = "|";
        for (int i = 0; i < level; i++) {
            s += "__|";
        }
        return s += name;
    }

    //Kiểm tra đường dẫn truyền vào có tồn tại không.
    public static long dirView(String source, int lev) throws IOException {
        File sfile = new File(source);
        String size = " _size:";
        String by = "(bytes)";
        long len = 0;
        if (!sfile.exists()) {
            System.out.println("source not found....");
        }
        // nếu là file thì in ra tên file và dung lượng
        if (sfile.isFile()) {
            size += sfile.length() + "_";
            len += sfile.length();
            System.out.println(out(sfile.getName() + size + by, lev));
        }
        //Nếu đường dẩn chỉ đến là thư mục thì tao ra danh sách file và duyệt danh sách và đệ quy gọi lại phương thức dirView()
        if (sfile.isDirectory()) {
            File[] list = sfile.listFiles();
            if (list != null) {
                for (File f : list) {
                    len += dirView(f.getCanonicalPath(), lev + 1);
                }
            }
        }
        //In ra dung lượng của folder.
        System.out.println(out(sfile.getName() + size + len + by, lev));
        return len;
    }
    public static void main(String[] args) throws IOException {
        dirView("D:\\", 0);
    }
}
