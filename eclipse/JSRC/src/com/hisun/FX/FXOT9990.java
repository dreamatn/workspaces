package com.hisun.FX;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class FXOT9990 {
    String BSL_RTC_FLAG = "SIT_GN_20150608_V1";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_R_TQPH = "BP-R-TQPH-B      ";
    String CPN_R_EXRM = "BP-R-EXRD-M       ";
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE   ";
    String CPN_INQ_EXR_RATE = "BP-INQ-TRN-RATE   ";
    String CPN_FX = "BP-FX             ";
    String K_FWD_TENOR = "FWDT ";
    int K_MAX_ROW = 50;
    int K_MAX_COL = 250;
    int K_Q_MAX_CNT = 1000;
    int K_MAX_TIME = 235959;
    String K_CCY_USD = "USD";
    int WS_BR = 0;
    String WS_CCY = " ";
    int WS_DATE = 0;
    String WS_FWD_TENOR = " ";
    int WS_UNIT = 0;
    int WS_UNIT1 = 0;
    int WS_UNIT2 = 0;
    String WS_ERR_MSG = " ";
    char WS_OUT_REC_FLG = 'N';
    char WS_BUY_METH = ' ';
    char WS_SELL_METH = ' ';
    char WS_METH_1 = ' ';
    char WS_METH_2 = ' ';
    char WS_BASE_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCRCCY BPCRCCY = new BPCRCCY();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPCRTQPH BPCRTQPH = new BPCRTQPH();
    BPCOIEC BPCOIEC = new BPCOIEC();
    BPCIFQ BPCIFQ = new BPCIFQ();
    BPCEX BPCEX = new BPCEX();
    SCCGWA SCCGWA;
    FXB9990_AWA_9990 FXB9990_AWA_9990;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FXOT9990 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "FXB9990_AWA_9990>");
        FXB9990_AWA_9990 = (FXB9990_AWA_9990) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.TL_ID = "7700053";
        SCCGWA.COMM_AREA.BR_DP.TR_BRANCH = 320581001;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCEX);
        BPCEX.BUY_CCY = "840";
        BPCEX.BUY_AMT = 10;
        BPCEX.SELL_CCY = "156";
        BPCEX.SELL_AMT = .5;
        BPCEX.EXR_TYPE = "TRE";
        BPCEX.TRN_EX_RATE = 496.39;
        BPCEX.READ_RT_TIME = SCCGWA.COMM_AREA.TR_TIME;
        S000_CALL_BPZSEX();
    }
    public void S000_CALL_BPZSEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCEX.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
