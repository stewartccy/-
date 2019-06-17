package com.ccy.passbook.passbook.vo;

import com.ccy.passbook.passbook.constant.FeedbackType;
import com.google.common.base.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户评论
 * @author CCY
 * @date 2019/6/15 15:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    private Long userId;

    private String type;

    //passtemplate rowkey
    private String templateId;

    private String comment;

    public boolean validate(){
        FeedbackType feedbackType = Enums.getIfPresent(
                FeedbackType.class,this.type.toUpperCase()
        ).orNull();

        return !(null == feedbackType || null == comment);
    }

}
