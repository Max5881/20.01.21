package ru.sapteh;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Program {
    public static void main(String[] args) throws IOException {
        Path sourceZipFile = Paths.get("C:/java_42/zipArchive.zip");
        Path unZip = Paths.get("C:/java_42/ZipUn222");

        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(sourceZipFile.toFile()));
        ZipFile zipFile = new ZipFile(sourceZipFile.toFile());

        List<ZipEntry> zipList = new ArrayList<>();
        ZipEntry zipEntry;

        while((zipEntry = zipInputStream.getNextEntry())!= null){
            zipList.add(zipEntry);
        }
        System.out.println(zipList);
        for (ZipEntry entry : zipList){
            Path absolutePath = concatPath(Paths.get(entry.toString()),unZip);
            if (entry.isDirectory()){
                Files.createDirectory(absolutePath);
            }else if (Files.notExists(absolutePath)){
                InputStream inputStream = zipFile.getInputStream(entry);
                Files.copy(inputStream,absolutePath);
                System.out.println("Робит?");
            }
        }
        zipInputStream.close();
    }
    public static Path concatPath(Path pathArchive,Path pathFile){
        return pathFile.resolve(pathArchive);
    }
    public static String parentDirectory (String path){
        int index = path.indexOf("/");
        return path.substring(0,index+1);
    }
}
