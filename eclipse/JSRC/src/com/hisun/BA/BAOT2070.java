package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BAOT2070 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BAT01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_ERR_FLG = ' ';
    BAOT2070_WS_OUT_DATA WS_OUT_DATA = new BAOT2070_WS_OUT_DATA();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCFMT SCCFMT = new SCCFMT();
    BACURECV BACURECV = new BACURECV();
    SCCGWA SCCGWA;
    BAB2070_AWA_2070 BAB2070_AWA_2070;
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
        CEP.TRC(SCCGWA, "BAOT2070 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BAB2070_AWA_2070>");
        BAB2070_AWA_2070 = (BAB2070_AWA_2070) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.FUN_COD);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.ID_NO);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.DQ_DT);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.CD_AMT);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.ENCR);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.C_BR);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.C_REC_DT);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.C_PYE_DT);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.C_PYE_AC);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.C_PYE_NM);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.C_PYE_BR);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.C_PYE_BN);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.SPPM_FLG);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.PAY_MTH);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.PAY_CHNL);
        CEP.TRC(SCCGWA, BAB2070_AWA_2070.REMK);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
        B300_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BAB2070_AWA_2070.FUN_COD == '0') {
            if (BAB2070_AWA_2070.DQ_DT == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_DQ_DT_M_INPUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (BAB2070_AWA_2070.FUN_COD == '0') {
            if (BAB2070_AWA_2070.CD_AMT == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_CD_AMT_M_INPUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (BAB2070_AWA_2070.FUN_COD == '0') {
            if (BAB2070_AWA_2070.ENCR.trim().length() == 0) {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_ENCR_M_INPUT;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (BAB2070_AWA_2070.FUN_COD == '0') {
            if (BAB2070_AWA_2070.PAY_CHNL == ' ') {
                WS_ERR_MSG = BACMSG_ERROR_MSG.BA_ERR_PAY_CHNL_M_IN;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (WS_ERR_FLG == 'Y') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_INPUT_ERR;
            S000_ERR_MSG_PROC_LAST();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
        if (BAB2070_AWA_2070.C_REC_DT == 0) {
            BAB2070_AWA_2070.C_REC_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BACURECV);
        BACURECV.DATA.FUN_COD = BAB2070_AWA_2070.FUN_COD;
        BACURECV.DATA.ID_NO = BAB2070_AWA_2070.ID_NO;
        BACURECV.DATA.DQ_DT = BAB2070_AWA_2070.DQ_DT;
        BACURECV.DATA.CD_AMT = BAB2070_AWA_2070.CD_AMT;
        BACURECV.DATA.ENCR = BAB2070_AWA_2070.ENCR;
        BACURECV.DATA.C_BR = BAB2070_AWA_2070.C_BR;
        BACURECV.DATA.C_REC_DT = BAB2070_AWA_2070.C_REC_DT;
        BACURECV.DATA.C_PYE_DT = BAB2070_AWA_2070.C_PYE_DT;
        BACURECV.DATA.C_PYE_AC = BAB2070_AWA_2070.C_PYE_AC;
        BACURECV.DATA.C_PYE_NM = BAB2070_AWA_2070.C_PYE_NM;
        BACURECV.DATA.C_PYE_BR = BAB2070_AWA_2070.C_PYE_BR;
        BACURECV.DATA.C_PYE_BN = BAB2070_AWA_2070.C_PYE_BN;
        BACURECV.DATA.SPPM_FLG = BAB2070_AWA_2070.SPPM_FLG;
        BACURECV.DATA.PAY_MTH = BAB2070_AWA_2070.PAY_MTH;
        BACURECV.DATA.PAY_CHNL = BAB2070_AWA_2070.PAY_CHNL;
        BACURECV.DATA.REMK = BAB2070_AWA_2070.REMK;
        CEP.TRC(SCCGWA, BACURECV.DATA.ID_NO);
        S000_CALL_BAZURECV();
        if (pgmRtn) return;
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_OUT_DATA.WS_CLCT_STS = BACURECV.DATA.CLCT_STS;
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUT_DATA;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, WS_OUT_DATA.WS_CLCT_STS);
    }
    public void S000_CALL_BAZURECV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BA-U-CLCT-RECV", BACURECV);
        if (BACURECV.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACURECV.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
