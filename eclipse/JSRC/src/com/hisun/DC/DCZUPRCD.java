package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUPRCD {
    brParm DCTMPRD_BR = new brParm();
    DBParm DCTMPRD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC999";
    int WS_OUT_CNT = 0;
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    int WS_EFF_DATE = 0;
    int WS_EXP_DATE = 0;
    char WS_MPRD_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCRMPRD DCRMPRD = new DCRMPRD();
    SCCGWA SCCGWA;
    DCCUPRCD DCCUPRCD;
    public void MP(SCCGWA SCCGWA, DCCUPRCD DCCUPRCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUPRCD = DCCUPRCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUPRCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DCCUPRCD.TX_TYPE == 'I') {
            B200_INQ_PARM_CD_PROC();
            if (pgmRtn) return;
        } else if (DCCUPRCD.TX_TYPE == 'A') {
            B300_ADD_PARM_CD_PROC();
            if (pgmRtn) return;
        } else if (DCCUPRCD.TX_TYPE == 'M') {
            B400_MOD_PARM_CD_PROC();
            if (pgmRtn) return;
        } else if (DCCUPRCD.TX_TYPE == 'D') {
            B500_DEL_PARM_CD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID TX TYPE(" + DCCUPRCD.TX_TYPE + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCUPRCD.TX_TYPE == ' ') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT, DCCUPRCD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCUPRCD.TX_TYPE != 'A' 
            && DCCUPRCD.TX_TYPE != 'D' 
            && DCCUPRCD.TX_TYPE != 'M' 
            && DCCUPRCD.TX_TYPE != 'I') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_INVALD, DCCUPRCD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCUPRCD.DATA.KEY.IBS_AC_BK.trim().length() == 0) {
            DCCUPRCD.DATA.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        }
        if (DCCUPRCD.DATA.VAL.PRDMO_CD.trim().length() == 0) {
            DCCUPRCD.DATA.VAL.PRDMO_CD = "CARD";
        }
        if (DCCUPRCD.DATA.KEY.CD.PARM_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PARM_CODE_M_INPUT, DCCUPRCD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCUPRCD.DATE.EFF_DATE == 0) {
            DCCUPRCD.DATE.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        }
        CEP.TRC(SCCGWA, DCCUPRCD.TX_TYPE);
        CEP.TRC(SCCGWA, DCCUPRCD.DATA.KEY.IBS_AC_BK);
        CEP.TRC(SCCGWA, DCCUPRCD.DATA.VAL.PRDMO_CD);
        CEP.TRC(SCCGWA, DCCUPRCD.DATA.KEY.CD.PARM_CODE);
        CEP.TRC(SCCGWA, DCCUPRCD.DATE.EFF_DATE);
    }
    public void B200_INQ_PARM_CD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRMPRD);
        DCRMPRD.KEY.IBS_AC_BK = DCCUPRCD.DATA.KEY.IBS_AC_BK;
        DCRMPRD.KEY.PRDMO_CD = DCCUPRCD.DATA.VAL.PRDMO_CD;
        DCRMPRD.KEY.PARM_CODE = DCCUPRCD.DATA.KEY.CD.PARM_CODE;
        WS_EFF_DATE = DCCUPRCD.DATE.EFF_DATE;
        R000_STARTBR_FIRST_DCTMPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_MPRD_FLG);
        CEP.TRC(SCCGWA, DCRMPRD.KEY.IBS_AC_BK);
        CEP.TRC(SCCGWA, DCRMPRD.KEY.PRDMO_CD);
        CEP.TRC(SCCGWA, DCRMPRD.KEY.PARM_CODE);
        CEP.TRC(SCCGWA, WS_EFF_DATE);
        if (WS_MPRD_FLG == 'N') {
            CEP.TRC(SCCGWA, "--PARMCODE NOTFND--");
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PRD_REC_NOTFND, DCCUPRCD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "--PARMCODE FOUND--");
            DCCUPRCD.DATA.KEY.IBS_AC_BK = DCRMPRD.KEY.IBS_AC_BK;
            DCCUPRCD.DATA.VAL.PRDMO_CD = DCRMPRD.KEY.PRDMO_CD;
            DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCRMPRD.KEY.PARM_CODE;
            DCCUPRCD.DATE.EFF_DATE = DCRMPRD.KEY.EFF_DATE;
            DCCUPRCD.DATE.EXP_DATE = DCRMPRD.EXP_DATE;
            DCCUPRCD.DATA.CDESC = DCRMPRD.CDESC;
            DCCUPRCD.DATA.VAL.ADSC_TYPE = DCRMPRD.ADSC_TYPE;
            DCCUPRCD.DATA.VAL.DD_PROD = DCRMPRD.DD_PROD;
            DCCUPRCD.DATA.VAL.CARD_PHY_TYP = DCRMPRD.CARD_PHY_TYP;
            DCCUPRCD.DATA.VAL.JOIN_CUS_FLG = DCRMPRD.JOIN_CUS_FLG;
            DCCUPRCD.DATA.VAL.AC_HANG_FLG = DCRMPRD.AC_HANG_FLG;
            DCCUPRCD.DATA.VAL.SUP_CARD_FLG = DCRMPRD.SUP_CARD_FLG;
            DCCUPRCD.DATA.VAL.PSW_FLG = DCRMPRD.PSW_FLG;
            DCCUPRCD.DATA.VAL.MOBL_PAY_FLG = DCRMPRD.MOBL_PAY_FLG;
            DCCUPRCD.DATA.VAL.DFT_EXP = DCRMPRD.DFT_EXP;
            DCCUPRCD.DATA.VAL.SVRLT_CD = DCRMPRD.SVRLT_CD;
            DCCUPRCD.DATA.ADESC = DCRMPRD.ADESC;
            DCCUPRCD.DATA.VAL.IC_APP_TYP = DCRMPRD.IC_APP_TYP;
            DCCUPRCD.DATA.VAL.IC_APP_FLG = DCRMPRD.IC_APP_FLG;
            DCCUPRCD.DATA.VAL.IC_PROD_CD = DCRMPRD.IC_PROD_CD;
            DCCUPRCD.DATA.VAL.CARD_CLS = DCRMPRD.CARD_CLS;
            DCCUPRCD.DATA.VAL.SUP_CARD_NUM = DCRMPRD.SUP_CARD_NUM;
            DCCUPRCD.DATA.VAL.FACE_FLG = DCRMPRD.FACE_FLG;
            CEP.TRC(SCCGWA, DCCUPRCD.DATA);
            CEP.TRC(SCCGWA, DCCUPRCD.DATA.VAL.FACE_FLG);
        }
    }
    public void B300_ADD_PARM_CD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRMPRD);
        DCRMPRD.KEY.IBS_AC_BK = DCCUPRCD.DATA.KEY.IBS_AC_BK;
        DCRMPRD.KEY.PRDMO_CD = DCCUPRCD.DATA.VAL.PRDMO_CD;
        DCRMPRD.KEY.PARM_CODE = DCCUPRCD.DATA.KEY.CD.PARM_CODE;
        DCRMPRD.KEY.EFF_DATE = DCCUPRCD.DATE.EFF_DATE;
        DCRMPRD.EXP_DATE = DCCUPRCD.DATE.EXP_DATE;
        DCRMPRD.CDESC = DCCUPRCD.DATA.CDESC;
        DCRMPRD.ADSC_TYPE = DCCUPRCD.DATA.VAL.ADSC_TYPE;
        DCRMPRD.DD_PROD = DCCUPRCD.DATA.VAL.DD_PROD;
        DCRMPRD.CARD_PHY_TYP = DCCUPRCD.DATA.VAL.CARD_PHY_TYP;
        DCRMPRD.JOIN_CUS_FLG = DCCUPRCD.DATA.VAL.JOIN_CUS_FLG;
        DCRMPRD.AC_HANG_FLG = DCCUPRCD.DATA.VAL.AC_HANG_FLG;
        DCRMPRD.SUP_CARD_FLG = DCCUPRCD.DATA.VAL.SUP_CARD_FLG;
        DCRMPRD.PSW_FLG = DCCUPRCD.DATA.VAL.PSW_FLG;
        DCRMPRD.MOBL_PAY_FLG = DCCUPRCD.DATA.VAL.MOBL_PAY_FLG;
        DCRMPRD.DFT_EXP = DCCUPRCD.DATA.VAL.DFT_EXP;
        DCRMPRD.SVRLT_CD = DCCUPRCD.DATA.VAL.SVRLT_CD;
        DCRMPRD.ADESC = DCCUPRCD.DATA.ADESC;
        DCRMPRD.IC_APP_TYP = DCCUPRCD.DATA.VAL.IC_APP_TYP;
        DCRMPRD.IC_APP_FLG = DCCUPRCD.DATA.VAL.IC_APP_FLG;
        DCRMPRD.IC_PROD_CD = DCCUPRCD.DATA.VAL.IC_PROD_CD;
        DCRMPRD.CARD_CLS = DCCUPRCD.DATA.VAL.CARD_CLS;
        DCRMPRD.SUP_CARD_NUM = DCCUPRCD.DATA.VAL.SUP_CARD_NUM;
        DCRMPRD.FACE_FLG = DCCUPRCD.DATA.VAL.FACE_FLG;
        DCRMPRD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRMPRD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRMPRD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRMPRD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R000_WRITE_DCTMPRD();
        if (pgmRtn) return;
        DCCUPRCD.DATA.KEY.IBS_AC_BK = DCRMPRD.KEY.IBS_AC_BK;
        DCCUPRCD.DATA.VAL.PRDMO_CD = DCRMPRD.KEY.PRDMO_CD;
        DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCRMPRD.KEY.PARM_CODE;
        DCCUPRCD.DATE.EFF_DATE = DCRMPRD.KEY.EFF_DATE;
        DCCUPRCD.DATE.EXP_DATE = DCRMPRD.EXP_DATE;
        DCCUPRCD.DATA.CDESC = DCRMPRD.CDESC;
        DCCUPRCD.DATA.VAL.ADSC_TYPE = DCRMPRD.ADSC_TYPE;
        DCCUPRCD.DATA.VAL.DD_PROD = DCRMPRD.DD_PROD;
        DCCUPRCD.DATA.VAL.CARD_PHY_TYP = DCRMPRD.CARD_PHY_TYP;
        DCCUPRCD.DATA.VAL.JOIN_CUS_FLG = DCRMPRD.JOIN_CUS_FLG;
        DCCUPRCD.DATA.VAL.AC_HANG_FLG = DCRMPRD.AC_HANG_FLG;
        DCCUPRCD.DATA.VAL.SUP_CARD_FLG = DCRMPRD.SUP_CARD_FLG;
        DCCUPRCD.DATA.VAL.PSW_FLG = DCRMPRD.PSW_FLG;
        DCCUPRCD.DATA.VAL.MOBL_PAY_FLG = DCRMPRD.MOBL_PAY_FLG;
        DCCUPRCD.DATA.VAL.DFT_EXP = DCRMPRD.DFT_EXP;
        DCCUPRCD.DATA.VAL.SVRLT_CD = DCRMPRD.SVRLT_CD;
        DCCUPRCD.DATA.ADESC = DCRMPRD.ADESC;
        DCCUPRCD.DATA.VAL.IC_APP_TYP = DCRMPRD.IC_APP_TYP;
        DCCUPRCD.DATA.VAL.IC_APP_FLG = DCRMPRD.IC_APP_FLG;
        DCCUPRCD.DATA.VAL.IC_PROD_CD = DCRMPRD.IC_PROD_CD;
        DCCUPRCD.DATA.VAL.CARD_CLS = DCRMPRD.CARD_CLS;
        DCCUPRCD.DATA.VAL.SUP_CARD_NUM = DCRMPRD.SUP_CARD_NUM;
        DCCUPRCD.DATA.VAL.FACE_FLG = DCRMPRD.FACE_FLG;
        CEP.TRC(SCCGWA, DCCUPRCD.DATA);
        CEP.TRC(SCCGWA, DCCUPRCD.DATA.VAL.FACE_FLG);
    }
    public void B400_MOD_PARM_CD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRMPRD);
        DCRMPRD.KEY.IBS_AC_BK = DCCUPRCD.DATA.KEY.IBS_AC_BK;
        DCRMPRD.KEY.PRDMO_CD = DCCUPRCD.DATA.VAL.PRDMO_CD;
        DCRMPRD.KEY.PARM_CODE = DCCUPRCD.DATA.KEY.CD.PARM_CODE;
        WS_EFF_DATE = DCCUPRCD.DATE.EFF_DATE;
        R000_STARTBR_FIRST_DCTMPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_MPRD_FLG);
        if (WS_MPRD_FLG == 'N') {
            CEP.TRC(SCCGWA, "--PARMCODE NOTFND--");
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PRD_REC_NOTFND, DCCUPRCD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, DCCUPRCD.DATE.EFF_DATE);
            CEP.TRC(SCCGWA, DCRMPRD.KEY.EFF_DATE);
            if (DCCUPRCD.DATE.EFF_DATE == DCRMPRD.KEY.EFF_DATE) {
                CEP.TRC(SCCGWA, "--MODIFY PROC--");
                IBS.init(SCCGWA, DCRMPRD);
                DCRMPRD.KEY.IBS_AC_BK = DCCUPRCD.DATA.KEY.IBS_AC_BK;
                DCRMPRD.KEY.PRDMO_CD = DCCUPRCD.DATA.VAL.PRDMO_CD;
                DCRMPRD.KEY.PARM_CODE = DCCUPRCD.DATA.KEY.CD.PARM_CODE;
                DCRMPRD.KEY.EFF_DATE = DCCUPRCD.DATE.EFF_DATE;
                R000_READUPDATE_DCTMPRD();
                if (pgmRtn) return;
                DCRMPRD.EXP_DATE = DCCUPRCD.DATE.EXP_DATE;
                DCRMPRD.CDESC = DCCUPRCD.DATA.CDESC;
                DCRMPRD.ADSC_TYPE = DCCUPRCD.DATA.VAL.ADSC_TYPE;
                DCRMPRD.DD_PROD = DCCUPRCD.DATA.VAL.DD_PROD;
                DCRMPRD.CARD_PHY_TYP = DCCUPRCD.DATA.VAL.CARD_PHY_TYP;
                DCRMPRD.JOIN_CUS_FLG = DCCUPRCD.DATA.VAL.JOIN_CUS_FLG;
                DCRMPRD.AC_HANG_FLG = DCCUPRCD.DATA.VAL.AC_HANG_FLG;
                DCRMPRD.SUP_CARD_FLG = DCCUPRCD.DATA.VAL.SUP_CARD_FLG;
                DCRMPRD.PSW_FLG = DCCUPRCD.DATA.VAL.PSW_FLG;
                DCRMPRD.MOBL_PAY_FLG = DCCUPRCD.DATA.VAL.MOBL_PAY_FLG;
                DCRMPRD.DFT_EXP = DCCUPRCD.DATA.VAL.DFT_EXP;
                DCRMPRD.SVRLT_CD = DCCUPRCD.DATA.VAL.SVRLT_CD;
                DCRMPRD.ADESC = DCCUPRCD.DATA.ADESC;
                DCRMPRD.IC_APP_TYP = DCCUPRCD.DATA.VAL.IC_APP_TYP;
                DCRMPRD.IC_APP_FLG = DCCUPRCD.DATA.VAL.IC_APP_FLG;
                DCRMPRD.IC_PROD_CD = DCCUPRCD.DATA.VAL.IC_PROD_CD;
                DCRMPRD.CARD_CLS = DCCUPRCD.DATA.VAL.CARD_CLS;
                DCRMPRD.SUP_CARD_NUM = DCCUPRCD.DATA.VAL.SUP_CARD_NUM;
                DCRMPRD.FACE_FLG = DCCUPRCD.DATA.VAL.FACE_FLG;
                DCRMPRD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRMPRD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                R000_REWRITE_DCTMPRD();
                if (pgmRtn) return;
                DCCUPRCD.DATA.KEY.IBS_AC_BK = DCRMPRD.KEY.IBS_AC_BK;
                DCCUPRCD.DATA.VAL.PRDMO_CD = DCRMPRD.KEY.PRDMO_CD;
                DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCRMPRD.KEY.PARM_CODE;
                DCCUPRCD.DATE.EFF_DATE = DCRMPRD.KEY.EFF_DATE;
                DCCUPRCD.DATE.EXP_DATE = DCRMPRD.EXP_DATE;
                DCCUPRCD.DATA.CDESC = DCRMPRD.CDESC;
                DCCUPRCD.DATA.VAL.ADSC_TYPE = DCRMPRD.ADSC_TYPE;
                DCCUPRCD.DATA.VAL.DD_PROD = DCRMPRD.DD_PROD;
                DCCUPRCD.DATA.VAL.CARD_PHY_TYP = DCRMPRD.CARD_PHY_TYP;
                DCCUPRCD.DATA.VAL.JOIN_CUS_FLG = DCRMPRD.JOIN_CUS_FLG;
                DCCUPRCD.DATA.VAL.AC_HANG_FLG = DCRMPRD.AC_HANG_FLG;
                DCCUPRCD.DATA.VAL.SUP_CARD_FLG = DCRMPRD.SUP_CARD_FLG;
                DCCUPRCD.DATA.VAL.PSW_FLG = DCRMPRD.PSW_FLG;
                DCCUPRCD.DATA.VAL.MOBL_PAY_FLG = DCRMPRD.MOBL_PAY_FLG;
                DCCUPRCD.DATA.VAL.DFT_EXP = DCRMPRD.DFT_EXP;
                DCCUPRCD.DATA.VAL.SVRLT_CD = DCRMPRD.SVRLT_CD;
                DCCUPRCD.DATA.ADESC = DCRMPRD.ADESC;
                DCCUPRCD.DATA.VAL.IC_APP_TYP = DCRMPRD.IC_APP_TYP;
                DCCUPRCD.DATA.VAL.IC_APP_FLG = DCRMPRD.IC_APP_FLG;
                DCCUPRCD.DATA.VAL.IC_PROD_CD = DCRMPRD.IC_PROD_CD;
                DCCUPRCD.DATA.VAL.CARD_CLS = DCRMPRD.CARD_CLS;
                DCCUPRCD.DATA.VAL.SUP_CARD_NUM = DCRMPRD.SUP_CARD_NUM;
                DCCUPRCD.DATA.VAL.FACE_FLG = DCRMPRD.FACE_FLG;
                CEP.TRC(SCCGWA, DCCUPRCD.DATA);
                CEP.TRC(SCCGWA, DCCUPRCD.DATA.VAL.FACE_FLG);
            } else {
                CEP.TRC(SCCGWA, "--ADD A NEW RECORD--");
                B300_ADD_PARM_CD_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B500_DEL_PARM_CD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRMPRD);
        DCRMPRD.KEY.IBS_AC_BK = DCCUPRCD.DATA.KEY.IBS_AC_BK;
        DCRMPRD.KEY.PRDMO_CD = DCCUPRCD.DATA.VAL.PRDMO_CD;
        DCRMPRD.KEY.PARM_CODE = DCCUPRCD.DATA.KEY.CD.PARM_CODE;
        DCRMPRD.KEY.EFF_DATE = DCCUPRCD.DATE.EFF_DATE;
        R000_READUPDATE_DCTMPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_MPRD_FLG);
        if (WS_MPRD_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PRD_REC_NOTFND, DCCUPRCD.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, DCRMPRD.EXP_DATE);
            if (DCRMPRD.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_PRDREC_ALR_EXP, DCCUPRCD.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                DCRMPRD.EXP_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRMPRD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                DCRMPRD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                R000_REWRITE_DCTMPRD();
                if (pgmRtn) return;
                DCCUPRCD.DATA.KEY.IBS_AC_BK = DCRMPRD.KEY.IBS_AC_BK;
                DCCUPRCD.DATA.VAL.PRDMO_CD = DCRMPRD.KEY.PRDMO_CD;
                DCCUPRCD.DATA.KEY.CD.PARM_CODE = DCRMPRD.KEY.PARM_CODE;
                DCCUPRCD.DATE.EFF_DATE = DCRMPRD.KEY.EFF_DATE;
                DCCUPRCD.DATE.EXP_DATE = DCRMPRD.EXP_DATE;
                DCCUPRCD.DATA.CDESC = DCRMPRD.CDESC;
                DCCUPRCD.DATA.VAL.ADSC_TYPE = DCRMPRD.ADSC_TYPE;
                DCCUPRCD.DATA.VAL.DD_PROD = DCRMPRD.DD_PROD;
                DCCUPRCD.DATA.VAL.CARD_PHY_TYP = DCRMPRD.CARD_PHY_TYP;
                DCCUPRCD.DATA.VAL.JOIN_CUS_FLG = DCRMPRD.JOIN_CUS_FLG;
                DCCUPRCD.DATA.VAL.AC_HANG_FLG = DCRMPRD.AC_HANG_FLG;
                DCCUPRCD.DATA.VAL.SUP_CARD_FLG = DCRMPRD.SUP_CARD_FLG;
                DCCUPRCD.DATA.VAL.PSW_FLG = DCRMPRD.PSW_FLG;
                DCCUPRCD.DATA.VAL.MOBL_PAY_FLG = DCRMPRD.MOBL_PAY_FLG;
                DCCUPRCD.DATA.VAL.DFT_EXP = DCRMPRD.DFT_EXP;
                DCCUPRCD.DATA.VAL.SVRLT_CD = DCRMPRD.SVRLT_CD;
                DCCUPRCD.DATA.ADESC = DCRMPRD.ADESC;
                DCCUPRCD.DATA.VAL.IC_APP_TYP = DCRMPRD.IC_APP_TYP;
                DCCUPRCD.DATA.VAL.IC_APP_FLG = DCRMPRD.IC_APP_FLG;
                DCCUPRCD.DATA.VAL.IC_PROD_CD = DCRMPRD.IC_PROD_CD;
                DCCUPRCD.DATA.VAL.CARD_CLS = DCRMPRD.CARD_CLS;
                DCCUPRCD.DATA.VAL.SUP_CARD_NUM = DCRMPRD.SUP_CARD_NUM;
                DCCUPRCD.DATA.VAL.FACE_FLG = DCRMPRD.FACE_FLG;
                CEP.TRC(SCCGWA, DCCUPRCD.DATA);
                CEP.TRC(SCCGWA, DCCUPRCD.DATA.VAL.FACE_FLG);
            }
        }
    }
    public void R000_STARTBR_FIRST_DCTMPRD() throws IOException,SQLException,Exception {
        DCTMPRD_BR.rp = new DBParm();
        DCTMPRD_BR.rp.TableName = "DCTMPRD";
        DCTMPRD_BR.rp.where = "IBS_AC_BK = :DCRMPRD.KEY.IBS_AC_BK "
            + "AND PRDMO_CD = :DCRMPRD.KEY.PRDMO_CD "
            + "AND PARM_CODE = :DCRMPRD.KEY.PARM_CODE "
            + "AND EFF_DATE <= :WS_EFF_DATE "
            + "AND EXP_DATE > :WS_EFF_DATE";
        DCTMPRD_BR.rp.fst = true;
        DCTMPRD_BR.rp.order = "EFF_DATE DESC";
        IBS.STARTBR(SCCGWA, DCRMPRD, this, DCTMPRD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MPRD_FLG = 'Y';
        } else {
            WS_MPRD_FLG = 'N';
        }
    }
    public void R000_WRITE_DCTMPRD() throws IOException,SQLException,Exception {
        DCTMPRD_RD = new DBParm();
        DCTMPRD_RD.TableName = "DCTMPRD";
        IBS.WRITE(SCCGWA, DCRMPRD, DCTMPRD_RD);
    }
    public void R000_READ_DCTMPRD() throws IOException,SQLException,Exception {
        DCTMPRD_RD = new DBParm();
        DCTMPRD_RD.TableName = "DCTMPRD";
        IBS.READ(SCCGWA, DCRMPRD, DCTMPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MPRD_FLG = 'Y';
        } else {
            WS_MPRD_FLG = 'N';
        }
    }
    public void R000_READUPDATE_DCTMPRD() throws IOException,SQLException,Exception {
        DCTMPRD_RD = new DBParm();
        DCTMPRD_RD.TableName = "DCTMPRD";
        DCTMPRD_RD.upd = true;
        IBS.READ(SCCGWA, DCRMPRD, DCTMPRD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MPRD_FLG = 'Y';
        } else {
            WS_MPRD_FLG = 'N';
        }
    }
    public void R000_REWRITE_DCTMPRD() throws IOException,SQLException,Exception {
        DCTMPRD_RD = new DBParm();
        DCTMPRD_RD.TableName = "DCTMPRD";
        IBS.REWRITE(SCCGWA, DCRMPRD, DCTMPRD_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_FLD_NO);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
