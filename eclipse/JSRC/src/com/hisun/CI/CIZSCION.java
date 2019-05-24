package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSCION {
    brParm CITCION_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRCION CIRCION = new CIRCION();
    CICOCION CICOCION = new CICOCION();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSCION CICSCION;
    public void MP(SCCGWA SCCGWA, CICSCION CICSCION) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSCION = CICSCION;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSCION return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSCION.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_CITCION_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_CITCION_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCION);
        if (CICSCION.DATA.AGR_NO.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICSCION.DATA.AGR_NO);
            CIRCION.AGR_NO = CICSCION.DATA.AGR_NO;
            T000_STARTBR_CITCION_BY_AC();
            if (pgmRtn) return;
        } else if (CICSCION.DATA.CI_NO_N.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICSCION.DATA.CI_NO_N);
            CIRCION.CI_NO_NEW = CICSCION.DATA.CI_NO_N;
            T000_STARTBR_CITCION_BY_CI();
            if (pgmRtn) return;
        } else if (CICSCION.DATA.ID_TYP_A.trim().length() > 0 
                && CICSCION.DATA.ID_NO_A.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICSCION.DATA.ID_TYP_A);
            CEP.TRC(SCCGWA, CICSCION.DATA.ID_NO_A);
            CIRCION.ID_TYP_AC = CICSCION.DATA.ID_TYP_A;
            CIRCION.ID_NO_AC = CICSCION.DATA.ID_NO_A;
            T000_STARTBR_CITCION_BY_ID_A();
            if (pgmRtn) return;
        } else if (CICSCION.DATA.ID_TYP_O.trim().length() > 0 
                && CICSCION.DATA.ID_NO_O.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICSCION.DATA.ID_TYP_O);
            CEP.TRC(SCCGWA, CICSCION.DATA.ID_NO_O);
            CIRCION.ID_TYP_OLD = CICSCION.DATA.ID_TYP_O;
            CIRCION.ID_NO_OLD = CICSCION.DATA.ID_NO_O;
            T000_STARTBR_CITCION_BY_ID_O();
            if (pgmRtn) return;
        } else if (CICSCION.DATA.ID_TYP_N.trim().length() > 0 
                && CICSCION.DATA.ID_NO_N.trim().length() > 0) {
            CEP.TRC(SCCGWA, CICSCION.DATA.ID_TYP_N);
            CEP.TRC(SCCGWA, CICSCION.DATA.ID_NO_O);
            CIRCION.ID_TYP_NEW = CICSCION.DATA.ID_TYP_N;
            CIRCION.ID_NO_NEW = CICSCION.DATA.ID_NO_N;
            T000_STARTBR_CITCION_BY_ID_N();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        T000_READNEXT_CITCION();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            R000_01_OUT_TITLE();
            if (pgmRtn) return;
        } else {
            Z_RET();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            CICOCION.DATA.AGR_NO = CIRCION.AGR_NO;
            CICOCION.DATA.ENTY_TYP = CIRCION.ENTY_TYP;
            CICOCION.DATA.BR = CIRCION.OWNER_BR;
            CICOCION.DATA.CI_NO_OLD = CIRCION.CI_NO_OLD;
            CICOCION.DATA.ID_TYP_OLD = CIRCION.ID_TYP_OLD;
            CICOCION.DATA.ID_NO_OLD = CIRCION.ID_NO_OLD;
            CICOCION.DATA.CI_NM_OLD = CIRCION.CI_NM_OLD;
            CICOCION.DATA.CI_NO_NEW = CIRCION.CI_NO_NEW;
            CICOCION.DATA.ID_TYP_NEW = CIRCION.ID_TYP_NEW;
            CICOCION.DATA.ID_NO_NEW = CIRCION.ID_NO_NEW;
            CICOCION.DATA.CI_NM_NEW = CIRCION.CI_NM_NEW;
            CICOCION.DATA.ID_TYP_AC = CIRCION.ID_TYP_AC;
            CICOCION.DATA.ID_NO_AC = CIRCION.ID_NO_AC;
            CICOCION.DATA.CI_NM_AC = CIRCION.CI_NM_AC;
            R000_02_OUTPUT_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITCION();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCION();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOCION);
        SCCMPAG.DATA_LEN = 504;
        CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
        B_MPAG();
        if (pgmRtn) return;
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
