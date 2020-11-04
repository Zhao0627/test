package com.dj.mall.auth.api.shopcar;

import com.dj.mall.auth.dto.shopcar.ShopCarDTO;
import com.dj.mall.model.base.BusinessException;

import java.util.List;

/**
 * 购物车借接口
 */
public interface ShopCarService {

    /**
     * 加入购物车
     * @param shopCarDTO 购物车dto
     */
    void saveShop(ShopCarDTO shopCarDTO) throws BusinessException;

    /**
     * 客户购物车
     * @param userId 用户id
     * @return 购物车信息
     * @throws BusinessException
     */
    List<ShopCarDTO> findShopCarByUserId(Integer userId) throws BusinessException;

    /**
     * 通过属性id和用户id来查询 判断是否是唯一值
     * @param productSkuId
     * @param userId
     * @return
     */
    ShopCarDTO findShopCarByProductSkuIdAndUserId(Integer productSkuId, Integer userId) throws BusinessException;

    /**
     * 修改购物车
     * @param shopCarDTO
     * @throws BusinessException
     */
    void updateShopCar(ShopCarDTO shopCarDTO) throws BusinessException;

    /**
     * 查询购物车数据通过skuIds和user表id
     * @param productSkuIds
     * @param userId
     * @return
     */
    List<ShopCarDTO> findShopCarByProductSkuIdsAndUserId(Integer[] productSkuIds, Integer userId,String isDel) throws BusinessException;

    /**
     * 批量修改商品的数目
     * @param shopCarIds
     * @param shopCounts
     */
    void updateShopCarBySkuIds(Integer[] shopCarIds, Integer[] shopCounts) throws BusinessException;

    /**
     *
     * @param productSkuIds
     * @param userId
     * @throws BusinessException
     */
    void deleteShopCar(Integer []productSkuIds, Integer userId) throws BusinessException;

    /**
     * 批量修改购物车的状态
     * @param skuIds
     * @param userId
     */
    void updateShopCarBatchById(String shopCarIsDel,List<Integer> skuIds, Integer userId,Integer spuId) throws BusinessException;
}
