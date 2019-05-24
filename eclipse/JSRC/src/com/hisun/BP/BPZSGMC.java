package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSGMC {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_I = 0;
    String WS_MSG_ERR = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCSIC BPCSIC = new BPCSIC();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPRGFHIS BPRGFHIS = new BPRGFHIS();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    SCCGWA SCCGWA;
    BPCSGMC BPCSGMC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSGMC BPCSGMC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSGMC = BPCSGMC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSGMC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSGMC.INFO);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_ADD_EWA_ENTY();
        if (pgmRtn) return;
        B030_WRITE_HISTORY();
        if (pgmRtn) return;
        B040_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSIC);
        BPCSIC.CNTR_TYPE = BPCSGMC.INFO.CNTR_TYP;
        BPCSIC.FUNC = "11";
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-SIM-INPUT-CHK";
        SCCCALL.COMMAREA_PTR = BPCSIC;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        IBS.init(SCCGWA, BPCSIC);
        BPCSIC.FUNC = "03";
        BPCSIC.CCY = BPCSGMC.INFO.CCY;
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-SIM-INPUT-CHK";
        SCCCALL.COMMAREA_PTR = BPCSIC;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        IBS.init(SCCGWA, BPCSIC);
        BPCSIC.FUNC = "10";
        BPCSIC.BR = BPCSGMC.INFO.BR;
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-SIM-INPUT-CHK";
        SCCCALL.COMMAREA_PTR = BPCSIC;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void B020_ADD_EWA_ENTY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.MOD_NO = BPCSGMC.INFO.MOD_NO;
        BPCPOEWA.DATA.CNTR_TYPE = BPCSGMC.INFO.CNTR_TYP;
        BPCPOEWA.DATA.EVENT_CODE = "GMC";
        BPCPOEWA.DATA.BR_OLD = BPCSGMC.INFO.BR;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPCSGMC.INFO.CCY;
        BPCPOEWA.DATA.CI_NO = BPCSGMC.INFO.CI_NO;
        BPCPOEWA.DATA.AC_NO = BPCSGMC.INFO.AC_NO;
        BPCPOEWA.DATA.REF_NO = BPCSGMC.INFO.REF;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            BPCPOEWA.DATA.GLAC_INFO[WS_I-1].GLM1 = BPCSGMC.INFO.GLMS[WS_I-1].GLM1;
            BPCPOEWA.DATA.GLAC_INFO[WS_I-1].GLM2 = BPCSGMC.INFO.GLMS[WS_I-1].GLM2;
        }
        for (WS_I = 1; WS_I <= 76; WS_I += 1) {
            BPCPOEWA.DATA.AMT_INFO[WS_I-1].AMT = BPCSGMC.INFO.AMTS[WS_I-1].AMT;
        }
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B030_WRITE_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        IBS.init(SCCGWA, BPRGFHIS);
        BPRGFHIS.INFO.CNTR_TYP = BPCSGMC.INFO.CNTR_TYP;
        BPRGFHIS.INFO.CCY = BPCSGMC.INFO.CCY;
        BPRGFHIS.INFO.CI_NO = BPCSGMC.INFO.CI_NO;
        BPRGFHIS.INFO.AC_NO = BPCSGMC.INFO.AC_NO;
        BPRGFHIS.INFO.REF = BPCSGMC.INFO.REF;
        BPRGFHIS.INFO.BR = BPCSGMC.INFO.BR;
        BPRGFHIS.INFO.MOD_NO = BPCSGMC.INFO.MOD_NO;
        BPRGFHIS.INFO.OTH_OLD = BPCSGMC.INFO.OTH_OLD;
        BPRGFHIS.INFO.OTH_NEW = BPCSGMC.INFO.OTH_NEW;
        for (WS_I = 1; WS_I <= 10; WS_I += 1) {
            BPRGFHIS.INFO.GLMS[WS_I-1].GLM1 = BPCSGMC.INFO.GLMS[WS_I-1].GLM1;
            BPRGFHIS.INFO.GLMS[WS_I-1].GLM2 = BPCSGMC.INFO.GLMS[WS_I-1].GLM2;
        }
        for (WS_I = 1; WS_I <= 76; WS_I += 1) {
            BPRGFHIS.INFO.AMTS[WS_I-1].AMT = BPCSGMC.INFO.AMTS[WS_I-1].AMT;
        }
        BPCPFHIS.DATA.REF_NO = BPCSGMC.INFO.REF;
        BPCPFHIS.DATA.PRINT_IND = 'N';
        BPCPFHIS.DATA.RLT_AC = BPCSGMC.INFO.AC_NO;
        BPCPFHIS.DATA.TX_VAL_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.TX_CCY = BPCSGMC.INFO.CCY;
        BPCPFHIS.DATA.TX_AMT = 1;
        BPCPFHIS.DATA.CI_NO = BPCSGMC.INFO.CI_NO;
        BPCPFHIS.DATA.REMARK = "GMC HISTORY DETAIL";
        BPCPFHIS.DATA.TX_DRCR_FLG = 'D';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGMC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRGFHIS);
        BPCPFHIS.DATA.FMT_LEN = 659;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, BPRGFHIS);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B040_DATA_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSGMC);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP217";
        SCCFMT.DATA_PTR = BPCSGMC;
        SCCFMT.DATA_LEN = 659;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        CEP.TRC(SCCGWA, BPCPFHIS.RC.RC_CODE);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            WS_MSG_ERR = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR);
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
        CEP.TRC(SCCGWA, BPCSGMC);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
