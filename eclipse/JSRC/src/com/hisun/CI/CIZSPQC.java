package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSPQC {
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    brParm CITCNT_BR = new brParm();
    DBParm CITID_RD;
    DBParm CITNAM_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    String K_OUTPUT_FMT = "CIB14";
    int K_COL_CNT = 3;
    int WS_I = 0;
    int WS_PAGE_ROW = 0;
    int WS_RECORD_NUM = 0;
    int WS_RECORD_NUM1 = 0;
    int WS_ALL_NUM = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRNAM CIRNAM = new CIRNAM();
    CIRID CIRID = new CIRID();
    CIRCNT CIRCNT = new CIRCNT();
    CICFB14 CICFB14 = new CICFB14();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSPQC CICSPQC;
    public void MP(SCCGWA SCCGWA, CICSPQC CICSPQC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSPQC = CICSPQC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSPQC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSPQC.RC);
        IBS.init(SCCGWA, CICFB14);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_SPQC_CI_NO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_SPQC_CI_NO() throws IOException,SQLException,Exception {
        if (CICSPQC.DATA.TEL_NO.trim().length() > 0) {
            B020_01_SPQC_BY_TEL_NO();
            if (pgmRtn) return;
        } else if (CICSPQC.DATA.CONT_NO.trim().length() > 0) {
            B020_02_SPQC_BY_CONT_NO();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        B022_OUT_PAGE_TITLE();
        if (pgmRtn) return;
        B023_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B020_01_SPQC_BY_TEL_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CNT_TYPE = "21";
        CIRCNT.TEL_NO = CICSPQC.DATA.TEL_NO;
        T000_STARTBR_CITCNT_BY_TEL();
        if (pgmRtn) return;
        T000_READNEXT_CITCNT_TEL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
        if (CICSPQC.DATA.PAGE_ROW > K_MAX_ROW 
            || CICSPQC.DATA.PAGE_ROW == 0) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICSPQC.DATA.PAGE_ROW;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            B024_DATA_OUTPUT_DEAL();
            if (pgmRtn) return;
            T000_READNEXT_CITCNT_TEL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCNT_TEL();
        if (pgmRtn) return;
    }
    public void B020_02_SPQC_BY_CONT_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.TEL_NO = CICSPQC.DATA.CONT_NO;
        T000_STARTBR_CITCNT_BY_CONT();
        if (pgmRtn) return;
        T000_READNEXT_CITCNT_CONT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
        if (CICSPQC.DATA.PAGE_ROW > K_MAX_ROW 
            || CICSPQC.DATA.PAGE_ROW == 0) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICSPQC.DATA.PAGE_ROW;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            B024_DATA_OUTPUT_DEAL();
            if (pgmRtn) return;
            T000_READNEXT_CITCNT_CONT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCNT_CONT();
        if (pgmRtn) return;
    }
    public void B021_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        CICFB14.DATA[WS_I-1].CI_NO = CIRBAS.KEY.CI_NO;
        CICFB14.DATA[WS_I-1].ID_TYPE = CIRBAS.ID_TYPE;
        CICFB14.DATA[WS_I-1].ID_NO = CIRBAS.ID_NO;
        CICFB14.DATA[WS_I-1].CI_NM = CIRBAS.CI_NM;
        CICFB14.DATA[WS_I-1].CI_STSW = CIRBAS.STSW;
        CEP.TRC(SCCGWA, CIRBAS.VER_STSW);
        if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
        JIBS_tmp_int = CIRBAS.VER_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
        if (CIRBAS.VER_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CICFB14.DATA[WS_I-1].VER_STS = 'T';
        } else {
            CICFB14.DATA[WS_I-1].VER_STS = 'F';
        }
        CICFB14.DATA[WS_I-1].IDE_STSW = CIRBAS.IDE_STSW;
        CICFB14.DATA[WS_I-1].SVR_LVL = CIRBAS.SVR_LVL;
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRID.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        T000_READ_CITID();
        if (pgmRtn) return;
        CICFB14.DATA[WS_I-1].ID_REMARK = CIRID.REMARK;
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFB14.DATA[WS_I-1].CI_ENM = CIRNAM.CI_NM;
        }
        if (CIRCNT.KEY.CNT_TYPE.equalsIgnoreCase("13")) {
            CICFB14.DATA[WS_I-1].TEL_NO = CIRCNT.TEL_NO;
        } else {
            CICFB14.DATA[WS_I-1].CONT_NO = CIRCNT.TEL_NO;
        }
    }
    public void B022_OUT_PAGE_TITLE() throws IOException,SQLException,Exception {
        WS_RECORD_NUM1 = WS_RECORD_NUM % WS_PAGE_ROW;
        CICFB14.OUTPUT_TITLE.TOTAL_PAGE = (int) ((WS_RECORD_NUM - WS_RECORD_NUM1) / WS_PAGE_ROW);
        if (WS_RECORD_NUM1 > 0) {
            CICFB14.OUTPUT_TITLE.TOTAL_PAGE = CICFB14.OUTPUT_TITLE.TOTAL_PAGE + 1;
        }
        CICFB14.OUTPUT_TITLE.TOTAL_NUM = WS_RECORD_NUM;
        CICFB14.OUTPUT_TITLE.CURR_PAGE = CICSPQC.DATA.PAGE_NUM;
        CICFB14.OUTPUT_TITLE.PAGE_ROW = WS_I;
        if (CICFB14.OUTPUT_TITLE.CURR_PAGE >= CICFB14.OUTPUT_TITLE.TOTAL_PAGE 
            || CICFB14.OUTPUT_TITLE.TOTAL_PAGE == 0) {
            CICFB14.OUTPUT_TITLE.LAST_PAGE = 'Y';
        } else {
            CICFB14.OUTPUT_TITLE.LAST_PAGE = 'N';
        }
    }
    public void B023_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CICFB14;
        SCCFMT.DATA_LEN = 8675;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B024_DATA_OUTPUT_DEAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRCNT.KEY.CI_NO;
        CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("0") 
            && CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("0") 
            && CIRBAS.CI_TYP == '1') {
            WS_RECORD_NUM = WS_RECORD_NUM + 1;
        }
        WS_ALL_NUM = WS_PAGE_ROW * ( CICSPQC.DATA.PAGE_NUM - 1 );
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, WS_ALL_NUM);
        if (WS_RECORD_NUM > WS_ALL_NUM 
            && WS_I < WS_PAGE_ROW) {
            WS_I = WS_I + 1;
            B021_DATA_TRANS_TO_FMT();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_STARTBR_CITCNT_BY_TEL() throws IOException,SQLException,Exception {
        CITCNT_BR.rp = new DBParm();
        CITCNT_BR.rp.TableName = "CITCNT";
        CITCNT_BR.rp.eqWhere = "CNT_TYPE,TEL_NO";
        CITCNT_BR.rp.order = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRCNT, CITCNT_BR);
    }
    public void T000_READNEXT_CITCNT_TEL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCNT, this, CITCNT_BR);
    }
    public void T000_ENDBR_CITCNT_TEL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCNT_BR);
    }
    public void T000_STARTBR_CITCNT_BY_CONT() throws IOException,SQLException,Exception {
        CITCNT_BR.rp = new DBParm();
        CITCNT_BR.rp.TableName = "CITCNT";
        CITCNT_BR.rp.eqWhere = "TEL_NO";
        CITCNT_BR.rp.order = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRCNT, CITCNT_BR);
    }
    public void T000_READNEXT_CITCNT_CONT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCNT, this, CITCNT_BR);
    }
    public void T000_ENDBR_CITCNT_CONT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCNT_BR);
    }
    public void T000_READ_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        IBS.READ(SCCGWA, CIRID, CITID_RD);
    }
    public void T000_READ_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        IBS.READ(SCCGWA, CIRNAM, CITNAM_RD);
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
