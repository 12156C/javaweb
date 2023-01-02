package cn.edu.swu.object;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.swu.object.Object;
import cn.edu.swu.object.ObjectRepo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


@WebServlet("/saveObject")
public class SaveObjectServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        System.out.println(name);
        int num=Integer.valueOf(request.getParameter("num"));
        String org=request.getParameter("org");
        String user=request.getParameter("user");
        String time1=request.getParameter("time1");
        String time2=request.getParameter("time2");
        String deal=request.getParameter("deal");

        Object object=new Object();
        object.setName(name);
        object.setNum(num);
        object.setOrg(org);
        object.setUser(user);
        object.setTime1(time1);
        object.setTime2(time2);
        object.setDeal(deal);
        System.out.println(String.format("%s %s %s %s %s %s %s",name,num,org,user,time1,time2,deal));

        if(id!=null){
            object.setId(Long.valueOf(id));
        }

        String message=null;
        try {
            ObjectRepo.getInstance().saveObject(object);
            message="借用申请已提交！";
        } catch (SQLException e) {
            message="提交信息保存失败！";
            System.out.println(e);
        }

        response.setContentType("text/html;charset=UTF-8");
        try(Writer writer=response.getWriter()){
            String html="<center style='margin-top:5em'><h1>%s</h1></center><br>"+
                    "<a href='./submit-book.html'><center>继续申请借用</center></a>&nbsp;&nbsp;&nbsp;" +
                    "<a href='./borrow/table/index.html'><center>显示列表</center></a>&nbsp;&nbsp;&nbsp;" +
                    "<a href='./index.html'><center>返回首页</center></a><br>";
            writer.write(String.format(html,message));
        }

    }


}
