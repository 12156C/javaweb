package Practice1;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

class StudentHttpReader extends StudentAbstractReader {
    private URL url;
    public StudentHttpReader(URL url){
        this.setUrl(url);
    }
    public InputStream getInputStream() throws IOException{
        URLConnection connection=this.getUrl().openConnection();
        InputStream inputStream=connection.getInputStream();
        return inputStream;
    }

    public URL getUrl(){
        return url;
    }
    public void setUrl(URL url){
        this.url=url;
    }
}
