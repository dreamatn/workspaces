package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAZUCSEQ {
    int JIBS_tmp_int;
    String K_HIS_RMKS = "BA CHANGE DEDUCT SEQENCE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_CNTR_NO = " ";
    short WS_ACCT_CNT = 0;
    int WS_ACPT_BR = 0;
    char WS_AMT_STS = ' ';
    double WS_BILL_AMT = 0;
    String WS_DRWR_AC = " ";
    char WS_DBT_SEQ = ' ';
    String WS_SUSP_NO = " ";
    String WS_BILL_CUR = " ";
    String WS_PYE_NM = " ";
    String WS_PYE_AC = " ";
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BACFMST1 BACFMST1 = new BACFMST1();
    BARMST1 BARMST1 = new BARMST1();
    BARTXDL BARTXDL = new BARTXDL();
    BACFTXDL BACFTXDL = new BACFTXDL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    BACUCSEQ BACUCSEQ;
    public void MP(SCCGWA SCCGWA, BACUCSEQ BACUCSEQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACUCSEQ = BACUCSEQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BAZUCSEQ return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BACFMST1.RC.RC_MMO = "BA";
        BACFMST1.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_PROC_MAIN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BACUCSEQ.BILL_NO.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_INPUT_BILL_NO;
            S000_ERR_MSG_PROC();
        }
        if (BACUCSEQ.DBT_SEQ == ' ') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_INPUT_NEW_SEQ;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_PROC_MAIN() throws IOException,SQLException,Exception {
        B021_READ_BILL_INFO();
        B022_CHECK_STATUS();
        B023_UPDATE_NEW_SEQ();
        B024_WRITE_BATTXDL();
    }
    public void B021_READ_BILL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFMST1);
        IBS.init(SCCGWA, BARMST1);
        BACFMST1.FUNC = 'T';
        BARMST1.BILL_NO = BACUCSEQ.BILL_NO;
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        S000_CALL_BAZFMST1();
        if (BACFMST1.RETURN_INFO == 'N') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_MST1_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (BACUCSEQ.DBT_SEQ == BARMST1.DBT_SEQ) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_WAR_DBT_SEQ_SAME;
            S000_ERR_MSG_PROC();
        }
        WS_CNTR_NO = BARMST1.KEY.CNTR_NO;
        WS_ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        WS_ACPT_BR = BARMST1.ACPT_BR;
        WS_AMT_STS = BARMST1.AMT_STS;
        WS_BILL_CUR = BARMST1.BILL_CUR;
        WS_DRWR_AC = BARMST1.DRWR_AC;
        WS_PYE_AC = BARMST1.PYE_AC;
        WS_PYE_NM = BARMST1.PYE_NM;
        WS_SUSP_NO = BARMST1.SUSP_NO;
    }
    public void B022_CHECK_STATUS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_AMT_STS);
        if (WS_AMT_STS != '1') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_BILL_IS_DEDUCT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B023_UPDATE_NEW_SEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFMST1);
        CEP.TRC(SCCGWA, BARMST1.BILL_NO);
        BARMST1.DBT_SEQ = BACUCSEQ.DBT_SEQ;
        BACFMST1.FUNC = 'U';
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        S000_CALL_BAZFMST1();
    }
    public void B024_WRITE_BATTXDL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFTXDL);
        IBS.init(SCCGWA, BARTXDL);
        B030_MOVE_TO_TXDL();
        BACFTXDL.FUNC = 'A';
        BACFTXDL.REC_PTR = BARTXDL;
        BACFTXDL.REC_LEN = 514;
        S000_CALL_BAZFTXDL();
    }
    public void B030_MOVE_TO_TXDL() throws IOException,SQLException,Exception {
        BARTXDL.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARTXDL.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.PRPH_SYS_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BARTXDL.PRPH_SYS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.PRPH_JRN = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = BARTXDL.PRPH_JRN.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BARTXDL.PRPH_JRN = "0" + BARTXDL.PRPH_JRN;
        BARTXDL.CNTR_NO = WS_CNTR_NO;
        BARTXDL.ACCT_CNT = WS_ACCT_CNT;
        BARTXDL.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BARTXDL.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BARTXDL.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BARTXDL.BILL_NO = BACUCSEQ.BILL_NO;
        BARTXDL.TX_AC = WS_DRWR_AC;
        BARTXDL.SUSP_NO = WS_SUSP_NO;
        BARTXDL.TX_CUR = WS_BILL_CUR;
        BARTXDL.TX_AMT = WS_BILL_AMT;
        BARTXDL.REC_FLG = '1';
    }
    public void S000_CALL_BAZFTXDL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFTXDL", BACFTXDL);
        if (BACFTXDL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFTXDL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
        if (BACFMST1.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFMST1.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
