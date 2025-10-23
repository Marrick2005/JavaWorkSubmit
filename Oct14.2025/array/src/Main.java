public class Main {
    public static void main(String args[]) {
        // 动态初始化的对象数组所有元素的内容全部为null
        Book [] books = new Book[3] ; // 开辟3个长度的对象数组
        books[0] = new Book("计算机科学与技术学院", "马如君", 20.05) ; // 对象实例化
        books[1] = new Book("数据科学与大数据技术", "马如君",12.95 ) ; // 对象实例化
        books[2] = new Book("2410160121", "马如君", 99.8) ; // 对象实例化
        for (Book book : books) {
            System.out.println(book.getInfo()) ;
        }
    }
}