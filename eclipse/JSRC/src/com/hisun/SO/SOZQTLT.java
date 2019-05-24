package com.hisun.SO;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class SOZQTLT {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTTLT_BR = new brParm();
    boolean pgmRtn = false;
    short K_SUBR_ROW_CNT = 0;
    short K_MAX_COL_NO = 500;
    short K_SCR_ROW_NO = 24;
    short K_SCR_COL_CNT = 3;
    short K_MAX_BUTT_CNT = 9;
    String WK_TEMP_CODE = " ";
    short WK_CNT = 0;
    int RESP_CODE = 0;
    char WS_EXIT = ' ';
    String WS_TS_REC = " ";
    short WS_LEN = 0;
    SOZQTLT_WS_MSGID WS_MSGID = new SOZQTLT_WS_MSGID();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SOCC01 SOCC01 = new SOCC01();
    BPRTLT BPRTLT = new BPRTLT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCGWA SCCGWA;
    SCRCWAT SCRCWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZQTLT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLT);
        T000_STARTBR_BPTTLT();
        if (pgmRtn) return;
        T000_READNEXT_BPTTLT();
        if (pgmRtn) return;
        WS_EXIT = 'N';
        while (WS_EXIT != 'Y') {
            IBS.init(SCCGWA, SOCC01);
            WS_TS_REC = " ";
            if (BPRTLT.SIGN_STS == 'O') {
                SOCC01.TLTNO = BPRTLT.KEY.TLR;
                SOCC01.BR = BPRTLT.TLR_BR;
                SOCC01.TLR_CN_NM = BPRTLT.TLR_CN_NM;
                SOCC01.TLR_EN_NM = BPRTLT.TLR_EN_NM;
                SOCC01.TLR_TELE = BPRTLT.TELE;
                SOCC01.SIGNSTS = BPRTLT.SIGN_STS;
                WS_TS_REC = IBS.CLS2CPY(SCCGWA, SOCC01);
                WS_LEN = 223;
                S000_WRITE_TS();
                if (pgmRtn) return;
            }
            T000_READNEXT_BPTTLT();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTTLT();
        if (pgmRtn) return;
    }
    public void S000_ERROR_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        Z_RET();
        if (pgmRtn) return;
    }
    public void B280_OUTPUT_QULIST_HEAD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = K_SUBR_ROW_CNT;
        SCCMPAG.MAX_COL_NO = K_MAX_COL_NO;
        SCCMPAG.SCR_ROW_CNT = K_SCR_ROW_NO;
        SCCMPAG.SCR_COL_CNT = K_SCR_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_WRITE_TS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = WS_TS_REC;
        SCCMPAG.DATA_LEN = WS_LEN;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTLT() throws IOException,SQLException,Exception {
        BPTTLT_BR.rp = new DBParm();
        BPTTLT_BR.rp.TableName = "BPTTLT";
        BPTTLT_BR.rp.where = "TLR = :BPRTLT.KEY.TLR";
        BPTTLT_BR.rp.order = "TLR";
        IBS.STARTBR(SCCGWA, BPRTLT, this, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_EXIT = 'Y';
        } else {
        }
    }
    public void T000_READNEXT_BPTTLT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTLT, this, BPTTLT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_EXIT = 'Y';
        } else {
        }
    }
    public void T000_ENDBR_BPTTLT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTLT_BR);
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
