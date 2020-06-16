package com.imooc.ad.service;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.Application;
import com.imooc.ad.constant.CommonStatus;
import com.imooc.ad.dao.AdPlanRepository;
import com.imooc.ad.dao.AdUnitRepository;
import com.imooc.ad.dao.CreativeRepository;
import com.imooc.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.imooc.ad.dao.unit_condition.AdUnitItRepository;
import com.imooc.ad.dao.unit_condition.AdUnitKeyWordRepository;
import com.imooc.ad.dao.unit_condition.CreativeUnitRepository;
import com.imooc.ad.dump.DConsatant;
import com.imooc.ad.dump.table.*;
import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.entity.AdUnit;
import com.imooc.ad.entity.Creative;
import com.imooc.ad.entity.unit_condition.AdUnitDistrict;
import com.imooc.ad.entity.unit_condition.AdUnitIt;
import com.imooc.ad.entity.unit_condition.AdUnitKeyword;
import com.imooc.ad.entity.unit_condition.CreativeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenqiang
 * @create 2020-06-16 11:15
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DumpDataService {

    @Autowired
    private AdPlanRepository adPlanRepository;
    @Autowired
    private AdUnitRepository adUnitRepository;
    @Autowired
    private CreativeRepository creativeRepository;
    @Autowired
    private CreativeUnitRepository creativeUnitRepository;
    @Autowired
    private AdUnitItRepository adUnitItRepository;
    @Autowired
    private AdUnitDistrictRepository adUnitDistrictRepository;
    @Autowired
    private AdUnitKeyWordRepository adUnitKeyWordRepository;

    @Test
    public void dumpDataToFile() {
        dumpAdPlanTable(String.format("%s%s",
                DConsatant.DATA_ROOT_DIR, DConsatant.AD_PLAN));
        dumpAdUnitTable(String.format("%s%s",
                DConsatant.DATA_ROOT_DIR, DConsatant.AD_UNIT));
        dumpAdCreativeTable(String.format("%s%s",
                DConsatant.DATA_ROOT_DIR, DConsatant.AD_CREATIVE));
        dumpAdCreativeUnitTable(String.format("%s%s",
                DConsatant.DATA_ROOT_DIR, DConsatant.AD_CREATIVE_UNIT));
        dumpAdUnitDistrictTable(String.format("%s%s",
                DConsatant.DATA_ROOT_DIR, DConsatant.AD_UNIT_DISTRICT));
        dumpAdUnitItTable(String.format("%s%s",
                DConsatant.DATA_ROOT_DIR, DConsatant.AD_UNIT_IT));
        dumpAdUnitKeywordTable(String.format("%s%s",
                DConsatant.DATA_ROOT_DIR, DConsatant.AD_UNIT_KEYWORD));
    }

    public void dumpAdPlanTable(String fileName) {
        List<AdPlan> adPlans = adPlanRepository.findAllByPlanStatus(CommonStatus.VALID.getStatus());
        if (CollectionUtils.isEmpty(adPlans)) {
            return;
        }
        List<AdPlanTable> adPlanTables = new ArrayList<>();
        adPlans.forEach(p -> adPlanTables.add(
                new AdPlanTable(
                        p.getId(),
                        p.getUserId(),
                        p.getPlanStatus(),
                        p.getStartDate(),
                        p.getEndDate()

                )));
        //将得到的数据库中的数据导出到文件
        try {
            Path path = Paths.get(fileName);
            BufferedWriter writer = Files.newBufferedWriter(path);
            for (AdPlanTable planTable : adPlanTables) {
                writer.write(JSON.toJSONString(planTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpAdPlanTable error");
        }
    }

    public void dumpAdUnitTable(String fileName) {
        List<AdUnit> adUnits = adUnitRepository.findAllByUnitStatus(CommonStatus.VALID.getStatus());
        if (CollectionUtils.isNotEmpty(adUnits)) {
            return;
        }
        List<AdUnitTable> adUnitTables = new ArrayList<>();
        adUnits.forEach(u -> adUnitTables.add(new AdUnitTable(
                u.getId(),
                u.getUnitStatus(),
                u.getPositionType(),
                u.getPlanId()
        )));
        try {
            Path path = Paths.get(fileName);
            BufferedWriter writer = Files.newBufferedWriter(path);
            for (AdUnitTable unitTable : adUnitTables) {
                writer.write(JSON.toJSONString(unitTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpAdUnitTable error");
        }
    }

    public void dumpAdCreativeTable(String fileName) {
        List<Creative> creatives = creativeRepository.findAll();
        if (CollectionUtils.isNotEmpty(creatives)) {
            return;
        }
        List<AdCreativeTable> adCreativeTables = new ArrayList<>();
        creatives.forEach(u -> adCreativeTables.add(new AdCreativeTable(
                u.getId(),
                u.getName(),
                u.getType(),
                u.getMaterialType(),
                u.getHeight(),
                u.getWidth(),
                u.getAuditStatus(),
                u.getUrl()
        )));
        try {
            Path path = Paths.get(fileName);
            BufferedWriter writer = Files.newBufferedWriter(path);
            for (AdCreativeTable creativeTable : adCreativeTables) {
                writer.write(JSON.toJSONString(creativeTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpAdCreativeTable error");
        }
    }

    public void dumpAdCreativeUnitTable(String fileName) {
        List<CreativeUnit> creativeUnits = creativeUnitRepository.findAll();
        if (CollectionUtils.isNotEmpty(creativeUnits)) {
            return;
        }
        List<AdCreativeUnitTable> adCreativeUnitTables = new ArrayList<>();
        creativeUnits.forEach(u -> adCreativeUnitTables.add(new AdCreativeUnitTable(
                u.getCreativeId(),
                u.getUnitId()
        )));
        try {
            Path path = Paths.get(fileName);
            BufferedWriter writer = Files.newBufferedWriter(path);
            for (AdCreativeUnitTable adCreativeUnitTable : adCreativeUnitTables) {
                writer.write(JSON.toJSONString(adCreativeUnitTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpAdCreativeUnitTable error");
        }
    }

    public void dumpAdUnitItTable(String fileName) {
        List<AdUnitIt> adUnitIts = adUnitItRepository.findAll();
        if (CollectionUtils.isNotEmpty(adUnitIts)) {
            return;
        }
        List<AdUnitItTable> adUnitItTables = new ArrayList<>();
        adUnitIts.forEach(u -> adUnitItTables.add(new AdUnitItTable(
                u.getUnitId(),
                u.getItTag()
        )));
        try {
            Path path = Paths.get(fileName);
            BufferedWriter writer = Files.newBufferedWriter(path);
            for (AdUnitItTable adUnitItTable : adUnitItTables) {
                writer.write(JSON.toJSONString(adUnitItTables));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpAdUnitItTable error");
        }
    }

    public void dumpAdUnitDistrictTable(String fileName) {
        List<AdUnitDistrict> adUnitDistricts = adUnitDistrictRepository.findAll();
        if (CollectionUtils.isNotEmpty(adUnitDistricts)) {
            return;
        }
        List<AdUnitDistrictTable> adUnitDistrictTables = new ArrayList<>();
        adUnitDistricts.forEach(u -> adUnitDistrictTables.add(new AdUnitDistrictTable(
                u.getUnitId(),
                u.getProvince(),
                u.getCity()
        )));
        try {
            Path path = Paths.get(fileName);
            BufferedWriter writer = Files.newBufferedWriter(path);
            for (AdUnitDistrictTable adUnitDistrictTable : adUnitDistrictTables) {
                writer.write(JSON.toJSONString(adUnitDistrictTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpAdUnitDistrictTable error");
        }
    }

    public void dumpAdUnitKeywordTable(String fileName) {
        List<AdUnitKeyword> adUnitKeywords = adUnitKeyWordRepository.findAll();
        if (CollectionUtils.isNotEmpty(adUnitKeywords)) {
            return;
        }
        List<AdUnitKeywordTable> adUnitKeywordTables = new ArrayList<>();
        adUnitKeywords.forEach(u -> adUnitKeywordTables.add(new AdUnitKeywordTable(
                u.getUnitId(),
                u.getKeyword()
        )));
        try {
            Path path = Paths.get(fileName);
            BufferedWriter writer = Files.newBufferedWriter(path);
            for (AdUnitKeywordTable adUnitKeywordTable : adUnitKeywordTables) {
                writer.write(JSON.toJSONString(adUnitKeywordTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpAdUnitKeywordTable error");
        }
    }
}
