package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSOSTO {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP104";
    String K_MOV_SEQ_TYPE = "CMOVE";
    String K_MOV_SEQ_NAME = "CASHMOVE";
    String K_BUSS_TYPE = "99";
    char K_LONGTOU_BOX_FLG = '5';
    String CPN_F_TLR_PEND_MGM = "BP-F-TLR-PEND-MGM   ";
    String CPN_F_TLR_ACCU_MGM = "BP-F-TLR-ACCU-MGM ";
    String CPN_S_GET_SEQ = "BP-S-GET-SEQ        ";
    String CPN_F_TLR_STS = "BP-F-TLR-STS-QRY    ";
    String CPN_U_CMOV_REGIST = "BP-U-CMOV-REGIST    ";
    String CPN_U_CASH_OUT = "BP-U-CASH-OUT       ";
    String CPN_P_ADD_CHIS = "BP-P-ADD-CHIS       ";
    String CPN_S_CSHAPP_MAINTAIN = "BP-S-CSHAPP-MAINTAIN";
    String K_HIS_REMARKS = "CS CHU RU KU SHEN QING ";
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
    BPCOOSTO BPCOOSTO = new BPCOOSTO();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    BPCOLIBB BPCOLIBB = new BPCOLIBB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCSOSTO BPCSOSTO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSOSTO BPCSOSTO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSOSTO = BPCSOSTO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSOSTO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B020_OUT_STORE_PROC_FOR_CN();
                if (pgmRtn) return;
                B030_ON_WAY_PROC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_CCY_CNT);
                CEP.TRC(SCCGWA, BPCURMOV.CONF_SEQ);
                if (WS_CCY_CNT == 1) {
                    WS_TEMP_CONF = BPCURMOV.CONF_SEQ;
                }
                B025_HISTORY_PROC();
                if (pgmRtn) return;
                if (BPCSOSTO.APP_NO != 0 
                    && BPCSOSTO.APP_NO != ' ') {
                    B040_UPDATE_CSH_APP_INF_PROC();
                    if (pgmRtn) return;
                    B030_HISTORY_RECORD();
                    if (pgmRtn) return;
                }
            }
        } else {
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B020_OUT_STORE_PROC();
                if (pgmRtn) return;
                B025_HISTORY_PROC();
                if (pgmRtn) return;
                B030_ON_WAY_PROC();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_CCY_CNT);
                CEP.TRC(SCCGWA, BPCURMOV.CONF_SEQ);
                if (WS_CCY_CNT == 1) {
                    WS_TEMP_CONF = BPCURMOV.CONF_SEQ;
                }
            }
        }
        BPCSOSTO.CONF_NO = WS_TEMP_CONF;
        BPCSOSTO.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
        if (BPCSOSTO.CNT < 3) {
            B050_OUTPUT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P901";
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B020_OUT_STORE_PROC_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCSTO);
        if (BPCSOSTO.PLBOX_NO == null) BPCSOSTO.PLBOX_NO = "";
        JIBS_tmp_int = BPCSOSTO.PLBOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCSOSTO.PLBOX_NO += " ";
        if (BPCSOSTO.PLBOX_NO.substring(0, 1).equalsIgnoreCase(K_LONGTOU_BOX_FLG+"")) {
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
            BPCUCSTO.CS_KIND = BPCSOSTO.CS_KIND;
        }
        BPCUCSTO.CASH_TYP = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCUCSTO.CCY = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCUCSTO.TX_AMT = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCSTO.PAR_INFO);
        CEP.TRC(SCCGWA, BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        S000_CALL_BPZUCSTO();
        if (pgmRtn) return;
    }
    public void B020_OUT_STORE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCSTO);
        BPCUCSTO.CASH_STAT = '1';
        BPCUCSTO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCSTO.VB_FLG = '1';
        BPCUCSTO.CASH_TYP = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCUCSTO.CCY = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCUCSTO.CS_KIND = BPCSOSTO.CS_KIND;
        BPCUCSTO.TX_AMT = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCSTO.PAR_INFO);
        CEP.TRC(SCCGWA, BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        S000_CALL_BPZUCSTO();
        if (pgmRtn) return;
    }
    public void B025_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCHIS);
        BPCPCHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPCHIS.PLBOX_NO = BPCUCSTO.PLBOX_NO;
        CEP.TRC(SCCGWA, BPCURMOV.CONF_SEQ);
        BPCPCHIS.CONF_SEQ = BPCURMOV.CONF_SEQ;
        BPCPCHIS.CASH_TYP = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPCHIS.CCY = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCPCHIS.AMT = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPCHIS.PAR_INFO);
        if (BPCUCSTO.PLBOX_NO == null) BPCUCSTO.PLBOX_NO = "";
        JIBS_tmp_int = BPCUCSTO.PLBOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCUCSTO.PLBOX_NO += " ";
        if (BPCUCSTO.PLBOX_NO.substring(0, 1).equalsIgnoreCase(K_LONGTOU_BOX_FLG+"")) {
            BPCPCHIS.VB_FLG = '0';
        } else {
            BPCPCHIS.VB_FLG = '1';
        }
        BPCPCHIS.IN_OUT = 'C';
        BPCPCHIS.CS_KIND = BPCSOSTO.CS_KIND;
        BPCPCHIS.CONF_NO = BPCSOSTO.CONF_NO;
        S000_CALL_BPZPCHIS();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_CSH_APP_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOLIBB);
        BPCOLIBB.FUNC = 'M';
        BPCOLIBB.MODIFY_STS = 'K';
        BPCOLIBB.CONF_SEQ = BPCURMOV.CONF_SEQ;
        BPCOLIBB.APP_NO = BPCSOSTO.APP_NO;
        CEP.TRC(SCCGWA, BPCOLIBB.CONF_SEQ);
        CEP.TRC(SCCGWA, BPCOLIBB.APP_NO);
        S000_CALL_BPZSLIBB();
        if (pgmRtn) return;
        BPCSOSTO.IN_BR = 0;
        BPCSOSTO.MOV_DT = 0;
        BPCSOSTO.CONF_NO = 0;
        BPCSOSTO.APP_TYPE = ' ';
        BPCSOSTO.IN_BR = BPCOLIBB.IN_BR;
        BPCSOSTO.MOV_DT = BPCOLIBB.MOV_DT;
        BPCSOSTO.CONF_NO = BPCOLIBB.CONF_SEQ;
        BPCSOSTO.APP_TYPE = BPCOLIBB.APP_TYPE;
    }
    public void B030_ON_WAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCURMOV);
        BPCURMOV.MOV_TYPE = '3';
        BPCURMOV.CASH_TYP = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCURMOV.CCY = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCURMOV.MOVE_DATE = BPCSOSTO.MOVE_DT;
            BPCURMOV.CONF_SEQ = BPCSOSTO.CONF_NO;
        } else {
            BPCURMOV.MOVE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCURMOV.CONF_SEQ = WS_TEMP_CONF;
        }
        BPCURMOV.CS_KIND = BPCSOSTO.CS_KIND;
        BPCURMOV.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCURMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCURMOV.IN_BR = BPCSOSTO.IN_BR;
        BPCURMOV.IN_AC = BPCSOSTO.IN_AC;
        BPCURMOV.BV_DATE = BPCSOSTO.BV_DATE;
        BPCURMOV.BV_NO = BPCSOSTO.BV_NO;
        BPCURMOV.ONWAY_DT = BPCSOSTO.ONWAY_DT;
        BPCURMOV.ALLOT_TP = BPCSOSTO.ALLOT_TYPE;
        CEP.TRC(SCCGWA, WS_CCY_CNT);
        CEP.TRC(SCCGWA, BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT);
        BPCURMOV.TOTAL_AMT = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCURMOV.CCY_INFO);
        S000_CALL_BPZURMOV();
        if (pgmRtn) return;
    }
    public void B035_ADD_TLR_PENDING_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLPM);
        BPCFTLPM.OP_CODE = 'A';
        CEP.TRC(SCCGWA, BPCSOSTO.IN_TLR);
        BPCFTLPM.BUSS_TYP = K_BUSS_TYPE;
        BPCFTLPM.TX_COUNT = 1;
        S000_CALL_BPZFTLPM();
        if (pgmRtn) return;
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOOSTO);
        BPCOOSTO.IN_BR = BPCSOSTO.IN_BR;
        BPCOOSTO.CS_KIND = BPCSOSTO.CS_KIND;
        BPCOOSTO.BV_DATE = BPCSOSTO.BV_DATE;
        BPCOOSTO.BV_NO = BPCSOSTO.BV_NO;
        BPCOOSTO.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOOSTO.CONF_SEQ = WS_TEMP_CONF;
        BPCOOSTO.APP_NO = BPCOLIBB.APP_NO;
        BPCOOSTO.FLG = BPCSOSTO.APP_TYPE;
        BPCOOSTO.ONWAY_DT = BPCSOSTO.ONWAY_DT;
        BPCOOSTO.ALLOT_TYPE = BPCSOSTO.ALLOT_TYPE;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCOOSTO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCOOSTO.CCY_INFO[WS_CCY_CNT-1].CCY = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY;
            BPCOOSTO.CCY_INFO[WS_CCY_CNT-1].AMT = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            for (WS_START_CNT = 1; WS_START_CNT <= 12 
                && BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_START_CNT-1].CCY_VAL != 0; WS_START_CNT += 1) {
                WS_INFO_CNT = ( WS_CCY_CNT - 1 ) * 12 + WS_START_CNT;
                BPCOOSTO.DT_INFO[WS_INFO_CNT-1].P_PVAL = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_START_CNT-1].CCY_VAL;
                BPCOOSTO.DT_INFO[WS_INFO_CNT-1].P_NUM = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_START_CNT-1].CCY_NUM;
                BPCOOSTO.DT_INFO[WS_INFO_CNT-1].P_MFLG = BPCSOSTO.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_START_CNT-1].CCY_MFLG;
            }
        }
        BPCOOSTO.CCY_CNT = WS_CCY_CNT - 1;
        BPCOOSTO.DT_CNT = WS_INFO_CNT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOOSTO;
        SCCFMT.DATA_LEN = 1760;
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
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSLIBB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_CSHAPP_MAINTAIN, BPCOLIBB);
    }
    public void S000_CALL_BPZPCHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_ADD_CHIS, BPCPCHIS);
        if (BPCPCHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZURMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CMOV_REGIST, BPCURMOV);
        if (BPCURMOV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCURMOV.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_ACCU_MGM, BPCFTLAM);
        if (BPCFTLAM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLAM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSOSTO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUCSTO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_OUT, BPCUCSTO);
        if (BPCUCSTO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCSTO.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_GET_SEQ, BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
