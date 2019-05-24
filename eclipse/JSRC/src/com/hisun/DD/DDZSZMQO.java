package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.AI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSZMQO {
    DBParm DDTCCY_RD;
    DBParm DDTZMQT_RD;
    String K_ZMQI_BUSI_CODE = "ZMRZ";
    String K_FZMQI_BUSI_CODE = "FZMRZ";
    String K_OUTPUT_FMT = "DD110";
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
    DDZSZMQO_WS_MIB_AC WS_MIB_AC = new DDZSZMQO_WS_MIB_AC();
    char WS_AC_ROUTINE_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    CICQACRI CICQACRI = new CICQACRI();
    CICQACRI CICFACRI = new CICQACRI();
    BPCPQORG BPCPQORG = new BPCPQORG();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    AICUUPIA AICUUPIA = new AICUUPIA();
    AICPQIA AICPQIA = new AICPQIA();
    DDCOZMQO DDCOZMQO = new DDCOZMQO();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DDRCCY DDRCCY = new DDRCCY();
    DDRZMQT DDRZMQT = new DDRZMQT();
    SCCGWA SCCGWA;
    DDCSZMQO DDCSZMQO;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSZMQO DDCSZMQO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSZMQO = DDCSZMQO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "DDZSZMQO return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT_DATA();
        B200_GET_TRAC_INFO();
        B300_GET_TRBK_INFO();
        B310_GET_INNER_AC_INFO();
        B400_WRITE_DDTZMQT();
        B500_ZMQ_TRANS();
        B600_OUTPUT_DATA();
    }
    public void B100_CHK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSZMQO.SOURCE);
        CEP.TRC(SCCGWA, DDCSZMQO.FR_BV_TP);
        CEP.TRC(SCCGWA, DDCSZMQO.FR_CARD);
        CEP.TRC(SCCGWA, DDCSZMQO.FR_ACNO);
        CEP.TRC(SCCGWA, DDCSZMQO.FR_ACNM);
        CEP.TRC(SCCGWA, DDCSZMQO.FR_ACBAL);
        CEP.TRC(SCCGWA, DDCSZMQO.FR_PBNO);
        CEP.TRC(SCCGWA, DDCSZMQO.CHQ_TYP);
        CEP.TRC(SCCGWA, DDCSZMQO.CHQ_NO);
        CEP.TRC(SCCGWA, DDCSZMQO.CHQ_ISDT);
        CEP.TRC(SCCGWA, DDCSZMQO.CHQ_PSW);
        CEP.TRC(SCCGWA, DDCSZMQO.FR_DRMTH);
        CEP.TRC(SCCGWA, DDCSZMQO.FR_PSWD);
        CEP.TRC(SCCGWA, DDCSZMQO.FR_CCY);
        CEP.TRC(SCCGWA, DDCSZMQO.FR_CYTP);
        CEP.TRC(SCCGWA, DDCSZMQO.TR_AMT);
        CEP.TRC(SCCGWA, DDCSZMQO.VAL_DT);
        CEP.TRC(SCCGWA, DDCSZMQO.DIRECT);
        CEP.TRC(SCCGWA, DDCSZMQO.TO_BV_TP);
        CEP.TRC(SCCGWA, DDCSZMQO.TO_CARD);
        CEP.TRC(SCCGWA, DDCSZMQO.TO_ACNO);
        CEP.TRC(SCCGWA, DDCSZMQO.TO_ACNM);
        CEP.TRC(SCCGWA, DDCSZMQO.TO_PBNO);
        CEP.TRC(SCCGWA, DDCSZMQO.TR_RSN);
        CEP.TRC(SCCGWA, DDCSZMQO.TR_DESC);
        CEP.TRC(SCCGWA, DDCSZMQO.TR_RMK);
        CEP.TRC(SCCGWA, DDCSZMQO.AGEN_FLG);
        CEP.TRC(SCCGWA, DDCSZMQO.TO_CCY);
        CEP.TRC(SCCGWA, DDCSZMQO.TO_CYTP);
        if (DDCSZMQO.FR_ACNO.trim().length() == 0 
            && DDCSZMQO.FR_CARD.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FRAC_NO_M_INPUT);
        }
        if (DDCSZMQO.FR_CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT);
        }
        if (DDCSZMQO.TR_AMT == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TRF_AMT_M_INPUT);
        } else {
            if (DDCSZMQO.TR_AMT < 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AMT_INVALID);
            }
        }
        if (DDCSZMQO.TO_ACNO.trim().length() == 0 
            && DDCSZMQO.TO_CARD.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TOAC_NO_M_INPUT);
        }
        if ((DDCSZMQO.FR_ACNO.trim().length() > 0 
            && DDCSZMQO.TO_ACNO.trim().length() > 0) 
            && (DDCSZMQO.FR_ACNO.equalsIgnoreCase(DDCSZMQO.TO_ACNO) 
            && DDCSZMQO.FR_CCY.equalsIgnoreCase(DDCSZMQO.TO_CCY))) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CAN_NOT_SAME);
        }
        if (DDCSZMQO.CHQ_TYP != ' ') {
            if (DDCSZMQO.CHQ_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CHQ_MUST_INP);
            }
        }
        if ((DDCSZMQO.FR_CYTP == '1' 
            && DDCSZMQO.TO_CYTP == '2') 
            || (DDCSZMQO.FR_CYTP == '2' 
            && DDCSZMQO.TO_CYTP == '1')) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYPE_ERROR);
        }
        if ((DDCSZMQO.FR_CCY.trim().length() > 0 
            && DDCSZMQO.TO_CCY.trim().length() > 0 
            && !DDCSZMQO.FR_CCY.equalsIgnoreCase(DDCSZMQO.TO_CCY))) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_NOT_SAME;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_GET_TRAC_INFO() throws IOException,SQLException,Exception {
        WS_AC_ROUTINE_FLG = 'N';
        if (DDCSZMQO.FR_ACNO.trim().length() > 0) {
            WS_FR_AC = DDCSZMQO.FR_ACNO;
        } else {
            WS_FR_AC = DDCSZMQO.FR_CARD;
        }
        CEP.TRC(SCCGWA, WS_FR_AC);
        IBS.init(SCCGWA, CICQACRI);
        IBS.init(SCCGWA, CICFACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_FR_AC;
        S000_CALL_CIZQACRI();
        IBS.CLONE(SCCGWA, CICQACRI, CICFACRI);
        CEP.TRC(SCCGWA, CICFACRI.O_DATA.O_OPN_BR);
        WS_AC_ROUTINE_FLG = 'N';
        if (DDCSZMQO.TO_ACNO.trim().length() > 0) {
            WS_TO_AC = DDCSZMQO.TO_ACNO;
        } else {
            WS_TO_AC = DDCSZMQO.TO_CARD;
        }
        CEP.TRC(SCCGWA, WS_TO_AC);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = WS_TO_AC;
        S000_CALL_CIZQACRI();
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_OPN_BR);
    }
    public void B300_GET_TRBK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = WS_FR_AC;
        DDRCCY.CCY = DDCSZMQO.FR_CCY;
        DDRCCY.CCY_TYPE = DDCSZMQO.FR_CYTP;
        R000_READ_DDTCCY();
        CEP.TRC(SCCGWA, DDRCCY.OWNER_BR);
        WS_FR_BR = CICFACRI.O_DATA.O_OPN_BR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_FR_BR;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
        WS_FR_BR_TYPE = BPCPQORG.TRA_TYP;
        WS_FR_BR_NM = BPCPQORG.CHN_NM;
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = WS_TO_AC;
        DDRCCY.CCY = DDCSZMQO.TO_CCY;
        DDRCCY.CCY_TYPE = DDCSZMQO.TO_CYTP;
        R000_READ_DDTCCY();
        CEP.TRC(SCCGWA, DDRCCY.OWNER_BR);
        WS_TO_BR = CICQACRI.O_DATA.O_OPN_BR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_TO_BR;
        S000_CALL_BPZPQORG();
        CEP.TRC(SCCGWA, BPCPQORG.TRA_TYP);
        CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
        WS_TO_BR_TYPE = BPCPQORG.TRA_TYP;
        WS_TO_BR_NM = BPCPQORG.CHN_NM;
        if (WS_FR_BR_TYPE.equalsIgnoreCase(WS_TO_BR_TYPE)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_BR_TYPE_SAME_CANT_TR);
        }
    }
    public void B310_GET_INNER_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.CD.AC_TYP = "3";
        if (WS_TO_BR_TYPE.equalsIgnoreCase("00")) {
            AICPQIA.CD.BUSI_KND = K_FZMQI_BUSI_CODE;
        } else {
            AICPQIA.CD.BUSI_KND = K_ZMQI_BUSI_CODE;
        }
        AICPQIA.BR = WS_TO_BR;
        AICPQIA.CCY = DDCSZMQO.TO_CCY;
        AICPQIA.SIGN = 'C';
        S000_CALL_AIZPQIA();
        IBS.CPY2CLS(SCCGWA, AICPQIA.AC, WS_MIB_AC);
        CEP.TRC(SCCGWA, AICPQIA.AC);
    }
    public void B400_WRITE_DDTZMQT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, DDRZMQT);
            DDRZMQT.KEY.OUT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRZMQT.KEY.OUT_JRN = SCCGWA.COMM_AREA.JRN_NO;
            DDRZMQT.STS = '0';
            DDRZMQT.FRM_AC = WS_FR_AC;
            DDRZMQT.FRM_CCY = DDCSZMQO.FR_CCY;
            DDRZMQT.FRM_CCY_TYP = DDCSZMQO.FR_CYTP;
            DDRZMQT.FRM_AC_NM = DDCSZMQO.FR_ACNM;
            DDRZMQT.FRM_BR = WS_FR_BR;
            DDRZMQT.FRM_BR_NM = WS_FR_BR_NM;
            DDRZMQT.FRM_BR_TYPE = WS_FR_BR_TYPE;
            DDRZMQT.OUT_AMT = DDCSZMQO.TR_AMT;
            DDRZMQT.AI_ACNO = IBS.CLS2CPY(SCCGWA, WS_MIB_AC);
            DDRZMQT.TR_RSN = DDCSZMQO.TR_RSN;
            DDRZMQT.TR_DESC = DDCSZMQO.TR_DESC;
            DDRZMQT.TR_RMK = DDCSZMQO.TR_RMK;
            DDRZMQT.TO_AC = WS_TO_AC;
            DDRZMQT.TO_CCY = DDCSZMQO.TO_CCY;
            DDRZMQT.TO_CCY_TYP = DDCSZMQO.TO_CYTP;
            DDRZMQT.TO_AC_NM = DDCSZMQO.TO_ACNM;
            DDRZMQT.TO_BR = WS_TO_BR;
            DDRZMQT.TO_BR_NM = WS_TO_BR_NM;
            DDRZMQT.TO_BR_TYPE = WS_TO_BR_TYPE;
            DDRZMQT.IN_AMT = DDCSZMQO.TR_AMT;
            DDRZMQT.IN_DATE = 0;
            DDRZMQT.IN_JRN = 0;
            DDRZMQT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRZMQT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRZMQT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRZMQT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            CEP.TRC(SCCGWA, DDRZMQT.KEY.OUT_DATE);
            CEP.TRC(SCCGWA, DDRZMQT.FRM_AC);
            CEP.TRC(SCCGWA, DDRZMQT.FRM_BR_TYPE);
            CEP.TRC(SCCGWA, DDRZMQT.TO_AC);
            CEP.TRC(SCCGWA, DDRZMQT.TO_BR_TYPE);
            R000_WRITE_DDTZMQT();
        } else {
            IBS.init(SCCGWA, DDRZMQT);
            DDRZMQT.KEY.OUT_DATE = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
            DDRZMQT.KEY.OUT_JRN = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
            R000_READUPDATE_DDTZMQT();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OUT_TRANS_NOTFND);
            } else {
                CEP.TRC(SCCGWA, DDRZMQT.STS);
                if (DDRZMQT.STS != '0' 
                    && DDRZMQT.STS != '4') {
                    CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_ZMQT_CANT_CANCEL);
                } else {
                    DDRZMQT.STS = '2';
                    DDRZMQT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    DDRZMQT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    R000_REWRITE_DDTZMQT();
                }
            }
        }
    }
    public void B500_ZMQ_TRANS() throws IOException,SQLException,Exception {
        if (WS_FR_BR_TYPE.equalsIgnoreCase("00") 
                && !WS_TO_BR_TYPE.equalsIgnoreCase("00")) {
            CEP.TRC(SCCGWA, "--AC TO FT--");
            B510_TRANS_AC_TO_FT();
        } else if (!WS_FR_BR_TYPE.equalsIgnoreCase("00") 
                && WS_TO_BR_TYPE.equalsIgnoreCase("00")) {
            CEP.TRC(SCCGWA, "--FT TO AC--");
            B520_TRANS_FT_TO_AC();
        } else if (!WS_FR_BR_TYPE.equalsIgnoreCase("00") 
                && !WS_TO_BR_TYPE.equalsIgnoreCase("00") 
                && !WS_FR_BR_TYPE.equalsIgnoreCase(WS_TO_BR_TYPE)) {
            CEP.TRC(SCCGWA, "--FT TO FT--");
            B530_TRANS_FT_TO_FT();
        } else {
            CEP.TRC(SCCGWA, "--NO FT TRANS--");
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_TRA_TYP_ERROR);
        }
        CEP.TRC(SCCGWA, DDCSZMQO.AGEN_FLG);
        if (DDCSZMQO.AGEN_FLG == '1') {
            B700_AGENT_INFO_PROC();
        }
    }
    public void B600_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCOZMQO);
        DDCOZMQO.FR_CARD = DDCSZMQO.FR_CARD;
        DDCOZMQO.FR_ACNO = DDCSZMQO.FR_ACNO;
        DDCOZMQO.FR_CCY = DDRZMQT.FRM_CCY;
        DDCOZMQO.FR_CYTP = DDRZMQT.FRM_CCY_TYP;
        DDCOZMQO.FR_ACNM = DDRZMQT.FRM_AC_NM;
        DDCOZMQO.FR_BR = DDRZMQT.FRM_BR;
        DDCOZMQO.FR_BRNM = DDRZMQT.FRM_BR_NM;
        DDCOZMQO.TR_AMT = DDCSZMQO.TR_AMT;
        DDCOZMQO.TO_CARD = DDCSZMQO.TO_CARD;
        DDCOZMQO.TO_ACNO = DDCSZMQO.TO_ACNO;
        DDCOZMQO.TO_CCY = DDRZMQT.TO_CCY;
        DDCOZMQO.TO_CYTP = DDRZMQT.TO_CCY_TYP;
        DDCOZMQO.TO_ACNM = DDRZMQT.TO_AC_NM;
        DDCOZMQO.TO_BR = DDRZMQT.TO_BR;
        DDCOZMQO.TO_BRNM = DDRZMQT.TO_BR_NM;
        DDCOZMQO.TR_RSN = DDRZMQT.TR_RSN;
        DDCOZMQO.TR_DESC = DDRZMQT.TR_DESC;
        DDCOZMQO.TR_RMK = DDRZMQT.TR_RMK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCOZMQO;
        SCCFMT.DATA_LEN = 1521;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B700_AGENT_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = CICFACRI.O_DATA.O_CI_NO;
        CICSAGEN.OUT_AC = CICFACRI.O_DATA.O_AGR_NO;
        CEP.TRC(SCCGWA, CICSAGEN.OUT_AC);
        CICSAGEN.ID_TYP = DDCSZMQO.ID_TYP;
        CICSAGEN.ID_NO = DDCSZMQO.ID_NO;
        CICSAGEN.AGENT_TP = "04";
        CICSAGEN.CI_NAME = DDCSZMQO.CI_NM;
        CICSAGEN.PHONE = DDCSZMQO.PHONE;
        S000_CALL_CIZSAGEN();
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void B510_TRANS_AC_TO_FT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.CHK_PSW_FLG = 'Y';
        DDCUDRAC.AC = WS_FR_AC;
        DDCUDRAC.CCY = DDCSZMQO.FR_CCY;
        DDCUDRAC.CCY_TYPE = DDCSZMQO.FR_CYTP;
        DDCUDRAC.TX_AMT = DDCSZMQO.TR_AMT;
        DDCUDRAC.VAL_DATE = DDCSZMQO.VAL_DT;
        DDCUDRAC.TX_MMO = "A019";
        DDCUDRAC.PAY_TYPE = DDCSZMQO.FR_DRMTH;
        DDCUDRAC.PSWD = DDCSZMQO.FR_PSWD;
        DDCUDRAC.OTHER_AC = IBS.CLS2CPY(SCCGWA, WS_MIB_AC);
        DDCUDRAC.OTHER_CCY = DDCSZMQO.TO_CCY;
        DDCUDRAC.RLT_AC = WS_TO_AC;
        DDCUDRAC.RLT_CCY = DDCSZMQO.TO_CCY;
        DDCUDRAC.OTHER_AMT = DDCSZMQO.TR_AMT;
        DDCUDRAC.CHQ_TYPE = DDCSZMQO.CHQ_TYP;
        DDCUDRAC.CHQ_NO = DDCSZMQO.CHQ_NO;
        DDCUDRAC.CHQ_ISS_DATE = DDCSZMQO.CHQ_ISDT;
        DDCUDRAC.PAY_PSWD = DDCSZMQO.CHQ_PSW;
        S000_CALL_DDZUDRAC();
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = IBS.CLS2CPY(SCCGWA, WS_MIB_AC);
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.CCY = DDCSZMQO.TO_CCY;
        AICUUPIA.DATA.AMT = DDCSZMQO.TR_AMT;
        AICUUPIA.DATA.VALUE_DATE = DDCSZMQO.VAL_DT;
        S000_CALL_AIZUUPIA();
    }
    public void B520_TRANS_FT_TO_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.AC = WS_FR_AC;
        DDCUDRAC.CCY = DDCSZMQO.FR_CCY;
        DDCUDRAC.CCY_TYPE = DDCSZMQO.FR_CYTP;
        DDCUDRAC.TX_AMT = DDCSZMQO.TR_AMT;
        DDCUDRAC.VAL_DATE = DDCSZMQO.VAL_DT;
        DDCUDRAC.TX_MMO = "A019";
        DDCUDRAC.OTHER_AC = IBS.CLS2CPY(SCCGWA, WS_MIB_AC);
        DDCUDRAC.OTHER_CCY = DDCSZMQO.TO_CCY;
        DDCUDRAC.RLT_AC = WS_TO_AC;
        DDCUDRAC.RLT_CCY = DDCSZMQO.TO_CCY;
        DDCUDRAC.OTHER_AMT = DDCSZMQO.TR_AMT;
        DDCUDRAC.CHQ_TYPE = DDCSZMQO.CHQ_TYP;
        DDCUDRAC.CHQ_NO = DDCSZMQO.CHQ_NO;
        DDCUDRAC.CHQ_ISS_DATE = DDCSZMQO.CHQ_ISDT;
        DDCUDRAC.PAY_PSWD = DDCSZMQO.CHQ_PSW;
        S000_CALL_DDZUDRAC();
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = IBS.CLS2CPY(SCCGWA, WS_MIB_AC);
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.CCY = DDCSZMQO.TO_CCY;
        AICUUPIA.DATA.AMT = DDCSZMQO.TR_AMT;
        AICUUPIA.DATA.VALUE_DATE = DDCSZMQO.VAL_DT;
        S000_CALL_AIZUUPIA();
    }
    public void B530_TRANS_FT_TO_FT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.AC = WS_FR_AC;
        DDCUDRAC.CCY = DDCSZMQO.FR_CCY;
        DDCUDRAC.CCY_TYPE = DDCSZMQO.FR_CYTP;
        DDCUDRAC.TX_AMT = DDCSZMQO.TR_AMT;
        DDCUDRAC.VAL_DATE = DDCSZMQO.VAL_DT;
        DDCUDRAC.TX_MMO = "A019";
        DDCUDRAC.OTHER_AC = IBS.CLS2CPY(SCCGWA, WS_MIB_AC);
        DDCUDRAC.OTHER_CCY = DDCSZMQO.TO_CCY;
        DDCUDRAC.RLT_AC = WS_TO_AC;
        DDCUDRAC.RLT_CCY = DDCSZMQO.TO_CCY;
        DDCUDRAC.OTHER_AMT = DDCSZMQO.TR_AMT;
        DDCUDRAC.CHQ_TYPE = DDCSZMQO.CHQ_TYP;
        DDCUDRAC.CHQ_NO = DDCSZMQO.CHQ_NO;
        DDCUDRAC.CHQ_ISS_DATE = DDCSZMQO.CHQ_ISDT;
        DDCUDRAC.PAY_PSWD = DDCSZMQO.CHQ_PSW;
        S000_CALL_DDZUDRAC();
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = IBS.CLS2CPY(SCCGWA, WS_MIB_AC);
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.CCY = DDCSZMQO.TO_CCY;
        AICUUPIA.DATA.AMT = DDCSZMQO.TR_AMT;
        AICUUPIA.DATA.VALUE_DATE = DDCSZMQO.VAL_DT;
        S000_CALL_AIZUUPIA();
    }
    public void R000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.eqWhere = "CUS_AC,CCY,CCY_TYPE";
        DDTCCY_RD.where = "STS = 'N'";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
    }
    public void R000_WRITE_DDTZMQT() throws IOException,SQLException,Exception {
        DDTZMQT_RD = new DBParm();
        DDTZMQT_RD.TableName = "DDTZMQT";
        IBS.WRITE(SCCGWA, DDRZMQT, DDTZMQT_RD);
    }
    public void R000_READUPDATE_DDTZMQT() throws IOException,SQLException,Exception {
        DDTZMQT_RD = new DBParm();
        DDTZMQT_RD.TableName = "DDTZMQT";
        DDTZMQT_RD.upd = true;
        IBS.READ(SCCGWA, DDRZMQT, DDTZMQT_RD);
    }
    public void R000_REWRITE_DDTZMQT() throws IOException,SQLException,Exception {
        DDTZMQT_RD = new DBParm();
        DDTZMQT_RD.TableName = "DDTZMQT";
        IBS.REWRITE(SCCGWA, DDRZMQT, DDTZMQT_RD);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        if (WS_AC_ROUTINE_FLG == 'N') {
            IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
            if (CICQACRI.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, CICQACRI.RC);
            }
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        if (AICPQIA.RC.RC_CODE != 0 
            || AICPQIA.AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, AICPQIA.RC);
        }
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
