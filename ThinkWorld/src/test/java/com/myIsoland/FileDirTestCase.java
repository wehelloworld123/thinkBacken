package com.myIsoland;

import cn.hutool.core.date.DateTime;
import com.myIsoland.common.file.FileUploadUtils;
import org.apache.tomcat.jni.Time;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileDirTestCase {
    @Test
    public void testSend(){
       System.out.print(FileUploadUtils.getRoot_dir());
    }
}
