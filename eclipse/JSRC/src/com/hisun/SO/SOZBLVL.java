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

public class SOZBLVL {
    brParm SOTSLVL_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    short WS_LEN = 0;
    String WS_TSQ_REC = " ";
    short WS_I = 0;
    SOZBLVL_WS_TS_CNTL WS_TS_CNTL = new SOZBLVL_WS_TS_CNTL();
    SOZBLVL_WS_LINE WS_LINE = new SOZBLVL_WS_LINE();
    SOZBLVL_WS_SUB_TIT WS_SUB_TIT = new SOZBLVL_WS_SUB_TIT();
    SOZBLVL_WS_REC_OUT WS_REC_OUT = new SOZBLVL_WS_REC_OUT();
    char WS_FND_FLG = ' ';
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SOCOP21 SOCOP21 = new SOCOP21();
    String WS_SRV_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SORSLVL SORSLVL = new SORSLVL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SORILVL SORILVL;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZBLVL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SORILVL = new SORILVL();
        IBS.init(SCCGWA, SORILVL);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SORILVL);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        WS_FND_FLG = 'N';
        T01_STARTBR_SOTSLVL();
        if (pgmRtn) return;
        T02_READNEXT_SOTSLVL();
        if (pgmRtn) return;
        while (WS_FND_FLG != 'N') {
            B02_PROC_PRM();
            if (pgmRtn) return;
            T02_READNEXT_SOTSLVL();
            if (pgmRtn) return;
        }
        T03_ENDBR_SOTSLVL();
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
        WS_LEN = 61;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_LINE);
        WS_LEN = 61;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_SUB_TIT);
        WS_LEN = 61;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_LINE);
        WS_LEN = 61;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void T01_STARTBR_SOTSLVL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORSLVL);
        SORSLVL.KEY.BANK_NO = SORILVL.KEY.BANK_NO;
        SORSLVL.KEY.ID = SORILVL.KEY.ID;
        SOTSLVL_BR.rp = new DBParm();
        SOTSLVL_BR.rp.TableName = "SOTSLVL";
        SOTSLVL_BR.rp.where = "BANK_NO >= :SORSLVL.KEY.BANK_NO "
            + "AND ID >= :SORSLVL.KEY.ID";
        SOTSLVL_BR.rp.errhdl = true;
        SOTSLVL_BR.rp.order = "BANK_NO";
        IBS.STARTBR(SCCGWA, SORSLVL, this, SOTSLVL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            CEP.TRC(SCCGWA, " *** DBIO ERROR ");
            SCCGWA.RETURN_CODE = 8;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T02_READNEXT_SOTSLVL() throws IOException,SQLException,Exception {
        SOTSLVL_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, SORSLVL, this, SOTSLVL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            CEP.TRC(SCCGWA, " *** DBIO ERROR ");
            SCCGWA.RETURN_CODE = 8;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T03_ENDBR_SOTSLVL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, SOTSLVL_BR);
    }
    public void B02_PROC_PRM() throws IOException,SQLException,Exception {
        WS_REC_OUT.WS_BANK_NO = SORSLVL.KEY.BANK_NO;
        WS_REC_OUT.WS_ID = SORSLVL.KEY.ID;
        WS_REC_OUT.WS_SUP_ID = SORSLVL.SUP_ID;
        WS_REC_OUT.WS_CRE_USER = SORSLVL.CRE_USER;
        WS_REC_OUT.WS_TS = SORSLVL.TS;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_REC_OUT);
        WS_LEN = 61;
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
