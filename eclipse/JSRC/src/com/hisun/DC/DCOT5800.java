package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCOT5800 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_MSGID = " ";
    short WS_FLD_NO = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCSELCD DCCSELCD = new DCCSELCD();
    SCCGWA SCCGWA;
    DCB5800_AWA_5800 DCB5800_AWA_5800;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, "CFX002");
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCOT5800 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DCB5800_AWA_5800>");
        DCB5800_AWA_5800 = (DCB5800_AWA_5800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CFX002   001");
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CFX002   002");
        B300_TRANS_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "OPEN-DD-AC");
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCB5800_AWA_5800.CI_NO);
        CEP.TRC(SCCGWA, DCB5800_AWA_5800.PROD_CD);
        CEP.TRC(SCCGWA, DCB5800_AWA_5800.AC_TYP);
        if (DCB5800_AWA_5800.CI_NO.trim().length() == 0) {
            WS_MSGID = DCCMSG_ERROR_MSG.DC_CINO_MUST_INPUT;
            if (DCB5800_AWA_5800.CI_NO.trim().length() == 0) WS_FLD_NO = 0;
            else WS_FLD_NO = Short.parseShort(DCB5800_AWA_5800.CI_NO);
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (DCB5800_AWA_5800.PROD_CD.trim().length() == 0) {
            WS_MSGID = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            WS_FLD_NO = DCB5800_AWA_5800.PROD_CD_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_MSGID = DCCMSG_ERROR_MSG.DC_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCSELCD);
        DCCSELCD.CI_NO = DCB5800_AWA_5800.CI_NO;
        DCCSELCD.PROD_CD = DCB5800_AWA_5800.PROD_CD;
        DCCSELCD.AC_NM = DCB5800_AWA_5800.AC_NM;
        DCCSELCD.AC_TYP = DCB5800_AWA_5800.AC_TYP;
        DCCSELCD.FACE_FLG = DCB5800_AWA_5800.FACE_FLG;
        DCCSELCD.PSW1 = DCB5800_AWA_5800.PSW1;
        DCCSELCD.PSW2 = DCB5800_AWA_5800.PSW2;
        DCCSELCD.IO_FLG = DCB5800_AWA_5800.IO_FLG;
        DCCSELCD.OTH_BNK = DCB5800_AWA_5800.OTH_BNK;
        DCCSELCD.OTH_BNK_NME = DCB5800_AWA_5800.OTH_BKNM;
        DCCSELCD.BIND_AC = DCB5800_AWA_5800.BIND_AC;
        S000_CALL_DCZSELCD();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZSELCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SVR-OPEN-ELE-CARD", DCCSELCD);
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
