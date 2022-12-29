package cn.edu.swu.object;

public class Object {
    private long id;
    private String name;
    private int num;
    private String org;
    private String user;
    private String describe;
    private String time1;
    private String time2;
    private int deal;

    public int getNum(){
        return num;
    }
    public void setNum(int num){
        this.num=num;
    }


    public String getUser(){
        return user;
    }
    public void setUser(String user){
        this.user=user;
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

    public String getOrg(){
        return org;
    }
    public void setOrg(String author){
        this.org= author;
    }

    public int getDeal(){
        return deal;
    }
    public void setDeal(int deal){
        this.deal= deal;
    }

    public String getTime1(){
        return time1;
    }
    public void setTime1(String time1){
        this.time1= time1;
    }
    public String getTime2(){
        return time2;
    }
    public void setTime2(String time2){
        this.time2= time2;
    }

}
