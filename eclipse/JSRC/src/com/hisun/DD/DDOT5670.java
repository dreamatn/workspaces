package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5670 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCUNARR BPCUNARR = new BPCUNARR();
    SCCGWA SCCGWA;
    DDB5670_AWA_5670 DDB5670_AWA_5670;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT5670 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5670_AWA_5670>");
        DDB5670_AWA_5670 = (DDB5670_AWA_5670) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B300_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDB5670_AWA_5670.AC_DATE == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TR_DATE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDB5670_AWA_5670.JRN_NO == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TR_JRN_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDB5670_AWA_5670.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5670_AWA_5670.AC_DATE);
        CEP.TRC(SCCGWA, DDB5670_AWA_5670.JRN_NO);
        CEP.TRC(SCCGWA, DDB5670_AWA_5670.AC);
        CEP.TRC(SCCGWA, DDB5670_AWA_5670.RLT_AC);
        CEP.TRC(SCCGWA, DDB5670_AWA_5670.RLT_NAME);
        CEP.TRC(SCCGWA, DDB5670_AWA_5670.RLT_BR);
        CEP.TRC(SCCGWA, DDB5670_AWA_5670.REMARK);
        CEP.TRC(SCCGWA, DDB5670_AWA_5670.NARRTIVE);
        IBS.init(SCCGWA, BPCUNARR);
        BPCUNARR.FUNC = 'R';
        BPCUNARR.INP_DATA.AC_DT = DDB5670_AWA_5670.AC_DATE;
        BPCUNARR.INP_DATA.JRNNO = DDB5670_AWA_5670.JRN_NO;
        BPCUNARR.INP_DATA.AC = DDB5670_AWA_5670.AC;
        BPCUNARR.INP_DATA.RLT_AC = DDB5670_AWA_5670.RLT_AC;
        BPCUNARR.INP_DATA.RLT_AC_NAME = DDB5670_AWA_5670.RLT_NAME;
        BPCUNARR.INP_DATA.RLT_BANK = DDB5670_AWA_5670.RLT_BR;
        BPCUNARR.INP_DATA.REMARK = DDB5670_AWA_5670.REMARK;
        BPCUNARR.INP_DATA.NARRATIVE = DDB5670_AWA_5670.NARRTIVE;
        S000_CALL_BPZUNARR();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZUNARR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-UPD-HIS-NARR", BPCUNARR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
