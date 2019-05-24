package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSTBLC {
    String R_MGM_TBVD = "BP-R-MGM-TBVD";
    String S_SOLD_CANCEL = "BP-S-SOLD-CANCEL    ";
    String REC_NHIS = "BP-REC-NHIS         ";
    String R_BRW_TBVD = "BP-R-BRW-TBVD";
    String R_MGM_TBV = "BP-R-MGM-TBV";
    int MAX_PAR_CNT = 99;
    BPZSTBLC_WS_VARIABLES WS_VARIABLES = new BPZSTBLC_WS_VARIABLES();
    BPZSTBLC_WS_HIS_REMARKS WS_HIS_REMARKS = new BPZSTBLC_WS_HIS_REMARKS();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBCUS BPRBCUS = new BPRBCUS();
    BPCRBCUS BPCRBCUS = new BPCRBCUS();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPCRTBVD BPCRTBVD = new BPCRTBVD();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
    BPCRTBV BPCRTBV = new BPCRTBV();
    BPRTBV BPRTBV = new BPRTBV();
    BPCO181 BPCO181 = new BPCO181();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    BPCSTBLC BPCSTBLC;
    public void MP(SCCGWA SCCGWA, BPCSTBLC BPCSTBLC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSTBLC = BPCSTBLC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSTBLC return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_HIS_REMARKS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT();
        B020_CALL_BPZRTBV();
        B040_REC_NHIS();
        B090_DATA_OUTPUT();
    }
    public void B100_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSTBLC.VB_FLG);
        CEP.TRC(SCCGWA, BPCSTBLC.INFO[1-1].BV_CODE);
        CEP.TRC(SCCGWA, BPCSTBLC.INFO[1-1].BV_NAME);
        CEP.TRC(SCCGWA, BPCSTBLC.INFO[1-1].NUM);
        CEP.TRC(SCCGWA, BPCSTBLC.INFO[1-1].BV_VALUE);
        CEP.TRC(SCCGWA, BPCSTBLC.INFO[2-1].BV_CODE);
        CEP.TRC(SCCGWA, BPCSTBLC.INFO[2-1].BV_NAME);
        CEP.TRC(SCCGWA, BPCSTBLC.INFO[2-1].NUM);
        CEP.TRC(SCCGWA, BPCSTBLC.INFO[2-1].BV_VALUE);
    }
    public void B020_CALL_BPZRTBV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTBV);
        IBS.init(SCCGWA, BPRTBV);
        BPRTBV.KEY.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRTBV.INFO.FUNC = 'R';
        S000_CALL_BPZRTBV();
        if (BPCSTBLC.VB_FLG == '0') {
            BPRTBV.BL_BOX_CHK = 'Y';
        }
        if (BPCSTBLC.VB_FLG == '1') {
            BPRTBV.BL_VLT_CHK = 'Y';
        }
        BPRTBV.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRTBV.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCRTBV.INFO.FUNC = 'U';
        S000_CALL_BPZRTBV();
    }
    public void B040_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "01";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        WS_HIS_REMARKS.HIS_VB_FLG = BPCSTBLC.VB_FLG;
        WS_HIS_REMARKS.HIS_BV_CODE1 = BPCSTBLC.INFO[1-1].BV_CODE;
        WS_HIS_REMARKS.HIS_VALUE1 = BPCSTBLC.INFO[1-1].BV_VALUE;
        WS_HIS_REMARKS.HIS_NUM1 = BPCSTBLC.INFO[1-1].NUM;
        WS_HIS_REMARKS.HIS_BV_CODE2 = BPCSTBLC.INFO[2-1].BV_CODE;
        WS_HIS_REMARKS.HIS_VALUE2 = BPCSTBLC.INFO[2-1].BV_VALUE;
        WS_HIS_REMARKS.HIS_NUM2 = BPCSTBLC.INFO[2-1].NUM;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, WS_HIS_REMARKS);
        S000_CALL_BPZPNHIS();
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO181);
        BPCO181.VB_FLG = BPCSTBLC.VB_FLG;
        for (WS_VARIABLES.I = 1; BPCSTBLC.INFO[WS_VARIABLES.I-1].BV_CODE.trim().length() != 0 
            && WS_VARIABLES.I <= MAX_PAR_CNT; WS_VARIABLES.I += 1) {
            IBS.init(SCCGWA, BPRTBVD);
            BPCO181.INFO[WS_VARIABLES.I-1].BV_CODE = BPCSTBLC.INFO[WS_VARIABLES.I-1].BV_CODE;
            BPCO181.INFO[WS_VARIABLES.I-1].BV_NAME = BPCSTBLC.INFO[WS_VARIABLES.I-1].BV_NAME;
            BPCO181.INFO[WS_VARIABLES.I-1].BV_VALUE = BPCSTBLC.INFO[WS_VARIABLES.I-1].BV_VALUE;
            BPCO181.INFO[WS_VARIABLES.I-1].NUM = BPCSTBLC.INFO[WS_VARIABLES.I-1].NUM;
            BPCO181.CNT = WS_VARIABLES.I;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSTBLC.OUTPUT_FMT;
        CEP.TRC(SCCGWA, SCCFMT.FMTID);
        SCCFMT.DATA_PTR = BPCO181;
        SCCFMT.DATA_LEN = 6155;
        CEP.TRC(SCCGWA, BPCO181);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRTBDB() throws IOException,SQLException,Exception {
        BPCRTBDB.INFO.POINTER = BPRTBVD;
        BPCRTBDB.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, R_BRW_TBVD, BPCRTBDB);
        CEP.TRC(SCCGWA, BPCRTBDB.RETURN_INFO);
        CEP.TRC(SCCGWA, BPCRTBDB.RC);
        if (BPCRTBDB.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBDB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTBVD() throws IOException,SQLException,Exception {
        BPCRTBVD.INFO.POINTER = BPRTBVD;
        BPCRTBVD.INFO.LEN = 160;
        IBS.CALLCPN(SCCGWA, R_MGM_TBVD, BPCRTBVD);
        if (BPCRTBVD.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBVD.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRTBV() throws IOException,SQLException,Exception {
        BPCRTBV.INFO.POINTER = BPRTBV;
        BPCRTBV.INFO.LEN = 30;
        IBS.CALLCPN(SCCGWA, R_MGM_TBV, BPCRTBV);
        CEP.TRC(SCCGWA, BPCRTBV.RC);
        if (BPCRTBV.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBV.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRBCUS() throws IOException,SQLException,Exception {
        BPCRBCUS.INFO.POINTER = BPRBCUS;
        BPCRBCUS.INFO.LEN = 106;
        IBS.CALLCPN(SCCGWA, S_SOLD_CANCEL, BPCRBCUS);
        if (BPCRBCUS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBCUS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
