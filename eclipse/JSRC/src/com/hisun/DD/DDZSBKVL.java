package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSBKVL {
    DBParm DDTBACK_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_END_DATE = 0;
    char WS_EOF_BALH_FLAG = ' ';
    char WS_BALH_FOUND_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DDRBACK DDRBACK = new DDRBACK();
    DDRBALH DDRBALH = new DDRBALH();
    DDRMST DDRMST = new DDRMST();
    DDCUBKVL DDCUBKVL = new DDCUBKVL();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDCIINTD DDCIINTD = new DDCIINTD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    DDCSBKVL DDCSBKVL;
    public void MP(SCCGWA SCCGWA, DDCSBKVL DDCSBKVL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSBKVL = DDCSBKVL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSBKVL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_ONL_ADJ_INT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBKVL.AC_NO);
        CEP.TRC(SCCGWA, DDCSBKVL.CCY);
        CEP.TRC(SCCGWA, DDCSBKVL.AC_DATE);
        CEP.TRC(SCCGWA, DDCSBKVL.VALUE_DATE);
        CEP.TRC(SCCGWA, DDCSBKVL.TR_JRN);
        if (DDCSBKVL.AC_NO.equalsIgnoreCase("0")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCSBKVL.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCSBKVL.VALUE_DATE >= DDCSBKVL.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VAL_DT_INVALID;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_ONL_ADJ_INT_PROC() throws IOException,SQLException,Exception {
        R000_GET_ACO_AC();
        if (pgmRtn) return;
        R000_READUPD_BACK_REC();
        if (pgmRtn) return;
        R000_ADJ_AC_INT_PROC();
        if (pgmRtn) return;
        R000_UPD_BACK_REC();
        if (pgmRtn) return;
    }
    public void R000_GET_ACO_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCSBKVL.AC_NO;
        CICQACAC.DATA.CCY_ACAC = DDCSBKVL.CCY;
        CICQACAC.DATA.CR_FLG = DDCSBKVL.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void R000_READUPD_BACK_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRBACK);
        DDRBACK.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRBACK.KEY.AC_DATE = DDCSBKVL.AC_DATE;
        DDRBACK.KEY.VALUE_DATE = DDCSBKVL.VALUE_DATE;
        DDRBACK.KEY.TR_JRN = DDCSBKVL.TR_JRN;
        DDTBACK_RD = new DBParm();
        DDTBACK_RD.TableName = "DDTBACK";
        DDTBACK_RD.upd = true;
        IBS.READ(SCCGWA, DDRBACK, DDTBACK_RD);
    }
    public void R000_UPD_BACK_REC() throws IOException,SQLException,Exception {
        DDRBACK.DO_FLAG = 'Y';
        DDTBACK_RD = new DBParm();
        DDTBACK_RD.TableName = "DDTBACK";
        IBS.REWRITE(SCCGWA, DDRBACK, DDTBACK_RD);
    }
    public void R000_ADJ_AC_INT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUBKVL);
        DDCUBKVL.AC_NO = DDCSBKVL.AC_NO;
        DDCUBKVL.CCY = DDCSBKVL.CCY;
        DDCUBKVL.CCY_TYPE = DDCSBKVL.CCY_TYPE;
        DDCUBKVL.AC_DATE = DDCSBKVL.AC_DATE;
        DDCUBKVL.VALUE_DATE = DDCSBKVL.VALUE_DATE;
        DDCUBKVL.TR_JRN = DDCSBKVL.TR_JRN;
        S000_CALL_DDZUBKVL();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZUBKVL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-BACK-VALUE", DDCUBKVL);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
