package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT1192 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCCSLKLT DCCSLKLT = new DCCSLKLT();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDPRT DCRCDPRT = new DCRCDPRT();
    DCCUPSWC DCCUPSWC = new DCCUPSWC();
    DCCUCINF DCCUCINF = new DCCUCINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCB1192_AWA_1192 DCB1192_AWA_1192;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "BEGENING");
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCOT1192 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB1192_AWA_1192>");
        DCB1192_AWA_1192 = (DCB1192_AWA_1192) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSLKLT);
        CEP.TRC(SCCGWA, DCB1192_AWA_1192.CARD_NO);
        CEP.TRC(SCCGWA, DCB1192_AWA_1192.PRIM_NO);
        DCCSLKLT.FUNC = 'U';
        DCCSLKLT.VAL.CARD_LNK_NO = DCB1192_AWA_1192.CARD_NO;
        DCCSLKLT.VAL.KEY.CARD_NO = DCB1192_AWA_1192.PRIM_NO;
        DCCSLKLT.VAL.KEY.REGN_TYP = DCB1192_AWA_1192.REGN_TYP;
        DCCSLKLT.VAL.KEY.CHNL_NO = DCB1192_AWA_1192.CHNL_NO;
        DCCSLKLT.VAL.KEY.TXN_TYPE = DCB1192_AWA_1192.TXN_TYPE;
        DCCSLKLT.VAL.KEY.LMT_CCY = DCB1192_AWA_1192.LMT_CCY;
        DCCSLKLT.VAL.TXN_LMT_AMT = DCB1192_AWA_1192.TXN_LMT;
        DCCSLKLT.VAL.DLY_LMT_AMT = DCB1192_AWA_1192.DLY_LMT;
        DCCSLKLT.VAL.DLY_LMT_VOL = DCB1192_AWA_1192.DLY_LMTV;
        DCCSLKLT.VAL.MLY_LMT_AMT = DCB1192_AWA_1192.MLY_LMT;
        DCCSLKLT.VAL.MLY_LMT_VOL = DCB1192_AWA_1192.MLY_LMTV;
        DCCSLKLT.VAL.SYY_LMT_AMT = DCB1192_AWA_1192.SYY_LMT;
        DCCSLKLT.VAL.YLY_LMT_AMT = DCB1192_AWA_1192.YLY_LMT;
        DCCSLKLT.VAL.SE_LMT_AMT = DCB1192_AWA_1192.SE_LMT;
        DCCSLKLT.VAL.STA_DT = DCB1192_AWA_1192.CRT_DATE;
        DCCSLKLT.VAL.END_DT = DCB1192_AWA_1192.END_DATE;
        S000_CALL_DCZSLKLT();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZSLKLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-PRIM-CARD-LIMI", DCCSLKLT);
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
