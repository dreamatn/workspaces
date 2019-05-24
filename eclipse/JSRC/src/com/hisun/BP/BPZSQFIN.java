package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSQFIN {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    char K_LONGTOU_BOX_FLG = '5';
    String CPN_U_CMOV_CONFIRM = "BP-U-CMOV-CONFIRM   ";
    String CPN_U_CASH_IN = "BP-U-CASH-IN        ";
    String CPN_P_ADD_CHIS = "BP-P-ADD-CHIS       ";
    String WS_ERR_MSG = " ";
    int WS_INDEX = 0;
    int WS_CCY_CNT = 0;
    int WS_START_CNT = 0;
    int WS_INFO_CNT = 0;
    char WS_MATCH_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUCSIN BPCUCSIN = new BPCUCSIN();
    BPCUCMOV BPCUCMOV = new BPCUCMOV();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    SCCGWA SCCGWA;
    BPCSQFIN BPCSQFIN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSQFIN BPCSQFIN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSQFIN = BPCSQFIN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSQFIN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA();
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            B020_IN_STORE_PROC_FOR_CN();
            B025_HISTORY_PROC();
            B030_ON_WAY_PROC();
        }
    }
    public void B010_CHECK_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_IN_STORE_PROC_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCSIN);
        if (BPCSQFIN.PLBOX_NO == null) BPCSQFIN.PLBOX_NO = "";
        JIBS_tmp_int = BPCSQFIN.PLBOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCSQFIN.PLBOX_NO += " ";
        if (BPCSQFIN.PLBOX_NO.substring(0, 1).equalsIgnoreCase(K_LONGTOU_BOX_FLG+"")) {
            BPCUCSIN.CASH_STAT = '0';
            BPCUCSIN.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUCSIN.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUCSIN.VB_FLG = '0';
            BPCUCSIN.CS_KIND = '0';
        } else {
            BPCUCSIN.CASH_STAT = '1';
            BPCUCSIN.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUCSIN.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUCSIN.VB_FLG = '1';
            BPCUCSIN.CS_KIND = BPCSQFIN.CS_KIND;
        }
        BPCUCSIN.CASH_TYP = BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCUCSIN.CCY = BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCUCSIN.TX_AMT = BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCSIN.PAR_INFO);
        S000_CALL_BPZUCSIN();
    }
    public void B025_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCHIS);
        BPCPCHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPCHIS.PLBOX_NO = BPCUCSIN.PLBOX_NO;
        BPCPCHIS.CASH_TYP = BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPCHIS.CCY = BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCPCHIS.AMT = BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        BPCPCHIS.VB_FLG = '1';
        BPCPCHIS.IN_OUT = 'D';
        BPCPCHIS.CS_KIND = BPCSQFIN.CS_KIND;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPCHIS.PAR_INFO);
        S000_CALL_BPZPCHIS();
    }
    public void B030_ON_WAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCMOV);
        BPCUCMOV.MOV_TYPE = '6';
        BPCUCMOV.MOVE_DATE = BPCSQFIN.MOV_DT;
        BPCUCMOV.CASH_TYP = BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCUCMOV.CCY = BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCUCMOV.TOTAL_AMT = BPCSQFIN.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        BPCUCMOV.CONF_SEQ = BPCSQFIN.CONF_NO;
        BPCUCMOV.CS_KIND = BPCSQFIN.CS_KIND;
        BPCUCMOV.OUT_BR = BPCSQFIN.OUT_BR;
        BPCUCMOV.OUT_TLR = BPCSQFIN.OUT_TLR;
        BPCUCMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCMOV.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUCMOV.MOV_TYPE = '6';
        S000_CALL_BPZUCMOV();
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZUCMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CMOV_CONFIRM, BPCUCMOV);
        if (BPCUCMOV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCMOV.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCSIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_IN, BPCUCSIN);
        if (BPCUCSIN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCSIN.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_ADD_CHIS, BPCPCHIS);
        if (BPCPCHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
