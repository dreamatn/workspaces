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

public class SOZBJRN {
    int JIBS_tmp_int;
    DBParm SOTJRN_RD;
    brParm SOTJRN_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    short WS_LEN = 0;
    String WS_TSQ_REC = " ";
    SOZBJRN_WS_TS_CNTL WS_TS_CNTL = new SOZBJRN_WS_TS_CNTL();
    SOZBJRN_WS_LINE WS_LINE = new SOZBJRN_WS_LINE();
    SOZBJRN_WS_SUB_TIT WS_SUB_TIT = new SOZBJRN_WS_SUB_TIT();
    SOZBJRN_WS_REC_OUT WS_REC_OUT = new SOZBJRN_WS_REC_OUT();
    char WS_FND_FLG = ' ';
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SORJRN SORJRN = new SORJRN();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP12 SOCOP12 = new SOCOP12();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCIJRN SOCIJRN;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        if (SOCIJRN.JRN_NO != 0) {
            B00_INQUIRY_JRN();
            if (pgmRtn) return;
        } else {
            B00_BROWSE_JRN();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "SOZBJRN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCIJRN = new SOCIJRN();
        IBS.init(SCCGWA, SOCIJRN);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCIJRN);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_INQUIRY_JRN() throws IOException,SQLException,Exception {
        B01_READ_SOTJRN();
        if (pgmRtn) return;
        B02_OUTPUT_JRN();
        if (pgmRtn) return;
    }
    public void B01_READ_SOTJRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORJRN);
        SORJRN.KEY.BANK_NO = "" + 0;
        JIBS_tmp_int = SORJRN.KEY.BANK_NO.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) SORJRN.KEY.BANK_NO = "0" + SORJRN.KEY.BANK_NO;
        SORJRN.KEY.JRN_NO = SOCIJRN.JRN_NO;
        SOTJRN_RD = new DBParm();
        SOTJRN_RD.TableName = "SOTJRN";
        SOTJRN_RD.errhdl = true;
        IBS.READ(SCCGWA, SORJRN, SOTJRN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = SOCMSG.SO_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_MSGID = SOCMSG.SO_ACCESS_TAB_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B02_OUTPUT_JRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SOCOP12);
        IBS.init(SCCGWA, SCCFMT);
        SOCOP12.JRN_NO = SORJRN.KEY.JRN_NO;
        SOCOP12.TR_DATE = SORJRN.TR_DATE;
        SOCOP12.TR_TIME = SORJRN.TR_TIME;
        SOCOP12.TL_ID = SORJRN.TL_ID;
        SOCOP12.TERM_ID = SORJRN.TERM_ID;
        SOCOP12.SERV_ID = SORJRN.SERV_ID;
        SOCOP12.TR_ID = SORJRN.TR_ID;
        SOCOP12.SUP1_ID = SORJRN.SUP1_ID;
        SOCOP12.SUP2_ID = SORJRN.SUP2_ID;
        SOCOP12.INP_DATA = SORJRN.INP_DATA;
        SCCFMT.FMTID = "SOP12";
        SCCFMT.DATA_PTR = SOCOP12;
        SCCFMT.DATA_LEN = 165;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B00_BROWSE_JRN() throws IOException,SQLException,Exception {
        B01_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        WS_FND_FLG = 'N';
        T01_STARTBR_SOTJRN();
        if (pgmRtn) return;
        T02_READNEXT_SOTJRN();
        if (pgmRtn) return;
        while (WS_FND_FLG != 'N') {
            if (SOCIJRN.TR_DATE != 0) {
                if (SOCIJRN.TR_DATE == SORJRN.TR_DATE) {
                    B02_PROC_PRM();
                    if (pgmRtn) return;
                }
            } else {
                B02_PROC_PRM();
                if (pgmRtn) return;
            }
            T02_READNEXT_SOTJRN();
            if (pgmRtn) return;
        }
        T03_ENDBR_SOTJRN();
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
    public void T01_STARTBR_SOTJRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORJRN);
        SOTJRN_BR.rp = new DBParm();
        SOTJRN_BR.rp.TableName = "SOTJRN";
        SOTJRN_BR.rp.errhdl = true;
        IBS.STARTBR(SCCGWA, SORJRN, SOTJRN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTJRN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T02_READNEXT_SOTJRN() throws IOException,SQLException,Exception {
        SOTJRN_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, SORJRN, this, SOTJRN_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTJRN";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T03_ENDBR_SOTJRN() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, SOTJRN_BR);
    }
    public void B02_PROC_PRM() throws IOException,SQLException,Exception {
        if (SORJRN.KEY.BANK_NO.trim().length() == 0) WS_REC_OUT.WS_BANK_NO = 0;
        else WS_REC_OUT.WS_BANK_NO = Short.parseShort(SORJRN.KEY.BANK_NO);
        WS_REC_OUT.WS_JRN_NO = SORJRN.KEY.JRN_NO;
        WS_REC_OUT.WS_TR_DATE = SORJRN.TR_DATE;
        WS_REC_OUT.WS_TR_TIME = SORJRN.TR_TIME;
        WS_REC_OUT.WS_TL_ID = SORJRN.TL_ID;
        WS_REC_OUT.WS_TERM_ID = SORJRN.TERM_ID;
        WS_REC_OUT.WS_SERV_ID = SORJRN.SERV_ID;
        WS_REC_OUT.WS_TR_ID = SORJRN.TR_ID;
        WS_REC_OUT.WS_SUP1_ID = SORJRN.SUP1_ID;
        WS_REC_OUT.WS_SUP2_ID = SORJRN.SUP2_ID;
        WS_REC_OUT.WS_INP_DATA = SORJRN.INP_DATA;
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
