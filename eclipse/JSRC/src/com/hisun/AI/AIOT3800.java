package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT3800 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm AITMIB_BR = new brParm();
    DBParm AITMIBA_RD;
    String K_OUTPUT_FMT = "AI380";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_BR = 0;
    char WS_GRANT = ' ';
    int WS_CNT = 0;
    char WS_SUCC = ' ';
    AIOT3800_WS_STR_POS WS_STR_POS = new AIOT3800_WS_STR_POS();
    char WS_AIRMIB_FLG = ' ';
    char WS_MIBA_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    AICSMIB AICSMIB = new AICSMIB();
    AIRMIB AIRMIB = new AIRMIB();
    BPCPQORG BPCPQORG = new BPCPQORG();
    AICPQITM AICPQITM = new AICPQITM();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    BPCPORLO BPCPORLO = new BPCPORLO();
    AICF380 AICF380 = new AICF380();
    AIRMIBH AIRMIBH = new AIRMIBH();
    AICRMIBH AICRMIBH = new AICRMIBH();
    AICRONA AICRONA = new AICRONA();
    AIRONA AIRONA = new AIRONA();
    AIRMIBA AIRMIBA = new AIRMIBA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    AIB3800_AWA_3800 AIB3800_AWA_3800;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "AIOT3800 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB3800_AWA_3800>");
        AIB3800_AWA_3800 = (AIB3800_AWA_3800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_BRW_REC_PROC();
        B210_OUTPUT_PROC();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (AIB3800_AWA_3800.GL_BOOK.trim().length() == 0 
            || AIB3800_AWA_3800.BR == 0) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.MUST_INPUT);
        }
        if (AIB3800_AWA_3800.ITM.trim().length() > 0) {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.NO = AIB3800_AWA_3800.ITM;
            AICPQITM.INPUT_DATA.BOOK_FLG = AIB3800_AWA_3800.GL_BOOK;
            S00_CALL_AIZPQITM();
        }
        if (AIB3800_AWA_3800.BR != 0 
            && AIB3800_AWA_3800.BR != 999999) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = AIB3800_AWA_3800.BR;
            S00_CALL_BPZPQORG();
            CEP.TRC(SCCGWA, BPCPQORG.ATTR);
            if (BPCPQORG.ATTR != '2') {
                if (BPCPQORG.ATTR == '3') {
                    if (BPCPQORG.BBR != 0) {
                        CEP.TRC(SCCGWA, BPCPQORG.BBR);
                        AIB3800_AWA_3800.BR = BPCPQORG.BBR;
                        if (AIB3800_AWA_3800.AC == null) AIB3800_AWA_3800.AC = "";
                        JIBS_tmp_int = AIB3800_AWA_3800.AC.length();
                        for (int i=0;i<32-JIBS_tmp_int;i++) AIB3800_AWA_3800.AC += " ";
                        JIBS_tmp_str[0] = "" + BPCPQORG.BBR;
                        JIBS_tmp_int = JIBS_tmp_str[0].length();
                        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                        AIB3800_AWA_3800.AC = JIBS_tmp_str[0] + AIB3800_AWA_3800.AC.substring(9);
                    } else {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR);
                    }
                } else {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_NOT_BOOK_BR);
                }
            }
        }
        if (AIB3800_AWA_3800.ITM.trim().length() > 0 
            && AIB3800_AWA_3800.SEQ != 0) {
            IBS.init(SCCGWA, AIRCMIB);
            IBS.init(SCCGWA, AICRCMIB);
            AICRCMIB.FUNC = 'F';
            AIRCMIB.KEY.GL_BOOK = AIB3800_AWA_3800.GL_BOOK;
            AIRCMIB.KEY.BR = AIB3800_AWA_3800.BR;
            AIRCMIB.KEY.ITM = AIB3800_AWA_3800.ITM;
            AIRCMIB.KEY.SEQ = AIB3800_AWA_3800.SEQ;
            AICRCMIB.POINTER = AIRCMIB;
            AICRCMIB.REC_LEN = 407;
            S000_CALL_AIZRCMIB();
            if (AICRCMIB.RETURN_INFO == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND);
            }
        }
        if (AIB3800_AWA_3800.STR_POS.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, AIB3800_AWA_3800.STR_POS, WS_STR_POS);
            AIB3800_AWA_3800.AC = WS_STR_POS.WS_STR_AC;
        }
        if (AIB3800_AWA_3800.AC.trim().length() > 0) {
            AICRONA.FUNC = 'B';
            AIRONA.KEY.OAC_NO = AIB3800_AWA_3800.AC;
            S000_CALL_AIZRONA();
            if (AIB3800_AWA_3800.AC == null) AIB3800_AWA_3800.AC = "";
            JIBS_tmp_int = AIB3800_AWA_3800.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AIB3800_AWA_3800.AC += " ";
            if (AIB3800_AWA_3800.AC == null) AIB3800_AWA_3800.AC = "";
            JIBS_tmp_int = AIB3800_AWA_3800.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) AIB3800_AWA_3800.AC += " ";
            if ((AIB3800_AWA_3800.AC.substring(25 - 1, 25 + 1 - 1).trim().length() == 0 
                || AIB3800_AWA_3800.AC.substring(26 - 1, 26 + 1 - 1).trim().length() > 0) 
                && AICRONA.RETURN_INFO == 'N') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.NOT_INVALID_ACCOUNT);
            }
            if (AICRONA.RETURN_INFO == 'F') {
                AIB3800_AWA_3800.AC = AIRONA.AC_NO;
                AIB3800_AWA_3800.CCY = AIRONA.CCY;
            }
        }
    }
    public void B200_BRW_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRMIB);
        WS_CNT = 0;
        IBS.init(SCCGWA, AICF380);
        CEP.TRC(SCCGWA, AIB3800_AWA_3800.GL_BOOK);
        CEP.TRC(SCCGWA, AIB3800_AWA_3800.ITM);
        CEP.TRC(SCCGWA, AIB3800_AWA_3800.BR);
        CEP.TRC(SCCGWA, AIB3800_AWA_3800.SEQ);
        CEP.TRC(SCCGWA, AIB3800_AWA_3800.CCY);
        CEP.TRC(SCCGWA, AIB3800_AWA_3800.AC);
        AIRMIB.KEY.GL_BOOK = AIB3800_AWA_3800.GL_BOOK;
        AIRMIB.KEY.ITM_NO = AIB3800_AWA_3800.ITM;
        AIRMIB.KEY.BR = AIB3800_AWA_3800.BR;
        AIRMIB.KEY.SEQ = AIB3800_AWA_3800.SEQ;
        AIRMIB.KEY.CCY = AIB3800_AWA_3800.CCY;
        AIRMIB.AC_NO = AIB3800_AWA_3800.AC;
        if (AIB3800_AWA_3800.STR_POS.trim().length() > 0) {
            T000_STARTBR_AITMIB_BY_AC();
        } else {
            T000_STARTBR_AITMIB();
        }
        T000_FETCH_AITMIB();
        while (WS_AIRMIB_FLG != 'N' 
            && WS_CNT <= 30) {
            IBS.init(SCCGWA, AIRMIBA);
            AIRMIBA.KEY.BOOK_FLG = AIRMIB.KEY.GL_BOOK;
            AIRMIBA.KEY.AC = AIRMIB.AC_NO;
            T000_READ_AITMIBA_FIRST();
            if (WS_MIBA_FLG == 'Y') {
                AIRMIB.LBAL = AIRMIBA.L_BAL;
            }
            CEP.TRC(SCCGWA, AIRMIB.HOT_FLG);
            if (AIRMIB.HOT_FLG == 'H') {
                IBS.init(SCCGWA, AIRMIBH);
                IBS.init(SCCGWA, AICRMIBH);
                AIRMIBH.KEY.GL_BOOK = AIRMIB.KEY.GL_BOOK;
                AIRMIBH.KEY.BR = AIRMIB.KEY.BR;
                AIRMIBH.KEY.CCY = AIRMIB.KEY.CCY;
                AIRMIBH.KEY.ITM_NO = AIRMIB.KEY.ITM_NO;
                AIRMIBH.KEY.SEQ = AIRMIB.KEY.SEQ;
                CEP.TRC(SCCGWA, AIRMIBH.KEY.GL_BOOK);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.BR);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.CCY);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.ITM_NO);
                CEP.TRC(SCCGWA, AIRMIBH.KEY.SEQ);
                AICRMIBH.FUNC = 'S';
                AICRMIBH.POINTER = AIRMIBH;
                AICRMIBH.REC_LEN = 637;
                S000_CALL_AIZRMIBH();
                if (AICRMIBH.RETURN_INFO == 'F') {
                    CEP.TRC(SCCGWA, AICRMIBH.LBAL_SUM);
                    CEP.TRC(SCCGWA, AICRMIBH.CBAL_SUM);
                    AIRMIB.CBAL = AICRMIBH.CBAL_SUM;
                }
            }
            R100_TRANS_DATA_OUPUT();
            T000_FETCH_AITMIB();
        }
        T000_CLOSE_AITMIB();
        if (WS_AIRMIB_FLG == 'N' 
            && WS_CNT == 0) {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITMIB_NOTFND);
        }
    }
    public void S000_CALL_AIZRONA() throws IOException,SQLException,Exception {
        AICRONA.POINTER = AIRONA;
        AICRONA.REC_LEN = 82;
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-ONA", AICRONA);
        CEP.TRC(SCCGWA, AICRONA.RC);
        CEP.TRC(SCCGWA, AICRONA.RETURN_INFO);
    }
    public void B210_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        if (WS_AIRMIB_FLG == 'N') {
            AICF380.END_FLG = 'Y';
        }
        SCCFMT.DATA_PTR = AICF380;
        SCCFMT.DATA_LEN = 7710;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R100_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        AICF380.DATA[WS_CNT-1].BR_AC = AIRMIB.AC_NO;
        AICF380.DATA[WS_CNT-1].BR = AIRMIB.KEY.BR;
        AICF380.DATA[WS_CNT-1].ITM = AIRMIB.KEY.ITM_NO;
        AICF380.DATA[WS_CNT-1].AC_SEQ = AIRMIB.KEY.SEQ;
        AICF380.DATA[WS_CNT-1].CCY = AIRMIB.KEY.CCY;
        AICF380.DATA[WS_CNT-1].LBAL_S = AIRMIB.LBAL;
        AICF380.DATA[WS_CNT-1].TBAL_S = AIRMIB.CBAL;
        AICF380.DATA[WS_CNT-1].AC_STS = AIRMIB.STS;
        if (AIRMIB.CHS_NM.trim().length() > 0) {
            AICF380.DATA[WS_CNT-1].ITM_NM = AIRMIB.CHS_NM;
        } else {
            AICF380.DATA[WS_CNT-1].ITM_NM = AIRCMIB.CHS_NM;
        }
        AICF380.DATA[WS_CNT-1].DR_CR = AIRMIB.BAL_DIR;
        AICF380.TOD_REC_NUM = WS_CNT;
        AICF380.END_POS = AIRMIB.AC_NO;
        AICF380.END_FLG = 'N';
    }
    public void T000_STARTBR_AITMIB() throws IOException,SQLException,Exception {
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "GL_BOOK = :AIRMIB.KEY.GL_BOOK "
            + "AND BR = :AIRMIB.KEY.BR "
            + "AND ( 0 = :AIRMIB.KEY.ITM_NO "
            + "OR ITM_NO = :AIRMIB.KEY.ITM_NO ) "
            + "AND ( ' ' = :AIRMIB.KEY.CCY "
            + "OR CCY = :AIRMIB.KEY.CCY ) "
            + "AND ( ' ' = :AIRMIB.KEY.SEQ "
            + "OR SEQ = :AIRMIB.KEY.SEQ ) "
            + "AND ( 0 = :AIRMIB.AC_NO "
            + "OR AC_NO = :AIRMIB.AC_NO )";
        AITMIB_BR.rp.order = "BR,ITM_NO,CCY,SEQ,AC_NO";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "OPEN AITMIB ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READ_AITMIBA_FIRST() throws IOException,SQLException,Exception {
        AITMIBA_RD = new DBParm();
        AITMIBA_RD.TableName = "AITMIBA";
        AITMIBA_RD.where = "BOOK_FLG = :AIRMIBA.KEY.BOOK_FLG "
            + "AND AC = :AIRMIBA.KEY.AC";
        AITMIBA_RD.fst = true;
        AITMIBA_RD.order = "AC_DT DESC";
        IBS.READ(SCCGWA, AIRMIBA, this, AITMIBA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIBA_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MIBA_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIBA";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
        }
    }
    public void T000_STARTBR_AITMIB_BY_AC() throws IOException,SQLException,Exception {
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "GL_BOOK = :AIRMIB.KEY.GL_BOOK "
            + "AND BR = :AIRMIB.KEY.BR "
            + "AND ( 0 = :AIRMIB.KEY.ITM_NO "
            + "OR ITM_NO = :AIRMIB.KEY.ITM_NO ) "
            + "AND ( ' ' = :AIRMIB.KEY.CCY "
            + "OR CCY = :AIRMIB.KEY.CCY ) "
            + "AND ( ' ' = :AIRMIB.KEY.SEQ "
            + "OR SEQ = :AIRMIB.KEY.SEQ ) "
            + "AND ( 0 = :AIRMIB.AC_NO "
            + "OR AC_NO > :AIRMIB.AC_NO )";
        AITMIB_BR.rp.order = "BR,ITM_NO,CCY,SEQ,AC_NO";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "OPEN AITMIB ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_FETCH_AITMIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRMIB, this, AITMIB_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AIRMIB_FLG = 'Y';
            WS_CNT += 1;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AIRMIB_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READNEXT AITMIB ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_CLOSE_AITMIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITMIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "CLOSE AITCMIB ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void S000_CALL_AIZRCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-CMIB", AICRCMIB);
        if (AICRCMIB.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICRCMIB.RC);
        }
    }
    public void S00_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        if (AICPQITM.RC.RTNCODE != 0) {
            CEP.ERR(SCCGWA, AICPQITM.RC);
        }
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_AIZRMIBH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-MIBH", AICRMIBH);
        CEP.TRC(SCCGWA, AICRMIBH.RC);
        CEP.TRC(SCCGWA, AICRMIBH.RC.RC_CODE);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
