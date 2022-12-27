package cn.edu.swu.common;

import java.io.File;

public class SystemTest {
    public static void main(String[] args) {
        File tmpdir=new File(System.getProperty("java.io.tmpdir"));
        System.out.println(tmpdir.getAbsolutePath());
    }
}
