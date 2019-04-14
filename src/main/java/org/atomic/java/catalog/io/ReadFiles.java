package org.atomic.java.catalog.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ReadFiles {

    public void traverseFolder(String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("File Folder is empty!");
                return;
            } else {
                for (File item : files) {
                    if (item.isDirectory()) {
                        System.out.println("Folder:" + item.getAbsolutePath());
                        traverseFolder(item.getAbsolutePath());
                    } else {
                        System.out.println("File:" + item.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("Not fount files!");
        }
    }

    public static List<File> getFileList(String strPath) {
        List fileList = new LinkedList();
        File dir = new File(strPath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("jpg") || fileName.endsWith("jpeg")) { // 判断文件名是否以.avi结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("[logger] out file, name=" + strFileName);
                    fileList.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        return fileList;
    }

    //delete all file in dir
    public static boolean deleteFile(String delPath) throws FileNotFoundException, IOException {
        try {
            File file = new File(delPath);
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] fileList = file.list();
                for (int i = 0; i < fileList.length; i++) {
                    File delFile = new File(delPath +"\\"+ fileList[i]);
                    if (!delFile.isDirectory()) {
                        System.out.println("path=" + delFile.getPath());
                        System.out.println("absolutepath=" + delFile.getAbsolutePath());
                        System.out.println("name=" + delFile.getName());
                        delFile.delete();
                        System.out.println("success delete.");
                    } else if (delFile.isDirectory()) {
                        deleteFile(delPath + "\\" + fileList[i]);
                    }
                }
                file.delete();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

}


