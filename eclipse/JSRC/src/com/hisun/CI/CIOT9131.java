package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9131 {
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CICMAGT2 CICMAGT2 = new CICMAGT2();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9131_AWA_9131 CIB9131_AWA_9131;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT9131 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9131_AWA_9131>");
        CIB9131_AWA_9131 = (CIB9131_AWA_9131) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        IBS.init(SCCGWA, CICMAGT2);
        CICMAGT2.OPT = CIB9131_AWA_9131.OPT;
        CICMAGT2.DATA.ENTY_TYP = CIB9131_AWA_9131.ENTY_TYP;
        CICMAGT2.DATA.ENTY_NO = CIB9131_AWA_9131.ENTY_NO;
        CICMAGT2.DATA.OLD_ENTY_TYP = CIB9131_AWA_9131.O_EN_TP;
        CICMAGT2.DATA.OLD_ENTY_NO = CIB9131_AWA_9131.O_EN_NO;
        CICMAGT2.DATA.AGT_NO = CIB9131_AWA_9131.AGT_NO;
        CICMAGT2.DATA.AGT_TYP = CIB9131_AWA_9131.AGT_TYP;
        CICMAGT2.DATA.CHNL_AGT_NO = CIB9131_AWA_9131.C_AGT_NO;
        CICMAGT2.DATA.CI_NO = CIB9131_AWA_9131.CI_NO;
        CICMAGT2.DATA.AGT_LVL = CIB9131_AWA_9131.AGT_LVL;
        CICMAGT2.DATA.EFF_DATE = CIB9131_AWA_9131.EFF_DATE;
        CICMAGT2.DATA.EXP_DATE = CIB9131_AWA_9131.EXP_DATE;
        CICMAGT2.DATA.REMARK = CIB9131_AWA_9131.REMARK;
        S000_CALL_CIZMAGT2();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9131_AWA_9131.OPT);
        CEP.TRC(SCCGWA, CIB9131_AWA_9131.ENTY_TYP);
        CEP.TRC(SCCGWA, CIB9131_AWA_9131.ENTY_NO);
        CEP.TRC(SCCGWA, CIB9131_AWA_9131.O_EN_TP);
        CEP.TRC(SCCGWA, CIB9131_AWA_9131.O_EN_NO);
        CEP.TRC(SCCGWA, CIB9131_AWA_9131.AGT_NO);
        CEP.TRC(SCCGWA, CIB9131_AWA_9131.AGT_TYP);
        CEP.TRC(SCCGWA, CIB9131_AWA_9131.CI_NO);
        CEP.TRC(SCCGWA, CIB9131_AWA_9131.AGT_LVL);
        if (CIB9131_AWA_9131.OPT == ' ') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        if (CIB9131_AWA_9131.OPT == 'A') {
            if (CIB9131_AWA_9131.ENTY_TYP == ' ' 
                || CIB9131_AWA_9131.ENTY_NO.trim().length() == 0 
                || CIB9131_AWA_9131.AGT_TYP.trim().length() == 0 
                || CIB9131_AWA_9131.CI_NO.trim().length() == 0 
                || CIB9131_AWA_9131.AGT_LVL == ' ') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
            }
        }
        if (CIB9131_AWA_9131.OPT == 'M') {
            if (CIB9131_AWA_9131.O_EN_TP == ' ' 
                || CIB9131_AWA_9131.O_EN_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "旧实体编号和实体类型必须输入");
            }
        }
        if (CIB9131_AWA_9131.OPT == 'M' 
            || CIB9131_AWA_9131.OPT == 'D') {
            if (CIB9131_AWA_9131.C_AGT_NO.trim().length() == 0 
                || CIB9131_AWA_9131.ENTY_TYP == ' ' 
                || CIB9131_AWA_9131.ENTY_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
            }
        }
        if (CIB9131_AWA_9131.ENTY_TYP == '0') {
            if (!CIB9131_AWA_9131.ENTY_NO.equalsIgnoreCase(CIB9131_AWA_9131.CI_NO)) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "输入实体编号与输入客户号不符");
            }
        }
        if (CIB9131_AWA_9131.ENTY_TYP == '0') {
            if (CIB9131_AWA_9131.AGT_LVL != 'C') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "输入实体类型与签约层次不�?");
            }
        } else {
            if (CIB9131_AWA_9131.AGT_LVL != 'A') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "输入实体类型与签约层次不�?");
            }
        }
    }
    public void S000_CALL_CIZMAGT2() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT2", CICMAGT2);
        if (CICMAGT2.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICMAGT2.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
