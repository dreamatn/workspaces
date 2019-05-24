package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2050 {
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BAN01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_CNTR_NO = " ";
    short WS_ACCT_CNT = 0;
    char WS_ERR_FLG = ' ';
    BAOT2050_WS_OUT_DATA WS_OUT_DATA = new BAOT2050_WS_OUT_DATA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BARLOSS BARLOSS = new BARLOSS();
    BACUBINF BACUBINF = new BACUBINF();
    BACFLOSS BACFLOSS = new BACFLOSS();
    BACULOSS BACULOSS = new BACULOSS();
    SCCGWA SCCGWA;
    BAB2050_AWA_2050 BAB2050_AWA_2050;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAOT2050 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2050_AWA_2050>");
        BAB2050_AWA_2050 = (BAB2050_AWA_2050) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_TRANCHE_MAIN_PROC();
        if (pgmRtn) return;
        B030_TRAN_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BAB2050_AWA_2050);
        CEP.TRC(SCCGWA, BAB2050_AWA_2050.ID_NO);
        CEP.TRC(SCCGWA, BAB2050_AWA_2050.FUN_COD);
        CEP.TRC(SCCGWA, BAB2050_AWA_2050.GSZF_NM);
        CEP.TRC(SCCGWA, BAB2050_AWA_2050.GSZF_ADD);
        CEP.TRC(SCCGWA, BAB2050_AWA_2050.GSZF_TEL);
        CEP.TRC(SCCGWA, BAB2050_AWA_2050.GSZF_NO);
        CEP.TRC(SCCGWA, BAB2050_AWA_2050.GSZF_BN);
        CEP.TRC(SCCGWA, BAB2050_AWA_2050.GS_BRK);
        CEP.TRC(SCCGWA, BAB2050_AWA_2050.SPPM_FLG);
        if (BAB2050_AWA_2050.ID_NO.trim().length() == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ID_NO_M_INPUT;
            WS_FLD_NO = BAB2050_AWA_2050.ID_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2050_AWA_2050.FUN_COD == ' ') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_FUNC_M_INPUT;
            WS_FLD_NO = BAB2050_AWA_2050.FUN_COD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2050_AWA_2050.GS_EN_DT == 0) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_GS_EN_DT_M_INPUT;
            WS_FLD_NO = BAB2050_AWA_2050.GS_EN_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            if (BAB2050_AWA_2050.GS_EN_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_GS_EN_DT_WRONG;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (BAB2050_AWA_2050.SPPM_FLG == ' ') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_SPPM_FLG_M_INPU;
            WS_FLD_NO = BAB2050_AWA_2050.SPPM_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (WS_ERR_FLG == 'Y') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_INPUT_ERR;
            S000_ERR_MSG_PROC_LAST();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_TRANCHE_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACULOSS);
        BACULOSS.DATA.TRAN_FLG = '0';
        BACULOSS.DATA.ID_NO = BAB2050_AWA_2050.ID_NO;
        BACULOSS.DATA.FUN_COD = BAB2050_AWA_2050.FUN_COD;
        BACULOSS.DATA.GSZF_NM = BAB2050_AWA_2050.GSZF_NM;
        BACULOSS.DATA.GSZF_ADD = BAB2050_AWA_2050.GSZF_ADD;
        BACULOSS.DATA.GSZF_TEL = BAB2050_AWA_2050.GSZF_TEL;
        BACULOSS.DATA.GSZF_NO = BAB2050_AWA_2050.GSZF_NO;
        BACULOSS.DATA.GSZF_BN = BAB2050_AWA_2050.GSZF_BN;
        BACULOSS.DATA.GS_EN_DT = BAB2050_AWA_2050.GS_EN_DT;
        BACULOSS.DATA.GS_BRK = BAB2050_AWA_2050.GS_BRK;
        BACULOSS.DATA.RMK = BAB2050_AWA_2050.RMK;
        BACULOSS.DATA.SPPM_FLG = BAB2050_AWA_2050.SPPM_FLG;
        S000_CALL_BAZULOSS();
        if (pgmRtn) return;
    }
    public void B030_TRAN_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_OUT_GS_ST_DT = SCCGWA.COMM_AREA.AC_DATE;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 8;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BAZUBINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-INQ-BILL-INF", BACUBINF);
        if (BACUBINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUBINF.RC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZULOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-LOSS-ULOS", BACULOSS);
        if (BACULOSS.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = "" + BACULOSS.RC.RC_RTNCODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            SCCMSG.INFO = "CALL-BAZSPLOS ERROR";
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFLOSS() throws IOException,SQLException,Exception {
        BACFLOSS.REC_PTR = BARLOSS;
        BACFLOSS.REC_LEN = 898;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFLOSS", BACFLOSS);
        if (BACFLOSS.RETURN_INFO != 'F') {
            if (BACFLOSS.RETURN_INFO != 'N') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFLOSS.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        WS_ERR_FLG = 'Y';
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
