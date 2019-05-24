package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZTCION {
    brParm CITCION_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    int WS_I = 0;
    int WS_PAGE_ROW = 0;
    int WS_CURRENT_ROW = 0;
    int WS_MIN_ROW = 0;
    int WS_MAX_ROW = 0;
    int WS_RECORD_NUM = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRCION CIRCION = new CIRCION();
    CICFA44 CICFA44 = new CICFA44();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICTCION CICTCION;
    public void MP(SCCGWA SCCGWA, CICTCION CICTCION) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICTCION = CICTCION;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZTCION return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICTCION.RC);
        IBS.init(SCCGWA, CICFA44);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_COMPUTE_OUTPUT_ROW();
        if (pgmRtn) return;
        B030_INQ_CITCION_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICTCION.DATA.PAGE_ROW);
        if (CICTCION.DATA.PAGE_ROW > K_MAX_ROW) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICTCION.DATA.PAGE_ROW;
        }
    }
    public void B020_COMPUTE_OUTPUT_ROW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICTCION.DATA.PAGE_NUM);
        WS_MIN_ROW = ( CICTCION.DATA.PAGE_NUM - 1 ) * WS_PAGE_ROW + 1;
        CEP.TRC(SCCGWA, WS_MIN_ROW);
        WS_MAX_ROW = CICTCION.DATA.PAGE_NUM * WS_PAGE_ROW;
        CEP.TRC(SCCGWA, WS_MAX_ROW);
        WS_CURRENT_ROW = 1;
        WS_I = 1;
    }
    public void B030_INQ_CITCION_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCION);
        if (CICTCION.DATA.AGR_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICTCION.DATA.AGR_NO);
            CIRCION.AGR_NO = CICTCION.DATA.AGR_NO;
            T000_STARTBR_CITCION_BY_AC();
            if (pgmRtn) return;
        } else if (CICTCION.DATA.CI_NO_N.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICTCION.DATA.CI_NO_N);
            CIRCION.CI_NO_NEW = CICTCION.DATA.CI_NO_N;
            T000_STARTBR_CITCION_BY_CI();
            if (pgmRtn) return;
        } else if (CICTCION.DATA.ID_TYP_A.trim().length() > 0 
                && CICTCION.DATA.ID_NO_A.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICTCION.DATA.ID_TYP_A);
            CEP.TRC(SCCGWA, CICTCION.DATA.ID_NO_A);
            CIRCION.ID_TYP_AC = CICTCION.DATA.ID_TYP_A;
            CIRCION.ID_NO_AC = CICTCION.DATA.ID_NO_A;
            T000_STARTBR_CITCION_BY_ID_A();
            if (pgmRtn) return;
        } else if (CICTCION.DATA.ID_TYP_O.trim().length() > 0 
                && CICTCION.DATA.ID_NO_O.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICTCION.DATA.ID_TYP_O);
            CEP.TRC(SCCGWA, CICTCION.DATA.ID_NO_O);
            CIRCION.ID_TYP_OLD = CICTCION.DATA.ID_TYP_O;
            CIRCION.ID_NO_OLD = CICTCION.DATA.ID_NO_O;
            T000_STARTBR_CITCION_BY_ID_O();
            if (pgmRtn) return;
        } else if (CICTCION.DATA.ID_TYP_N.trim().length() > 0 
                && CICTCION.DATA.ID_NO_N.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICTCION.DATA.ID_TYP_N);
            CEP.TRC(SCCGWA, CICTCION.DATA.ID_NO_O);
            CIRCION.ID_TYP_NEW = CICTCION.DATA.ID_TYP_N;
            CIRCION.ID_NO_NEW = CICTCION.DATA.ID_NO_N;
            T000_STARTBR_CITCION_BY_ID_N();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        T000_READNEXT_CITCION();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            if (WS_CURRENT_ROW >= WS_MIN_ROW 
                && WS_CURRENT_ROW <= WS_MAX_ROW 
                && WS_I <= WS_PAGE_ROW) {
                CICFA44.DATA[WS_I-1].AGR_NO = CIRCION.AGR_NO;
                CICFA44.DATA[WS_I-1].ENTY_TYP = CIRCION.ENTY_TYP;
                CICFA44.DATA[WS_I-1].BR = CIRCION.OWNER_BR;
                CICFA44.DATA[WS_I-1].CI_NO_OLD = CIRCION.CI_NO_OLD;
                CICFA44.DATA[WS_I-1].ID_TYP_OLD = CIRCION.ID_TYP_OLD;
                CICFA44.DATA[WS_I-1].ID_NO_OLD = CIRCION.ID_NO_OLD;
                CICFA44.DATA[WS_I-1].CI_NM_OLD = CIRCION.CI_NM_OLD;
                CICFA44.DATA[WS_I-1].CI_NO_NEW = CIRCION.CI_NO_NEW;
                CICFA44.DATA[WS_I-1].ID_TYP_NEW = CIRCION.ID_TYP_NEW;
                CICFA44.DATA[WS_I-1].ID_NO_NEW = CIRCION.ID_NO_NEW;
                CICFA44.DATA[WS_I-1].CI_NM_NEW = CIRCION.CI_NM_NEW;
                CICFA44.DATA[WS_I-1].ID_TYP_AC = CIRCION.ID_TYP_AC;
                CICFA44.DATA[WS_I-1].ID_NO_AC = CIRCION.ID_NO_AC;
                CICFA44.DATA[WS_I-1].CI_NM_AC = CIRCION.CI_NM_AC;
                WS_I = WS_I + 1;
            }
            WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
            T000_READNEXT_CITCION();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCION();
        if (pgmRtn) return;
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        WS_CURRENT_ROW = WS_CURRENT_ROW - 1;
        WS_I = WS_I - 1;
        CICFA44.TOTAL_NUM = WS_CURRENT_ROW;
        WS_RECORD_NUM = WS_CURRENT_ROW % WS_PAGE_ROW;
        CICFA44.TOTAL_PAGE = (int) ((WS_CURRENT_ROW - WS_RECORD_NUM) / WS_PAGE_ROW);
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        if (WS_RECORD_NUM > 0) {
            CICFA44.TOTAL_PAGE = CICFA44.TOTAL_PAGE + 1;
        }
        CICFA44.CURR_PAGE = CICTCION.DATA.PAGE_NUM;
        CICFA44.PAGE_ROW = WS_I;
        if (CICFA44.CURR_PAGE >= CICFA44.TOTAL_PAGE 
            || CICFA44.TOTAL_PAGE == 0) {
            CICFA44.LAST_PAGE = 'Y';
        } else {
            CICFA44.LAST_PAGE = 'N';
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIA44";
        SCCFMT.DATA_PTR = CICFA44;
        SCCFMT.DATA_LEN = 5035;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_CITCION_BY_AC() throws IOException,SQLException,Exception {
        CITCION_BR.rp = new DBParm();
        CITCION_BR.rp.TableName = "CITCION";
        CITCION_BR.rp.eqWhere = "AGR_NO";
        IBS.STARTBR(SCCGWA, CIRCION, CITCION_BR);
    }
    public void T000_STARTBR_CITCION_BY_CI() throws IOException,SQLException,Exception {
        CITCION_BR.rp = new DBParm();
        CITCION_BR.rp.TableName = "CITCION";
        CITCION_BR.rp.eqWhere = "CI_NO_NEW";
        IBS.STARTBR(SCCGWA, CIRCION, CITCION_BR);
    }
    public void T000_STARTBR_CITCION_BY_ID_A() throws IOException,SQLException,Exception {
        CITCION_BR.rp = new DBParm();
        CITCION_BR.rp.TableName = "CITCION";
        CITCION_BR.rp.eqWhere = "ID_TYP_AC , ID_NO_AC";
        IBS.STARTBR(SCCGWA, CIRCION, CITCION_BR);
    }
    public void T000_STARTBR_CITCION_BY_ID_O() throws IOException,SQLException,Exception {
        CITCION_BR.rp = new DBParm();
        CITCION_BR.rp.TableName = "CITCION";
        CITCION_BR.rp.eqWhere = "ID_TYP_OLD , ID_NO_OLD";
        IBS.STARTBR(SCCGWA, CIRCION, CITCION_BR);
    }
    public void T000_STARTBR_CITCION_BY_ID_N() throws IOException,SQLException,Exception {
        CITCION_BR.rp = new DBParm();
        CITCION_BR.rp.TableName = "CITCION";
        CITCION_BR.rp.eqWhere = "ID_TYP_NEW , ID_NO_NEW";
        IBS.STARTBR(SCCGWA, CIRCION, CITCION_BR);
    }
    public void T000_READNEXT_CITCION() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCION, this, CITCION_BR);
    }
    public void T000_ENDBR_CITCION() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCION_BR);
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
