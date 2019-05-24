package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9043 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICQLIS CICQLIS = new CICQLIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9043_AWA_9043 CIB9043_AWA_9043;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT9043 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9043_AWA_9043>");
        CIB9043_AWA_9043 = (CIB9043_AWA_9043) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_LIST_CHSTS_INQ_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9043_AWA_9043.AGR_NO);
        CEP.TRC(SCCGWA, CIB9043_AWA_9043.CI_NO);
        CEP.TRC(SCCGWA, CIB9043_AWA_9043.ID_TYPE);
        CEP.TRC(SCCGWA, CIB9043_AWA_9043.ID_NO);
        CEP.TRC(SCCGWA, CIB9043_AWA_9043.CI_NM);
        if (CIB9043_AWA_9043.AGR_NO.trim().length() == 0 
            && CIB9043_AWA_9043.CI_NO.trim().length() == 0 
            && (CIB9043_AWA_9043.ID_TYPE.trim().length() == 0 
            || CIB9043_AWA_9043.ID_NO.trim().length() == 0 
            || CIB9043_AWA_9043.CI_NM.trim().length() == 0)) {
            CEP.TRC(SCCGWA, "MUST INPUT ALL IS NULL");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "账号、客户号、证件信息三者皆�?");
        }
    }
    public void B020_LIST_CHSTS_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQLIS);
        CICQLIS.DATA.CI_NO = CIB9043_AWA_9043.CI_NO;
        CICQLIS.DATA.ID_TYPE = CIB9043_AWA_9043.ID_TYPE;
        CICQLIS.DATA.ID_NO = CIB9043_AWA_9043.ID_NO;
        CICQLIS.DATA.CI_NM = CIB9043_AWA_9043.CI_NM;
        CICQLIS.DATA.AGR_NO = CIB9043_AWA_9043.AGR_NO;
        CICQLIS.DATA.LST_TYP = CIB9043_AWA_9043.LST_TYP;
        CICQLIS.DATA.CHK_STS = CIB9043_AWA_9043.CHK_STS;
        CICQLIS.DATA.FUNC = CIB9043_AWA_9043.FUNC;
        CICQLIS.DATA.PAGE_NUM = CIB9043_AWA_9043.PAGE_NUM;
        CICQLIS.DATA.PAGE_ROW = CIB9043_AWA_9043.PAGE_ROW;
        S000_CALL_CIZQLIS_PROC();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZQLIS_PROC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-QLS-INF", CICQLIS);
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
