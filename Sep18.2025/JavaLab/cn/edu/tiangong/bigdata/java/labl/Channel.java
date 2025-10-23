package cn.edu.tiangong.bigdata.java.labl; // 包声明
public class Channel { 
	private Message message ; // 消息
	public Channel(String title, String content) {
		this.message = new Message(this, title, content) ;
		this.message.send() ; // 消息发送
	}
	public boolean connect() {	// 通道的连接
		System.out.println("【Channel】建立消息的发送通道...") ;
		return true ;
	}
	public void close() {	// 关闭通道
		System.out.println("【Channel】关闭消息通道...") ;
	}// 改为public
    // 原Book类代码（private属性、构造方法、getInfo()等）不变
}
