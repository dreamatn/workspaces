package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2150 {
    boolean pgmRtn = false;
    String K_PAY_CHNL_PAYMENT = "1";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_ERR_FLG = ' ';
    BAOT2150_WS_OUT_DATA WS_OUT_DATA = new BAOT2150_WS_OUT_DATA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BACUPAYT BACUPAYT = new BACUPAYT();
    SCCGWA SCCGWA;
    BAB2150_AWA_2150 BAB2150_AWA_2150;
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
        CEP.TRC(SCCGWA, "BAOT2150 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2150_AWA_2150>");
        BAB2150_AWA_2150 = (BAB2150_AWA_2150) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        CEP.TRC(SCCGWA, BAB2150_AWA_2150.ID_NO);
        CEP.TRC(SCCGWA, BAB2150_AWA_2150.PAY_CHNL);
        CEP.TRC(SCCGWA, BAB2150_AWA_2150.FH_AC);
        CEP.TRC(SCCGWA, BAB2150_AWA_2150.FH_NM);
        if (BAB2150_AWA_2150.ID_NO.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ID_NO_M_INPUT;
            WS_FLD_NO = BAB2150_AWA_2150.ID_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2150_AWA_2150.PAY_CHNL == ' ') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_PAY_CHNL_M_IN;
            if (BAB2150_AWA_2150.PAY_CHNL == ' ') WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(""+BAB2150_AWA_2150.PAY_CHNL);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2150_AWA_2150.PAY_CHNL == K_PAY_CHNL_PAYMENT) {
            if (BAB2150_AWA_2150.FH_AC.trim().length() == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_PYE_AC_M_IN;
                if (BAB2150_AWA_2150.FH_AC.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(BAB2150_AWA_2150.FH_AC);
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (BAB2150_AWA_2150.FH_NM.trim().length() == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_PYE_NM_M_IN;
                if (BAB2150_AWA_2150.FH_NM.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(BAB2150_AWA_2150.FH_NM);
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_ERR_FLG == 'Y') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC_LAST();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_TRANCHE_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUPAYT);
        BACUPAYT.DATA.ID_NO = BAB2150_AWA_2150.ID_NO;
        BACUPAYT.DATA.PAY_CHNL = BAB2150_AWA_2150.PAY_CHNL;
        BACUPAYT.DATA.FH_AC = BAB2150_AWA_2150.FH_AC;
        BACUPAYT.DATA.FH_NM = BAB2150_AWA_2150.FH_NM;
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
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
