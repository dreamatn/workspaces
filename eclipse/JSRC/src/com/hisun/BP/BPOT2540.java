package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2540 {
    int JIBS_tmp_int;
    String K_OUTPUT_FMT = "BP276";
    char K_ATM_IN = '4';
    char K_MOVE_STS_UNCONF = '1';
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_MOVD = "BP-P-INQ-MOVD       ";
    String CPN_P_Q_CBOX = "BP-P-Q-CBOX         ";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_R_BRW_CMOV = "BP-R-BRE-CMOV ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INDEX = 0;
    int WS_I = 0;
    int WS_CONF_NO = 0;
    char WS_CS_KIND = ' ';
    char WS_ONWAY_INFO_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQMOV BPCPQMOV = new BPCPQMOV();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQBOX BPCPQBOX = new BPCPQBOX();
    BPCOZAMO BPCOZAMO = new BPCOZAMO();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCTMOVB BPCTMOVB = new BPCTMOVB();
    BPRCMOV BPRCMOV = new BPRCMOV();
    SCCGWA SCCGWA;
    BPB2500_AWA_2500 BPB2500_AWA_2500;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2540 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2500_AWA_2500>");
        BPB2500_AWA_2500 = (BPB2500_AWA_2500) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CONF_NO);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.MOVE_DT);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[1-1].CCY_AMT);
        B010_CHECK_INPUT_CCY_INFO();
        B020_CHECK_ORG();
        B070_GET_PLBOX_NO();
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0) {
                B030_CHECK_TELLER_FOR_CN();
            }
        }
        if (BPB2500_AWA_2500.CONF_NO == 0) {
            BPB2500_AWA_2500.CONF_NO = WS_CONF_NO;
        }
        B060_TRANS_DATA_OUTPUT();
    }
    public void B010_CHECK_INPUT_CCY_INFO() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 2; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY);
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                for (WS_INDEX = 1; WS_INDEX != WS_CCY_CNT; WS_INDEX += 1) {
                    CEP.TRC(SCCGWA, WS_INDEX);
                    CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY);
                    CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_INDEX-1].CCY);
                    if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2500_AWA_2500.CCY_INFO[WS_INDEX-1].CCY) 
                        && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP.equalsIgnoreCase(BPB2500_AWA_2500.CCY_INFO[WS_INDEX-1].CASH_TYP)) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_REPEATED;
                        WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                        S000_ERR_MSG_PROC();
                    }
                }
            }
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT - 1-1].CCY.trim().length() == 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC();
            }
        }
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != 0 
                && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
        }
        if (BPB2500_AWA_2500.CCY_INFO[1-1].CCY.trim().length() == 0 
            || BPB2500_AWA_2500.CCY_INFO[1-1].CASH_TYP.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT;
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[1-1].CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_CHECK_ORG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ATM_ID_STS_ERR;
            WS_FLD_NO = 0;
            S000_ERR_MSG_PROC();
        }
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2500_AWA_2500.ATM_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_CHECK_TELLER_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBOX);
        BPCPQBOX.DATA_INFO.VB_BR = BPRTLVB.KEY.BR;
        BPCPQBOX.DATA_INFO.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPCPQBOX.DATA_INFO.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
        BPCPQBOX.DATA_INFO.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
        S000_CALL_BPZPQBOX();
        if (!BPCPQBOX.DATA_INFO.MGR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CASHBOX_TLR;
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB2500_AWA_2500.CONF_NO == 0) {
            IBS.init(SCCGWA, BPCTMOVB);
            IBS.init(SCCGWA, BPRCMOV);
            BPRCMOV.KEY.MOV_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPRCMOV.KEY.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPRCMOV.KEY.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPRCMOV.MOV_TYP = K_ATM_IN;
            BPRCMOV.MOV_STS = K_MOVE_STS_UNCONF;
            BPRCMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRCMOV.IN_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTMOVB.FUNC = 'I';
            BPCTMOVB.POINTER = BPRCMOV;
            BPCTMOVB.DATA_LEN = 228;
            S000_CALL_BPZTMOVB();
            BPCTMOVB.FUNC = 'R';
            BPCTMOVB.POINTER = BPRCMOV;
            BPCTMOVB.DATA_LEN = 228;
            S000_CALL_BPZTMOVB();
            WS_ONWAY_INFO_FLAG = 'N';
            while (BPCTMOVB.RETURN_INFO != 'N' 
                && WS_ONWAY_INFO_FLAG != 'Y') {
                if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT == BPRCMOV.AMT) {
                    WS_ONWAY_INFO_FLAG = 'Y';
                    WS_CONF_NO = BPRCMOV.KEY.CONF_NO;
                } else {
                    BPCTMOVB.FUNC = 'R';
                    BPCTMOVB.POINTER = BPRCMOV;
                    BPCTMOVB.DATA_LEN = 228;
                    S000_CALL_BPZTMOVB();
                }
            }
            if (WS_ONWAY_INFO_FLAG == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC();
            }
        } else {
            IBS.init(SCCGWA, BPCPQMOV);
            BPCPQMOV.DATA_INFO.MOV_DT = BPB2500_AWA_2500.MOVE_DT;
            BPCPQMOV.DATA_INFO.CONF_NO = BPB2500_AWA_2500.CONF_NO;
            BPCPQMOV.DATA_INFO.CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCPQMOV.DATA_INFO.CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_DT);
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.CONF_NO);
            S000_CALL_BPZPQMOV();
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.MOV_STS);
            if (BPCPQMOV.DATA_INFO.MOV_STS != '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.IN_TLR);
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.IN_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            if (!SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPCPQMOV.DATA_INFO.IN_TLR)) {
                CEP.TRC(SCCGWA, "GWA-TL-ID NOT = PQMOV-IN-TLR");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPQMOV.DATA_INFO.IN_BR) {
                CEP.TRC(SCCGWA, "GWA-TR-BRANCH NOT = PQMOV-IN-B");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT);
            CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.AMT);
            if (BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT != BPCPQMOV.DATA_INFO.AMT) {
                CEP.TRC(SCCGWA, "AWA-CCY-AMT(WS-CCY-CNT) NOT = PQMOV-AMT");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
                WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT_NO;
                S000_ERR_MSG_PROC_CONTINUE();
            }
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV;
            S000_ERR_MSG_PROC_LAST();
        }
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOZAMO);
        BPCOZAMO.TO_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCOZAMO.CONF_SEQ = BPB2500_AWA_2500.CONF_NO;
        BPCOZAMO.MOVE_DT = BPB2500_AWA_2500.MOVE_DT;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY);
            BPCOZAMO.CCY_INFO[WS_CCY_CNT-1].CASH_TYP = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CASH_TYP;
            BPCOZAMO.CCY_INFO[WS_CCY_CNT-1].CCY = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY;
            BPCOZAMO.CCY_INFO[WS_CCY_CNT-1].AMT = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_AMT;
            BPCOZAMO.CCY_CNT = WS_CCY_CNT;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOZAMO;
        SCCFMT.DATA_LEN = 135;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B070_GET_PLBOX_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCTLVBF);
        BPRTLVB.PLBOX_TP = '4';
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCTLVBF.INFO.FUNC = 'I';
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        S000_CALL_BPZTLVBF();
        if (BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQMOV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_MOVD, BPCPQMOV);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (BPCPQMOV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQMOV.RC);
            WS_FLD_NO = BPB2500_AWA_2500.CCY_INFO[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTMOVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_CMOV, BPCTMOVB);
    }
    public void S000_CALL_BPZPQBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_Q_CBOX, BPCPQBOX);
        if (BPCPQBOX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBOX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
