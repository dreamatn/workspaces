package com.hisun.AI;

import com.hisun.SC.*;
import com.hisun.SL.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIOT5171 {
    DBParm AITCMIB_RD;
    DBParm AITITAD_RD;
    brParm AITCMIB_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short WS_FLD_NO = 0;
    String WS_ERR_MSG = " ";
    String WS_ERR_INF = " ";
    int WS_SEQ = 0;
    AIOT5171_WS_DATA WS_DATA = new AIOT5171_WS_DATA();
    AIOT5171_WS_CONSTANT WS_CONSTANT = new AIOT5171_WS_CONSTANT();
    String WS_ITM_NO = " ";
    String WS_COA_FLG = " ";
    char WS_FUNC_FLG = ' ';
    String WS_DATA_TAD = " ";
    String WS_ITMD_OLD = " ";
    String WS_ITMD_NEW = " ";
    String WS_DATA_OLD = " ";
    String WS_DATA_NEW = " ";
    int WS_PROC_DATE = 0;
    char WS_OTH_STS = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    AICSITAD AICSITAD = new AICSITAD();
    AIRITAD AIRITAD = new AIRITAD();
    AICPQITM AICQITM = new AICPQITM();
    AICPIBTM AICPIBTM = new AICPIBTM();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AICRCMIB AICRCMIB = new AICRCMIB();
    AICPCMIB AICPCMIB = new AICPCMIB();
    SLCISAC SLCISAC = new SLCISAC();
    BPCPCMWD BPCPCMWD = new BPCPCMWD();
    SCCGWA SCCGWA;
    AIB5171_AWA_5171 AIB5171_AWA_5171;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "BB11");
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIOT5171 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "AIB5171_AWA_5171>");
        AIB5171_AWA_5171 = (AIB5171_AWA_5171) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        AICSITAD.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B100_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BB22");
        CEP.TRC(SCCGWA, AIB5171_AWA_5171.FUNC_FLG);
        if (AIB5171_AWA_5171.FUNC_FLG == 'A') {
            if (AIB5171_AWA_5171.ADJ_DATE == ' ' 
                || AIB5171_AWA_5171.ADJ_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.INPUT_DATE_ERROR;
                WS_FLD_NO = (short) AIB5171_AWA_5171.ADJ_DATE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AIB5171_AWA_5171.ITM_OLD.trim().length() == 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
                if (AIB5171_AWA_5171.ITM_OLD.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(AIB5171_AWA_5171.ITM_OLD);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AIB5171_AWA_5171.ITM_NEW.trim().length() == 0) {
                WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
                if (AIB5171_AWA_5171.ITM_NEW.trim().length() == 0) WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(AIB5171_AWA_5171.ITM_NEW);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (AIB5171_AWA_5171.FUNC_FLG == ' ') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.MUST_INPUT;
                if (AIB5171_AWA_5171.FUNC_FLG == ' ') WS_FLD_NO = 0;
                else WS_FLD_NO = Short.parseShort(""+AIB5171_AWA_5171.FUNC_FLG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, AIB5171_AWA_5171.PRC_STS);
        if (AIB5171_AWA_5171.FUNC_FLG == 'A' 
            || AIB5171_AWA_5171.FUNC_FLG == 'M') {
            CEP.TRC(SCCGWA, "WL01");
            IBS.init(SCCGWA, SLCISAC);
            SLCISAC.ITM = AIB5171_AWA_5171.ITM_OLD;
            CEP.TRC(SCCGWA, AIB5171_AWA_5171.ITM_OLD);
            S000_CALL_SLZISAC();
            if (pgmRtn) return;
            if (SLCISAC.AC_FLG == 'Y') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.OLD_ITM_HAVE_OPENED_SL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, SLCISAC);
            SLCISAC.ITM = AIB5171_AWA_5171.ITM_NEW;
            CEP.TRC(SCCGWA, AIB5171_AWA_5171.ITM_NEW);
            S000_CALL_SLZISAC();
            if (pgmRtn) return;
            if (SLCISAC.AC_FLG == 'Y') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.NEW_ITM_HAVE_OPENED_SL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPCPCMWD);
            BPCPCMWD.CALCD[1-1] = "CN";
            BPCPCMWD.FUNC_FLAG = 'C';
            BPCPCMWD.CHECK_DATE = AIB5171_AWA_5171.ADJ_DATE;
            CEP.TRC(SCCGWA, "TST518");
            S000_CALL_BPZPCMWD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPCMWD.HOLIDAY_FLG_ALL);
            if (BPCPCMWD.HOLIDAY_FLG_ALL == 'Y') {
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_DATE_NOT_WORK;
                WS_FLD_NO = (short) AIB5171_AWA_5171.ADJ_DATE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, SLCISAC.AC_FLG);
            if (SLCISAC.AC_FLG == 'N') {
            } else {
                if (SLCISAC.AC_FLG == 'Y') {
                    WS_ERR_MSG = AICMSG_ERROR_MSG.AI_READ_SLZISAC_FND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            CEP.TRC(SCCGWA, "BB33A");
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.COA_FLG = AIB5171_AWA_5171.COA_TYP;
            AICQITM.INPUT_DATA.NO = AIB5171_AWA_5171.ITM_OLD;
            if (AIB5171_AWA_5171.ITM_OLD.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(AIB5171_AWA_5171.ITM_OLD);
            CEP.TRC(SCCGWA, AIB5171_AWA_5171.ITM_OLD);
            CEP.TRC(SCCGWA, WS_ERR_INF);
            S000_CALL_AIZQITM();
            if (pgmRtn) return;
            AIB5171_AWA_5171.ITMOLD_D = AICQITM.OUTPUT_DATA.CHS_NM;
            CEP.TRC(SCCGWA, "BB33A1");
            CEP.TRC(SCCGWA, AIB5171_AWA_5171.ITMOLD_D);
            CEP.TRC(SCCGWA, "BB33B");
            IBS.init(SCCGWA, AICQITM);
            AICQITM.INPUT_DATA.COA_FLG = AIB5171_AWA_5171.COA_TYP;
            AICQITM.INPUT_DATA.NO = AIB5171_AWA_5171.ITM_NEW;
            if (AIB5171_AWA_5171.ITM_NEW.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(AIB5171_AWA_5171.ITM_NEW);
            CEP.TRC(SCCGWA, AIB5171_AWA_5171.ITM_NEW);
            S000_CALL_AIZQITM();
            if (pgmRtn) return;
            AIB5171_AWA_5171.ITMNEW_D = AICQITM.OUTPUT_DATA.CHS_NM;
            CEP.TRC(SCCGWA, "BB33B1");
            CEP.TRC(SCCGWA, AIB5171_AWA_5171.ITMNEW_D);
            CEP.TRC(SCCGWA, AIB5171_AWA_5171.ITMNEW_S);
            if (AIB5171_AWA_5171.ITM_OLD.equalsIgnoreCase(AIB5171_AWA_5171.ITM_NEW) 
                && AIB5171_AWA_5171.ITMNEW_S == 1 
                && AICQITM.OUTPUT_DATA.MIB_FLG == 'G') {
                CEP.TRC(SCCGWA, "CHECK CMIB");
                IBS.init(SCCGWA, AIRCMIB);
                IBS.init(SCCGWA, AICRCMIB);
                IBS.init(SCCGWA, AICPCMIB);
                AICRCMIB.FUNC = 'Q';
                AIRCMIB.KEY.GL_BOOK = "BK001";
                AIRCMIB.KEY.ITM = AIB5171_AWA_5171.ITM_OLD;
                AIRCMIB.KEY.SEQ = AIB5171_AWA_5171.ITMNEW_S;
                AIRCMIB.KEY.BR = 999999;
                CEP.TRC(SCCGWA, AIRCMIB.KEY.BR);
                CEP.TRC(SCCGWA, AIRCMIB.KEY.ITM);
                CEP.TRC(SCCGWA, AIRCMIB.KEY.GL_BOOK);
                CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
                AICRCMIB.POINTER = AIRCMIB;
                AICRCMIB.REC_LEN = 407;
                S000_CALL_AIZRCMIB();
                if (pgmRtn) return;
                if (AICRCMIB.RETURN_INFO == 'N') {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND);
                }
                CEP.TRC(SCCGWA, AIRCMIB.STS);
                if (AIRCMIB.STS != 'N') {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_MIB_STS_ERRO);
                }
                CEP.TRC(SCCGWA, AIRCMIB.RVS_TYP);
                if (AIRCMIB.RVS_TYP != 'N') {
                    CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_RVS_TYP_MUST_N);
                }
            }
            if (AIB5171_AWA_5171.ITM_OLD.equalsIgnoreCase(AIB5171_AWA_5171.ITM_NEW) 
                && AIB5171_AWA_5171.ITMNEW_S != 1 
                && AICQITM.OUTPUT_DATA.MIB_FLG == 'G') {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_ITM_TRANS_BR_SEQERR);
            }
            if (AIB5171_AWA_5171.ITM_OLD.equalsIgnoreCase(AIB5171_AWA_5171.ITM_NEW) 
                && AICQITM.OUTPUT_DATA.MIB_FLG != 'G') {
                CEP.TRC(SCCGWA, "SEQ INVALID 1");
                WS_FLD_NO = AIB5171_AWA_5171.ITM_OLD_NO;
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_NOT_VAL;
                CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
            }
            if (!AIB5171_AWA_5171.ITM_OLD.equalsIgnoreCase(AIB5171_AWA_5171.ITM_NEW) 
                && AIB5171_AWA_5171.ITMNEW_S != 0) {
                CEP.TRC(SCCGWA, "SEQ INVALID 2");
                WS_FLD_NO = AIB5171_AWA_5171.ITM_OLD_NO;
                WS_ERR_MSG = AICMSG_ERROR_MSG.AI_GITM_TRSF_INVAL;
                CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
            }
            if (!AIB5171_AWA_5171.ITM_OLD.equalsIgnoreCase(AIB5171_AWA_5171.ITM_NEW) 
                && AIB5171_AWA_5171.ITMNEW_S == 0 
                && AICQITM.OUTPUT_DATA.MIB_FLG == 'Y') {
                IBS.init(SCCGWA, AIRCMIB);
                WS_CONSTANT.WS_DB2_CMIB_STATUS = ' ';
                AIRCMIB.KEY.GL_BOOK = "BK001";
                AIRCMIB.KEY.ITM = AIB5171_AWA_5171.ITM_OLD;
                CEP.TRC(SCCGWA, AIB5171_AWA_5171.ITM_OLD);
                CEP.TRC(SCCGWA, AIRCMIB.KEY.ITM);
                T000_STARTBR_AITCMIB();
                if (pgmRtn) return;
                T000_READNEXT_AITCMIB();
                if (pgmRtn) return;
                while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                    WS_SEQ = AIRCMIB.KEY.SEQ;
                    IBS.init(SCCGWA, AIRCMIB);
                    WS_CONSTANT.WS_DB2_CMIB_STATUS = ' ';
                    CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
                    AIRCMIB.KEY.SEQ = WS_SEQ;
                    AIRCMIB.KEY.ITM = AIB5171_AWA_5171.ITM_NEW;
                    AIRCMIB.KEY.GL_BOOK = "BK001";
                    S000_READ_AITCMIB();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
                    if (WS_CONSTANT.WS_DB2_CMIB_STATUS == 'N') {
                        CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_READ_AITCMIB_NOTFND);
                    }
                    T000_READNEXT_AITCMIB();
                    if (pgmRtn) return;
                }
                T000_ENDBR_AITCMIB();
                if (pgmRtn) return;
            }
        }
        AICPIBTM.INPUT_DATA.COA_FLG = AIB5171_AWA_5171.COA_TYP;
        AICPIBTM.INPUT_DATA.OLD_ITM = AIB5171_AWA_5171.ITM_OLD;
        AICPIBTM.INPUT_DATA.NEW_ITM = AIB5171_AWA_5171.ITM_NEW;
        CEP.TRC(SCCGWA, "AI-P-ITM-EXCH-CHECK");
        CEP.TRC(SCCGWA, AICPIBTM.INPUT_DATA.BOOK_FLG);
        CEP.TRC(SCCGWA, AICPIBTM.INPUT_DATA.COA_FLG);
        CEP.TRC(SCCGWA, AICPIBTM.INPUT_DATA.OLD_ITM);
        CEP.TRC(SCCGWA, AICPIBTM.INPUT_DATA.NEW_ITM);
        CEP.TRC(SCCGWA, AIB5171_AWA_5171.FUNC_FLG);
        if (AIB5171_AWA_5171.FUNC_FLG == 'A' 
            || AIB5171_AWA_5171.FUNC_FLG == 'M') {
            S000_CALL_AIZPIBTM();
            if (pgmRtn) return;
            if (AIB5171_AWA_5171.FUNC_FLG == 'A') {
                B011_CHECK_DUP_DATA();
                if (pgmRtn) return;
            }
            if (AIB5171_AWA_5171.FUNC_FLG == 'M') {
                B011_CHECK_DUP_DATA_M();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_READ_AITCMIB() throws IOException,SQLException,Exception {
        AITCMIB_RD = new DBParm();
        AITCMIB_RD.TableName = "AITCMIB";
        AITCMIB_RD.where = "GL_BOOK = :AIRCMIB.KEY.GL_BOOK "
            + "AND ITM = :AIRCMIB.KEY.ITM "
            + "AND SEQ = :AIRCMIB.KEY.SEQ";
        IBS.READ(SCCGWA, AIRCMIB, this, AITCMIB_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONSTANT.WS_DB2_CMIB_STATUS = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CONSTANT.WS_DB2_CMIB_STATUS = 'N';
        } else {
            WS_ERR_MSG = "READ TABLE AITCMIB ERROR!";
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B011_CHECK_DUP_DATA() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = '1';
        WS_DATA.WS_PRC_STS = 'U';
        WS_OTH_STS = 'U';
        WS_DATA.WS_ITM_NEW = AIB5171_AWA_5171.ITM_NEW;
        WS_COA_FLG = AIB5171_AWA_5171.COA_TYP;
        WS_ITM_NO = AIB5171_AWA_5171.ITM_OLD;
        WS_DATA_TAD = IBS.CLS2CPY(SCCGWA, WS_DATA);
        CEP.TRC(SCCGWA, WS_COA_FLG);
        CEP.TRC(SCCGWA, WS_ITM_NO);
        CEP.TRC(SCCGWA, WS_DATA_TAD);
        CEP.TRC(SCCGWA, WS_FUNC_FLG);
        AITITAD_RD = new DBParm();
        AITITAD_RD.TableName = "AITITAD";
        AITITAD_RD.where = "COA_FLG = :WS_COA_FLG "
            + "AND ITM_NO = :WS_ITM_NO "
            + "AND FUNC_FLG = :WS_FUNC_FLG "
            + "AND SUBSTR ( DATA , 11 , 1 ) = :WS_OTH_STS";
        AITITAD_RD.fst = true;
        AITITAD_RD.order = "DESC";
        IBS.READ(SCCGWA, AIRITAD, this, AITITAD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND1");
            WS_CONSTANT.WS_DB2_REC_STATUS = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_CONSTANT.WS_DB2_REC_STATUS = 'N';
        } else {
            WS_ERR_INF = "STARBR TABLE AITITAD ERROR!";
            WS_FLD_NO = AIB5171_AWA_5171.ITM_NEW_NO;
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_NOT_DUP;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INF, WS_FLD_NO);
        }
        if (WS_CONSTANT.WS_DB2_REC_STATUS == 'F') {
            WS_FLD_NO = AIB5171_AWA_5171.ITM_NEW_NO;
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_NOT_DUP;
            CEP.ERR(SCCGWA, WS_ERR_MSG, AICPIBTM.OUTPUT_DATA.ERRMSG_TXT, WS_FLD_NO);
        }
        WS_ITMD_NEW = AIB5171_AWA_5171.ITM_NEW;
        WS_ITMD_OLD = AIB5171_AWA_5171.ITM_OLD;
        WS_DATA.WS_ITM_NEW = AIB5171_AWA_5171.ITM_NEW;
        WS_DATA_NEW = IBS.CLS2CPY(SCCGWA, WS_DATA);
        WS_DATA.WS_ITM_NEW = AIB5171_AWA_5171.ITM_OLD;
        WS_DATA_OLD = IBS.CLS2CPY(SCCGWA, WS_DATA);
        WS_PROC_DATE = AIB5171_AWA_5171.ADJ_DATE;
        CEP.TRC(SCCGWA, WS_COA_FLG);
        CEP.TRC(SCCGWA, WS_FUNC_FLG);
        CEP.TRC(SCCGWA, WS_ITMD_NEW);
        CEP.TRC(SCCGWA, WS_ITMD_OLD);
        CEP.TRC(SCCGWA, WS_DATA_NEW);
        CEP.TRC(SCCGWA, WS_DATA_OLD);
        CEP.TRC(SCCGWA, WS_PROC_DATE);
        AITITAD_RD = new DBParm();
        AITITAD_RD.TableName = "AITITAD";
        AITITAD_RD.where = "COA_FLG = :WS_COA_FLG "
            + "AND PROC_DATE = :WS_PROC_DATE "
            + "AND FUNC_FLG = :WS_FUNC_FLG "
            + "AND ( ITM_NO = :WS_ITMD_OLD "
            + "OR ITM_NO = :WS_ITMD_NEW "
            + "OR SUBSTR ( DATA , 1 , 11 ) = :WS_DATA_OLD ) "
            + "AND SUBSTR ( DATA , 11 , 1 ) = 'U'";
        AITITAD_RD.fst = true;
        AITITAD_RD.order = "DESC";
        IBS.READ(SCCGWA, AIRITAD, this, AITITAD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND1");
            WS_CONSTANT.WS_DB2_REC_STATUS = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_CONSTANT.WS_DB2_REC_STATUS = 'N';
        } else {
            WS_ERR_INF = "STARBR TABLE AITITAD ERROR!";
            WS_FLD_NO = AIB5171_AWA_5171.ITM_NEW_NO;
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_NOT_DUP;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INF, WS_FLD_NO);
        }
        if (WS_CONSTANT.WS_DB2_REC_STATUS == 'F') {
            WS_FLD_NO = AIB5171_AWA_5171.ITM_NEW_NO;
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_NOT_DUP;
            CEP.ERR(SCCGWA, WS_ERR_MSG, AICPIBTM.OUTPUT_DATA.ERRMSG_TXT, WS_FLD_NO);
        }
    }
    public void B011_CHECK_DUP_DATA_M() throws IOException,SQLException,Exception {
        WS_FUNC_FLG = '1';
        WS_DATA.WS_PRC_STS = 'U';
        WS_DATA.WS_ITM_NEW = AIB5171_AWA_5171.ITM_NEW;
        WS_COA_FLG = AIB5171_AWA_5171.COA_TYP;
        WS_ITMD_NEW = AIB5171_AWA_5171.ITM_NEW;
        WS_ITMD_OLD = AIB5171_AWA_5171.ITM_OLD;
        WS_DATA_OLD = IBS.CLS2CPY(SCCGWA, WS_DATA);
        WS_PROC_DATE = AIB5171_AWA_5171.ADJ_DATE;
        CEP.TRC(SCCGWA, WS_ITMD_NEW);
        CEP.TRC(SCCGWA, WS_ITMD_OLD);
        CEP.TRC(SCCGWA, WS_DATA_NEW);
        CEP.TRC(SCCGWA, WS_DATA_OLD);
        CEP.TRC(SCCGWA, WS_PROC_DATE);
        AITITAD_RD = new DBParm();
        AITITAD_RD.TableName = "AITITAD";
        AITITAD_RD.where = "COA_FLG = :WS_COA_FLG "
            + "AND PROC_DATE = :WS_PROC_DATE "
            + "AND FUNC_FLG = :WS_FUNC_FLG "
            + "AND ITM_NO < > :WS_ITMD_OLD "
            + "AND ( ITM_NO = :WS_ITMD_NEW "
            + "OR SUBSTR ( DATA , 1 , 10 ) = :WS_ITMD_OLD ) "
            + "AND SUBSTR ( DATA , 11 , 1 ) = 'U'";
        AITITAD_RD.fst = true;
        AITITAD_RD.order = "DESC";
        IBS.READ(SCCGWA, AIRITAD, this, AITITAD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND1");
            WS_CONSTANT.WS_DB2_REC_STATUS = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_CONSTANT.WS_DB2_REC_STATUS = 'N';
        } else {
            WS_ERR_INF = "STARBR TABLE AITITAD ERROR!";
            WS_FLD_NO = AIB5171_AWA_5171.ITM_NEW_NO;
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_NOT_DUP;
            CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INF, WS_FLD_NO);
        }
        if (WS_CONSTANT.WS_DB2_REC_STATUS == 'F') {
            WS_FLD_NO = AIB5171_AWA_5171.ITM_NEW_NO;
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_NOT_DUP;
            CEP.ERR(SCCGWA, WS_ERR_MSG, AICPIBTM.OUTPUT_DATA.ERRMSG_TXT, WS_FLD_NO);
        }
    }
    public void B100_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICSITAD);
        AICSITAD.COMM_DATA.ADJ_DATE = AIB5171_AWA_5171.ADJ_DATE;
        AICSITAD.COMM_DATA.COA_TYP = AIB5171_AWA_5171.COA_TYP;
        AICSITAD.COMM_DATA.ITM_OLD = AIB5171_AWA_5171.ITM_OLD;
        AICSITAD.COMM_DATA.ITM_NEW = AIB5171_AWA_5171.ITM_NEW;
        AICSITAD.COMM_DATA.FUNC_FLG = AIB5171_AWA_5171.FUNC_FLG;
        AICSITAD.COMM_DATA.ITMOLD_D = AIB5171_AWA_5171.ITMOLD_D;
        AICSITAD.COMM_DATA.ITMNEW_D = AIB5171_AWA_5171.ITMNEW_D;
        AICSITAD.COMM_DATA.PRC_STS = AIB5171_AWA_5171.PRC_STS;
        AICSITAD.FUNC = AICSITAD.COMM_DATA.FUNC_FLG;
        if (AIB5171_AWA_5171.ITMNEW_S == 1) {
            AICSITAD.COMM_DATA.ITMNEW_S = AIB5171_AWA_5171.ITMNEW_S;
        }
        CEP.TRC(SCCGWA, "BB44");
        S000_CALL_AIZSITAD();
        if (pgmRtn) return;
    }
    public void S000_CALL_AIZSITAD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BB55");
        IBS.CALLCPN(SCCGWA, "AI-SVR-AIZSITAD", AICSITAD);
        CEP.TRC(SCCGWA, "BB551");
        CEP.TRC(SCCGWA, AICSITAD.RC);
        if (AICSITAD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BB552");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICSITAD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPIBTM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TEST20150310");
        IBS.CALLCPN(SCCGWA, "AI-P-ITM-EXCH-CHECK", AICPIBTM);
        AICPIBTM.OUTPUT_DATA.ERRMSG_TXT = " ";
        CEP.TRC(SCCGWA, AICPIBTM.OUTPUT_DATA.ERRMSG_TXT);
        CEP.TRC(SCCGWA, AICPIBTM.RC);
        if (AICPIBTM.RTNCODE != 0) {
            WS_FLD_NO = AIB5171_AWA_5171.ITM_NEW_NO;
            WS_ERR_MSG = AICPIBTM.RC;
            CEP.ERR(SCCGWA, WS_ERR_MSG, AICPIBTM.OUTPUT_DATA.ERRMSG_TXT, WS_FLD_NO);
        }
    }
    public void S000_CALL_AIZQITM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BB66A");
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICQITM);
        CEP.TRC(SCCGWA, "BB66B");
        CEP.TRC(SCCGWA, AICQITM.RC.RTNCODE);
        if (AICQITM.RC.RTNCODE != 0) {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_ITM_NOT_VAL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.MIB_FLG);
        CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.LOW_LVL_FLG);
        if (AICQITM.OUTPUT_DATA.LOW_LVL_FLG == 'N') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_NO_LOW_LVL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICQITM.OUTPUT_DATA.STS);
        if (AICQITM.OUTPUT_DATA.STS != 'A') {
            WS_ERR_MSG = AICMSG_ERROR_MSG.AI_COA_STS_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_AITCMIB() throws IOException,SQLException,Exception {
        AITCMIB_BR.rp = new DBParm();
        AITCMIB_BR.rp.TableName = "AITCMIB";
        AITCMIB_BR.rp.where = "ITM = :AIRCMIB.KEY.ITM";
        IBS.STARTBR(SCCGWA, AIRCMIB, this, AITCMIB_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READNEXT_AITCMIB() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, AIRCMIB, this, AITCMIB_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CONSTANT.WS_DB2_CMIB_STATUS = 'F';
        } else {
            WS_CONSTANT.WS_DB2_CMIB_STATUS = 'N';
        }
    }
    public void T000_ENDBR_AITCMIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITCMIB_BR);
    }
    public void S000_CALL_AIZRCMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-R-MAIN-CMIB", AICRCMIB);
        if (AICRCMIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICRCMIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCMWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-MUL-WORK-DAY", BPCPCMWD);
        CEP.TRC(SCCGWA, BPCPCMWD.RC);
        if (BPCPCMWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPCMWD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BB66");
        WS_ERR_INF = " ";
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INF, WS_FLD_NO);
    }
    public void S000_CALL_SLZISAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "WLL02");
        IBS.CALLCPN(SCCGWA, "SL-INQ-SLAC", SLCISAC);
        CEP.TRC(SCCGWA, SLCISAC.RC);
        if (SLCISAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SLCISAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
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
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
