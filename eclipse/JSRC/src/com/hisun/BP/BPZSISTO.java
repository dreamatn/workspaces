package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSISTO {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP106";
    String K_BUSS_TYPE = "99";
    char K_LONGTOU_BOX_FLG = '5';
    String CPN_F_TLR_PEND_MGM = "BP-F-TLR-PEND-MGM   ";
    String CPN_F_TLR_ACCU_MGM = "BP-F-TLR-ACCU-MGM ";
    String CPN_U_CMOV_CONFIRM = "BP-U-CMOV-CONFIRM   ";
    String CPN_U_CASH_IN = "BP-U-CASH-IN        ";
    String CPN_P_ADD_CHIS = "BP-P-ADD-CHIS       ";
    String CPN_S_CSHAPP_MAINTAIN = "BP-S-CSHAPP-MAINTAIN";
    String K_HIS_REMARKS = "CS CHU RU KU SHEN QING ";
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
    BPCOOSTO BPCOOSTO = new BPCOOSTO();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    BPCOLIBB BPCOLIBB = new BPCOLIBB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCSISTO BPCSISTO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSISTO BPCSISTO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSISTO = BPCSISTO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSISTO return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DATA();
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B020_IN_STORE_PROC_FOR_CN();
                B025_HISTORY_PROC();
                B030_ON_WAY_PROC();
                if (BPCSISTO.APP_NO != 0 
                    && BPCSISTO.APP_NO != ' ') {
                    B040_UPDATE_CSH_APP_INF_PROC();
                    B030_HISTORY_RECORD();
                }
            }
        } else {
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B020_IN_STORE_PROC();
                B025_HISTORY_PROC();
                B030_ON_WAY_PROC();
            }
        }
        if (BPCSISTO.CNT < 3) {
            B050_OUTPUT_PROC();
        }
    }
    public void B010_CHECK_DATA() throws IOException,SQLException,Exception {
    }
    public void B040_UPDATE_CSH_APP_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLIBB);
        BPCOLIBB.FUNC = 'M';
        BPCOLIBB.MODIFY_STS = 'I';
        BPCOLIBB.APP_NO = BPCSISTO.APP_NO;
        S000_CALL_BPZSLIBB();
        BPCSISTO.OUT_BR = 0;
        BPCSISTO.OUT_TLR = " ";
        BPCSISTO.MOV_DT = 0;
        BPCSISTO.CONF_NO = 0;
        BPCSISTO.APP_TYPE = ' ';
        BPCSISTO.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSISTO.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSISTO.MOV_DT = BPCOLIBB.MOV_DT;
        BPCSISTO.CONF_NO = BPCOLIBB.CONF_SEQ;
        BPCSISTO.APP_TYPE = BPCOLIBB.APP_TYPE;
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P901";
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZSLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CSHAPP_MAINTAIN, BPCOLIBB);
    }
    public void B020_IN_STORE_PROC_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCSIN);
        if (BPCSISTO.PLBOX_NO == null) BPCSISTO.PLBOX_NO = "";
        JIBS_tmp_int = BPCSISTO.PLBOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCSISTO.PLBOX_NO += " ";
        if (BPCSISTO.PLBOX_NO.substring(0, 1).equalsIgnoreCase(K_LONGTOU_BOX_FLG+"")) {
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
            BPCUCSIN.CS_KIND = BPCSISTO.CS_KIND;
        }
        BPCUCSIN.CASH_TYP = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCUCSIN.CCY = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCUCSIN.TX_AMT = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCSIN.PAR_INFO);
        S000_CALL_BPZUCSIN();
    }
    public void B020_IN_STORE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCSIN);
        BPCUCSIN.CASH_STAT = '1';
        BPCUCSIN.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCSIN.VB_FLG = '1';
        BPCUCSIN.CASH_TYP = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCUCSIN.CCY = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCUCSIN.CS_KIND = BPCSISTO.CS_KIND;
        BPCUCSIN.TX_AMT = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCSIN.PAR_INFO);
        S000_CALL_BPZUCSIN();
    }
    public void B025_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCHIS);
        BPCPCHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPCHIS.PLBOX_NO = BPCUCSIN.PLBOX_NO;
        BPCPCHIS.CONF_SEQ = BPCSISTO.CONF_NO;
        BPCPCHIS.CASH_TYP = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPCHIS.CCY = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCPCHIS.AMT = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        if (BPCUCSIN.PLBOX_NO == null) BPCUCSIN.PLBOX_NO = "";
        JIBS_tmp_int = BPCUCSIN.PLBOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCUCSIN.PLBOX_NO += " ";
        if (BPCUCSIN.PLBOX_NO.substring(0, 1).equalsIgnoreCase(K_LONGTOU_BOX_FLG+"")) {
            BPCPCHIS.VB_FLG = '0';
        } else {
            BPCPCHIS.VB_FLG = '1';
        }
        BPCPCHIS.IN_OUT = 'D';
        BPCPCHIS.CS_KIND = BPCSISTO.CS_KIND;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPCHIS.PAR_INFO);
        S000_CALL_BPZPCHIS();
    }
    public void B030_ON_WAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCMOV);
        BPCUCMOV.MOVE_DATE = BPCSISTO.MOV_DT;
        BPCUCMOV.CASH_TYP = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCUCMOV.CCY = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCUCMOV.TOTAL_AMT = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        BPCUCMOV.CONF_SEQ = BPCSISTO.CONF_NO;
        BPCUCMOV.CS_KIND = BPCSISTO.CS_KIND;
        BPCUCMOV.OUT_BR = BPCSISTO.OUT_BR;
        BPCUCMOV.OUT_TLR = BPCSISTO.OUT_TLR;
        BPCUCMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCMOV.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZUCMOV();
    }
    public void B035_DEL_TLR_PENDING_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLPM);
        BPCFTLPM.OP_CODE = 'D';
        BPCFTLPM.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCFTLPM.BUSS_TYP = K_BUSS_TYPE;
        BPCFTLPM.TX_COUNT = 1;
        S000_CALL_BPZFTLPM();
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOOSTO);
        BPCOOSTO.IN_BR = BPCSISTO.OUT_BR;
        BPCOOSTO.IN_TLR = BPCSISTO.OUT_TLR;
        BPCOOSTO.CS_KIND = BPCSISTO.CS_KIND;
        BPCOOSTO.BV_DATE = BPCSISTO.BV_DATE;
        BPCOOSTO.BV_NO = BPCSISTO.BV_NO;
        BPCOOSTO.MOVE_DT = BPCSISTO.MOV_DT;
        BPCOOSTO.CONF_SEQ = BPCSISTO.CONF_NO;
        BPCOOSTO.FLG = BPCSISTO.APP_TYPE;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCOOSTO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCOOSTO.CCY_INFO[WS_CCY_CNT-1].CCY = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY;
            BPCOOSTO.CCY_INFO[WS_CCY_CNT-1].AMT = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            for (WS_START_CNT = 1; WS_START_CNT <= 12 
                && BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_START_CNT-1].CCY_VAL != 0; WS_START_CNT += 1) {
                WS_INFO_CNT = ( WS_CCY_CNT - 1 ) * 12 + WS_START_CNT;
                BPCOOSTO.DT_INFO[WS_INFO_CNT-1].P_PVAL = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_START_CNT-1].CCY_VAL;
                BPCOOSTO.DT_INFO[WS_INFO_CNT-1].P_NUM = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_START_CNT-1].CCY_NUM;
                BPCOOSTO.DT_INFO[WS_INFO_CNT-1].P_MFLG = BPCSISTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_START_CNT-1].CCY_MFLG;
            }
        }
        BPCOOSTO.CCY_CNT = WS_CCY_CNT - 1;
        BPCOOSTO.DT_CNT = WS_INFO_CNT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOOSTO;
        SCCFMT.DATA_LEN = 1760;
        CEP.TRC(SCCGWA, BPCOOSTO);
        CEP.TRC(SCCGWA, BPCOOSTO);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZFTLPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_PEND_MGM, BPCFTLPM);
        if (BPCFTLPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLPM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZUCMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CMOV_CONFIRM, BPCUCMOV);
        if (BPCUCMOV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCMOV.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_ACCU_MGM, BPCFTLAM);
        if (BPCFTLAM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLAM.RC);
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
