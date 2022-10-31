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
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

@Path("/assets")
public class AssetDaoTestCopy {
    private static final Logger LOGGER = LogManager.getLogger();
    @DisplayName("Read Asset Test")
    @Test
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Asset test1(@PathParam("id") Integer id) {
       try {
        int assetNum = AssetDao.INSTANCE.getCount();
        // assetNum = 0;
        LOGGER.info(" asset num >> " + assetNum);
        assertTrue(assetNum>0);

        Integer assetId = id;
        Asset asset = AssetDao.INSTANCE.read(assetId);
        assertNotNull(asset);
        LOGGER.info(" asset >> " + asset);
        return asset;
        }
        catch(AssertionError e){
            LOGGER.error("test1" + e.getMessage());
            return null;
        }

    }
    // @Disabled
    @DisplayName("Update Asset Test")
    @Test
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Asset test2(@PathParam("id") Integer id) {

        Integer assetId = id;
        Asset asset = AssetDao.INSTANCE.read(assetId);

        Date newDate = DateUtils.addMonths(asset.getCreatedDate(), -1);
        asset.setCreatedDate(newDate);
        AssetDao.INSTANCE.update(asset);
        assertTrue(asset.getCreatedDate().getTime()==newDate.getTime());

        Asset assetTestAgain = AssetDao.INSTANCE.read(assetId);
        assertTrue(assetTestAgain.getCreatedDate().getTime()==newDate.getTime());
        LOGGER.info(" newDate >> " + newDate);
        LOGGER.info(" assetTestAgain >> " + assetTestAgain);
        return assetTestAgain;
    }
   
    
    @Disabled
    @POST
    @Produces(MediaType.APPLICATION_JSON)
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
