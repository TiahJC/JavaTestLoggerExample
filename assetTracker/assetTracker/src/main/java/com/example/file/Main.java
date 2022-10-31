package com.example.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.file.dao.AssetDao;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

        int assetNum = AssetDao.INSTANCE.getCount();
        LOGGER.info(" asset num >> " + assetNum);
    }
}