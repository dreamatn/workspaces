package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5750 {
    DBParm TDTBVT_RD;
    DBParm TDTCMST_RD;
    brParm TDTHBVT_BR = new brParm();
    DBParm TDTHBVT_RD;
    int WS_CNT = 0;
    short WS_SEQ = 0;
    String WS_AC_NO = " ";
    char WS_FUNC = ' ';
    char WS_BV_TYP = ' ';
    String WS_BV_NO = " ";
    String WS_BV_CD = " ";
    int WS_RCD_SEQ = 0;
    int WS_READ_CNT = 1;
    String K_OUTPUT_FMT = "TD575";
    int K_SCR_ROW_NO = 15;
    String WS_MSGID = " ";
    short WS_NUM = 0;
    short WS_NUM1 = 0;
    short WS_A = 0;
    short WS_PAGE_ROW_NO = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    int WS_P_ROW = 0;
    int WS_P_NUM = 0;
    char WS_TBL_FLAG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    CICQACAC CICQACAC = new CICQACAC();
    TDRHBVT TDRHBVT = new TDRHBVT();
    TDRCMST TDRCMST = new TDRCMST();
    TDRBVT TDRBVT = new TDRBVT();
    CICQACRI CICQACRI = new CICQACRI();
    TDCC575 TDCC575 = new TDCC575();
    SCCGWA SCCGWA;
    TDB5750_AWA_5750 TDB5750_AWA_5750;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5750 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5750_AWA_5750>");
        TDB5750_AWA_5750 = (TDB5750_AWA_5750) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCC575);
        B010_CHECK_INPUT();
        B020_GET_PRIMARY_KEY();
        B050_COUNT_TDTHBVT();
        B040_OUTPUT_DATA();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5750_AWA_5750.FUNC);
        CEP.TRC(SCCGWA, TDB5750_AWA_5750.BV_TYP);
        CEP.TRC(SCCGWA, TDB5750_AWA_5750.AC_NO);
        CEP.TRC(SCCGWA, TDB5750_AWA_5750.NUM);
        CEP.TRC(SCCGWA, TDB5750_AWA_5750.BV_NO);
        CEP.TRC(SCCGWA, TDB5750_AWA_5750.BV_CD);
        CEP.TRC(SCCGWA, TDB5750_AWA_5750.PAGE_ROW);
        CEP.TRC(SCCGWA, TDB5750_AWA_5750.PAGE_NUM);
        TDRBVT.KEY.AC_NO = TDB5750_AWA_5750.AC_NO;
        if (TDB5750_AWA_5750.FUNC == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_FUNC_ERR;
            S000_ERR_MSG_PROC();
        }
        if (TDB5750_AWA_5750.BV_TYP == ' ') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_BV_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (TDB5750_AWA_5750.AC_NO.trim().length() == 0) {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (TDB5750_AWA_5750.BV_CD.trim().length() == 0) {
            TDTBVT_RD = new DBParm();
            TDTBVT_RD.TableName = "TDTBVT";
            TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
            IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
            WS_BV_CD = TDRBVT.BV_CD;
        } else {
            WS_BV_CD = TDB5750_AWA_5750.BV_CD;
        }
        S000_CALL_CI_INQ_ACR_INF();
        if (CICQACRI.O_DATA.O_ENTY_TYP == '2') {
            if (TDB5750_AWA_5750.NUM == ' ') {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_MUST_IPT_AC_SEQ;
                S000_ERR_MSG_PROC();
            }
        } else {
            S000_CALL_TDTCMST();
            if (TDRCMST.BV_TYP == '0') {
                if (TDB5750_AWA_5750.NUM == ' ') {
                    WS_MSGID = TDCMSG_ERROR_MSG.TD_MUST_IPT_AC_SEQ;
                    S000_ERR_MSG_PROC();
                }
            } else {
                if ((TDB5750_AWA_5750.BV_TYP == '7' 
                    || TDB5750_AWA_5750.BV_TYP == '8') 
                    && TDB5750_AWA_5750.NUM == 0 
                    && TDB5750_AWA_5750.BV_NO.trim().length() == 0) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_MUST_IPT_AC_SEQ);
                }
            }
        }
    }
    public void B020_GET_PRIMARY_KEY() throws IOException,SQLException,Exception {
        if (TDB5750_AWA_5750.FUNC == '2' 
            && TDB5750_AWA_5750.BV_TYP != '1' 
            && TDB5750_AWA_5750.BV_TYP != '4') {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDB5750_AWA_5750.AC_NO;
            CICQACAC.DATA.AGR_SEQ = TDB5750_AWA_5750.NUM;
            CICQACAC.DATA.BV_NO = TDB5750_AWA_5750.BV_NO;
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.RC);
            if (CICQACAC.RC.RC_CODE != 0) {
                WS_MSGID = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, TDRBVT);
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        CEP.TRC(SCCGWA, TDB5750_AWA_5750.BV_NO);
        CEP.TRC(SCCGWA, TDB5750_AWA_5750.NUM);
        if (TDB5750_AWA_5750.BV_NO.trim().length() == 0) {
            WS_BV_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_BV_NO;
        } else {
            WS_BV_NO = TDB5750_AWA_5750.BV_NO;
        }
        if (TDB5750_AWA_5750.NUM == 0) {
            WS_SEQ = (short) CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        } else {
            WS_SEQ = (short) TDB5750_AWA_5750.NUM;
        }
        WS_AC_NO = TDB5750_AWA_5750.AC_NO;
        WS_BV_TYP = TDB5750_AWA_5750.BV_TYP;
        WS_FUNC = TDB5750_AWA_5750.FUNC;
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FUNC);
        CEP.TRC(SCCGWA, WS_AC_NO);
        CEP.TRC(SCCGWA, WS_BV_TYP);
        CEP.TRC(SCCGWA, WS_BV_NO);
        CEP.TRC(SCCGWA, WS_SEQ);
        CEP.TRC(SCCGWA, WS_BV_CD);
        if (TDRCMST.BV_TYP == '0' 
            && (TDB5750_AWA_5750.BV_TYP == '3' 
            || TDB5750_AWA_5750.BV_TYP == '7' 
            || TDB5750_AWA_5750.BV_TYP == '8')) {
            TDRHBVT.OAC = WS_AC_NO;
            TDRHBVT.OLD_BV_TYP = WS_BV_TYP;
            TDRHBVT.OAC_SEQ = WS_SEQ;
            TDRHBVT.OLD_BV_NO = WS_BV_NO;
            TDRHBVT.OLD_BV_CD = WS_BV_CD;
            if (0X00 == ' ') TDRHBVT.UPD_DATE = 0;
            else TDRHBVT.UPD_DATE = Integer.parseInt(""+0X00);
            if (0X00 == ' ') TDRHBVT.UPD_TIME = 0;
            else TDRHBVT.UPD_TIME = Integer.parseInt(""+0X00);
            T000_STARTBR_TDTHBVT_OLD();
            CEP.TRC(SCCGWA, "XXXXXXXXXXXX");
            CEP.TRC(SCCGWA, TDB5750_AWA_5750.PAGE_NUM);
            CEP.TRC(SCCGWA, WS_RCD_SEQ);
            T000_READNEXT_TDTHBVT_FIRST();
            WS_NUM = 0;
            WS_NUM1 = 0;
            CEP.TRC(SCCGWA, WS_TBL_FLAG);
            CEP.TRC(SCCGWA, WS_READ_CNT);
            R000_PAGE_COM();
            WS_NUM1 = 1;
            CEP.TRC(SCCGWA, WS_NUM1);
            while ((WS_TBL_FLAG != 'N' 
                && WS_NUM < 10)) {
                if (WS_NUM1 > WS_STR_NUM 
                    && WS_NUM1 <= WS_END_NUM) {
                    WS_NUM += 1;
                    R000_OUTPUT_DATA();
                    T000_READNEXT_TDTHBVT();
                }
                WS_NUM1 += 1;
                CEP.TRC(SCCGWA, "**********************");
                CEP.TRC(SCCGWA, WS_NUM);
            }
            if (WS_TBL_FLAG == 'N') {
                TDCC575.LAST_PAGE = 'Y';
            }
            if (WS_NUM == 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
            } else {
                TDCC575.CURR_PAGE = (short) TDB5750_AWA_5750.PAGE_NUM;
                TDCC575.PAGE_ROW = WS_NUM;
            }
            CEP.TRC(SCCGWA, TDCC575.LAST_PAGE);
            CEP.TRC(SCCGWA, TDCC575.CURR_PAGE);
            CEP.TRC(SCCGWA, TDCC575.PAGE_ROW);
            T000_ENDBR_TDTHBVT();
        } else {
            B050_COUNT_TDTHBVT();
        }
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRHBVT.KEY.NAC);
        CEP.TRC(SCCGWA, TDRHBVT.KEY.NAC_SEQ);
        CEP.TRC(SCCGWA, TDRHBVT.KEY.NEW_BV_NO);
        CEP.TRC(SCCGWA, TDRHBVT.KEY.NEW_BV_TYP);
        CEP.TRC(SCCGWA, TDRHBVT.KEY.NEW_BV_CD);
        CEP.TRC(SCCGWA, TDRHBVT.CHA_RMK);
        CEP.TRC(SCCGWA, TDRHBVT.CRT_DATE);
        TDCC575.DATA[WS_NUM-1].NAC = TDRHBVT.KEY.NAC;
        TDCC575.DATA[WS_NUM-1].NAC_SEQ = TDRHBVT.KEY.NAC_SEQ;
        TDCC575.DATA[WS_NUM-1].NEW_BV_NO = TDRHBVT.KEY.NEW_BV_NO;
        TDCC575.DATA[WS_NUM-1].NEW_BV_TYP = TDRHBVT.KEY.NEW_BV_TYP;
        TDCC575.DATA[WS_NUM-1].NEW_BV_CD = TDRHBVT.KEY.NEW_BV_CD;
        TDCC575.DATA[WS_NUM-1].CHA_RMK = TDRHBVT.CHA_RMK;
        TDCC575.DATA[WS_NUM-1].OAC = TDRHBVT.OAC;
        TDCC575.DATA[WS_NUM-1].OAC_SEQ = TDRHBVT.OAC_SEQ;
        TDCC575.DATA[WS_NUM-1].OLD_BV_NO = TDRHBVT.OLD_BV_NO;
        TDCC575.DATA[WS_NUM-1].OLD_BV_TYP = TDRHBVT.OLD_BV_TYP;
        TDCC575.DATA[WS_NUM-1].OLD_BV_CD = TDRHBVT.OLD_BV_CD;
        TDCC575.DATA[WS_NUM-1].UPD_BR = TDRHBVT.UPD_BR;
        TDCC575.DATA[WS_NUM-1].CRT_TLR = TDRHBVT.CRT_TLR;
        TDCC575.DATA[WS_NUM-1].CRT_DATE = TDRHBVT.CRT_DATE;
        TDCC575.DATA[WS_NUM-1].OWNER_BK = TDRHBVT.OWNER_BK;
        TDCC575.DATA[WS_NUM-1].UPD_TLT = TDRHBVT.UPD_TLT;
        TDCC575.DATA[WS_NUM-1].UPD_DATE = TDRHBVT.UPD_DATE;
        TDCC575.DATA[WS_NUM-1].UPD_TIME = TDRHBVT.UPD_TIME;
    }
    public void R000_PAGE_COM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5750_AWA_5750.PAGE_ROW);
        CEP.TRC(SCCGWA, TDB5750_AWA_5750.PAGE_NUM);
        if (TDB5750_AWA_5750.PAGE_ROW == 0) {
            TDB5750_AWA_5750.PAGE_ROW = 1;
        }
        if (TDB5750_AWA_5750.PAGE_NUM == 0) {
            TDB5750_AWA_5750.PAGE_NUM = 10;
        } else {
            if (TDB5750_AWA_5750.PAGE_NUM > 10) {
                TDB5750_AWA_5750.PAGE_NUM = 10;
            }
        }
        WS_P_ROW = TDB5750_AWA_5750.PAGE_NUM;
        WS_P_NUM = TDB5750_AWA_5750.PAGE_NUM - 1;
        CEP.TRC(SCCGWA, WS_P_NUM);
        WS_STR_NUM = WS_P_NUM * WS_P_ROW;
        CEP.TRC(SCCGWA, WS_STR_NUM);
        WS_END_NUM = TDB5750_AWA_5750.PAGE_ROW * WS_P_ROW;
        CEP.TRC(SCCGWA, WS_STR_NUM);
        CEP.TRC(SCCGWA, WS_END_NUM);
    }
    public void S000_CALL_CI_INQ_ACR_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDB5750_AWA_5750.AC_NO;
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDTCMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :WS_AC_NO";
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
    }
    public void B040_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "***********");
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCC575;
        SCCFMT.DATA_LEN = 1827;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_COUNT_TDTHBVT() throws IOException,SQLException,Exception {
        if (TDB5750_AWA_5750.FUNC == '1') {
            TDRHBVT.OAC = WS_AC_NO;
            TDRHBVT.OLD_BV_TYP = WS_BV_TYP;
            TDRHBVT.OAC_SEQ = WS_SEQ;
            TDRHBVT.OLD_BV_NO = WS_BV_NO;
            TDRHBVT.OLD_BV_CD = WS_BV_CD;
            TDRHBVT.KEY.CHG_SEQ = 0;
            R000_PAGE_COM();
            WS_NUM1 = 1;
            CEP.TRC(SCCGWA, TDRHBVT.OAC);
            CEP.TRC(SCCGWA, TDRHBVT.OLD_BV_TYP);
            CEP.TRC(SCCGWA, TDRHBVT.OAC_SEQ);
            CEP.TRC(SCCGWA, TDRHBVT.OLD_BV_NO);
            CEP.TRC(SCCGWA, TDRHBVT.OLD_BV_CD);
            T000_READ_TDTHBVT_O_FIRST();
            CEP.TRC(SCCGWA, TDRHBVT.KEY.CHG_SEQ);
            while ((WS_TBL_FLAG != 'N' 
                && WS_NUM < 10)) {
                if (WS_NUM1 > WS_STR_NUM 
                    && WS_NUM1 <= WS_END_NUM) {
                    WS_NUM += 1;
                    R000_OUTPUT_DATA();
                }
                WS_NUM1 += 1;
                TDRHBVT.OAC = TDRHBVT.KEY.NAC;
                TDRHBVT.OAC_SEQ = TDRHBVT.KEY.NAC_SEQ;
                TDRHBVT.OLD_BV_TYP = TDRHBVT.KEY.NEW_BV_TYP;
                TDRHBVT.OLD_BV_NO = TDRHBVT.KEY.NEW_BV_NO;
                TDRHBVT.OLD_BV_CD = TDRHBVT.KEY.NEW_BV_CD;
                WS_NUM1 += 1;
                T000_READ_TDTHBVT_O();
            }
            if (WS_TBL_FLAG == 'N') {
                TDCC575.LAST_PAGE = 'Y';
            }
            if (WS_NUM == 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
            } else {
                TDCC575.CURR_PAGE = (short) TDB5750_AWA_5750.PAGE_NUM;
                TDCC575.PAGE_ROW = WS_NUM;
            }
            CEP.TRC(SCCGWA, TDCC575.LAST_PAGE);
            CEP.TRC(SCCGWA, TDCC575.CURR_PAGE);
            CEP.TRC(SCCGWA, TDCC575.PAGE_ROW);
        } else if (TDB5750_AWA_5750.FUNC == '2') {
            TDRHBVT.KEY.NAC = WS_AC_NO;
            TDRHBVT.KEY.NEW_BV_TYP = WS_BV_TYP;
            TDRHBVT.KEY.NAC_SEQ = WS_SEQ;
            TDRHBVT.KEY.NEW_BV_NO = WS_BV_NO;
            TDRHBVT.KEY.NEW_BV_CD = WS_BV_CD;
            TDRHBVT.KEY.CHG_SEQ = 999999;
            R000_PAGE_COM();
            WS_NUM1 = 1;
            CEP.TRC(SCCGWA, TDRHBVT.KEY.NAC);
            CEP.TRC(SCCGWA, TDRHBVT.KEY.NEW_BV_TYP);
            CEP.TRC(SCCGWA, TDRHBVT.KEY.NAC_SEQ);
            CEP.TRC(SCCGWA, TDRHBVT.KEY.NEW_BV_NO);
            CEP.TRC(SCCGWA, TDRHBVT.KEY.NEW_BV_CD);
            T000_READ_TDTHBVT_D();
            CEP.TRC(SCCGWA, TDRHBVT.KEY.CHG_SEQ);
            while ((WS_TBL_FLAG != 'N' 
                && WS_NUM < 10)) {
                if (WS_NUM1 > WS_STR_NUM 
                    && WS_NUM1 <= WS_END_NUM) {
                    WS_NUM += 1;
                    R000_OUTPUT_DATA();
                }
                CEP.TRC(SCCGWA, TDRHBVT.OAC_SEQ);
                TDRHBVT.KEY.NAC = TDRHBVT.OAC;
                TDRHBVT.KEY.NAC_SEQ = TDRHBVT.OAC_SEQ;
                TDRHBVT.KEY.NEW_BV_TYP = TDRHBVT.OLD_BV_TYP;
                TDRHBVT.KEY.NEW_BV_NO = TDRHBVT.OLD_BV_NO;
                TDRHBVT.KEY.NEW_BV_CD = TDRHBVT.OLD_BV_CD;
                TDRHBVT.KEY.CHG_SEQ += 1;
                CEP.TRC(SCCGWA, TDRHBVT.KEY.CHG_SEQ);
                if (TDRHBVT.KEY.CHG_SEQ != 0) {
                    TDRHBVT.KEY.CHG_SEQ = TDRHBVT.KEY.CHG_SEQ - 1;
                }
                T000_READ_TDTHBVT_D();
            }
            if (WS_TBL_FLAG == 'N') {
                TDCC575.LAST_PAGE = 'Y';
            }
            if (WS_NUM == 0) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
            } else {
                TDCC575.CURR_PAGE = (short) TDB5750_AWA_5750.PAGE_NUM;
                TDCC575.PAGE_ROW = WS_NUM;
            }
            CEP.TRC(SCCGWA, TDCC575.LAST_PAGE);
            CEP.TRC(SCCGWA, TDCC575.CURR_PAGE);
            CEP.TRC(SCCGWA, TDCC575.PAGE_ROW);
    }
    public void T000_STARTBR_TDTHBVT_OLD() throws IOException,SQLException,Exception {
        TDTHBVT_BR.rp = new DBParm();
        TDTHBVT_BR.rp.TableName = "TDTHBVT";
        TDTHBVT_BR.rp.where = "OAC = :TDRHBVT.OAC "
            + "AND OAC_SEQ = :TDRHBVT.OAC_SEQ "
            + "AND UPD_DATE > :TDRHBVT.UPD_DATE "
            + "AND UPD_TIME > :TDRHBVT.UPD_TIME";
        TDTHBVT_BR.rp.order = "UPD_DATE,UPD_TIME DESC";
        IBS.STARTBR(SCCGWA, TDRHBVT, this, TDTHBVT_BR);
        CEP.TRC(SCCGWA, TDRHBVT.OLD_BV_TYP);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.TRC(SCCGWA, TDRHBVT.OAC);
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void T000_READ_TDTHBVT_O_FIRST() throws IOException,SQLException,Exception {
        TDTHBVT_RD = new DBParm();
        TDTHBVT_RD.TableName = "TDTHBVT";
        TDTHBVT_RD.where = "OAC = :TDRHBVT.OAC "
            + "AND ( ( OAC_SEQ = :TDRHBVT.OAC_SEQ ) "
            + "OR ( OLD_BV_TYP = :TDRHBVT.OLD_BV_TYP "
            + "AND OLD_BV_NO = :TDRHBVT.OLD_BV_NO "
            + "AND OLD_BV_CD = :TDRHBVT.OLD_BV_CD ) ) "
            + "AND CHG_SEQ >= :TDRHBVT.KEY.CHG_SEQ";
        TDTHBVT_RD.fst = true;
        TDTHBVT_RD.order = "CHG_SEQ";
        IBS.READ(SCCGWA, TDRHBVT, this, TDTHBVT_RD);
    }
    public void T000_READ_TDTHBVT_O() throws IOException,SQLException,Exception {
        TDTHBVT_RD = new DBParm();
        TDTHBVT_RD.TableName = "TDTHBVT";
        TDTHBVT_RD.where = "OAC = :TDRHBVT.OAC "
            + "AND OAC_SEQ = :TDRHBVT.OAC_SEQ "
            + "AND OLD_BV_TYP = :TDRHBVT.OLD_BV_TYP "
            + "AND OLD_BV_NO = :TDRHBVT.OLD_BV_NO "
            + "AND OLD_BV_CD = :TDRHBVT.OLD_BV_CD "
            + "AND CHG_SEQ >= :TDRHBVT.KEY.CHG_SEQ";
        TDTHBVT_RD.fst = true;
        TDTHBVT_RD.order = "CHG_SEQ";
        IBS.READ(SCCGWA, TDRHBVT, this, TDTHBVT_RD);
        CEP.TRC(SCCGWA, TDRHBVT.OLD_BV_TYP);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_TBL_FLAG = 'N';
                CEP.TRC(SCCGWA, "XXXXXXXXX");
                CEP.TRC(SCCGWA, TDRHBVT.OAC);
            } else {
                CEP.TRC(SCCGWA, TDRHBVT.OAC);
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
            }
        }
    }
    public void T000_READ_TDTHBVT_D() throws IOException,SQLException,Exception {
        TDTHBVT_RD = new DBParm();
        TDTHBVT_RD.TableName = "TDTHBVT";
        TDTHBVT_RD.where = "NAC = :TDRHBVT.KEY.NAC "
            + "AND NAC_SEQ = :TDRHBVT.KEY.NAC_SEQ "
            + "AND NEW_BV_TYP = :TDRHBVT.KEY.NEW_BV_TYP "
            + "AND NEW_BV_NO = :TDRHBVT.KEY.NEW_BV_NO "
            + "AND NEW_BV_CD = :TDRHBVT.KEY.NEW_BV_CD "
            + "AND CHG_SEQ <= :TDRHBVT.KEY.CHG_SEQ";
        TDTHBVT_RD.fst = true;
        TDTHBVT_RD.order = "CHG_SEQ DESC";
        IBS.READ(SCCGWA, TDRHBVT, this, TDTHBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_TBL_FLAG = 'N';
            } else {
                CEP.TRC(SCCGWA, TDRHBVT.OAC);
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
            }
        }
    }
    public void T000_STARTBR_TDTHBVT_NEW() throws IOException,SQLException,Exception {
        TDTHBVT_BR.rp = new DBParm();
        TDTHBVT_BR.rp.TableName = "TDTHBVT";
        TDTHBVT_BR.rp.where = "NAC = :TDRHBVT.KEY.NAC "
            + "AND NEW_BV_TYP = :TDRHBVT.KEY.NEW_BV_TYP "
            + "AND NAC_SEQ = :TDRHBVT.KEY.NAC_SEQ "
            + "AND NEW_BV_NO = :TDRHBVT.KEY.NEW_BV_NO "
            + "AND NEW_BV_CD = :TDRHBVT.KEY.NEW_BV_CD";
        IBS.STARTBR(SCCGWA, TDRHBVT, this, TDTHBVT_BR);
        CEP.TRC(SCCGWA, TDRHBVT.KEY.NAC);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
            CEP.TRC(SCCGWA, TDRHBVT.KEY.NAC_SEQ);
        }
    }
    public void T000_READNEXT_TDTHBVT_FIRST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRHBVT, this, TDTHBVT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void T000_READNEXT_TDTHBVT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRHBVT, this, TDTHBVT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DATE_MISSING);
        }
    }
    public void T000_ENDBR_TDTHBVT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTHBVT_BR);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
