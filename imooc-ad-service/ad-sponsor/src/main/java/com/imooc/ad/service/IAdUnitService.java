package com.imooc.ad.service;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.*;

/**
 * @author chenqiang
 * @create 2020-06-10 8:51
 */
public interface IAdUnitService {

    AdUnitResponse createAdUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createAdUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createAdUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createAdUnitDistrict(AdUnitDistrictRequest request) throws AdException;

    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException;
}
