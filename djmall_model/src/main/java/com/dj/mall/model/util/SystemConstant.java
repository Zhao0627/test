package com.dj.mall.model.util;
/**
 * 常量
 * @author
 *
 */
public class SystemConstant {
    /**
     * 评价表的父级id
     */
    public static final Integer COMMENT_PARENT_ID=-1;

    /**
     * 评价表的状态---删除
     */
    public static final Integer COMMENT_IS_DEL_YES=1;

    /**
     * 评价表的状态---正常
     */
    public static final Integer COMMENT_IS_DEL_NO=0;

    /**
     * 订单的状态——待支付
     */
    public static final String ORDER_STATUS_PAY="待支付";

    /**
     * 订单的状态——已取消
     */
    public static final String ORDER_STATUS_CANCEL="已取消";

    /**
     * 购物车的状态
     */
    public static final String SHOP_CAR_IS_DEL_YES="YES";

    /**
     * 购物车的状态
     */
    public static final String SHOP_CAR_IS_DEL_NO="NO";

    /**
     * 支付方式的父级id
     */
    public static final String PAY_TYPE="PAY_TYPE";

    /**
     * 商品默认
     */
    public static final String DEFAULT="DEFAULT";

    /**
     * 商品默认
     */
    public static final String NO_DEFAULT="NODEFAULT";

    /**
     * 买家
     */
    public static final Integer BUYER_ID=10;

    /**
     * 保留小数位
     */
    public static final Integer DECIMAL_PLACES_2 = 2;

	/**
	 * 邮箱激活状态未激活
	 */
    public static final String EMAIL_STATES_1 = "INACTIVE" ;

	/**
	 * 邮箱激活状态已激活
	 */
    public static final String EMAIL_STATES_2 = "NORMAL" ;

	/**
	 * 父级 商品类型
	 */
    public static final String PRODUCT_TYPE = "PRODUCT_TYPE" ;

	/**
	 * 商户id
	 */
    public static final Integer ROLE_ID = 2 ;

	/**
	 * 商户id
	 */
    public static final Integer COMMERCIAL_ID = 2 ;

	/**
	 * 管理员id
	 */
    public static final Integer ADMIN_ID = 3;

	/**
	 * 已删除为2
	 */
    public static final Integer IS_DEL_YES = 2 ;

	/**
	 * 未删除为1
	 */
    public static final Integer IS_DEL_NO = 1 ;

	/**
	 * 菜单——1
	 */
    public static final Integer MENU_1 = 1 ;

	/**
	 * 按钮——2
	 */
    public static final Integer BUTTON_2 = 2 ;

	/**
	 * 基础数据表父级code
	 */
    public static final String P_CODE = "SYSTEM" ;

	/**
	 * 性别父级查询
	 */
    public static final String SEX_P_CODE = "SEX_TYPE" ;

	/**
	 * 状态父级查询
	 */
    public static final String STATUS_P_CODE = "STATUS_TYPE" ;

	/**
	 * 包邮价格为0
	 */
    public static final String PINKAGE_0 = "0";

	/**
	 * 包邮价格为0
	 */
    public static final String PINKAGE = "包邮" ;

	/**
	 * 元
	 */
    public static final String YUAN = "元" ;

    /**
	 * 物流公司code
	 */
    public static final String LOGISTICS_COMPANY = "LOGISTICS_COMPANY" ;

    /**
     * 商品下架
     */
    public static final String PRODUCT_DOWN = "PRODUCT_DOWN" ;

    /**
     * 商品上架
     */
    public static final String PRODUCT_UP = "PRODUCT_UP" ;

    /**
     * 下架商品的状态
     */
    public static final Integer PRODUCT_DOWN_0 = 0;

    /**
     * token
     */
    public static final String TOKEN = "TOKEN";

}
