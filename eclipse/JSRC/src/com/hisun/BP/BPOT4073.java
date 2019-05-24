package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4073 {
    String K_CPN_S_MAINTAIN_TWND = "BP-S-MAINTAIN-TWND  ";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY     ";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP747";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    char WS_CON_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSACM BPCSACM = new BPCSACM();
    BPCPQENT BPCPQENT = new BPCPQENT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4070_AWA_4070 BPB4070_AWA_4070;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4073 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4070_AWA_4070>");
        BPB4070_AWA_4070 = (BPB4070_AWA_4070) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSACM);
        BPCSACM.FUNC = 'D';
        BPCSACM.KEY.CNTR_TYPE = BPB4070_AWA_4070.CNTR_TYP;
        if (BPB4070_AWA_4070.PROD_TYP.equalsIgnoreCase("*")) {
            BPCSACM.KEY.PROD_TYPE = " ";
        } else {
            BPCSACM.KEY.PROD_TYPE = BPB4070_AWA_4070.PROD_TYP;
        }
        if (BPB4070_AWA_4070.BR == 999999) {
            BPCSACM.KEY.BR = 0;
        } else {
            BPCSACM.KEY.BR = BPB4070_AWA_4070.BR;
        }
        BPCSACM.DATA.MOD_NO = BPB4070_AWA_4070.MOD_NO;
        BPCSACM.DATA.MOD_NAME = BPB4070_AWA_4070.MOD_NAME;
        BPCSACM.DATA.MOD_TYP = BPB4070_AWA_4070.MOD_TYP;
        BPCSACM.EFF_DT = BPB4070_AWA_4070.EFF_DT;
        BPCSACM.EXP_DT = BPB4070_AWA_4070.EXP_DT;
        for (WS_CNT = 1; WS_CNT <= 60; WS_CNT += 1) {
            BPCSACM.DATA.EVENT[WS_CNT-1].EVENT_TYPE = BPB4070_AWA_4070.EVENT[WS_CNT-1].EV_TYPE;
        }
        S010_CALL_BPZSACM();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4070_AWA_4070.MOD_NO.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ACCU_MOD_MUST_INPUT;
            WS_FLD_NO = BPB4070_AWA_4070.BR_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, BPCPQENT);
            BPCPQENT.DATA_INFO.MODNO = BPB4070_AWA_4070.MOD_NO;
            BPCPQENT.FUNC = 'B';
            S001_CALL_BPZPQENT();
            if (BPCPQENT.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQENT.RC);
                WS_FLD_NO = BPB4070_AWA_4070.MOD_NO_NO;
                S000_ERR_MSG_PROC();
            }
            BPCPQENT.FUNC = 'N';
            S001_CALL_BPZPQENT();
            CEP.TRC(SCCGWA, BPCPQENT.DATA_INFO);
            if (BPCPQENT.RC.RC_CODE == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MODEL_EXIST_ENTY;
                WS_FLD_NO = BPB4070_AWA_4070.MOD_NO_NO;
                S000_ERR_MSG_PROC();
            }
            BPCPQENT.FUNC = 'E';
            S001_CALL_BPZPQENT();
        }
    }
    public void S001_CALL_BPZPQENT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-EVENT", BPCPQENT);
        CEP.TRC(SCCGWA, BPCPQENT.RC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S010_CALL_BPZSACM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-ACM", BPCSACM);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
