package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9044 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICTCION CICTCION = new CICTCION();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9044_AWA_9044 CIB9044_AWA_9044;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT9044 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9044_AWA_9044>");
        CIB9044_AWA_9044 = (CIB9044_AWA_9044) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_CITCION_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9044_AWA_9044.AGR_NO);
        CEP.TRC(SCCGWA, CIB9044_AWA_9044.CI_NM_A);
        CEP.TRC(SCCGWA, CIB9044_AWA_9044.ID_TYP_A);
        CEP.TRC(SCCGWA, CIB9044_AWA_9044.ID_NO_A);
        CEP.TRC(SCCGWA, CIB9044_AWA_9044.CI_NO_N);
        CEP.TRC(SCCGWA, CIB9044_AWA_9044.ID_TYP_O);
        CEP.TRC(SCCGWA, CIB9044_AWA_9044.ID_NO_O);
        CEP.TRC(SCCGWA, CIB9044_AWA_9044.ID_TYP_N);
        CEP.TRC(SCCGWA, CIB9044_AWA_9044.ID_NO_N);
        CEP.TRC(SCCGWA, CIB9044_AWA_9044.BR);
        if (CIB9044_AWA_9044.AGR_NO.trim().length() == 0 
            && (CIB9044_AWA_9044.ID_TYP_A.trim().length() == 0 
            || CIB9044_AWA_9044.ID_NO_A.trim().length() == 0) 
            && CIB9044_AWA_9044.CI_NO_N.trim().length() == 0 
            && (CIB9044_AWA_9044.ID_TYP_O.trim().length() == 0 
            || CIB9044_AWA_9044.ID_NO_O.trim().length() == 0) 
            && (CIB9044_AWA_9044.ID_TYP_N.trim().length() == 0 
            || CIB9044_AWA_9044.ID_NO_N.trim().length() == 0)) {
            CEP.TRC(SCCGWA, "MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_INQ_CITCION_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICTCION);
        CICTCION.DATA.AGR_NO = CIB9044_AWA_9044.AGR_NO;
        CICTCION.DATA.CI_NM_A = CIB9044_AWA_9044.CI_NM_A;
        CICTCION.DATA.ID_TYP_A = CIB9044_AWA_9044.ID_TYP_A;
        CICTCION.DATA.ID_NO_A = CIB9044_AWA_9044.ID_NO_A;
        CICTCION.DATA.CI_NO_N = CIB9044_AWA_9044.CI_NO_N;
        CICTCION.DATA.ID_TYP_O = CIB9044_AWA_9044.ID_TYP_O;
        CICTCION.DATA.ID_NO_O = CIB9044_AWA_9044.ID_NO_O;
        CICTCION.DATA.ID_TYP_N = CIB9044_AWA_9044.ID_TYP_N;
        CICTCION.DATA.ID_NO_N = CIB9044_AWA_9044.ID_NO_N;
        CICTCION.DATA.BR = CIB9044_AWA_9044.BR;
        CICTCION.DATA.PAGE_ROW = CIB9044_AWA_9044.PAGE_ROW;
        CICTCION.DATA.PAGE_NUM = CIB9044_AWA_9044.PAGE_NUM;
        S000_CALL_CIZTCION();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZTCION() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZTCION", CICTCION);
        if (CICTCION.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICTCION.RC);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
