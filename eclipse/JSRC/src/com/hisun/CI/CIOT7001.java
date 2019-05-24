package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT7001 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    CIOT7001_WS_RC WS_RC = new CIOT7001_WS_RC();
    int WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICCUST CICCUST = new CICCUST();
    CICOCUST CICOCUST = new CICOCUST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB7001_AWA_7001 CIB7001_AWA_7001;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT7001 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB7001_AWA_7001>");
        CIB7001_AWA_7001 = (CIB7001_AWA_7001) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICCUST);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQUIRE_CUST();
        if (pgmRtn) return;
        B030_FMT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB7001_AWA_7001.CI_NO);
        CEP.TRC(SCCGWA, CIB7001_AWA_7001.ENTY_TYP);
        CEP.TRC(SCCGWA, CIB7001_AWA_7001.AGR_NO);
        CEP.TRC(SCCGWA, CIB7001_AWA_7001.ID_TYP);
        CEP.TRC(SCCGWA, CIB7001_AWA_7001.ID_NO);
        CEP.TRC(SCCGWA, CIB7001_AWA_7001.CI_NM);
        if (CIB7001_AWA_7001.CI_NO.trim().length() > 0) {
            CICCUST.FUNC = 'C';
        } else if (CIB7001_AWA_7001.ENTY_TYP != ' ' 
                && CIB7001_AWA_7001.AGR_NO.trim().length() > 0) {
            CICCUST.FUNC = 'A';
        } else if (CIB7001_AWA_7001.ID_TYP.trim().length() > 0 
                && CIB7001_AWA_7001.ID_NO.trim().length() > 0 
                && CIB7001_AWA_7001.CI_NM.trim().length() > 0) {
            CICCUST.FUNC = 'B';
        } else {
            CEP.TRC(SCCGWA, "INPUT ERROR");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR);
        }
    }
    public void B020_INQUIRE_CUST() throws IOException,SQLException,Exception {
        CICCUST.DATA.CI_NO = CIB7001_AWA_7001.CI_NO;
        CICCUST.DATA.AGR_NO = CIB7001_AWA_7001.AGR_NO;
        CICCUST.DATA.ID_TYPE = CIB7001_AWA_7001.ID_TYP;
        CICCUST.DATA.ID_NO = CIB7001_AWA_7001.ID_NO;
        CICCUST.DATA.CI_NM = CIB7001_AWA_7001.CI_NM;
        CEP.TRC(SCCGWA, "DYQ ADD FOR TEST");
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void B030_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOCUST);
        CICOCUST.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CICOCUST.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        CICOCUST.ID_NO = CICCUST.O_DATA.O_ID_NO;
        CICOCUST.CI_NM = CICCUST.O_DATA.O_CI_NM;
        CICOCUST.CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        CICOCUST.CI_ATTR = CICCUST.O_DATA.O_CI_ATTR;
        CICOCUST.STSW = CICCUST.O_DATA.O_STSW;
        CICOCUST.RESIDENT = CICCUST.O_DATA.O_RESIDENT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CI106";
        SCCFMT.DATA_PTR = CICOCUST;
        SCCFMT.DATA_LEN = 422;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
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
