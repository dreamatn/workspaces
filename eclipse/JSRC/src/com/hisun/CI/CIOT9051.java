package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT9051 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    String WS_AGR_NO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICQACAC CICQACAC = new CICQACAC();
    CICSACTL CICSACTL = new CICSACTL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB9051_AWA_9051 CIB9051_AWA_9051;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT9051 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB9051_AWA_9051>");
        CIB9051_AWA_9051 = (CIB9051_AWA_9051) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_CISACTL_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9051_AWA_9051.AGR_NO);
        if (CIB9051_AWA_9051.AGR_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "AGR-NO MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_AC_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CIB9051_AWA_9051.FUNC);
        if (CIB9051_AWA_9051.FUNC == ' ') {
            CEP.TRC(SCCGWA, "FUNC MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR);
        }
        CEP.TRC(SCCGWA, CIB9051_AWA_9051.CTL_POS);
        if (CIB9051_AWA_9051.CTL_POS.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CTL-POS MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "状�?�控制位必须输入");
        }
    }
    public void B020_INQ_CISACTL_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB9051_AWA_9051.AGR_NO);
        CEP.TRC(SCCGWA, CIB9051_AWA_9051.SEQ);
        CEP.TRC(SCCGWA, CIB9051_AWA_9051.CCY);
        CEP.TRC(SCCGWA, CIB9051_AWA_9051.CCY_TYP);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = CIB9051_AWA_9051.AGR_NO;
        CICQACAC.DATA.AGR_SEQ = CIB9051_AWA_9051.SEQ;
        CICQACAC.DATA.CCY_ACAC = CIB9051_AWA_9051.CCY;
        CICQACAC.DATA.CR_FLG = CIB9051_AWA_9051.CCY_TYP;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO.trim().length() == 0 
            && CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO.trim().length() > 0) {
            WS_AGR_NO = CICQACAC.O_DATA.O_ACR_DATA.O_REL_AC_NO;
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = WS_AGR_NO;
            CICQACAC.DATA.AGR_SEQ = CIB9051_AWA_9051.SEQ;
            CICQACAC.DATA.CCY_ACAC = CIB9051_AWA_9051.CCY;
            CICQACAC.DATA.CR_FLG = CIB9051_AWA_9051.CCY_TYP;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, CICSACTL);
        CICSACTL.DATA.ACAC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CICSACTL.DATA.FUNC = CIB9051_AWA_9051.FUNC;
        CICSACTL.DATA.CTL_POS = CIB9051_AWA_9051.CTL_POS;
        CICSACTL.DATA.FUN_POS = CIB9051_AWA_9051.FUN_POS;
        S000_CALL_CIZSACTL();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSACTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-ACAC-CTL", CICSACTL);
        if (CICSACTL.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSACTL.RC);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
