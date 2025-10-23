abstract class Book {	// 抽象类
    public abstract void read() ;
    public static Book getBook() {	// 静态方法
        return new MathBook() ;
    }
}
class MathBook extends Book {	// 继承父类
    public void read() {
        System.out.println("天津工业大学大数据2401") ;
    }
}
public class Main { // 李兴华编程训练营：yootk.ke.qq.com
    public static void main(String args[]) {
        Book book = Book.getBook() ; // 获取Book类的对象实例
        book.read() ;
    }
}
