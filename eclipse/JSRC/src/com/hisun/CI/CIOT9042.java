package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9042 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICSLCH CICSLCH = new CICSLCH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9042_AWA_9042 CIB9042_AWA_9042;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT9042 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9042_AWA_9042>");
        CIB9042_AWA_9042 = (CIB9042_AWA_9042) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_LIST_CHECK_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9042_AWA_9042.AGR_NO);
        CEP.TRC(SCCGWA, CIB9042_AWA_9042.LST_TYP);
        CEP.TRC(SCCGWA, CIB9042_AWA_9042.AC_TYP);
        if (CIB9042_AWA_9042.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AGR-NO INPUT IS NULL");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        if (CIB9042_AWA_9042.LST_TYP.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LST-TYP INPUT IS NULL");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        if (CIB9042_AWA_9042.AC_TYP == ' ') {
            CEP.TRC(SCCGWA, "AC-TYP INPUT IS NULL");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.ERR(SCCGWA);
    }
    public void B020_LIST_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSLCH);
        CICSLCH.AGR_NO = CIB9042_AWA_9042.AGR_NO;
        CICSLCH.LST_TYP = CIB9042_AWA_9042.LST_TYP;
        CICSLCH.AC_TYP = CIB9042_AWA_9042.AC_TYP;
        CICSLCH.TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSLCH.TL_ID = SCCGWA.COMM_AREA.TL_ID;
        CICSLCH.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_CIZSLCH_PROC();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSLCH_PROC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-LCH-INF", CICSLCH);
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
