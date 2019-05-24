package com.hisun.PS;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class PSZSQRYP {
    boolean pgmRtn = false;
    String K_EXG_BK_NO = "001";
    String K_OUTPUT_FMT = "PS431";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    PSZSQRYP_WS_FMT WS_FMT = new PSZSQRYP_WS_FMT();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    PSROBLL PSROBLL = new PSROBLL();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSREINF PSREINF = new PSREINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCSQRYP PSCSQRYP;
    public void MP(SCCGWA SCCGWA, PSCSQRYP PSCSQRYP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCSQRYP = PSCSQRYP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSZSQRYP return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_READ_PSTOBLL();
        if (pgmRtn) return;
        B300_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (PSCSQRYP.EXG_AREA_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AREA_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCSQRYP.EXG_RT_DT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TCDT_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCSQRYP.EXG_RT_TMS == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TCTMS_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCSQRYP.TX_JRNNO == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TX_JRN_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_READ_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSROBLL);
        PSROBLL.KEY.EXG_BK_NO = PSCSQRYP.EXG_BK_NO;
        PSROBLL.KEY.EXG_AREA_NO = PSCSQRYP.EXG_AREA_NO;
        PSROBLL.KEY.EXG_DT = PSCSQRYP.EXG_RT_DT;
        PSROBLL.KEY.EXG_TMS = PSCSQRYP.EXG_RT_TMS;
        PSROBLL.KEY.EXG_JRN_NO = PSCSQRYP.TX_JRNNO;
        CEP.TRC(SCCGWA, PSCSQRYP.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSCSQRYP.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSCSQRYP.EXG_RT_DT);
        CEP.TRC(SCCGWA, PSCSQRYP.EXG_RT_TMS);
        CEP.TRC(SCCGWA, PSCSQRYP.TX_JRNNO);
        T000_READ_PSTOBLL();
        if (pgmRtn) return;
        if (PSROBLL.EXG_DC == 'C') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EXG_C_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_OUTPUT_PROC() throws IOException,SQLException,Exception {
        WS_FMT.WS_EXG_DT = PSROBLL.KEY.EXG_DT;
        WS_FMT.WS_EXG_TMS = PSROBLL.KEY.EXG_TMS;
        WS_FMT.WS_EXG_JRN_NO = PSROBLL.KEY.EXG_JRN_NO;
        WS_FMT.WS_OUR_EXG_NO = PSROBLL.OUR_EXG_NO;
        WS_FMT.WS_EXG_CCY = PSROBLL.EXG_CCY;
        WS_FMT.WS_OTH_EXG_NO = PSROBLL.OTH_EXG_NO;
        WS_FMT.WS_EXG_DC = PSROBLL.EXG_DC;
        WS_FMT.WS_EXG_VOUCH_CD = PSROBLL.EXG_VOUCH_CD;
        WS_FMT.WS_EXG_CERT_NO = PSROBLL.EXG_CERT_NO;
        WS_FMT.WS_EXG_REC_STS = PSROBLL.EXG_REC_STS;
        WS_FMT.WS_EXG_AMT = PSROBLL.EXG_AMT;
        WS_FMT.WS_EXG_TX_BR = PSROBLL.EXG_TX_BR;
        WS_FMT.WS_OUR_ACNO = PSROBLL.OUR_ACNO;
        WS_FMT.WS_OUR_ACNM = PSROBLL.OUR_ACNM;
        WS_FMT.WS_OTH_ACNO = PSROBLL.OTH_ACNO;
        WS_FMT.WS_OTH_ACNM = PSROBLL.OTH_ACNM;
        WS_FMT.WS_R_BILL_TYP = PSROBLL.R_BILL_TYP;
        WS_FMT.WS_CERT_DT = PSROBLL.CERT_DT;
        WS_FMT.WS_ISS_BKNO = PSROBLL.ISS_BKNO;
        WS_FMT.WS_EXP_DT = PSROBLL.EXP_DT;
        WS_FMT.WS_ISS_AMT = PSROBLL.ISS_AMT;
        WS_FMT.WS_ENCRY_NO = PSROBLL.ENCRY_NO;
        WS_FMT.WS_CT_FLG = PSROBLL.CT_FLG;
        WS_FMT.WS_CREV_NO = PSROBLL.CREV_NO;
        WS_FMT.WS_CASH_ID = PSROBLL.CASH_ID;
        WS_FMT.WS_SHL_EXG_DT = PSROBLL.SHL_EXG_DT;
        WS_FMT.WS_SHL_EXG_TMS = PSROBLL.SHL_EXG_TMS;
        WS_FMT.WS_ACT_EXG_DT = PSROBLL.ACT_EXG_DT;
        WS_FMT.WS_ACT_EXG_TMS = PSROBLL.ACT_EXG_TMS;
        WS_FMT.WS_EXG_REPT_FLG = PSROBLL.EXG_REPT_FLG;
        WS_FMT.WS_RT_CODE = PSROBLL.RT_CODE;
