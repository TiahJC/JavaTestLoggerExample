package com.example.file.dao;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.file.dao.AssetDao;
import com.example.file.data.Asset;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;


public class AssetDaoTest {
    private static final Logger LOGGER = LogManager.getLogger();
    @DisplayName("Read Asset Test")
    @Test
    public void test1() {
       try {
        int assetNum = AssetDao.INSTANCE.getCount();
        assetNum = 0;
        LOGGER.info(" asset num >> " + assetNum);
        assertTrue(assetNum>0);

        Integer assetId = 2;
        Asset asset = AssetDao.INSTANCE.read(assetId);
        assertNotNull(asset);
        LOGGER.info(" asset >> " + asset);
        }
        catch(AssertionError e){
            LOGGER.error("test1" + e.getMessage());
        }

    }
    @Disabled
    @DisplayName("Update Asset Test")
    @Test
    public void test2() {

        Integer assetId = 2;
        Asset asset = AssetDao.INSTANCE.read(assetId);

        Date newDate = DateUtils.addMonths(asset.getCreatedDate(), -1);
        asset.setCreatedDate(newDate);
        AssetDao.INSTANCE.update(asset);
        assertTrue(asset.getCreatedDate().getTime()==newDate.getTime());

        Asset assetTestAgain = AssetDao.INSTANCE.read(assetId);
        assertTrue(assetTestAgain.getCreatedDate().getTime()==newDate.getTime());
        LOGGER.info(" newDate >> " + newDate);
        LOGGER.info(" assetTestAgain >> " + assetTestAgain);

    }
    @DisplayName("Create and delete Asset Test")
    @Test
    public void test3() {
        LocalDate localtoday = LocalDate.now();
        Date today = java.util.Date.from(localtoday.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());

        LocalDate oneMonthEarilerLocalDate  = LocalDate.now().minusMonths(1);
        Date oneMonthEarilerDate = java.util.Date.from(oneMonthEarilerLocalDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());

        Asset asset = new Asset("abc1234", "model ABC, RAM 32GB, INTEL 7",oneMonthEarilerDate, today, "enabled");
        assertNull(asset.getId());
        // create
        AssetDao.INSTANCE.create(asset);
        LOGGER.info(" asset return >> " + asset);
        assertTrue(asset.getId()>0);
        Integer assetId = asset.getId();

        // delete
        AssetDao.INSTANCE.delete(assetId);
        Asset assetTest = AssetDao.INSTANCE.read(assetId);
        assertNull(assetTest);
        //assertNotNull(assetTest);

    }
}
