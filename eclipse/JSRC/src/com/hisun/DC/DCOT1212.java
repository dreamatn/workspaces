package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT1212 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_DCZSSWLW = "DC-S-DCZSSWLW";
    String CPN_DCZUCINF = "DC-U-CARD-INF";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String K_HIS_RMK = "ORAL REPORTING OF LOSE";
    int K_MAX_ROW = 99;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    DCOT1212_WS_OUTPUT WS_OUTPUT = new DCOT1212_WS_OUTPUT();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSSAGM DCCSSAGM = new DCCSSAGM();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDPRT DCRCDPRT = new DCRCDPRT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    int WS_DB_START_DATE = 0;
    int WS_DB_END_DATE = 0;
    String WS_DB_INCD_TYPE = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCB1212_AWA_1212 DCB1212_AWA_1212;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCOT1212 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB1212_AWA_1212>");
        DCB1212_AWA_1212 = (DCB1212_AWA_1212) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_SAGM_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB1212_AWA_1212.CARD_NO);
        if (DCB1212_AWA_1212.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_SAGM_INFO() throws IOException,SQLException,Exception {
        DCCSSAGM.FUNC = 'Q';
        DCCSSAGM.VAL.KEY.CARD_NO = DCB1212_AWA_1212.CARD_NO;
        DCCSSAGM.VAL.KEY.PRT_TYP = DCB1212_AWA_1212.PRT_TYP;
        DCCSSAGM.VAL.PRT_NO = DCB1212_AWA_1212.PRT_NO;
        DCCSSAGM.VAL.IN_FLG = DCB1212_AWA_1212.IN_FLG;
        DCCSSAGM.VAL.OUT_FLG = DCB1212_AWA_1212.OUT_FLG;
        DCCSSAGM.VAL.RMK = DCB1212_AWA_1212.RMK;
        S000_CALL_DCZSSAGM();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZSSAGM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SIGN-CARD-AGREEME", DCCSSAGM);
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
