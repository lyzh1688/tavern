package com.tuoyou.tavern.common.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/13 <br>
 */
@Slf4j
public class FileUtils {

    private final static int BUFFER_SIZE = 1024 * 1024;

    public static String multiPartFileWriter(MultipartFile file, String location) throws Exception {
        BufferedOutputStream buffStream = null;
        try {
            File locationFile = new File(location);
            if (!locationFile.exists()) {
                locationFile.mkdirs();
            }
            String fileName = file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            buffStream = new BufferedOutputStream(new FileOutputStream(new File(StringUtils.join(location, "/", fileName))));
            buffStream.write(bytes);
            buffStream.close();
            return StringUtils.join(location, "/", fileName);
        } catch (Exception e) {
            log.error("FileUtils-->multiPartFileWriter-->Error: {}", e.getMessage());
            throw e;
        } finally {
            try {
                if (buffStream != null) {
                    buffStream.close();
                }
            } catch (IOException e1) {
                log.error("FileUtils-->multiPartFileWriter-->Error: {}", e1.getMessage());
            }
        }
    }

    /**
     * zip解压
     *
     * @param srcFile     zip源文件
     * @param destDirPath 解压后的目标文件夹
     * @throws RuntimeException 解压失败会抛出运行时异常
     */
    public static String unZip(File srcFile, String destDirPath) throws Exception {
        long start = System.currentTimeMillis();
        destDirPath = StringUtils.join(destDirPath, "/", StringUtils.substringBeforeLast(srcFile.getName(), "."));
        File dirFile = new File(destDirPath);
        if (!dirFile.isDirectory()) {
            dirFile.mkdirs();
        }
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            log.error("FileUtils-->unZip-->Error: {}", "所指文件不存在");
            throw new Exception("所指文件不存在:" + srcFile.getPath());
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile, Charset.forName("GBK"));
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[BUFFER_SIZE];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
            long end = System.currentTimeMillis();
            log.info("解压完成，耗时：{}", (end - start), " ms");
        } catch (Exception e) {
            log.error("unzip error from ZipUtils-->Error: {}", e.getMessage());
            throw e;
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    log.error("close zip file Error: {}", e.getMessage());
                }
            }
        }
        return destDirPath;
    }

}
