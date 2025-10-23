class Book {
    private String title ;
    private String author ;
    private double price ;
    public Book(String title, String author, double price) {
        this.title = title ;
        this.author = author ;
        this.price = price ;
    }
    public String getInfo() {
        return "图书名称：" + this.title + "、图书作者：" + this.author + "、图书价格：" + this.price ;
    }
}