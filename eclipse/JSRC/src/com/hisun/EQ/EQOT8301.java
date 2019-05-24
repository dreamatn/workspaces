package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQOT8301 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_BSZ_BANKID = "01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQCMPPLG EQCMPPLG = new EQCMPPLG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EQB0003_AWA_0003 EQB0003_AWA_0003;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQOT8301 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "EQB0003_AWA_0003>");
        EQB0003_AWA_0003 = (EQB0003_AWA_0003) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQB0003_AWA_0003.EQ_BKID);
        if (EQB0003_AWA_0003.EQ_BKID.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BANKID_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQB0003_AWA_0003.PLG_QTY <= 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_PLG_QTY_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (EQB0003_AWA_0003.PLG_NUM.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_PLG_NUM_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCMPPLG);
        EQCMPPLG.DATA.EQ_BKID = EQB0003_AWA_0003.EQ_BKID;
        EQCMPPLG.DATA.CI_NO = EQB0003_AWA_0003.CI_NO;
        EQCMPPLG.DATA.CI_CNM = EQB0003_AWA_0003.EQ_CNM;
        EQCMPPLG.DATA.EQ_ACT = EQB0003_AWA_0003.EQ_ACT;
        EQCMPPLG.DATA.EQ_CINO = EQB0003_AWA_0003.EQ_CINO;
        EQCMPPLG.DATA.EQ_AC = EQB0003_AWA_0003.EQ_AC;
        EQCMPPLG.DATA.PLG_QTY = EQB0003_AWA_0003.PLG_QTY;
        EQCMPPLG.DATA.GRT_AMT = EQB0003_AWA_0003.GRT_AMT;
        EQCMPPLG.DATA.ASMT_AMT = EQB0003_AWA_0003.ASMT_AMT;
        EQCMPPLG.DATA.PLG_NUM = EQB0003_AWA_0003.PLG_NUM;
        EQCMPPLG.DATA.REMARK = EQB0003_AWA_0003.REMARK;
        EQCMPPLG.FUNC = 'A';
        CEP.TRC(SCCGWA, EQCMPPLG.FUNC);
        CEP.TRC(SCCGWA, EQCMPPLG.SUB_FUNC);
        S000_CALL_EQZSPLG();
        if (pgmRtn) return;
    }
    public void S000_CALL_EQZSPLG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "EQ-S-MAIN-PLG", EQCMPPLG);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
