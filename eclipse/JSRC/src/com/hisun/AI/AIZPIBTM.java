package com.hisun.AI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class AIZPIBTM {
    DBParm AITITM_RD;
    brParm AITCMIB_BR = new brParm();
    brParm AITMIB_BR = new brParm();
    DBParm AITCMIB_RD;
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    char WS_DSET_FLG = ' ';
    char WS_FIRST_QUR = ' ';
    AIZPIBTM_WS_VAL_ITM WS_VAL_ITM = new AIZPIBTM_WS_VAL_ITM();
    AIZPIBTM_WS_VAL_CMIB WS_VAL_CMIB = new AIZPIBTM_WS_VAL_CMIB();
    char WS_ERR_FLAG = 'N';
    char WS_DBIO_ERR_OLD = 'N';
    char WS_DBIO_ERR_NEW = 'N';
    String WS_CON_COA_TYP = " ";
    String WS_CON_GL_BOOK = " ";
    int WS_CON_BR = 0;
    String WS_CON_ITM = " ";
    int WS_CON_SEQ = 0;
    char WS_MIB_FLG = ' ';
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    AIRITM AIRITM = new AIRITM();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AIRMIB AIRMIB = new AIRMIB();
    SCCGWA SCCGWA;
    AICPIBTM AICPIBTM;
    public void MP(SCCGWA SCCGWA, AICPIBTM AICPIBTM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICPIBTM = AICPIBTM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZPIBTM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AICPIBTM.OUTPUT_DATA.ERRMSG_TXT = " ";
        IBS.init(SCCGWA, AIRITM);
        IBS.init(SCCGWA, AIRCMIB);
        WS_ERR_FLAG = 'N';
        CEP.TRC(SCCGWA, WS_ERR_FLAG);
        AICPIBTM.RTNCODE = 0;
        WS_CON_COA_TYP = AICPIBTM.INPUT_DATA.COA_FLG;
        WS_CON_GL_BOOK = AICPIBTM.INPUT_DATA.BOOK_FLG;
        CEP.TRC(SCCGWA, WS_CON_GL_BOOK);
        CEP.TRC(SCCGWA, AICPIBTM.INPUT_DATA.COA_FLG);
        CEP.TRC(SCCGWA, AICPIBTM.INPUT_DATA.OLD_ITM);
        CEP.TRC(SCCGWA, AICPIBTM.INPUT_DATA.NEW_ITM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "MAIN-PRO-START");
        B010_CHECK_ITM_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TEST1625");
        if (WS_ERR_FLAG == 'Y') {
            AICPIBTM.RTNCODE = 1;
            CEP.TRC(SCCGWA, AICPIBTM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "TEST1626");
        CEP.TRC(SCCGWA, AICPIBTM.RC);
        CEP.TRC(SCCGWA, WS_VAL_ITM.WS_ITM_MIB_FLG);
        if (WS_VAL_ITM.WS_ITM_MIB_FLG == 'Y') {
            B010_CHECK_MIB_PROC();
            if (pgmRtn) return;
            if (WS_ERR_FLAG == 'Y') {
                AICPIBTM.RTNCODE = 1;
                CEP.TRC(SCCGWA, AICPIBTM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_CHECK_ITM_PROC() throws IOException,SQLException,Exception {
        WS_CON_COA_TYP = AICPIBTM.INPUT_DATA.COA_FLG;
        WS_CON_ITM = AICPIBTM.INPUT_DATA.OLD_ITM;
        T000_READ_REC_ITM_1();
        if (pgmRtn) return;
        T000_READ_REC_ITM_2();
        if (pgmRtn) return;
        B020_COMP_ITM_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_ERR_FLAG);
    }
    public void B010_CHECK_MIB_PROC() throws IOException,SQLException,Exception {
        WS_CON_ITM = AICPIBTM.INPUT_DATA.OLD_ITM;
        T000_STARTBR_CMIB_1();
        if (pgmRtn) return;
        WS_ERR_FLAG = ' ';
        CEP.TRC(SCCGWA, WS_ERR_FLAG);
        WS_FIRST_QUR = 'Y';
        T000_READNEXT_CMIB_1();
        if (pgmRtn) return;
        while (WS_ERR_FLAG != 'Y' 
            && SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            T000_GET_MIB_DATA_OLD();
            if (pgmRtn) return;
            T000_READ_REC_MIB_2();
            if (pgmRtn) return;
            B020_COMP_CMIB_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_ERR_FLAG);
            CEP.TRC(SCCGWA, "COMP REC");
            WS_FIRST_QUR = 'N';
            T000_READNEXT_CMIB_1();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_REC_ITM_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "ITM-1-READ");
        CEP.TRC(SCCGWA, WS_CON_COA_TYP);
        CEP.TRC(SCCGWA, WS_CON_ITM);
        AITITM_RD = new DBParm();
        AITITM_RD.TableName = "AITITM";
        AITITM_RD.where = "COA_FLG = :WS_CON_COA_TYP "
            + "AND NO = :WS_CON_ITM";
        AITITM_RD.Reqid = 1;
        IBS.READ(SCCGWA, AIRITM, this, AITITM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND;
            AICPIBTM.RTNCODE = 1;
            Z_RET();
            if (pgmRtn) return;
        }
        WS_VAL_ITM.WS_ITM_TYPE = AIRITM.TYPE;
        WS_VAL_ITM.WS_ITM_CATE = AIRITM.CATE;
        WS_VAL_ITM.WS_ITM_MIB_FLG = AIRITM.MIB_FLG;
        WS_VAL_ITM.WS_ITM_ITM_LVL = AIRITM.ITM_LVL;
        WS_VAL_ITM.WS_ITM_RED_FLG = AIRITM.RED_FLG;
        WS_VAL_ITM.WS_ITM_POST_TYPE = AIRITM.POST_TYPE;
        WS_VAL_ITM.WS_ITM_DTL_IND = AIRITM.DTL_IND;
        WS_VAL_ITM.WS_ITM_BAL_ZERO_FLG = AIRITM.BAL_ZERO_FLG;
        WS_VAL_ITM.WS_ITM_BAL_SIGN_FLG = AIRITM.BAL_SIGN_FLG;
        WS_VAL_ITM.WS_ITM_STS = AIRITM.STS;
        WS_VAL_ITM.WS_ITM_ODE_FLG = AIRITM.ODE_FLG;
        WS_VAL_ITM.WS_ITM_SL_FLG = AIRITM.SL_FLG;
        WS_VAL_ITM.WS_ITM_SEGMENT = AIRITM.SEGMENT;
    }
    public void T000_READ_REC_ITM_2() throws IOException,SQLException,Exception {
        WS_CON_COA_TYP = AICPIBTM.INPUT_DATA.COA_FLG;
        WS_CON_ITM = AICPIBTM.INPUT_DATA.NEW_ITM;
        CEP.TRC(SCCGWA, "ITM-2-READ");
        CEP.TRC(SCCGWA, WS_CON_COA_TYP);
        CEP.TRC(SCCGWA, WS_CON_ITM);
        AITITM_RD = new DBParm();
        AITITM_RD.TableName = "AITITM";
        AITITM_RD.where = "COA_FLG = :WS_CON_COA_TYP "
            + "AND NO = :WS_CON_ITM";
        AITITM_RD.Reqid = 2;
        IBS.READ(SCCGWA, AIRITM, this, AITITM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICPIBTM.RTNCODE = 1;
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_READ_AITITM_NOTFND;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_COMP_ITM_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AIRITM.TYPE);
        CEP.TRC(SCCGWA, WS_VAL_ITM.WS_ITM_TYPE);
        CEP.TRC(SCCGWA, WS_ERR_FLAG);
        CEP.TRC(SCCGWA, "2015031001");
        if (!AIRITM.TYPE.equalsIgnoreCase(WS_VAL_ITM.WS_ITM_TYPE) 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_ITM_TYP_NOSAM;
            WS_ERR_FLAG = 'Y';
            CEP.TRC(SCCGWA, AIRITM.SL_FLG);
            CEP.TRC(SCCGWA, WS_VAL_ITM.WS_ITM_SL_FLG);
        } else if (AIRITM.SL_FLG != WS_VAL_ITM.WS_ITM_SL_FLG 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_ITM_SFLG_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRITM.MIB_FLG != WS_VAL_ITM.WS_ITM_MIB_FLG 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_ITM_MIBF_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRITM.ITM_LVL != WS_VAL_ITM.WS_ITM_ITM_LVL 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_ITM_LVL_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRITM.RED_FLG != WS_VAL_ITM.WS_ITM_RED_FLG 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_ITM_RED_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRITM.POST_TYPE != WS_VAL_ITM.WS_ITM_POST_TYPE 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_ITM_POST_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRITM.DTL_IND != WS_VAL_ITM.WS_ITM_DTL_IND 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_ITM_DTL_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRITM.BAL_SIGN_FLG != WS_VAL_ITM.WS_ITM_BAL_SIGN_FLG 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_ITM_SIGN_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRITM.STS != WS_VAL_ITM.WS_ITM_STS 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_ITM_STS_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRITM.ODE_FLG != WS_VAL_ITM.WS_ITM_ODE_FLG 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_ITM_ODE_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRITM.SEGMENT != WS_VAL_ITM.WS_ITM_SEGMENT 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_ITM_SEGMENT_NOSAM;
            WS_ERR_FLAG = 'Y';
        }
    }
    public void T000_STARTBR_CMIB_1() throws IOException,SQLException,Exception {
        WS_CON_GL_BOOK = "BK002";
        CEP.TRC(SCCGWA, WS_CON_COA_TYP);
        CEP.TRC(SCCGWA, WS_CON_GL_BOOK);
        CEP.TRC(SCCGWA, WS_CON_ITM);
        SCCGWA.COMM_AREA.DBIO_FLG = ' ';
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        AITCMIB_BR.rp = new DBParm();
        AITCMIB_BR.rp.TableName = "AITCMIB";
        AITCMIB_BR.rp.where = "GL_BOOK = :WS_CON_GL_BOOK "
            + "AND ITM = :WS_CON_ITM";
        AITCMIB_BR.rp.Reqid = 3;
        IBS.STARTBR(SCCGWA, AIRCMIB, this, AITCMIB_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "FOUND REC");
            CEP.TRC(SCCGWA, AIRCMIB);
            WS_ERR_FLAG = 'N';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            WS_ERR_FLAG = 'N';
        } else {
            CEP.TRC(SCCGWA, "STARBR TABLE AITITAD ERROR!");
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_READ_TABLE_ERROR;
            WS_ERR_FLAG = 'Y';
            AICPIBTM.RTNCODE = 1;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_CMIB_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READNEXT");
        AITCMIB_BR.rp.Reqid = 3;
        IBS.READNEXT(SCCGWA, AIRCMIB, this, AITCMIB_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, AIRCMIB);
        if (WS_FIRST_QUR == 'Y') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, "FOUND REC");
            } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "NOT FOUND");
                WS_ERR_FLAG = 'N';
            } else {
                CEP.TRC(SCCGWA, "READNEXT TABLE AITITAD ERROR!");
                AICPIBTM.RC = AICMSG_ERROR_MSG.AI_READ_TABLE_ERROR;
                WS_ERR_FLAG = 'Y';
                AICPIBTM.RTNCODE = 1;
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_STARTBR_MIB() throws IOException,SQLException,Exception {
        AITMIB_BR.rp = new DBParm();
        AITMIB_BR.rp.TableName = "AITMIB";
        AITMIB_BR.rp.where = "GL_BOOK = :AIRMIB.KEY.GL_BOOK "
            + "AND ITM_NO = :AIRMIB.KEY.ITM_NO "
            + "AND SEQ = :AIRMIB.KEY.SEQ "
            + "AND STS < > :AIRMIB.STS";
        IBS.STARTBR(SCCGWA, AIRMIB, this, AITMIB_BR);
    }
    public void T000_READNEXT_MIB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "READNEXT");
        IBS.READNEXT(SCCGWA, AIRMIB, this, AITMIB_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, AIRMIB);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MIB_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MIB_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "AITMIB";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_MIB() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, AITMIB_BR);
    }
    public void T000_GET_MIB_DATA_OLD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CMIB-1-READ");
        CEP.TRC(SCCGWA, AIRCMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.BR);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.ITM);
        CEP.TRC(SCCGWA, AIRCMIB.KEY.SEQ);
        WS_CON_BR = AIRCMIB.KEY.BR;
        WS_VAL_CMIB.WS_CMIB_STS = AIRCMIB.STS;
        WS_VAL_CMIB.WS_CMIB_AC_TYP = AIRCMIB.AC_TYP;
        WS_VAL_CMIB.WS_CMIB_CCY_LMT = AIRCMIB.CCY_LMT;
        WS_VAL_CMIB.WS_CMIB_BAL_DIR = AIRCMIB.BAL_DIR;
        WS_VAL_CMIB.WS_CMIB_BAL_RFLG = AIRCMIB.BAL_RFLG;
        WS_VAL_CMIB.WS_CMIB_AMT_DIR = AIRCMIB.AMT_DIR;
        WS_VAL_CMIB.WS_CMIB_DTL_FLG = AIRCMIB.DTL_FLG;
        WS_VAL_CMIB.WS_CMIB_RVS_TYP = AIRCMIB.RVS_TYP;
        WS_VAL_CMIB.WS_CMIB_RVS_KND = AIRCMIB.RVS_KND;
        WS_VAL_CMIB.WS_CMIB_RVS_EXP = AIRCMIB.RVS_EXP;
        WS_VAL_CMIB.WS_CMIB_RVS_UNIT = AIRCMIB.RVS_UNIT;
        WS_VAL_CMIB.WS_CMIB_AC_EXP = AIRCMIB.AC_EXP;
        WS_VAL_CMIB.WS_CMIB_MANUAL_FLG = AIRCMIB.MANUAL_FLG;
        WS_VAL_CMIB.WS_CMIB_ONL_FLG = AIRCMIB.ONL_FLG;
        WS_VAL_CMIB.WS_CMIB_CARD_FLG = AIRCMIB.CARD_FLG;
        WS_VAL_CMIB.WS_CMIB_BAL_CHK = AIRCMIB.BAL_CHK;
        SOOO_CHK_MIB_STS();
        if (pgmRtn) return;
    }
    public void SOOO_CHK_MIB_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRMIB);
        AIRMIB.KEY.GL_BOOK = AIRCMIB.KEY.GL_BOOK;
        AIRMIB.KEY.ITM_NO = AIRCMIB.KEY.ITM;
        AIRMIB.KEY.SEQ = AIRCMIB.KEY.SEQ;
        AIRMIB.STS = 'N';
        CEP.TRC(SCCGWA, AIRMIB.KEY.GL_BOOK);
        CEP.TRC(SCCGWA, AIRMIB.KEY.BR);
        CEP.TRC(SCCGWA, AIRMIB.KEY.ITM_NO);
        CEP.TRC(SCCGWA, AIRMIB.KEY.SEQ);
        CEP.TRC(SCCGWA, AIRMIB.STS);
        T000_STARTBR_MIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_ERR_FLAG);
        T000_READNEXT_MIB();
        if (pgmRtn) return;
        while (WS_MIB_FLG != 'N') {
            CEP.TRC(SCCGWA, AIRMIB.STS);
            CEP.TRC(SCCGWA, AIRMIB);
            if (WS_MIB_FLG == 'Y' 
                && AIRMIB.STS != 'N') {
                AICPIBTM.RC = AICMSG_ERROR_MSG.AI_MIB_STS_WRONG;
                WS_ERR_FLAG = 'Y';
                AICPIBTM.RTNCODE = 1;
                Z_RET();
                if (pgmRtn) return;
            }
            T000_READNEXT_MIB();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICPIBTM.RC);
        T000_ENDBR_MIB();
        if (pgmRtn) return;
    }
    public void T000_READ_REC_MIB_2() throws IOException,SQLException,Exception {
        WS_ERR_FLAG = 'N';
        WS_CON_ITM = AICPIBTM.INPUT_DATA.NEW_ITM;
        WS_CON_SEQ = AIRCMIB.KEY.SEQ + 0;
        CEP.TRC(SCCGWA, "READ-CMIB-2");
        CEP.TRC(SCCGWA, WS_CON_GL_BOOK);
        CEP.TRC(SCCGWA, WS_CON_BR);
        CEP.TRC(SCCGWA, WS_CON_ITM);
        CEP.TRC(SCCGWA, WS_CON_SEQ);
        AITCMIB_RD = new DBParm();
        AITCMIB_RD.TableName = "AITCMIB";
        AITCMIB_RD.where = "GL_BOOK = :WS_CON_GL_BOOK "
            + "AND BR = :WS_CON_BR "
            + "AND ITM = :WS_CON_ITM "
            + "AND SEQ = :WS_CON_SEQ";
        AITCMIB_RD.Reqid = 4;
        IBS.READ(SCCGWA, AIRCMIB, this, AITCMIB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "READ-CMIB-2 ERROR");
            AICPIBTM.RC = AICMSG_ERROR_MSG.NEW_ITM_CMIB_HAVENOT;
            WS_ERR_FLAG = 'Y';
            AICPIBTM.RTNCODE = 1;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, WS_ERR_FLAG);
    }
    public void B020_COMP_CMIB_PROC() throws IOException,SQLException,Exception {
        WS_ERR_FLAG = 'N';
        CEP.TRC(SCCGWA, "COMP-CMIB-START");
        CEP.TRC(SCCGWA, WS_ERR_FLAG);
        CEP.TRC(SCCGWA, AIRCMIB.AC_TYP);
        CEP.TRC(SCCGWA, WS_VAL_CMIB.WS_CMIB_AC_TYP);
        if (WS_ERR_FLAG == 'N') {
            AICPIBTM.OUTPUT_DATA.ERRMSG_TXT = " ";
        }
        SOOO_CHK_MIB_STS();
        if (pgmRtn) return;
        if (AIRCMIB.STS != WS_VAL_CMIB.WS_CMIB_STS 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_STS_NOSAM;
            WS_ERR_FLAG = 'Y';
            CEP.TRC(SCCGWA, AIRCMIB.STS);
            CEP.TRC(SCCGWA, WS_VAL_CMIB.WS_CMIB_STS);
        } else if (((AIRCMIB.STS != 'N' 
                || AIRCMIB.STS != 'N') 
                && WS_ERR_FLAG == 'N')) {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_STS_WRONG;
            WS_ERR_FLAG = 'Y';
            CEP.TRC(SCCGWA, AIRCMIB.STS);
            CEP.TRC(SCCGWA, WS_VAL_CMIB.WS_CMIB_STS);
            CEP.TRC(SCCGWA, AIRCMIB.AC_TYP);
            CEP.TRC(SCCGWA, WS_VAL_CMIB.WS_CMIB_AC_TYP);
        } else if (AIRCMIB.AC_TYP != WS_VAL_CMIB.WS_CMIB_AC_TYP 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_TYP_NOSAM;
            WS_ERR_FLAG = 'Y';
            CEP.TRC(SCCGWA, AIRCMIB.CCY_LMT);
            CEP.TRC(SCCGWA, WS_VAL_CMIB.WS_CMIB_CCY_LMT);
        } else if (AIRCMIB.CCY_LMT != WS_VAL_CMIB.WS_CMIB_CCY_LMT 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_CCYL_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRCMIB.BAL_DIR != WS_VAL_CMIB.WS_CMIB_BAL_DIR 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_DIR_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if ((AIRCMIB.AMT_DIR != 'B' 
                || WS_VAL_CMIB.WS_CMIB_AMT_DIR != 'B')) {
            AICPIBTM.RC = AICMSG_ERROR_MSG.ADJ_ITM_NEED_CMIB_BAL_B;
            WS_ERR_FLAG = 'Y';
        } else if (AIRCMIB.BAL_RFLG != WS_VAL_CMIB.WS_CMIB_BAL_RFLG 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_RFLG_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRCMIB.AMT_DIR != WS_VAL_CMIB.WS_CMIB_AMT_DIR 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_ADIR_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRCMIB.DTL_FLG != WS_VAL_CMIB.WS_CMIB_DTL_FLG 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_DTLF_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRCMIB.RVS_TYP != WS_VAL_CMIB.WS_CMIB_RVS_TYP 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_RVST_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRCMIB.RVS_KND != WS_VAL_CMIB.WS_CMIB_RVS_KND 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_RVSK_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRCMIB.RVS_EXP != WS_VAL_CMIB.WS_CMIB_RVS_EXP 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_RVSE_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRCMIB.RVS_UNIT != WS_VAL_CMIB.WS_CMIB_RVS_UNIT 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_RVSU_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRCMIB.MANUAL_FLG != WS_VAL_CMIB.WS_CMIB_MANUAL_FLG 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_MANUAL_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRCMIB.ONL_FLG != WS_VAL_CMIB.WS_CMIB_ONL_FLG 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_ONLF_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRCMIB.CARD_FLG != WS_VAL_CMIB.WS_CMIB_CARD_FLG 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_CARDF_NOSAM;
            WS_ERR_FLAG = 'Y';
        } else if (AIRCMIB.BAL_CHK != WS_VAL_CMIB.WS_CMIB_BAL_CHK 
                && WS_ERR_FLAG == 'N') {
            AICPIBTM.RC = AICMSG_ERROR_MSG.AI_CMIB_BALC_NOSAM;
            WS_ERR_FLAG = 'Y';
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (AICPIBTM.RTNCODE != 0) {
            CEP.TRC(SCCGWA, "AICPIBTM = ");
            CEP.TRC(SCCGWA, AICPIBTM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
