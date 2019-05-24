package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5802 {
    String CPN_S_MIB_MAIN = "AI-S-MAIN-MIB  ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_RVS_EXP_DT = 0;
    int WS_CMIB_EXP_DT = 0;
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AICPQITM AICPQITM = new AICPQITM();
    AICPCMIB AICPCMIB = new AICPCMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCQCCY BPCQCCY = new BPCQCCY();
    AICSMIB AICSMIB = new AICSMIB();
    SCCCLDT SCCCLDT = new SCCCLDT();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    SCCGWA SCCGWA;
    SCCAWAC SCCAWAC;
    BPCPQBNK_DATA_INFO BPCRBANK;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB5800_AWA_5800 AIB5800_AWA_5800;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT5802 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5800_AWA_5800>");
        AIB5800_AWA_5800 = (AIB5800_AWA_5800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_UPD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.GL_BOOK);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.BR);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.ITM);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.SEQ);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.CCY);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.ITM_NM);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.AC_TYP);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.CCY_RNG);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.DC_CTL);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.BAL_OD);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.DTL_FLG);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.SUS_TYP);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.SW_TYP);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.SUS_TRM);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.SUS_UNIT);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.AC_EXPDT);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.ALW_AI);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.DIR);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.ONL_UPD);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.CARD_FLG);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.HOT_FLG);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.DR_LMT);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.CR_LMT);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.CHK_FLG);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.APP);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.APP1);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.APP2);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.APP3);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.APP4);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.APP5);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.APP6);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.APP7);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.APP8);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.APP9);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.STS);
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.ADDR);
        if (AIB5800_AWA_5800.GL_BOOK.trim().length() == 0 
            || AIB5800_AWA_5800.BR == 0 
            || AIB5800_AWA_5800.ITM.trim().length() == 0 
            || AIB5800_AWA_5800.SEQ == 0 
            || AIB5800_AWA_5800.CCY.trim().length() == 0 
            || AIB5800_AWA_5800.BAL_OD == ' ' 
            || AIB5800_AWA_5800.DTL_FLG == ' ' 
            || AIB5800_AWA_5800.SUS_TYP == ' ' 
            || AIB5800_AWA_5800.ALW_AI == ' ' 
            || AIB5800_AWA_5800.ONL_UPD == ' ' 
            || AIB5800_AWA_5800.CARD_FLG == ' ' 
            || AIB5800_AWA_5800.CHK_FLG == ' ') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, AIB5800_AWA_5800.AC_EXPDT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (AIB5800_AWA_5800.AC_EXPDT <= SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EXPDT_GT_ACDT;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.NO = AIB5800_AWA_5800.ITM;
        AICPQITM.INPUT_DATA.BOOK_FLG = AIB5800_AWA_5800.GL_BOOK;
        S000_CALL_AIZPQITM();
        if (AIB5800_AWA_5800.BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AIB5800_AWA_5800.BR;
            S000_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
            if (BPCPQORG.ATTR != '2') {
                if (BPCPQORG.ATTR == '3') {
                    if (BPCPQORG.BBR != 0) {
                        CEP.TRC(SCCGWA, BPCPQORG.BBR);
                        AIB5800_AWA_5800.BR = BPCPQORG.BBR;
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR);
                    }
                } else {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_NOT_BOOK_BR);
                }
            }
        }
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = AIB5800_AWA_5800.CCY;
        S000_CALL_BPZQCCY();
        IBS.init(SCCGWA, AIRCMIB);
        IBS.init(SCCGWA, AICPCMIB);
        AIRCMIB.KEY.GL_BOOK = AIB5800_AWA_5800.GL_BOOK;
        AIRCMIB.KEY.BR = AIB5800_AWA_5800.BR;
        AIRCMIB.KEY.ITM = AIB5800_AWA_5800.ITM;
        AIRCMIB.KEY.SEQ = AIB5800_AWA_5800.SEQ;
        CEP.TRC(SCCGWA, AIRCMIB);
        S000_CALL_AIZPCMIB();
        if (AICPCMIB.RETURN_INFO == 'F' 
            && AIRCMIB.KEY.BR != 999999 
            && AIRCMIB.STS != 'N') {
            IBS.init(SCCGWA, AICPCMIB);
            IBS.init(SCCGWA, AIRCMIB);
            AIRCMIB.KEY.GL_BOOK = AIB5800_AWA_5800.GL_BOOK;
            AIRCMIB.KEY.BR = 999999;
            AIRCMIB.KEY.ITM = AIB5800_AWA_5800.ITM;
            AIRCMIB.KEY.SEQ = AIB5800_AWA_5800.SEQ;
            S000_CALL_AIZPCMIB();
        }
        CEP.TRC(SCCGWA, "11111");
        if (AICPCMIB.RETURN_INFO == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            if (AIRCMIB.STS != 'N') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_CMIB_STS_WRONG;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, AIRCMIB.CCY_LMT);
            if (AIRCMIB.CCY_LMT == '1') {
                if (!AIB5800_AWA_5800.CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_LOCAL_CCY;
                    S000_ERR_MSG_PROC();
                }
            }
            if (AIRCMIB.CCY_LMT == '2') {
                if (AIB5800_AWA_5800.CCY.equalsIgnoreCase(BPCRBANK.LOC_CCY1)) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.CANOT_LOCAL_CCY;
                    S000_ERR_MSG_PROC();
                }
            }
            if (AIB5800_AWA_5800.AC_EXPDT > AIRCMIB.AC_EXP) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EXP_GL_CMIB;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, AIRCMIB.EFF_DATE);
            if (AIB5800_AWA_5800.AC_EXPDT <= AIRCMIB.EFF_DATE) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_EXPDT_GT_EFFDT;
                S000_ERR_MSG_PROC();
            }
            if (AIB5800_AWA_5800.DR_LMT > AIRCMIB.DRLT_BAL) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_DRLMT_OVER_CMIB;
                S000_ERR_MSG_PROC();
            }
            if (AIB5800_AWA_5800.CR_LMT > AIRCMIB.CRLT_BAL) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_CRLMT_OVER_CMIB;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, AIB5800_AWA_5800.SUS_TYP);
            CEP.TRC(SCCGWA, AIB5800_AWA_5800.SUS_TRM);
            CEP.TRC(SCCGWA, AIB5800_AWA_5800.SUS_UNIT);
            if (AIB5800_AWA_5800.SUS_TYP == 'C' 
                || AIB5800_AWA_5800.SUS_TYP == 'D') {
                if (AIB5800_AWA_5800.SUS_TRM != 0 
                    && AIB5800_AWA_5800.SUS_UNIT != ' ') {
                    IBS.init(SCCGWA, SCCCLDT);
                    SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
                    CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                    if (AIB5800_AWA_5800.SUS_UNIT == 'D') {
                        SCCCLDT.DAYS = AIB5800_AWA_5800.SUS_TRM;
                        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
                    } else {
                        SCCCLDT.MTHS = AIB5800_AWA_5800.SUS_TRM;
                        CEP.TRC(SCCGWA, SCCCLDT.MTHS);
                    }
                    CEP.TRC(SCCGWA, "22");
                    SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
                    SCSSCLDT1.MP(SCCGWA, SCCCLDT);
                    if (SCCCLDT.RC == 0) {
                        WS_RVS_EXP_DT = SCCCLDT.DATE2;
                    }
                } else {
                    WS_RVS_EXP_DT = 20991231;
                }
                CEP.TRC(SCCGWA, WS_RVS_EXP_DT);
                CEP.TRC(SCCGWA, AIB5800_AWA_5800.AC_EXPDT);
                if (WS_RVS_EXP_DT > AIB5800_AWA_5800.AC_EXPDT) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_RVS_TEM_GL_CMIB_EXP;
                    S000_ERR_MSG_PROC();
                }
                CEP.TRC(SCCGWA, AIRCMIB.RVS_EXP);
                CEP.TRC(SCCGWA, AIRCMIB.RVS_UNIT);
                if (AIRCMIB.RVS_EXP != 0 
                    && AIRCMIB.RVS_UNIT != ' ') {
                    IBS.init(SCCGWA, SCCCLDT);
                    SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
                    CEP.TRC(SCCGWA, SCCCLDT.DATE1);
                    if (AIRCMIB.RVS_UNIT == 'D') {
                        SCCCLDT.DAYS = AIRCMIB.RVS_EXP;
                        CEP.TRC(SCCGWA, SCCCLDT.DAYS);
                    } else {
                        SCCCLDT.MTHS = AIRCMIB.RVS_EXP;
                        CEP.TRC(SCCGWA, SCCCLDT.MTHS);
                    }
                    CEP.TRC(SCCGWA, "33");
                    SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
                    SCSSCLDT2.MP(SCCGWA, SCCCLDT);
                    if (SCCCLDT.RC == 0) {
                        WS_CMIB_EXP_DT = SCCCLDT.DATE2;
                    }
                } else {
                    WS_CMIB_EXP_DT = 20991231;
                }
                CEP.TRC(SCCGWA, WS_CMIB_EXP_DT);
                if (WS_RVS_EXP_DT > WS_CMIB_EXP_DT) {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_MIB_RVSDT_OVER_CMIB;
                    S000_ERR_MSG_PROC();
                }
            }
            if (AIB5800_AWA_5800.HOT_FLG != AIRCMIB.HOT_FLG) {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_HOTFLG_NOTEQ_CMIB);
            }
        }
    }
    public void B020_UPD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSMIB);
        AICSMIB.FUNC = 'A';
        AICSMIB.GL_BOOK = AIB5800_AWA_5800.GL_BOOK;
        AICSMIB.BR = AIB5800_AWA_5800.BR;
        AICSMIB.ITM_NO = AIB5800_AWA_5800.ITM;
        AICSMIB.SEQ = AIB5800_AWA_5800.SEQ;
        AICSMIB.CCY = AIB5800_AWA_5800.CCY;
        AICSMIB.NAME = AIB5800_AWA_5800.ITM_NM;
        AICSMIB.AC_TYP = AIB5800_AWA_5800.AC_TYP;
        AICSMIB.CCY_LMT = AIB5800_AWA_5800.CCY_RNG;
        AICSMIB.BAL_DIR = AIB5800_AWA_5800.DC_CTL;
        AICSMIB.BAL_RFLG = AIB5800_AWA_5800.BAL_OD;
        AICSMIB.DTL_FLG = AIB5800_AWA_5800.DTL_FLG;
        AICSMIB.RVS_TYP = AIB5800_AWA_5800.SUS_TYP;
        AICSMIB.RVS_KND = AIB5800_AWA_5800.SW_TYP;
        AICSMIB.RVS_EXP = AIB5800_AWA_5800.SUS_TRM;
        AICSMIB.RVS_UNIT = AIB5800_AWA_5800.SUS_UNIT;
        AICSMIB.AC_EXP = AIB5800_AWA_5800.AC_EXPDT;
        AICSMIB.MANUAL_FLG = AIB5800_AWA_5800.ALW_AI;
        AICSMIB.AMT_DIR = AIB5800_AWA_5800.DIR;
        AICSMIB.ONL_FLG = AIB5800_AWA_5800.ONL_UPD;
        AICSMIB.CARD_FLG = AIB5800_AWA_5800.CARD_FLG;
        AICSMIB.HOT_FLG = AIB5800_AWA_5800.HOT_FLG;
        AICSMIB.DRLT_BAL = AIB5800_AWA_5800.DR_LMT;
        AICSMIB.CRLT_BAL = AIB5800_AWA_5800.CR_LMT;
        AICSMIB.CHK_FLG = AIB5800_AWA_5800.CHK_FLG;
        AICSMIB.APP = AIB5800_AWA_5800.APP;
        AICSMIB.APP1 = AIB5800_AWA_5800.APP1;
        AICSMIB.APP2 = AIB5800_AWA_5800.APP2;
        AICSMIB.APP3 = AIB5800_AWA_5800.APP3;
        AICSMIB.APP4 = AIB5800_AWA_5800.APP4;
        AICSMIB.APP5 = AIB5800_AWA_5800.APP5;
        AICSMIB.APP6 = AIB5800_AWA_5800.APP6;
        AICSMIB.APP7 = AIB5800_AWA_5800.APP7;
        AICSMIB.APP8 = AIB5800_AWA_5800.APP8;
        AICSMIB.APP9 = AIB5800_AWA_5800.APP9;
        AICSMIB.STS = AIB5800_AWA_5800.STS;
        AICSMIB.ADDR = AIB5800_AWA_5800.ADDR;
        S000_CALL_AIZSMIB();
    }
    public void S000_CALL_AIZSMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_MIB_MAIN, AICSMIB);
        if (AICSMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICSMIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC);
        if (AICPQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZRCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-CMIB", AICRCMIB);
        CEP.TRC(SCCGWA, AICRCMIB.RC.RC_CODE);
        if (AICRCMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRCMIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZPCMIB() throws IOException,SQLException,Exception {
        AICPCMIB.POINTER = AIRCMIB;
        AICPCMIB.REC_LEN = 407;
        IBS.CALLCPN(SCCGWA, "AI-P-GET-CMIB", AICPCMIB);
        CEP.TRC(SCCGWA, AICPCMIB.RC.RC_CODE);
        CEP.TRC(SCCGWA, AICPCMIB.RETURN_INFO);
        if (AICPCMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPCMIB.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
