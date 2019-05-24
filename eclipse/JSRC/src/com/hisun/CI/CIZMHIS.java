package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMHIS {
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    brParm CITHIS_BR = new brParm();
    brParm CITACR_BR = new brParm();
    brParm CITAGT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 30;
    int K_MAX_COL = 99;
    int K_COL_STS = 9;
    char WS_MPAG_FLG = ' ';
    int WS_CNT_NUM = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    CIRAGT CIRAGT = new CIRAGT();
    CIRHIS CIRHIS = new CIRHIS();
    CICOMBAS CICOMBAS = new CICOMBAS();
    CICOMACR CICOMACR = new CICOMACR();
    CICOMAGT CICOMAGT = new CICOMAGT();
    int WS_AC_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMHIS CICMHIS;
    public void MP(SCCGWA SCCGWA, CICMHIS CICMHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMHIS = CICMHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICMHIS.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICMHIS.FUNC == 'A') {
            B020_BRW_COMB_INFO();
            if (pgmRtn) return;
        } else if (CICMHIS.FUNC == 'C'
            || CICMHIS.FUNC == 'G') {
            B030_BRW_PRI_ACRAGT_INFO();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICMHIS.DATA.CI_NO;
        CEP.TRC(SCCGWA, CICMHIS.DATA.CI_NO);
        CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
            || CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "BAS INF NOT FND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICMHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BRW_COMB_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRHIS);
        CIRHIS.CI_NO = CICMHIS.DATA.CI_NO;
        CEP.TRC(SCCGWA, CIRHIS.CI_NO);
        CEP.TRC(SCCGWA, CIRHIS.KEY.PRI_CI);
        T000_STARTBR_CITHIS_BY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITHIS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.init(SCCGWA, CIRHIS);
            CIRHIS.KEY.PRI_CI = CICMHIS.DATA.CI_NO;
            T000_STARTBR_CITHIS_BY_PRI();
            if (pgmRtn) return;
            T000_READNEXT_CITHIS();
            if (pgmRtn) return;
        }
        B020_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (CIRHIS.KEY.FMT_ID.equalsIgnoreCase("CITBAS")) {
                IBS.init(SCCGWA, CIRBAS);
                CIRBAS.KEY.CI_NO = CIRHIS.CI_NO;
                CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
                T000_READ_CITBAS();
                if (pgmRtn) return;
                WS_MPAG_FLG = 'A';
                B020_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITHIS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITHIS();
        if (pgmRtn) return;
    }
    public void B030_BRW_PRI_ACRAGT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRHIS);
        CIRHIS.KEY.PRI_CI = CICMHIS.DATA.CI_NO;
        CEP.TRC(SCCGWA, CICMHIS.FUNC);
        T000_STARTBR_CITHIS_BY_PRI();
        if (pgmRtn) return;
        T000_READNEXT_CITHIS();
        if (pgmRtn) return;
        B020_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CICMHIS.FUNC);
            CEP.TRC(SCCGWA, CIRHIS.KEY.FMT_ID);
            if (CIRHIS.KEY.FMT_ID.equalsIgnoreCase("CITACR")) {
                CEP.TRC(SCCGWA, "COME HERE CITACR");
                WS_CNT_NUM += 1;
                CEP.TRC(SCCGWA, WS_CNT_NUM);
                if (CICMHIS.FUNC == 'C') {
                    IBS.CPY2CLS(SCCGWA, CIRHIS.BLOB_VAL, CIRACR);
                    WS_MPAG_FLG = 'C';
                    B020_02_OUT_BRW_DATA();
                    if (pgmRtn) return;
                }
            } else if (CIRHIS.KEY.FMT_ID.equalsIgnoreCase("CITAGT")) {
                CEP.TRC(SCCGWA, "COME HERE CITAGT");
                WS_CNT_NUM += 1;
                CEP.TRC(SCCGWA, WS_CNT_NUM);
                if (CICMHIS.FUNC == 'G') {
                    IBS.CPY2CLS(SCCGWA, CIRHIS.BLOB_VAL, CIRAGT);
                    WS_MPAG_FLG = 'G';
                    B020_02_OUT_BRW_DATA();
                    if (pgmRtn) return;
                }
            } else if (CIRHIS.KEY.FMT_ID.equalsIgnoreCase("CITBAS")) {
            } else {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR);
            }
            T000_READNEXT_CITHIS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITHIS();
        if (pgmRtn) return;
    }
    public void B020_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_STS;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        CEP.TRC(SCCGWA, WS_MPAG_FLG);
        if (WS_MPAG_FLG == 'A') {
            R000_DATA_TRANS_TO_FMTMPAG_BAS();
            if (pgmRtn) return;
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOMBAS);
            SCCMPAG.DATA_LEN = 386;
        } else if (WS_MPAG_FLG == 'C') {
            R000_DATA_TRANS_TO_FMTMPAG_ACR();
            if (pgmRtn) return;
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOMACR);
            SCCMPAG.DATA_LEN = 746;
        } else if (WS_MPAG_FLG == 'G') {
            R000_DATA_TRANS_TO_FMTMPAG_AGT();
            if (pgmRtn) return;
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOMAGT);
            SCCMPAG.DATA_LEN = 447;
        }
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_DATA_TRANS_TO_FMTMPAG_BAS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "COME HERE CITBAS");
        CEP.TRC(SCCGWA, CIRHIS.CI_NO);
        CEP.TRC(SCCGWA, CIRHIS.KEY.PRI_CI);
        IBS.init(SCCGWA, CICOMBAS);
        CICOMBAS.DATA.CI_NO = CIRHIS.CI_NO;
        CICOMBAS.DATA.PRI_CI = CIRHIS.KEY.PRI_CI;
        CEP.TRC(SCCGWA, CICOMBAS.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICOMBAS.DATA.PRI_CI);
        CICOMBAS.DATA.CI_NM = CIRBAS.CI_NM;
        CICOMBAS.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICOMBAS.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICOMBAS.DATA.SVR_LVL = CIRBAS.SVR_LVL;
        CICOMBAS.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICOMBAS.DATA.ID_NO = CIRBAS.ID_NO;
        CICOMBAS.DATA.OPEN_DT = CIRBAS.OPEN_DT;
        CICOMBAS.DATA.AC_DATE = CIRHIS.KEY.AC_DATE;
        CICOMBAS.DATA.JRN_NO = CIRHIS.KEY.JRN_NO;
    }
    public void R000_DATA_TRANS_TO_FMTMPAG_ACR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOMACR);
        CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
        CICOMACR.DATA.PRI_CI = CIRHIS.KEY.PRI_CI;
        CICOMACR.DATA.CI_NO = CIRHIS.CI_NO;
        CICOMACR.DATA.ENTY_TYP = CIRACR.ENTY_TYP;
        CICOMACR.DATA.AGR_NO = CIRACR.KEY.AGR_NO;
        CICOMACR.DATA.STS = CIRACR.STS;
        CICOMACR.DATA.PROD_CD = CIRACR.PROD_CD;
        CICOMACR.DATA.CNTRCT_TYP = CIRACR.CNTRCT_TYP;
        CICOMACR.DATA.FRM_APP = CIRACR.FRM_APP;
        CICOMACR.DATA.CCY = CIRACR.CCY;
        CICOMACR.DATA.AC_CNM = CIRACR.AC_CNM;
        CICOMACR.DATA.AC_ENM = CIRACR.AC_ENM;
        CICOMACR.DATA.OPN_BR = CIRACR.OPN_BR;
        CICOMACR.DATA.OWNER_BK = CIRACR.OWNER_BK;
        CICOMACR.DATA.OPEN_DT = CIRACR.OPEN_DT;
        CICOMACR.DATA.SMS_FLG = CIRACR.SMS_FLG;
        CICOMACR.DATA.AC_DATE = CIRHIS.KEY.AC_DATE;
        CICOMACR.DATA.JRN_NO = CIRHIS.KEY.JRN_NO;
        CICOMACR.DATA.COMB_RES = CIRHIS.COMB_RES;
        CICOMACR.DATA.ELSE_RES = CIRHIS.ELSE_RES;
        CEP.TRC(SCCGWA, CICOMACR.DATA.PRI_CI);
        CEP.TRC(SCCGWA, CICOMACR.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICOMACR.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICOMACR.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICOMACR.DATA.STS);
        CEP.TRC(SCCGWA, CICOMACR.DATA.PROD_CD);
        CEP.TRC(SCCGWA, CICOMACR.DATA.CNTRCT_TYP);
        CEP.TRC(SCCGWA, CICOMACR.DATA.FRM_APP);
        CEP.TRC(SCCGWA, CICOMACR.DATA.CCY);
        CEP.TRC(SCCGWA, CICOMACR.DATA.AC_CNM);
        CEP.TRC(SCCGWA, CICOMACR.DATA.AC_ENM);
        CEP.TRC(SCCGWA, CICOMACR.DATA.OPN_BR);
        CEP.TRC(SCCGWA, CICOMACR.DATA.OWNER_BK);
        CEP.TRC(SCCGWA, CICOMACR.DATA.OPEN_DT);
        CEP.TRC(SCCGWA, CICOMACR.DATA.SMS_FLG);
        CEP.TRC(SCCGWA, CICOMACR.DATA.AC_DATE);
        CEP.TRC(SCCGWA, CICOMACR.DATA.JRN_NO);
        CEP.TRC(SCCGWA, CICOMACR.DATA.COMB_RES);
        CEP.TRC(SCCGWA, CICOMACR.DATA.ELSE_RES);
    }
    public void R000_DATA_TRANS_TO_FMTMPAG_AGT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOMAGT);
        CEP.TRC(SCCGWA, CIRAGT.KEY.AGT_NO);
        CICOMAGT.DATA.PRI_CI = CIRHIS.KEY.PRI_CI;
        CICOMAGT.DATA.CI_NO = CIRHIS.CI_NO;
        CICOMAGT.DATA.AGT_NO = CIRAGT.KEY.AGT_NO;
        CICOMAGT.DATA.AGT_TYP = CIRAGT.AGT_TYP;
        CICOMAGT.DATA.ENTY_TYP = CIRAGT.KEY.ENTY_TYP;
        CICOMAGT.DATA.ENTY_NO = CIRAGT.KEY.ENTY_NO;
        CICOMAGT.DATA.FRM_APP = CIRAGT.FRM_APP;
        CICOMAGT.DATA.AGT_LVL = CIRAGT.AGT_LVL;
        CICOMAGT.DATA.EFF_DATE = CIRAGT.EFF_DATE;
        CICOMAGT.DATA.EXP_DATE = CIRAGT.EXP_DATE;
        CICOMAGT.DATA.SGN_DATE = CIRAGT.SGN_DATE;
        CICOMAGT.DATA.AGT_STS = CIRAGT.AGT_STS;
        CICOMAGT.DATA.ORG_NO = CIRAGT.ORG_NO;
        CICOMAGT.DATA.SGN_CHNL = CIRAGT.SGN_CHNL;
        CICOMAGT.DATA.REMARK = CIRAGT.REMARK;
        CICOMAGT.DATA.AC_DATE = CIRHIS.KEY.AC_DATE;
        CICOMAGT.DATA.JRN_NO = CIRHIS.KEY.JRN_NO;
        CICOMAGT.DATA.COMB_RES = CIRHIS.COMB_RES;
        CICOMAGT.DATA.ELSE_RES = CIRHIS.ELSE_RES;
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_STARTBR_CITHIS_BY_CI() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRHIS.KEY.PRI_CI);
        CEP.TRC(SCCGWA, CIRHIS.CI_NO);
        CITHIS_BR.rp = new DBParm();
        CITHIS_BR.rp.TableName = "CITHIS";
        CITHIS_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRHIS, CITHIS_BR);
    }
    public void T000_STARTBR_CITHIS_BY_PRI() throws IOException,SQLException,Exception {
        CITHIS_BR.rp = new DBParm();
        CITHIS_BR.rp.TableName = "CITHIS";
        CITHIS_BR.rp.eqWhere = "PRI_CI";
        IBS.STARTBR(SCCGWA, CIRHIS, CITHIS_BR);
    }
    public void T000_READNEXT_CITHIS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRHIS, this, CITHIS_BR);
    }
    public void T000_ENDBR_CITHIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITHIS_BR);
    }
    public void T000_STARTBR_CITACR_BY_CI() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRACR, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void T000_STARTBR_CITAGT_BY_ENTY() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.eqWhere = "ENTY_TYP,ENTY_NO";
        IBS.STARTBR(SCCGWA, CIRAGT, CITAGT_BR);
    }
    public void T000_STARTBR_CITAGT_BY_CI() throws IOException,SQLException,Exception {
        CITAGT_BR.rp = new DBParm();
        CITAGT_BR.rp.TableName = "CITAGT";
        CITAGT_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRAGT, CITAGT_BR);
    }
    public void T000_READNEXT_CITAGT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRAGT, this, CITAGT_BR);
    }
    public void T000_ENDBR_CITAGT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITAGT_BR);
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
