package com.microstudy.otherservice;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class OtherServiceApplicationTests {
//    @Autowired
//    OSSClient ossClient;
//
//    @Test
//    public void Testupload() throws FileNotFoundException {
//
//        // 上传文件流。
//        InputStream inputStream = new FileInputStream("C:\\Users\\xiang\\Desktop\\12958.png");
//
//        ossClient.putObject("file-leave", "1111.png", inputStream);
//        System.out.print("upload suc");
//        // 关闭OSSClient。
//        ossClient.shutdown();
//    }
    @Test
    void contextLoads() {
    }

}
