package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4074 {
    String K_CPN_S_MAINTAIN_TWND = "BP-S-MAINTAIN-TWND  ";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String CPN_BP_I_INQ_CITY = "BP-I-INQ-CITY     ";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP747";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCSACM BPCSACM = new BPCSACM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4070_AWA_4070 BPB4070_AWA_4070;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_SET_RETURN_INFO();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4074 return!");
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
        BPCSACM.FUNC = 'Q';
        BPCSACM.KEY.CNTR_TYPE = BPB4070_AWA_4070.CNTR_TYP;
        if (BPB4070_AWA_4070.PROD_TYP.equalsIgnoreCase("*")) {
            BPB4070_AWA_4070.PROD_TYP = " ";
        } else {
            BPCSACM.KEY.PROD_TYPE = BPB4070_AWA_4070.PROD_TYP;
        }
        if (BPB4070_AWA_4070.BR == 999999) {
            BPCSACM.KEY.BR = 0;
        } else {
            BPCSACM.KEY.BR = BPB4070_AWA_4070.BR;
        }
        BPCSACM.EFF_DT = BPB4070_AWA_4070.EFF_DT;
        S010_CALL_BPZSACM();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB4070_AWA_4070.EFF_DT == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B000_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB4070_AWA_4070.FUNC);
        WS_FUNC_FLG = BPB4070_AWA_4070.FUNC;
        if (WS_FUNC_FLG == 'U') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4072;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'D') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4073;
            S000_SET_SUBS_TRN();
        } else if (WS_FUNC_FLG == 'Q') {
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 4076;
            S000_SET_SUBS_TRN();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPB4070_AWA_4070.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, SCCSUBS.TR_CODE);
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_SET_SBUS_TRN, SCCSUBS);
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
