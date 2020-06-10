package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * @author chenqiang
 * @create 2020-06-10 8:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitRequest {

    private Long planId;
    private String unitName;
    private Integer positionType;
    private Long budget;

    public boolean createValidate() {
        if (budget < 0) {
            return false;
        }
        return planId != null && StringUtils.isEmpty(unitName)
                && positionType != null;
    }
}
