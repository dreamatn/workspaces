package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZDIST2 {
    int BAS_CI_NM_LEN;
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    brParm CITID_BR = new brParm();
    DBParm CITID_RD;
    DBParm CITNAM_RD;
    DBParm CITCNT_RD;
    DBParm CITACR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    String K_OUTPUT_FMT = "CIB10";
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
    CIRACR CIRACR = new CIRACR();
    CIRCNT CIRCNT = new CIRCNT();
    CICFB10 CICFB10 = new CICFB10();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICDIST2 CICDIST2;
    public void MP(SCCGWA SCCGWA, CICDIST2 CICDIST2) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICDIST2 = CICDIST2;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZDIST2 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICDIST2.RC);
        IBS.init(SCCGWA, CICFB10);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_DIST2_CI_NO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_DIST2_CI_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICDIST2.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICDIST2.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICDIST2.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICDIST2.DATA.CI_NM);
        CEP.TRC(SCCGWA, CICDIST2.DATA.AGR_NO);
        if (CICDIST2.DATA.CI_NO.trim().length() > 0) {
            B020_01_DIST2_BY_CI_NO();
            if (pgmRtn) return;
        } else if (CICDIST2.DATA.AGR_NO.trim().length() > 0) {
            B020_02_DIST2_BY_AGR_NO();
            if (pgmRtn) return;
        } else if (CICDIST2.DATA.ID_TYPE.trim().length() > 0 
                && CICDIST2.DATA.ID_NO.trim().length() > 0 
                && CICDIST2.DATA.CI_NM.trim().length() > 0) {
            B020_03_DIST2_BY_IDNM();
            if (pgmRtn) return;
        } else if (CICDIST2.DATA.ID_TYPE.trim().length() > 0 
                && CICDIST2.DATA.ID_NO.trim().length() > 0) {
            B020_04_DIST2_BY_ID();
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
    public void B020_01_DIST2_BY_CI_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICDIST2.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        WS_I = 1;
        WS_PAGE_ROW = 1;
        WS_RECORD_NUM = 1;
        CICDIST2.DATA.PAGE_NUM = 1;
        B021_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
    }
    public void B020_02_DIST2_BY_AGR_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.KEY.AGR_NO = CICDIST2.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND);
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CIRACR.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        WS_I = 1;
        WS_PAGE_ROW = 1;
        WS_RECORD_NUM = 1;
        CICDIST2.DATA.PAGE_NUM = 1;
        B021_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
    }
    public void B020_03_DIST2_BY_IDNM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICDIST2.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICDIST2.DATA.ID_NO;
        CIRBAS.CI_NM = CICDIST2.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        T000_READ_CITBAS_BY_IDNM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "NOT FOUND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        WS_I = 1;
        WS_PAGE_ROW = 1;
        WS_RECORD_NUM = 1;
        CICDIST2.DATA.PAGE_NUM = 1;
        B021_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
    }
    public void B020_04_DIST2_BY_ID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.ID_TYPE = CICDIST2.DATA.ID_TYPE;
        CIRID.ID_NO = CICDIST2.DATA.ID_NO;
        T000_STARTBR_CITID_BY_ID();
        if (pgmRtn) return;
        T000_READNEXT_CITID();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        if (CICDIST2.DATA.PAGE_ROW > K_MAX_ROW 
            || CICDIST2.DATA.PAGE_ROW == 0) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICDIST2.DATA.PAGE_ROW;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CIRID.KEY.CI_NO;
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
                && CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("0")) {
                WS_RECORD_NUM = WS_RECORD_NUM + 1;
            }
            WS_ALL_NUM = WS_PAGE_ROW * ( CICDIST2.DATA.PAGE_NUM - 1 );
            CEP.TRC(SCCGWA, WS_RECORD_NUM);
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, WS_ALL_NUM);
            if (WS_RECORD_NUM > WS_ALL_NUM 
                && WS_I < WS_PAGE_ROW) {
                WS_I = WS_I + 1;
                B021_DATA_TRANS_TO_FMT();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITID();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITID();
        if (pgmRtn) return;
    }
    public void B021_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        CICFB10.DATA[WS_I-1].CI_NO = CIRBAS.KEY.CI_NO;
        CICFB10.DATA[WS_I-1].CI_TYP = CIRBAS.CI_TYP;
        CICFB10.DATA[WS_I-1].CI_ATTR = CIRBAS.CI_ATTR;
        CICFB10.DATA[WS_I-1].ID_TYPE = CIRBAS.ID_TYPE;
        CICFB10.DATA[WS_I-1].ID_NO = CIRBAS.ID_NO;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICFB10.DATA[WS_I-1].CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICFB10.DATA[WS_I-1].CI_STSW = CIRBAS.STSW;
        CEP.TRC(SCCGWA, CIRBAS.VER_STSW);
        if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
        JIBS_tmp_int = CIRBAS.VER_STSW.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
        if (CIRBAS.VER_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CICFB10.DATA[WS_I-1].VER_STS = 'T';
        } else {
            CICFB10.DATA[WS_I-1].VER_STS = 'F';
        }
        CICFB10.DATA[WS_I-1].IDE_STSW = CIRBAS.IDE_STSW;
        CICFB10.DATA[WS_I-1].SVR_LVL = CIRBAS.SVR_LVL;
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRID.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        T000_READ_CITID();
        if (pgmRtn) return;
        CICFB10.DATA[WS_I-1].ID_REMARK = CIRID.REMARK;
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFB10.DATA[WS_I-1].CI_ENM = CIRNAM.CI_NM;
        }
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRCNT.KEY.CNT_TYPE = "21";
        T000_READ_CITCNT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFB10.DATA[WS_I-1].TEL_NO = CIRCNT.TEL_NO;
        }
    }
    public void B022_OUT_PAGE_TITLE() throws IOException,SQLException,Exception {
        WS_RECORD_NUM1 = WS_RECORD_NUM % WS_PAGE_ROW;
        CICFB10.OUTPUT_TITLE.TOTAL_PAGE = (int) ((WS_RECORD_NUM - WS_RECORD_NUM1) / WS_PAGE_ROW);
        if (WS_RECORD_NUM1 > 0) {
            CICFB10.OUTPUT_TITLE.TOTAL_PAGE = CICFB10.OUTPUT_TITLE.TOTAL_PAGE + 1;
        }
        CICFB10.OUTPUT_TITLE.TOTAL_NUM = WS_RECORD_NUM;
        CICFB10.OUTPUT_TITLE.CURR_PAGE = CICDIST2.DATA.PAGE_NUM;
        CICFB10.OUTPUT_TITLE.PAGE_ROW = WS_I;
        if (CICFB10.OUTPUT_TITLE.CURR_PAGE >= CICFB10.OUTPUT_TITLE.TOTAL_PAGE 
            || CICFB10.OUTPUT_TITLE.TOTAL_PAGE == 0) {
            CICFB10.OUTPUT_TITLE.LAST_PAGE = 'Y';
        } else {
            CICFB10.OUTPUT_TITLE.LAST_PAGE = 'N';
        }
    }
    public void B023_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICFB10.DATA[1-1].CI_NO);
        CEP.TRC(SCCGWA, CICFB10.DATA[2-1].CI_NO);
        CEP.TRC(SCCGWA, CICFB10.DATA[3-1].CI_NO);
        CEP.TRC(SCCGWA, CICFB10.DATA[4-1].CI_NO);
        CEP.TRC(SCCGWA, CICFB10.DATA[5-1].CI_NO);
        CEP.TRC(SCCGWA, CICFB10.DATA[6-1].CI_NO);
        CEP.TRC(SCCGWA, CICFB10.DATA[7-1].CI_NO);
        CEP.TRC(SCCGWA, CICFB10.DATA[8-1].CI_NO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CICFB10;
        SCCFMT.DATA_LEN = 8515;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITBAS_BY_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
    }
    public void T000_STARTBR_CITID_BY_ID() throws IOException,SQLException,Exception {
        CITID_BR.rp = new DBParm();
        CITID_BR.rp.TableName = "CITID";
        CITID_BR.rp.eqWhere = "ID_TYPE,ID_NO";
        CITID_BR.rp.order = "CI_NO,ID_TYPE";
        IBS.STARTBR(SCCGWA, CIRID, CITID_BR);
    }
    public void T000_READNEXT_CITID() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRID, this, CITID_BR);
    }
    public void T000_ENDBR_CITID() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITID_BR);
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
    public void T000_READ_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        IBS.READ(SCCGWA, CIRCNT, CITCNT_RD);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
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
