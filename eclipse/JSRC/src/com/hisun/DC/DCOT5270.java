package com.hisun.DC;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5270 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_VIA_AC = " ";
    String WS_SUB_AC = " ";
    double WS_HOL_BAL = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_K = 0;
    char WS_AC_STS = ' ';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_RVS_EXP_DT = 0;
    int WS_CMIB_EXP_DT = 0;
    char WS_TBL_FLAG = ' ';
    char WS_IAACR_FLAG = ' ';
    char WS_SPAC_FLAG = ' ';
    char WS_MST_FLAG = ' ';
    char WS_CCY_FLAG = ' ';
    char WS_FIND_CCY = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCCIYJUP DCCIYJUP = new DCCIYJUP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCB5270_AWA_5270 DCB5270_AWA_5270;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCOT5270 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5270_AWA_5270>");
        DCB5270_AWA_5270 = (DCB5270_AWA_5270) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCCIYJUP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_PROCESS_UPD();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5270_AWA_5270.AC);
        if (DCB5270_AWA_5270.AC.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCB5270_AWA_5270.CCY);
        CEP.TRC(SCCGWA, DCB5270_AWA_5270.CCY_TYPE);
        CEP.TRC(SCCGWA, DCB5270_AWA_5270.STS);
        if (DCB5270_AWA_5270.STS != '1' 
            && DCB5270_AWA_5270.STS != '2' 
            && DCB5270_AWA_5270.STS != '3') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_STS_NO_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_PROCESS_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIYJUP);
        DCCIYJUP.IN_DATA.AC = DCB5270_AWA_5270.AC;
        DCCIYJUP.IN_DATA.CCY = DCB5270_AWA_5270.CCY;
        DCCIYJUP.IN_DATA.CCY_TYPE = DCB5270_AWA_5270.CCY_TYPE;
        DCCIYJUP.IN_DATA.STS = DCB5270_AWA_5270.STS;
        S000_CALL_DCZIYJUP();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZIYJUP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "S000-CALL-DCZIYJUP");
        IBS.CALLCPN(SCCGWA, "DC-I-UPD-YJ-STS", DCCIYJUP);
        CEP.TRC(SCCGWA, DCCIYJUP.RC);
        if (DCCIYJUP.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIYJUP.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
