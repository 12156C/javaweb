package Practice1;

import java.io.*;
import java.nio.charset.Charset;

//抽象类不能直接new，找抽象类的实现类（子类）   什么是抽象类

//reader基本上都是用来读文本文件的

//public class APP {
//    public static void main(String[] args) throws FileNotFoundException {
//        APP app=new APP();
//        String filepath= "C:/students.txt";
//        try {
//            app.readStudentFromFile2(filepath); //1和2都可以，两种不同方式
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    public void readStudentFromFile2(String filepath) throws IOException {
//        FileReader fileReader=new FileReader(filepath, Charset.forName("UTF-8")) ;
//        BufferedReader reader=new BufferedReader(fileReader);
//        String line=null;
//        while((line=reader.readLine())!=null){
//            if(!line.trim().isEmpty()) {
//                Student student = this.creatStudent(line);
//
//                System.out.println(student.toString());
//            }
//        }
//        reader.close();
//    }
//
//
//    public void readStudentFromFile(String filepath) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream(filepath);
//        InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"utf-8"); //21：33
//        int data;
//        String line="";
//        int cnt=1;
//        while((data=inputStreamReader.read())!=-1){
//            if(data=='\n') {
//                if(!line.trim().isEmpty()) {
//                    Student student = this.creatStudent(line);
//                    System.out.println(student.toString());
//                }
//                line="";
//                cnt++;
//            }
//            else{
//                line+=(char)data;
//            }
//            //System.out.print((char)data);
//        }
//
//        if(inputStreamReader != null){
//            inputStreamReader.close();
//        }
//    }
//    private Student creatStudent(String line){
//        String[] values=line.split("\t");
//        Student student=new Student();
//        student.setID(values[0]);
//        student.setName(values[1]);
//        return student;
//    }
//}

/**/