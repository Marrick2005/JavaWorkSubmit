abstract class Book {	// 抽象类
    private String title ;
    private String author ;
    private double price ;

    public Book() {}
    public Book(String title, String author, double price) {
        this.title = title ;
        this.author = author ;
        this.price = price ;
    }
    public String toString() {	// 定义普通方法
        return "图书名称：" + this.title + "、图书作者：" + this.author + "、图书价格：" + this.price ;
    }
}
class MathBook extends Book {	// 继承父类
    private String type ; // 分类
    public MathBook() {}
    public MathBook(String title, String author, double price, String type) {
        super(title, author, price) ; // 调用父类构造
        this.type = type ; // 保存子类的属性内容
    }
    public String toString() {
        return super.toString() + "、分类：" + this.type ;
    }
}
public class Main { //
    public static void main(String args[]) {
        Book book = new MathBook("大数据2401", "马如君", 24, "计算机") ; // 对象实例化
        System.out.println(book) ;
    }
}
