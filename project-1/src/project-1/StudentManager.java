package Practice1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class StudentManager {
    private Map<String, Student> students = new HashMap<>();
    private Map<String, Student> getStudents() {
        return this.students;
    }

    public int getTotalNumber() {
        return this.getStudents().size();
    }
    public void printAll() {
        Map<String, Student> ss = this.getStudents();
        //System.out.println(ss);
        for (Iterator<String> it = ss.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            Student student = ss.get(key);
//          System.out.println(student.getName());
            System.out.println(String.format("学号：%s, 姓名：%s, 语文成绩：%d, 数学成绩：%d, 英语成绩：%d ,总成绩：%d ",
                    student.getID(), student.getName(),student.getChineseScore(),
                    student.getMathScore(),student.getEnglishScore(),student.getAllScore()));
        }
    }
    public void writeMathScore(List<Student> students){
        Map<String, Student> ss = this.getStudents();
        for (int i = 0; i < students.size(); i++) {
            ss.get(students.get(i).getID()).MathScore=students.get(i).getMathScore();
            //.MathScore=students.get(i).getMathScore();
        }

    }

    public void writeEnglishScore(List<Student> students){
        Map<String, Student> ss = this.getStudents();
        for (int i = 0; i < students.size(); i++) {
            ss.get(students.get(i).getID()).EnglishScore=students.get(i).getEnglishScore();
            ss.get(students.get(i).getID()).AllScore=ss.get(students.get(i).getID()).EnglishScore+
                                                        ss.get(students.get(i).getID()).MathScore+
                                                        ss.get(students.get(i).getID()).ChineseScore;
            //.MathScore=students.get(i).getMathScore();
        }
    }
    public void writeName(List<Student> students){
        Map<String, Student> ss = this.getStudents();
        for (int i = 0; i < students.size(); i++) {
            ss.get(students.get(i).getID()).name=students.get(i).getName(); //private怎么办
            //.MathScore=students.get(i).getMathScore();
        }

    }

    public void visit(IStudentVisitor visitor) {
        this.getStudents().forEach((key, val) -> {
            visitor.visit(val);
        });
    }

    public void addStudent(Student student) {
        //System.out.println(student.getName());
        this.students.put(student.getID(), student);
    }



//    private List list = new ArrayList();
//    private List getList(){ return this.list; }
//    public void addListStudent(Student student) {
//        this.list.add();
//    }





//    public void addChineseScore(Student student) {
//        this.students.put(student.getChineseScore(),student);
//    }
    public void deleteById(String id) {
        this.getStudents().remove(id);
    }
    public Student getStudentById(String id) {
        return this.students.get(id);
    }

    public List<Student> findByScore(String classType, int min, int max){
        Map<String, Student> ss = this.getStudents();
        List<Student> student1 = new ArrayList<Student>();
        //System.out.println(ss);
        int num = 0;
        if(classType=="Chinese") num=1;
        else if(classType=="Math") num=2;
        else if(classType=="English") num=3;
        else System.out.println("您的输入不合法，请输入英文并首字母大写");
        for (Iterator<String> it = ss.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            Student student = ss.get(key);
//          System.out.println(student.getName());
            if(num==1){
                if(student.getChineseScore()>=min&&student.getChineseScore()<=max){
                    student1.add(student);
                }
            }
            else if(num==2){
                if(student.getMathScore()>=min&&student.getMathScore()<=max){
                    student1.add(student);
                }
            }
            else if(num==3){
                if(student.getEnglishScore()>=min&&student.getEnglishScore()<=max){
                    student1.add(student);
                }
            }
        }
        return student1;
    }
    public List<Student> findByScore(int min, int max){
        int min1=min*3;
        int max1=max*3;
        Map<String, Student> ss = this.getStudents();
        List<Student> student1 = new ArrayList<Student>();
        for (Iterator<String> it = ss.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            Student student = ss.get(key);
            if(student.getAllScore()>=min1&&student.getAllScore()<=max1){
                student1.add(student);
            }
        }
        return student1;
    }


    public List<Student> sortByTotal(){
        Map<String, Student> ss = this.getStudents();
        List<Student> student1 = new ArrayList<Student>();
        for (Iterator<String> it = ss.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            Student student = ss.get(key);
            student1.add(student);
        }
        List<Map.Entry<String, Student>> list = new ArrayList<>(ss.entrySet());
        Collections.sort(list,
                (Map.Entry<String, Student> e1, Map.Entry<String, Student> e2) -> {
                    if(e1.getValue().getAllScore() < e2.getValue().getAllScore()) return 1;
                    else if(e1.getValue().getAllScore() > e2.getValue().getAllScore()) return -1;
                    else return 0;
                });
        // 打印映射中的元素
        for(int i=0; i<list.size(); i++) {
            System.out.println("第一名："+list.get(i).getValue());
        }
        return student1;
    }
    public void saveAs(String filePath) throws IOException {
        Map<String, Student> ss = this.getStudents();
        FileWriter writer = new FileWriter(filePath);
        for (Iterator<String> it = ss.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            Student student = ss.get(key);
            writer.write(String.format("%s, %s, %d, %d, %d, %d\n",
                    student.getName(), student.getID(),student.getChineseScore(),
                    student.getMathScore(),student.getEnglishScore(),student.getAllScore()));
        }
        writer.close();
    }


}
