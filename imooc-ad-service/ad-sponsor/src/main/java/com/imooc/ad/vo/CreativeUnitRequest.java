package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author chenqiang
 * @create 2020-06-10 13:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUnitRequest {

    private List<CreativeUnit> creativeUnits;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreativeUnit{
        private Long creativeId;
        private Long unitId;
    }
}
