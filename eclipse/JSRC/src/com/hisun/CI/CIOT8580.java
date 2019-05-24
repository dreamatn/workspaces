package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8580 {
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
    CICSRMDT CICSRMDT = new CICSRMDT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8580_AWA_8580 CIB8580_AWA_8580;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT8580 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8580_AWA_8580>");
        CIB8580_AWA_8580 = (CIB8580_AWA_8580) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_CITRMDT_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB8580_AWA_8580.CI_NO);
        if (CIB8580_AWA_8580.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CIB8580_AWA_8580.RMD_TYP);
        if (CIB8580_AWA_8580.RMD_TYP.trim().length() == 0) {
            CEP.TRC(SCCGWA, "RMD-TYP MUST INPUT");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.ERR(SCCGWA);
    }
    public void B020_INQ_CITRMDT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSRMDT);
        CICSRMDT.DATA.CI_NO = CIB8580_AWA_8580.CI_NO;
        CICSRMDT.DATA.RMD_TYP = CIB8580_AWA_8580.RMD_TYP;
        S000_CALL_CIZSRMDT();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSRMDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-RMDT", CICSRMDT);
        if (CICSRMDT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSRMDT.RC);
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
