package Practice1;

public class Student {
    int MathScore;
    private String SwuID;

    String name;  // 通过函数接口访问

    int EnglishScore;
    int ChineseScore;
    int AllScore;
    public Student(){
    }
    public Student(String SwuID,String name,String chineseScore,String mathScore,String englishScore,int allscore){
        this.setID(SwuID);
        this.setChineseScore(chineseScore);
        this.setName(name); // set、get到底是什么
        this.setMathScore(mathScore);
        this.setEnglishScore(englishScore);
        this.setAllScore(allscore);

    }
    public void setAllScore(int allScore){
        this.AllScore=allScore;
    }
    public int getAllScore(){
        return this.AllScore;
    }
    public void setMathScore(String mathScore){
        int intMathScore = Integer.parseInt(mathScore);
        this.MathScore=intMathScore;
    }
    public int getMathScore(){
        return this.MathScore;
    }
    public void setEnglishScore(String englishScore){
        int intEnglishScore = Integer.parseInt(englishScore);
        this.EnglishScore= intEnglishScore;
    }
    public int getEnglishScore(){
        return this.EnglishScore;
    }
    public void setChineseScore(String chineseScore){
        int intChineseScore = Integer.parseInt(chineseScore);
        this.ChineseScore=intChineseScore;
    }
    public int getChineseScore(){
        return this.ChineseScore;
    }

    public void setID(String id){
        this.SwuID=id;
    }
    public String getID(){
        return this.SwuID;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public String toString(){
        return String.format("%s,%s",this.SwuID,this.name);
    }

    public int compareTo(Student p2){
        if(this.AllScore > p2.AllScore){
            return 1;
        }else if(this.AllScore < p2.AllScore){
            return -1;
        }else{
            return 0;
        }
    }

/*



    void printAll(){
    }
    String getStudentByld(String id){
        return id;
    }
    String findByScore(String classType,int min,int max){
        return classType;
    }
    void saveAs(String filePath){
    }

}

class Run{
    public static void main(String[] args){

    }
 */
}
