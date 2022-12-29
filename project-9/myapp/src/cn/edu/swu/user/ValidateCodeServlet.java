package cn.edu.swu.user;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@WebServlet("/verifyCode")
public class ValidateCodeServlet  extends HttpServlet {
    private final static String CODES="abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLNOPQRSTUVWXYZ";
    private final static int WIDTH=100;
    private final static int HEIGHT=40;
    private final static String IMAGE_TYPE="png";
    private final static int CODE_SIZE=5;

    public final static String LOGIN_VERIFY_CODE="login_verify_code";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code=this.randomCode(CODE_SIZE);

        HttpSession session=request.getSession(true);
        session.setAttribute(LOGIN_VERIFY_CODE,code);

        BufferedImage image=drawImage(WIDTH,HEIGHT,code);
        try(OutputStream outputStream=response.getOutputStream()){
            ImageIO.write(image,IMAGE_TYPE,outputStream);
        }
    }

    public BufferedImage drawImage(int width,int height,String code) throws IOException {


        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Graphics g2d=image.getGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,width,height);

        g2d.setColor(Color.GRAY);

        g2d.drawRect(0,0,width-1,height-1);

        Font font=new Font("Arial",Font.BOLD|Font.ITALIC,18);
        g2d.setFont(font);
        g2d.setColor(Color.decode("#8f438b"));
        g2d.drawString(code,20,28);

        return image;
    }

    public String randomCode(int size){
        StringBuilder sb=new StringBuilder();
        Random random=new Random();
        for(int i=0;i<size;++i){
            sb.append(CODES.charAt(random.nextInt(CODES.length())));
        }
        return sb.toString();
    }

}
