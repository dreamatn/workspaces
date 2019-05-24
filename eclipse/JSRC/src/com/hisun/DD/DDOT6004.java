package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT6004 {
    boolean pgmRtn = false;
    String PGM_SCSSCLDT = "SCSSCLDT";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DDCSQREG DDCSQREG = new DDCSQREG();
    SCCGWA SCCGWA;
    DDB6004_AWA_6004 DDB6004_AWA_6004;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT6004 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB6004_AWA_6004>");
        DDB6004_AWA_6004 = (DDB6004_AWA_6004) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_TRANS_DATA_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDB6004_AWA_6004.FUNC == '1') {
            if (DDB6004_AWA_6004.AC.trim().length() == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
            }
            if (DDB6004_AWA_6004.END_DATE == 0) {
                DDB6004_AWA_6004.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
        }
        if (DDB6004_AWA_6004.FUNC == '2') {
            if (DDB6004_AWA_6004.END_DATE == 0) {
                DDB6004_AWA_6004.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
            }
            if (DDB6004_AWA_6004.STR_DATE == 0) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = DDB6004_AWA_6004.END_DATE;
                SCCCLDT.MTHS = -6;
                S000_CALL_SCSSCLDT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, SCCCLDT.DATE2);
                DDB6004_AWA_6004.STR_DATE = SCCCLDT.DATE2;
            }
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQREG);
        DDCSQREG.FUNC = DDB6004_AWA_6004.FUNC;
        DDCSQREG.AC = DDB6004_AWA_6004.AC;
        DDCSQREG.STR_DATE = DDB6004_AWA_6004.STR_DATE;
        DDCSQREG.END_DATE = DDB6004_AWA_6004.END_DATE;
        DDCSQREG.CI_TYP = DDB6004_AWA_6004.CI_TYP;
        DDCSQREG.STS = DDB6004_AWA_6004.STS;
        S000_CALL_DDZSQREG();
        if (pgmRtn) return;
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "LINK SCSSCLDT,CLDT-RC=" + SCCCLDT.RC;
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSQREG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-INQ-QREG", DDCSQREG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
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
