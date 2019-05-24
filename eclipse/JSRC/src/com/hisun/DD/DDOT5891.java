package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5891 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCSGENT DDCSGENT = new DDCSGENT();
    SCCGWA SCCGWA;
    DDB5891_AWA_5891 DDB5891_AWA_5891;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT5891 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5891_AWA_5891>");
        DDB5891_AWA_5891 = (DDB5891_AWA_5891) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B300_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5891_AWA_5891.FUNC);
        CEP.TRC(SCCGWA, DDB5891_AWA_5891.CUS_AC);
        CEP.TRC(SCCGWA, DDB5891_AWA_5891.CCY);
        CEP.TRC(SCCGWA, DDB5891_AWA_5891.BAL);
        CEP.TRC(SCCGWA, DDB5891_AWA_5891.ID_TYP);
        CEP.TRC(SCCGWA, DDB5891_AWA_5891.ID_NO);
        CEP.TRC(SCCGWA, DDB5891_AWA_5891.CI_NM);
        if (DDB5891_AWA_5891.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDB5891_AWA_5891.FUNC != 'M' 
            && DDB5891_AWA_5891.FUNC != 'Q') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDB5891_AWA_5891.FUNC == 'M') {
            if (DDB5891_AWA_5891.ID_TYP.trim().length() == 0) {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_CUS_TYP_MISSING;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDB5891_AWA_5891.ID_NO.trim().length() == 0) {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_AG_ID_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDB5891_AWA_5891.CI_NM.trim().length() == 0) {
                WS_MSGID = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CI_NAME;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDB5891_AWA_5891.CUS_AC.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSGENT);
        DDCSGENT.FUNC = DDB5891_AWA_5891.FUNC;
        DDCSGENT.CUS_AC = DDB5891_AWA_5891.CUS_AC;
        DDCSGENT.LMT_CCY = DDB5891_AWA_5891.CCY;
        DDCSGENT.LMT_BAL = DDB5891_AWA_5891.BAL;
        DDCSGENT.AGID_TYP = DDB5891_AWA_5891.ID_TYP;
        DDCSGENT.AGID_NO = DDB5891_AWA_5891.ID_NO;
        DDCSGENT.AGID_NM = DDB5891_AWA_5891.CI_NM;
        CEP.TRC(SCCGWA, DDCSGENT.FUNC);
        CEP.TRC(SCCGWA, DDCSGENT.CUS_AC);
        CEP.TRC(SCCGWA, DDCSGENT.LMT_CCY);
        CEP.TRC(SCCGWA, DDCSGENT.LMT_BAL);
        CEP.TRC(SCCGWA, DDCSGENT.AGID_TYP);
        CEP.TRC(SCCGWA, DDCSGENT.AGID_NO);
        CEP.TRC(SCCGWA, DDCSGENT.AGID_NM);
        S000_CALL_DDZSGENT();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZSGENT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-MOQ-GENT", DDCSGENT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
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
