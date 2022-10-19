package Practice1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

abstract class StudentAbstractReader implements IStudentReader {
    public abstract InputStream getInputStream() throws IOException;

    @Override
    public List<Student> read() throws IOException {
        List<Student> students=new ArrayList<>();
        try(InputStreamReader inputStreamReader = new InputStreamReader(this.getInputStream()))
        {
            try(BufferedReader reader=new BufferedReader(inputStreamReader))
            {
                String line=null;
                while((line= reader.readLine())!=null){
                    if(line.trim().isEmpty()) continue; //这步在干什么？？？
                    Student student =this.createStudent(line);
                    students.add(student);
                }
            }
        }
        return  students;
    };

    public List<Student> read1() throws IOException {
        List<Student> students=new ArrayList<>();
        try(InputStreamReader inputStreamReader = new InputStreamReader(this.getInputStream()))
        {
            try(BufferedReader reader=new BufferedReader(inputStreamReader))//读字节——》字符-》一行
            {
                String line=null;
                while((line= reader.readLine())!=null){
                    if(line.trim().isEmpty()) continue; //这步在干什么？？？
                    Student student =this.createStudent1(line);
                    students.add(student);
                }
            }
        }
        return  students;
    };

    public List<Student> read2() throws IOException {
        List<Student> students=new ArrayList<>();
        try(InputStreamReader inputStreamReader = new InputStreamReader(this.getInputStream()))
        {
            try(BufferedReader reader=new BufferedReader(inputStreamReader))
            {
                String line=null;
                while((line= reader.readLine())!=null){
                    if(line.trim().isEmpty()) continue; //这步在干什么？？？
                    Student student =this.createStudent2(line);
                    students.add(student);
                }
            }
        }
        return  students;
    };

    public List<Student> read3() throws IOException {
        List<Student> students=new ArrayList<>();
        try(InputStreamReader inputStreamReader = new InputStreamReader(this.getInputStream()))
        {
            try(BufferedReader reader=new BufferedReader(inputStreamReader))
            {
                String line=null;
                while((line= reader.readLine())!=null){
                    if(line.trim().isEmpty()) continue; //这步在干什么？？？
                    Student student =this.createStudent3(line);
                    students.add(student);
                }
            }
        }
        return  students;
    };

    //统一进行解析
    private Student createStudent(String line){
        String[] vals=line.split("\t");
        Student student=new Student();
        student.setID(vals[0]);
        student.setChineseScore(vals[1]); //此时其他成绩没有值，为null
        return student;
    }
    private Student createStudent1(String line){
        String[] vals=line.split("\t");
        Student student=new Student();
        student.setID(vals[0]);
        student.setMathScore(vals[1]);
        return student;
    }
    private Student createStudent2(String line){
        String[] vals=line.split("\t");
        Student student=new Student();
        student.setID(vals[0]);
        student.setEnglishScore(vals[1]);
        return student;
    }
    private Student createStudent3(String line){
        String[] vals=line.split("\t");
        Student student=new Student();
        student.setID(vals[0]);
        student.setName(vals[1]);
        return student;
    }
}
