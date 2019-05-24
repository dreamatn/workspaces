package com.hisun.CM;

import com.hisun.SC.*;
import com.hisun.DC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CMZS8830 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CM830";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    int WS_J = 0;
    CMZS8830_WS_BATH_PARM WS_BATH_PARM = new CMZS8830_WS_BATH_PARM();
    char WS_END_FLG = ' ';
    String WS_AC_NO = " ";
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCCURHLD DCCURHLD = new DCCURHLD();
    DCCUHLD DCCUHLD = new DCCUHLD();
    CMRBSP13 CMRBSP13 = new CMRBSP13();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCBATH SCCBATH;
    CMCS8830 CMCS8830;
    public void MP(SCCGWA SCCGWA, CMCS8830 CMCS8830) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCS8830 = CMCS8830;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CMZS8830 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCCUHLD);
        IBS.init(SCCGWA, DCCURHLD);
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT();
        if (pgmRtn) return;
        B200_HLD_PROC();
        if (pgmRtn) return;
        B300_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CMCS8830.TR_TYPE);
        CEP.TRC(SCCGWA, CMCS8830.REQ_JRN);
        CEP.TRC(SCCGWA, CMCS8830.REQ_DATE);
        CEP.TRC(SCCGWA, CMCS8830.REQ_SYS);
        CEP.TRC(SCCGWA, CMCS8830.REQ_CHNL);
        CEP.TRC(SCCGWA, CMCS8830.TL_NO);
        CEP.TRC(SCCGWA, CMCS8830.TR_BR);
        CEP.TRC(SCCGWA, CMCS8830.AP_REF);
        CEP.TRC(SCCGWA, CMCS8830.CHN_TYP);
        CEP.TRC(SCCGWA, CMCS8830.CUS_AC);
        CEP.TRC(SCCGWA, CMCS8830.AC_SEQ);
        CEP.TRC(SCCGWA, CMCS8830.CCY);
        CEP.TRC(SCCGWA, CMCS8830.CCY_TYP);
        CEP.TRC(SCCGWA, CMCS8830.RHLD_TYP);
        CEP.TRC(SCCGWA, CMCS8830.RAMT);
        CEP.TRC(SCCGWA, CMCS8830.HLD_NO);
        CEP.TRC(SCCGWA, CMCS8830.HLD_AMT);
        CEP.TRC(SCCGWA, CMCS8830.VAL_DATE);
        CEP.TRC(SCCGWA, CMCS8830.EXP_DATE);
        CEP.TRC(SCCGWA, CMCS8830.RSN);
        CEP.TRC(SCCGWA, CMCS8830.SMR);
        CEP.TRC(SCCGWA, CMCS8830.PROC_STS);
        CEP.TRC(SCCGWA, CMCS8830.RET_CODE);
        CEP.TRC(SCCGWA, CMCS8830.RET_MSG);
        CEP.TRC(SCCGWA, CMCS8830.DATE);
        CEP.TRC(SCCGWA, CMCS8830.JRNNO);
        CEP.TRC(SCCGWA, CMCS8830.VCH_NO);
        CEP.TRC(SCCGWA, CMCS8830.REM_AMT);
        if (CMCS8830.RHLD_TYP == '1' 
            && CMCS8830.CUS_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_ERR);
        }
        if (CMCS8830.RHLD_TYP == '1' 
            && CMCS8830.HLD_AMT == 0) {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_AMT_ERR);
        }
        if (CMCS8830.RHLD_TYP == '2') {
            if (CMCS8830.HLD_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_HLD_NO_MUST_IN);
            }
            if (CMCS8830.RAMT == 0) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_TXN_AMT_ERR);
            }
        }
    }
    public void B200_HLD_PROC() throws IOException,SQLException,Exception {
        if (CMCS8830.RHLD_TYP == '1') {
            DCCUHLD.DATA.AC = CMCS8830.CUS_AC;
            DCCUHLD.DATA.SEQ = CMCS8830.AC_SEQ;
            DCCUHLD.DATA.SPR_NO = CMCS8830.HLD_NO;
            DCCUHLD.DATA.HLD_TYP = '2';
            DCCUHLD.DATA.SPR_TYP = '2';
            DCCUHLD.DATA.CCY = CMCS8830.CCY;
            DCCUHLD.DATA.CCY_TYP = CMCS8830.CCY_TYP;
            DCCUHLD.DATA.AMT = CMCS8830.HLD_AMT;
            DCCUHLD.DATA.EFF_DT = CMCS8830.VAL_DATE;
            DCCUHLD.DATA.EXP_DT = CMCS8830.EXP_DATE;
            DCCUHLD.DATA.RSN = CMCS8830.RSN;
            DCCUHLD.DATA.RMK = CMCS8830.SMR;
            S000_CALL_DCZUHLD();
            if (pgmRtn) return;
            CMCS8830.REM_AMT = CMCS8830.HLD_AMT;
        } else if (CMCS8830.RHLD_TYP == '2') {
            DCCURHLD.DATA.HLD_NO = CMCS8830.HLD_NO;
            DCCURHLD.DATA.RHLD_TYP = '2';
            DCCURHLD.DATA.RAMT = CMCS8830.RAMT;
            DCCURHLD.DATA.RSN = CMCS8830.RSN;
            DCCURHLD.DATA.RMK = CMCS8830.SMR;
            S000_CALL_DCZURHLD();
            if (pgmRtn) return;
            CMCS8830.REM_AMT = CMCS8830.RAMT;
        } else {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_ERR_INPUT_BUS_TYPE);
        }
    }
    public void B300_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = CMCS8830;
        SCCFMT.DATA_LEN = 614;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZUHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-HLD", DCCUHLD);
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD, true);
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
