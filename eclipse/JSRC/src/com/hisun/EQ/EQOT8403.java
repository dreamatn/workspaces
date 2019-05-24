package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQOT8403 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
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
    BPRPARM BPRPARM;
    EQB0004_AWA_0004 EQB0004_AWA_0004;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQOT8403 return!");
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
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.O_FRZ_NO);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.EQ_ACT);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.EQ_CINO);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.EQ_AC);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.CI_NO);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.EQ_CNM);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.PSBK_NO);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.SPR_NO);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.SPR_NM);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.FRZ_QTY);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.EFF_DT);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.EXP_DT);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.REL_QTY);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.R_SPR_NM);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.R_SPR_NO);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.LAW_NM1);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.LAW_BNO1);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.LAW_ENO1);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.LAW_NM2);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.LAW_BNO2);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.LAW_ENO2);
        CEP.TRC(SCCGWA, EQB0004_AWA_0004.REMARK);
        if (EQB0004_AWA_0004.EQ_BKID.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BANKID_MUST_INPUT;
            WS_FLD_NO = EQB0004_AWA_0004.EQ_BKID_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0004_AWA_0004.REL_QTY == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_RELQTY_MUST_INPUT;
            WS_FLD_NO = EQB0004_AWA_0004.REL_QTY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            if (EQB0004_AWA_0004.REL_QTY <= 0) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_RELQTY_ERROR;
                WS_FLD_NO = EQB0004_AWA_0004.REL_QTY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            } else {
                if (EQB0004_AWA_0004.REL_QTY > EQB0004_AWA_0004.FRZ_QTY) {
                    WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_RELQTY_BIG;
                    WS_FLD_NO = EQB0004_AWA_0004.REL_QTY_NO;
                    S000_ERR_MSG_PROC_CONTINUE();
                    if (pgmRtn) return;
                }
            }
        }
        if (EQB0004_AWA_0004.R_SPR_NM.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_SPRNM_MUST_INPUT;
            WS_FLD_NO = EQB0004_AWA_0004.R_SPR_NM_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            if (!EQB0004_AWA_0004.R_SPR_NM.equalsIgnoreCase(EQB0004_AWA_0004.SPR_NM)) {
                WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_RSPRNM_ERROR;
                WS_FLD_NO = EQB0004_AWA_0004.R_SPR_NM_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        if (EQB0004_AWA_0004.R_SPR_NO.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_SPRNO_MUST_INPUT;
            WS_FLD_NO = EQB0004_AWA_0004.R_SPR_NO_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0004_AWA_0004.LAW_NM1.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_LAWNM1_MUST_INPUT;
            WS_FLD_NO = EQB0004_AWA_0004.LAW_NM1_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0004_AWA_0004.LAW_BNO1.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_LAWBN1_MUST_INPUT;
            WS_FLD_NO = EQB0004_AWA_0004.LAW_BNO1_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0004_AWA_0004.LAW_ENO1.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_LAWEN1_MUST_INPUT;
            WS_FLD_NO = EQB0004_AWA_0004.LAW_ENO1_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0004_AWA_0004.LAW_NM2.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_LAWNM2_MUST_INPUT;
            WS_FLD_NO = EQB0004_AWA_0004.LAW_NM2_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0004_AWA_0004.LAW_BNO2.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_LAWBN2_MUST_INPUT;
            WS_FLD_NO = EQB0004_AWA_0004.LAW_BNO2_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (EQB0004_AWA_0004.LAW_ENO2.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_LAWEN2_MUST_INPUT;
            WS_FLD_NO = EQB0004_AWA_0004.LAW_ENO2_NO;
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
        EQCSFRZ.DATA.FRZ_NO = EQB0004_AWA_0004.O_FRZ_NO;
        EQCSFRZ.DATA.EQ_AC = EQB0004_AWA_0004.EQ_AC;
        EQCSFRZ.DATA.FRZ_QTY = EQB0004_AWA_0004.FRZ_QTY;
        EQCSFRZ.DATA.REL_QTY = EQB0004_AWA_0004.REL_QTY;
        EQCSFRZ.DATA.R_SPR_NM = EQB0004_AWA_0004.R_SPR_NM;
        EQCSFRZ.DATA.R_SPR_NO = EQB0004_AWA_0004.R_SPR_NO;
        EQCSFRZ.DATA.LAW_NM1 = EQB0004_AWA_0004.LAW_NM1;
        EQCSFRZ.DATA.LAW_BNO1 = EQB0004_AWA_0004.LAW_BNO1;
        EQCSFRZ.DATA.LAW_ENO1 = EQB0004_AWA_0004.LAW_ENO1;
        EQCSFRZ.DATA.LAW_NM2 = EQB0004_AWA_0004.LAW_NM2;
        EQCSFRZ.DATA.LAW_BNO2 = EQB0004_AWA_0004.LAW_BNO2;
        EQCSFRZ.DATA.LAW_ENO2 = EQB0004_AWA_0004.LAW_ENO2;
        EQCSFRZ.DATA.REMARK = EQB0004_AWA_0004.REMARK;
        EQCSFRZ.FUNC = 'T';
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
