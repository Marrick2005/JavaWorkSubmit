interface IBook {	// 定义接口
    public abstract void read() ; // 抽象方法
}
class MathBook implements IBook {
    public void read() {	// 抽象方法覆写
        System.out.println("天津工业大学2410160121马如君") ;
    }
}
public class Main { // 李兴华编程训练营：yootk.ke.qq.com
    public static void main(String args[]) {
        IBook book = new MathBook() ; // 通过子类进行对象实例化处理操作
        book.read() ; // 读书
    }
}
