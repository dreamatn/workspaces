package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2135 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm BPTTLVB_RD;
    String K_OUTPUT_FMT = "BP138";
    String CPN_S_IN_STORE_F = "BP-S-IN-STORE-F       ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_MOVD = "BP-P-INQ-MOVD       ";
    String K_HIS_REMARKS = "BR-SPCL-CS-MOV-IN";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY_CNT = 0;
    int WS_INDEX = 0;
    char WS_MATCH_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSISTF BPCSISTF = new BPCSISTF();
    BPCPQMOV BPCPQMOV = new BPCPQMOV();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPB2135_AWA_2135 BPB2135_AWA_2135;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2135 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2135_AWA_2135>");
        BPB2135_AWA_2135 = (BPB2135_AWA_2135) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSISTF);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            B010_CHECK_INPUT();
            B020_CHECK_TLR();
            for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
                && BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
                B030_CHECK_TELLER_FOR_CN();
            }
            B030_HISTORY_RECORD();
            B040_IN_STORE_PROCESS_FOR_CN();
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2135_AWA_2135.CCY_INF[1-1].CCY);
        CEP.TRC(SCCGWA, BPB2135_AWA_2135.CCY_INF[2-1].CCY);
        CEP.TRC(SCCGWA, BPB2135_AWA_2135.CCY_INF[3-1].CCY);
        CEP.TRC(SCCGWA, BPB2135_AWA_2135.CCY_INF[4-1].CCY);
        CEP.TRC(SCCGWA, BPB2135_AWA_2135.CCY_INF[5-1].CCY);
        B010_03_CHECK_CCY_INFO();
    }
    public void B030_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.TX_TYP_CD = "P905";
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
    public void B010_03_CHECK_CCY_INFO() throws IOException,SQLException,Exception {
        for (WS_CCY_CNT = 2; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CCY_CNT);
            CEP.TRC(SCCGWA, BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY);
            if (BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                for (WS_INDEX = 1; WS_INDEX != WS_CCY_CNT; WS_INDEX += 1) {
                    CEP.TRC(SCCGWA, WS_INDEX);
                    CEP.TRC(SCCGWA, BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY);
                    CEP.TRC(SCCGWA, BPB2135_AWA_2135.CCY_INF[WS_INDEX-1].CCY);
                    if (BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY.equalsIgnoreCase(BPB2135_AWA_2135.CCY_INF[WS_INDEX-1].CCY)) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_REPEATED);
                    }
                }
            }
            if (BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT - 1-1].CCY.trim().length() == 0 
                && BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ);
            }
            if (BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT - 1-1].AMT == 0 
                && BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].AMT != 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT_BYSEQ);
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5; WS_CCY_CNT += 1) {
            if (BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                if (BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].AMT == 0) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT);
                }
            }
            if (BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].AMT != 0 
                && BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_INPUT);
            }
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B020_CHECK_TLR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
    }
    public void B030_CHECK_TELLER_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPTTLVB();
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (BPRTLVB.PLBOX_TP == '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR108);
        }
        if (BPRTLVB.PLBOX_TP == '2' 
            || BPRTLVB.PLBOX_TP == '5') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR);
            }
            if (BPB2135_AWA_2135.IN_FLG != '1') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR109);
            }
        }
        if (BPRTLVB.PLBOX_TP == '3' 
            || BPRTLVB.PLBOX_TP == '6') {
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_CASHLIB_TLR);
            }
            if (BPB2135_AWA_2135.IN_FLG != '0') {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR110);
            }
        }
        IBS.init(SCCGWA, BPCPQMOV);
        BPCPQMOV.DATA_INFO.MOV_DT = BPB2135_AWA_2135.MOV_DT;
        BPCPQMOV.DATA_INFO.CONF_NO = BPB2135_AWA_2135.CONF_NO;
        if (BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() > 0) {
            BPCPQMOV.DATA_INFO.CASH_TYP = "01";
        } else {
            BPCPQMOV.DATA_INFO.CASH_TYP = " ";
        }
        BPCPQMOV.DATA_INFO.CCY = BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY;
        S000_CALL_BPZPQMOV();
        if (BPCPQMOV.DATA_INFO.MOV_STS != '1') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CMOV_STS_ERROR);
        }
        if (BPB2135_AWA_2135.OUT_BR != BPCPQMOV.DATA_INFO.OUT_BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV);
        }
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != BPCPQMOV.DATA_INFO.IN_BR) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV);
        }
        CEP.TRC(SCCGWA, BPB2135_AWA_2135.OUT_TLR);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.OUT_TLR);
        if (!BPB2135_AWA_2135.OUT_TLR.equalsIgnoreCase(BPCPQMOV.DATA_INFO.OUT_TLR)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, BPCPQMOV.DATA_INFO.IN_TLR);
        if (!SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPCPQMOV.DATA_INFO.IN_TLR)) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV);
        }
        if (BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].AMT != BPCPQMOV.DATA_INFO.AMT) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_MATCH_CMOV);
        }
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void S000_CALL_BPTTLVB() throws IOException,SQLException,Exception {
        BPTTLVB_RD = new DBParm();
        BPTTLVB_RD.TableName = "BPTTLVB";
        BPTTLVB_RD.where = "BR = :BPRTLVB.KEY.BR "
            + "AND CRNT_TLR = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR1 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR2 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR3 = :BPRTLVB.CRNT_TLR "
            + "OR CRNT_TLR4 = :BPRTLVB.CRNT_TLR";
        BPTTLVB_RD.upd = true;
        IBS.READ(SCCGWA, BPRTLVB, this, BPTTLVB_RD);
    }
    public void B040_IN_STORE_PROCESS_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSISTF);
        BPCSISTF.FMT = K_OUTPUT_FMT;
        BPCSISTF.MOV_DT = BPB2135_AWA_2135.MOV_DT;
        BPCSISTF.CONF_NO = BPB2135_AWA_2135.CONF_NO;
        BPCSISTF.OUT_BR = BPB2135_AWA_2135.OUT_BR;
        BPCSISTF.OUT_TLR = BPB2135_AWA_2135.OUT_TLR;
        BPCSISTF.PLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        BPCSISTF.PLBOX_TP = BPRTLVB.PLBOX_TP;
        BPCSISTF.VB_BOX_OT = BPB2135_AWA_2135.OUT_FLG;
        BPCSISTF.VB_BOX_IN = BPB2135_AWA_2135.IN_FLG;
        for (WS_CCY_CNT = 1; WS_CCY_CNT <= 5 
            && BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() != 0; WS_CCY_CNT += 1) {
            BPCSISTF.DATA_INFO[WS_CCY_CNT-1].CCY = BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY;
            BPCSISTF.DATA_INFO[WS_CCY_CNT-1].TOTAL_AMT = BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].AMT;
            if (BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY.trim().length() > 0) {
                BPCSISTF.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = "01";
            } else {
                BPCSISTF.DATA_INFO[WS_CCY_CNT-1].CASH_TYP = " ";
            }
        }
        S000_CALL_BPZSISTF();
    }
    public void S000_CALL_BPZSISTF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = CPN_S_IN_STORE_F;
        SCCCALL.COMMAREA_PTR = BPCSISTF;
        SCCCALL.ERR_FLDNO = BPB2135_AWA_2135.OUT_BR_NO;
        IBS.CALL(SCCGWA, SCCCALL);
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
            WS_FLD_NO = BPB2135_AWA_2135.CCY_INF[WS_CCY_CNT-1].CCY_NO;
            S000_ERR_MSG_PROC();
        }
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
