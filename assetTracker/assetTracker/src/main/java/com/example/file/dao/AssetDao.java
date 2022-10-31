package com.example.file.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.file.data.Asset;
import com.example.file.util.MyBatisUtil;

public enum AssetDao {

    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger();

    public void create(Asset asset){
        try (SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession()) {

            session.insert("com.stengg.mss.data.AssetMapper.insertAsset", asset);
            session.commit();
            session.close();
        } catch (Exception ex) {
            LOGGER.error("AssetDao::create : " + ex.getMessage());
        }
    }

    public Integer getCount() {

        try (SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession()) {

            Integer countTotal = session.selectOne("com.stengg.mss.data.AssetMapper.getAssetCount");
            session.close();
            return countTotal;
        } catch (Exception ex) {
            LOGGER.error("OsmRoadManagerOracle::getCount : " + ex.getMessage());
            return null;
        }
    }

    public Asset read(Integer assetId){
        try (SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession()) {

            Asset asset = session.selectOne("com.stengg.mss.data.AssetMapper.selectAsset", assetId);
            session.close();
            return asset;
        } catch (Exception ex) {
            LOGGER.error("AssetDao::read : " + ex.getMessage());
            return null;
        }
    }

    public void update(Asset asset){
        try (SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession()) {

            session.update("com.stengg.mss.data.AssetMapper.updateAsset", asset);
            session.commit();
            session.close();
        } catch (Exception ex) {
            LOGGER.error("AssetDao::update : " + ex.getMessage());
        }
    }

    public void delete(Integer assetId){
        try (SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession()) {

            session.delete("com.stengg.mss.data.AssetMapper.deleteAsset", assetId);
            session.commit();
            session.close();
        } catch (Exception ex) {
            LOGGER.error("AssetDao::delete : " + ex.getMessage());
        }
    }

}
