package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9006 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICSNCD CICSNCD = new CICSNCD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9006_AWA_9006 CIB9006_AWA_9006;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT9006 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9006_AWA_9006>");
        CIB9006_AWA_9006 = (CIB9006_AWA_9006) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSNCD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQUIRE_XXT_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9006_AWA_9006.CI_NO);
        CEP.TRC(SCCGWA, CIB9006_AWA_9006.AGR_NO);
        CEP.TRC(SCCGWA, CIB9006_AWA_9006.PAGE_NUM);
        if (CIB9006_AWA_9006.PAGE_NUM == 0) {
            CEP.TRC(SCCGWA, "PAGE-NUM INPUT ERROR");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CIB9006_AWA_9006.PAGE_ROW);
        if (CIB9006_AWA_9006.PAGE_ROW == 0) {
            CEP.TRC(SCCGWA, "PAGE-ROW INPUT ERROR");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        CEP.ERR(SCCGWA);
    }
    public void B020_INQUIRE_XXT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSNCD);
        CICSNCD.DATA.AGR_NO = CIB9006_AWA_9006.AGR_NO;
        CICSNCD.DATA.CI_NO = CIB9006_AWA_9006.CI_NO;
        CICSNCD.DATA.PROD_CD = CIB9006_AWA_9006.PROD_CD;
        CICSNCD.DATA.OPN_DT_B = CIB9006_AWA_9006.OPN_DT_B;
        CICSNCD.DATA.OPN_DT_E = CIB9006_AWA_9006.OPN_DT_E;
        CICSNCD.DATA.AC_STS = CIB9006_AWA_9006.AC_STS;
        CICSNCD.DATA.PAGE_NUM = CIB9006_AWA_9006.PAGE_NUM;
        CICSNCD.DATA.PAGE_ROW = CIB9006_AWA_9006.PAGE_ROW;
        S000_CALL_CIZSNCD();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSNCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-NCD-INF", CICSNCD);
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
