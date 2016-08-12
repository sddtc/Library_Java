package sddtc.library.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Author sddtc
 * Date 16/8/12
 */
public class FileIOTest {
    final static String filePath = "/sddtc/sddtc.mp4";
    final static String des1 = "/sddtc/sddtc.mp4.copy";
    final static String des2 = "/sddtc/sddtc.mp4.copy2";
    final static String des3 = "/sddtc/sddtc.mp4.copy3";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 8; i++) {
            baseCopy(filePath, des1);
        }
        System.out.println(System.currentTimeMillis() - start);

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 8; i++) {
            baseCopy(filePath, des2);
        }
        System.out.println(System.currentTimeMillis() - start2);

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 8; i++) {
            baseCopy(filePath, des3);
        }
        System.out.println(System.currentTimeMillis() - start3);
    }

    private static void baseCopy(String filePath, String des) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File sourceFile = new File(filePath);
            File desFile = new File(des);
            if (!desFile.exists()) {
                desFile.createNewFile();
            }
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(desFile);
            byte[] buf = new byte[512];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fis.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void nioBaseCopy(String filePath, String des) {
        FileChannel sc = null;
        FileChannel dc = null;
        try {
            File sourceFile = new File(filePath);
            File desFile = new File(des);
            if (!desFile.exists()) {
                desFile.createNewFile();
            }
            FileInputStream fis = new FileInputStream(sourceFile);
            FileOutputStream fos = new FileOutputStream(desFile);
            sc = fis.getChannel();
            dc = fos.getChannel();
            dc.transferFrom(sc, 0, sc.size());
            sc.close();
            dc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                sc.close();
                dc.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void nioCopy(String filePath, String des) {
        FileChannel sc = null;
        FileChannel dc = null;
        try {
            File sourceFile = new File(filePath);
            File desFile = new File(des);
            if (!desFile.exists()) {
                desFile.createNewFile();
            }
            FileInputStream fis = new FileInputStream(sourceFile);
            FileOutputStream fos = new FileOutputStream(desFile);
            sc = fis.getChannel();
            dc = fos.getChannel();
            MappedByteBuffer mbb = sc.map(FileChannel.MapMode.READ_ONLY, 0, sc.size());
            dc.write(mbb);
            sc.close();
            dc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                sc.close();
                dc.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
