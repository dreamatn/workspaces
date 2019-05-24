package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT1191 {
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
    DCB1191_AWA_1191 DCB1191_AWA_1191;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "BEGENING");
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCOT1191 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB1191_AWA_1191>");
        DCB1191_AWA_1191 = (DCB1191_AWA_1191) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSLKLT);
        DCCSLKLT.FUNC = 'A';
        CEP.TRC(SCCGWA, DCB1191_AWA_1191.FUNC);
        CEP.TRC(SCCGWA, DCB1191_AWA_1191.CARD_NO);
        CEP.TRC(SCCGWA, DCB1191_AWA_1191.PRIM_NO);
        DCCSLKLT.VAL.CARD_LNK_NO = DCB1191_AWA_1191.CARD_NO;
        DCCSLKLT.VAL.KEY.CARD_NO = DCB1191_AWA_1191.PRIM_NO;
        DCCSLKLT.VAL.KEY.REGN_TYP = DCB1191_AWA_1191.REGN_TYP;
        DCCSLKLT.VAL.KEY.CHNL_NO = DCB1191_AWA_1191.CHNL_NO;
        DCCSLKLT.VAL.KEY.TXN_TYPE = DCB1191_AWA_1191.TXN_TYPE;
        DCCSLKLT.VAL.KEY.LMT_CCY = DCB1191_AWA_1191.LMT_CCY;
        DCCSLKLT.VAL.TXN_LMT_AMT = DCB1191_AWA_1191.TXN_LMT;
        DCCSLKLT.VAL.DLY_LMT_AMT = DCB1191_AWA_1191.DLY_LMT;
        DCCSLKLT.VAL.DLY_LMT_VOL = DCB1191_AWA_1191.DLY_LMTV;
        DCCSLKLT.VAL.MLY_LMT_AMT = DCB1191_AWA_1191.MLY_LMT;
        DCCSLKLT.VAL.MLY_LMT_VOL = DCB1191_AWA_1191.MLY_LMTV;
        DCCSLKLT.VAL.SYY_LMT_AMT = DCB1191_AWA_1191.SYY_LMT;
        DCCSLKLT.VAL.YLY_LMT_AMT = DCB1191_AWA_1191.YLY_LMT;
        DCCSLKLT.VAL.SE_LMT_AMT = DCB1191_AWA_1191.SE_LMT;
        DCCSLKLT.VAL.STA_DT = DCB1191_AWA_1191.CRT_DATE;
        DCCSLKLT.VAL.END_DT = DCB1191_AWA_1191.END_DATE;
        CEP.TRC(SCCGWA, DCCSLKLT.VAL.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCCSLKLT.VAL.CARD_LNK_NO);
        CEP.TRC(SCCGWA, DCCSLKLT.VAL.KEY.REGN_TYP);
        CEP.TRC(SCCGWA, DCCSLKLT.VAL.KEY.CHNL_NO);
        CEP.TRC(SCCGWA, DCCSLKLT.VAL.KEY.TXN_TYPE);
        CEP.TRC(SCCGWA, DCCSLKLT.VAL.KEY.LMT_CCY);
        CEP.TRC(SCCGWA, DCCSLKLT.VAL.STA_DT);
        CEP.TRC(SCCGWA, DCCSLKLT.VAL.END_DT);
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
