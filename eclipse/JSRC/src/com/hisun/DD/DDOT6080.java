package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT6080 {
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
    DDCSCOMI DDCSCOMI = new DDCSCOMI();
    SCCGWA SCCGWA;
    DDB6080_AWA_6080 DDB6080_AWA_6080;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT6080 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB6080_AWA_6080>");
        DDB6080_AWA_6080 = (DDB6080_AWA_6080) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B300_TRANS_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TEST-COMP-INT");
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB6080_AWA_6080.PRD_CODE);
        CEP.TRC(SCCGWA, DDB6080_AWA_6080.AMT);
        CEP.TRC(SCCGWA, DDB6080_AWA_6080.DAYS);
        CEP.TRC(SCCGWA, DDB6080_AWA_6080.CCY);
        CEP.TRC(SCCGWA, DDB6080_AWA_6080.TR_DT);
        CEP.TRC(SCCGWA, "INPUT CHECK");
        if (DDB6080_AWA_6080.AMT == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AMT_M_INPUT;
            WS_FLD_NO = DDB6080_AWA_6080.AMT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB6080_AWA_6080.DAYS == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_DAYS_M_INPUT;
            WS_FLD_NO = DDB6080_AWA_6080.DAYS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB6080_AWA_6080.CCY.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
            WS_FLD_NO = DDB6080_AWA_6080.CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_MSGID = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCOMI);
        DDCSCOMI.PRD_CODE = DDB6080_AWA_6080.PRD_CODE;
        DDCSCOMI.AMT = DDB6080_AWA_6080.AMT;
        DDCSCOMI.DAYS = DDB6080_AWA_6080.DAYS;
        DDCSCOMI.CCY = DDB6080_AWA_6080.CCY;
        DDCSCOMI.TR_DT = DDB6080_AWA_6080.TR_DT;
        S000_CALL_DDZSCOMI();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZSCOMI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-TRY-COM-INT", DDCSCOMI);
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
