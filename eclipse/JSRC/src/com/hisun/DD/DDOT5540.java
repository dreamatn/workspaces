package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT5540 {
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
    DDCSLSPB DDCSLSPB = new DDCSLSPB();
    SCCGWA SCCGWA;
    DDB5540_AWA_5540 DDB5540_AWA_5540;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT5540 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB5540_AWA_5540>");
        DDB5540_AWA_5540 = (DDB5540_AWA_5540) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B300_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB5540_AWA_5540.DR_CARD);
        CEP.TRC(SCCGWA, DDB5540_AWA_5540.AC_NO);
        CEP.TRC(SCCGWA, DDB5540_AWA_5540.FUNC);
        if (DDB5540_AWA_5540.AC_NO.trim().length() == 0) {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            if (DDB5540_AWA_5540.AC_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DDB5540_AWA_5540.AC_NO);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB5540_AWA_5540.FUNC == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_MUST_INPUT;
            WS_FLD_NO = DDB5540_AWA_5540.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DDB5540_AWA_5540.FUNC == 'W' 
            && DDB5540_AWA_5540.HLD_FLG == ' ') {
            WS_MSGID = DDCMSG_ERROR_MSG.DD_HLD_FLG_M_INPUT;
            WS_FLD_NO = DDB5540_AWA_5540.FUNC_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_MSGID = DDCMSG_ERROR_MSG.DD_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSLSPB);
        DDCSLSPB.CARD_NO = DDB5540_AWA_5540.DR_CARD;
        DDCSLSPB.AC = DDB5540_AWA_5540.AC_NO;
        DDCSLSPB.FUNC = DDB5540_AWA_5540.FUNC;
        DDCSLSPB.LOST_NO = DDB5540_AWA_5540.LOST_NO;
        DDCSLSPB.PAY_TYPE = DDB5540_AWA_5540.PAY_TYPE;
        DDCSLSPB.PSWD = DDB5540_AWA_5540.PSWD;
        DDCSLSPB.ID_TYPE = DDB5540_AWA_5540.ID_TYPE;
        DDCSLSPB.ID_NO = DDB5540_AWA_5540.ID_NO;
        DDCSLSPB.HLD_FLG = DDB5540_AWA_5540.HLD_FLG;
        DDCSLSPB.REMARKS = DDB5540_AWA_5540.REMARKS;
        CEP.TRC(SCCGWA, DDB5540_AWA_5540.LOST_NO);
        CEP.TRC(SCCGWA, DDCSLSPB.LOST_NO);
        CEP.TRC(SCCGWA, DDB5540_AWA_5540.AC_NO);
        CEP.TRC(SCCGWA, DDB5540_AWA_5540.FUNC);
        S000_CALL_DDZSLSPB();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZSLSPB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-LOST-PSB", DDCSLSPB);
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
