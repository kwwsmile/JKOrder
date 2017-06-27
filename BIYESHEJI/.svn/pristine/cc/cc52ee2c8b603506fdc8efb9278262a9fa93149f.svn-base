package com.example.ayou.jk_takeout.firstpage.model;

import java.io.Serializable;

/**
 * @Description: 参数表
 */
public class SysParamsBean implements Serializable {

    // 意图 IntentUtils 的参数key
    public static final String PARAMS_TYPE = "params_type";
    public static final String PARAMS_ID = "params_id";
    public static final String PARAMS_NAME = "params_name";

    private Long id;

    /**
     * @Fields parCode : 参数 Code
     */
    private String parCode;

    /**
     * @Fields parName : 参数名称
     */
    private String parName;

    /**
     * @Fields pDicCode : 上级参数Code
     */
    private String parParentCode;

    /**
     * @Fields 备注
     */
    private String remark1;

    /**
     * @Fields 备注
     */
    private String remark2;

    /**
     * @Fields 备注
     */
    private String remark3;

    private boolean isChecked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParCode() {
        return parCode;
    }

    public void setParCode(String parCode) {
        this.parCode = parCode;
    }

    public String getParName() {
        return parName;
    }

    public void setParName(String parName) {
        this.parName = parName;
    }

    public String getParParentCode() {
        return parParentCode;
    }

    public void setParParentCode(String parParentCode) {
        this.parParentCode = parParentCode;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }


    public boolean isChecked() {
        return isChecked;
    }


    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else {
            if (this.getClass() == o.getClass()) {
                SysParamsBean sp = (SysParamsBean) o;
                if (this.getParCode().equals(sp.getParCode())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

}
