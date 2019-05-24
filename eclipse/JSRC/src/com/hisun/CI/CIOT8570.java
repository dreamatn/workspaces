package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8570 {
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
    CICSCION CICSCION = new CICSCION();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8570_AWA_8570 CIB8570_AWA_8570;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT8570 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8570_AWA_8570>");
        CIB8570_AWA_8570 = (CIB8570_AWA_8570) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        CEP.TRC(SCCGWA, CIB8570_AWA_8570.AGR_NO);
        CEP.TRC(SCCGWA, CIB8570_AWA_8570.CI_NM_A);
        CEP.TRC(SCCGWA, CIB8570_AWA_8570.ID_TYP_A);
        CEP.TRC(SCCGWA, CIB8570_AWA_8570.ID_NO_A);
        CEP.TRC(SCCGWA, CIB8570_AWA_8570.CI_NO_N);
        CEP.TRC(SCCGWA, CIB8570_AWA_8570.ID_TYP_O);
        CEP.TRC(SCCGWA, CIB8570_AWA_8570.ID_NO_O);
        CEP.TRC(SCCGWA, CIB8570_AWA_8570.ID_TYP_N);
        CEP.TRC(SCCGWA, CIB8570_AWA_8570.ID_NO_N);
        CEP.TRC(SCCGWA, CIB8570_AWA_8570.BR);
        if (CIB8570_AWA_8570.AGR_NO.trim().length() == 0 
            && (CIB8570_AWA_8570.ID_TYP_A.trim().length() == 0 
            || CIB8570_AWA_8570.ID_NO_A.trim().length() == 0) 
            && CIB8570_AWA_8570.CI_NO_N.trim().length() == 0 
            && (CIB8570_AWA_8570.ID_TYP_O.trim().length() == 0 
            || CIB8570_AWA_8570.ID_NO_O.trim().length() == 0) 
            && (CIB8570_AWA_8570.ID_TYP_N.trim().length() == 0 
            || CIB8570_AWA_8570.ID_NO_N.trim().length() == 0)) {
            CEP.TRC(SCCGWA, "MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B020_INQ_CITCION_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSCION);
        CICSCION.DATA.AGR_NO = CIB8570_AWA_8570.AGR_NO;
        CICSCION.DATA.CI_NM_A = CIB8570_AWA_8570.CI_NM_A;
        CICSCION.DATA.ID_TYP_A = CIB8570_AWA_8570.ID_TYP_A;
        CICSCION.DATA.ID_NO_A = CIB8570_AWA_8570.ID_NO_A;
        CICSCION.DATA.CI_NO_N = CIB8570_AWA_8570.CI_NO_N;
        CICSCION.DATA.ID_TYP_O = CIB8570_AWA_8570.ID_TYP_O;
        CICSCION.DATA.ID_NO_O = CIB8570_AWA_8570.ID_NO_O;
        CICSCION.DATA.ID_TYP_N = CIB8570_AWA_8570.ID_TYP_N;
        CICSCION.DATA.ID_NO_N = CIB8570_AWA_8570.ID_NO_N;
        CICSCION.DATA.BR = CIB8570_AWA_8570.BR;
        S000_CALL_CIZSCION();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSCION() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CION", CICSCION);
        if (CICSCION.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSCION.RC);
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
