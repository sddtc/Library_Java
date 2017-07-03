package sddtc.library.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class FileIOTest {
    final static String filePath = "src/test/resources/15. CITY OF BLINDING LIGHT.mp3";
    final static String des1 = "src/test/resources/15. CITY OF BLINDING LIGHT.mp3.copy";
    final static String des2 = "src/test/resources/15. CITY OF BLINDING LIGHT.mp3.copy2";
    final static String des3 = "src/test/resources/15. CITY OF BLINDING LIGHT.mp3.copy3";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 8; i++) {
            baseCopy(filePath, des1);
        }
        System.out.println("base copy: ");
        System.out.println((System.currentTimeMillis() - start) + "ms");

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 8; i++) {
            nioBaseCopy(filePath, des2);
        }
        System.out.println("nio base copy: ");
        System.out.println((System.currentTimeMillis() - start2) + "ms");

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 8; i++) {
            nioCopy(filePath, des3);
        }
        System.out.println("nio copy: ");
        System.out.println((System.currentTimeMillis() - start3) + "ms");

        deleteCopyFiles();
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

    private static void nioBaseCopy(String filePath, String des) {
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

    private static void nioCopy(String filePath, String des) {
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

    private static void deleteCopyFiles() {
        try {
            Files.deleteIfExists(new File(des1).toPath());
            Files.deleteIfExists(new File(des2).toPath());
            Files.deleteIfExists(new File(des3).toPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
