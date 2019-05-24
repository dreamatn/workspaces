package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2030 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BAC01";
    String K_HIS_REMARKS = "ACCRUAL DRAWDOWN CONTRACT DRAWDOWN";
    BAOT2030_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BAOT2030_WS_TEMP_VARIABLE();
    BAOT2030_WS_OUT_DATA WS_OUT_DATA = new BAOT2030_WS_OUT_DATA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BACUBINF BACUBINF = new BACUBINF();
    BACUCHGE BACUCHGE = new BACUCHGE();
    SCCGWA SCCGWA;
    BAB2030_AWA_2030 BAB2030_AWA_2030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    public void MP(SCCGWA SCCGWA, BAB2030_AWA_2030 BAB2030_AWA_2030) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BAB2030_AWA_2030 = BAB2030_AWA_2030;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAOT2030 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2030_AWA_2030>");
        BAB2030_AWA_2030 = (BAB2030_AWA_2030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        CEP.TRC(SCCGWA, BAB2030_AWA_2030);
        CEP.TRC(SCCGWA, BAB2030_AWA_2030.XZK_FLG);
        CEP.TRC(SCCGWA, BAB2030_AWA_2030.ID_NO_O);
        CEP.TRC(SCCGWA, BAB2030_AWA_2030.ID_NO_N);
        CEP.TRC(SCCGWA, BAB2030_AWA_2030.HP_BRK);
        CEP.TRC(SCCGWA, BAB2030_AWA_2030.ENCR_FLG);
        CEP.TRC(SCCGWA, BAB2030_AWA_2030.ENCR);
        CEP.TRC(SCCGWA, BAB2030_AWA_2030.LP_BR);
        CEP.TRC(SCCGWA, BAB2030_AWA_2030.LP_GY);
        CEP.TRC(SCCGWA, BAB2030_AWA_2030.ID_TYP);
        CEP.TRC(SCCGWA, BAB2030_AWA_2030.FEE_FLG);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
        B300_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BAB2030_AWA_2030.XZK_FLG == ' ') {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_MUST_I_XZK_FLG;
            WS_TEMP_VARIABLE.WS_FLD_NO = BAB2030_AWA_2030.XZK_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2030_AWA_2030.ID_NO_O.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_MUST_I_ID_NO_O;
            WS_TEMP_VARIABLE.WS_FLD_NO = BAB2030_AWA_2030.ID_NO_O_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2030_AWA_2030.ID_NO_N.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_MUST_I_ID_NO_N;
            WS_TEMP_VARIABLE.WS_FLD_NO = BAB2030_AWA_2030.ID_NO_N_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2030_AWA_2030.HP_BRK.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_MUST_I_HP_BRK;
            WS_TEMP_VARIABLE.WS_FLD_NO = BAB2030_AWA_2030.HP_BRK_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2030_AWA_2030.ENCR_FLG == ' ') {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_MUST_I_ENCR_FLG;
            WS_TEMP_VARIABLE.WS_FLD_NO = BAB2030_AWA_2030.ENCR_FLG_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (BAB2030_AWA_2030.ID_TYP.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_MUST_I_ID_TYP;
            WS_TEMP_VARIABLE.WS_FLD_NO = BAB2030_AWA_2030.ID_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "11111");
        if (BAB2030_AWA_2030.XZK_FLG == 'Y') {
            CEP.TRC(SCCGWA, "33333");
            if (BAB2030_AWA_2030.LP_BR == 0) {
                WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_MUST_I_LP_BR;
                WS_TEMP_VARIABLE.WS_FLD_NO = BAB2030_AWA_2030.LP_BR_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, "22222");
        if (BAB2030_AWA_2030.XZK_FLG == 'Y') {
            CEP.TRC(SCCGWA, "44444");
            if (BAB2030_AWA_2030.LP_GY.trim().length() == 0) {
                WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_MUST_I_LP_GY;
                WS_TEMP_VARIABLE.WS_FLD_NO = BAB2030_AWA_2030.LP_GY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (BAB2030_AWA_2030.ID_NO_O.trim().length() > 0 
            && BAB2030_AWA_2030.ID_NO_N.trim().length() > 0) {
            if (BAB2030_AWA_2030.ID_NO_O.equalsIgnoreCase(BAB2030_AWA_2030.ID_NO_N)) {
                WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ID_NO_IS_SAME;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (BAB2030_AWA_2030.ENCR_FLG == 'Y') {
            if (BAB2030_AWA_2030.ENCR.trim().length() == 0) {
                WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ENCR_M_INPUT;
                WS_TEMP_VARIABLE.WS_FLD_NO = BAB2030_AWA_2030.ENCR_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_TEMP_VARIABLE.WS_ERR_FLG == 'Y') {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BACMSG_ERROR_MSG.BA_INPUT_ERR;
            S000_ERR_MSG_PROC_LAST();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACUCHGE);
        BACUCHGE.COMM_DATA.XZK_FLG = BAB2030_AWA_2030.XZK_FLG;
        BACUCHGE.COMM_DATA.ID_NO_O = BAB2030_AWA_2030.ID_NO_O;
        BACUCHGE.COMM_DATA.ID_NO_N = BAB2030_AWA_2030.ID_NO_N;
        BACUCHGE.COMM_DATA.HP_BRK = BAB2030_AWA_2030.HP_BRK;
        BACUCHGE.COMM_DATA.ENCR_FLG = BAB2030_AWA_2030.ENCR_FLG;
        BACUCHGE.COMM_DATA.ENCR = BAB2030_AWA_2030.ENCR;
        BACUCHGE.COMM_DATA.LP_BR = BAB2030_AWA_2030.LP_BR;
        BACUCHGE.COMM_DATA.LP_GY = BAB2030_AWA_2030.LP_GY;
        BACUCHGE.COMM_DATA.ID_TYP = BAB2030_AWA_2030.ID_TYP;
        BACUCHGE.COMM_DATA.FEE_FLG = BAB2030_AWA_2030.FEE_FLG;
        S000_CALL_BAZUCHGE();
        if (pgmRtn) return;
    }
    public void B300_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        B310_TRANS_DATA_TO_OUT_FMT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 16;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B310_TRANS_DATA_TO_OUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_DATA);
        WS_OUT_DATA.WS_OUT_ENCR = BACUCHGE.COMM_DATA.ENCR;
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_OUT_ENCR);
    }
    public void S000_CALL_BAZUBINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-INQ-BILL-INF", BACUBINF);
        if (BACUBINF.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUBINF.RC);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZUCHGE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-ACCP-CHGE", BACUCHGE);
        if (BACUCHGE.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACUCHGE.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG, WS_TEMP_VARIABLE.WS_FLD_NO);
        WS_TEMP_VARIABLE.WS_ERR_FLG = 'Y';
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
