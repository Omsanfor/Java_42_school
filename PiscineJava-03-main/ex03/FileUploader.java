import java.io.*;
import java.net.URL;
import java.util.List;


public class FileUploader implements Runnable {
    private List<URL> urls;
    private static volatile int index = 0;
    private static int idThreads = 1;
    private int id;

    public FileUploader(List<URL> urls) {
        this.urls = urls;
        id  = idThreads;
        idThreads++;
    }

    @Override
    public void run() {
        try {
            loading();
        } catch (FileUploader.ExceptionSuccess e) {
            System.out.println("========");
        }
    }

    private void loading() throws FileUploader.ExceptionSuccess {
        int currentIndex;
        URL url;
        InputStream input;
        FileOutputStream writer;
        byte [] data;
        int byteContent;
        while (true) {
            currentIndex = getCurrentIndex();
            System.out.println("Thread " + id + " start download file " + "number " + (currentIndex + 1));
            url = urls.get(currentIndex);
            try {
                input = url.openStream();
                File newFile = new File(url.getFile().substring(url.getFile().lastIndexOf("/") + 1));
                newFile.createNewFile();
                writer = new FileOutputStream(newFile.getAbsolutePath());
                data = new byte[1024];

                while ((byteContent = input.read(data, 0, 1024)) != -1) {
                    writer.write(data, 0, byteContent);
                }
                writer.close();
                input.close();
                System.out.println("Thread" + id + " finish download file " + "number " + (currentIndex + 1));
            }catch(IOException e){
                System.out.println("Ошибка загрузки файла №" + (currentIndex + 1));
                continue;
            }
        }
    }

    private synchronized int getCurrentIndex() throws FileUploader.ExceptionSuccess {
        int index = this.index;
        if (index < urls.size())
            this.index++;
        else
            throw new FileUploader.ExceptionSuccess();
        return index;
    }

    private class ExceptionSuccess extends Throwable {
        @Override
        public String getMessage() {
            return "Success, all file download";
        }
    }
}
