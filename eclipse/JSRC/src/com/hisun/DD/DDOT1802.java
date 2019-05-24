package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDOT1802 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCSQCLR DDCSQCLR = new DDCSQCLR();
    SCCGWA SCCGWA;
    DDB1800_AWA_1800 DDB1800_AWA_1800;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDOT1802 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "DDB1800_AWA_1800>");
        DDB1800_AWA_1800 = (DDB1800_AWA_1800) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B300_TRANS_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.DR_CARD);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.ACNO);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.AC_CNM);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.AC_ENM);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.CCY);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.CCY_TYP);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.PSBK_NO);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.DRW_PSW);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.PAY_MTH);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.ID_TYP);
        CEP.TRC(SCCGWA, DDB1800_AWA_1800.ID_NO);
        if (DDB1800_AWA_1800.ACNO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQCLR);
        DDCSQCLR.DR_CARD = DDB1800_AWA_1800.DR_CARD;
        DDCSQCLR.AC_NO = DDB1800_AWA_1800.ACNO;
        DDCSQCLR.AC_CNM = DDB1800_AWA_1800.AC_CNM;
        DDCSQCLR.AC_ENM = DDB1800_AWA_1800.AC_ENM;
        DDCSQCLR.CCY = DDB1800_AWA_1800.CCY;
        CEP.TRC(SCCGWA, DDCSQCLR.CCY);
        DDCSQCLR.CCY_TYPE = DDB1800_AWA_1800.CCY_TYP;
        DDCSQCLR.PSBK_NO = DDB1800_AWA_1800.PSBK_NO;
        DDCSQCLR.DRW_PSW = DDB1800_AWA_1800.DRW_PSW;
        DDCSQCLR.PAY_TYP = DDB1800_AWA_1800.PAY_MTH;
        DDCSQCLR.ID_TYPE = DDB1800_AWA_1800.ID_TYP;
        DDCSQCLR.ID_NO = DDB1800_AWA_1800.ID_NO;
        S000_CALL_DDZSQCLR();
        if (pgmRtn) return;
    }
    public void S000_CALL_DDZSQCLR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-INQ-CLR", DDCSQCLR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
