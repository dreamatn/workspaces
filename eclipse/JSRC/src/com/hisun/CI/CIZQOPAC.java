package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZQOPAC {
    brParm CITOPAC_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIROPAC CIROPAC = new CIROPAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICQOPAC CICQOPAC;
    public void MP(SCCGWA SCCGWA, CICQOPAC CICQOPAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQOPAC = CICQOPAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZQOPAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQUIRE_OPAC_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQOPAC.DATA.AC_TYP);
        CEP.TRC(SCCGWA, CICQOPAC.DATA.AC_DATE);
        CEP.TRC(SCCGWA, CICQOPAC.DATA.JRN_NO);
        CEP.TRC(SCCGWA, CICQOPAC.DATA.DEAL_FLG);
        if (CICQOPAC.DATA.AC_TYP.trim().length() == 0 
            || CICQOPAC.DATA.AC_DATE == 0 
            || CICQOPAC.DATA.JRN_NO == 0 
            || CICQOPAC.DATA.DEAL_FLG == ' ') {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQOPAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQUIRE_OPAC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIROPAC);
        CIROPAC.KEY.AC_TYP = CICQOPAC.DATA.AC_TYP;
        CIROPAC.AC_DATE = CICQOPAC.DATA.AC_DATE;
        CIROPAC.JRN_NO = CICQOPAC.DATA.JRN_NO;
        CIROPAC.DEAL_FLG = CICQOPAC.DATA.DEAL_FLG;
        T000_STARTBR_CITOPAC();
        if (pgmRtn) return;
        T000_READNEXT_CITOPAC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_I = (short) (WS_I + 1);
            CICQOPAC.AGR_NO_AREA[WS_I-1].AC_NO = CIROPAC.KEY.AC_NO;
            T000_READNEXT_CITOPAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITOPAC();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_CITOPAC() throws IOException,SQLException,Exception {
        CITOPAC_BR.rp = new DBParm();
        CITOPAC_BR.rp.TableName = "CITOPAC";
        CITOPAC_BR.rp.eqWhere = "AC_TYP,AC_DATE,JRN_NO,DEAL_FLG";
        IBS.STARTBR(SCCGWA, CIROPAC, CITOPAC_BR);
    }
    public void T000_READNEXT_CITOPAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIROPAC, this, CITOPAC_BR);
    }
    public void T000_ENDBR_CITOPAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITOPAC_BR);
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
