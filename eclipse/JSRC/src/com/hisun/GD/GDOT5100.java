package com.hisun.GD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class GDOT5100 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    char WS_TXBUR_MT = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    GDCSMPRL GDCSMPRL = new GDCSMPRL();
    SCCGWA SCCGWA;
    GDB5100_AWA_5100 GDB5100_AWA_5100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "GDOT5100 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "GDB5100_AWA_5100>");
        GDB5100_AWA_5100 = (GDB5100_AWA_5100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXDD_AC);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXACTYP);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXCI_NM);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXSEQ);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXCCY);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXTYP);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXRSEQ);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXCTA_NO);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXREF_NO);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXBSTYP);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXRAMT);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXDT);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXRMK);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXHLD_NO);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXPN_AMT);
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, GDB5100_AWA_5100);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXDD_AC);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXACTYP);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXSEQ);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXCTA_NO);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXBSTYP);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXRAMT);
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXDT);
        if (GDB5100_AWA_5100.TXDD_AC.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_NO_M_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB5100_AWA_5100.TXACTYP == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_M_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB5100_AWA_5100.TXCTA_NO.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_M_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB5100_AWA_5100.TXBSTYP.trim().length() == 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUSI_TYPE_M_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB5100_AWA_5100.TXRAMT < 0) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AMT_INVALID;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB5100_AWA_5100.TXDT != 0 
            && GDB5100_AWA_5100.TXDT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_EXPDT_M_BIGR_TODAY;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        if (GDB5100_AWA_5100.TXBUR_MT == ' ') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_BUR_MTH_M_INPUT;
            CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
        }
        WS_TXBUR_MT = GDB5100_AWA_5100.TXBUR_MT;
        if (WS_TXBUR_MT == '1') {
            if (GDB5100_AWA_5100.TXCTA_NO.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_CTA_NO_M_INPUT;
                CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
            }
        } else if (WS_TXBUR_MT == '2') {
            if (GDB5100_AWA_5100.TXREF_NO.trim().length() == 0) {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.M_BUS_NO_MST_IPT;
                CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
            }
        } else {
        }
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSMPRL);
        GDCSMPRL.KEY.AC = GDB5100_AWA_5100.TXDD_AC;
        GDCSMPRL.VAL.AC_TYP = GDB5100_AWA_5100.TXACTYP;
        GDCSMPRL.VAL.CI_NM = GDB5100_AWA_5100.TXCI_NM;
        GDCSMPRL.KEY.AC_SEQ = GDB5100_AWA_5100.TXSEQ;
        GDCSMPRL.VAL.CCY = GDB5100_AWA_5100.TXCCY;
        GDCSMPRL.VAL.CCY_TYP = GDB5100_AWA_5100.TXTYP;
        GDCSMPRL.KEY.RSEQ = GDB5100_AWA_5100.TXRSEQ;
        GDCSMPRL.VAL.CTA_NO = GDB5100_AWA_5100.TXCTA_NO;
        GDCSMPRL.VAL.REF_NO = GDB5100_AWA_5100.TXREF_NO;
        GDCSMPRL.VAL.BUSI_TYP = GDB5100_AWA_5100.TXBSTYP;
        CEP.TRC(SCCGWA, "TST1");
        CEP.TRC(SCCGWA, GDCSMPRL.VAL.BUSI_TYP);
        GDCSMPRL.VAL.RL_AMT = GDB5100_AWA_5100.TXRAMT;
        GDCSMPRL.VAL.EXP_DT = GDB5100_AWA_5100.TXDT;
        GDCSMPRL.VAL.RMK = GDB5100_AWA_5100.TXRMK;
        GDCSMPRL.VAL.SYS_NO = GDB5100_AWA_5100.TXSYS_NO;
        GDCSMPRL.VAL.HLD_NO = GDB5100_AWA_5100.TXHLD_NO;
        GDCSMPRL.VAL.PN_AMT = GDB5100_AWA_5100.TXPN_AMT;
        GDCSMPRL.VAL.RELAT_WAY = GDB5100_AWA_5100.TXBUR_MT;
        CEP.TRC(SCCGWA, GDB5100_AWA_5100.TXACTYP);
        if (GDB5100_AWA_5100.TXACTYP != '1' 
            && GDB5100_AWA_5100.TXACTYP != '0') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_AC_TYPE_INVALID;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        S000_CALL_GDZCMPRL();
    }
    public void S000_CALL_GDZCMPRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSMPRL", GDCSMPRL);
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