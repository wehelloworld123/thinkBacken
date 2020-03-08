package com.myIsoland;

import com.SpringbootStart;
import com.myIsoland.common.file.FileUploadUtils;
import com.myIsoland.enitity.think.Quotation;
import com.myIsoland.service.think.QuotationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStart.class)
public class SaveImageTestCase {
    @Autowired
    private QuotationService quotationService;
    @Test
    public void test(){
        Quotation data = new Quotation();
        data.setContent("1111");
        data.setId(12131);
        System.out.println("SSSSSSS:"+data.getCreateBy());
        System.out.println("SSSSSSS:"+data.getCreateDat());
        System.out.println("SSSSSSS:"+data.getLUpdateDat());
        quotationService.SaveQuotation(data);
    }
}
