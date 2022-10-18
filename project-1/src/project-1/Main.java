package Practice1;

import java.io.IOException;
import java.util.List;

public class Main {
    private static StudentManager studentManager = null;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.startup("http://139.186.26.196/javaweb/data/chinese.txt");
        main.startup1("http://139.186.26.196/javaweb/data/math.txt");
        main.startup2("http://139.186.26.196/javaweb/data/english.txt");
        main.startup3("C:/students.txt");
        //http://10.122.7.154/javaweb/data/students.txt
        main.execute();
        main.shutdown();
    }
    public void startup(String uri) throws IOException {
        List<Student> students = this.loadData(uri);
        this.studentManager = this.createManager(students);
    } //从网上下载下来的是列表类型的
   public void startup1(String uri) throws IOException {
        List<Student> students = this.loadData1(uri);
        //System.out.println(students);
        this.studentManager.writeMathScore(students);
    }
    public void startup2(String uri) throws IOException {
        List<Student> students = this.loadData2(uri);
       // System.out.println(students.get(1).getEnglishScore());
        this.studentManager.writeEnglishScore(students);
    }
    public void startup3(String uri) throws IOException {
        List<Student> students = this.loadData3(uri);
      //  System.out.println(students.get(1).getEnglishScore());
        this.studentManager.writeName(students);
    }
    public void shutdown() {
        System.out.println();
        System.out.println();
        System.out.println("     代码结束啦！！！");
        System.out.println();
        for(int i=0,k=0; i<14;i++){//打印行
            //上部分 上分为 四个部分
            if(i<3){
                for(int j=0;j<5-2*i;j++){//1、空心
                    System.out.print(" ");
                }
                if(i==2){//2、*
                    for(int j=0;j<6+4*i-1;j++){
                        System.out.print("*");
                    }
                    for(int j=0;j<7-4*i+2;j++){//3、空心
                        System.out.print(" ");
                    }
                    for(int j=0;j<6+4*i-1;j++){//4、*
                        System.out.print("*");
                    }
                }else{
                    for(int j=0;j<6+4*i;j++){//2、*
                        System.out.print("*");
                    }
                    for(int j=0;j<7-4*i;j++){//3、空心
                        System.out.print(" ");
                    }
                    for(int j=0;j<6+4*i;j++){//4、*
                        System.out.print("*");
                    }
                }
            }else if(i<6){//中间
                for(int j=0;j<29;j++){
                    System.out.print("*");
                }
            }else{//下部分 6
                if(i==13){
                    for(int j=0;j<2*(i-6);j++){//打印空格
                        System.out.print(" ");
                    }
                    System.out.print("*");
                }else{
                    for(int j=0;j<2*(i-6)+1;j++){//打印空格
                        System.out.print(" ");
                    }
                    for(int j=1;j<28-4*k;j++){
                        System.out.print("*");
                    }
                    k++;
                }
            }
            System.out.println();//换行
        }
    }
    public void execute() throws IOException {

        System.out.println("*******************************************************************************************");
        System.out.println("现在打印全部信息：");
        this.studentManager.printAll();
        System.out.println("*******************************************************************************************");
        System.out.println("现在输出学生总数：");
        System.out.println("Total Student: " + this.studentManager.getTotalNumber());
        System.out.println("*******************************************************************************************");
        System.out.println("现在查询学号为222021321072003同学的信息：");
        String id = "222021321072003";
        Student student = studentManager.getStudentById(id);
        this.findStudent(student);
        System.out.println("*******************************************************************************************");
        System.out.println("现在查询英语成绩在90——100分范围之内的同学：");
        List<Student> studentScore=this.studentManager.findByScore("English",90,100);
        System.out.println("输出分数段范围列表"+studentScore);
        System.out.println("*******************************************************************************************");
        System.out.println("现在查询平均成绩在90——100分范围之内的同学：");
        List<Student> average=this.studentManager.findByScore(90,100);
        System.out.println("*******************************************************************************************");
        System.out.println("现在按照总成绩进行排序：");
        List<Student> allScoreSort=this.studentManager.sortByTotal();
        System.out.println("*******************************************************************************************");
        System.out.println("现在进行储存");
        this.studentManager.saveAs("D:/students.txt");
        System.out.println("储存完毕");
        System.out.println("*******************************************************************************************");



//        System.out.println(student.toString());
//        studentManager.deleteById(id);
//        System.out.println("Total Student: " + studentManager.getTotalNumber());
//
//        this.studentManager.visit(new IStudentVisitor() {
//            @Override
//            public void visit(Student student) {
//                if (student.getName().startsWith("李")) {
//                    System.out.println(student.toString());
//                }
//            }
//        });
    }
    public void findStudent(Student student){
        System.out.println("查找到："+student.getName());
        System.out.println(String.format("学号：%s, 姓名：%s, 语文成绩：%d, 数学成绩：%d, 英语成绩：%d ,总成绩：%d ",
                student.getID(), student.getName(),student.getChineseScore(),
                student.getMathScore(),student.getEnglishScore(),student.getAllScore()));
    }
    public List<Student> loadData(String uri) throws IOException {
        IStudentReader reader = StudentReaderFactory.create(uri);
        for (int i = 0; i < reader.read().size(); i++) {
           // System.out.println(reader.read().get(i).getChineseScore());
        }
        return reader.read();
    }
    public List<Student> loadData1(String uri) throws IOException {
        IStudentReader reader = StudentReaderFactory.create(uri);
//        for (int i = 0; i < reader.read1().size(); i++) {
//           System.out.println(reader.read1().get(i).getMathScore());
//           System.out.println(reader.read1().get(i).getID());
//        }
        return reader.read1();
    }
    public List<Student> loadData2(String uri) throws IOException {
        IStudentReader reader = StudentReaderFactory.create(uri);
//        for (int i = 0; i < reader.read().size(); i++) {
//            System.out.println(reader.read().get(i).getName());
//        }
        return reader.read2();
    }
    public List<Student> loadData3(String uri) throws IOException {
        IStudentReader reader = StudentReaderFactory.create(uri);
//        for (int i = 0; i < reader.read().size(); i++) {
//            System.out.println(reader.read().get(i).getName());
//        }
        return reader.read3();
    }

    public StudentManager createManager(List<Student> students) {
        StudentManager manager = new StudentManager();
//        for (int i = 0; i < students.size(); i++) {
//            System.out.println(students.get(i).getName());
//        }
        students.forEach(s -> {
            manager.addStudent(s);
        }); //把列表类型的玩意加到manager中
        return manager;

    }
}
