package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQOT8400 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_INPUT_FLG = ' ';
    char WS_FRZ_STS = ' ';
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQCSFRZ EQCSFRZ = new EQCSFRZ();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EQB0004_AWA_0004 EQB0004_AWA_0004;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQOT8400 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "EQB0004_AWA_0004>");
        EQB0004_AWA_0004 = (EQB0004_AWA_0004) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.EQ_BKID);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.EQ_ACT);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.EQ_CINO);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.EQ_AC);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.CI_NO);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.EQ_CNM);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.ID_TYPE);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.ID_NO);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.SPR_NM);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.FRZ_STS);
        if (EQB0004_AWA_0004.EQ_BKID.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BANKID_MUST_INPUT;
            WS_FLD_NO = EQB0004_AWA_0004.EQ_BKID_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_FRZ_STS = EQB0004_AWA_0004.FRZ_STS;
        if ((WS_FRZ_STS != 'N' 
            && WS_FRZ_STS != 'C')) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_FRZ_STS;
            WS_FLD_NO = EQB0004_AWA_0004.FRZ_STS_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCSFRZ);
        EQCSFRZ.DATA.EQ_BKID = EQB0004_AWA_0004.EQ_BKID;
        EQCSFRZ.DATA.EQ_ACT = EQB0004_AWA_0004.EQ_ACT;
        EQCSFRZ.DATA.EQ_CINO = EQB0004_AWA_0004.EQ_CINO;
        EQCSFRZ.DATA.EQ_AC = EQB0004_AWA_0004.EQ_AC;
        EQCSFRZ.DATA.CI_NO = EQB0004_AWA_0004.CI_NO;
        EQCSFRZ.DATA.SPR_NM = EQB0004_AWA_0004.SPR_NM;
        EQCSFRZ.DATA.FRZ_STS = EQB0004_AWA_0004.FRZ_STS;
        WS_INPUT_FLG = 'N';
        EQCSFRZ.FUNC = 'R';
        if (EQCSFRZ.DATA.CI_NO.trim().length() > 0) {
            EQCSFRZ.SUB_FUNC = 'C';
            WS_INPUT_FLG = 'Y';
        }
        if (EQCSFRZ.DATA.EQ_ACT.trim().length() > 0 
            && WS_INPUT_FLG == 'N') {
            EQCSFRZ.SUB_FUNC = 'T';
            WS_INPUT_FLG = 'Y';
        }
        if (EQCSFRZ.DATA.EQ_CINO.trim().length() > 0 
            && WS_INPUT_FLG == 'N') {
            EQCSFRZ.SUB_FUNC = 'E';
            WS_INPUT_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_INPUT_FLG);
        if (WS_INPUT_FLG == 'N') {
            if (EQCSFRZ.DATA.EQ_AC.trim().length() > 0) {
                EQCSFRZ.FUNC = 'A';
            } else {
                EQCSFRZ.FUNC = 'B';
                if (EQCSFRZ.DATA.SPR_NM.trim().length() > 0) {
                    if (EQCSFRZ.DATA.FRZ_STS != ' ') {
                        EQCSFRZ.SUB_FUNC = 'B';
                    } else {
                        EQCSFRZ.SUB_FUNC = 'P';
                    }
                } else {
                    if (EQCSFRZ.DATA.FRZ_STS != ' ') {
                        EQCSFRZ.SUB_FUNC = 'S';
                    } else {
                        EQCSFRZ.SUB_FUNC = 'A';
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, EQCSFRZ.FUNC);
        CEP.TRC(SCCGWA, EQCSFRZ.SUB_FUNC);
        S000_CALL_EQZSFRZ();
        if (pgmRtn) return;
    }
    public void S000_CALL_EQZSFRZ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "EQ-S-MAIN-EQFRZ", EQCSFRZ);
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
