package cn.edu.swu.book;

public class Book {
    private long id;
    private String name;
    private String author;
    private float price;
    private String describe;
    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name= name;
    }

    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author= author;
    }

    public float getPrice(){
        return price;
    }
    public void setPrice(Float price){
        this.price= price;
    }

    public String getDescribe(){
        return describe;
    }
    public void setDescribe(String describe){
        this.describe= describe;
    }

}
