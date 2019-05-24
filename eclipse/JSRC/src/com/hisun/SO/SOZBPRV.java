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

public class SOZBPRV {
    brParm SOTPRIV_BR = new brParm();
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    short WS_LEN = 0;
    String WS_TSQ_REC = " ";
    SOZBPRV_WS_TS_CNTL WS_TS_CNTL = new SOZBPRV_WS_TS_CNTL();
    SOZBPRV_WS_LINE WS_LINE = new SOZBPRV_WS_LINE();
    SOZBPRV_WS_SUB_TIT WS_SUB_TIT = new SOZBPRV_WS_SUB_TIT();
    SOZBPRV_WS_REC_OUT WS_REC_OUT = new SOZBPRV_WS_REC_OUT();
    char WS_FND_FLG = ' ';
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SORPRIV SORPRIV = new SORPRIV();
    SCCFMT SCCFMT = new SCCFMT();
    String WS_PRV_ID = " ";
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPRTLT SORTLT;
    SOCIPRV SOCIPRV;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZBPRV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOCIPRV = new SOCIPRV();
        IBS.init(SCCGWA, SOCIPRV);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, SOCIPRV);
        SORTLT = (BPRTLT) SCCGWA.COMM_AREA.TLT_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        WS_FND_FLG = 'N';
        T01_STARTBR_SOTPRIV();
        if (pgmRtn) return;
        T02_READNEXT_SOTPRIV();
        if (pgmRtn) return;
        while (WS_FND_FLG != 'N') {
            B02_PROC_PRM();
            if (pgmRtn) return;
            T02_READNEXT_SOTPRIV();
            if (pgmRtn) return;
        }
        T03_ENDBR_SOTPRIV();
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
        WS_LEN = 200;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_LINE);
        WS_LEN = 200;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_SUB_TIT);
        WS_LEN = 200;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_LINE);
        WS_LEN = 200;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void T01_STARTBR_SOTPRIV() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SORPRIV);
        WS_PRV_ID = SOCIPRV.ID;
        SOTPRIV_BR.rp = new DBParm();
        SOTPRIV_BR.rp.TableName = "SOTPRIV";
        SOTPRIV_BR.rp.where = "ID >= :WS_PRV_ID";
        SOTPRIV_BR.rp.errhdl = true;
        SOTPRIV_BR.rp.order = "BANK_NO,ID";
        IBS.STARTBR(SCCGWA, SORPRIV, this, SOTPRIV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTPRIV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T02_READNEXT_SOTPRIV() throws IOException,SQLException,Exception {
        SOTPRIV_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, SORPRIV, this, SOTPRIV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SOTPRIV";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T03_ENDBR_SOTPRIV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, SOTPRIV_BR);
    }
    public void B02_PROC_PRM() throws IOException,SQLException,Exception {
        WS_REC_OUT.WS_ID = SORPRIV.KEY.ID;
        WS_REC_OUT.WS_GRP_ID = SORPRIV.GRP_ID;
        WS_REC_OUT.WS_IND = SORPRIV.IND;
        WS_REC_OUT.WS_READ_IND = SORPRIV.READ_IND;
        WS_REC_OUT.WS_BROW_IND = SORPRIV.BROW_IND;
        WS_REC_OUT.WS_WRITE_IND = SORPRIV.WRITE_IND;
        WS_REC_OUT.WS_UPD_IND = SORPRIV.UPD_IND;
        WS_REC_OUT.WS_DEL_IND = SORPRIV.DEL_IND;
        WS_REC_OUT.WS_CRE_USER = SORPRIV.CRE_USER;
        if (SORPRIV.TS == null) SORPRIV.TS = "";
        JIBS_tmp_int = SORPRIV.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORPRIV.TS += " ";
        JIBS_tmp_str[0] = "" + WS_REC_OUT.WS_CRE_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_REC_OUT.WS_CRE_DATE;
        JIBS_NumStr = SORPRIV.TS.substring(0, 4) + JIBS_NumStr.substring(4);
        WS_REC_OUT.WS_CRE_DATE = Integer.parseInt(JIBS_NumStr);
        if (SORPRIV.TS == null) SORPRIV.TS = "";
        JIBS_tmp_int = SORPRIV.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORPRIV.TS += " ";
        JIBS_tmp_str[0] = "" + WS_REC_OUT.WS_CRE_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_REC_OUT.WS_CRE_DATE;
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + SORPRIV.TS.substring(6 - 1, 6 + 2 - 1) + JIBS_NumStr.substring(5 + 2 - 1);
        WS_REC_OUT.WS_CRE_DATE = Integer.parseInt(JIBS_NumStr);
        if (SORPRIV.TS == null) SORPRIV.TS = "";
        JIBS_tmp_int = SORPRIV.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) SORPRIV.TS += " ";
        JIBS_tmp_str[0] = "" + WS_REC_OUT.WS_CRE_DATE;
        JIBS_f0 = "";
        for (int i=0;i<8-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + WS_REC_OUT.WS_CRE_DATE;
        JIBS_NumStr = JIBS_NumStr.substring(0, 7 - 1) + SORPRIV.TS.substring(9 - 1, 9 + 2 - 1) + JIBS_NumStr.substring(7 + 2 - 1);
        WS_REC_OUT.WS_CRE_DATE = Integer.parseInt(JIBS_NumStr);
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_REC_OUT);
        WS_LEN = 200;
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