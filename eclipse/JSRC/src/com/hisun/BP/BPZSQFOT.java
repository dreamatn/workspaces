package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSQFOT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    char K_LONGTOU_BOX_FLG = '5';
    String CPN_U_CMOV_REGIST = "BP-U-CMOV-REGIST    ";
    String CPN_U_CASH_OUT = "BP-U-CASH-OUT       ";
    String CPN_P_ADD_CHIS = "BP-P-ADD-CHIS       ";
    String WS_ERR_MSG = " ";
    int WS_INDEX = 0;
    int WS_CCY_CNT = 0;
    int WS_INFO_CNT = 0;
    int WS_START_CNT = 0;
    int WS_TEMP_CONF = 0;
    char WS_MATCH_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCUCSTO BPCUCSTO = new BPCUCSTO();
    BPCURMOV BPCURMOV = new BPCURMOV();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCFTLAM BPCFTLAM = new BPCFTLAM();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    SCCGWA SCCGWA;
    BPCSQFOT BPCSQFOT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSQFOT BPCSQFOT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSQFOT = BPCSQFOT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSQFOT return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            B020_OUT_STORE_PROC_FOR_CN();
            B025_HISTORY_PROC();
            B030_ON_WAY_PROC();
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPCURMOV.CONF_SEQ);
            if (WS_CCY_CNT == 1) {
                WS_TEMP_CONF = BPCURMOV.CONF_SEQ;
            }
        }
        BPCSQFOT.CONF_NO = WS_TEMP_CONF;
        BPCSQFOT.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B020_OUT_STORE_PROC_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCSTO);
        if (BPCSQFOT.PLBOX_NO == null) BPCSQFOT.PLBOX_NO = "";
        JIBS_tmp_int = BPCSQFOT.PLBOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCSQFOT.PLBOX_NO += " ";
        if (BPCSQFOT.PLBOX_NO.substring(0, 1).equalsIgnoreCase(K_LONGTOU_BOX_FLG+"")) {
            BPCUCSTO.CASH_STAT = '0';
            BPCUCSTO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUCSTO.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUCSTO.VB_FLG = '0';
            BPCUCSTO.CS_KIND = '0';
        } else {
            BPCUCSTO.CASH_STAT = '1';
            BPCUCSTO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUCSTO.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUCSTO.VB_FLG = '1';
            BPCUCSTO.CS_KIND = BPCSQFOT.CS_KIND;
        }
        BPCUCSTO.CASH_TYP = BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCUCSTO.CCY = BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCUCSTO.TX_AMT = BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCSTO.PAR_INFO);
        CEP.TRC(SCCGWA, BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        S000_CALL_BPZUCSTO();
    }
    public void B025_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCHIS);
        BPCPCHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPCHIS.PLBOX_NO = BPCUCSTO.PLBOX_NO;
        BPCPCHIS.CASH_TYP = BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPCHIS.CCY = BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCPCHIS.AMT = BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPCHIS.PAR_INFO);
        BPCPCHIS.VB_FLG = '1';
        BPCPCHIS.IN_OUT = 'C';
        BPCPCHIS.CS_KIND = BPCSQFOT.CS_KIND;
        S000_CALL_BPZPCHIS();
    }
    public void B030_ON_WAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCURMOV);
        BPCURMOV.MOV_TYPE = '6';
        BPCURMOV.CASH_TYP = BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCURMOV.CCY = BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CCY);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCURMOV.MOVE_DATE = BPCSQFOT.MOVE_DT;
            BPCURMOV.CONF_SEQ = BPCSQFOT.CONF_NO;
        } else {
            BPCURMOV.MOVE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCURMOV.CONF_SEQ = WS_TEMP_CONF;
        }
        BPCURMOV.CS_KIND = BPCSQFOT.CS_KIND;
        BPCURMOV.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCURMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCURMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, WS_CCY_CNT);
        CEP.TRC(SCCGWA, BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT);
        BPCURMOV.TOTAL_AMT = BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSQFOT.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCURMOV.CCY_INFO);
        S000_CALL_BPZURMOV();
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZPCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_ADD_CHIS, BPCPCHIS);
        if (BPCPCHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZURMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CMOV_REGIST, BPCURMOV);
        if (BPCURMOV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCURMOV.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCSTO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_OUT, BPCUCSTO);
        if (BPCUCSTO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCSTO.RC);
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
