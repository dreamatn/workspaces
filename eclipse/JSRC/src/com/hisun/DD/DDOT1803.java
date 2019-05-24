package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1803 {
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCSCLAC DDCSCLAC = new DDCSCLAC();
    SCCGWA SCCGWA;
    DDB1800_AWA_1800 DDB1800_AWA_1800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "DDOT1803 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1800_AWA_1800>");
        DDB1800_AWA_1800 = (DDB1800_AWA_1800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_TRANS_DATA_PROC();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.ACNO);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.CCY);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TRF_FLG);
        if (DDB1800_AWA_1800.ACNO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB1800_AWA_1800.ACNO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (DDB1800_AWA_1800.CCY.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
    }
    public void B200_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.BV_TYP);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.DR_CARD);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.ACNO);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.AC_CNM);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.AC_ENM);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TOT_BAL);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TRF_FLG);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TO_BVTYP);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.NARRATIV);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TO_CARD);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TRF_AC);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.MMO);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.REMARK);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.PSBK_NO);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.CCY);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.CCY_TYP);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.DRW_PSW);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.FEE_AMT);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.CHK_PSW);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TRK_DT2);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TRK_DT3);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.CARD_NO2);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.PSW2);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TRK2_DT2);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TRK3_DT2);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.CARD_NO3);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.PSW3);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TRK2_DT3);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TRK3_DT3);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.CARD_NO4);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.PSW4);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TRK2_DT4);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TRK3_DT4);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.CARD_NO5);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.PSW5);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TRK2_DT5);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.TRK3_DT5);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.LOSS_NO);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.ID_TYP);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.ID_NO);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.RVS_NO);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.BAT_FLG);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.AC_CNM);
        IBS.init(SCCGWA, DDCSCLAC);
        DDCSCLAC.BV_TYP = DDB1800_AWA_1800.BV_TYP;
        DDCSCLAC.DR_CARD = DDB1800_AWA_1800.DR_CARD;
        DDCSCLAC.AC_NO = DDB1800_AWA_1800.ACNO;
        DDCSCLAC.AC_CNM = DDB1800_AWA_1800.AC_CNM;
        DDCSCLAC.AC_ENM = DDB1800_AWA_1800.AC_ENM;
        DDCSCLAC.AC_BAL = DDB1800_AWA_1800.TOT_BAL;
        DDCSCLAC.TRF_FLG = DDB1800_AWA_1800.TRF_FLG;
        DDCSCLAC.TO_BVTYP = DDB1800_AWA_1800.TO_BVTYP;
        DDCSCLAC.NARRATIVE = DDB1800_AWA_1800.NARRATIV;
        DDCSCLAC.TO_CARD = DDB1800_AWA_1800.TO_CARD;
        DDCSCLAC.TRF_AC = DDB1800_AWA_1800.TRF_AC;
        DDCSCLAC.MMO = DDB1800_AWA_1800.MMO;
        DDCSCLAC.PSBK_NO = DDB1800_AWA_1800.PSBK_NO;
        DDCSCLAC.CCY = DDB1800_AWA_1800.CCY;
        DDCSCLAC.CCY_TYPE = DDB1800_AWA_1800.CCY_TYP;
        DDCSCLAC.DRW_PSW = DDB1800_AWA_1800.DRW_PSW;
        DDCSCLAC.TRF_FLG = DDB1800_AWA_1800.TRF_FLG;
        DDCSCLAC.REMARK = DDB1800_AWA_1800.REMARK;
        DDCSCLAC.TO_BVTYP = DDB1800_AWA_1800.TO_BVTYP;
        DDCSCLAC.TO_CARD = DDB1800_AWA_1800.TO_CARD;
        DDCSCLAC.TRF_AC = DDB1800_AWA_1800.TRF_AC;
        DDCSCLAC.FEE_AMT = DDB1800_AWA_1800.FEE_AMT;
        DDCSCLAC.CHK_PSW = DDB1800_AWA_1800.CHK_PSW;
        DDCSCLAC.TRK_DT2 = DDB1800_AWA_1800.TRK_DT2;
        DDCSCLAC.TRK_DT3 = DDB1800_AWA_1800.TRK_DT3;
        DDCSCLAC.CARD_INFO[1-1].CARD_NO = DDB1800_AWA_1800.CARD_NO2;
        DDCSCLAC.CARD_INFO[1-1].CARD_PSW = DDB1800_AWA_1800.PSW2;
        DDCSCLAC.CARD_INFO[1-1].TRK2_DAT = DDB1800_AWA_1800.TRK2_DT2;
        DDCSCLAC.CARD_INFO[1-1].TRK3_DAT = DDB1800_AWA_1800.TRK3_DT2;
        DDCSCLAC.CARD_INFO[2-1].CARD_NO = DDB1800_AWA_1800.CARD_NO3;
        DDCSCLAC.CARD_INFO[2-1].CARD_PSW = DDB1800_AWA_1800.PSW3;
        DDCSCLAC.CARD_INFO[2-1].TRK2_DAT = DDB1800_AWA_1800.TRK2_DT3;
        DDCSCLAC.CARD_INFO[2-1].TRK3_DAT = DDB1800_AWA_1800.TRK3_DT3;
        DDCSCLAC.CARD_INFO[3-1].CARD_NO = DDB1800_AWA_1800.CARD_NO4;
        DDCSCLAC.CARD_INFO[3-1].CARD_PSW = DDB1800_AWA_1800.PSW4;
        DDCSCLAC.CARD_INFO[3-1].TRK2_DAT = DDB1800_AWA_1800.TRK2_DT4;
        DDCSCLAC.CARD_INFO[3-1].TRK3_DAT = DDB1800_AWA_1800.TRK3_DT4;
        DDCSCLAC.CARD_INFO[4-1].CARD_NO = DDB1800_AWA_1800.CARD_NO5;
        DDCSCLAC.CARD_INFO[4-1].CARD_PSW = DDB1800_AWA_1800.PSW5;
        DDCSCLAC.CARD_INFO[4-1].TRK2_DAT = DDB1800_AWA_1800.TRK2_DT5;
        DDCSCLAC.CARD_INFO[4-1].TRK3_DAT = DDB1800_AWA_1800.TRK3_DT5;
        DDCSCLAC.LOSS_NO = DDB1800_AWA_1800.LOSS_NO;
        DDCSCLAC.PAY_TYPE = DDB1800_AWA_1800.DRW_MTH;
        DDCSCLAC.PAY_ID_TYPE = DDB1800_AWA_1800.ID_TYP;
        DDCSCLAC.PAY_ID_NO = DDB1800_AWA_1800.ID_NO;
        DDCSCLAC.RVS_NO = DDB1800_AWA_1800.RVS_NO;
        DDCSCLAC.BAT_FLG = DDB1800_AWA_1800.BAT_FLG;
        DDCSCLAC.AC_CNM = DDB1800_AWA_1800.AC_CNM;
        DDCSCLAC.TRF_CCY = DDB1800_AWA_1800.TO_CCY;
        DDCSCLAC.TRF_CCY_TYPE = DDB1800_AWA_1800.TO_CCYTP;
        DDCSCLAC.REG_CD = DDB1800_AWA_1800.REG_CD;
        S000_CALL_DDZSCLAC();
    }
    public void S000_CALL_DDZSCLAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CLOSE-AC", DDCSCLAC);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}