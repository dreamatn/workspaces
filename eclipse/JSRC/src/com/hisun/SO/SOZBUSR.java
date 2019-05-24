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

public class SOZBUSR {
    brParm SOTUSR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    short WS_LEN = 0;
    String WS_TSQ_REC = " ";
    SOZBUSR_WS_TS_CNTL WS_TS_CNTL = new SOZBUSR_WS_TS_CNTL();
    SOZBUSR_WS_LINE WS_LINE = new SOZBUSR_WS_LINE();
    SOZBUSR_WS_SUB_TIT WS_SUB_TIT = new SOZBUSR_WS_SUB_TIT();
    SOZBUSR_WS_REC_OUT WS_REC_OUT = new SOZBUSR_WS_REC_OUT();
    char WS_FND_FLG = ' ';
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SORUSR SORUSR = new SORUSR();
    SCCFMT SCCFMT = new SCCFMT();
    String WS_USR_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCIUSR SOCIUSR;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZBUSR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCIUSR = new SOCIUSR();
        IBS.init(SCCGWA, SOCIUSR);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCIUSR);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        WS_FND_FLG = 'N';
        T01_STARTBR_SOTUSR();
        if (pgmRtn) return;
        T02_READNEXT_SOTUSR();
        if (pgmRtn) return;
        while (WS_FND_FLG != 'N') {
            B02_PROC_PRM();
            if (pgmRtn) return;
            T02_READNEXT_SOTUSR();
            if (pgmRtn) return;
        }
        T03_ENDBR_SOTUSR();
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
    public void T01_STARTBR_SOTUSR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORUSR);
        WS_USR_ID = SOCIUSR.ID;
        SOTUSR_BR.rp = new DBParm();
        SOTUSR_BR.rp.TableName = "SOTUSR";
        SOTUSR_BR.rp.where = "ID >= :WS_USR_ID";
        SOTUSR_BR.rp.errhdl = true;
        SOTUSR_BR.rp.order = "BANK_NO,ID";
        IBS.STARTBR(SCCGWA, SORUSR, this, SOTUSR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTUSR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T02_READNEXT_SOTUSR() throws IOException,SQLException,Exception {
        SOTUSR_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, SORUSR, this, SOTUSR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTUSR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T03_ENDBR_SOTUSR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, SOTUSR_BR);
    }
    public void B02_PROC_PRM() throws IOException,SQLException,Exception {
        WS_REC_OUT.WS_ID = SORUSR.KEY.ID;
        WS_REC_OUT.WS_NAME = SORUSR.NAME;
        WS_REC_OUT.WS_TYPE = SORUSR.TYPE;
        WS_REC_OUT.WS_AUTH_LVL = SORUSR.AUTH_LVL;
        WS_REC_OUT.WS_SNON_STUS = SORUSR.SNON_STUS;
        WS_REC_OUT.WS_SNON_DATE = SORUSR.SNON_DATE;
        WS_REC_OUT.WS_SNON_TIME = SORUSR.SNON_TIME;
        WS_REC_OUT.WS_TERM_ID = SORUSR.TERM_ID;
        WS_REC_OUT.WS_TERM_TYPE = 'T';
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
