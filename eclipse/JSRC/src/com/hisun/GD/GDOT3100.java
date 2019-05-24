package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT3100 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    char WS_TXBUR_MT = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSPNPR GDCSPNPR = new GDCSPNPR();
    SCCGWA SCCGWA;
    GDB3100_AWA_3100 GDB3100_AWA_3100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "GDOT3100 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB3100_AWA_3100>");
        GDB3100_AWA_3100 = (GDB3100_AWA_3100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXDD_AC);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXCI_NM);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXSEQ);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXCCY);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXTYP);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXRSEQ);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXCTA_NO);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXREF_NO);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXBSTYP);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXRAMT);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXDT);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXRMK);
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB3100_AWA_3100);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXDD_AC);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXSEQ);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXCTA_NO);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXBSTYP);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXRAMT);
        CEP.TRC(SCCGWA, GDB3100_AWA_3100.TXDT);
        if (GDB3100_AWA_3100.TXDD_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB3100_AWA_3100.TXCTA_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_M_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB3100_AWA_3100.TXBSTYP.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYPE_M_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB3100_AWA_3100.TXDT == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_EXP_DT_M_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB3100_AWA_3100.TXDT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_EXPDT_M_BIGR_TODAY;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB3100_AWA_3100.TXBUR_MT == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUR_MTH_M_INPUT;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        WS_TXBUR_MT = GDB3100_AWA_3100.TXBUR_MT;
        if (WS_TXBUR_MT == '1') {
            if (GDB3100_AWA_3100.TXCTA_NO.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_M_INPUT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        } else if (WS_TXBUR_MT == '2') {
            if (GDB3100_AWA_3100.TXREF_NO.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_RSEQ_M_INPUT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        } else {
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSPNPR);
        GDCSPNPR.KEY.AC = GDB3100_AWA_3100.TXDD_AC;
        if (GDB3100_AWA_3100.TXSEQ == 0) {
            GDCSPNPR.VAL.AC_TYP = '0';
        } else {
            GDCSPNPR.VAL.AC_TYP = '1';
        }
        GDCSPNPR.VAL.CI_NM = GDB3100_AWA_3100.TXCI_NM;
        GDCSPNPR.KEY.AC_SEQ = GDB3100_AWA_3100.TXSEQ;
        GDCSPNPR.VAL.CCY = GDB3100_AWA_3100.TXCCY;
        GDCSPNPR.VAL.CCY_TYP = GDB3100_AWA_3100.TXTYP;
        GDCSPNPR.VAL.SYS_NO = GDB3100_AWA_3100.TXSYS_NO;
        GDCSPNPR.KEY.RSEQ = GDB3100_AWA_3100.TXRSEQ;
        GDCSPNPR.VAL.CTA_NO = GDB3100_AWA_3100.TXCTA_NO;
        GDCSPNPR.VAL.REF_NO = GDB3100_AWA_3100.TXREF_NO;
        GDCSPNPR.VAL.BUSI_TYP = GDB3100_AWA_3100.TXBSTYP;
        GDCSPNPR.VAL.RL_AMT = GDB3100_AWA_3100.TXRAMT;
        GDCSPNPR.VAL.PNAMT = GDB3100_AWA_3100.TXPN_AMT;
        GDCSPNPR.VAL.EXP_DT = GDB3100_AWA_3100.TXDT;
        GDCSPNPR.VAL.ALLO_FLG = GDB3100_AWA_3100.TXALLO_F;
        GDCSPNPR.VAL.ALLO_B = GDB3100_AWA_3100.TXALLO_B;
        GDCSPNPR.VAL.BR_TYP = GDB3100_AWA_3100.TXBR_TYP;
        GDCSPNPR.VAL.FEE_CD = GDB3100_AWA_3100.TXFEE_CD;
        GDCSPNPR.VAL.FAC_NO = GDB3100_AWA_3100.TXFAC_NO;
        GDCSPNPR.VAL.FEE_AMT = GDB3100_AWA_3100.TXFEE_AM;
        GDCSPNPR.VAL.RMK = GDB3100_AWA_3100.TXRMK;
        GDCSPNPR.VAL.HLD_NO = GDB3100_AWA_3100.TXHLD_NO;
        S000_CALL_GDZSPNPR();
    }
    public void S000_CALL_GDZSPNPR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSPNPR", GDCSPNPR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
