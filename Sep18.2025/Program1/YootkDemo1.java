public class YootkDemo1 { // 主类
	public static void main(String args[]) {
		Book bookA = new Book() ; // 无参构造
		Book bookB = new Book("AA马如君") ;
		Book bookC = new Book("2410160121", "马如君", 2401) ;
		System.out.println(bookA.getInfo()) ;
		System.out.println(bookB.getInfo()) ;
		System.out.println(bookC.getInfo()) ;
		System.out.println("【main()方法】bookA = " + bookA);  // 直接进行对象的输出
		bookA.print() ; // 调用类中的普通方法
	}
}