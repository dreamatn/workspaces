package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.BPRTLT;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class SOZBOLG {
    brParm SCTOLOG_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    short WS_LEN = 0;
    String WS_TSQ_REC = " ";
    SOZBOLG_WS_TS_CNTL WS_TS_CNTL = new SOZBOLG_WS_TS_CNTL();
    SOZBOLG_WS_LINE WS_LINE = new SOZBOLG_WS_LINE();
    SOZBOLG_WS_SUB_TIT WS_SUB_TIT = new SOZBOLG_WS_SUB_TIT();
    SOZBOLG_WS_REC_OUT WS_REC_OUT = new SOZBOLG_WS_REC_OUT();
    char WS_FND_FLG = ' ';
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCROLOG SCROLOG = new SCROLOG();
    SCCFMT SCCFMT = new SCCFMT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCIOLG SOCIOLG;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZBOLG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCIOLG = new SOCIOLG();
        IBS.init(SCCGWA, SOCIOLG);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCIOLG);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        WS_FND_FLG = 'N';
        T01_STARTBR_SCTOLOG();
        if (pgmRtn) return;
        T02_READNEXT_SCTOLOG();
        if (pgmRtn) return;
        if (SOCIOLG.AP_MMO.trim().length() == 0 
            && SOCIOLG.TR_DATE == 0) {
            while (WS_FND_FLG != 'N') {
                B02_PROC_PRM();
                if (pgmRtn) return;
                T02_READNEXT_SCTOLOG();
                if (pgmRtn) return;
            }
        }
        if (SOCIOLG.AP_MMO.trim().length() == 0 
            && SOCIOLG.TR_DATE != 0) {
            while (WS_FND_FLG != 'N') {
                if (SOCIOLG.TR_DATE == SCROLOG.KEY.AC_DATE) {
                    B02_PROC_PRM();
                    if (pgmRtn) return;
                }
                T02_READNEXT_SCTOLOG();
                if (pgmRtn) return;
            }
        }
        if (SOCIOLG.AP_MMO.trim().length() > 0 
            && SOCIOLG.TR_DATE == 0) {
            while (WS_FND_FLG != 'N') {
                if (SOCIOLG.AP_MMO.equalsIgnoreCase(SCROLOG.AP_MMO)) {
                    B02_PROC_PRM();
                    if (pgmRtn) return;
                }
                T02_READNEXT_SCTOLOG();
                if (pgmRtn) return;
            }
        }
        if (SOCIOLG.AP_MMO.trim().length() > 0 
            && SOCIOLG.TR_DATE != 0) {
            while (WS_FND_FLG != 'N') {
                if (SOCIOLG.AP_MMO.equalsIgnoreCase(SCROLOG.AP_MMO) 
                    && SOCIOLG.TR_DATE == SCROLOG.KEY.AC_DATE) {
                    B02_PROC_PRM();
                    if (pgmRtn) return;
                }
                T02_READNEXT_SCTOLOG();
                if (pgmRtn) return;
            }
        }
        T03_ENDBR_SCTOLOG();
        if (pgmRtn) return;
    }
    public void B01_READY_TS_HEAD_TIT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = WS_TS_CNTL.WS_TS_MAIN_TIT;
        SCCMPAG.MAX_COL_NO = WS_TS_CNTL.WS_TS_MAX_RECLEN;
        SCCMPAG.SUBT_ROW_CNT = WS_TS_CNTL.WS_TS_TIT_NUM;
        SCCMPAG.SCR_ROW_CNT = WS_TS_CNTL.WS_SCR_ROW_CNT;
        B_MPAG();
        if (pgmRtn) return;
        WS_TSQ_REC = " ";
        WS_LEN = 250;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_LINE);
        WS_LEN = 250;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_SUB_TIT);
        WS_LEN = 250;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_LINE);
        WS_LEN = 250;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void T01_STARTBR_SCTOLOG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCROLOG);
        SCTOLOG_BR.rp = new DBParm();
        SCTOLOG_BR.rp.TableName = "SCTOLOG";
        SCTOLOG_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, SCROLOG, SCTOLOG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SCTOLOG";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T02_READNEXT_SCTOLOG() throws IOException,SQLException,Exception {
        SCTOLOG_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, SCROLOG, this, SCTOLOG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SCTOLOG";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T03_ENDBR_SCTOLOG() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, SCTOLOG_BR);
    }
    public void B02_PROC_PRM() throws IOException,SQLException,Exception {
        WS_REC_OUT.WS_AC_DATE = SCROLOG.KEY.AC_DATE;
        WS_REC_OUT.WS_SEQ_NO = SCROLOG.KEY.SEQ_NO;
        WS_REC_OUT.WS_LAST_NO = SCROLOG.LAST_NO;
        WS_REC_OUT.WS_MAX_NO = SCROLOG.MAX_NO;
        WS_REC_OUT.WS_SYS_LST_NO = SCROLOG.SYS_LST_NO;
        WS_REC_OUT.WS_DATE = SCROLOG.DATE;
        WS_REC_OUT.WS_TIME = SCROLOG.TIME;
        WS_REC_OUT.WS_AP_MMO = SCROLOG.AP_MMO;
        WS_REC_OUT.WS_TR_CODE = SCROLOG.TR_CODE;
        WS_REC_OUT.WS_TRMID = SCROLOG.TRMID;
        WS_REC_OUT.WS_OP_ID = SCROLOG.OP_ID;
        WS_REC_OUT.WS_PGM = SCROLOG.PGM;
        WS_REC_OUT.WS_LVL = SCROLOG.LVL;
        WS_REC_OUT.WS_TEXT = SCROLOG.TEXT;
        WS_REC_OUT.WS_LST_NO = SCROLOG.LST_NO;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_REC_OUT);
        WS_LEN = 250;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TSQ_REC;
        SCCMPAG.DATA_LEN = WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
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
