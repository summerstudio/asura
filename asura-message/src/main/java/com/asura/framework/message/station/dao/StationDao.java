/*
 * Copyright (c) 2015, struggle.2036@163.com All Rights Reserved. 
 *
 * @project asura 
 * @file StationDao 
 * @package com.asura.framework.message.station.dao 
 *
 * @date 2015/4/7 16:29 
 */
package com.asura.framework.message.station.dao;

import com.asura.framework.base.paging.PagingResult;
import com.asura.framework.base.paging.SearchModel;
import com.asura.framework.dao.ibatis.BaseIbatisDaoContext;
import com.asura.framework.message.entity.StationInfoEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 站内信持久化操作 </P>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author SZL
 * @version 1.0
 * @since 1.0
 */
@Repository("station.stationDao")
public class StationDao {

    public static final String SQLMAP_NAMESPACE = "com.asura.framework.messagestation.xml.station.stationDao";

    @Resource(name = "messageBaseIbatisDao")
    private BaseIbatisDaoContext messageBaseIbatisDao;

    public int saveStationInfoEntity(StationInfoEntity entity) {
        return Integer.class.cast(this.messageBaseIbatisDao.save(SQLMAP_NAMESPACE + ".saveStationInfoEntity", entity));
    }

    public PagingResult<StationInfoEntity> findStationByPager(SearchModel searchModel) {
        Map<String, Object> params = this.messageBaseIbatisDao.getConditionMap(searchModel);
        params.put("firstRowIndex", searchModel.getFirstRowIndex());
        params.put("pageSize", searchModel.getPageSize());
        List<StationInfoEntity> rows = this.messageBaseIbatisDao.findAll(SQLMAP_NAMESPACE + ".findStationEntityByCondition", StationInfoEntity.class, params);
        long total = this.messageBaseIbatisDao.count(SQLMAP_NAMESPACE + ".countStationEntityByCondition", params);
        return new PagingResult<StationInfoEntity>(total, rows);
    }

    public List<StationInfoEntity> repeatStation(Integer status) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("smsStatus", status);
        return this.messageBaseIbatisDao.findAll(SQLMAP_NAMESPACE + ".repeatStation", StationInfoEntity.class, params);
    }

    public int updateStationEntity(StationInfoEntity entity) {
        return this.messageBaseIbatisDao.update(SQLMAP_NAMESPACE + ".updateStationEntity", entity);
    }
}
