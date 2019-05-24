package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9041 {
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
    CICSFAC CICSFAC = new CICSFAC();
    CICFA41 CICFA41 = new CICFA41();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9041_AWA_9041 CIB9041_AWA_9041;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT9041 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9041_AWA_9041>");
        CIB9041_AWA_9041 = (CIB9041_AWA_9041) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_CISFAC_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_INQ_CISFAC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSFAC);
        CICSFAC.DATA.ID_TYPE = CIB9041_AWA_9041.ID_TYPE;
        CICSFAC.DATA.ID_NO = CIB9041_AWA_9041.ID_NO;
        CICSFAC.DATA.CI_NM = CIB9041_AWA_9041.CI_NM;
        CICSFAC.DATA.CI_NO = CIB9041_AWA_9041.CI_NO;
        S000_CALL_CIZSFAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSFAC.DATA.FIRST_NUM);
        CEP.TRC(SCCGWA, CICSFAC.DATA.NO_ACT_NUM);
        IBS.init(SCCGWA, CICFA41);
        CICFA41.CI_NO = CICSFAC.DATA.CI_NO;
        CICFA41.FIRST_NUM = CICSFAC.DATA.FIRST_NUM;
        CICFA41.NO_ACT_NUM = CICSFAC.DATA.NO_ACT_NUM;
        CICFA41.HOLD_NUM = CICSFAC.DATA.HOLD_NUM;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIA41";
        SCCFMT.DATA_PTR = CICFA41;
        SCCFMT.DATA_LEN = 30;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZSFAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-FIRST-AC", CICSFAC);
        if (CICSFAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSFAC.RC);
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
