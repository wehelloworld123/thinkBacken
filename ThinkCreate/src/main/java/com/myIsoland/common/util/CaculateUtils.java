package com.myIsoland.common.util;

import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.*;

import java.util.List;

public class CaculateUtils {

    public static String translateId(String prefix,String no){
        if(StringUtils.isEmpty(no)){
            return  null;
        }
        int length = prefix.length();
        return no.substring(length);
    }

    public static String subStr(String content,int length){
        if(!StringUtils.isEmpty(content)){
            return content.substring(0,length);
        }
        return content;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除文学章节前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:23:05 2020/1/30
     **/
    public static LiterCharpt deletePrefix(LiterCharpt data){
        data.setBookId(CaculateUtils.translateId(ProjectConstant.LITERPREFIX,data.getBookId()));
        data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
        if(!data.getLiterContent().equals(null)){
            data.getLiterContent().setNo(CaculateUtils.translateId(ProjectConstant.CLITERPREFIX,
                    data.getLiterContent().getNo()));
            data.getLiterContent().setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,
                    data.getLiterContent().getCreateBy()));
        }
        if (!data.getCharpts().equals(null)){
            for(LiterCharpt item :data.getCharpts()){
                data.setBookId(CaculateUtils.translateId(ProjectConstant.LITERPREFIX,data.getBookId()));
                data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
            }
        }
        return data;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除文学章节列表前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:23:05 2020/1/30
     **/
    public static List<LiterCharpt> deleteChartpsPrefix(List<LiterCharpt> data) {
        for (LiterCharpt literCharpt : data) {
            literCharpt.setBookId(CaculateUtils.translateId(ProjectConstant.LITERPREFIX, literCharpt.getBookId()));
            literCharpt.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX, literCharpt.getCreateBy()));
            if(literCharpt.getLiterContent()!=null){
                literCharpt.getLiterContent().setNo(CaculateUtils.translateId(ProjectConstant.CLITERPREFIX,
                        literCharpt.getLiterContent().getNo()));
                literCharpt.getLiterContent().setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,
                        literCharpt.getLiterContent().getCreateBy()));
            }
            if (literCharpt.getCharpts()!=null) {
                for (LiterCharpt item : literCharpt.getCharpts()) {
                    item.setBookId(CaculateUtils.translateId(ProjectConstant.LITERPREFIX, item.getBookId()));
                    item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX, item.getCreateBy()));
                }
            }
        }
        return data;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除文学前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:23:05 2020/1/30
     **/
    public static Literature deletePrefix(Literature data){

        data.setUid(CaculateUtils.translateId(ProjectConstant.LITERPREFIX,data.getUid()));
        data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
        if (data.getCharpt()!=null){
            data.getCharpt().setBookId(CaculateUtils.translateId(ProjectConstant.LITERPREFIX,data.getCharpt().getBookId()));
        }
        return data;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除文学列表前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:23:05 2020/1/30
     **/
    public static List<Literature> deleteLiteraturePrefix(List<Literature> data){
        for(Literature item :data){
            item.setUid(CaculateUtils.translateId(ProjectConstant.LITERPREFIX,item.getUid()));
            item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
            if (!item.getCharpt().equals(null)){
                item.getCharpt().setBookId(CaculateUtils.translateId(ProjectConstant.LITERPREFIX,item.getCharpt().getBookId()));
            }
        }
        return data;
    }

    /**
     *@Author:THINKPAD
     *@Description:消除文学创作前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:23:05 2020/1/30
     **/
    public static LiterContent deletePrefix(LiterContent data){
        data.setNo(CaculateUtils.translateId(ProjectConstant.CLITERPREFIX,data.getNo()));
        data.setBookId(CaculateUtils.translateId(ProjectConstant.LITERPREFIX,data.getBookId()));
        data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
        if(!data.getRecommends().isEmpty()){
            for(Recommend item : data.getRecommends()){
                item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
            }
        }
        return data;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除文学创作列表前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:23:05 2020/1/30
     **/
    public static List<LiterContent> deleteListPrefix(List<LiterContent> data) {
        for (LiterContent literContent : data) {
            literContent.setNo(CaculateUtils.translateId(ProjectConstant.CLITERPREFIX,literContent.getNo()));
            literContent.setBookId(CaculateUtils.translateId(ProjectConstant.LITERPREFIX, literContent.getBookId()));
            literContent.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX, literContent.getCreateBy()));
            if(!literContent.getRecommends().isEmpty()){
                for(Recommend item : literContent.getRecommends()){
                    item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
                }
            }
        }
        return data;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除绘画前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:23:05 2020/1/30
     **/
    public static Painting deletePrefix(Painting data){

        data.setUid(CaculateUtils.translateId(ProjectConstant.PAINTINGPREFIX,data.getUid()));
        data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
        if (!data.getPart().equals(null)){
            data.getPart().setPaintId(CaculateUtils.translateId(ProjectConstant.PAINTINGPREFIX,data.getPart().getPaintId()));
        }
        return data;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除绘画列表前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.Painting
     *@Data:23:05 2020/1/30
     **/
    public static List<Painting> deletePaintingPrefix(List<Painting> data){
        for(Painting item :data){
            item.setUid(CaculateUtils.translateId(ProjectConstant.PAINTINGPREFIX,item.getUid()));
            item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
            if (!item.getPart().equals(null)){
                item.getPart().setPaintId(CaculateUtils.translateId(ProjectConstant.PAINTINGPREFIX,item.getPart().getPaintId()));
            }
        }
        return data;
    }


    /**
     *@Author:THINKPAD
     *@Description:消除艺术绘画部分前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.PaintingPart
     *@Data:23:05 2020/1/30
     **/
    public static PaintingPart deletePrefix(PaintingPart data){
        data.setPaintId(CaculateUtils.translateId(ProjectConstant.PAINTINGPREFIX,data.getPaintId()));
        data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
        if(!data.getPaintContent().equals(null)){
            data.getPaintContent().setNo(CaculateUtils.translateId(ProjectConstant.CPAINTINGPREFIX,
                    data.getPaintContent().getNo()));
            data.getPaintContent().setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,
                    data.getPaintContent().getCreateBy()));
        }
        if (!data.getParts().equals(null)){
            for(PaintingPart item :data.getParts()){
                data.setPaintId(CaculateUtils.translateId(ProjectConstant.PAINTINGPREFIX,data.getPaintId()));
                data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
            }
        }
        return data;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除艺术绘画章节列表前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.PaintingPart
     *@Data:23:05 2020/1/30
     **/
    public static List<PaintingPart> deletePartsPrefix(List<PaintingPart> data) {
        for (PaintingPart part : data) {
            part.setPaintId(CaculateUtils.translateId(ProjectConstant.PAINTINGPREFIX, part.getPaintId()));
            part.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX, part.getCreateBy()));
            if(!part.getPaintContent().equals(null)){
                part.getPaintContent().setNo(CaculateUtils.translateId(ProjectConstant.CPAINTINGPREFIX,
                        part.getPaintContent().getNo()));
                part.getPaintContent().setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,
                        part.getPaintContent().getCreateBy()));
            }
            if (!part.getParts().equals(null)) {
                for (PaintingPart item : part.getParts()) {
                    item.setPaintId(CaculateUtils.translateId(ProjectConstant.PAINTINGPREFIX, item.getPaintId()));
                    item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX, item.getCreateBy()));
                }
            }
        }
        return data;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除文学创作前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:23:05 2020/1/30
     **/
    public static PaintContent deletePrefix(PaintContent data){
        data.setNo(CaculateUtils.translateId(ProjectConstant.CPAINTINGPREFIX,data.getNo()));
        data.setPaintId(CaculateUtils.translateId(ProjectConstant.PAINTINGPREFIX,data.getPaintId()));
        data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
        if(!data.getRecommends().isEmpty()){
            for(Recommend item : data.getRecommends()){
                item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
            }
        }
        return data;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除艺术绘画创作列表前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.PaintContent
     *@Data:23:05 2020/1/30
     **/
    public static List<PaintContent> deletePaintsPrefix(List<PaintContent> data) {
        for (PaintContent paintContent : data) {
            paintContent.setNo(CaculateUtils.translateId(ProjectConstant.CPAINTINGPREFIX,paintContent.getNo()));
            paintContent.setPaintId(CaculateUtils.translateId(ProjectConstant.PAINTINGPREFIX, paintContent.getPaintId()));
            paintContent.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX, paintContent.getCreateBy()));
            if(!paintContent.getRecommends().isEmpty()){
                for(Recommend item : paintContent.getRecommends()){
                    item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
                }
            }
        }
        return data;
    }


    /**
     *@Author:THINKPAD
     *@Description:消除诗歌章节前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.PoemSet
     *@Data:23:05 2020/1/30
     **/
    public static PoemSet deletePrefix(PoemSet data){
        data.setPoetryId(CaculateUtils.translateId(ProjectConstant.POETRYPREFIX,data.getPoetryId()));
        data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
        if(!data.getPoemContent().equals(null)){
            data.getPoemContent().setNo(CaculateUtils.translateId(ProjectConstant.CPOETRYPREFIX,
                    data.getPoemContent().getNo()));
            data.getPoemContent().setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,
                    data.getPoemContent().getCreateBy()));
        }
        if (!data.getSets().equals(null)){
            for(PoemSet item :data.getSets()){
                data.setPoetryId(CaculateUtils.translateId(ProjectConstant.POETRYPREFIX,data.getPoetryId()));
                data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
            }
        }
        return data;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除诗歌章节列表前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.PoemSet
     *@Data:23:05 2020/1/30
     **/
    public static List<PoemSet> deleteSetsPrefix(List<PoemSet> data) {
        for (PoemSet poemSet : data) {
            poemSet.setPoetryId(CaculateUtils.translateId(ProjectConstant.POETRYPREFIX, poemSet.getPoetryId()));
            poemSet.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX, poemSet.getCreateBy()));
            if(!poemSet.getPoemContent().equals(null)){
                poemSet.getPoemContent().setNo(CaculateUtils.translateId(ProjectConstant.CPOETRYPREFIX,
                        poemSet.getPoemContent().getNo()));
                poemSet.getPoemContent().setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,
                        poemSet.getPoemContent().getCreateBy()));
            }
            if (!poemSet.getSets().equals(null)) {
                for (PoemSet item : poemSet.getSets()) {
                    item.setPoetryId(CaculateUtils.translateId(ProjectConstant.POETRYPREFIX, item.getPoetryId()));
                    item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX, item.getCreateBy()));
                }
            }
        }
        return data;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除诗歌前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.Poetry
     *@Data:23:05 2020/1/30
     **/
    public static Poetry deletePrefix(Poetry data){

        data.setUid(CaculateUtils.translateId(ProjectConstant.POETRYPREFIX,data.getUid()));
        data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
        if (!data.getPoemSet().equals(null)){
            data.getPoemSet().setPoetryId(CaculateUtils.translateId(ProjectConstant.LITERPREFIX,data.getPoemSet().getPoetryId()));
        }
        return data;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除诗歌列表前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.Poetry
     *@Data:23:05 2020/1/30
     **/
    public static List<Poetry> deletePoetryPrefix(List<Poetry> data){
        for(Poetry item :data){
            item.setUid(CaculateUtils.translateId(ProjectConstant.POETRYPREFIX,item.getUid()));
            item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
            if (!item.getPoemSet().equals(null)){
                item.getPoemSet().setPoetryId(CaculateUtils.translateId(ProjectConstant.POETRYPREFIX,item.getPoemSet().getPoetryId()));
            }
        }
        return data;
    }

    /**
     *@Author:THINKPAD
     *@Description:消除诗歌创作前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.PoemContent
     *@Data:23:05 2020/1/30
     **/
    public static PoemContent deletePrefix(PoemContent data){
        data.setNo(CaculateUtils.translateId(ProjectConstant.CPOETRYPREFIX,data.getNo()));
        data.setPoetryId(CaculateUtils.translateId(ProjectConstant.POETRYPREFIX,data.getPoetryId()));
        data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
        if(!data.getRecommends().isEmpty()){
            for(Recommend item : data.getRecommends()){
                item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
            }
        }
        return data;
    }
    /**
     *@Author:THINKPAD
     *@Description:消除诗歌创作列表前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.PoemContent
     *@Data:23:05 2020/1/30
     **/
    public static List<PoemContent> deletePoemsPrefix(List<PoemContent> data) {
        for (PoemContent literContent : data) {
            literContent.setNo(CaculateUtils.translateId(ProjectConstant.CPOETRYPREFIX,literContent.getNo()));
            literContent.setPoetryId(CaculateUtils.translateId(ProjectConstant.POETRYPREFIX, literContent.getPoetryId()));
            literContent.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX, literContent.getCreateBy()));
            if(!literContent.getRecommends().isEmpty()){
                for(Recommend item : literContent.getRecommends()){
                    item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
                }
            }
        }
        return data;
    }

}
