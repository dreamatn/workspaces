package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT8540 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICSGRLT CICSGRLT = new CICSGRLT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CIB8540_AWA_8540 CIB8540_AWA_8540;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIOT8540 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB8540_AWA_8540>");
        CIB8540_AWA_8540 = (CIB8540_AWA_8540) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, GWA_SC_AREA.BSP_APTYPE);
        CEP.TRC(SCCGWA, GWA_SC_AREA.BSP_BATNO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        IBS.init(SCCGWA, CICSGRLT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_ADD_SGRLT_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIB8540_AWA_8540.CI_NO);
        CEP.TRC(SCCGWA, CIB8540_AWA_8540.ID_TYPE);
        CEP.TRC(SCCGWA, CIB8540_AWA_8540.ID_NO);
        CEP.TRC(SCCGWA, CIB8540_AWA_8540.CI_NM);
        if (CIB8540_AWA_8540.CI_NO.trim().length() == 0 
            && (CIB8540_AWA_8540.ID_TYPE.trim().length() == 0 
            || CIB8540_AWA_8540.ID_NO.trim().length() == 0 
            || CIB8540_AWA_8540.CI_NM.trim().length() == 0)) {
            CEP.TRC(SCCGWA, "CI-NO OR IDNM MUST INPUT ONE");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_NO_OR_IDNM_MUST_INPT);
        }
        CEP.TRC(SCCGWA, CIB8540_AWA_8540.GRPS_NO);
        if (CIB8540_AWA_8540.GRPS_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "GRPS-NO MUST INPUT ");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_GRPS_NO_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CIB8540_AWA_8540.EFF_DATE);
        if (CIB8540_AWA_8540.EFF_DATE == 0) {
            CEP.TRC(SCCGWA, "EFF-DATE MUST INPUT");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_EFF_DT_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CIB8540_AWA_8540.EXP_DATE);
        if (CIB8540_AWA_8540.EXP_DATE == 0) {
            CEP.TRC(SCCGWA, "EXP-DATE MUST INPUT");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_EXP_DT_MUST_INPUT);
        }
        if (CIB8540_AWA_8540.EXP_DATE < CIB8540_AWA_8540.EFF_DATE 
            || CIB8540_AWA_8540.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            CEP.TRC(SCCGWA, "EXP-DATE INPUT ERROR");
            CEP.ERRC(SCCGWA, CICMSG_ERROR_MSG.CI_EXP_DT_INPUT_ERR);
        }
        CEP.ERR(SCCGWA);
    }
    public void B020_ADD_SGRLT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSGRLT);
        CICSGRLT.DATA.CI_NO = CIB8540_AWA_8540.CI_NO;
        CICSGRLT.DATA.ID_TYPE = CIB8540_AWA_8540.ID_TYPE;
        CICSGRLT.DATA.ID_NO = CIB8540_AWA_8540.ID_NO;
        CICSGRLT.DATA.CI_NM = CIB8540_AWA_8540.CI_NM;
        CICSGRLT.DATA.GRPS_NO = CIB8540_AWA_8540.GRPS_NO;
        CICSGRLT.DATA.EFF_DATE = CIB8540_AWA_8540.EFF_DATE;
        CICSGRLT.DATA.EXP_DATE = CIB8540_AWA_8540.EXP_DATE;
        S000_CALL_CIZSGRLT();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZSGRLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-GRLST", CICSGRLT);
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
