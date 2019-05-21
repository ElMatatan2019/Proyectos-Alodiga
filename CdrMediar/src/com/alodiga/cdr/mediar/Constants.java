package com.alodiga.cdr.mediar;

public class Constants {
	public static final String urlConexion = "jdbc:mysql://192.168.3.20/mediator";
    public static final String user = "root";
    public static final String password = "root";
    public static final int MAX_LOGIN_DIGITS = 8;
    public static final int MAX_PASSWORD_DIGITS = 4;
    public static final int MAX_PINFREE = 10;
    public static final int USA_CODE = 1;
    public static final Integer NOTIFICATION_LOGIN = 1;
    public static final Integer NOTIFICATION_PASSDWORD = 2;
    public static final Integer NOTIFICATION_SERIAL = 3;
    public static final Integer NOTIFICATION_ACCESS_NUMBER = 4;
    public static final Integer NOTIFICATION_TOTAL_AMOUNT = 5;
    public static final Integer NOTIFICATION_MAIL_ADDRESS = 6;
    public static final Integer NOTIFICATION_NAME_CUSTOMER = 7;
    public static final Integer NOTIFICATION_CUSTOMER_ENTERPRISE = 8;
    public static final Integer NOTIFICATION_CURRENCY = 9;
    public static final Integer NOTIFICATION_DNI = 10;
    public static final Integer NOTIFICATION_SECRET_PIN = 11;
    public static final String RESPONSE_TRANSACTION = "transaction";
    public static final String RESPONSE_SUCCESSFUL = "successfulVerificationPromotions";
    public static final String RESPONSE_SUCCESSFUL_NOTIFICATION = "successfulNotification";
    public static final String RESPONSE_SUCCESSFUL_TRANSACTION = "successfulTransaction";
    public static final String RESPONSE_SUCCESSFUL_COMMISSION = "successfulCommission";
    public static final String RESPONSE_PURCHASE_BALANCE_Distributor = "successfulPurchaseBalanceDistributor";
    public static final String BONUS_APPLIED_PROMOTION = "floatBonusAppliedPromotion";
    public static final String PROMOTION_APPLIED = "promotionApplied";
    public static final String BONUS_APPLIED = "bonusApplied";
    public static final String RESPONSE_PIN = "pin";
    public static final String ELECTRONIC_PIN = "product.category.electronicPin";
    public static final String TOP_UP = "product.category.topUp";
    public static final String RESPONSE_ORDER_RECHARGE = "orderRecharge";
    public static final Float MIN_COMMISSION_ELECTRONIC_PIN = 20F;
    public static final String IP_CONNECTION= "192.168.3.35";
}