package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNOT8915 {
    int JIBS_tmp_int;
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_REF_RATE = 0;
    double WS_END_RATE = 0;
    char WS_CMMT_IND = ' ';
    String WS_RAT_CCY = " ";
    String WS_RAT_BASE_TYP = "029";
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    LNCCMMTM LNCCMMTM = new LNCCMMTM();
    LNCPPMQ LNCPPMQ = new LNCPPMQ();
    LNCRATQ LNCRATQ = new LNCRATQ();
    LNCXQ71 LNCXQ71 = new LNCXQ71();
    BPCUINTI BPCUINTI = new BPCUINTI();
    BPCCINTI BPCCINTI = new BPCCINTI();
    SCCFMT SCCFMT = new SCCFMT();
    SCCGWA SCCGWA;
    LNB8915_AWA_8915 LNB8915_AWA_8915;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "LNOT8915 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "LNB8915_AWA_8915>");
        LNB8915_AWA_8915 = (LNB8915_AWA_8915) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCUINTI);
        CEP.TRC(SCCGWA, LNB8915_AWA_8915.DATE);
        CEP.TRC(SCCGWA, LNB8915_AWA_8915.BR);
        CEP.TRC(SCCGWA, LNB8915_AWA_8915.RATE_ID);
        CEP.TRC(SCCGWA, LNB8915_AWA_8915.CCY);
        CEP.TRC(SCCGWA, LNB8915_AWA_8915.TENOR);
        CEP.TRC(SCCGWA, LNB8915_AWA_8915.PRAT_FLG);
        CEP.TRC(SCCGWA, LNB8915_AWA_8915.PRAT_DIF);
        CEP.TRC(SCCGWA, LNB8915_AWA_8915.CTA_NO);
        CEP.TRC(SCCGWA, LNB8915_AWA_8915.RAT_PEC);
        CEP.TRC(SCCGWA, LNB8915_AWA_8915.PROD_CD);
        CEP.TRC(SCCGWA, LNB8915_AWA_8915.OWN_RATE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (LNB8915_AWA_8915.PRAT_FLG == 'P' 
            || LNB8915_AWA_8915.PRAT_FLG == 'A' 
            || LNB8915_AWA_8915.PRAT_FLG == 'T') {
            B100_OVD_RATE_PROC();
        } else {
            B200_NOR_RATE_PROC();
        }
        B030_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (LNB8915_AWA_8915.CTA_NO.trim().length() == 0 
            && LNB8915_AWA_8915.PRAT_FLG == 'A') {
        }
    }
    public void B010_GET_PROD_PPMQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCPPMQ);
        LNCPPMQ.KEY.REQ_INSTRUCT.REQ_MODULE_PARM = 'N';
        LNCPPMQ.KEY.REQ_INSTRUCT.REQ_COMMIT_PARM = 'N';
        LNCPPMQ.KEY.REQ_INSTRUCT.REQ_CONTRACT_PARM = 'Y';
        LNCPPMQ.KEY.KEY_METH = 'P';
        LNCPPMQ.KEY.KEY_VALUE.LEVEL = 'D';
        LNCPPMQ.KEY.KEY_VALUE.PROD_CD = LNB8915_AWA_8915.PROD_CD;
        S000_CALL_LNZPPMQ();
    }
    public void B200_NOR_RATE_PROC() throws IOException,SQLException,Exception {
        B123_GET_RTTYPE_RATE();
        WS_REF_RATE = BPCCINTI.BASE_INFO.RATE;
        CEP.TRC(SCCGWA, LNB8915_AWA_8915.PRAT_DIF);
        CEP.TRC(SCCGWA, LNB8915_AWA_8915.RAT_PEC);
    }
    public void B210_GET_RTCODE_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUINTI);
        BPCUINTI.DT = LNB8915_AWA_8915.DATE;
        BPCUINTI.BR = LNB8915_AWA_8915.BR;
        BPCUINTI.CCY = LNB8915_AWA_8915.CCY;
        BPCUINTI.BASE_TYP = LNB8915_AWA_8915.BASE_TYP;
        BPCUINTI.TENOR = LNB8915_AWA_8915.TENOR;
        S000_CALL_BPZUINTI();
    }
    public void B030_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCXQ71);
        IBS.init(SCCGWA, SCCFMT);
        LNCXQ71.DATE = BPCUINTI.DT;
        LNCXQ71.N_DATE = BPCUINTI.N_DT;
        LNCXQ71.BR = BPCUINTI.BR;
        LNCXQ71.CCY = BPCUINTI.CCY;
        LNCXQ71.BASE_TYP = BPCUINTI.BASE_TYP;
        LNCXQ71.TENOR = BPCUINTI.TENOR;
        LNCXQ71.OWN_RATE = WS_REF_RATE;
        LNCXQ71.END_RATE = WS_END_RATE;
        LNCXQ71.INTDAY = BPCUINTI.INTDAY;
        LNCXQ71.BDESC = BPCUINTI.BDESC;
        LNCXQ71.TDESC = BPCUINTI.TDESC;
        CEP.TRC(SCCGWA, LNCXQ71.DATE);
        CEP.TRC(SCCGWA, LNCXQ71.N_DATE);
        CEP.TRC(SCCGWA, LNCXQ71.BR);
        CEP.TRC(SCCGWA, LNCXQ71.CCY);
        CEP.TRC(SCCGWA, LNCXQ71.BASE_TYP);
        CEP.TRC(SCCGWA, LNCXQ71.TENOR);
        CEP.TRC(SCCGWA, LNCXQ71.OWN_RATE);
        CEP.TRC(SCCGWA, LNCXQ71.END_RATE);
        CEP.TRC(SCCGWA, LNCXQ71.INTDAY);
        CEP.TRC(SCCGWA, LNCXQ71.BDESC);
        CEP.TRC(SCCGWA, LNCXQ71.TDESC);
        SCCFMT.FMTID = "LNQ71";
        SCCFMT.DATA_PTR = LNCXQ71;
        SCCFMT.DATA_LEN = 313;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B100_OVD_RATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUINTI);
        if (LNB8915_AWA_8915.PRAT_FLG == 'A') {
            if (LNB8915_AWA_8915.CTA_NO.trim().length() == 0) {
                WS_REF_RATE = LNB8915_AWA_8915.OWN_RATE;
            } else {
                B122_GET_ALL_IN_RATE();
                WS_REF_RATE = LNCRATQ.COMM_DATA.RATE;
            }
        }
        if (LNB8915_AWA_8915.PRAT_FLG == 'T') {
            B123_GET_RTTYPE_RATE();
            WS_REF_RATE = BPCCINTI.BASE_INFO.RATE;
        }
    }
    public void B121_GET_PRIME_RATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNOT8915_WS2);
        WS_RAT_CCY = LNB8915_AWA_8915.CCY;
        BPCUINTI.DT = LNB8915_AWA_8915.DATE;
        S000_CALL_BPZUINTI();
    }
    public void B122_GET_ALL_IN_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRATQ);
        LNCRATQ.COMM_DATA.LN_AC = LNB8915_AWA_8915.CTA_NO;
        LNCRATQ.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCRATQ.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCRATQ.COMM_DATA.SUF_NO = "0" + LNCRATQ.COMM_DATA.SUF_NO;
        LNCRATQ.COMM_DATA.RATE_TYPE = 'N';
        LNCRATQ.COMM_DATA.VAL_DATE = LNB8915_AWA_8915.DATE;
        S000_CALL_LNZRATQ();
    }
    public void B123_GET_RTCODE_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUINTI);
        BPCUINTI.DT = LNB8915_AWA_8915.DATE;
        BPCUINTI.BR = LNB8915_AWA_8915.BR;
        BPCUINTI.CCY = LNB8915_AWA_8915.CCY;
        BPCUINTI.BASE_TYP = LNB8915_AWA_8915.BASE_TYP;
        BPCUINTI.TENOR = LNB8915_AWA_8915.TENOR;
        S000_CALL_BPZUINTI();
        WS_REF_RATE = BPCUINTI.OWN_RATE;
        WS_END_RATE = LNB8915_AWA_8915.PRAT_DIF + BPCUINTI.OWN_RATE;
    }
    public void B123_GET_RTTYPE_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.BASE_INFO.DT = LNB8915_AWA_8915.DATE;
        BPCCINTI.BASE_INFO.CCY = LNB8915_AWA_8915.CCY;
        BPCCINTI.BASE_INFO.BASE_TYP = LNB8915_AWA_8915.BASE_TYP;
        BPCCINTI.BASE_INFO.TENOR = LNB8915_AWA_8915.TENOR;
        BPCCINTI.FUNC = 'I';
        if (LNB8915_AWA_8915.BASE_TYP.trim().length() > 0) {
            S000_CALL_BPZCINTI();
        }
    }
    public void S000_CALL_BPZUINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-INTR-INQ", BPCUINTI);
        CEP.TRC(SCCGWA, BPCUINTI.RC.RC_CODE);
        CEP.TRC(SCCGWA, BPCUINTI.OWN_RATE);
        if (BPCUINTI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUINTI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        CEP.TRC(SCCGWA, BPCCINTI.RC.RC_CODE);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.RATE);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.N_DT);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.OWN_RATE);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.INTDAY);
        CEP.TRC(SCCGWA, BPCCINTI.BASE_INFO.BASETYP_STS);
        CEP.TRC(SCCGWA, BPCCINTI.EXT_INFO);
        if (BPCCINTI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZRATQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-RATE-INQ", LNCRATQ);
        if (LNCRATQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCRATQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZPPMQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUN-PRD-PRM-INQ", LNCPPMQ);
        if (LNCPPMQ.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, LNCPPMQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_LNZCMMTM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-CMMT-MAINT", LNCCMMTM);
        if (LNCCMMTM.RC.RC_RTNCODE == 0) {
            WS_CMMT_IND = 'Y';
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
