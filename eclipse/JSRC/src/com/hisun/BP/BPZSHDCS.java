package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSHDCS {
    String JIBS_tmp_str[] = new String[10];
    String K_OUTPUT_FMT = "BP102";
    String K_MOV_SEQ_TYPE = "CMOVE";
    String K_MOV_SEQ_NAME = "CASHMOVE";
    String K_BUSS_TYPE = "99";
    String CPN_F_TLR_PEND_MGM = "BP-F-TLR-PEND-MGM   ";
    String CPN_F_TLR_ACCU_MGM = "BP-F-TLR-ACCU-MGM ";
    String CPN_S_GET_SEQ = "BP-S-GET-SEQ        ";
    String CPN_F_TLR_STS = "BP-F-TLR-STS-QRY    ";
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
    BPCOCFDR BPCOCFDR = new BPCOCFDR();
    BPCPCHIS BPCPCHIS = new BPCPCHIS();
    BPCFTLPM BPCFTLPM = new BPCFTLPM();
    SCCGWA SCCGWA;
    BPCSHDCS BPCSHDCS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSHDCS BPCSHDCS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSHDCS = BPCSHDCS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSHDCS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            B020_OUT_STORE_PROC();
            B030_ON_WAY_PROC();
            CEP.TRC(SCCGWA, BPCURMOV.CONF_SEQ);
            if (WS_CCY_CNT == 1) {
                WS_TEMP_CONF = BPCURMOV.CONF_SEQ;
            }
            B025_HISTORY_PROC();
        }
        BPCSHDCS.CONF_NO = WS_TEMP_CONF;
        BPCSHDCS.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B050_OUTPUT_PROC();
        }
    }
    public void B020_OUT_STORE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUCSTO);
        BPCUCSTO.CASH_STAT = '1';
        BPCUCSTO.VB_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUCSTO.VB_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUCSTO.VB_FLG = '1';
        BPCUCSTO.CASH_TYP = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCUCSTO.CCY = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCUCSTO.CS_KIND = BPCSHDCS.CS_KIND;
        BPCUCSTO.TX_AMT = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCSTO.PAR_INFO);
        S000_CALL_BPZUCSTO();
    }
    public void B025_HISTORY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPCHIS);
        BPCPCHIS.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPCHIS.PLBOX_NO = BPCUCSTO.PLBOX_NO;
        BPCPCHIS.CASH_TYP = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPCHIS.CCY = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY;
        BPCPCHIS.AMT = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPCHIS.PAR_INFO);
        BPCPCHIS.VB_FLG = '1';
        BPCPCHIS.IN_OUT = 'C';
        CEP.TRC(SCCGWA, BPCURMOV.CONF_SEQ);
        BPCPCHIS.CONF_SEQ = BPCURMOV.CONF_SEQ;
        BPCPCHIS.CS_KIND = BPCSHDCS.CS_KIND;
        S000_CALL_BPZPCHIS();
    }
    public void B030_ON_WAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCURMOV);
        BPCURMOV.MOV_TYPE = '2';
        BPCURMOV.CASH_TYP = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCURMOV.CCY = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY;
        CEP.TRC(SCCGWA, BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            BPCURMOV.MOVE_DATE = BPCSHDCS.MOVE_DT;
            BPCURMOV.CONF_SEQ = BPCSHDCS.CONF_NO;
        } else {
            BPCURMOV.MOVE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCURMOV.CONF_SEQ = WS_TEMP_CONF;
        }
        CEP.TRC(SCCGWA, BPCSHDCS.IN_BR);
        BPCURMOV.CS_KIND = BPCSHDCS.CS_KIND;
        BPCURMOV.OUT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCURMOV.OUT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCURMOV.IN_BR = BPCSHDCS.IN_BR;
        BPCURMOV.IN_TLR = BPCSHDCS.IN_TLR;
        BPCURMOV.IN_AC = BPCSHDCS.IN_AC;
        BPCURMOV.BV_DATE = BPCSHDCS.BV_DATE;
        BPCURMOV.BV_NO = BPCSHDCS.BV_NO;
        BPCURMOV.TO_BANK = BPCSHDCS.TO_BANK;
        BPCURMOV.TOTAL_AMT = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY_INFO);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCURMOV.CCY_INFO);
        S000_CALL_BPZURMOV();
    }
    public void B035_ADD_TLR_PENDING_NUM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLPM);
        BPCFTLPM.OP_CODE = 'A';
        BPCFTLPM.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCFTLPM.BUSS_TYP = K_BUSS_TYPE;
        BPCFTLPM.TX_COUNT = 1;
        S000_CALL_BPZFTLPM();
    }
    public void B050_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCFDR);
        BPCOCFDR.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCOCFDR.CS_KIND = BPCSHDCS.CS_KIND;
        BPCOCFDR.MOVE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOCFDR.CONF_SEQ = WS_TEMP_CONF;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY);
            BPCOCFDR.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCOCFDR.CCY_INFO[WS_CCY_CNT-1].CCY = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY;
            BPCOCFDR.CCY_INFO[WS_CCY_CNT-1].AMT = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT;
            for (WS_START_CNT = 1; WS_START_CNT <= 12 
                && BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_START_CNT-1].CCY_VAL != 0; WS_START_CNT += 1) {
                WS_INFO_CNT = ( WS_CCY_CNT - 1 ) * 12 + WS_START_CNT;
                BPCOCFDR.DT_INFO[WS_INFO_CNT-1].P_PVAL = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_START_CNT-1].CCY_VAL;
                BPCOCFDR.DT_INFO[WS_INFO_CNT-1].P_NUM = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_START_CNT-1].CCY_NUM;
                BPCOCFDR.DT_INFO[WS_INFO_CNT-1].P_MFLG = BPCSHDCS.DATA_INFO[WS_CCY_CNT-1].CCY_INFO.CCY_DETAIL[WS_START_CNT-1].CCY_MFLG;
                BPCOCFDR.DT_CNT = WS_INFO_CNT;
            }
            BPCOCFDR.CCY_CNT = WS_CCY_CNT;
        }
        CEP.TRC(SCCGWA, "WS-CCY-CNT1234");
        CEP.TRC(SCCGWA, WS_CCY_CNT);
        CEP.TRC(SCCGWA, WS_INFO_CNT);
        CEP.TRC(SCCGWA, BPCOCFDR.CCY_CNT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOCFDR;
        SCCFMT.DATA_LEN = 2348;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, BPCOCFDR);
        CEP.TRC(SCCGWA, SCCFMT.FMTID);
        CEP.TRC(SCCGWA, SCCFMT);
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
    public void S000_CALL_BPZURMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CMOV_REGIST, BPCURMOV);
        if (BPCURMOV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCURMOV.RC);
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
    public void S000_CALL_BPZUCSTO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CASH_OUT, BPCUCSTO);
        if (BPCUCSTO.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUCSTO.RC);
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
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_GET_SEQ, BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
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
