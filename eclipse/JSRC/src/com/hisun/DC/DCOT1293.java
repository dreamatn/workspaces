package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT1293 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSPWRP DCCSPWRP = new DCCSPWRP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCB1293_AWA_1293 DCB1293_AWA_1293;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "BEGENING");
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCOT1293 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB1293_AWA_1293>");
        DCB1293_AWA_1293 = (DCB1293_AWA_1293) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSPWRP);
        CEP.TRC(SCCGWA, DCB1293_AWA_1293.APP_BR_C);
        CEP.TRC(SCCGWA, DCB1293_AWA_1293.TLR_NO);
        CEP.TRC(SCCGWA, DCB1293_AWA_1293.PROD_NO);
        CEP.TRC(SCCGWA, DCB1293_AWA_1293.CRT_DT);
        CEP.TRC(SCCGWA, DCB1293_AWA_1293.BAT_NO);
        CEP.TRC(SCCGWA, DCB1293_AWA_1293.PRIN_SEQ);
        CEP.TRC(SCCGWA, DCB1293_AWA_1293.CON_NUM);
        DCCSPWRP.APP_BR_C = DCB1293_AWA_1293.APP_BR_C;
        DCCSPWRP.TLR_NO = DCB1293_AWA_1293.TLR_NO;
        DCCSPWRP.PROD_NO = DCB1293_AWA_1293.PROD_NO;
        DCCSPWRP.CRT_DT = DCB1293_AWA_1293.CRT_DT;
        DCCSPWRP.BAT_NO = DCB1293_AWA_1293.BAT_NO;
        DCCSPWRP.PRIN_SEQ = DCB1293_AWA_1293.PRIN_SEQ;
        DCCSPWRP.CON_NUM = DCB1293_AWA_1293.CON_NUM;
        CEP.TRC(SCCGWA, DCCSPWRP.APP_BR_C);
        CEP.TRC(SCCGWA, DCCSPWRP.TLR_NO);
        CEP.TRC(SCCGWA, DCCSPWRP.PROD_NO);
        CEP.TRC(SCCGWA, DCCSPWRP.CRT_DT);
        CEP.TRC(SCCGWA, DCCSPWRP.BAT_NO);
        CEP.TRC(SCCGWA, DCCSPWRP.PRIN_SEQ);
        CEP.TRC(SCCGWA, DCCSPWRP.CON_NUM);
        S000_CALL_DCZSPWRP();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZSPWRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-PSWD-REPRINT", DCCSPWRP);
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
