package Practice1;

import java.io.IOException;
import java.util.List;

public interface IStudentReader {
    //接口，负责从抽象源中读出并返回List
    /*
    *
    * 根据其写了三个类
    * StudentAbstractReader:给了数据流就能读取信息
    * 先准备一个接收器（返回对象），读一个存入一个
    * 读取字符串要用Reader或者Writer
    * 设计模式为装饰者模式
    * 本质上对操作没有改变，只是在包装增加功能
    *
    * Student
    *
    *
    * */
    public List<Student> read() throws IOException;
    public List<Student> read1() throws IOException;
    public List<Student> read2() throws IOException;
    public List<Student> read3() throws IOException;


    /*
    *
    * 写IscoreReader
    * ScoreHttpReader
    * 再写ScoreAbstractReader
    *再写ScoreReaderFactor
    *
    * */
}
