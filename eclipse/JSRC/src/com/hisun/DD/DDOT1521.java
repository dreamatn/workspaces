package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1521 {
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
    DDCSROZM DDCSROZM = new DDCSROZM();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    DDB1521_AWA_1521 DDB1521_AWA_1521;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT1521 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1521_AWA_1521>");
        DDB1521_AWA_1521 = (DDB1521_AWA_1521) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B300_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1521_AWA_1521.FUNC);
        CEP.TRC(SCCGWA, DDB1521_AWA_1521.BV_NO);
        CEP.TRC(SCCGWA, DDB1521_AWA_1521.REF_NO);
        CEP.TRC(SCCGWA, DDB1521_AWA_1521.NEW_BVNO);
        if (DDB1521_AWA_1521.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB1521_AWA_1521.BV_NO.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_BV_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB1521_AWA_1521.REF_NO.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_REF_NO_M_INPUT;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSROZM);
        DDCSROZM.FUNC = DDB1521_AWA_1521.FUNC;
        DDCSROZM.BV_NO = DDB1521_AWA_1521.BV_NO;
        DDCSROZM.REF_NO = DDB1521_AWA_1521.REF_NO;
        DDCSROZM.BV_CODE = "C00008";
        DDCSROZM.NEW_BV_NO = DDB1521_AWA_1521.NEW_BVNO;
        CEP.TRC(SCCGWA, DDCSROZM.FUNC);
        CEP.TRC(SCCGWA, DDCSROZM.BV_NO);
        CEP.TRC(SCCGWA, DDCSROZM.REF_NO);
        CEP.TRC(SCCGWA, DDCSROZM.BV_CODE);
        CEP.TRC(SCCGWA, DDCSROZM.NEW_BV_NO);
        S000_CALL_DDZSROZM();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZSROZM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-REOP-CCZM", DDCSROZM);
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
