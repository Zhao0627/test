package com.dj.mall.dict.provider.basedata;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import com.dj.mall.dict.api.basedata.BaseDataService;
import com.dj.mall.dict.dto.basedata.BaseDataDTO;
import com.dj.mall.dict.entity.basedata.BaseData;
import com.dj.mall.dict.mapper.basedata.BaseDataMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 基础数据表实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseDataServiceImpl extends ServiceImpl<BaseDataMapper, BaseData> implements BaseDataService {

    @Reference
    private RedisService redisService;

    /**
     * 获取基础数据表的全部信息
     *
     * @return
     * @throws BusinessException
     */
    @Override
    public List<BaseDataDTO> getBaseDataAll() throws BusinessException {
        return DozerUtil.mapList(list(),BaseDataDTO.class);
    }

    /**
     * 新增基础数据
     * @param baseDataDTO
     * @throws BusinessException
     */
    @Override
    public void insertBaseData(BaseDataDTO baseDataDTO) throws BusinessException {
        baseDataDTO.setCode(baseDataDTO.getCode().toUpperCase());
        save(DozerUtil.map(baseDataDTO,BaseData.class));
        redisService.pushHash(baseDataDTO.getPCode(),baseDataDTO.getCode(),baseDataDTO);
    }

    /**
     * 通过code获取一条数据
     *
     * @param code
     * @return 返回基础数据的一条信息
     * @throws BusinessException
     */
    @Override
    public BaseDataDTO getBasedataByCode(String code) throws BusinessException {
        QueryWrapper<BaseData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code",code);
        return DozerUtil.map(getOne(queryWrapper),BaseDataDTO.class);
    }

    /**
     * 修改一条数据
     *
     * @param baseDataDTO 要修改的数据
     * @throws BusinessException
     */
    @Override
    public void updateBaseData(BaseDataDTO baseDataDTO) throws BusinessException {
        UpdateWrapper<BaseData> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("code",baseDataDTO.getCode());
        update(DozerUtil.map(baseDataDTO,BaseData.class),updateWrapper);
        BaseDataDTO baseDataDTO1 = this.getBasedataByCode(baseDataDTO.getCode());
        redisService.pushHash(baseDataDTO1.getPCode(),baseDataDTO1.getCode(),baseDataDTO1);
    }

    /**
     * 通过父级code查询
     *
     * @param pCode
     * @return
     * @throws BusinessException
     */
    @Override
    public List<BaseDataDTO> getBasedataByPCode(String pCode) throws BusinessException {
        List<BaseDataDTO> baseDataDTOS = redisService.getHashValues(pCode);
        if (null == baseDataDTOS || baseDataDTOS.isEmpty()){
            QueryWrapper<BaseData> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("p_code",pCode);
            List<BaseDataDTO> baseData = DozerUtil.mapList(list(queryWrapper), BaseDataDTO.class);
            for (BaseDataDTO base:baseData) {
                redisService.pushHash(pCode,base.getCode(),base);
            }
            return baseData;
        }
        return baseDataDTOS;
    }
}
