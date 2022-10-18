package Practice1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class StudentFileReader extends StudentAbstractReader{
    private File file;
    public StudentFileReader(File file){
        this.setFile(file);
    }
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.getFile());
    }
    public File getFile(){
        return file;
    }
    public void setFile(File file){
        this.file=file;
    }
}
