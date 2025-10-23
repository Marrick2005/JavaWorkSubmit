interface IBook {	// 定义接口
    String SITE = "www.yootk.com" ;
    public void read() ; // 抽象方法
}
interface ISpec { // 规格信息
    public double size() ; // 尺寸
}
abstract class Print {	// 图书刊印的类
    public abstract void batch() ; // 批次印刷
}
class MathBook extends Print implements IBook, ISpec { // 接口的多实现
    public void read() {	// 抽象方法覆写
        System.out.println("计算机科学与技术") ;
    }
    public double size() {
        return 7.9 ;
    }
    public void batch() {	// 覆写抽象类中的方法
        System.out.println("数据科学与大数据技术") ;
    }
}
public class Main { // 李兴华编程训练营：yootk.ke.qq.com
    public static void main(String args[]) {
        IBook book = new MathBook() ;	// 向上转型
        // IBook和ISpec彼此之间没有任何的联系，但是此时的IBook的对象是通过MathBook子类实例化的
        // MathBook和ISpec接口之间有联系，所以以下的关系成立。
        ISpec spec = (ISpec) book ;
        System.out.println(spec.size()) ;
        Print print = (Print) spec ; // 接口和抽象类也没有直接关系
        print.batch() ;
    }
}
