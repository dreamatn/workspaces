package com.hisun.FS;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCOCLWD;
import com.hisun.DD.DDCMSG_ERROR_MSG;
import com.hisun.DD.DDRCCY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class FSZBTRAN {
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_MSG_ID = " ";
    String WS_ERR_INFO = " ";
    double WS_AVL_AMT = 0;
    double WS_OD_AMT = 0;
    String WS_UP_AC_TMP = " ";
    char WS_TBL_FLAG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    FSCMSG_ERROR_MSG FSCMSG_ERROR_MSG = new FSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    FSCFTRAN FSCFTRAN = new FSCFTRAN();
    FSRMST FSRMST = new FSRMST();
    DDRCCY DDRCCY = new DDRCCY();
    FSRTXD FSRTXD = new FSRTXD();
    SCCGWA SCCGWA;
    FSCBTRAN FSCBTRAN;
    public void MP(SCCGWA SCCGWA, FSCBTRAN FSCBTRAN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.FSCBTRAN = FSCBTRAN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FSZBTRAN return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_INFO();
        if (pgmRtn) return;
        B020_TRAN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_INFO() throws IOException,SQLException,Exception {
        if (FSCBTRAN.INFO.SYS_JRN.trim().length() == 0 
            || FSCBTRAN.INFO.SYS_JRN.charAt(0) == 0X00) {
            WS_MSG_ID = FSCMSG_ERROR_MSG.FS_SYS_JRN_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (FSCBTRAN.INFO.TX_TYPE != '0' 
            && FSCBTRAN.INFO.TX_TYPE != '1' 
            && FSCBTRAN.INFO.TX_TYPE != '2' 
            && FSCBTRAN.INFO.TX_TYPE != '3' 
            && FSCBTRAN.INFO.TX_TYPE != '4') {
            WS_MSG_ID = FSCMSG_ERROR_MSG.FS_TX_TYP_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (FSCBTRAN.INFO.AC.trim().length() == 0) {
            WS_MSG_ID = FSCMSG_ERROR_MSG.FS_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (FSCBTRAN.INFO.UP_AC.trim().length() == 0) {
            WS_MSG_ID = FSCMSG_ERROR_MSG.FS_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!FSCBTRAN.INFO.WTDR_TYP.equalsIgnoreCase("01") 
            && !FSCBTRAN.INFO.WTDR_TYP.equalsIgnoreCase("02") 
            && !FSCBTRAN.INFO.WTDR_TYP.equalsIgnoreCase("03") 
            && !FSCBTRAN.INFO.WTDR_TYP.equalsIgnoreCase("04")) {
            WS_MSG_ID = FSCMSG_ERROR_MSG.FS_WTDR_T_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (FSCBTRAN.INFO.WTDR_MTH != '0' 
            && FSCBTRAN.INFO.WTDR_MTH != '1') {
            WS_MSG_ID = FSCMSG_ERROR_MSG.FS_WTDR_M_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (FSCBTRAN.INFO.WTDR_MTH == '1' 
            && FSCBTRAN.INFO.TX_AMT > 100) {
            WS_MSG_ID = FSCMSG_ERROR_MSG.FS_WTDR_M_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (FSCBTRAN.INFO.CCY.trim().length() == 0) {
            WS_MSG_ID = FSCMSG_ERROR_MSG.FS_CCY_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (FSCBTRAN.INFO.ALL_FLG != '0' 
            && FSCBTRAN.INFO.ALL_FLG != '1') {
            WS_MSG_ID = FSCMSG_ERROR_MSG.FS_ALL_F_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (FSCBTRAN.INFO.CANCEL_FLG != '0' 
            && FSCBTRAN.INFO.CANCEL_FLG != '1') {
            WS_MSG_ID = FSCMSG_ERROR_MSG.FS_CAN_FLG_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_TRAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, FSCFTRAN);
        FSCFTRAN.PAY_ACCT = FSCBTRAN.INFO.AC;
        FSCFTRAN.RES_ACCT = FSCBTRAN.INFO.UP_AC;
        FSCFTRAN.ZY_CODE = FSCBTRAN.INFO.MMO;
        if (FSCBTRAN.INFO.CANCEL_FLG == '1' 
            && FSCBTRAN.INFO.JRNNO != 0) {
            IBS.init(SCCGWA, FSRTXD);
            FSRTXD.KEY.SERIAL_NO = FSCBTRAN.INFO.JRNNO;
            FSRTXD.KEY.TRAN_DATE = FSCBTRAN.TR_DT;
            T000_READ_FSTTXD();
            if (pgmRtn) return;
            if (FSCBTRAN.INFO.WTDR_TYP.equalsIgnoreCase("01") 
                || FSCBTRAN.INFO.WTDR_TYP.equalsIgnoreCase("02")) {
                if ((!FSRTXD.PAY_ACCT.equalsIgnoreCase(FSCBTRAN.INFO.AC)) 
                    || (!FSRTXD.RES_ACCT.equalsIgnoreCase(FSCBTRAN.INFO.UP_AC))) {
                    CEP.ERR(SCCGWA, FSCMSG_ERROR_MSG.FS_CAN_AC_NOT_SAME);
                }
            } else {
                if ((!FSRTXD.PAY_ACCT.equalsIgnoreCase(FSCBTRAN.INFO.UP_AC)) 
                    || (!FSRTXD.RES_ACCT.equalsIgnoreCase(FSCBTRAN.INFO.AC))) {
                    CEP.ERR(SCCGWA, FSCMSG_ERROR_MSG.FS_CAN_AC_NOT_SAME);
                }
            }
