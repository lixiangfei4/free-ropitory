package com.ixinhoo.shopping.entity;

/**
 * 实体配置
 *
 * @author cici
 */
public class EntitySetting {

    /**
     * 用户积分类型
     */
    public enum UserIntegralType {
        SIGN("签到"), STORE("门店");
        private final String typeName;

        UserIntegralType(final String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    /**
     * 用户收藏类型
     */
    public enum UserCollectionType {
        PRODUCT("商品"), STORE("门店");
        private final String typeName;

        UserCollectionType(final String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    /**
     * 用户评价类型
     */
    public enum UserEvaluateType {
        PRODUCT("商品"), STORE("门店");
        private final String typeName;

        UserEvaluateType(final String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    /**
     * 短信类型
     */
    public enum MessageCodeType {
        REGISTER("注册"), CHANGE_PHONE("更换手机号");
        private final String typeName;

        MessageCodeType(final String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }


    /**
     * 商城类型
     */
    public enum ProductType {
        SHOPPING_MALL("普通商城"), INTEGRAL_MALL("积分商城");
        private final String typeName;

        ProductType(final String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    /**
     * 积分消费类型
     */
    public enum ConsumeIntegralType {
        SHOPPING_MALL("普通商城"), INTEGRAL_MALL("积分商城");
        private final String typeName;

        ConsumeIntegralType(final String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    /**
     * 优惠券类型
     */
    public enum CouponType {
        COMMON("通用"), PRODUCT("商品"), CLASSIFY("分类");
        private final String typeName;

        CouponType(final String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    /**
     * 是否类型
     */
    public enum YesOrNoType {
        YES("是"), NO("否");
        private final String typeName;

        YesOrNoType(final String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }


    /**
     * 提货送货类型
     */
    public enum BuyProductType {
        STORE("门店自提"), EXPRESS("货到付款");
        private final String typeName;

        BuyProductType(final String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    /**
     * 订单类型；待提货、已取消、已关闭、已提货
     */
    public enum OrderStatus {
        TAKING("待提货"), CANCEL("已取消"), CLOSED("已关闭"), TAKEN("已提货");
        private final String typeName;

        OrderStatus(final String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }
}