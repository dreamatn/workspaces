package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DD.*;
import com.hisun.VT.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1298 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_S_INQ_HIST = "BP-S-INQ-HIST";
    char K_ERROR = 'E';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_EVET_AMT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    VTCPQTAX VTCPQTAX = new VTCPQTAX();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    BPB1298_AWA_1298 BPB1298_AWA_1298;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1298 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1298_AWA_1298>");
        BPB1298_AWA_1298 = (BPB1298_AWA_1298) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_FEE_CANCEL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == K_ERROR 
            && SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_ID.MSG_CODE != 0) {
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B020_FEE_CANCEL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = BPB1298_AWA_1298.REB_AC;
        DDCUCRAC.CCY = "156";
        DDCUCRAC.CCY_TYPE = '1';
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.BANK_CR_FLG = 'Y';
        DDCUCRAC.TX_AMT = BPB1298_AWA_1298.RETR_AMT;
        DDCUCRAC.TX_MMO = "Q008";
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
        S000_OUT_EVENT();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_OUT_EVENT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.PROD_CODE = BPB1298_AWA_1298.FEE_CODE;
        BPCPOEWA.DATA.PROD_CODE_REL = BPB1298_AWA_1298.PROD_CD;
        BPCPOEWA.DATA.CNTR_TYPE = "FEES";
        BPCPOEWA.DATA.BR_OLD = BPB1298_AWA_1298.CHG_BR;
        BPCPOEWA.DATA.BR_NEW = BPB1298_AWA_1298.CHG_BR;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = BPB1298_AWA_1298.CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.THEIR_AC = BPB1298_AWA_1298.REB_AC;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = BPB1298_AWA_1298.REB_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        BPCPOEWA.DATA.CI_NO = CICACCU.DATA.CI_NO;
        IBS.init(SCCGWA, CICACCU);
        IBS.init(SCCGWA, VTCPQTAX);
        CICACCU.DATA.AGR_NO = BPB1298_AWA_1298.REB_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        VTCPQTAX.INPUT_DATA.CI_NO = CICACCU.DATA.CI_NO;
        VTCPQTAX.INPUT_DATA.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        VTCPQTAX.INPUT_DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        VTCPQTAX.INPUT_DATA.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        VTCPQTAX.INPUT_DATA.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        VTCPQTAX.INPUT_DATA.BR = BPB1298_AWA_1298.CHG_BR;
        VTCPQTAX.INPUT_DATA.CNTR_TYPE = "FEES";
        VTCPQTAX.INPUT_DATA.PROD_CD = BPB1298_AWA_1298.FEE_CODE;
        VTCPQTAX.INPUT_DATA.PROD_CD_REL = BPB1298_AWA_1298.PROD_CD;
        VTCPQTAX.INPUT_DATA.CCY = BPB1298_AWA_1298.CCY;
        S000_CALL_VTZPQTAX();
        if (pgmRtn) return;
        BPCPOEWA.DATA.EVENT_CODE = "QFFH";
        BPCPOEWA.DATA.AMT_INFO[2-1].AMT = BPB1298_AWA_1298.RETR_AMT;
        BPCPOEWA.DATA.AMT_INFO[8-1].AMT = VTCPQTAX.OUTPUT_DATA.TAX_AMT;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_VTZPQTAX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "VT-P-QUERY-TAX", VTCPQTAX);
        CEP.TRC(SCCGWA, VTCPQTAX.RC);
        if (VTCPQTAX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, VTCPQTAX.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        CEP.TRC(SCCGWA, VTCPQTAX.OUTPUT_DATA.TAX_AMT);
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_ERR_MSG);
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
