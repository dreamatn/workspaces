package com.hisun.FS;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class FSZFTRAN {
    int JIBS_tmp_int;
    DBParm FSTMST_RD;
    DBParm FSTTXD_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "FS100";
    String WS_MSG_ID = " ";
    String WS_ERR_INFO = " ";
    double WS_AVL_AMT = 0;
    double WS_OD_AMT = 0;
    double WS_AVL_BAL = 0;
    char WS_TBL_FLAG = ' ';
    String WS_TRAN_TYPE = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    FSCOTRAN FSCOTRAN = new FSCOTRAN();
    DDCIQBAL DDCIQBAL = new DDCIQBAL();
    BPCRDAMT BPCRDAMT = new BPCRDAMT();
    FSRMST FSRMST = new FSRMST();
    FSRTXD FSRTXD = new FSRTXD();
    SCCGWA SCCGWA;
    FSCFTRAN FSCFTRAN;
    public void MP(SCCGWA SCCGWA, FSCFTRAN FSCFTRAN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FSCFTRAN = FSCFTRAN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FSZFTRAN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCOTRAN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_INFO();
        if (pgmRtn) return;
        B020_TRAN_PROC();
        if (pgmRtn) return;
        B050_UPDATE_FSTMST_PROC();
        if (pgmRtn) return;
        B060_WRITE_FSTTXD_PROC();
        if (pgmRtn) return;
        B070_SET_RES();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_INFO() throws IOException,SQLException,Exception {
        WS_TRAN_TYPE = FSCFTRAN.TYPE;
        IBS.init(SCCGWA, FSRMST);
        if (((WS_TRAN_TYPE.equalsIgnoreCase("00") 
                || WS_TRAN_TYPE.equalsIgnoreCase("20")))) {
            FSRMST.KEY.ACC_NO = FSCFTRAN.PAY_ACCT;
            T000_READ_FSTMST();
            if (pgmRtn) return;
            if (!FSCFTRAN.RES_ACCT.equalsIgnoreCase(FSRMST.UPACC_NO)) {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NOT_GROUP_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else if (((WS_TRAN_TYPE.equalsIgnoreCase("01") 
                || WS_TRAN_TYPE.equalsIgnoreCase("21")))) {
            FSRMST.KEY.ACC_NO = FSCFTRAN.RES_ACCT;
            T000_READ_FSTMST();
            if (pgmRtn) return;
            if (!FSCFTRAN.PAY_ACCT.equalsIgnoreCase(FSRMST.UPACC_NO)) {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_NOT_GROUP_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_AVL_AMT = ( FSRMST.SS_BAL - FSRMST.XH_BAL + FSRMST.LIMIT_AMT );
            WS_OD_AMT = ( FSRMST.SS_BAL - FSRMST.XH_BAL - FSCFTRAN.TRANS_ATM );
            if (WS_AVL_AMT < FSCFTRAN.TRANS_ATM 
                && FSCFTRAN.LMT_FLG != '1') {
                WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AVL_BAL_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        R000_CHECK_UPACCT_PROC();
        if (pgmRtn) return;
        WS_AVL_BAL = DDCIQBAL.DATA.CURR_BAL - DDCIQBAL.DATA.HOLD_BAL;
        if (WS_AVL_BAL < 0) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (FSCFTRAN.TRANS_ATM != 0) {
            IBS.init(SCCGWA, BPCRDAMT);
            BPCRDAMT.CCY = FSCFTRAN.CCY;
            BPCRDAMT.AMT = FSCFTRAN.TRANS_ATM;
            S00_CALL_BPZRDAMT();
            if (pgmRtn) return;
            FSCFTRAN.TRANS_ATM = BPCRDAMT.RESULT_AMT;
        }
    }
    public void B020_TRAN_PROC() throws IOException,SQLException,Exception {
        B030_DRW_PROC();
        if (pgmRtn) return;
        B040_DEP_PROC();
        if (pgmRtn) return;
    }
    public void B030_DRW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.CHK_PSW_FLG = 'N';
        if (DDCUDRAC.TRT_CTLW == null) DDCUDRAC.TRT_CTLW = "";
        JIBS_tmp_int = DDCUDRAC.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUDRAC.TRT_CTLW += " ";
        DDCUDRAC.TRT_CTLW = DDCUDRAC.TRT_CTLW.substring(0, 2 - 1) + "1" + DDCUDRAC.TRT_CTLW.substring(2 + 1 - 1);
        DDCUDRAC.AC = FSCFTRAN.PAY_ACCT;
        DDCUDRAC.OTHER_AC = FSCFTRAN.RES_ACCT;
        DDCUDRAC.CCY = FSCFTRAN.CCY;
        DDCUDRAC.CCY_TYPE = FSCFTRAN.CCY_TYP;
        DDCUDRAC.TX_MMO = FSCFTRAN.ZY_CODE;
        DDCUDRAC.TX_AMT = FSCFTRAN.TRANS_ATM;
        DDCUDRAC.VAL_DATE = FSCFTRAN.TRAN_DATE;
        DDCUDRAC.REMARKS = FSCFTRAN.REMARK;
        CEP.TRC(SCCGWA, DDCUDRAC.AC);
        CEP.TRC(SCCGWA, DDCUDRAC.CCY);
        CEP.TRC(SCCGWA, DDCUDRAC.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCUDRAC.TX_AMT);
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
    }
    public void B040_DEP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'T';
        if (DDCUCRAC.TRT_CTLW == null) DDCUCRAC.TRT_CTLW = "";
        JIBS_tmp_int = DDCUCRAC.TRT_CTLW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) DDCUCRAC.TRT_CTLW += " ";
        DDCUCRAC.TRT_CTLW = DDCUCRAC.TRT_CTLW.substring(0, 2 - 1) + "1" + DDCUCRAC.TRT_CTLW.substring(2 + 1 - 1);
        DDCUCRAC.AC = FSCFTRAN.RES_ACCT;
        DDCUCRAC.OTHER_AC = FSCFTRAN.PAY_ACCT;
        DDCUCRAC.CCY = FSCFTRAN.CCY;
        DDCUCRAC.CCY_TYPE = FSCFTRAN.CCY_TYP;
        DDCUCRAC.TX_MMO = FSCFTRAN.ZY_CODE;
        DDCUCRAC.TX_AMT = FSCFTRAN.TRANS_ATM;
        DDCUCRAC.VAL_DATE = FSCFTRAN.TRAN_DATE;
        DDCUCRAC.REMARKS = FSCFTRAN.REMARK;
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B050_UPDATE_FSTMST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRMST);
        if (((WS_TRAN_TYPE.equalsIgnoreCase("00") 
                || WS_TRAN_TYPE.equalsIgnoreCase("20")))) {
            FSRMST.KEY.ACC_NO = FSCFTRAN.PAY_ACCT;
            T000_READ_UPDATE_FSTMST();
            if (pgmRtn) return;
            FSRMST.SS_BAL = FSRMST.SS_BAL + FSCFTRAN.TRANS_ATM;
            if (FSRMST.SS_BAL >= FSRMST.XH_BAL) {
                FSRMST.LIMIT_USED = 0;
            } else {
                FSRMST.LIMIT_USED = FSRMST.XH_BAL - FSRMST.SS_BAL;
            }
            T000_REWRITE_FSTMST();
            if (pgmRtn) return;
        } else if (((WS_TRAN_TYPE.equalsIgnoreCase("01") 
                || WS_TRAN_TYPE.equalsIgnoreCase("21")))) {
            FSRMST.KEY.ACC_NO = FSCFTRAN.RES_ACCT;
            T000_READ_UPDATE_FSTMST();
            if (pgmRtn) return;
            FSRMST.XH_BAL = FSRMST.XH_BAL + FSCFTRAN.TRANS_ATM;
            if (FSRMST.SS_BAL >= FSRMST.XH_BAL) {
                FSRMST.LIMIT_USED = 0;
            } else {
                FSRMST.LIMIT_USED = FSRMST.XH_BAL - FSRMST.SS_BAL;
            }
            T000_REWRITE_FSTMST();
            if (pgmRtn) return;
        }
    }
    public void B060_WRITE_FSTTXD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSRTXD);
        FSRTXD.KEY.TRAN_DATE = FSCFTRAN.TRAN_DATE;
        FSRTXD.KEY.SERIAL_NO = SCCGWA.COMM_AREA.JRN_NO;
        FSRTXD.KEY.SUB_SERIAL_NO = 0;
        if (((WS_TRAN_TYPE.equalsIgnoreCase("00") 
                || WS_TRAN_TYPE.equalsIgnoreCase("20")) 
                || (WS_TRAN_TYPE.equalsIgnoreCase("10") 
                || WS_TRAN_TYPE.equalsIgnoreCase("30")))) {
            FSRTXD.PAY_ACCT = FSCFTRAN.PAY_ACCT;
            FSRTXD.RES_ACCT = FSCFTRAN.RES_ACCT;
            FSRTXD.LT_ATM = 0;
        } else if (((WS_TRAN_TYPE.equalsIgnoreCase("01") 
                || WS_TRAN_TYPE.equalsIgnoreCase("21")))) {
            FSRTXD.RES_ACCT = FSCFTRAN.PAY_ACCT;
            FSRTXD.PAY_ACCT = FSCFTRAN.RES_ACCT;
            CEP.TRC(SCCGWA, FSRMST.SS_BAL);
            CEP.TRC(SCCGWA, FSRMST.XH_BAL);
            CEP.TRC(SCCGWA, WS_OD_AMT);
            if (WS_OD_AMT < 0) {
                CEP.TRC(SCCGWA, "WS-OD-AMT < 0");
                FSRTXD.LT_ATM = WS_OD_AMT;
                FSRTXD.LT_ATM = WS_OD_AMT * -1;
            } else {
                FSRTXD.LT_ATM = 0;
            }
            CEP.TRC(SCCGWA, FSRTXD.LT_ATM);
        } else if (((WS_TRAN_TYPE.equalsIgnoreCase("11") 
                || WS_TRAN_TYPE.equalsIgnoreCase("31")))) {
            FSRTXD.RES_ACCT = FSCFTRAN.PAY_ACCT;
            FSRTXD.PAY_ACCT = FSCFTRAN.RES_ACCT;
            FSRTXD.LT_ATM = 0;
        }
        FSRTXD.CCY = FSCFTRAN.CCY;
        FSRTXD.CCY_TYP = FSCFTRAN.CCY_TYP;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            FSRTXD.TRIG_TYP = '1';
        } else {
            FSRTXD.TRIG_TYP = '2';
        }
        FSRTXD.TYPE = FSCFTRAN.TYPE;
        FSRTXD.TRANS_ATM = FSCFTRAN.TRANS_ATM;
        FSRTXD.TRAN_TIME = FSCFTRAN.TRAN_TIME;
        FSRTXD.ZY_CODE = FSCFTRAN.ZY_CODE;
        FSRTXD.REQFM_NO = " ";
        FSRTXD.EC_FLG = ' ';
        FSRTXD.REMARK = FSCFTRAN.REMARK;
        FSRTXD.USE = FSCFTRAN.USER;
        FSRTXD.OPP_ACCT = " ";
        FSRTXD.OPP_NME = " ";
        FSRTXD.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRTXD.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRTXD.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_FSTTXD();
        if (pgmRtn) return;
    }
    public void B070_SET_RES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCOTRAN);
        FSCOTRAN.SERIAL_NO = SCCGWA.COMM_AREA.JRN_NO;
        FSCOTRAN.PAY_ACCT = FSCFTRAN.PAY_ACCT;
        FSCOTRAN.PAY_ACCT_NAME = FSCFTRAN.PAY_ACCT_NAME;
        FSCOTRAN.CCY = FSCFTRAN.CCY;
        FSCOTRAN.CCY_TYP = FSCFTRAN.CCY_TYP;
        FSCOTRAN.RES_ACCT = FSCFTRAN.RES_ACCT;
        FSCOTRAN.RES_ACCT_NAME = FSCFTRAN.RES_ACCT_NAME;
        FSCOTRAN.TYPE = FSCFTRAN.TYPE;
        FSCOTRAN.TRANS_ATM = FSCFTRAN.TRANS_ATM;
        FSCOTRAN.TRAN_DATE = FSCFTRAN.TRAN_DATE;
        FSCOTRAN.TRAN_TIME = FSCFTRAN.TRAN_TIME;
        FSCOTRAN.ZY_CODE = FSCFTRAN.ZY_CODE;
        FSCOTRAN.USER = FSCFTRAN.USER;
        FSCOTRAN.REMARK = FSCFTRAN.REMARK;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = FSCOTRAN;
        SCCFMT.DATA_LEN = 782;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_CHECK_UPACCT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQBAL);
        DDCIQBAL.DATA.AC = FSCFTRAN.RES_ACCT;
        DDCIQBAL.DATA.CCY = FSCFTRAN.CCY;
        DDCIQBAL.DATA.CCY_TYPE = FSCFTRAN.CCY_TYP;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.AC);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_TYPE);
        S000_CALL_DDZIQBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CURR_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.HOLD_BAL);
        CEP.TRC(SCCGWA, DDCIQBAL.DATA.CCY_STS_WORD);
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD == null) DDCIQBAL.DATA.CCY_STS_WORD = "";
        JIBS_tmp_int = DDCIQBAL.DATA.CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCIQBAL.DATA.CCY_STS_WORD += " ";
        if (DDCIQBAL.DATA.CCY_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
            || DDCIQBAL.DATA.CCY_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_AC_FR_NOT_ALL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE FSTMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_FSTMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, FSRMST.KEY.ACC_NO);
        FSTMST_RD = new DBParm();
        FSTMST_RD.TableName = "FSTMST";
        FSTMST_RD.upd = true;
        IBS.READ(SCCGWA, FSRMST, FSTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_LPAY_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE FSTMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_FSTMST() throws IOException,SQLException,Exception {
        FSRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        FSRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        FSRMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        FSTMST_RD = new DBParm();
        FSTMST_RD.TableName = "FSTMST";
        IBS.REWRITE(SCCGWA, FSRMST, FSTMST_RD);
    }
    public void T000_WRITE_FSTTXD() throws IOException,SQLException,Exception {
        FSTTXD_RD = new DBParm();
        FSTTXD_RD.TableName = "FSTTXD";
        FSTTXD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, FSRTXD, FSTTXD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_MSG_ID = DDCMSG_ERROR_MSG.DD_MST1_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "WRITE TABLE FSTTXD ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "FSTTXD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_DDZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-CCY-BAL", DDCIQBAL);
        if (DDCIQBAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, DDCIQBAL.RC);
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, DDCIQBAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZRDAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-ROUND-AMT", BPCRDAMT);
        if (BPCRDAMT.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCRDAMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID, WS_ERR_INFO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
