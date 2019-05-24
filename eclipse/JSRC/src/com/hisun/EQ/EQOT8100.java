package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQOT8100 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_BSZ_BANKID = "01";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_INPUT_FLG = ' ';
    char WS_EQ_TYPE = ' ';
    char WS_AC_STS = ' ';
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQCQACT EQCQACT = new EQCQACT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EQB1100_AWA_1100 EQB1100_AWA_1100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQOT8100 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "EQB1100_AWA_1100>");
        EQB1100_AWA_1100 = (EQB1100_AWA_1100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EQ_BKID);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.CI_NO);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EQ_ACT);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EQ_CINO);
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EQ_AC);
        if (EQB1100_AWA_1100.EQ_BKID.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BANKID_MUST_INPUT;
            WS_FLD_NO = EQB1100_AWA_1100.EQ_BKID_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.EQ_TYP);
        WS_EQ_TYPE = EQB1100_AWA_1100.EQ_TYP;
        if ((WS_EQ_TYPE != '1' 
            && WS_EQ_TYPE != '2' 
            && WS_EQ_TYPE != '3' 
            && WS_EQ_TYPE != '4')) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_TYP;
            WS_FLD_NO = EQB1100_AWA_1100.EQ_TYP_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, EQB1100_AWA_1100.AC_STS);
        WS_AC_STS = EQB1100_AWA_1100.AC_STS;
        if ((WS_AC_STS != 'N' 
            && WS_AC_STS != 'C' 
            && WS_AC_STS != 'R')) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_AC_STS;
            WS_FLD_NO = EQB1100_AWA_1100.AC_STS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCQACT);
        EQCQACT.DATA.EQ_BKID = EQB1100_AWA_1100.EQ_BKID;
        EQCQACT.DATA.CI_NO = EQB1100_AWA_1100.CI_NO;
        EQCQACT.DATA.EQ_ACT = EQB1100_AWA_1100.EQ_ACT;
        EQCQACT.DATA.EQ_CINO = EQB1100_AWA_1100.EQ_CINO;
        EQCQACT.DATA.EQ_AC = EQB1100_AWA_1100.EQ_AC;
        EQCQACT.DATA.EQ_TYP = EQB1100_AWA_1100.EQ_TYP;
        EQCQACT.DATA.AC_STS = EQB1100_AWA_1100.AC_STS;
        EQCQACT.FUNC = 'B';
        S000_CALL_EQZQACT();
        if (pgmRtn) return;
    }
    public void S000_CALL_EQZQACT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "EQ-S-MAIN-EQAC", EQCQACT);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
