package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT6010 {
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_ERR_FLG = ' ';
    char WS_REC_FLG = ' ';
    BARLOGP BARLOGP = new BARLOGP();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BACURCPT BACURCPT = new BACURCPT();
    SCCGWA SCCGWA;
    BAB6010_AWA_6010 BAB6010_AWA_6010;
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
        CEP.TRC(SCCGWA, "BAOT6010 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB6010_AWA_6010>");
        BAB6010_AWA_6010 = (BAB6010_AWA_6010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_TRANS_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BAB6010_AWA_6010.O_SD_TAG);
        CEP.TRC(SCCGWA, BAB6010_AWA_6010.O_SD_DT);
        CEP.TRC(SCCGWA, BAB6010_AWA_6010.O_SD_FLW);
        CEP.TRC(SCCGWA, BAB6010_AWA_6010.PROC_RST);
        CEP.TRC(SCCGWA, BAB6010_AWA_6010.RTN_MSG);
        if (BAB6010_AWA_6010.O_SD_TAG.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ORG_SD_TG_M_IPT;
            if (BAB6010_AWA_6010.O_SD_TAG.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BAB6010_AWA_6010.O_SD_TAG);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB6010_AWA_6010.O_SD_DT == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ORG_SD_DT_M_IPT;
            WS_FLD_NO = (short) BAB6010_AWA_6010.O_SD_DT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB6010_AWA_6010.O_SD_FLW.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ORG_SD_FLW_M_IPT;
            if (BAB6010_AWA_6010.O_SD_FLW.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BAB6010_AWA_6010.O_SD_FLW);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB6010_AWA_6010.PROC_RST.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_PROC_RSLT_M_IPT;
            if (BAB6010_AWA_6010.PROC_RST.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(BAB6010_AWA_6010.PROC_RST);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            if (!BAB6010_AWA_6010.PROC_RST.equalsIgnoreCase("00") 
                && !BAB6010_AWA_6010.PROC_RST.equalsIgnoreCase("01")) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_PROC_RST_IPT_ERR;
                if (BAB6010_AWA_6010.PROC_RST.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(BAB6010_AWA_6010.PROC_RST);
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
    public void B020_TRANS_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACURCPT);
        BACURCPT.COMM_DATA.ORG_SND_TAG = BAB6010_AWA_6010.O_SD_TAG;
        BACURCPT.COMM_DATA.ORG_SND_DT = BAB6010_AWA_6010.O_SD_DT;
        BACURCPT.COMM_DATA.ORG_SND_FLW = BAB6010_AWA_6010.O_SD_FLW;
        BACURCPT.COMM_DATA.PROC_RSLT = BAB6010_AWA_6010.PROC_RST;
        BACURCPT.COMM_DATA.RTN_MSG = BAB6010_AWA_6010.RTN_MSG;
        S000_CALL_BAZURCPT();
        if (pgmRtn) return;
    }
    public void S000_CALL_BAZURCPT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-UNVSL-RCPT", BACURCPT);
        if (BACURCPT.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACURCPT.RC);
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
