package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
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

public class SOZBBLG {
    int JIBS_tmp_int;
    brParm SCTBLOG_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    short WS_LEN = 0;
    String WS_TSQ_REC = " ";
    SOZBBLG_WS_TS_CNTL WS_TS_CNTL = new SOZBBLG_WS_TS_CNTL();
    SOZBBLG_WS_LINE WS_LINE = new SOZBBLG_WS_LINE();
    SOZBBLG_WS_SUB_TIT WS_SUB_TIT = new SOZBBLG_WS_SUB_TIT();
    SOZBBLG_WS_REC_OUT WS_REC_OUT = new SOZBBLG_WS_REC_OUT();
    char WS_FND_FLG = ' ';
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCRBLOG SCRBLOG = new SCRBLOG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCBINF SCCBINF = new SCCBINF();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCIBLG SOCIBLG;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZBBLG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCBINF);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCIBLG = new SOCIBLG();
        IBS.init(SCCGWA, SOCIBLG);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCIBLG);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        WS_FND_FLG = 'N';
        T01_STARTBR_SCTBLOG();
        if (pgmRtn) return;
        T02_READNEXT_SCTBLOG();
        if (pgmRtn) return;
        if (SOCIBLG.AP_MMO.trim().length() == 0 
            && SOCIBLG.TR_DATE == 0) {
            while (WS_FND_FLG != 'N') {
                B02_PROC_PRM();
                if (pgmRtn) return;
                T02_READNEXT_SCTBLOG();
                if (pgmRtn) return;
            }
        }
        if (SOCIBLG.AP_MMO.trim().length() == 0 
            && SOCIBLG.TR_DATE != 0) {
            while (WS_FND_FLG != 'N') {
                if (SOCIBLG.TR_DATE == SCRBLOG.KEY.AC_DATE) {
                    B02_PROC_PRM();
                    if (pgmRtn) return;
                }
                T02_READNEXT_SCTBLOG();
                if (pgmRtn) return;
            }
        }
        if (SOCIBLG.AP_MMO.trim().length() > 0 
            && SOCIBLG.TR_DATE == 0) {
            while (WS_FND_FLG != 'N') {
                if (SCRBLOG.PGM_NAME == null) SCRBLOG.PGM_NAME = "";
                JIBS_tmp_int = SCRBLOG.PGM_NAME.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) SCRBLOG.PGM_NAME += " ";
                if (SOCIBLG.AP_MMO.equalsIgnoreCase(SCRBLOG.PGM_NAME.substring(0, 2))) {
                    B02_PROC_PRM();
                    if (pgmRtn) return;
                }
                T02_READNEXT_SCTBLOG();
                if (pgmRtn) return;
            }
        }
        if (SOCIBLG.AP_MMO.trim().length() > 0 
            && SOCIBLG.TR_DATE != 0) {
            while (WS_FND_FLG != 'N') {
                if (SCRBLOG.PGM_NAME == null) SCRBLOG.PGM_NAME = "";
                JIBS_tmp_int = SCRBLOG.PGM_NAME.length();
                for (int i=0;i<8-JIBS_tmp_int;i++) SCRBLOG.PGM_NAME += " ";
                if (SOCIBLG.AP_MMO.equalsIgnoreCase(SCRBLOG.PGM_NAME.substring(0, 2)) 
                    && SOCIBLG.TR_DATE == SCRBLOG.KEY.AC_DATE) {
                    B02_PROC_PRM();
                    if (pgmRtn) return;
                }
                T02_READNEXT_SCTBLOG();
                if (pgmRtn) return;
            }
        }
        T03_ENDBR_SCTBLOG();
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
        WS_LEN = 280;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_LINE);
        WS_LEN = 280;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_SUB_TIT);
        WS_LEN = 280;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_LINE);
        WS_LEN = 280;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void T01_STARTBR_SCTBLOG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCRBLOG);
        SCTBLOG_BR.rp = new DBParm();
        SCTBLOG_BR.rp.TableName = "SCTBLOG";
        SCTBLOG_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, SCRBLOG, SCTBLOG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SCTBLOG";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T02_READNEXT_SCTBLOG() throws IOException,SQLException,Exception {
        SCTBLOG_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, SCRBLOG, this, SCTBLOG_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SCTBLOG";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T03_ENDBR_SCTBLOG() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, SCTBLOG_BR);
    }
    public void B02_PROC_PRM() throws IOException,SQLException,Exception {
        WS_REC_OUT.WS_AC_DATE = SCRBLOG.KEY.AC_DATE;
        WS_REC_OUT.WS_SEQ_NO = SCRBLOG.KEY.SEQ_NO;
        WS_REC_OUT.WS_DATE = SCRBLOG.DATE;
        WS_REC_OUT.WS_TIME = SCRBLOG.TIME;
        WS_REC_OUT.WS_STEP_NAME = SCRBLOG.STEP_NAME;
        WS_REC_OUT.WS_PGM_NAME = SCRBLOG.PGM_NAME;
        WS_REC_OUT.WS_MSGID_G = SCRBLOG.MSGID;
        WS_REC_OUT.WS_TYPE = SCRBLOG.TYPE;
        WS_REC_OUT.WS_CODE_MSG = SCRBLOG.CODE_MSG;
        IBS.CPY2CLS(SCCGWA, SCRBLOG.DETAIL, SCCBINF);
        WS_REC_OUT.WS_ERR_TYPE = SCCBINF.ERR_TYPE;
        WS_REC_OUT.WS_ERR_ACTION = SCCBINF.ERR_ACTION;
        WS_REC_OUT.WS_ERR_NAME = SCCBINF.ERR_NAME;
        WS_REC_OUT.WS_STS_CODE = SCCBINF.STS_CODE;
        WS_REC_OUT.WS_OTHER_INFO = SCCBINF.OTHER_INFO;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_REC_OUT);
        WS_LEN = 280;
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
