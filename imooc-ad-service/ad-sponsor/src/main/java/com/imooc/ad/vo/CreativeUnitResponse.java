package com.imooc.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author chenqiang
 * @create 2020-06-10 13:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUnitResponse {

    private List<Long> ids;
}
