package com.hisun.BA;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DD.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2121 {
    int JIBS_tmp_int;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_OPEN_DP = 0;
    BAOT2121_WS_BILL_TEMP WS_BILL_TEMP = new BAOT2121_WS_BILL_TEMP();
    long WS_OUT_JRNNO = 0;
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BACFMST1 BACFMST1 = new BACFMST1();
    BARMST1 BARMST1 = new BARMST1();
    BARTXDL BARTXDL = new BARTXDL();
    BACFTXDL BACFTXDL = new BACFTXDL();
    DDCIMMST DDCIMMST = new DDCIMMST();
    CICACCU CICACCU = new CICACCU();
    DDCSCINM DDCSCINM = new DDCSCINM();
    SCCGWA SCCGWA;
    BAB2121_AWA_2121 BAB2121_AWA_2121;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGAPV SCCGAPV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BAOT2121 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2121_AWA_2121>");
        BAB2121_AWA_2121 = (BAB2121_AWA_2121) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) SCCGSCA_SC_AREA.APVL_AREA_PTR;
        BACFMST1.RC.RC_MMO = "BA";
        BACFMST1.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_PROC_MAIN();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BAB2121_AWA_2121.BILL_NO);
        CEP.TRC(SCCGWA, BAB2121_AWA_2121.NEW_AC);
        if (BAB2121_AWA_2121.BILL_NO.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_INPUT_BILL_NO;
            if (BAB2121_AWA_2121.BILL_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BAB2121_AWA_2121.BILL_NO);
            S000_ERR_MSG_PROC();
        }
        if (BAB2121_AWA_2121.NEW_AC.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_INPUT_NEW_AC;
            if (BAB2121_AWA_2121.NEW_AC.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BAB2121_AWA_2121.NEW_AC);
            S000_ERR_MSG_PROC();
        }
        if (BAB2121_AWA_2121.NEW_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDCIMMST);
            DDCIMMST.DATA.KEY.AC_NO = BAB2121_AWA_2121.NEW_AC;
            DDCIMMST.TX_TYPE = 'I';
            S000_CALL_DDZIMMST();
            CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS_WORD);
        }
    }
    public void B020_PROC_MAIN() throws IOException,SQLException,Exception {
        B021_READ_BILL_INFO();
        B023_UPDATE_NEW_AC();
        B024_WRITE_BATTXDL();
    }
    public void B021_READ_BILL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFMST1);
        IBS.init(SCCGWA, BARMST1);
        IBS.init(SCCGWA, WS_BILL_TEMP);
        BACFMST1.FUNC = 'T';
        BARMST1.BILL_NO = BAB2121_AWA_2121.BILL_NO;
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        S000_CALL_BAZFMST1();
        if (BACFMST1.RETURN_INFO == 'N') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_MST1_NOTFND;
            S000_ERR_MSG_PROC();
        }
        if (BAB2121_AWA_2121.NEW_AC.equalsIgnoreCase(BARMST1.DRWR_AC)) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_WAR_DBT_AC_SAME;
            S000_ERR_MSG_PROC();
        }
        WS_BILL_TEMP.WS_CNTR_NO = BARMST1.KEY.CNTR_NO;
        WS_BILL_TEMP.WS_ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        WS_BILL_TEMP.WS_DRWR_AC = BARMST1.DRWR_AC;
        CEP.TRC(SCCGWA, WS_BILL_TEMP.WS_DRWR_AC);
        if (WS_BILL_TEMP.WS_DRWR_AC.trim().length() > 0) {
            IBS.init(SCCGWA, DDCSCINM);
            DDCSCINM.INPUT_DATA.AC_NO = WS_BILL_TEMP.WS_DRWR_AC;
            DDCSCINM.INPUT_DATA.FUNC = '2';
            S000_CALL_DDZSCINM();
            WS_OPEN_DP = DDCSCINM.OUTPUT_DATA.OPEN_DP;
        }
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.AC_NO = BAB2121_AWA_2121.NEW_AC;
        DDCSCINM.INPUT_DATA.FUNC = '2';
        S000_CALL_DDZSCINM();
        if (WS_OPEN_DP != 0 
            && DDCSCINM.OUTPUT_DATA.OPEN_DP != 0) {
            if (DDCSCINM.OUTPUT_DATA.OPEN_DP != WS_OPEN_DP) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_CMMT_NO_NOTMATCH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B023_UPDATE_NEW_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACFMST1);
        CEP.TRC(SCCGWA, BARMST1.BILL_NO);
        BARMST1.DRWR_AC = BAB2121_AWA_2121.NEW_AC;
        if (BARMST1.OLD_DRWR_AC.trim().length() == 0) {
            BARMST1.OLD_DRWR_AC = WS_BILL_TEMP.WS_DRWR_AC;
        }
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
        BARTXDL.CNTR_NO = WS_BILL_TEMP.WS_CNTR_NO;
        BARTXDL.ACCT_CNT = WS_BILL_TEMP.WS_ACCT_CNT;
        BARTXDL.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BARTXDL.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BARTXDL.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BARTXDL.BILL_NO = BAB2121_AWA_2121.BILL_NO;
        BARTXDL.TX_AC = WS_BILL_TEMP.WS_DRWR_AC;
        BARTXDL.REC_FLG = '1';
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void S000_CALL_BAZFTXDL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFTXDL", BACFTXDL);
        if (BACFTXDL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "*CALL S000-CALL-BAZFTXDL RETURN FAILED*");
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
    public void S000_CALL_DDZIMMST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-NFIN-M-MST", DDCIMMST);
        if (DDCIMMST.RC.RC_CODE != 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_NOT_FOUND_OR_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
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
