package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT9160 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCSPFEE DDCSPFEE = new DDCSPFEE();
    SCCGWA SCCGWA;
    DDB9160_AWA_9160 DDB9160_AWA_9160;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT9160 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB9160_AWA_9160>");
        DDB9160_AWA_9160 = (DDB9160_AWA_9160) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B300_TRANS_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TRY-COMP-FEE");
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB9160_AWA_9160.SEL_AC);
        CEP.TRC(SCCGWA, DDB9160_AWA_9160.OTH_AC);
        CEP.TRC(SCCGWA, DDB9160_AWA_9160.FLG);
        CEP.TRC(SCCGWA, DDB9160_AWA_9160.AMT);
        CEP.TRC(SCCGWA, DDB9160_AWA_9160.CCY);
        CEP.TRC(SCCGWA, DDB9160_AWA_9160.CCY_TYP);
        CEP.TRC(SCCGWA, DDB9160_AWA_9160.FEE_TYP);
        if (DDB9160_AWA_9160.SEL_AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB9160_AWA_9160.SEL_AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB9160_AWA_9160.OTH_AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            WS_FLD_NO = DDB9160_AWA_9160.SEL_AC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB9160_AWA_9160.AMT == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            WS_FLD_NO = DDB9160_AWA_9160.AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB9160_AWA_9160.CCY.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB9160_AWA_9160.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB9160_AWA_9160.CCY_TYP == ' ') {
            if (DDB9160_AWA_9160.CCY.equalsIgnoreCase("156")) {
                DDB9160_AWA_9160.CCY_TYP = '1';
            } else {
                WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY_TYP_INVALID;
                WS_FLD_NO = DDB9160_AWA_9160.CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        WS_MSGID = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSPFEE);
        DDCSPFEE.SEL_AC = DDB9160_AWA_9160.SEL_AC;
        DDCSPFEE.OTH_AC = DDB9160_AWA_9160.OTH_AC;
        DDCSPFEE.FLG = DDB9160_AWA_9160.FLG;
        DDCSPFEE.CCY = DDB9160_AWA_9160.CCY;
        DDCSPFEE.CCY_TYP = DDB9160_AWA_9160.CCY_TYP;
        DDCSPFEE.TRA_AMT = DDB9160_AWA_9160.AMT;
        DDCSPFEE.FEE_TYP = DDB9160_AWA_9160.FEE_TYP;
        S000_CALL_DDZSPFEE();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZSPFEE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-TEL-TRY-PFEE-FEE", DDCSPFEE);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_MSGID, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_FLD_NO);
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
