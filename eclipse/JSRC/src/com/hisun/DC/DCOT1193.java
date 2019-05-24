package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT1193 {
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
    DCCUCINF DCCUCINF = new DCCUCINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCB1193_AWA_1193 DCB1193_AWA_1193;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "BEGENING");
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCOT1193 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB1193_AWA_1193>");
        DCB1193_AWA_1193 = (DCB1193_AWA_1193) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSLKLT);
        DCCSLKLT.FUNC = 'D';
        CEP.TRC(SCCGWA, DCB1193_AWA_1193.CARD_NO);
        CEP.TRC(SCCGWA, DCB1193_AWA_1193.PRIM_NO);
        DCCSLKLT.VAL.CARD_LNK_NO = DCB1193_AWA_1193.CARD_NO;
        DCCSLKLT.VAL.KEY.CARD_NO = DCB1193_AWA_1193.PRIM_NO;
        DCCSLKLT.VAL.KEY.REGN_TYP = DCB1193_AWA_1193.REGN_TYP;
        DCCSLKLT.VAL.KEY.CHNL_NO = DCB1193_AWA_1193.CHNL_NO;
        DCCSLKLT.VAL.KEY.TXN_TYPE = DCB1193_AWA_1193.TXN_TYPE;
        DCCSLKLT.VAL.KEY.LMT_CCY = DCB1193_AWA_1193.LMT_CCY;
        DCCSLKLT.VAL.TXN_LMT_AMT = DCB1193_AWA_1193.TXN_LMT;
        DCCSLKLT.VAL.DLY_LMT_AMT = DCB1193_AWA_1193.DLY_LMT;
        DCCSLKLT.VAL.DLY_LMT_VOL = DCB1193_AWA_1193.DLY_LMTV;
        DCCSLKLT.VAL.MLY_LMT_AMT = DCB1193_AWA_1193.MLY_LMT;
        DCCSLKLT.VAL.MLY_LMT_VOL = DCB1193_AWA_1193.MLY_LMTV;
        DCCSLKLT.VAL.SYY_LMT_AMT = DCB1193_AWA_1193.SYY_LMT;
        DCCSLKLT.VAL.YLY_LMT_AMT = DCB1193_AWA_1193.YLY_LMT;
        DCCSLKLT.VAL.SE_LMT_AMT = DCB1193_AWA_1193.SE_LMT;
        DCCSLKLT.VAL.STA_DT = DCB1193_AWA_1193.CRT_DATE;
        DCCSLKLT.VAL.END_DT = DCB1193_AWA_1193.END_DATE;
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
