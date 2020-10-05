package ru.itis.javalab;

//import ru.itis.words.utils.Info;
import com.beust.jcommander.JCommander;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Deque;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
/*
https://www.iguides.ru/upload/medialibrary/188/1882580f9432c743b6b7a116def5036b.jpg
https://img5.goodfon.ru/wallpaper/nbig/4/da/gory-tsvety-leto-priroda.jpg
https://klike.net/uploads/posts/2019-11/1574607325_2.jpg
https://klike.net/uploads/posts/2019-11/1574606248_3.jpg
https://tastybar.ru/wa-data/public/blog/plugins/logopost/images/4R7CkuKBBb.png
https://cdn.lifehacker.ru/wp-content/uploads/2017/02/cupofcoffee_1487243336.jpg

ссылки в строку через запятую
https://www.iguides.ru/upload/medialibrary/188/1882580f9432c743b6b7a116def5036b.jpg,https://img5.goodfon.ru/wallpaper/nbig/4/da/gory-tsvety-leto-priroda.jpg,https://klike.net/uploads/posts/2019-11/1574607325_2.jpg,https://klike.net/uploads/posts/2019-11/1574606248_3.jpg,https://tastybar.ru/wa-data/public/blog/plugins/logopost/images/4R7CkuKBBb.png,https://cdn.lifehacker.ru/wp-content/uploads/2017/02/cupofcoffee_1487243336.jpg
*/

//javac -cp lib/jcommander-1.78.jar -d target/ src/java/ru/itis/words/*/*.java
//cd target
//jar -cvfm download.jar ../src/manifest.txt . 
//java -jar download.jar  --mode=multi-thread --count=2 --files=https://img5.goodfon.ru/wallpaper/nbig/4/da/gory-tsvety-leto-priroda.jpg,https://klike.net/uploads/posts/2019-11/1574607325_2.jpg --folder=C:/images



    public static void main(String[] argv) {
        Args args = new Args();

        JCommander.newBuilder().addObject(args).build().parse(argv);

        String mode = args.mode;

        int threadsCount = 1;
        if (mode.equals("multi-thread")) {
            threadsCount = args.count;
        }

        String[] s = args.files.split(",");
        int n = s.length;

        Deque<Info> sites = new ConcurrentLinkedDeque<>();
        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);

        for (int i = 0; i < n; i++) {
            sites.add(new Info(sites.size(), s[i]));
        }

        //String path = "C:\\myPrograms\\javalab\\JARS1\\";
        String path = args.folder;

        for (int j = 0; j < threadsCount; j++) {
            synchronized (sites) {
                executorService.submit(() -> {
                    while(!sites.isEmpty()) {
                        Info info = sites.pop();
                        System.out.println(Thread.currentThread().getName() + " photo " + info.getNum() + ": start ");
                        String strPath = path.replace("/","\\\\") + "\\\\" + info.getNum() + ".jpg";
                        downloadFiles(info.getSite(), strPath, 1);
                        System.out.println(Thread.currentThread().getName() + " photo " + info.getNum() + ": end");
                    }
                });
            }
        }
    }

    public static void downloadFiles(String strURL, String strPath, int buffSize) {
        try {
            URL connection = new URL(strURL);
            HttpURLConnection urlconn;
            urlconn = (HttpURLConnection) connection.openConnection();
            urlconn.setRequestMethod("GET");
            urlconn.connect();

            InputStream in = null;
            in = urlconn.getInputStream();
            File file = new File(strPath);

            if(!file.exists()) {
                file.createNewFile();
            }

            OutputStream writer = new FileOutputStream(file);
            byte buffer[] = new byte[buffSize];
            int c = in.read(buffer);
            while (c > 0) {
                writer.write(buffer, 0, c);
                c = in.read(buffer);
            }
            writer.flush();
            writer.close();
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
