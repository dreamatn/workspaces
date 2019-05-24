package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT1292 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSPTES DCCSPTES = new DCCSPTES();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDPRT DCRCDPRT = new DCRCDPRT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCB1292_AWA_1292 DCB1292_AWA_1292;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "BEGENING");
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCOT1292 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB1292_AWA_1292>");
        DCB1292_AWA_1292 = (DCB1292_AWA_1292) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSPTES);
        CEP.TRC(SCCGWA, DCB1292_AWA_1292.APP_BR_C);
        CEP.TRC(SCCGWA, DCB1292_AWA_1292.TLR_NO);
        CEP.TRC(SCCGWA, DCB1292_AWA_1292.PROD_NO);
        CEP.TRC(SCCGWA, DCB1292_AWA_1292.CRT_DT);
        CEP.TRC(SCCGWA, DCB1292_AWA_1292.BAT_NO);
        CEP.TRC(SCCGWA, DCB1292_AWA_1292.CRT_SEQ);
        CEP.TRC(SCCGWA, DCB1292_AWA_1292.OPER_FLG);
        CEP.TRC(SCCGWA, DCB1292_AWA_1292.CONT_FLG);
        DCCSPTES.OPER_FLG = "01";
        DCCSPTES.APP_BR_C = DCB1292_AWA_1292.APP_BR_C;
        DCCSPTES.TLR_NO = DCB1292_AWA_1292.TLR_NO;
        DCCSPTES.PROD_CD = DCB1292_AWA_1292.PROD_NO;
        DCCSPTES.CRT_DT = DCB1292_AWA_1292.CRT_DT;
        DCCSPTES.APP_BAT_NO = DCB1292_AWA_1292.BAT_NO;
        DCCSPTES.CRT_SEQ = DCB1292_AWA_1292.CRT_SEQ;
        DCCSPTES.OPER_FLG = DCB1292_AWA_1292.OPER_FLG;
        DCCSPTES.CONT_FLG = DCB1292_AWA_1292.CONT_FLG;
        CEP.TRC(SCCGWA, DCCSPTES.APP_BR_C);
        CEP.TRC(SCCGWA, DCCSPTES.TLR_NO);
        CEP.TRC(SCCGWA, DCCSPTES.PROD_CD);
        CEP.TRC(SCCGWA, DCCSPTES.CRT_DT);
        CEP.TRC(SCCGWA, DCCSPTES.APP_BAT_NO);
        CEP.TRC(SCCGWA, DCCSPTES.CRT_SEQ);
        CEP.TRC(SCCGWA, DCCSPTES.OPER_FLG);
        CEP.TRC(SCCGWA, DCCSPTES.CONT_FLG);
        S000_CALL_DCZSPTES();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZSPTES() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-PSWD-ENVE-PRI", DCCSPTES);
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
