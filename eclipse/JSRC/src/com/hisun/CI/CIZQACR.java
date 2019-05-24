package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZQACR {
    brParm CITACR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    char WS_ACR_FLG = ' ';
    char WS_SHW_FLG = ' ';
    char WS_BROWSE_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRACR CIRACR = new CIRACR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICQACR CICQACR;
    public void MP(SCCGWA SCCGWA, CICQACR CICQACR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQACR = CICQACR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZQACR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CICQACR.RC.RC_MMO = "CI";
        CICQACR.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACR.FUNC);
        if (CICQACR.FUNC == 'I') {
            B030_CINO_BRW_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, 4);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CICQACR.AC_CNM.trim().length() > 0) {
            WS_BROWSE_FLG = 'C';
        }
        if (CICQACR.AC_CNM.trim().length() == 0 
            && CICQACR.AC_ENM.trim().length() > 0) {
            WS_BROWSE_FLG = 'E';
        }
        if (CICQACR.AC_CNM.trim().length() == 0 
            && CICQACR.AC_ENM.trim().length() == 0) {
            WS_BROWSE_FLG = 'N';
        }
    }
    public void B020_ACRNM_BRW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        if (WS_BROWSE_FLG == 'N') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR);
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR);
        }
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            B021_FILTER_AC();
            if (pgmRtn) return;
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B021_FILTER_AC() throws IOException,SQLException,Exception {
        WS_SHW_FLG = 'Y';
        if (CICQACR.CLS_FLG == 'N' 
            && CIRACR.STS == '1') {
            WS_SHW_FLG = 'N';
        }
        if (WS_SHW_FLG == 'Y' 
            && CICQACR.FRM_APP.trim().length() > 0 
            && !CICQACR.FRM_APP.equalsIgnoreCase(CIRACR.FRM_APP)) {
            WS_SHW_FLG = 'N';
        }
        if (WS_SHW_FLG == 'Y' 
            && CICQACR.ENTY_TYP != ' ' 
            && CICQACR.ENTY_TYP != CIRACR.ENTY_TYP) {
            WS_SHW_FLG = 'N';
        }
        if (WS_SHW_FLG == 'Y' 
            && CICQACR.I_CNTRCT_TYP.trim().length() > 0 
            && !CICQACR.I_CNTRCT_TYP.equalsIgnoreCase(CIRACR.CNTRCT_TYP)) {
            WS_SHW_FLG = 'N';
        }
        if (WS_SHW_FLG == 'Y') {
            CICQACR.CNT = (short) (CICQACR.CNT + 1);
            if (CICQACR.CNT <= 999) {
                CICQACR.ACS[CICQACR.CNT-1].AC_NO = CIRACR.KEY.AGR_NO;
                CICQACR.ACS[CICQACR.CNT-1].O_CNTRCT_TYP = CIRACR.CNTRCT_TYP;
                CEP.TRC(SCCGWA, CICQACR.ACS[CICQACR.CNT-1].AC_NO);
            }
        }
    }
    public void B030_CINO_BRW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICQACR.CI_NO;
        T000_STARTBR_CITACR_BYNO();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            B021_FILTER_AC();
            if (pgmRtn) return;
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_CITACR_BYNO() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.where = "CI_NO = :CIRACR.CI_NO";
        CITACR_BR.rp.order = "AGR_NO";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICQACR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICQACR=");
            CEP.TRC(SCCGWA, CICQACR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
