package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class SOZBTLT {
    brParm BPTTLT_BR = new brParm();
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    short WS_LEN = 0;
    String WS_TSQ_REC = " ";
    SOZBTLT_WS_TS_CNTL WS_TS_CNTL = new SOZBTLT_WS_TS_CNTL();
    SOZBTLT_WS_LINE WS_LINE = new SOZBTLT_WS_LINE();
    SOZBTLT_WS_SUB_TIT WS_SUB_TIT = new SOZBTLT_WS_SUB_TIT();
    SOZBTLT_WS_REC_OUT WS_REC_OUT = new SOZBTLT_WS_REC_OUT();
    char WS_FND_FLAG = ' ';
    SOCMSG SOCMSG = new SOCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRTLT BPRTLT = new BPRTLT();
    SCCFMT SCCFMT = new SCCFMT();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZBTLT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_READY_TS_HEAD_TIT();
        if (pgmRtn) return;
        WS_FND_FLAG = 'N';
        T01_STARTBR_BPTTLT();
        if (pgmRtn) return;
        T02_READNEXT_BPTTLT();
        if (pgmRtn) return;
        while (WS_FND_FLAG != 'N') {
            if (BPRTLT.SIGN_STS == 'O' 
                || BPRTLT.SIGN_STS == 'T') {
                B02_PROC_PRM();
                if (pgmRtn) return;
            }
            T02_READNEXT_BPTTLT();
            if (pgmRtn) return;
        }
        T03_ENDBR_BPTTLT();
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
        WS_LEN = 350;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_LINE);
        WS_LEN = 350;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_SUB_TIT);
        WS_LEN = 350;
        S000_WRITE_TS();
        if (pgmRtn) return;
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_LINE);
        WS_LEN = 350;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void T01_STARTBR_BPTTLT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        BPTTLT_BR.rp = new DBParm();
        BPTTLT_BR.rp.TableName = "BPTTLT";
        BPTTLT_BR.rp.errhdl = true;
        BPTTLT_BR.rp.order = "TLR_BR";
        IBS.STARTBR(SCCGWA, BPRTLT, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T02_READNEXT_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, BPRTLT, this, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FND_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FND_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTTLR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T03_ENDBR_BPTTLT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTLT_BR);
    }
    public void B02_PROC_PRM() throws IOException,SQLException,Exception {
        WS_REC_OUT.WS_TLR_BR = BPRTLT.TLR_BR;
        WS_REC_OUT.WS_TLR = BPRTLT.KEY.TLR;
        WS_REC_OUT.WS_TLR_CN_NM = BPRTLT.TLR_CN_NM;
        WS_REC_OUT.WS_TLR_EN_NM = BPRTLT.TLR_EN_NM;
        WS_REC_OUT.WS_SIGN_STS = BPRTLT.SIGN_STS;
        WS_REC_OUT.WS_SIGN_DT = BPRTLT.SIGN_DT;
        WS_REC_OUT.WS_TLR_TYP = BPRTLT.TLR_TYP;
        WS_REC_OUT.WS_SIGN_TRM = BPRTLT.SIGN_TRM;
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        WS_REC_OUT.WS_FIN_IND = BPRTLT.TLR_STSW.substring(6 - 1, 6 + 1 - 1).charAt(0);
        if (BPRTLT.TLR_STSW == null) BPRTLT.TLR_STSW = "";
        JIBS_tmp_int = BPRTLT.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTLT.TLR_STSW += " ";
        WS_REC_OUT.WS_CSH_IND = BPRTLT.TLR_STSW.substring(3 - 1, 3 + 1 - 1).charAt(0);
        WS_REC_OUT.WS_LAST_JRN = BPRTLT.LAST_JRN;
        WS_REC_OUT.WS_TELE = BPRTLT.TELE;
        WS_REC_OUT.WS_END_FLAG = 'E';
        WS_TSQ_REC = IBS.CLS2CPY(SCCGWA, WS_REC_OUT);
        WS_LEN = 350;
        S000_WRITE_TS();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TSQ_REC;
        CEP.TRC(SCCGWA, WS_LEN);
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
