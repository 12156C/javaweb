package Practice1;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class StudentReaderFactory {
    public static IStudentReader create(String uri) throws MalformedURLException {
        StudentAbstractReader reader;
        if (uri.toLowerCase().startsWith("http")) {
            reader = new StudentHttpReader(new URL(uri));
        } else {
            reader = new StudentFileReader(new File(uri));
        }
        return reader;
    }
}
