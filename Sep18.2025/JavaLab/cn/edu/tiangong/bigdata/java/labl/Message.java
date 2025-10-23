package cn.edu.tiangong.bigdata.java.labl; // 包声明
public class Message { 
	private String title ; // 要发送的标题
	private String content ; // 要发送的内容
	private Channel channel ; // 消息通道
	public Message(Channel channel, String title, String content) {
		this.title = title ;
		this.content = content ;
		this.channel = channel ;
	}
	public void send() {	// 进行消息的发送
		if (this.channel.connect()) {	// 判断通道是否可以连接
			System.out.println("【Message】消息发送，消息标题：" + this.title + "、消息内容：" + this.content) ;
			this.channel.close() ; // 关闭消息发送通道
		} else {
			System.out.println("【Message】没有可用的消息发送通道，消息发送失败...") ;
		}
	}// 改为public
    // 原Book类代码（private属性、构造方法、getInfo()等）不变
}
