package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSZMQI {
    DBParm DDTZMQT_RD;
    String K_OUTPUT_FMT = "DD113";
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_FR_AC = " ";
    String WS_FR_ACNM = " ";
    int WS_FR_BR = 0;
    String WS_FR_BR_NM = " ";
    String WS_FR_BR_TYPE = " ";
    String WS_TO_AC = " ";
    String WS_TO_ACNM = " ";
    int WS_TO_BR = 0;
    String WS_TO_BR_NM = " ";
    String WS_TO_BR_TYPE = " ";
    String WS_MIB_AC = " ";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCOZMQI DDCOZMQI = new DDCOZMQI();
    DDRCCY DDRCCY = new DDRCCY();
    DDRZMQT DDRZMQT = new DDRZMQT();
    SCCGWA SCCGWA;
    DDCSZMQI DDCSZMQI;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSZMQI DDCSZMQI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSZMQI = DDCSZMQI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSZMQI return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT_DATA();
        B200_CHK_TR_INFO();
        B300_TRANS_AMT_IN();
        B400_OUTPUT_DATA();
    }
    public void B100_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSZMQI.TR_DATE);
        CEP.TRC(SCCGWA, DDCSZMQI.TR_JRN);
        CEP.TRC(SCCGWA, DDCSZMQI.IN_AMT);
        CEP.TRC(SCCGWA, DDCSZMQI.IN_AC);
        CEP.TRC(SCCGWA, DDCSZMQI.IN_ACNM);
        CEP.TRC(SCCGWA, DDCSZMQI.IN_CCY);
        CEP.TRC(SCCGWA, DDCSZMQI.IN_CYTP);
        CEP.TRC(SCCGWA, DDCSZMQI.TR_RSN);
        CEP.TRC(SCCGWA, DDCSZMQI.TR_DESC);
        CEP.TRC(SCCGWA, DDCSZMQI.TR_RMK);
        if (DDCSZMQI.TR_DATE == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_DATE_M_INPUT);
        }
        if (DDCSZMQI.TR_JRN == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_JRN_M_INPUT);
        }
        if (DDCSZMQI.IN_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TOAC_NO_M_INPUT);
        }
        if (DDCSZMQI.IN_CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT);
        }
        if (DDCSZMQI.IN_CYTP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT);
        }
        if (DDCSZMQI.IN_AMT == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TRF_AMT_M_INPUT);
        }
    }
    public void B200_CHK_TR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRZMQT);
        DDRZMQT.KEY.OUT_DATE = DDCSZMQI.TR_DATE;
        DDRZMQT.KEY.OUT_JRN = DDCSZMQI.TR_JRN;
        R000_READUPDATE_DDTZMQT();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OUT_TRANS_NOTFND);
        }
        CEP.TRC(SCCGWA, DDCSZMQI.IN_AC);
        CEP.TRC(SCCGWA, DDRZMQT.TO_AC);
        CEP.TRC(SCCGWA, DDCSZMQI.IN_CCY);
        CEP.TRC(SCCGWA, DDRZMQT.TO_CCY);
        CEP.TRC(SCCGWA, DDCSZMQI.IN_CYTP);
        CEP.TRC(SCCGWA, DDRZMQT.TO_CCY_TYP);
        CEP.TRC(SCCGWA, DDCSZMQI.IN_AMT);
        CEP.TRC(SCCGWA, DDRZMQT.IN_AMT);
        CEP.TRC(SCCGWA, DDRZMQT.STS);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (!DDCSZMQI.IN_AC.equalsIgnoreCase(DDRZMQT.TO_AC)) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IN_AC_INP_ERR);
            }
            if (!DDCSZMQI.IN_CCY.equalsIgnoreCase(DDRZMQT.TO_CCY)) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IN_CCY_INP_ERR);
            }
            if (DDCSZMQI.IN_CYTP != DDRZMQT.TO_CCY_TYP) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IN_CYTP_INP_ERR);
            }
            if (DDCSZMQI.IN_AMT != DDRZMQT.IN_AMT) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_IN_AMT_INP_ERR);
            }
            if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
                if (DDRZMQT.STS == '1') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_ALREADY_IN);
                }
            } else {
                if (DDRZMQT.STS != '1') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ZMQT_CANT_CANCEL);
                }
            }
            if (DDRZMQT.STS == '2' 
                || DDRZMQT.STS == '3') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_ALR_CANCEL);
            }
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_BANK);
            CEP.TRC(SCCGWA, DDRZMQT.TO_BR);
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != DDRZMQT.TO_BR) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TR_BRANCH_ERR);
            }
        }
    }
    public void B300_TRANS_AMT_IN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = DDRZMQT.AI_ACNO;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.CCY = DDRZMQT.TO_CCY;
        AICUUPIA.DATA.AMT = DDRZMQT.IN_AMT;
        AICUUPIA.DATA.VALUE_DATE = DDRZMQT.KEY.OUT_DATE;
        S000_CALL_AIZUUPIA();
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.AC = DDRZMQT.TO_AC;
        DDCUCRAC.CCY = DDRZMQT.TO_CCY;
        DDCUCRAC.CCY_TYPE = DDRZMQT.TO_CCY_TYP;
        DDCUCRAC.TX_AMT = DDRZMQT.IN_AMT;
        DDCUCRAC.VAL_DATE = DDRZMQT.KEY.OUT_DATE;
        DDCUCRAC.TX_MMO = "A001";
        DDCUCRAC.RLT_AC = DDRZMQT.FRM_AC;
        DDCUCRAC.RLT_CCY = DDRZMQT.FRM_CCY;
        DDCUCRAC.OTHER_AMT = DDRZMQT.IN_AMT;
        DDCUCRAC.OTHER_AC = DDRZMQT.AI_ACNO;
        DDCUCRAC.OTHER_CCY = DDRZMQT.TO_CCY;
        S000_CALL_DDZUCRAC();
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            DDRZMQT.STS = '1';
            DDRZMQT.IN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRZMQT.IN_JRN = SCCGWA.COMM_AREA.JRN_NO;
            DDRZMQT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRZMQT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            R000_REWRITE_DDTZMQT();
        } else {
            IBS.init(SCCGWA, DDRZMQT);
            DDRZMQT.IN_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            DDRZMQT.IN_JRN = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            R000_READUPDATE_DDTZMQT1();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OUT_TRANS_NOTFND);
            } else {
                CEP.TRC(SCCGWA, DDRZMQT.STS);
                if (DDRZMQT.STS != '1') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ZMQT_CANT_CANCEL);
                } else {
                    DDRZMQT.STS = '3';
                    DDRZMQT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    DDRZMQT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    R000_REWRITE_DDTZMQT();
                }
            }
        }
    }
    public void B400_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOZMQI);
        DDCOZMQI.TR_DATE = DDCSZMQI.TR_DATE;
        DDCOZMQI.TR_JRN = DDCSZMQI.TR_JRN;
        DDCOZMQI.IN_AC = DDCSZMQI.IN_AC;
        DDCOZMQI.IN_CCY = DDCSZMQI.IN_CCY;
        DDCOZMQI.IN_CYTP = DDCSZMQI.IN_CYTP;
        DDCOZMQI.IN_AMT = DDCSZMQI.IN_AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOZMQI;
        SCCFMT.DATA_LEN = 696;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_READUPDATE_DDTZMQT() throws IOException,SQLException,Exception {
        DDTZMQT_RD = new DBParm();
        DDTZMQT_RD.TableName = "DDTZMQT";
        DDTZMQT_RD.upd = true;
        IBS.READ(SCCGWA, DDRZMQT, DDTZMQT_RD);
    }
    public void R000_READUPDATE_DDTZMQT1() throws IOException,SQLException,Exception {
        DDTZMQT_RD = new DBParm();
        DDTZMQT_RD.TableName = "DDTZMQT";
        DDTZMQT_RD.where = "IN_DATE = :DDRZMQT.IN_DATE "
            + "AND IN_JRN = :DDRZMQT.IN_JRN";
        DDTZMQT_RD.upd = true;
        IBS.READ(SCCGWA, DDRZMQT, this, DDTZMQT_RD);
    }
    public void R000_REWRITE_DDTZMQT() throws IOException,SQLException,Exception {
        DDTZMQT_RD = new DBParm();
        DDTZMQT_RD.TableName = "DDTZMQT";
        IBS.REWRITE(SCCGWA, DDRZMQT, DDTZMQT_RD);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
