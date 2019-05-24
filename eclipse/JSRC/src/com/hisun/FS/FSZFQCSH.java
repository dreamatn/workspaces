package com.hisun.FS;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.CI.*;
import com.hisun.DC.*;
import java.util.StringTokenizer;

import java.io.IOException;
import java.sql.SQLException;

public class FSZFQCSH {
    StringTokenizer JIBS_stb;
    DBParm FSTMST_RD;
    String CDD_U_BPZPNHIS = "BP-REC-NHIS";
    String K_OUTPUT_FMT = "FS800";
    String WS_MSG_ID = " ";
    String WS_ERR_INFO = " ";
    int WS_I = 0;
    FSZFQCSH_WS_ACCT_ARRAY[] WS_ACCT_ARRAY = new FSZFQCSH_WS_ACCT_ARRAY[20];
    char WS_TBL_FLAG = ' ';
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    FSCOQCSH FSCOQCSH = new FSCOQCSH();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDRCCY DDRCCY = new DDRCCY();
    DCRHLD DCRHLD = new DCRHLD();
    FSRMST FSRMST = new FSRMST();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    FSCFQCSH FSCFQCSH;
    public FSZFQCSH() {
        for (int i=0;i<20;i++) WS_ACCT_ARRAY[i] = new FSZFQCSH_WS_ACCT_ARRAY();
    }
    public void MP(SCCGWA SCCGWA, FSCFQCSH FSCFQCSH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FSCFQCSH = FSCFQCSH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "FSZFQCSH return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_INFO();
        B015_LOOP_PROC();
        B040_BP_NFHIS();
        B050_SET_RES();
    }
    public void B010_CHECK_INPUT_INFO() throws IOException,SQLException,Exception {
        JIBS_stb = new StringTokenizer(FSCFQCSH.ACC_NO, "|");
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[01-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[02-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[03-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[04-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[05-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[06-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[07-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[08-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[09-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[10-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[11-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[12-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[13-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[14-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[15-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[16-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[17-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[18-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[19-1].WS_TXN_AC = JIBS_stb.nextToken();
        if (JIBS_stb.hasMoreTokens()) WS_ACCT_ARRAY[20-1].WS_TXN_AC = JIBS_stb.nextToken();
    }
    public void B015_LOOP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCOQCSH);
        for (WS_I = 1; WS_I <= 20 
            && WS_ACCT_ARRAY[WS_I-1].WS_TXN_AC.trim().length() != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            B020_INQ_BAL_PROC();
            B030_TRN_CSHPL_PROC();
        }
    }
    public void B020_INQ_BAL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = WS_ACCT_ARRAY[WS_I-1].WS_TXN_AC;
        DDCIQBAL.DATA.CCY = FSCFQCSH.CCY;
        if (FSCFQCSH.CCY.trim().length() == 0) {
            DDCIQBAL.DATA.CCY = "156";
        }
        DDCIQBAL.DATA.CCY_TYPE = FSCFQCSH.CCY_TYP;
        if (FSCFQCSH.CCY_TYP == ' ') {
            DDCIQBAL.DATA.CCY_TYPE = '1';
        }
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AC);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_TYPE);
        S000_CALL_DDZIQBAL();
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS);
        if (DDCIQBAL.DATA.CCY_STS != 'N') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_STS_NO_INVALID;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, FSRMST);
        FSRMST.KEY.ACC_NO = WS_ACCT_ARRAY[WS_I-1].WS_TXN_AC;
        T000_READ_FSTMST();
        CEP.TRC(SCCGWA, FSRMST.UPACC_NO);
        if (!FSRMST.UPACC_NO.equalsIgnoreCase(FSCFQCSH.UPACC_NO)) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.SOZM_IZM_CI_NO_SAME;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_TRN_CSHPL_PROC() throws IOException,SQLException,Exception {
        FSCOQCSH.INQ_ARRAY[WS_I-1].UP_ACCT = FSRMST.UPACC_NO;
        FSCOQCSH.INQ_ARRAY[WS_I-1].ACCT = FSRMST.KEY.ACC_NO;
        FSCOQCSH.INQ_ARRAY[WS_I-1].CCY = DDCIQBAL.DATA.CCY;
        FSCOQCSH.INQ_ARRAY[WS_I-1].CCY_TYP = DDCIQBAL.DATA.CCY_TYPE;
        FSCOQCSH.INQ_ARRAY[WS_I-1].UP_AMT = FSRMST.SS_BAL;
        FSCOQCSH.INQ_ARRAY[WS_I-1].DOWN_AMT = FSRMST.XH_BAL;
        FSCOQCSH.INQ_ARRAY[WS_I-1].LT_AMT = FSRMST.LIMIT_AMT;
        FSCOQCSH.INQ_ARRAY[WS_I-1].LT_AL_AMT = FSRMST.LIMIT_USED;
        FSCOQCSH.INQ_ARRAY[WS_I-1].AC_BAL = DDCIQBAL.DATA.CURR_BAL;
        FSCOQCSH.INQ_ARRAY[WS_I-1].AVL_BAL = DDCIQBAL.DATA.AVL_BAL;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
        CEP.TRC(SCCGWA, FSCOQCSH.INQ_ARRAY[WS_I-1].AC_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AVL_BAL);
        CEP.TRC(SCCGWA, FSCOQCSH.INQ_ARRAY[WS_I-1].AVL_BAL);
        CEP.TRC(SCCGWA, FSCOQCSH.INQ_ARRAY[WS_I-1]);
    }
    public void B040_BP_NFHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.AC = FSCFQCSH.ACC_NO;
        BPCPNHIS.INFO.TX_TOOL = FSCFQCSH.ACC_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = " ";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.REF_NO = " ";
        BPCPNHIS.INFO.TX_RMK = " ";
        BPCPNHIS.INFO.NEW_DAT_PT = FSCFQCSH;
        BPCPNHIS.INFO.FMT_ID = "FSZFQCSH";
        BPCPNHIS.INFO.FMT_ID_LEN = 436;
        S000_CALL_BPZPNHIS();
    }
    public void B050_SET_RES() throws IOException,SQLException,Exception {
        WS_I = WS_I - 1;
        FSCOQCSH.ACCTS_NUM = (short) WS_I;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = FSCOQCSH;
        SCCFMT.DATA_LEN = 3403;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_FSTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FSRMST.KEY.ACC_NO);
        FSTMST_RD = new DBParm();
        FSTMST_RD.TableName = "FSTMST";
        IBS.READ(SCCGWA, FSRMST, FSTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_NO_RSLT;
            S000_ERR_MSG_PROC();
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE FSTMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DDCIQBAL.RC);
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CDD_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
