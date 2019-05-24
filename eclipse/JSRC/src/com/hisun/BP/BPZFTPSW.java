package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFTPSW {
    int JIBS_tmp_int;
    String CPN_P_SET_PSW_REPEAT = "BP-P-SET-PSW-RETRY  ";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "SIGN ON PASSWORD ERROR!";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFSPWR BPCFSPWR = new BPCFSPWR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCFTPSW BPCFTPSW;
    public void MP(SCCGWA SCCGWA, BPCFTPSW BPCFTPSW) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFTPSW = BPCFTPSW;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZFTPSW return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFSPWR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFTPSW.TLR);
        B010_CHECK_INPUT_DATA();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFSPWR);
        BPCFSPWR.FUNC = 'A';
        BPCFSPWR.TLR = BPCFTPSW.TLR;
        S000_CALL_BPZFSPWR();
        S000_HISTORY_PROCESS();
    }
    public void S000_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = "BPVCNTY";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        BPCPNHIS.INFO.REF_NO = BPCFTPSW.TLR;
        BPCPNHIS.INFO.TX_TYP_CD = "TLRSIGN";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        if (BPCPNHIS.INFO.TX_RMK == null) BPCPNHIS.INFO.TX_RMK = "";
        JIBS_tmp_int = BPCPNHIS.INFO.TX_RMK.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) BPCPNHIS.INFO.TX_RMK += " ";
        if (K_HIS_REMARKS == null) K_HIS_REMARKS = "";
        JIBS_tmp_int = K_HIS_REMARKS.length();
        for (int i=0;i<240-JIBS_tmp_int;i++) K_HIS_REMARKS += " ";
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS + BPCPNHIS.INFO.TX_RMK.substring(30);
        if (BPCPNHIS.INFO.TX_RMK == null) BPCPNHIS.INFO.TX_RMK = "";
        JIBS_tmp_int = BPCPNHIS.INFO.TX_RMK.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) BPCPNHIS.INFO.TX_RMK += " ";
        if (SCCGWA.COMM_AREA.TERM_ID == null) SCCGWA.COMM_AREA.TERM_ID = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.TERM_ID.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.TERM_ID += " ";
        BPCPNHIS.INFO.TX_RMK = BPCPNHIS.INFO.TX_RMK.substring(0, 31 - 1) + SCCGWA.COMM_AREA.TERM_ID + BPCPNHIS.INFO.TX_RMK.substring(31 + 7 - 1);
        S000_CALL_BPZPNHIS();
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFSPWR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_SET_PSW_REPEAT, BPCFSPWR);
        if (BPCFSPWR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFSPWR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
