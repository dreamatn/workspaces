package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4088 {
    String K_MMO = "BP";
    String K_OUTPUT_FMT = "BP211";
    String WS_ERR_MSG = " ";
    char WS_END_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQAMO BPCPQAMO = new BPCPQAMO();
    BPCOACM BPCOACM = new BPCOACM();
    SCCGWA SCCGWA;
    BPB4070_AWA_4070 BPB4070_AWA_4070;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4088 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4070_AWA_4070>");
        BPB4070_AWA_4070 = (BPB4070_AWA_4070) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4070_AWA_4070.MOD_NO);
        CEP.TRC(SCCGWA, BPB4070_AWA_4070.CNTR_TYP);
        CEP.TRC(SCCGWA, BPB4070_AWA_4070.PROD_TYP);
        CEP.TRC(SCCGWA, BPB4070_AWA_4070.BR);
        IBS.init(SCCGWA, BPCPQAMO);
        IBS.init(SCCGWA, BPCOACM);
        BPCPQAMO.FUNC = 'Q';
        if (BPB4070_AWA_4070.MOD_NO.trim().length() > 0) {
            BPCPQAMO.DATA_INFO.MOD_NO = BPB4070_AWA_4070.MOD_NO;
        } else {
            BPCPQAMO.DATA_INFO.CNTR_TYPE = BPB4070_AWA_4070.CNTR_TYP;
            if (BPB4070_AWA_4070.BR == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_MUST_INPUT;
                S000_ERR_MSG_PROC();
            }
            if (BPB4070_AWA_4070.PROD_TYP.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PRD_TYP_MST_IPT;
                S000_ERR_MSG_PROC();
            }
            if (BPB4070_AWA_4070.PROD_TYP.equalsIgnoreCase("*")) {
                BPCPQAMO.DATA_INFO.PROD_TYPE = " ";
            } else {
                BPCPQAMO.DATA_INFO.PROD_TYPE = BPB4070_AWA_4070.PROD_TYP;
            }
            if (BPB4070_AWA_4070.BR == 999999) {
                BPCPQAMO.DATA_INFO.BR = 0;
            } else {
                BPCPQAMO.DATA_INFO.BR = BPB4070_AWA_4070.BR;
            }
        }
        S000_CALL_BPZPQAMO();
        BPCOACM.CNTR_TYPE = BPCPQAMO.DATA_INFO.CNTR_TYPE;
        BPCOACM.MOD_NO = BPCPQAMO.DATA_INFO.MOD_NO;
        BPCOACM.MOD_NAME = BPCPQAMO.DATA_INFO.MOD_NAME;
        if (BPCPQAMO.DATA_INFO.PROD_TYPE.trim().length() == 0) {
            BPCOACM.PROD_TYPE = "*";
        } else {
            BPCOACM.PROD_TYPE = BPCPQAMO.DATA_INFO.PROD_TYPE;
        }
        if (BPCPQAMO.DATA_INFO.BR == 0) {
            BPCOACM.BR = 999999;
        } else {
            BPCOACM.BR = BPCPQAMO.DATA_INFO.BR;
        }
        CEP.TRC(SCCGWA, BPCOACM.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCOACM.PROD_TYPE);
        CEP.TRC(SCCGWA, BPCOACM.BR);
        CEP.TRC(SCCGWA, BPCOACM.MOD_NO);
        CEP.TRC(SCCGWA, BPCOACM.MOD_NAME);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOACM;
        SCCFMT.DATA_LEN = 649;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPQAMO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-AMOD  ", BPCPQAMO);
        CEP.TRC(SCCGWA, BPCPQAMO.RC);
        if (BPCPQAMO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQAMO.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
