package com.xxl.job.executor.dao.query;

import java.util.Date;
import lombok.Data;

/**
 * <p>
 * 用户借款信息表查询对象
 * </p>
 *
 * @author chenlei
 * @since 2020-04-14
 */
@Data
public class AppBorrowQuery {

    /**
     * 创建时间
     */
    private Date gmtCreateBegin;

    private Date gmtCreateEnd;

    /**
     * 最后一次修改时间
     */
    private Date gmtModifiedBegin;

    private Date gmtModifiedEnd;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户第三方产品唯一id
     */
    private String openId;

    /**
     * 借款产品id
     */
    private Long productId;

    /**
     * 借款编号
     */
    private String borrowNo;

    /**
     * 风控单号
     */
    private String riskNo;

    /**
     * 借款金额
     */
    private BigDecimal amount;

    /**
     * 实际到账金额
     */
    private BigDecimal arrivalAmount;

    /**
     *  是否分期(N:不分期,Y:分期)
     */
    private String isPeriod;

    /**
     * 借款期数
     */
    private Integer totalPeriod;

    /**
     * 借钱状态：【REVIEWING:审核中，TRANSFERING:打款中 ，TRANSFERRED:已经打款/待还款,OVERDUE 以逾期 ，CLOSED:关闭，FINISHED:已结清】
     */
    private String status;

    /**
     * 审核状态：【FKREVIEWING:审核中，FKREVIEWFAIL:审核不通过 ，REVIEWED:审核通过(已放款)】
     */
    private String reviewStatus;

    /**
     * 资金方商户打款交易流水号
     */
    private String tradeNoOut;

    /**
     * 银行卡号
     */
    private String cardNo;

    /**
     * 卡名称
     */
    private String cardName;

    /**
     * 审核时间
     */
    private Date gmtAuditBegin;

    private Date gmtAuditEnd;

    /**
     * 打款到账时间
     */
    private Date gmtArrivalBegin;

    private Date gmtArrivalEnd;

    /**
     * 借款关闭时间
     */
    private Date gmtCloseBegin;

    private Date gmtCloseEnd;

    /**
     * 结清时间
     */
    private Date gmtFinishBegin;

    private Date gmtFinishEnd;

    /**
     * 借款用途
     */
    private String borrowRemark;

    /**
     * 还款方式【AVG等额本息，RIBP先息后本】
     */
    private String repaymentMethod;

    /**
     * 还款来源
     */
    private String repayRemark;

    /**
     * 借款月化利率
     */
    private BigDecimal interestRate;

    /**
     * 手续费率
     */
    private BigDecimal serviceRate;

    /**
     * 日逾期费率
     */
    private BigDecimal overdueRate;

    /**
     * 总利息
     */
    private BigDecimal totalInterestFee;

    /**
     * 总手续费
     */
    private BigDecimal totalServiceFee;

    /**
     * 总逾期费
     */
    private BigDecimal totalOverdueFee;

    /**
     * 逾期状态，Y表示逾期，N表示未逾期
     */
    private String overdueStatus;

    /**
     * 总逾期天数
     */
    private Integer totalOverdueDays;

    /**
     * 借款发生时IP地址
     */
    private String ip;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 省份
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 县
     */
    private String county;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 失败原因
     */
    private String reason;

    /**
     * 协议地址，多个，隔开
     */
    private String protocolUrl;

    /**
     * 总还款额费用组成，例如：本金1000元，利息100元
     */
    private String costRemark;

    /**
     * 关闭状态【CANCEL：机构取消，FAILED：放款失败，RISK：风控审核不通过】
     */
    private String closedStatus;

    /**
     * 第三方同步时间
     */
    private Date gmtThirdBegin;

    private Date gmtThirdEnd;

    /**
     * 借款期限
     */
    private Integer term;

    /**
     * 单位：【MONTH：月，DAY：天】
     */
    private String unit;

    /**
     * 借款用途详情json串存储详细信息，较长
     */
    private String usageDetail;

    /**
     * 禁止期
     */
    private Integer banningPeriod;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * ups支付渠道
     */
    private String upsPayChannel;

    /**
     * 城市
     */
    private String workCity;

    /**
     * 公司
     */
    private String workCompany;

    /**
     * 单位地址
     */
    private String workAddress;


}
