package com.dj.mall.dict.provider.logistics;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.dict.api.basedata.BaseDataService;
import com.dj.mall.dict.api.logistics.LogisticsService;
import com.dj.mall.dict.dto.logistics.LogisticsDTO;
import com.dj.mall.dict.entity.basedata.BaseData;
import com.dj.mall.dict.entity.logistics.Logistics;
import com.dj.mall.dict.mapper.basedata.BaseDataMapper;
import com.dj.mall.dict.mapper.logistics.LogisticsMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 物流实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LogisticsServiceImpl extends ServiceImpl<LogisticsMapper, Logistics> implements LogisticsService {

    @Autowired
    private LogisticsMapper logisticsMapper;

    /**
     * 查询所有物流信息
     * @return
     * @throws BusinessException
     */
    @Override
    public List<LogisticsDTO> findLogAll() throws BusinessException {
        return DozerUtil.mapList(logisticsMapper.findLogAll(),LogisticsDTO.class);
    }

    /**
     * 新增物流信息
     * @param logisticsDTO
     * @throws BusinessException
     */
    @Override
    public void addLog(LogisticsDTO logisticsDTO) throws BusinessException {
        Logistics logistics = DozerUtil.map(logisticsDTO, Logistics.class);
        if (logisticsDTO.getFreight().equals(SystemConstant.PINKAGE_0)){
            logistics.setFreight(SystemConstant.PINKAGE);
        }else{
            logistics.setFreight(logisticsDTO.getFreight()+SystemConstant.YUAN);
        }
        save(logistics);
    }

    /**
     * 通过id查询Log表
     *
     * @param freightId
     * @return
     * @throws BusinessException
     */
    @Override
    public LogisticsDTO findLogByFreightId(String freightId) throws BusinessException {
        Logistics byId = getById(freightId);
        LogisticsDTO logisticsDTO = new LogisticsDTO();
        if (byId.getFreight().equals(SystemConstant.PINKAGE)){
            logisticsDTO.setFreight(SystemConstant.PINKAGE_0);
        }else{
            String freight = byId.getFreight();
            logisticsDTO.setFreight(freight.substring(0,(freight.length()-1)));
        }
        logisticsDTO.setFreightId(byId.getId());
        logisticsDTO.setLogisticsCompany(byId.getLogisticsCompany());
        return logisticsDTO;
    }

    /**
     * 修改log表
     *
     * @param logisticsDTO
     */
    @Override
    public void UpdateLog(LogisticsDTO logisticsDTO) throws BusinessException {
        Logistics logistics = DozerUtil.map(logisticsDTO, Logistics.class);
        if (logisticsDTO.getFreight().equals(SystemConstant.PINKAGE_0)){
            logistics.setFreight(SystemConstant.PINKAGE);
        }else {
            logistics.setFreight(logisticsDTO.getFreight()+SystemConstant.YUAN);
        }
        updateById(logistics);
    }

}
