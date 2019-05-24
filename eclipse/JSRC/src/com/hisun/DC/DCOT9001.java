package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT9001 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_DCZSZHWH = "DC-S-DCZSZHWH";
    String K_OUTPUT_FMT = "DC901";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    DCOT9001_WS_OUTPUT WS_OUTPUT = new DCOT9001_WS_OUTPUT();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCSZHWH DCCSZHWH = new DCCSZHWH();
    SCCGWA SCCGWA;
    DCB9001_AWA_9001 DCB9001_AWA_9001;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCOT9001 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB9001_AWA_9001>");
        DCB9001_AWA_9001 = (DCB9001_AWA_9001) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB9001_AWA_9001.FUNC);
        CEP.TRC(SCCGWA, DCB9001_AWA_9001.CARD_NO);
        CEP.TRC(SCCGWA, DCB9001_AWA_9001.AC_TYP);
        CEP.TRC(SCCGWA, DCB9001_AWA_9001.AC_NO);
        if (DCB9001_AWA_9001.FUNC == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCB9001_AWA_9001.FUNC != 'A' 
            && DCB9001_AWA_9001.FUNC != 'D') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCB9001_AWA_9001.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCB9001_AWA_9001.FUNC == 'D' 
            && DCB9001_AWA_9001.AC_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSZHWH);
        DCCSZHWH.FUNC = DCB9001_AWA_9001.FUNC;
        DCCSZHWH.CARD_NO = DCB9001_AWA_9001.CARD_NO;
        DCCSZHWH.AC_TYP = DCB9001_AWA_9001.AC_TYP;
        DCCSZHWH.AC_NO = DCB9001_AWA_9001.AC_NO;
        DCCSZHWH.PSW = DCB9001_AWA_9001.PSW;
        S000_CALL_DCZSZHWH();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZSZHWH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DCZSZHWH, DCCSZHWH);
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
