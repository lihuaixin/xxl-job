package com.xxl.job.executor.core.enmus;

/**
 * @describe: 业务单号前缀
 * @author: wangli
 * @date: 2018/12/28 10:02
 */
public enum BizNoPrefixEnum {

    /**
     *
     */
    BORROW("TCB", "淘车借款编号"),
    BORROW_BILL("TCBB", "淘车借款分期账单编号"),
    BORROW_BANK("TCBK", "淘车银行编号"),
    REPAY("TCR","淘车还款编号")
    ;

    private String code;

    private String name;

    BizNoPrefixEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
