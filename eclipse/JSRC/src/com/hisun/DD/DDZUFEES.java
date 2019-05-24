package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUFEES {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    boolean pgmRtn = false;
    String WS_AC_NO = " ";
    short WS_I = 0;
    short WS_K = 0;
    char WS_TAG = ' ';
    char WS_SAVE_FLG = ' ';
    char WS_WAVE_FND_FLG = ' ';
    char WS_NOSIGN_FLG = ' ';
    char WS_AC_WAVE_FLG = ' ';
    int WS_OPEN_DP = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    DDRMST DDRMST = new DDRMST();
    CICQCIAC CICQCIAC = new CICQCIAC();
    BPCDCMZR BPCDCMZR = new BPCDCMZR();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    DDCUFEES DDCUFEES;
    public void MP(SCCGWA SCCGWA, DDCUFEES DDCUFEES) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUFEES = DDCUFEES;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUFEES return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DDCUFEES.FUNC == '1') {
            B020_TAG_FOR_WAVE();
            if (pgmRtn) return;
        } else if (DDCUFEES.FUNC == '2') {
            B030_REMOVE_TAG();
            if (pgmRtn) return;
            B040_WAVE_OTHER_AC();
            if (pgmRtn) return;
        } else if (DDCUFEES.FUNC == '3') {
            B040_WAVE_OTHER_AC();
            if (pgmRtn) return;
        } else if (DDCUFEES.FUNC == '4') {
            B040_WAVE_OTHER_AC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCUFEES.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUFEES.FUNC);
        CEP.TRC(SCCGWA, DDCUFEES.AC_NO);
        CEP.TRC(SCCGWA, DDCUFEES.CI_NO);
        if (DDCUFEES.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if ((DDCUFEES.FUNC == '2' 
            || DDCUFEES.FUNC == '3' 
            || DDCUFEES.FUNC == '4') 
            && DDCUFEES.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CI_NO_M_INPUT);
        }
    }
    public void B020_TAG_FOR_WAVE() throws IOException,SQLException,Exception {
        WS_TAG = '1';
        WS_AC_NO = DDCUFEES.AC_NO;
        R000_UPDATE_DDTMST();
        if (pgmRtn) return;
    }
    public void B030_REMOVE_TAG() throws IOException,SQLException,Exception {
        WS_TAG = '0';
        WS_AC_NO = DDCUFEES.AC_NO;
        R000_UPDATE_DDTMST();
        if (pgmRtn) return;
    }
    public void B040_WAVE_OTHER_AC() throws IOException,SQLException,Exception {
        R000_GET_AC_WAVE_FLG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQCIAC);
        WS_SAVE_FLG = 'N';
        WS_WAVE_FND_FLG = 'N';
        while (CICQCIAC.DATA.LAST_FLG != 'Y' 
            && WS_WAVE_FND_FLG != 'Y') {
            IBS.init(SCCGWA, CICQCIAC);
            CICQCIAC.FUNC = '6';
            CICQCIAC.DATA.CI_NO = DDCUFEES.CI_NO;
            CICQCIAC.DATA.OPEN_BR = WS_OPEN_DP;
            S000_CALL_CIZQCIAC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQCIAC.DATA.LAST_FLG);
            if (WS_SAVE_FLG == 'N') {
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO);
                WS_AC_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO;
                WS_SAVE_FLG = 'Y';
            }
            for (WS_I = 1; WS_I <= 100 
                && CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO.trim().length() != 0 
                && WS_WAVE_FND_FLG != 'Y'; WS_I += 1) {
                CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO);
                IBS.init(SCCGWA, DDRMST);
                DDRMST.KEY.CUS_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO;
                T000_READ_DDTMST();
                if (pgmRtn) return;
                if (DDRMST.AC_STS == 'C') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
                }
                if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
                JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
                if (DDRMST.AC_STS_WORD.substring(68 - 1, 68 + 1 - 1).equalsIgnoreCase("1") 
                    && !CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_I-1].ENTY_NO.equalsIgnoreCase(DDCUFEES.AC_NO)) {
                    WS_WAVE_FND_FLG = 'Y';
                }
            }
            if (WS_WAVE_FND_FLG == 'N') {
                for (WS_K = 1; WS_K <= 100 
                    && CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_K-1].ENTY_NO.trim().length() != 0 
                    && WS_NOSIGN_FLG != 'Y'; WS_K += 1) {
                    CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_K-1].ENTY_NO);
                    IBS.init(SCCGWA, BPCDCMZR);
                    BPCDCMZR.INPUT_DATA.CMZ_AC = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_K-1].ENTY_NO;
                    S000_CALL_BPZDCMZR();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCDCMZR.OUTPUT_DATA.OUT_FLG);
                    if (BPCDCMZR.OUTPUT_DATA.OUT_FLG == 'N') {
                        WS_NOSIGN_FLG = 'Y';
                        WS_AC_NO = CICQCIAC.DATA.ACR_AREA.ENTY_INF[WS_K-1].ENTY_NO;
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_WAVE_FND_FLG);
        CEP.TRC(SCCGWA, WS_AC_NO);
        if (WS_WAVE_FND_FLG == 'N' 
            && WS_AC_NO.trim().length() > 0) {
            WS_TAG = '1';
            R000_UPDATE_DDTMST();
            if (pgmRtn) return;
        }
    }
    public void R000_GET_AC_WAVE_FLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCUFEES.AC_NO;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDRMST.AC_STS_WORD.substring(68 - 1, 68 + 1 - 1));
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        if (DDRMST.AC_STS_WORD.substring(68 - 1, 68 + 1 - 1).equalsIgnoreCase("1")) {
            WS_AC_WAVE_FLG = 'Y';
        }
        WS_OPEN_DP = DDRMST.OPEN_DP;
        if (DDCUFEES.FUNC == '4' 
            && WS_AC_WAVE_FLG == 'Y') {
            IBS.init(SCCGWA, BPCDCMZR);
            BPCDCMZR.INPUT_DATA.CMZ_AC = DDCUFEES.AC_NO;
            S000_CALL_BPZDCMZR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCDCMZR.OUTPUT_DATA.OUT_FLG);
            if (BPCDCMZR.OUTPUT_DATA.OUT_FLG != 'A') {
                B030_REMOVE_TAG();
                if (pgmRtn) return;
            } else {
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_UPDATE_DDTMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = WS_AC_NO;
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        T000_READUPD_DDTMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMST.AC_STS);
        if (DDRMST.AC_STS == 'C') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CLOSE);
        }
        if (DDRMST.AC_STS_WORD == null) DDRMST.AC_STS_WORD = "";
        JIBS_tmp_int = DDRMST.AC_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRMST.AC_STS_WORD += " ";
        JIBS_tmp_str[0] = "" + WS_TAG;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        DDRMST.AC_STS_WORD = DDRMST.AC_STS_WORD.substring(0, 68 - 1) + JIBS_tmp_str[0] + DDRMST.AC_STS_WORD.substring(68 + 1 - 1);
        DDRMST.LAST_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTMST();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
        if (CICQCIAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQCIAC.RC);
        }
    }
    public void S000_CALL_BPZDCMZR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-D-MAINTAIN-CMZR", BPCDCMZR);
        if (BPCDCMZR.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCDCMZR.RC);
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND);
        }
    }
    public void T000_READUPD_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND);
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
