package com.myIsoland.common.util;

import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.*;
import com.myIsoland.enums.RecomType;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class CaculateUtils {

    private static final String SYMBOLS = "0123456789"; // 数字

    private static final Random RANDOM = new SecureRandom();

    /**
     * 获取长度为 6 的随机数字
     *
     * @return 随机数字
     * @date 修改日志：由 space 创建于 2018-8-2 下午2:43:51
     */
    public static String getNonce_str(int length) {

        // 如果需要4位，那 new char[4] 即可，其他位数同理可得
        char[] nonceChars = new char[length];

        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }

        return new String(nonceChars);

    }

    public static String translateId(String prefix,String no){
        if(StringUtils.isEmpty(no)){
            return  null;
        }
        if(no.indexOf(prefix)!=-1) {
            return no.substring(prefix.length());
        }else {
            return no;
        }
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
        if(data.getLiterContent()!=null){
            data.getLiterContent().setNo(CaculateUtils.translateId(ProjectConstant.CLITERPREFIX,
                    data.getLiterContent().getNo()));
            data.getLiterContent().setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,
                    data.getLiterContent().getCreateBy()));
        }
        if (data.getCharpts()!=null){
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
            if (item.getCharpt()!=null){
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
        if(data.getRecommends()!=null){
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
            if(literContent.getRecommends()!=null){
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
        if (data.getPart()!=null){
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
            if (item.getPart()!=null){
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
        if(data.getPaintContent()!=null){
            data.getPaintContent().setNo(CaculateUtils.translateId(ProjectConstant.CPAINTINGPREFIX,
                    data.getPaintContent().getNo()));
            data.getPaintContent().setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,
                    data.getPaintContent().getCreateBy()));
        }
        if (data.getParts()!=null){
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
            if(part.getPaintContent()!=null){
                part.getPaintContent().setNo(CaculateUtils.translateId(ProjectConstant.CPAINTINGPREFIX,
                        part.getPaintContent().getNo()));
                part.getPaintContent().setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,
                        part.getPaintContent().getCreateBy()));
            }
            if (part.getParts()!=null) {
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
        if(data.getRecommends()!=null){
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
            if(paintContent.getRecommends()!=null){
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
        if(data.getPoemContent()!=null){
            data.getPoemContent().setNo(CaculateUtils.translateId(ProjectConstant.CPOETRYPREFIX,
                    data.getPoemContent().getNo()));
            data.getPoemContent().setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,
                    data.getPoemContent().getCreateBy()));
        }
        if (data.getSets()!=null){
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
            if(poemSet.getPoemContent()!=null){
                poemSet.getPoemContent().setNo(CaculateUtils.translateId(ProjectConstant.CPOETRYPREFIX,
                        poemSet.getPoemContent().getNo()));
                poemSet.getPoemContent().setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,
                        poemSet.getPoemContent().getCreateBy()));
            }
            if (poemSet.getSets()!=null) {
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
        if (data.getPoemSet()!=null){
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
            if (item.getPoemSet()!=null){
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
        if(data.getRecommends()!=null){
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
            if(literContent.getRecommends()!=null){
                for(Recommend item : literContent.getRecommends()){
                    item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
                }
            }
        }
        return data;
    }

    /**
     *@Author:THINKPAD
     *@Description:消除创作推荐列表前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.Recommend
     *@Data:23:05 2020/1/30
     **/
    public static List<Recommend> deleteRecomPrefix(List<Recommend> data) {
        for (Recommend recom : data) {
            if(RecomType.LITERATURE.equals(RecomType.valueOf(recom.getKind())))
                recom.setContentId(CaculateUtils.translateId(ProjectConstant.CLITERPREFIX,recom.getContentId()));
            else if(RecomType.PAINTING.equals(RecomType.valueOf(recom.getKind())))
                recom.setContentId(CaculateUtils.translateId(ProjectConstant.CPAINTINGPREFIX,recom.getContentId()));
            else if(RecomType.POEMTY.equals(RecomType.valueOf(recom.getKind())))
                recom.setContentId(CaculateUtils.translateId(ProjectConstant.CPOETRYPREFIX,recom.getContentId()));
            recom.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX, recom.getCreateBy()));
            if(recom.getComments()!=null){
                for(Comment item : recom.getComments()){
                    item.setReplyId(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getReplyId()));
                    item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
                }
            }
        }
        return data;
    }

    /**
     *@Author:THINKPAD
     *@Description:消除创作推荐列表前缀
     * @param data
     *@Return:com.myIsoland.enitity.product.Recommend
     *@Data:23:05 2020/1/30
     **/
    public static List<Comment> deleteComPrefix(List<Comment> data) {
        for (Comment comment : data) {
                comment.setReplyId(CaculateUtils.translateId(ProjectConstant.USERPREFIX,comment.getReplyId()));
                comment.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,comment.getCreateBy()));
        }
        return data;
    }
}
