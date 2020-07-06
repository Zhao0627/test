package com.dj.mall.dict.api.logistics;

import com.dj.mall.dict.dto.logistics.LogisticsDTO;
import com.dj.mall.model.base.BusinessException;

import java.util.List;

public interface LogisticsService {

    /**
     * 查询物流信息
     */
    List<LogisticsDTO> findLogAll() throws BusinessException;

    /**
     * 新增物流信息
     */
    void addLog(LogisticsDTO logisticsDTO) throws BusinessException;

    /**
     * 通过id查询Log表
     * @param freightId
     * @return
     * @throws BusinessException
     */
    LogisticsDTO findLogByFreightId(String freightId) throws BusinessException;

    /**
     * 修改log表
     * @param logisticsDTO
     */
    void UpdateLog(LogisticsDTO logisticsDTO) throws BusinessException;
}
