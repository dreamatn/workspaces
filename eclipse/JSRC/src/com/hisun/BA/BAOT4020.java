package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT4020 {
    boolean pgmRtn = false;
    String K_PAY_CHNL_PAYMENT = "1";
    char K_BILL_TYP_E = 'E';
    char K_BLK_STS_BLOCK = '0';
    char K_BILL_STS_WAIT_PAY = '3';
    char K_AMT_STS_WAIT_PAY = '1';
    char K_SETL_MTH_ONLINE = 'N';
    char K_SETL_MTH_OFFLINE = 'F';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_ERR_FLG = ' ';
    BAOT4020_WS_OUT_DATA WS_OUT_DATA = new BAOT4020_WS_OUT_DATA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BACUPAYT BACUPAYT = new BACUPAYT();
    SCCGWA SCCGWA;
    BAB4020_AWA_4020 BAB4020_AWA_4020;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAOT4020 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB4020_AWA_4020>");
        BAB4020_AWA_4020 = (BAB4020_AWA_4020) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_TRANCHE_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BAB4020_AWA_4020.ID_NO);
        CEP.TRC(SCCGWA, BAB4020_AWA_4020.SETL_MTH);
        if (BAB4020_AWA_4020.ID_NO.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ID_NO_M_INPUT;
            if (BAB4020_AWA_4020.ID_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BAB4020_AWA_4020.ID_NO);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB4020_AWA_4020.SETL_MTH == ' ') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_SETL_MTH_M_INPUT;
            if (BAB4020_AWA_4020.SETL_MTH == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+BAB4020_AWA_4020.SETL_MTH);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB4020_AWA_4020.SETL_MTH != K_SETL_MTH_ONLINE 
            && BAB4020_AWA_4020.SETL_MTH != K_SETL_MTH_OFFLINE) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_SETL_MTH_M_INPUT;
            if (BAB4020_AWA_4020.SETL_MTH == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+BAB4020_AWA_4020.SETL_MTH);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (WS_ERR_FLG == 'Y') {
            S000_ERR_MSG_PROC_LAST();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_TRANCHE_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUPAYT);
        BACUPAYT.DATA.ID_NO = BAB4020_AWA_4020.ID_NO;
        BACUPAYT.DATA.SETL_MTH = BAB4020_AWA_4020.SETL_MTH;
        S000_CALL_BAZUPAYT();
        if (pgmRtn) return;
    }
    public void S000_CALL_BAZUPAYT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-PAYT-SENT", BACUPAYT);
        if (BACUPAYT.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUPAYT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        WS_ERR_FLG = 'Y';
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
