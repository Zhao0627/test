package com.dj.mall.dict.api.basedata;

import com.dj.mall.dict.dto.basedata.BaseDataDTO;
import com.dj.mall.model.base.BusinessException;

import java.util.List;

/**
 * 基础数据表接口
 */
public interface BaseDataService {

    /**
     * 获取基础数据表的全部信息
     * @return  基础数据表信息
     * @throws BusinessException
     */
    List<BaseDataDTO> getBaseDataAll() throws BusinessException;

    /**
     * 新增基础数据
     * @param baseDataDTO
     * @throws BusinessException
     */
    void insertBaseData(BaseDataDTO baseDataDTO) throws BusinessException;

    /**
     * 通过code获取一条数据
     * @param code
     * @return  返回基础数据的一条信息
     * @throws BusinessException
     */
    BaseDataDTO getBasedataByCode(String code) throws BusinessException;

    /**
     * 修改一条数据
     * @param baseDataDTO 要修改的数据
     * @throws BusinessException
     */
    void updateBaseData(BaseDataDTO baseDataDTO) throws BusinessException;

    /**
     * 通过父级code查询
     * @param pCode
     * @return
     * @throws BusinessException
     */
    List<BaseDataDTO> getBasedataByPCode(String pCode) throws BusinessException;
}
